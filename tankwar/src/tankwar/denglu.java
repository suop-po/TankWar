package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class denglu {
    public static void main(String[] args) throws SQLException {
        final boolean[] b = {false};
        final boolean[] c={false};
        int xx=0;

        String url = "jdbc:mysql://localhost:3306/db1";
        String user = "root";
        String passward = "sjr123456";
        Connection conn = DriverManager.getConnection(url,user, passward);
        Statement stat = conn.createStatement();

        JFrame denglu = new JFrame("登录窗口");
        JLabel zhanghao = new JLabel("账号");
        JLabel mima = new JLabel("密码");
        JTextField jtxz = new JTextField(20);
        JTextField jtxm = new JTextField(20);
        JButton d = new JButton("登录");
        JButton register  = new JButton("注册");
        JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jp3 = new JPanel();
        JPanel jp4=new JPanel (new FlowLayout(FlowLayout.CENTER));
        Box box = Box.createVerticalBox();

        //组装视图
        denglu.setVisible(true);
        denglu.setResizable(false);
        denglu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp1.add(zhanghao);
        jp1.add(jtxz);

        jp2.add(mima);
        jp2.add(jtxm);


        jp3.add(jp1, BorderLayout.SOUTH);
        jp3.add(jp2, BorderLayout.NORTH);
        box.add(jp3);
        jp4.add(d);jp4.add(register);
        box.add(jp4);
        denglu.add(box, BorderLayout.CENTER);
        denglu.setSize(400, 400);
        denglu.setLocationRelativeTo(null);


        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("登录")) {

                    String use = jtxz.getText().trim();
                    String pass = jtxm.getText().trim();

                    try {
                        ResultSet rs = stat.executeQuery("select *from uuuuu where kk='" + use + "'&&tt='" + pass + "';");
                        if (rs.next()) {
                            System.out.println("登录成功");
                            denglu.dispose();

                            //设置登录窗口不可见

                        } else {
                            //System.out.println("密码或账号错误");
                            JOptionPane.showMessageDialog(denglu,"密码或账号错误");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };

        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("登录")) {

                    String use = jtxz.getText().trim();
                    String pass = jtxm.getText().trim();
                    boolean x=false;
                    try {
                        ResultSet rs = stat.executeQuery("select *from uuuuu where kk='" + use + "'&&tt='" + pass + "';");
                        if (rs.next()) {
                            System.out.println("登录成功");
                            denglu.dispose();
                            b[0] =true;

                        } else {
                            System.out.println("密码或账号错误");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("注册")){
                    c[0]=false;
                    String use = jtxz.getText().trim();
                    String pass = jtxm.getText().trim();

                    try {
                        ResultSet rs  = stat.executeQuery("select *from uuuuu where kk='" + use + "';");
                        if (rs.next()){
                            System.out.println("注册失败，该账号已经存在");
                        }else {String sql="insert into uuuuu values ('"+use+"','"+pass+"');";
                            try {
                                int num=stat.executeUpdate(sql);
                                if (num!=0){
                                    System.out.println("注册成功");
                                }else {

                                    c[0]=true;
                                    System.out.println("注册失败");

                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });



//控制主游戏窗口
        while (true){
            System.out.print("");
            while (b[0]){

                new GamePanel().launch();
                xx++;
                if (xx!=0){
                    break;
                }
            }

            System.out.print("");
            if (c[0]){
                JOptionPane.showMessageDialog(null,"该账号已经存在","注册失败",JOptionPane.ERROR_MESSAGE);
                c[0]=false;
            }
        }




    }

}
