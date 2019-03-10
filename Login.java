import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    {

    }

    public void createJFrame() {
        JFrame jframe = new JFrame("登录");
        jframe.setSize(500, 350);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        //显示位置是屏幕的宽度-JFrame宽度的一半，高度类似。
        jframe.setLocation((screenSize.width - 500) / 2, (screenSize.height - 350) / 2);
        Container panel = jframe.getContentPane();
        panel.setLayout(null);

        JLabel welcome = new JLabel("用户登录 USER LOGIN");
        welcome.setBounds(120, 20, 300, 50);
        panel.add(welcome);

        JLabel name = new JLabel("用户名：");
        name.setBounds(100, 100, 100, 30);
        JTextField nameinput = new JTextField();
        nameinput.setBounds(180, 100, 200, 30);

        JLabel password = new JLabel("密    码：");
        password.setBounds(100, 160, 100, 30);
        JPasswordField pswinput = new JPasswordField();
        pswinput.setBounds(180, 160, 200, 30);

        JLabel jurisdiction = new JLabel("权    限");
        jurisdiction.setBounds(100,220,100,30);
        String[] jd = {"管理员", "员工"};
        JComboBox jComboBox=new JComboBox(jd);
        jComboBox.setBounds(180,220,100,30);


        panel.add(name);
        panel.add(password);
        panel.add(nameinput);
        panel.add(pswinput);
        panel.add(jurisdiction);

        JButton ok = new JButton("登录");
        ok.setBounds(140, 230, 100, 30);
        panel.add(ok);

        JButton cancel = new JButton("取消");
        cancel.setBounds(260, 230, 100, 30);
        panel.add(cancel);
        jframe.setVisible(true);

        ok.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameinput.getText();
                String psw = new String(pswinput.getPassword());
                if (name.equals("哈哈哈") && psw.equals("666")) {
                    JOptionPane.showMessageDialog(null, "登陆成功！");
                    jframe.dispose();//关闭释放
                } else {
                    int n = JOptionPane.showConfirmDialog(null, "账号密码不一致！是否重新输入？",
                            "消息", JOptionPane.YES_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        nameinput.setText(" ");
                        pswinput.setText(" ");
                        count++;
                    } else {
                        System.exit(0);
                    }
                    if (count == 3) {
                        JOptionPane.showMessageDialog(null, "输入错误超过三次！程序结束");
                        System.exit(0);
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
