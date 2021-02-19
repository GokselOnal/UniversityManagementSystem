import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class NewCourse extends JFrame implements ActionListener {

    JLabel studentAddimage;
    JLabel headline,courseId,title,credit,semester,startTime,year,day,duration,prerequisite,department;
    JTextField text_courseId,text_title,text_year;
    JComboBox combo_credit,combo_semester,combo_day,combo_startTime,combo_duration,combo_prerequisite,combo_department;
    JButton submit;
    JButton cancel;

    NewCourse(){
        this.setTitle("Add Course");
        this.setBackground(Color.white);
        this.setSize(900,700);
        this.setLayout(null);

        studentAddimage = new JLabel();
        studentAddimage.setBounds(0,0,900,700);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/studentAdd.png"));
        Image image = img.getImage().getScaledInstance(900,700,Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        studentAddimage.setIcon(icon);

        headline = new JLabel("New Course Details");
        headline.setBounds(320,50,250,30);
        headline.setFont(new Font(",Courier",Font.ITALIC,25));
        headline.setForeground(Color.black);

        courseId = new JLabel("Course id");
        courseId.setBounds(100,150,100,30);
        courseId.setFont(new Font("serif",Font.BOLD,15));

        text_courseId = new JTextField();
        text_courseId.setBounds(200,150,150,30);

        title = new JLabel("Title");
        title.setBounds(100,250,100,30);
        title.setFont(new Font("serif",Font.BOLD,15));

        text_title = new JTextField();
        text_title.setBounds(200,250,150,30);

        credit = new JLabel("Credit");
        credit.setBounds(100,350,100,30);
        credit.setFont(new Font("serif",Font.BOLD,15));

        String[] creditArr = {"2","4","6"};
        combo_credit = new JComboBox(creditArr);
        combo_credit.setBounds(200,350,150,30);
        combo_credit.setBackground(Color.white);
        combo_credit.setSelectedIndex(2);

        semester = new JLabel("Semester");
        semester.setBounds(100,450,100,30);
        semester.setFont(new Font("serif",Font.BOLD,15));

        String[] semesterArr = {"Fall","Spring"};
        combo_semester = new JComboBox(semesterArr);
        combo_semester.setBounds(200,450,150,30);
        combo_semester.setBackground(Color.white);

        year = new JLabel("Year");
        year.setBounds(100,550,100,30);
        year.setFont(new Font("serif",Font.BOLD,15));

        text_year = new JTextField("yyyy");
        text_year.setBounds(200,550,150,30);

        day = new JLabel("Day");
        day.setBounds(450,150,100,30);
        day.setFont(new Font("serif",Font.BOLD,15));

        String[] dayArr = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
        combo_day = new JComboBox(semesterArr);
        combo_day.setBounds(550,150,150,30);
        combo_day.setBackground(Color.white);
        combo_day.setSelectedIndex(0);

        startTime = new JLabel("Start time");
        startTime.setBounds(450,250,100,30);
        startTime.setFont(new Font("serif",Font.BOLD,15));

        String[] timeArr = {"08:40:00","09:40:00","10:40:00","11:40:00","12:40:00","13:40:00","14:40:00","15:40:00","16:40:00","17:40:00","18:40:00"};
        combo_startTime = new JComboBox(timeArr);
        combo_startTime.setBounds(550,250,150,30);
        combo_startTime.setBackground(Color.white);

        duration = new JLabel("Duration");
        duration.setBounds(450,350,100,30);
        duration.setFont(new Font("serif",Font.BOLD,15));

        String[] durationArr = {"2","3","4"};
        combo_duration = new JComboBox(durationArr);
        combo_duration.setBounds(550,350,150,30);
        combo_duration.setBackground(Color.white);
        combo_duration.setSelectedIndex(0);

        prerequisite = new JLabel("Prerequisite");
        prerequisite.setBounds(450,450,100,30);
        prerequisite.setFont(new Font("serif",Font.BOLD,15));

        String[] courseArr = new String[getCourseCount()];
        try{
            conn connection = new conn();
            String selectQuery = "select * from course";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            int i = 0;
            while (rs.next()) {
                courseArr[i] = rs.getString("cid");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_prerequisite = new JComboBox(courseArr);
        combo_prerequisite.setBounds(550,450,150,30);
        combo_prerequisite.setBackground(Color.white);
        combo_prerequisite.insertItemAt("(None)",0);
        combo_prerequisite.setSelectedIndex(0);

        department = new JLabel("Department");
        department.setBounds(450,550,100,30);
        department.setFont(new Font("serif",Font.BOLD,15));

        String[] depArr = new String[getDepCount()];
        try{
            conn connection = new conn();
            String selectQuery = "select * from department";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            int i = 0;
            while (rs.next()) {
                depArr[i] = rs.getString("did");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_department = new JComboBox(depArr);
        combo_department.setBounds(550,550,150,30);
        combo_department.setBackground(Color.white);

        submit = new JButton("Submit");
        submit.setBounds(450,600,150,40);
        submit.setBackground(new Color(255,140,0));
        submit.setFocusable(false);
        submit.setForeground(Color.BLACK);
        submit.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBounds(620,600,150,40);
        cancel.setBackground(new Color(255,140,0));
        cancel.setFocusable(false);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);

        studentAddimage.add(headline);
        studentAddimage.add(courseId);
        studentAddimage.add(text_courseId);
        studentAddimage.add(title);
        studentAddimage.add(text_title);
        studentAddimage.add(credit);
        studentAddimage.add(combo_credit);
        studentAddimage.add(semester);
        studentAddimage.add(combo_semester);
        studentAddimage.add(year);
        studentAddimage.add(text_year);
        studentAddimage.add(day);
        studentAddimage.add(combo_day);
        studentAddimage.add(startTime);
        studentAddimage.add(combo_startTime);
        studentAddimage.add(duration);
        studentAddimage.add(combo_duration);
        studentAddimage.add(prerequisite);
        studentAddimage.add(combo_prerequisite);
        studentAddimage.add(department);
        studentAddimage.add(combo_department);
        studentAddimage.add(submit);
        studentAddimage.add(cancel);

        this.add(studentAddimage);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            try {
                conn connection = new conn();
                String insertQuery = "";
                connection.statement.executeUpdate(insertQuery);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        else if(e.getSource() == cancel){
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCourse();
    }

    public int getCourseCount(){
        int courseCount = 0;
        try{
            conn connection = new conn();
            String selectQuery = "select count(cid) as count from course";
            ResultSet rs= connection.statement.executeQuery(selectQuery);
            rs.next();
            courseCount += rs.getInt("count");

        }catch (Exception ee){
            ee.printStackTrace();
        }
        return courseCount;
    }

    public int getDepCount(){
        int depCount = 0;
        try{
            conn connection = new conn();
            String selectQuery = "select count(did) as count from department";
            ResultSet rs= connection.statement.executeQuery(selectQuery);
            rs.next();
            depCount += rs.getInt("count");

        }catch (Exception ee){
            ee.printStackTrace();
        }
        return depCount;
    }
}
