package web.sockethandlers;

import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tcp_ip.AllClientsBase;
import tcp_ip.ServerCommunication;
import tcp_ip.channels.WebSocket;

import java.util.Map;

public class AgentSocketHandler extends TextWebSocketHandler {
    private AllClientsBase allClientsBase=AllClientsBase.getInstance();

    private Logger logger = Logger.getRootLogger();
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {

       // for(WebSocketSession webSocketSession : sessions) {
            Map value = new Gson().fromJson(message.getPayload(), Map.class);
            System.out.println(message.getPayload());
            if(value.containsKey("name")) {
                String name= (String) value.get("name");
                //webSocketSession.sendMessage(new TextMessage("Hello agent " + value.get("name") + " !"));
                allClientsBase.addNewAgent(new WebSocket(session),name);
                logger.log(Level.INFO, "Registered web agent " + name);
            }
            else ServerCommunication.getInstance().handleMessagesFromAutorizedUser(new WebSocket(session),value.get("message").toString());
        //}
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //sessions.remove(session);
        ServerCommunication.getInstance().handlingClientDisconnecting(new WebSocket(session));
        System.out.println("closed agent");
    }
}
