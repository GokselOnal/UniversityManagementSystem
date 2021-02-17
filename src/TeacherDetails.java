import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TeacherDetails extends JFrame {

    JTable table;
    String x[] = {"Name","Age","E-mail","Address","Phone","Date of Birth","Program"};
    String y[][];

    TeacherDetails(){
        this.setTitle("Teacher Details");
        this.setSize(1250,430);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        try{
            conn connection = new conn();
            String selectQuery= "select * from user where type = 2";
            ResultSet rs = connection.statement.executeQuery(selectQuery);

            int i = 0;
            int j = 0;
            int tCount = getTeacherCount();
            y = new String[tCount][7];
            while (rs.next()){
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("age");
                y[i][j++] = rs.getString("e_mail");
                y[i][j++] = rs.getString("address");
                y[i][j++] = rs.getString("phone");
                y[i][j++] = rs.getString("birthdate");

                conn connection2 = new conn();
                String department_id = rs.getString("department_id");
                String selectQueryDep = "select * from department where did= '"+department_id+"'";
                ResultSet rsDep = connection2.statement.executeQuery(selectQueryDep);
                rsDep.next();
                String department_name = rsDep.getString("department_name");
                y[i][j++] = department_name;

                i++;
                j=0;
            }
            table = new JTable(y,x);
            table.setGridColor(Color.orange);

        }catch (Exception ee){
            ee.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,20,1200,330);

        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
        this.setResizable(false);
    }

    public int getTeacherCount(){
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
    public static void main(String[] args) {
        new TeacherDetails();
    }
}