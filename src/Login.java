import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

    JFrame frame;
    JLabel label1,label2;
    JTextField textField1;
    JPasswordField textField2;
    JButton button1,button2;

    Login(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setBackground(Color.white);
        this.setLayout(null);

        label1 = new JLabel("Username");
        label1.setBounds(40,20,100,30);

        label2 = new JLabel("Password");
        label2.setBounds(40,70,100,30);

        textField1 = new JTextField();
        textField1.setBounds(150,20,150,30);

        textField2 = new JPasswordField();
        textField2.setBounds(150,70,150,30);

        button1 = new JButton("Login");
        button1.setBounds(40,140,120,30);
        button1.setFont(new Font("serif",Font.BOLD,15));
        button1.addActionListener(this);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);
        button1.setFocusable(false);

        button2 = new JButton("Cancel");
        button2.setBounds(180,140,120,30);
        button2.setFont(new Font("serif",Font.BOLD,15));
        button2.addActionListener(this);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.white);
        button2.setFocusable(false);

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/indir.jfif"));
        Image i2 = icon1.getImage().getScaledInstance(155,175,Image.SCALE_DEFAULT);
        ImageIcon iconLogin = new ImageIcon(i2);
        JLabel label3 = new JLabel(iconLogin);
        label3.setBounds(350,20,150,150);

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
        try {
            conn conn1 = new conn();
            String name = textField1.getText();
            String password = textField2.getText();

            String query = "select * from login .... ";
            ResultSet rs = conn1.statement.executeQuery(query);
            if(rs.next()){
                this.setVisible(false);
                //yeni sayfa ac
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid login");
                setVisible(false);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
