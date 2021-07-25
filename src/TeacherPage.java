import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherPage extends JFrame implements ActionListener {
    JButton courseOffered;
    JButton examination;
    JButton mySchedule;
    JButton academicInformation;
    JButton name;

    TeacherPage(){
        this.setTitle("Teacher Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1925,1050);
        this.setBackground(new Color(116,96,96));

        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("icons/mainPage.png"));
        Image i3 = ic.getImage().getScaledInstance(1925, 880,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel label = new JLabel(icc3);
        label.setBounds(0,150,1925,880);

        String userName = Login.Username;

        name = new JButton(userName);
        name.setBounds(35,50,130,60);
        name.setFocusable(false);
        name.setFont(new Font("SansSerif",Font.BOLD,17));
        name.setBackground(Color.white);
        name.setForeground(Color.BLACK);

        courseOffered = new JButton("Course Offered");
        courseOffered.setBounds(260,50,205,130);
        courseOffered.setFocusable(false);
        courseOffered.setFont(new Font("SansSerif",Font.BOLD,17));
        courseOffered.setBackground(new Color(255,140,0));
        courseOffered.setForeground(new Color(116,96,96));
        courseOffered.addActionListener(this);

        examination = new JButton("Examination");
        examination.setBounds(660,50,205,130);
        examination.setFocusable(false);
        examination.setFont(new Font("SansSerif",Font.BOLD,17));
        examination.setBackground(new Color(255,140,0));
        examination.setForeground(new Color(116,96,96));
        examination.addActionListener(this);

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
        this.add(examination);
        this.add(mySchedule);
        this.add(academicInformation);
        this.add(name);
        this.add(label);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == courseOffered){
            new CourseOffered();
        }
        else if(e.getSource() == mySchedule){
            new MyScheduleTeacher();
        }
        else if(e.getSource() == examination){
            new Exams();
        }
        else if(e.getSource() == academicInformation){
            new AcademicInformationTeacher();
        }
    }
}
