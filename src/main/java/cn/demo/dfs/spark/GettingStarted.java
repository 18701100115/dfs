package cn.demo.dfs.spark;


import cn.demo.dfs.mongo.Account;
import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.ReadConfig;
import com.mongodb.spark.config.WriteConfig;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import org.junit.Test;

import java.util.*;

public  class GettingStarted implements java .io.Serializable {
    public JavaSparkContext getSparkSession(String collectionName){
        System.setProperty("HADOOP_USER_NAME","root");
        System.setProperty("hadoop.home.dir", "D:\\hadoop-3.1.2");
        SparkSession spark = SparkSession.builder()
                .master("local")
                .appName("MongoSparkConnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://admin:admin123@192.168.37.132:27017/test_database.spark?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false")
                .config("spark.mongodb.output.uri", "mongodb://admin:admin123@192.168.37.132:27017/test_database.spark?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false")
                .getOrCreate();

        // Create a JavaSparkContext using the SparkSession's SparkContext object
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        // More application logic would go here...

        // Create a custom WriteConfig
//        Map<String, String> writeOverrides = new HashMap<>();
//        writeOverrides.put("collection", collectionName);
//        writeOverrides.put("writeConcern.w", "majority");
//        WriteConfig.create(jsc).withOptions(writeOverrides);
        return jsc;
    }
    @Test
    public   void sparkWrite(){
        /* Create the SparkSession.
         * If config arguments are passed from the command line using --conf,
         * parse args for the values to set.
         */
//        System.setProperty("HADOOP_USER_NAME","root");
//        System.setProperty("hadoop.home.dir", "D:\\hadoop-3.1.2");
//        System.out.println(System.getenv("HADOOP_HOME"));
//        SparkSession spark = SparkSession.builder()
//                .master("local")
//                .appName("MongoSparkConnectorIntro")
//                .config("spark.mongodb.input.uri", "mongodb://admin:admin123@192.168.37.132:27017/test_database.spark?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false")
//                .config("spark.mongodb.output.uri", "mongodb://admin:admin123@192.168.37.132:27017/test_database.spark?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false")
//                .getOrCreate();
//
//        // Create a JavaSparkContext using the SparkSession's SparkContext object
//          JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
//        // More application logic would go here...
//
//        // Create a custom WriteConfig
//        Map<String, String> writeOverrides = new HashMap<>();
//        writeOverrides.put("collection", "spark");
//        writeOverrides.put("writeConcern.w", "majority");
//        WriteConfig writeConfig = WriteConfig.create(jsc).withOptions(writeOverrides);
//        WriteConfig writeConfig = WriteConfig.create(jsc);
        // Create a RDD of 10 documents
        List<Account> list =  new ArrayList<Account>();
        for(int i=0;i<10000;i++){
            list.add(new Account(RandomStringUtils.randomAlphanumeric(12),"用户"+RandomStringUtils.randomAlphanumeric(12),"123456",new Date()));
        }
        JavaSparkContext jsc = getSparkSession("spark");
        JavaRDD<org.bson.Document> sparkDocuments = jsc.parallelize(list).map
                (new Function<Account,Document>() {
                    public  Document call(final Account i) throws Exception {
                        return Document.parse(JSON.toJSONString(i));
                    }
                });
        /*Start Example: Save data from RDD to MongoDB*****************/
        long startTime = System.currentTimeMillis();
        MongoSpark.save(sparkDocuments);
        /*End Example**************************************************/
//        jsc.close();
        System.out.println((System.currentTimeMillis()-startTime)/1000);
    }
    @Test
    public void mongoWirte(){

        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://admin:admin123@192.168.37.132:27017/test_database?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false");
//通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test_database");
        MongoCollection<Document> collection = mongoDatabase.getCollection("spark");
        List<Document> list = new ArrayList<Document>();
        for(int i=0;i<5000000;i++){
            Document document = new Document("id",i)
                    .append("userName", "用户"+RandomStringUtils.randomAlphanumeric(12))
                    .append("passwWord", "123456");
            list.add(document);
        }
        long startTime = System.currentTimeMillis();
        collection.insertMany(list);
        System.out.println("插入时间============="+(System.currentTimeMillis()-startTime)/1000);
    }
    @Test
    public void sparkRead(){
        System.setProperty("HADOOP_USER_NAME","root");
        System.setProperty("hadoop.home.dir", "D:\\hadoop-3.1.2");
        SparkSession spark = SparkSession.builder()
                .master("local")
                .appName("MongoSparkConnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://admin:admin123@192.168.37.132:27017/test_database.spark?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false")
                .config("spark.mongodb.output.uri", "mongodb://admin:admin123@192.168.37.132:27017/test_database.spark?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false")
                .getOrCreate();

        // Create a JavaSparkContext using the SparkSession's SparkContext object
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        Dataset<Row> implicitDS = MongoSpark.load(jsc).toDF();
        implicitDS.printSchema();
        implicitDS.show();

        // Load data with explicit schema
        Dataset<Character> explicitDS = MongoSpark.load(jsc).toDS(Character.class);
        explicitDS.printSchema();
        explicitDS.show();

        // Create the temp view and execute the query
        explicitDS.createOrReplaceTempView("characters");
        Dataset<Row> centenarians = spark.sql("SELECT id,userName, passWord FROM spark WHERE id <= 100");
        centenarians.show();

        jsc.close();
    }
}


