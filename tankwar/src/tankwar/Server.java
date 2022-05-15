package tankwar;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

    //1.造ServerSocket
        try {
            System.out.println("Server准备就绪...");
            ServerSocket ss = new ServerSocket(9999);
            while (true) {
                Socket socket = ss.accept();
                new Thread(new Server_listen(socket)).start();
                new Thread(new Server_send(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
}
 }

 class Server_listen implements Runnable{
    private Socket socket;

    Server_listen(Socket socket){
        this.socket=socket;
    }

     @Override
     public void run() {
         try{
             ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
             while (true)
                 System.out.println(ois.readObject());
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             try {
                 socket.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
 }

 class Server_send implements Runnable{
    private Socket socket;
    Server_send(Socket socket){
        this.socket=socket;
    }

    @Override
     public void run() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true){
                //创建窗体对象：调用窗体的构造方法，制作窗体
                GamePanel frame = new GamePanel();
                frame.launch();
                frame.setVisible(true);


            }
        }catch (Exception e){
            e.printStackTrace();
        }
     }
 }