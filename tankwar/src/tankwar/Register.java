package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 模拟注册页面
 */
public class Register extends JFrame {
    JTextField tName;
    JPasswordField pass;
    JButton bLogin,bRegis;
    JPanel pCenter,pSouth;
    Register(){
        /**
         * 北面的标签
         */
        JLabel title=new JLabel("注册",JLabel.CENTER);
        this.add(title, BorderLayout.NORTH);

        /**
         * 中间
         */
        pCenter=new JPanel();
        tName=new JTextField("tom",37);
        pass=new JPasswordField("123",37);
        pCenter.add(new JLabel("姓名"));
        pCenter.add(tName);
        pCenter.add(new JLabel("密码"));
        pCenter.add(pass);
        this.add(pCenter);

        /**
         * 南边
         */
        pSouth=new JPanel();
        bRegis=new JButton("注册");  //事件源
        bRegis.addActionListener(new ActionListener() {  //给事件源注册监听器
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","sjr123456");
                    PreparedStatement pstmt = conn.prepareStatement("insert into user values(?,?)");
                    pstmt.setString(1,tName.getText());//指定第一个参数用注册界面用户名输入框中的内容代替
                    pstmt.setString(2,new String(pass.getPassword()));//指定第二个参数用注册页面密码输入框中的内容代替
                    int flag = pstmt.executeUpdate();
                    if(flag==1)
                        JOptionPane.showMessageDialog(Register.this,"insert success!");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pSouth.add(bRegis);
        add(pSouth,BorderLayout.SOUTH);

        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

//    public static void main(String[] args) {
//        new Register();
//    }


}
