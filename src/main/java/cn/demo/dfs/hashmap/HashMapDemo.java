package cn.demo.dfs.hashmap;

import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {

        HashMap<String,Object> hs = new HashMap(13);
        hs.put("1","ss");
        hs.put("2","ss");
        hs.put("3","ss");
        hs.put("4","ss");
        hs.put("5","ss");
        hs.put("6","ss");
        hs.put("7","ss");
        hs.put("8","ss");
//        hs.put("9","ss");
        String str = hs.put("9","ss1").toString();
        System.out.println(str);

//        for(String key : hs.keySet()){
//
//            int index = key.hashCode()/8;
//            System.out.println(String.format("key:%s,index:%s,value:%s",key,index,hs.get(key)));
//        }

    }
}
