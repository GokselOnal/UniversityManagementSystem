import com.mysql.cj.log.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class CourseRegistration extends JFrame implements ActionListener {
    JTable table;
    JScrollPane sp;
    String x[] = {"Course Id","Teacher","Title","Credit","Semester","Year","Day","Start Time","Duration","Prerequisite","Quota","Department"};
    String y[][];
    ArrayList<String> regis_lec = new ArrayList<String>();
    JButton add,submit;

    CourseRegistration(){
        this.setTitle("Course Registration");
        this.setSize(1550,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        createTable("select * from course",getCourseCount());

        add = new JButton("Add");
        add.setBounds(100,380,120,30);
        add.setFont(new Font("serif",Font.BOLD,15));
        add.addActionListener(this);
        add.setBackground(Color.white);
        add.setForeground(Color.BLACK);
        add.setFocusable(false);

        submit = new JButton("Submit");
        submit.setBounds(400,380,120,30);
        submit.setFont(new Font("serif",Font.BOLD,15));
        submit.addActionListener(this);
        submit.setBackground(Color.white);
        submit.setForeground(Color.BLACK);
        submit.setFocusable(false);


        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(add);
        this.add(submit);
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
                String selectQuery = "select day, start_time, duration from course where cid = '" + selectedCourseId + "'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                rs.next();
                String selectedDay = rs.getString("day");
                String selectedStartTime = rs.getString("start_time");
                String selectedDuration = rs.getString("duration");
                /*System.out.println(selectedDay);
                System.out.println(selectedStartTime);
                System.out.println(selectedDuration);*/
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

                for(int i = 0 ; i < Integer.parseInt(selectedDuration); i++) {
                    String insertQuery = "insert into schedule(user_id,dayIndex,timeIndex) values('" + user_id + "','" + selectedDayIndex + "','" + (selectedTimeIndex+i) + "')";
                    connection2.statement.executeUpdate(insertQuery);
                }
                regis_lec.add(selectedCourseId);
            }catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry")){
                    JOptionPane.showMessageDialog(null,"You can't take this course");
                }
            }
        }
        else if(e.getSource() == submit) {
           /* try {
                String userName = Login.Username;

                conn connection = new conn();
                String selectQuery = "select uid from user where name = '"+userName+"'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                rs.next();
                String user_id = rs.getString("uid");

                int lecSize = regis_lec.size();
                String insertQuery = "insert into course_grouped (cgid) values('"+user_id+"')";
                connection.statement.executeUpdate(insertQuery);
                for (int i = 1; i <= lecSize; i++) {
                    String updateQuery = "update course_grouped set course"+i+" = '"+regis_lec.get(i-1)+"'";
                    connection.statement.executeUpdate(updateQuery);
                }
            }catch (Exception ee){
                ee.printStackTrace();
            }*/
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
                JCheckBox cb = new JCheckBox();

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
        sp.setBounds(20,20,1500,330);
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

    public static void main(String[] args) {
        new CourseRegistration();

    }
}
