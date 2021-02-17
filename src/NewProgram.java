import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProgram extends JFrame implements ActionListener {
    JLabel title;
    JLabel facultyAddImage;
    JLabel department_name;

    JTextField text_dept_name;

    JButton submit;
    JButton cancel;

    NewProgram(){
        this.setTitle("Add Program");
        this.setBackground(Color.white);
        this.setSize(900,700);
        this.setLayout(null);

        facultyAddImage = new JLabel();
        facultyAddImage.setBounds(0,0,900,700);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/studentAdd.png"));
        Image image = img.getImage().getScaledInstance(900,700,Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        facultyAddImage.setIcon(icon);


        title = new JLabel("New Program Details");
        title.setBounds(320,50,250,30);
        title.setFont(new Font(",Courier",Font.ITALIC,25));
        title.setForeground(Color.black);

        department_name = new JLabel("Department");
        department_name.setBounds(300,320,150,30);
        department_name.setFont(new Font("serif",Font.BOLD,15));

        text_dept_name = new JTextField();
        text_dept_name.setBounds(400,320,150,30);

        submit = new JButton("Submit");
        submit.setBounds(250,430,150,40);
        submit.setBackground(new Color(255,140,0));
        submit.setFocusable(false);
        submit.setForeground(Color.BLACK);
        submit.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBounds(450,430,150,40);
        cancel.setBackground(new Color(255,140,0));
        cancel.setFocusable(false);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);

        facultyAddImage.add(title);
        facultyAddImage.add(department_name);
        facultyAddImage.add(text_dept_name);
        facultyAddImage.add(submit);
        facultyAddImage.add(cancel);

        this.setResizable(false);
        this.add(facultyAddImage);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dept_name;
        if (e.getSource() == submit) {

            dept_name = text_dept_name.getText();

            try {
                conn connection = new conn();
                String insertQuery = "insert into department(department_name) values ('" + dept_name + "')";
                connection.statement.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null,"Department has been added successfully");
                text_dept_name.setText("");
            } catch (Exception ee) {
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry")){
                    JOptionPane.showMessageDialog(null, "Please enter a new department\nIt is already exists");
                    text_dept_name.setText("");
                }
            }
        }
        else if(e.getSource() == cancel){
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewProgram();
    }
}
