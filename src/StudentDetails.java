import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StudentDetails extends JFrame {

    JTable table;
    String x[] = {"Name","Age","E-mail","Address","Phone","Date of Birth","Level","Program"};
    String y[][];

    StudentDetails(){
        this.setTitle("Student Details");
        this.setSize(1250,430);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        try{
           conn connection = new conn();
           String selectQuery= "select * from user where type = 1";
           ResultSet rs = connection.statement.executeQuery(selectQuery);

           int i = 0;
           int j = 0;
           int stCount = getStudentCount();
           y = new String[stCount][8];
           while (rs.next()){
               y[i][j++] = rs.getString("name");
               y[i][j++] = rs.getString("age");
               y[i][j++] = rs.getString("e_mail");
               y[i][j++] = rs.getString("address");
               y[i][j++] = rs.getString("phone");
               y[i][j++] = rs.getString("birthdate");

               conn connection2 = new conn();
               String level_id = rs.getString("level_id");
               System.out.println(level_id);
               System.out.println(y[i][0]);
               String selectQueryLevel = "select * from level where lid= '"+level_id+"'";
               ResultSet rsLevel = connection2.statement.executeQuery(selectQueryLevel);
               rsLevel.next();
               String level_name = rsLevel.getString("level_name");
               y[i][j++] = level_name;
               rsLevel.close();

               conn connection3 = new conn();
               String department_id = rs.getString("department_id");
               String selectQueryDep = "select * from department where did= '"+department_id+"'";
               ResultSet rsDep = connection3.statement.executeQuery(selectQueryDep);
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

        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
    }

    public int getStudentCount(){
        int studentCount = 0;
        try{
            conn connection = new conn();
            String selectQuery = "select count(uid) as count from user where type = 1";
            ResultSet rs= connection.statement.executeQuery(selectQuery);
            rs.next();
             studentCount += rs.getInt("count");

        }catch (Exception ee){
            ee.printStackTrace();
        }
        return studentCount;
    }
    public static void main(String[] args) {
        new StudentDetails();
    }
}
