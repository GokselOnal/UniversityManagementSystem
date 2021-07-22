import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class NewCourse extends JFrame implements ActionListener {

    JLabel studentAddimage;
    JLabel headline,courseId,title,teacher,credit,semester,startTime,year,day,duration,prerequisite,department;
    JTextField text_courseId,text_title,text_year;
    JComboBox combo_teacher,combo_credit,combo_semester,combo_day,combo_startTime,combo_duration,combo_prerequisite,combo_department;
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
        courseId.setBounds(100,100,100,30);
        courseId.setFont(new Font("serif",Font.BOLD,15));

        text_courseId = new JTextField();
        text_courseId.setBounds(200,100,150,30);

        title = new JLabel("Title");
        title.setBounds(100,200,100,30);
        title.setFont(new Font("serif",Font.BOLD,15));

        text_title = new JTextField();
        text_title.setBounds(200,200,150,30);

        teacher = new JLabel("Teacher");
        teacher.setBounds(100,300,100,30);
        teacher.setFont(new Font("serif",Font.BOLD,15));

        String[] teacherArr = new String[getTeacherCount()];
        int i= 0;
        try {
            conn connection = new conn();
            String selectQuery = "select uid from user where type = 2";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            while (rs.next()){
                int teacher_id = rs.getInt("uid");
                conn connection2 = new conn();
                String query2 = "select name from user where uid ="+ teacher_id;
                ResultSet rs2 = connection2.statement.executeQuery(query2);
                rs2.next();
                String teacherName = rs2.getString("name");
                teacherArr[i] = teacherName;
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_teacher = new JComboBox(teacherArr);
        combo_teacher.setBounds(200,300,150,30);
        combo_teacher.setBackground(Color.white);

        credit = new JLabel("Credit");
        credit.setBounds(100,400,100,30);
        credit.setFont(new Font("serif",Font.BOLD,15));

        String[] creditArr = {"2","4","6"};
        combo_credit = new JComboBox(creditArr);
        combo_credit.setBounds(200,400,150,30);
        combo_credit.setBackground(Color.white);
        combo_credit.setSelectedIndex(2);

        semester = new JLabel("Semester");
        semester.setBounds(100,500,100,30);
        semester.setFont(new Font("serif",Font.BOLD,15));

        String[] semesterArr = {"Fall","Spring"};
        combo_semester = new JComboBox(semesterArr);
        combo_semester.setBounds(200,500,150,30);
        combo_semester.setBackground(Color.white);

        year = new JLabel("Year");
        year.setBounds(100,600,100,30);
        year.setFont(new Font("serif",Font.BOLD,15));

        text_year = new JTextField("yyyy");
        text_year.setBounds(200,600,150,30);

        day = new JLabel("Day");
        day.setBounds(450,100,100,30);
        day.setFont(new Font("serif",Font.BOLD,15));

        String[] dayArr = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
        combo_day = new JComboBox(dayArr);
        combo_day.setBounds(550,100,150,30);
        combo_day.setBackground(Color.white);
        combo_day.setSelectedIndex(0);

        startTime = new JLabel("Start time");
        startTime.setBounds(450,200,100,30);
        startTime.setFont(new Font("serif",Font.BOLD,15));

        String[] timeArr = {"08:40:00","09:40:00","10:40:00","11:40:00","12:40:00","13:40:00","14:40:00","15:40:00","16:40:00","17:40:00","18:40:00"};
        combo_startTime = new JComboBox(timeArr);
        combo_startTime.setBounds(550,200,150,30);
        combo_startTime.setBackground(Color.white);

        duration = new JLabel("Duration");
        duration.setBounds(450,300,100,30);
        duration.setFont(new Font("serif",Font.BOLD,15));

        String[] durationArr = {"2","3","4"};
        combo_duration = new JComboBox(durationArr);
        combo_duration.setBounds(550,300,150,30);
        combo_duration.setBackground(Color.white);
        combo_duration.setSelectedIndex(0);

        prerequisite = new JLabel("Prerequisite");
        prerequisite.setBounds(450,400,100,30);
        prerequisite.setFont(new Font("serif",Font.BOLD,15));

        String[] courseArr = new String[getCourseCount()];
        try{
            conn connection = new conn();
            String selectQuery = "select * from course";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            int j = 0;
            while (rs.next()) {
                courseArr[j] = rs.getString("cid");
                j++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_prerequisite = new JComboBox(courseArr);
        combo_prerequisite.setBounds(550,400,150,30);
        combo_prerequisite.setBackground(Color.white);
        combo_prerequisite.insertItemAt("(None)",0);
        combo_prerequisite.setSelectedIndex(0);

        department = new JLabel("Department");
        department.setBounds(450,500,100,30);
        department.setFont(new Font("serif",Font.BOLD,15));

        String[] depArr = new String[getDepCount()];
        try{
            conn connection = new conn();
            String selectQuery = "select * from department";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            int k = 0;
            while (rs.next()) {
                depArr[k] = rs.getString("did");
                k++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_department = new JComboBox(depArr);
        combo_department.setBounds(550,500,150,30);
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
        studentAddimage.add(teacher);
        studentAddimage.add(combo_teacher);
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
            String course_id = text_courseId.getText();
            String teacher_name = (String) combo_teacher.getSelectedItem();
            String title = text_title.getText();
            String credit = (String) combo_credit.getSelectedItem();
            String semester = (String) combo_semester.getSelectedItem();
            String year = text_year.getText();
            String day = (String) combo_day.getSelectedItem();
            String start_time = (String) combo_startTime.getSelectedItem();
            String duration = (String) combo_duration.getSelectedItem();
            String department_id = (String) combo_department.getSelectedItem();

            //AYNI HOCAYA, AYNI SAATLER ARALIGINDA BASKA DERS EKLENEMEZ
            try {
                conn connection = new conn();
                String query = "select * from user where name = '"+teacher_name+"'";
                ResultSet rs = connection.statement.executeQuery(query);
                rs.next();
                String teacher_id = rs.getString("uid");
                String insertQuery = "insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, department_id) values('"+course_id+"','"+teacher_id+"','"+title+"','"+credit+"','"+semester+"','"+year+"','"+day+"','"+start_time+"','"+duration+"','"+department_id+"')";
                connection.statement.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null, "Course have been added successfully");
                text_courseId.setText("");
                text_year.setText("");
                text_title.setText("");
            } catch (Exception ee) {
                ee.printStackTrace();
                if((String.valueOf(ee).startsWith("com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column"))){
                    JOptionPane.showMessageDialog(null, "Please enter max 6 digit id on course id");
                    text_courseId.setText("");
                    text_year.setText("");
                    text_title.setText("");
                }
                else if(String.valueOf(ee).startsWith("com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect date value:") || String.valueOf(ee).startsWith("java.sql.SQLException: Incorrect integer value:") || String.valueOf(ee).startsWith("com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Out of range")){
                    JOptionPane.showMessageDialog(null, "Please enter year correctly (yyyy)");
                    text_courseId.setText("");
                    text_year.setText("");
                    text_title.setText("");
                }
                else if(String.valueOf(ee).startsWith("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry")){
                    JOptionPane.showMessageDialog(null, "Please enter a new course\n The Course Id is already exists");
                    text_courseId.setText("");
                    text_year.setText("");
                    text_title.setText("");
                }
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
    public int getTeacherCount() {
        int teacherCount = 0;
        try{
            conn connection = new conn();
            String selectQuery = "select count(uid) as count from user where type = 2";
            ResultSet rs= connection.statement.executeQuery(selectQuery);
            rs.next();
            teacherCount += rs.getInt("count");

        }catch (Exception ee){
            ee.printStackTrace();
        }
        return teacherCount;
    }
}
