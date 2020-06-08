package cn.demo.dfs.utils;

import com.alibaba.fastjson.JSONObject;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SocketIOUtls {
    @Value("clientLoginUrl")
    String clientLoginUrl = "http://view.csslcloud.net/api/view/login/publisher?roomid=D6F1510372D466569C33DC5901307461&userid=8619146441B7FA6F&passwd=111&role=0&name=sadasd";
    @Value("createLiveUrl")
    String createLiveUrl = "http://zeus.csslcloud.net/api/live/create?userid=8619146441B7FA6F&roomid=D6F1510372D466569C33DC5901307461";
    @Value("startLiveUrl")
    String startLiveUrl = "http://zeus.csslcloud.net/api/live/start?liveid=188F5CC2C99EDC75&isrecord=1";


    public static Map<String, Socket> clientMap = new HashMap<String, Socket>();

    private static IO.Options getOptions(String query) {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"polling"};
        //失败重试次数
        options.reconnectionAttempts = 2;
        //失败重连的时间间隔
        options.reconnectionDelay = 1000;
        //连接超时时间(ms)
        options.timeout = 500;
        options.secure = true;
        options.query = query;
        return options;
    }

    public static void connect(String url, String query, String roomId) throws URISyntaxException {
        final Socket socketClient = IO.socket(url, getOptions(query));
        socketClient.on(Socket.EVENT_CONNECT, objects -> {
            System.out.println("client: " + "连接成功");
            clientMap.put(roomId, socketClient);
        })
                .on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"))
                .on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"))
                .on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
        socketClient.connect();
    }

    public void sendPusher(String roomId, String eventName, JSONObject messageText) {
        Socket socketClient = clientMap.get(roomId);
        if (null != socketClient) {
            socketClient.emit(eventName, messageText);
        }
    }

    public void disconnect(String roomId) {
        Socket socketClient = clientMap.get(roomId);
        if (null != socketClient) {
            socketClient.disconnect();
            clientMap.remove(socketClient);
        }
    }

    public static void main(String[] args) {
        String url = "https://io.csslcloud.net/D6F1510372D466569C33DC5901307461";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"polling"};
            //失败重试次数
            options.reconnectionAttempts = 2;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;
            options.secure = true;
//学员
//            options.query="sessionid=93F5DEE4AD99D2D04FA0438A5F5C845BF1DBB018D03A911FFDC649C57FCFF39B91E09E37E3481A0C471115D87DDCD96E&platform=1&terminal=0&secure=true&EIO=3&transport=websocket&sid=4xOZt_TJPzXHyb6RAABC";
// 讲师
//            options.query="sessionid=C7ED17FE6221F571DD9E7966DFC37CD1D210E5557EA031A26EB7188F536D022722F97139B755FA4A5161D40BF244267C&platform=0&terminal=0&EIO=3&t="+System.currentTimeMillis()+"-2&sid=gpZV3aNNHMK-OUSCAABh";
            final Socket socket = IO.socket(url, options);
            //监听自定义订阅事件
            Emitter emitter = socket.on("draw", object -> System.out.println("client: " + "订阅成功，收到反馈->" + Arrays.toString(object)));
            socket.on(Socket.EVENT_CONNECT, objects -> {
//                socket.emit("draw", "{\"action\":\"draw\",\"time\":100,\"value\":{\"fileName\":\"WhiteBorad\",\"data\":{\"alpha\":1,\"color\":16711695,\"docid\":\"{95491985-d038-46c8-9e88-11166ec37d53}\",\"draw\":[{\"x\":\"0.610000\",\"y\":\"0.450000\"},{\"x\":\"0.611855\",\"y\":\"0.448870\"},{\"x\":\"0.613709\",\"y\":\"0.447740\"},{\"x\":\"0.615564\",\"y\":\"0.446610\"},{\"x\":\"0.616982\",\"y\":\"0.445073\"},{\"x\":\"0.619411\",\"y\":\"0.442484\"},{\"x\":\"0.622850\",\"y\":\"0.438844\"},{\"x\":\"0.627300\",\"y\":\"0.434152\"},{\"x\":\"0.632761\",\"y\":\"0.428408\"},{\"x\":\"0.639232\",\"y\":\"0.421613\"},{\"x\":\"0.646714\",\"y\":\"0.413766\"},{\"x\":\"0.655207\",\"y\":\"0.404868\"},{\"x\":\"0.664710\",\"y\":\"0.394918\"},{\"x\":\"0.677493\",\"y\":\"0.381554\"},{\"x\":\"0.689596\",\"y\":\"0.368919\"},{\"x\":\"0.701020\",\"y\":\"0.357011\"},{\"x\":\"0.711765\",\"y\":\"0.345832\"},{\"x\":\"0.721831\",\"y\":\"0.335380\"},{\"x\":\"0.731217\",\"y\":\"0.325657\"},{\"x\":\"0.739925\",\"y\":\"0.316661\"},{\"x\":\"0.747953\",\"y\":\"0.308394\"},{\"x\":\"0.755302\",\"y\":\"0.300854\"},{\"x\":\"0.762624\",\"y\":\"0.293357\"},{\"x\":\"0.769262\",\"y\":\"0.286484\"},{\"x\":\"0.775215\",\"y\":\"0.280236\"},{\"x\":\"0.780484\",\"y\":\"0.274613\"},{\"x\":\"0.785068\",\"y\":\"0.269614\"},{\"x\":\"0.788969\",\"y\":\"0.265240\"},{\"x\":\"0.792184\",\"y\":\"0.261491\"},{\"x\":\"0.794716\",\"y\":\"0.258366\"},{\"x\":\"0.796563\",\"y\":\"0.255866\"}],\"drawid\":\"2020-06-08-15:17:23-494494\",\"drawtime\":194,\"height\":600,\"page\":\"1\",\"thickness\":2,\"type\":2,\"width\":1000},\"page\":1}}");
                System.out.println("client: " + "连接成功");
//                while (true){
//                }


            });
            socket.connect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}