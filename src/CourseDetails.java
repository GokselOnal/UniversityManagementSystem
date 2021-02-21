import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CourseDetails extends JFrame implements ActionListener {

    JTable table;
    String x[] = {"Course Id","Teacher","Title","Credit","Semester","Year","Day","Start Time","Duration","Prerequisite","Quota","Department"};
    String y[][];
    JLabel label_credit, label_department, label_semester, label_year;
    JComboBox combo_credit,combo_department,combo_semester, combo_year;
    JButton search;
    JScrollPane sp;
    CourseDetails(){
        this.setTitle("Course Details");
        this.setSize(1550,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        label_year = new JLabel("Year");
        label_year.setBounds(25,20,80,30);
        label_year.setFont(new Font("serif",Font.BOLD,15));

        String[] yearArr = new String[getYearCount()];
        try{
            conn connection = new conn();
            String query= "select distinct(year) from course";
            ResultSet rs = connection.statement.executeQuery(query);
            int i = 0;
            while (rs.next()){
                yearArr[i] = rs.getString("year").substring(0,4);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_year = new JComboBox(yearArr);
        combo_year.setBounds(105,20,150,30);
        combo_year.setBackground(Color.white);

        label_semester = new JLabel("Semester");
        label_semester.setBounds(350,20,80,30);
        label_semester.setFont(new Font("serif",Font.BOLD,15));

        String[] semesterArr = {"Fall","Spring"};
        combo_semester = new JComboBox(semesterArr);
        combo_semester.setBounds(430,20,150,30);
        combo_semester.setBackground(Color.white);

        label_credit = new JLabel("Credit");
        label_credit.setBounds(700,20,80,30);
        label_credit.setFont(new Font("serif",Font.BOLD,15));

        String[] creditArr = {"2","4","6"};
        combo_credit = new JComboBox(creditArr);
        combo_credit.setBounds(780,20,150,30);
        combo_credit.setBackground(Color.white);

        label_department = new JLabel("Department");
        label_department.setBounds(1065,20,80,30);
        label_department.setFont(new Font("serif",Font.BOLD,15));

        String[] depArr = new String[getDepCount()];
        try{
            conn connection = new conn();
            String query= "select department_name from department";
            ResultSet rs = connection.statement.executeQuery(query);
            int i = 0;
            while (rs.next()){
                depArr[i] = rs.getString("department_name");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_department = new JComboBox(depArr);
        combo_department.setBounds(1165,20,150,30);
        combo_department.setBackground(Color.white);

        search = new JButton("Search");
        search.setBounds(1350,20,120,30);
        search.setFont(new Font("serif",Font.BOLD,15));
        search.addActionListener(this);
        search.setBackground(Color.white);
        search.setForeground(Color.BLACK);
        search.setFocusable(false);

        createTable("select * from course",getCourseCount());

        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(label_year);
        this.add(combo_year);
        this.add(label_semester);
        this.add(combo_semester);
        this.add(label_credit);
        this.add(combo_credit);
        this.add(label_department);
        this.add(combo_department);
        this.add(search);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void createTable(String query, int number){
        try{
            conn connection = new conn();
            ResultSet rs = connection.statement.executeQuery(query);

            int i = 0;
            int j = 0;
            y = new String[number][12];
            while (rs.next()){
                y[i][j++] = rs.getString("cid");

                conn connection2 = new conn();
                String teacher_id = rs.getString("teacher");
                String selectQueryTeacher = "select * from user where uid= '"+teacher_id+"'";
                ResultSet rsTeacher = connection2.statement.executeQuery(selectQueryTeacher);
                rsTeacher.next();
                String teacher_name = rsTeacher.getString("name");
                y[i][j++] = teacher_name;

                y[i][j++] = rs.getString("title");
                y[i][j++] = rs.getString("credit");
                y[i][j++] = rs.getString("semester");
                y[i][j++] = rs.getString("year");
                y[i][j++] = rs.getString("day");
                y[i][j++] = rs.getString("start_time");
                y[i][j++] = rs.getString("duration");
                y[i][j++] = rs.getString("prerequisite");
                y[i][j++] = rs.getString("quota");
                y[i][j++] = rs.getString("department_id");

                i++;
                j=0;
            }
            table = new JTable(y,x);
            table.setGridColor(Color.orange);

        }catch (Exception ee){
            ee.printStackTrace();
        }
        sp = new JScrollPane(table);
        sp.setBounds(20,100,1500,330);
        this.add(sp);
    }

    public int getCourseCount(){
        int courseCount= 0;
        try{
            conn connection = new conn();
            String query = "select count(cid) as count from course";
            ResultSet rs = connection.statement.executeQuery(query);
            rs.next();
            int countDep = rs.getInt("count");
            courseCount += countDep;
        }catch (Exception e){
            e.printStackTrace();
        }
        return courseCount;
    }
    public int getYearCount(){
        int yearCount= 0;
        try{
            conn connection = new conn();
            String query = "select count(distinct(year)) as count from course";
            ResultSet rs = connection.statement.executeQuery(query);
            rs.next();
            int countDep = rs.getInt("count");
            yearCount += countDep;
        }catch (Exception e){
            e.printStackTrace();
        }
        return yearCount;
    }
    public int getDepCount(){
        int depCount= 0;
        try{
            conn connection = new conn();
            String query = "select count(did) as count from department";
            ResultSet rs = connection.statement.executeQuery(query);
            rs.next();
            int countDep = rs.getInt("count");
            depCount += countDep;
        }catch (Exception e){
            e.printStackTrace();
        }
        return depCount;
    }

    public static void main(String[] args) {
        new CourseDetails();
    }
}
