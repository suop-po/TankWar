package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Start extends JFrame {
    private static Start s;
    private int flag = 0;
    JTextField tName;
    JPasswordField pass;
    JButton bLogin, bRegis;
    JPanel pCenter, pSouth;

    public static Start getS() {
        return s;
    }

    public static void setS(Start s) {
        Start.s = s;
    }

//    public int getFlag() {
//        return flag;
//    }
//
//    public boolean setFlag(int flag) {
//        this.flag = flag;
//
//        return false;
   // }


    Start(){
        /**
         * 北边：标签
         */

        JLabel title=new JLabel("登录",JLabel.CENTER);//JLabel.CENTER参数设置文字居中显示
        title.setFont(new Font("黑体",Font.BOLD,24));//设置字体为黑体，加粗，24磅大小
        title.setForeground(Color.BLUE);  //设置字的颜色为蓝色

        this.add(title,BorderLayout.NORTH);  //把标题标签添加到面板的北面

        /**
         * 中间：有用户名、密码框、提示标签信息
         */

        pCenter=new JPanel();    //设置中间的面板
        tName=new JTextField("",25);
        pass=new JPasswordField("",25);
        pCenter.add(new JLabel("姓名"));
        pCenter.add(tName);
        pCenter.add(new JLabel("密码"));
        pCenter.add(pass);

        this.add(pCenter);//把中间面板添加到边布局管理器的中间（没有第二个参数，默认添加到中间）

        /**
         * 南边：登录、注册按钮
         */
        pSouth=new JPanel();
        bLogin=new JButton("登录");  //按钮时事件源
        bLogin.addActionListener(new ActionListener() {
            @Override   //单击登录按钮后，以用户输入的用户名和密码为条件查询，找到可以登录，否则说明用户或密码错
            public void actionPerformed(ActionEvent actionEvent) {
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;

                try {
                    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","sjr123456");
                    pstmt=conn.prepareStatement("select * from user where username=? and password=?");
                    //封装sql语句到容器中(即PreparedStatement)中，根据用户名和密码查询记录，如果找到，只有一条记录（不存在重复的）
                    pstmt.setString(1,tName.getText());
                    pstmt.setString(2,new String(pass.getPassword()));
                    rs = pstmt.executeQuery();  //执行时不再需要sql语句
                    if(rs.next()){
                        JOptionPane.showMessageDialog(Start.this,"Login success!");
                     //   boolean b = s.setFlag(1);

                    }else{
                        JOptionPane.showMessageDialog(Start.this,"用户名或密码错，请重新输入！");
                        //清空用户名和密码输入框，方便用户重新输入
                        tName.setText("");
                        pass.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(rs!=null)
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    if(pstmt!=null)
                        try {
                            pstmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    if(conn!=null)
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }


            }
        });
        //用匿名内部类的形式为bRegis按钮注册监听器，处理动作事件ActionEvent
        bRegis=new JButton("注册");
        bRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               new Register();
            }
        });
        pSouth.add(bLogin);
        pSouth.add(bRegis);
        this.add(pSouth,BorderLayout.SOUTH);
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new Start();
     //   if(s.setFlag(1))
       // new GamePanel().launch();
    }

}
