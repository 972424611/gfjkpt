//package com.json.test;
//
//import org.junit.Test;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SocketTest {
//
//    private static final String URL = "39.108.5.210";
//
//    private static final int PORT = 10054;
//
//    @Test
//    public void test() {
//       // socketClient();
//        List<Thread> threadList = new ArrayList<Thread>();
//        for (int i = 0; i < 10; i++) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    socketClient();
//                }
//            });
//            threadList.add(thread);
//        }
//        for(Thread thread : threadList) {
//            thread.start();
//        }
//        for(Thread thread : threadList) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void socketClient() {
//        Socket socket = null;
//        try {
//            System.out.println(Thread.currentThread().getName());
//            socket = new Socket(URL, PORT);
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            int i = 0;
//            while(true) {
//                //Thread.sleep(1000);
//                bufferedWriter.write(Thread.currentThread().getName() + ": " + i++ + "\n");
//                bufferedWriter.flush();
//                Thread.sleep(300);
//            }
//
//            //bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (socket != null) {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
//
