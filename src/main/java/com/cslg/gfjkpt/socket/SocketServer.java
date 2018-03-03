//package com.cslg.gfjkpt.socket;
//
//import com.cslg.gfjkpt.model.Inverter;
//import com.cslg.gfjkpt.common.ApplicationContextHelper;
//import com.cslg.gfjkpt.service.InverterService;
//import org.apache.commons.lang.StringUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.SocketException;
//import java.util.Date;
//
///**
// * @author Twilight
// */
//public class SocketServer extends HttpServlet {
//
//    private static final int PORT = 10054;
//
//    /**
//     * 客户端断开
//     */
//    private static final String CLIENT_BREAK = "Connection reset";
//
//    private static InverterService inverterService = ApplicationContextHelper.getBean(InverterService.class);
//
//    private Thread t = null;
//
//    @Override
//    public void destroy() {
//        t.interrupt();
//    }
//
//    @Override
//    public void init() throws ServletException {
//        t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ServerSocket serverSocket = new ServerSocket(PORT);
//                    socketReceive(serverSocket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t.start();
//    }
//
//    private void socketReceive(ServerSocket serverSocket) {
//        try {
//            while(true) {
//                Socket socket = serverSocket.accept();
//                System.out.println("成功连接到客户端");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        handleSocket(socket);
//                    }
//                }).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handleSocket(Socket socket) {
//        try {
//            while(true) {
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String message = bufferedReader.readLine();
//                if(!StringUtils.isBlank(message)) {
//                    System.out.println(message);
//                    handleMessage(message);
//                }else if(message == null) {
//                    System.out.println("客户端关闭, 等待下一次连接....");
//                    break;
//                }
//            }
//        } catch (SocketException e) {
//            if(CLIENT_BREAK.equals(e.getMessage())) {
//                System.out.println("客户端非正常关闭, 等待下一次连接....");
//            } else {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void handleMessage(String message) {
//        //存入数据库
//        inverterService.saveInverterData(message);
//    }
//}
