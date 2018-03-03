package com.cslg.gfjkpt.listener;

import com.cslg.gfjkpt.common.ApplicationContextHelper;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.*;

@WebListener
public class SocketListener implements ServletContextListener {

    /**
     * socket连接端口
     */
    private int port;

    /**
     * 客户端断开
     */
    private static final String CLIENT_BREAK = "Connection reset";

    private static final Logger logger = LoggerFactory.getLogger(SocketListener.class);

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    private static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
    //private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new SynchronousQueue<>());

    private void socketReceive(ServerSocket serverSocket) {
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("成功连接到客户端");
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleSocket(socket);
                    }
                });
            } catch (IOException e) {
                logger.error("连接客户端失败", e);
                e.printStackTrace();
            }

        }
    }

    private void handleSocket(Socket socket) {
        try {
            while(true) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = bufferedReader.readLine();
                if(!StringUtils.isBlank(message)) {
                    System.out.println(message);
                    //handleMessage(message);
                }else if(message == null) {
                    System.out.println("客户端关闭, 等待下一次连接....");
                    break;
                }
            }
        } catch (SocketException e) {
            if(CLIENT_BREAK.equals(e.getMessage())) {
                System.out.println("客户端非正常关闭, 等待下一次连接....");
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleMessage(String message) {
        //存入数据库
        InverterService inverterService = ApplicationContextHelper.getBean(InverterService.class);
        inverterService.saveInverterData(message);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        setPort(PropertyUtil.getProperty("SOCKET_PORT"));
        singleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(getPort());
                    socketReceive(serverSocket);
                } catch (IOException e) {
                    logger.error("创建ServerSocket出错", e);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        threadPool.shutdown();
        singleThreadPool.shutdown();
    }

    private int getPort() {
        return port;
    }

    private void setPort(String port) {
        this.port = Integer.parseInt(port);
    }
}
