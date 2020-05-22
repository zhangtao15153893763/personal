package com.ajeesia.one2one;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import com.alibaba.fastjson.JSON;

/**
 * @Description: Websocket控制器
 * @Author: ajeesia
 * @UpdateDate: 2020/4/1 13:43
 * @Version: 1.0
 */
@Component
@ServerEndpoint("/chatRoom/{userName}")
public class SocketServerEndPoint {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    // 实现一对一聊天
    private static ConcurrentHashMap<String, SocketServerEndPoint> webSocketSet = new ConcurrentHashMap<String, SocketServerEndPoint>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 连接用户
    private static String userName = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public synchronized void onOpen(@PathParam(value = "userName") String userName, Session session) {

        this.userName = userName;
        this.session = session;

        webSocketSet.put(userName,this);     //加入set中

        addOnlineCount();           //在线数加1
        System.out.println("用户"+userName+"加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public synchronized void onClose() {
        webSocketSet.remove(userName);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("用户"+userName+"关闭！当前在线人数为" + getOnlineCount());

    }

    /**
     * 给指定用户发送消息
     * @param message
     */
    @OnMessage
    public synchronized void sendToUser(String message) {
        System.err.println(message);
        JSONObject json = JSON.parseObject(message);
        String sendUserName = json.getString("senderUserName");
        String toUserName = json.getString("toUserName");
        try {
            if (webSocketSet.get(toUserName) != null) {
                webSocketSet.get(toUserName).sendMessage(message);
            } else {
                System.out.println(message + "当前用户不在线");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    // 推送消息
    public synchronized void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    // 发生错误时调用
    @OnError
    public synchronized void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SocketServerEndPoint.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SocketServerEndPoint.onlineCount--;
    }

}
