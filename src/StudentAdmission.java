import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentAdmission extends JFrame implements ActionListener {
    JLabel title;
    JLabel name;
    JLabel age;
    JLabel email;
    JLabel address;
    JLabel phone;
    JLabel birth;
    JLabel level;
    JLabel program;
    JLabel studentAddimage;

    JTextField text_name;
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

        text_birth = new JTextField("dd/mm/yyyy");
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


        String programs[] = {"Computer Science","...","..."};
        combo_program = new JComboBox(programs);
        combo_program.setBounds(550,350,150,30);
        combo_program.setBackground(Color.white);

        submit = new JButton("Submit");
        submit.setBounds(450,500,150,40);
        submit.setBackground(new Color(255,140,0));
        submit.setFocusable(false);
        submit.setForeground(Color.BLACK);
        submit.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBounds(620,500,150,40);
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
        studentAddimage.add(submit);
        studentAddimage.add(cancel);
        this.add(studentAddimage);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){

        }
        else if(e.getSource() == cancel){
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentAdmission();
    }

}
