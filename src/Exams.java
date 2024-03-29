import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Calendar;

public class Exams extends JFrame implements ActionListener {
    JTable table;
    JScrollPane sp;
    String x[] = {"Student","Grade"};
    String y[][];
    JComboBox courses;
    JButton select,submit,view;
    Exams(){
        this.setTitle("Exams");
        this.setSize(600,800);
        this.setLayout(null);
        this.setResizable(false);

        try{
            String userName = Login.Username;
            conn connection = new conn();
            ResultSet rs = connection.statement.executeQuery("select uid from user where name = '"+userName+"'");
            rs.next();
            int teacher_id = rs.getInt("uid");
            String[] course = new String[getCourseCount(teacher_id)];
            conn connection2 = new conn();
            ResultSet rs2 = connection2.statement.executeQuery("select cid from course where teacher = '"+teacher_id+"'");
            int i = 0;
            while (rs2.next()){
                course[i] = rs2.getString("cid");
                i++;
            }
            courses = new JComboBox(course);
            courses.setBounds(40,20,100,30);
            courses.setBackground(Color.white);

        }catch (Exception e){
            e.printStackTrace();

        }

        select = new JButton("Select");
        select.setBounds(150,20,100,30);
        select.setFont(new Font("serif",Font.BOLD,15));
        select.addActionListener(this);
        select.setBackground(Color.white);
        select.setForeground(Color.BLACK);
        select.setFocusable(false);

        view = new JButton("View");
        view.setBounds(260,20,100,30);
        view.setFont(new Font("serif",Font.BOLD,15));
        view.addActionListener(this);
        view.setBackground(Color.white);
        view.setForeground(Color.BLACK);
        view.setFocusable(false);

        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(courses);
        this.add(select);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public int getCourseCount(int id){
        int courseCount = 0;
        try {
            conn connection = new conn();
            String query = "select count(cid) as count from course where teacher = '" + id + "'";
            ResultSet rs = connection.statement.executeQuery(query);
            rs.next();
            int countDep = rs.getInt("count");
            courseCount += countDep;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseCount;
    }

    public int getCourseStudentCount(String id){
        int studentCount = 0;
        try {
            conn connection = new conn();
            String query = "select count(distinct(user_id)) as count from schedule where course_id = '" + id + "'";
            ResultSet rs = connection.statement.executeQuery(query);
            rs.next();
            int countDep = rs.getInt("count");
            studentCount += countDep;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentCount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == select){
            try{
                conn connection = new conn();
                String cid = (String)courses.getSelectedItem();
                y = new String[getCourseStudentCount(cid)][2];
                ResultSet rs = connection.statement.executeQuery("select name from user where uid in (select distinct(user_id) as uid from schedule where course_id = '"+cid+"')");
                int i = 0;
                int j = 0;
                while (rs.next()){
                    y[i][j++] = rs.getString("name");

                    i++;
                    j= 0;
                }
                table = new JTable(y,x);
            }catch (Exception ee){
                ee.printStackTrace();
            }
            sp = new JScrollPane(table);
            sp.setBounds(40,60,500,650);

            submit = new JButton("Submit");
            submit.setBounds(435,720,100,30);
            submit.setFont(new Font("serif",Font.BOLD,15));
            submit.addActionListener(this);
            submit.setBackground(Color.white);
            submit.setForeground(Color.BLACK);
            submit.setFocusable(false);

            this.add(submit);
            this.add(sp);
            this.repaint();
        }
        else if(e.getSource() == submit){
            String selectedCourse = (String)courses.getSelectedItem();
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            String alph = "";
            try {
                conn connection = new conn();

                for (int i = 0; i < getCourseStudentCount(selectedCourse); i++) {
                    String selectedStudentName = (String) table.getModel().getValueAt(i, 0);
                    ResultSet rs = connection.statement.executeQuery("select uid from user where name = '"+selectedStudentName+"'");
                    rs.next();
                    String selectedUserId = rs.getString("uid");
                    String selectedStudentGrade = (String) table.getModel().getValueAt(i, 1);
                    if((Integer.valueOf(selectedStudentGrade) >= 90) && (Integer.valueOf(selectedStudentGrade) <= 100)){
                        alph += "A";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 85) && (Integer.valueOf(selectedStudentGrade) < 90)){
                        alph += "A-";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 80) && (Integer.valueOf(selectedStudentGrade) < 85)){
                        alph += "B+";
                    } else if((Integer.valueOf(selectedStudentGrade) >= 75) && (Integer.valueOf(selectedStudentGrade) < 80)){
                        alph += "B";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 70) && (Integer.valueOf(selectedStudentGrade) < 75)){
                        alph += "B-";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 65) && (Integer.valueOf(selectedStudentGrade) < 70)){
                        alph += "C+";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 60) && (Integer.valueOf(selectedStudentGrade) < 65)){
                        alph += "C";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 55) && (Integer.valueOf(selectedStudentGrade) < 60)){
                        alph += "C-";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 50) && (Integer.valueOf(selectedStudentGrade) < 55)){
                        alph += "D+";
                    }else if((Integer.valueOf(selectedStudentGrade) >= 45) && (Integer.valueOf(selectedStudentGrade) < 50)){
                        alph += "D";
                    }else{
                        alph += "F";
                    }
                    if((Integer.valueOf(selectedStudentGrade) >= 0) && (Integer.valueOf(selectedStudentGrade) <= 100)){
                        String insertQuery = "insert into notes (user_id, course_id, year, grade) values('"+selectedUserId+"','"+selectedCourse+"','"+year+"','"+alph+"')";
                        connection.statement.executeUpdate(insertQuery);

                        conn connection2 = new conn();
                        int success = 0;
                        if(!alph.equals("F"))
                            success = 1;
                        String insertQuery2 = "insert into takes (student_id,course_id,success) values('"+selectedUserId+"','"+selectedCourse+"','"+success+"')";
                        connection2.statement.executeUpdate(insertQuery2);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Grades must between 0 - 100");
                    }
                    alph = "";
                }
                JOptionPane.showMessageDialog(null,"Grades are entered successfully");
                this.setVisible(false);
            }catch (Exception ex){
                ex.printStackTrace();
                if(String.valueOf(ex).startsWith("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry")){
                    JOptionPane.showMessageDialog(null,"You have already entered grades");
                }
            }
        }
    }
}
