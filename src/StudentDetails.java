import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StudentDetails extends JFrame implements ActionListener{

    JTable table;
    String x[] = {"Name","Age","E-mail","Address","Phone","Date of Birth","Level","Program"};
    String y[][];
    JLabel label_level, label_department;
    JComboBox levels,departments;
    JButton search;

    StudentDetails(){
        this.setTitle("Student Details");
        this.setSize(1250,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        label_level = new JLabel("Level");
        label_level.setBounds(25,20,50,30);

        String levelsArr[] = new String[getLevelCount()];
        try{
            conn connection = new conn();
            String selectQuery = "select * from level";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            int i = 0;
            while(rs.next()){
                levelsArr[i] = rs.getString("level_name");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        levels = new JComboBox(levelsArr);
        levels.setBounds(70,20,150,30);
        levels.setBackground(Color.white);

        label_department = new JLabel("Department");
        label_department.setBounds(300,20,70,30);


        String departmentsArr[] = new String[getDepartmentCount()];
        try{
            conn connection = new conn();
            String selectQuery = "select * from department";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            int i = 0;
            while(rs.next()){
                departmentsArr[i] = rs.getString("department_name");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        departments = new JComboBox(departmentsArr);
        departments.setBounds(380,20,150,30);
        departments.setBackground(Color.white);

        search = new JButton("Search");
        search.setBounds(650,20,120,30);
        search.setFont(new Font("serif",Font.BOLD,15));
        search.addActionListener(this);
        search.setBackground(Color.white);
        search.setForeground(Color.BLACK);
        search.setFocusable(false);

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
        sp.setBounds(20,100,1200,330);

        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(label_level);
        this.add(levels);
        this.add(label_department);
        this.add(departments);
        this.add(search);
        this.add(sp);
        this.setVisible(true);
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

    public int getLevelCount(){
        int levelCount = 0;
        try{
            conn connection = new conn();
            String selectQuery = "select count(lid) as count from level";
            ResultSet rs= connection.statement.executeQuery(selectQuery);
            rs.next();
            levelCount += rs.getInt("count");

        }catch (Exception ee){
            ee.printStackTrace();
        }
        return levelCount;
    }
    public int getDepartmentCount(){
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
    public static void main(String[] args) {
        new StudentDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
