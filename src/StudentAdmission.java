import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentAdmission extends JFrame implements ActionListener {
    JLabel title;
    JLabel name;
    JLabel password;
    JLabel age;
    JLabel email;
    JLabel address;
    JLabel phone;
    JLabel birth;
    JLabel level;
    JLabel program;
    JLabel studentAddimage;

    JTextField text_name;
    JPasswordField text_password;
    JTextField text_age;
    JTextField text_email;
    JTextField text_address;
    JTextField text_phone;
    JTextField text_birth;

    JComboBox combo_level;
    JComboBox combo_program;

    JButton submit;
    JButton cancel;

    StudentAdmission(){
        this.setTitle("Add Student");
        this.setBackground(Color.white);
        this.setSize(900,700);
        this.setLayout(null);

        studentAddimage = new JLabel();
        studentAddimage.setBounds(0,0,900,700);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/studentAdd.png"));
        Image image = img.getImage().getScaledInstance(900,700,Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        studentAddimage.setIcon(icon);


        title = new JLabel("New Student Details");
        title.setBounds(320,50,250,30);
        title.setFont(new Font(",Courier",Font.ITALIC,25));
        title.setForeground(Color.black);

        name = new JLabel("Name");
        name.setBounds(100,150,100,30);
        name.setFont(new Font("serif",Font.BOLD,15));

        text_name = new JTextField();
        text_name.setBounds(200,150,150,30);

        age = new JLabel("Age");
        age.setBounds(100,250,100,30);
        age.setFont(new Font("serif",Font.BOLD,15));

        text_age = new JTextField();
        text_age.setBounds(200,250,150,30);

        email = new JLabel("E-mail");
        email.setBounds(100,350,100,30);
        email.setFont(new Font("serif",Font.BOLD,15));

        text_email = new JTextField();
        text_email.setBounds(200,350,150,30);

        address = new JLabel("Address");
        address.setBounds(100,450,100,30);
        address.setFont(new Font("serif",Font.BOLD,15));

        text_address = new JTextField();
        text_address.setBounds(200,450,150,30);

        phone = new JLabel("Phone");
        phone.setBounds(100,550,100,30);
        phone.setFont(new Font("serif",Font.BOLD,15));

        text_phone = new JTextField();
        text_phone.setBounds(200,550,150,30);

        birth = new JLabel("DOB");
        birth.setBounds(450,150,150,30);
        birth.setFont(new Font("serif",Font.BOLD,15));

        text_birth = new JTextField("yyyy-mm-dd");
        text_birth.setBounds(550,150,150,30);

        level = new JLabel("Level");
        level.setBounds(450,250,150,30);
        level.setFont(new Font("serif",Font.BOLD,15));


        String levels[] = {"Undergraduate","Master","PhD"};
        combo_level = new JComboBox(levels);
        combo_level.setBounds(550,250,150,30);
        combo_level.setBackground(Color.white);

        program = new JLabel("Program");
        program.setBounds(450,350,150,30);
        program.setFont(new Font("serif",Font.BOLD,15));

        ArrayList<String> programs = new ArrayList<String>();
        try {
            conn connection = new conn();
            String selectQuery = "select department_name from department";
            ResultSet rs = connection.statement.executeQuery(selectQuery);

            while(rs.next()){
                String department_name = rs.getString("department_name");
                programs.add(department_name);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        String[] programsArr = new String[programs.size()];
        for(int i = 0 ; i < programs.size(); i++){
            programsArr[i] = programs.get(i);
        }

        combo_program = new JComboBox(programsArr);
        combo_program.setBounds(550,350,150,30);
        combo_program.setBackground(Color.white);


        password = new JLabel("Password");
        password.setBounds(450,450,150,30);
        password.setFont(new Font("serif",Font.BOLD,15));

        text_password = new JPasswordField();
        text_password.setBounds(550,450,150,30);


        submit = new JButton("Submit");
        submit.setBounds(450,530,150,40);
        submit.setBackground(new Color(255,140,0));
        submit.setFocusable(false);
        submit.setForeground(Color.BLACK);
        submit.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBounds(620,530,150,40);
        cancel.setBackground(new Color(255,140,0));
        cancel.setFocusable(false);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);

        studentAddimage.add(title);
        studentAddimage.add(name);
        studentAddimage.add(age);
        studentAddimage.add(email);
        studentAddimage.add(address);
        studentAddimage.add(phone);
        studentAddimage.add(birth);
        studentAddimage.add(text_name);
        studentAddimage.add(text_age);
        studentAddimage.add(text_email);
        studentAddimage.add(text_address);
        studentAddimage.add(text_phone);
        studentAddimage.add(text_birth);
        studentAddimage.add(level);
        studentAddimage.add(combo_level);
        studentAddimage.add(program);
        studentAddimage.add(combo_program);
        studentAddimage.add(password);
        studentAddimage.add(text_password);
        studentAddimage.add(submit);
        studentAddimage.add(cancel);
        this.setResizable(false);
        this.add(studentAddimage);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name,password,age,e_mail,address,phone,dob,level,program;

        if(e.getSource() == submit){
            name = text_name.getText();
            password = text_password.getText();
            age = text_age.getText();
            e_mail = text_age.getText();
            address = text_address.getText();
            phone = text_phone.getText();
            dob = text_birth.getText();
            level = (String)combo_level.getSelectedItem();
            program = (String)combo_program.getSelectedItem();

            try{
                conn connection = new conn();

                String selectQuery = "select lid from level where level_name = '"+level+"'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);

                rs.next();
                String level_id = rs.getString("lid");
                int level_idInt = Integer.parseInt(level_id);

                String selectQueryDep = "select did from department where department_name = '"+program+"'";
                ResultSet rs2 = connection.statement.executeQuery(selectQueryDep);

                rs2.next();
                String department_id = rs2.getString("did");

                String query = "insert into user(name,password,age,e_mail,address,phone,birthdate,level_id,department_id,type) values ('"+name+"','"+password+"','"+age+"','"+e_mail+"','"+address+"','"+phone+"','"+dob+"',"+level_idInt+",'"+department_id+"','1')";
                connection.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Student has been added successfully");
                makeTextFieldEmpty();
            }
            catch (Exception ee){
                ee.printStackTrace();
                if((String.valueOf(ee).startsWith("java.sql.SQLException: Incorrect integer value:")) || String.valueOf(ee).startsWith("java.sql.SQLException: Data truncated")){
                    JOptionPane.showMessageDialog(null, "Please enter only integer values on age field");
                    makeTextFieldEmpty();
                }
                else if(String.valueOf(ee).startsWith("com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long")){
                    JOptionPane.showMessageDialog(null, "Please enter 11 numbers on phone field");
                    makeTextFieldEmpty();
                }
                else if(String.valueOf(ee).startsWith("com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect date value:")){
                    JOptionPane.showMessageDialog(null, "Please enter date correctly on DOB field");
                    makeTextFieldEmpty();
                }
            }

        }
        else if(e.getSource() == cancel){
            this.setVisible(false);
        }
    }
    public void makeTextFieldEmpty(){
        text_name.setText("");
        text_password.setText("");
        text_age.setText("");
        text_email.setText("");
        text_address.setText("");
        text_phone.setText("");
        text_birth.setText("");
    }

    public static void main(String[] args) {
        new StudentAdmission();
    }

}
