package web;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tcp_ip.ServerConnection;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ServerConnection serverConnection =new ServerConnection();
        SpringApplication.run(Application.class, args);
        try {
            serverConnection.createConnection();
        } catch (IOException e) {
            Logger.getRootLogger().log(Level.TRACE,e);
            return;
        }
        System.out.println("Server started\n");
        try {
            serverConnection.listenConnection();
        } catch (IOException e) {
            Logger.getRootLogger().log(Level.TRACE,e);
            System.out.println("Error in server work");
        }
    }

    public static void startTCPServer(){

    }
}
