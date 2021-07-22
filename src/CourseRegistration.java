import com.mysql.cj.log.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class CourseRegistration extends JFrame implements ActionListener {
    static String registrationDateSpring = String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-07-22";
    static String registrationDateFall = String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-07-22";
    JTable table;
    JScrollPane sp;
    String x[] = {"Course Id","Teacher","Title","Credit","Semester","Year","Day","Start Time","Duration","Prerequisite","Quota","Department"};
    String y[][];
    ArrayList<String> regis_lec = new ArrayList<String>();
    JLabel label_credit, label_department, label_semester, label_year;
    JComboBox combo_credit,combo_department,combo_semester, combo_year;
    JButton add,search;

    CourseRegistration(){
        this.setTitle("Course Registration");
        this.setSize(1550,550);
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
            String query= "select did from department";
            ResultSet rs = connection.statement.executeQuery(query);
            int i = 0;
            while (rs.next()){
                depArr[i] = rs.getString("did");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        combo_department = new JComboBox(depArr);
        combo_department.setBounds(1165,20,150,30);
        combo_department.setBackground(Color.white);

        search = new JButton("Search");
        search.setBounds(1390,20,120,30);
        search.setFont(new Font("serif",Font.BOLD,15));
        search.addActionListener(this);
        search.setBackground(Color.white);
        search.setForeground(Color.BLACK);
        search.setFocusable(false);

        LocalDate curDate = LocalDate.now();
        String month = String.valueOf(curDate).substring(5,7);

        if(Integer.parseInt(month.substring(1)) < 9){
            createTable("select * from course where semester = 'Spring'",getCourseCount());
        }
        else{
            createTable("select * from course where semester = 'Fall'",getCourseCount());
        }


        add = new JButton("Add");
        add.setBounds(100,450,120,30);
        add.setFont(new Font("serif",Font.BOLD,15));
        add.addActionListener(this);
        add.setBackground(Color.white);
        add.setForeground(Color.BLACK);
        add.setFocusable(false);

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
        this.add(add);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add) {
            String selectedCourseId = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
                                    //0      //1        //2          //3         //4
            String[][] program = {{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"},
                    {"08:40:00", "08:40:00", "08:40:00", "08:40:00", "08:40:00", "08:40:00"},//1
                    {"09:40:00", "09:40:00", "09:40:00", "09:40:00", "09:40:00", "09:40:00"},//2
                    {"10:40:00", "10:40:00", "10:40:00", "10:40:00", "10:40:00", "10:40:00"},//3
                    {"11:40:00", "11:40:00", "11:40:00", "11:40:00", "11:40:00", "11:40:00"},//4
                    {"12:40:00", "12:40:00", "12:40:00", "12:40:00", "12:40:00", "12:40:00"},//5
                    {"13:40:00", "13:40:00", "13:40:00", "13:40:00", "13:40:00", "13:40:00"},//6
                    {"14:40:00", "14:40:00", "14:40:00", "14:40:00", "14:40:00", "14:40:00"},//7
                    {"15:40:00", "15:40:00", "15:40:00", "15:40:00", "15:40:00", "15:40:00"},//8
                    {"16:40:00", "16:40:00", "16:40:00", "16:40:00", "16:40:00", "16:40:00"},//9
                    {"17:40:00", "17:40:00", "17:40:00", "17:40:00", "17:40:00", "17:40:00"},//10
                    {"18:40:00", "18:40:00", "18:40:00", "18:40:00", "18:40:00", "18:40:00"},//11
                    {"19:40:00", "19:40:00", "19:40:00", "19:40:00", "19:40:00", "19:40:00"},//12
                    {"20:40:00", "20:40:00", "20:40:00", "20:40:00", "20:40:00", "20:40:00"}};//13
            try {
                conn connection = new conn();
                String selectQuery = "select * from course where cid = '" + selectedCourseId + "'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                rs.next();
                String selectedDay = rs.getString("day");
                String selectedStartTime = rs.getString("start_time");
                String selectedDuration = rs.getString("duration");
                String selectedPrerequisite = rs.getString("prerequisite");
                int selectedCredit = rs.getInt("credit");
                int selectedQuota = rs.getInt("quota");

                int selectedDayIndex = 0;
                for(int i = 0; i <program[0].length; i++){
                    if(program[0][i].equals(selectedDay)){
                        selectedDayIndex += i;
                    }
                }

                int selectedTimeIndex = 0;
                for(int i = 0; i <program.length; i++){
                    if(program[i][selectedDayIndex].equals(selectedStartTime)){
                        selectedTimeIndex += i;
                    }
                }
                String userName = Login.Username;

                conn connection2 = new conn();
                String selectQuery2 = "select uid from user where name = '"+userName+"'";
                ResultSet rs2 = connection2.statement.executeQuery(selectQuery2);
                rs2.next();
                String user_id = rs2.getString("uid");

                conn connection3 = new conn();
                conn connection4 = new conn();

                int year = Calendar.getInstance().get(Calendar.YEAR);

                String query4 = "select credit from user where uid = '"+user_id+"'";
                ResultSet rs4 = connection4.statement.executeQuery(query4);
                rs4.next();
                int currentCredit = rs4.getInt("credit");

                String selectSchedule = "select * from schedule where user_id = '"+user_id+"' and year !='"+registrationDateSpring+"' and year !='"+registrationDateFall+"'";
                ResultSet rs3 = connection3.statement.executeQuery(selectSchedule);
                ArrayList<String> history = new ArrayList<String>();

                while (rs3.next()){
                    history.add(rs3.getString("course_id"));
                }
                //dersi almış olması değil, basarıyla geçmiş olma durumuna göre bakmayı unutma!!!
                //o anda oldukları dönemdeki derslerden alabilmeli(course registrationı course offereddan fakrlı yap)
                if((selectedPrerequisite == null || history.contains(selectedPrerequisite)) && (selectedQuota > 0) && currentCredit > 0) {
                    for (int i = 0; i < Integer.parseInt(selectedDuration); i++) {
                        String insertQuery = "insert into schedule(user_id,course_id,year,dayIndex,timeIndex) values('" + user_id +"','"+selectedCourseId+"','"+year+"','" + selectedDayIndex + "','" + (selectedTimeIndex + i) + "')";
                        connection2.statement.executeUpdate(insertQuery);
                    }
                    String updateQuery =  "update course set quota = quota - 1 where cid = '"+selectedCourseId+"'";
                    connection2.statement.executeUpdate(updateQuery);
                    String newCredit = String.valueOf(currentCredit - selectedCredit);
                    String decCredit = "update user set credit ="+newCredit+" where uid = '"+user_id+"'";
                    connection2.statement.executeUpdate(decCredit);
                    JOptionPane.showMessageDialog(null,"aldın");
                    this.setVisible(false);
                    this.remove(sp);
                    createTable("select * from course",getCourseCount());
                }
                else if(selectedPrerequisite != null && !history.contains(selectedPrerequisite)){
                    JOptionPane.showMessageDialog(null,"prerequisete");
                }
                else if(selectedQuota <= 0){
                    JOptionPane.showMessageDialog(null,"not enough quota");
                }
            }catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry")){
                    JOptionPane.showMessageDialog(null,"You can't take this course");//saat uymuyor, dersi daha önce almıs vermiş(unutma)
                }
            }
        }
        else if(e.getSource() == search){
            String year = (String) combo_year.getSelectedItem();
            String semester = (String) combo_semester.getSelectedItem();
            String credit = (String) combo_credit.getSelectedItem();
            String department = (String) combo_department.getSelectedItem();

            try {
                conn connection = new conn();
                ResultSet rs = connection.statement.executeQuery("select count(cid) as count from course where year = '"+year+"' and  semester = '"+semester+"' and credit = '"+credit+"' and department_id = '"+department+"'");
                rs.next();
                this.remove(sp);
                int searchCount = rs.getInt("count");
                createTable("select * from course where year = '"+year+"' and  semester = '"+semester+"' and credit = '"+credit+"' and department_id = '"+department+"'",searchCount);
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }

    public void createTable(String query, int number){
        try{
            conn connection = new conn();
            ResultSet rs = connection.statement.executeQuery(query);

            int i = 0;
            int j = 0;
            y = new String[number][13];
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
                y[i][j++] = rs.getString("year").substring(0,4);
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
        sp.setBounds(20,95,1500,330);
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
        new CourseRegistration();

    }
}
