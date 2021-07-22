import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JLabel label1,label2;
    JTextField textField1;
    JPasswordField textField2;
    JButton button1,button2;
    static String Username;


    Login(){
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        this.setSize(600,300);
        this.setLayout(null);

        label1 = new JLabel("Username");
        label1.setBounds(40,50,100,30);

        label2 = new JLabel("Password");
        label2.setBounds(40,100,100,30);

        textField1 = new JTextField();
        textField1.setBounds(150,50,150,30);

        textField2 = new JPasswordField();
        textField2.setBounds(150,100,150,30);

        button1 = new JButton("Login");
        button1.setBounds(40,170,120,30);
        button1.setFont(new Font("serif",Font.BOLD,15));
        button1.addActionListener(this);
        button1.setBackground(new Color(255,140,0));
        button1.setForeground(Color.BLACK);
        button1.setFocusable(false);

        button2 = new JButton("Cancel");
        button2.setBounds(180,170,120,30);
        button2.setFont(new Font("serif",Font.BOLD,15));
        button2.addActionListener(this);
        button2.setBackground(new Color(255,140,0));
        button2.setForeground(Color.BLACK);
        button2.setFocusable(false);

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/indir.jfif"));
        Image i2 = icon1.getImage().getScaledInstance(155,175,Image.SCALE_DEFAULT);
        ImageIcon iconLogin = new ImageIcon(i2);
        JLabel label3 = new JLabel(iconLogin);
        label3.setBounds(350,48,150,150);

        this.getContentPane().setBackground(Color.white);
        this.add(label1);
        this.add(label2);
        this.add(textField1);
        this.add(textField2);
        this.add(button1);
        this.add(button2);
        this.add(label3);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            try {
                conn conn1 = new conn();
                String name = textField1.getText();
                String password = textField2.getText();

                String query = "select * from user where name= '" + name + "' and password= '" + password + "'";
                ResultSet rs = conn1.statement.executeQuery(query);

                if(name.equals("admin") && password.equals("aa")){
                    this.setVisible(false);
                    new ManagerPage().setVisible(true);
                }
                else if (rs.next()) {
                    String user_type = rs.getString("type");
                    if(user_type.equals("1")) {
                        this.setVisible(false);
                        new StudentPage().setVisible(true);
                        Username = name;
                    }
                    else if(user_type.equals("2")){
                        this.setVisible(false);
                        new TeacherPage().setVisible(true);
                        Username = name;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    textField2.setText("");
                    textField1.setText("");
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else{
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
