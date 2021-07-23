import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class StudentPage extends JFrame implements ActionListener {
    JButton courseOffered;
    JButton courseRegistration;
    JButton mySchedule;
    JButton academicInformation;

    StudentPage(){
        this.setTitle("Student Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1925,1050);
        this.setBackground(new Color(116,96,96));

        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("icons/mainPage.png"));
        Image i3 = ic.getImage().getScaledInstance(1925, 880,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel label = new JLabel(icc3);
        label.setBounds(0,150,1925,880);


        courseOffered = new JButton("Course Offered");
        courseOffered.setBounds(260,50,205,130);
        courseOffered.setFocusable(false);
        courseOffered.setFont(new Font("SansSerif",Font.BOLD,17));
        courseOffered.setBackground(new Color(255,140,0));
        courseOffered.setForeground(new Color(116,96,96));
        courseOffered.addActionListener(this);

        courseRegistration = new JButton("Course Registration");
        courseRegistration.setBounds(660,50,205,130);
        courseRegistration.setFocusable(false);
        courseRegistration.setFont(new Font("SansSerif",Font.BOLD,17));
        courseRegistration.setBackground(new Color(255,140,0));
        courseRegistration.setForeground(new Color(116,96,96));
        courseRegistration.addActionListener(this);

        mySchedule = new JButton("My Schedule");
        mySchedule.setBounds(1060,50,205,130);
        mySchedule.setFocusable(false);
        mySchedule.setFont(new Font("SansSerif",Font.BOLD,17));
        mySchedule.setBackground(new Color(255,140,0));
        mySchedule.setForeground(new Color(126,96,96));
        mySchedule.addActionListener(this);

        academicInformation = new JButton("Academic Information");
        academicInformation.setBounds(1460,50,205,130);
        academicInformation.setFocusable(false);
        academicInformation.setFont(new Font("SansSerif",Font.BOLD,17));
        academicInformation.setBackground(new Color(255,140,0));
        academicInformation.setForeground(new Color(116,96,96));
        academicInformation.addActionListener(this);

        this.getContentPane().setBackground(Color.white);
        this.add(courseOffered);
        this.add(courseRegistration);
        this.add(mySchedule);
        this.add(academicInformation);
        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == courseOffered){
            new CourseOffered();
        }
        if(e.getSource() == courseRegistration){
            LocalDate date = LocalDate.now();
            if((String.valueOf(date).equals(CourseRegistration.registrationDateSpring) || String.valueOf(date).equals(CourseRegistration.registrationDateFall))){
                new CourseRegistration();
            }else{
                JOptionPane.showMessageDialog(null,"Course registration system is currently inactive");
            }
        }
        if(e.getSource() == mySchedule){
            new MySchedule();
        }
        if(e.getSource() == academicInformation){
            new AcademicInformation();
        }
    }

    public static void main(String[] args) {
        new StudentPage();
    }


}
