import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    public Login() {
    }

    public void createJFrame() {
        JFrame jframe = new JFrame("登录");
        jframe.setSize(500, 450);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        //显示位置是屏幕的宽度-JFrame宽度的一半，高度类似。
        jframe.setLocation((screenSize.width - 500) / 2, (screenSize.height - 450) / 2);
        Container panel = jframe.getContentPane();
        panel.setLayout(null);

        JLabel welcome = new JLabel("用户登录 USER LOGIN");
        //jlabel.setFont(new Font("Dialog",1,15));
        //“dialog”代表字体，1代表样式(1是粗体，0是平常的）15是字号
        welcome.setFont(new Font("黑体", 1, 14));
        welcome.setBounds(100, 20, 300, 50);
        panel.add(welcome);

        JLabel name = new JLabel("用户名：");
        name.setBounds(100, 100, 100, 30);
        JTextField nameinput = new JTextField();
        nameinput.setBounds(180, 100, 200, 30);

        JLabel password = new JLabel("密    码：");
        password.setBounds(100, 160, 100, 30);
        JPasswordField pswinput = new JPasswordField();
        pswinput.setBounds(180, 160, 200, 30);

        JLabel quanxian = new JLabel("权    限：");
        quanxian.setBounds(100, 220, 100, 30);
        String[] jd = {"管理员", "员工"};
        JComboBox jComboBox = new JComboBox(jd);
        jComboBox.setBounds(180, 220, 100, 30);

        JButton ok = new JButton("登录(O)");
        //键盘助记符   Alt+键盘上的一个键
        ok.setMnemonic('O');
        ok.setBounds(140, 280, 100, 30);


        JButton cancel = new JButton("退出(X)");
        cancel.setMnemonic('X');
        cancel.setBounds(260, 280, 100, 30);


        panel.add(name);
        panel.add(password);
        panel.add(nameinput);
        panel.add(pswinput);
        panel.add(quanxian);
        panel.add(jComboBox);
        panel.add(ok);
        panel.add(cancel);

        jframe.setVisible(true);

        ok.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameinput.getText();
                String psw = new String(pswinput.getPassword());

                if (name == null || psw == null || name.trim().equals("") || psw.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
                } else {
                    //加载类
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    //连接数据库
                    String url = "jdbc:mysql://localhost:3306/login";
                    String mysqlroot = "root";
                    String mysqlrootpsw = "root";

                    try {
                        Connection con = DriverManager.getConnection(url, mysqlroot, mysqlrootpsw);
                        String sql = "select * from admin";
                        PreparedStatement pst = con.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        String n = null;
                        String p = null;
                        while (rs.next()) {
                            n = rs.getString("用户名");
                            p = rs.getString("密码");
                            if (n.equals(name) && p.equals(psw)) {
                                JOptionPane.showMessageDialog(null, "登录成功！");
                                Demo demo = new Demo();
                                demo.createJFrame();
                                jframe.dispose();//关闭释放
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }
        });
    }
}
