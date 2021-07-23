import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UpdateCourse extends JFrame implements ActionListener {

    JTable table;
    String x[] = {"Course Id","Teacher","Title","Credit","Semester","Year","Day","Start Time","Duration","Prerequisite","Quota","Department"};
    String y[][];
    JScrollPane sp;
    JButton changeQuota, changeDay, changeTime, delCourse, changeTeacher;

    UpdateCourse(){
        this.setTitle("Update Course");
        this.setSize(1550,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        createTable("select * from course",getCourseCount());

        changeQuota = new JButton("Change Quota");
        changeQuota.setBounds(100,380,160,40);
        changeQuota.setFont(new Font("serif",Font.BOLD,15));
        changeQuota.setBackground(Color.white);
        changeQuota.setFocusable(false);
        changeQuota.addActionListener(this);

        changeDay = new JButton("Change Day");
        changeDay.setBounds(400,380,160,40);
        changeDay.setFont(new Font("serif",Font.BOLD,15));
        changeDay.setBackground(Color.white);
        changeDay.setFocusable(false);
        changeDay.addActionListener(this);

        changeTime = new JButton("Change Start time");
        changeTime.setBounds(700,380,160,40);
        changeTime.setFont(new Font("serif",Font.BOLD,15));
        changeTime.setBackground(Color.white);
        changeTime.setFocusable(false);
        changeTime.addActionListener(this);

        delCourse = new JButton("Delete Course");
        delCourse.setBounds(1000,380,160,40);
        delCourse.setFont(new Font("serif",Font.BOLD,15));
        delCourse.setBackground(Color.white);
        delCourse.setFocusable(false);
        delCourse.addActionListener(this);

        changeTeacher = new JButton("Change Teacher");
        changeTeacher.setBounds(1300,380,160,40);
        changeTeacher.setFont(new Font("serif",Font.BOLD,15));
        changeTeacher.setBackground(Color.white);
        changeTeacher.setFocusable(false);
        changeTeacher.addActionListener(this);

        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(changeQuota);
        this.add(changeDay);
        this.add(changeTime);
        this.add(delCourse);
        this.add(changeTeacher);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeQuota){
            try {
                String selectedCourseId = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
                String newQuota = (String) table.getModel().getValueAt(table.getSelectedRow(), 10);

                conn connection = new conn();
                String updateQuery = "update course set quota = '" + newQuota + "' where cid = '" + selectedCourseId + "'";
                connection.statement.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null,"Quota has been updated successfully");
            }
            catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 16")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == changeDay) {
            try {
                String selectedCourseId = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
                String newDay = (String) table.getModel().getValueAt(table.getSelectedRow(), 6);
                if(newDay.equals("Monday") || newDay.equals("Tuesday") || newDay.equals("Wednesday") || newDay.equals("Thursday") || newDay.equals("Friday")) {
                    conn connection = new conn();
                    String updateQuery = "update course set day = '" + newDay + "' where cid = '" + selectedCourseId + "'";
                    connection.statement.executeUpdate(updateQuery);
                    JOptionPane.showMessageDialog(null, "Day has been updated successfully");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Write a proper weekday please");
                }
            }catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 16")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == changeTime){
            try {
                String selectedCourseId = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
                String newTime = (String) table.getModel().getValueAt(table.getSelectedRow(), 7);
                if(newTime.equals("08:40:00") || newTime.equals("09:40:00") || newTime.equals("10:40:00") || newTime.equals("11:40:00") || newTime.equals("12:40:00") || newTime.equals("13:40:00") || newTime.equals("14:40:00") || newTime.equals("15:40:00") || newTime.equals("16:40:00") || newTime.equals("17:40:00") || newTime.equals("18:40:00")){
                    conn connection = new conn();
                    String updateQuery = "update course set start_time = '" + newTime + "' where cid = '" + selectedCourseId + "'";
                    connection.statement.executeUpdate(updateQuery);
                    JOptionPane.showMessageDialog(null, "Start time has been updated successfully");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Write a hour(hh:mm:ss) please");
                }

            }catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 16")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == delCourse){
            try{
                String selectedCourseId = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
                conn connection = new conn();
                String delQuery = "delete from course where cid = '"+selectedCourseId+"'";
                connection.statement.executeUpdate(delQuery);
                JOptionPane.showMessageDialog(null,"Course has been deleted successfully");
                this.remove(sp);
                createTable("select * from course",getCourseCount());
            }catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 16")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == changeTeacher){
            try{
                String selectedCourseId = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
                String newTeacher = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
                boolean valid = false;
                conn connection = new conn();
                String selectQuery = "select name from user where type = 2";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                int k = 0;
                while(rs.next()){
                    if(rs.getString("name").equals(newTeacher)){
                        conn connection2 = new conn();
                        String selectQuery2 = "select uid from user where name = '" + newTeacher + "'";
                        ResultSet rs2 = connection2.statement.executeQuery(selectQuery2);
                        rs2.next();
                        String uidTeacher = rs2.getString("uid");
                        conn connection3 = new conn();
                        String selectQuery3 = "select cid from course where teacher = '"+ uidTeacher + "'";
                        ResultSet rs3 = connection3.statement.executeQuery(selectQuery3);
                        while(rs3.next()){
                            String teacherCourse = rs3.getString("cid");
                            conn connection4 = new conn();
                            String selectQuery4 = "select day, start_time, duration from course where cid = '"+ teacherCourse + "'";
                            ResultSet rs4 = connection4.statement.executeQuery(selectQuery4);
                            rs4.next();
                            String srtTime = rs4.getString("start_time");
                            String dur = rs4.getString("duration");
                            String day = rs4.getString("day");

                            conn connection5 = new conn();
                            String selectQue = "select day, start_time, duration from course where cid = '" + selectedCourseId + "'";
                            ResultSet rs5 = connection5.statement.executeQuery(selectQue);
                            rs5.next();
                            String srtTimeSelected = rs5.getString("start_time");
                            String durSelected = rs5.getString("duration");
                            String daySelected = rs5.getString("day");

                            if(daySelected.equals(day)){
                                ArrayList<String> timePeriod = new ArrayList<String>();
                                for(int i = 0; i < Integer.valueOf(durSelected); i++){
                                    timePeriod.add(srtTimeSelected);
                                }
                                for(int i = 0; i < Integer.valueOf(durSelected); i++){
                                    timePeriod.get(i).replace(srtTime.charAt(1), ((char)Integer.parseInt(String.valueOf(srtTime.charAt(1)) + 1)));
                                }
                                ArrayList<String> timePeriodOld = new ArrayList<String>();
                                for(int i = 0; i < Integer.valueOf(dur); i++){
                                    timePeriodOld.add(srtTime);
                                }
                                for(int i = 0; i < Integer.valueOf(dur); i++){
                                    timePeriodOld.get(i).replace(srtTime.charAt(1), ((char)Integer.parseInt(String.valueOf(srtTime.charAt(1)) + 1)));
                                }

                                for(int i = 0; i < Integer.valueOf(dur); i++){
                                    if(timePeriod.contains(timePeriodOld.get(i))){
                                        valid = false;
                                    }else{
                                        valid = true;
                                    }
                                }
                            }else{
                                valid = true;
                            }
                        }
                        if(valid) {
                            String updateQuery = "update course set teacher = '" + uidTeacher + "' where cid = '" + selectedCourseId + "'";
                            connection.statement.executeUpdate(updateQuery);
                            JOptionPane.showMessageDialog(null, "Teacher has been updated successfully");
                            return;
                        }else if(valid == false && k != getTeacherCount()){
                            JOptionPane.showMessageDialog(null, "Time period is not available for given teacher");
                        }
                    }
                    k++;
                }
                if(k == getTeacherCount()){
                    JOptionPane.showMessageDialog(null, "Write a teacher from the university");

                }else{
                    this.remove(sp);
                    createTable("select * from course",getCourseCount());
                }
            }catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 16")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
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

    public int getTeacherCount(){
        int teacherCount= 0;
        try{
            conn connection = new conn();
            String query = "select count(uid) as count from user where type = 2";
            ResultSet rs = connection.statement.executeQuery(query);
            rs.next();
            int countDep = rs.getInt("count");
            teacherCount += countDep;
        }catch (Exception e){
            e.printStackTrace();
        }
        return teacherCount;
    }

    public static void main(String[] args) {
        new UpdateCourse().setVisible(true);
    }
}
