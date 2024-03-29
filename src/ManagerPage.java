import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPage extends JFrame implements ActionListener {
    JMenuBar menuBar;

    ManagerPage(){
        this.setTitle("University Information System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(1925,1050);
        this.setBackground(new Color(116,96,96));

        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("icons/mainPage.png"));
        Image i3 = ic.getImage().getScaledInstance(1925, 880,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel label = new JLabel(icc3);

        menuBar = new JMenuBar();

        JMenu master = new JMenu("Master");
        JMenuItem m1 = new JMenuItem("New Program");
        JMenuItem m2 = new JMenuItem("New Student Admission");
        JMenuItem m3 = new JMenuItem("New Teacher Admission");
        JMenuItem m4 = new JMenuItem("New Course");
        master.setForeground(new Color(255,140,0));
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        master.add(m1);
        master.add(m2);
        master.add(m3);
        master.add(m4);

        JMenu details = new JMenu("Details");
        JMenuItem d1 = new JMenuItem("Program Details");
        JMenuItem d2 = new JMenuItem("Student Details");
        JMenuItem d3 = new JMenuItem("Teacher Details");
        JMenuItem d4 = new JMenuItem("Course Details");
        details.setForeground(new Color(116,96,96));
        d1.addActionListener(this);
        d2.addActionListener(this);
        d3.addActionListener(this);
        d4.addActionListener(this);

        details.add(d1);
        details.add(d2);
        details.add(d3);
        details.add(d4);

        JMenu updateDetails = new JMenu("Update Details");
        JMenuItem ud1 = new JMenuItem("Update Program");
        JMenuItem ud2 = new JMenuItem("Update Student");
        JMenuItem ud3 = new JMenuItem("Update Teacher");
        JMenuItem ud4 = new JMenuItem("Update Course");
        updateDetails.setForeground(new Color(255,140,0));
        ud1.addActionListener(this);
        ud2.addActionListener(this);
        ud3.addActionListener(this);
        ud4.addActionListener(this);

        updateDetails.add(ud1);
        updateDetails.add(ud2);
        updateDetails.add(ud3);
        updateDetails.add(ud4);

        JMenu exit = new JMenu("Exit");
        JMenuItem ext = new JMenuItem("Exit");
        exit.setForeground(Color.red);
        ext.addActionListener(this);

        exit.add(ext);

        menuBar.add(master);
        menuBar.add(details);
        menuBar.add(updateDetails);
        menuBar.add(exit);

        this.getContentPane().setBackground(Color.white);
        this.setJMenuBar(menuBar);
        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if(msg.equals("New Student Admission")){
            new StudentAdmission().setVisible(true);
        }
        else if(msg.equals("New Teacher Admission")){
            new TeacherAdmission().setVisible(true);
        }
        else if(msg.equals("New Program")){
            new NewProgram().setVisible(true);
        }
        else if(msg.equals("New Course")){
            new NewCourse().setVisible(true);
        }
        else if(msg.equals("Student Details")){
            new StudentDetails().setVisible(true);
        }
        else if(msg.equals("Teacher Details")){
            new TeacherDetails().setVisible(true);
        }
        else if(msg.equals("Program Details")){
            new ProgramDetails().setVisible(true);
        }
        else if(msg.equals("Course Details")){
            new CourseDetails().setVisible(true);
        }
        else if(msg.equals("Update Program")){
            new UpdateProgram().setVisible(true);
        }
        else if(msg.equals("Update Student")){
            new UpdateStudent().setVisible(true);
        }
        else if(msg.equals("Update Teacher")){
            new UpdateTeacher().setVisible(true);
        }
        else if(msg.equals("Update Course")){
            new UpdateCourse().setVisible(true);
        }
        else if(msg.equals("Exit")){
            System.exit(0);
        }
    }
}
