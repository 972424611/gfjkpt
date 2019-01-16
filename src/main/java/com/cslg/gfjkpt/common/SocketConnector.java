package com.cslg.gfjkpt.common;

import com.cslg.gfjkpt.utils.CodeUtil;

import java.net.Socket;

//用来web与socket服务通信,socket那边只能接受单个连接
public class SocketConnector {
    private static Socket socket=null;
    private SocketConnector(){

    }

    private static Socket getSocket(){
        if (socket==null||socket.isClosed()){
            try {
                socket=new Socket("127.0.0.1",10054);
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    public static boolean sentMsg(String msg){
        Socket socket=getSocket();
        if (socket==null){
            return false;
        }
        byte[] bytes=new byte[1];
        try {
            getSocket().getOutputStream().write(CodeUtil.hex2byte(msg));
            getSocket().getInputStream().read(bytes);
            if (CodeUtil.encode(bytes).equals("01")){
                return true;
            }else if (CodeUtil.encode(bytes).equals("02")){
                return false;
            }else if(CodeUtil.encode(bytes).equals("03")){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
