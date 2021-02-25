import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class AcademicInformationTeacher extends JFrame {
    JTable table;
    String x[] = {"Course Id","Course Name"};
    String y[][];
    JScrollPane sp;

    AcademicInformationTeacher(){
        this.setTitle("My Courses");
        this.setSize(500,300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        int teacher_id = 0;
        try {
            conn connection = new conn();
            String userName = Login.Username;
            ResultSet rs = connection.statement.executeQuery("select uid from user where name = '" + userName + "'");
            rs.next();
            teacher_id += rs.getInt("uid");

            conn connection2 = new conn();
            ResultSet rs2 = connection2.statement.executeQuery("select cid, title from course where teacher = '"+teacher_id+"'");

            System.out.println(teacher_id);
            int i = 0;
            int j = 0;
            y = new String[getCourseCount(teacher_id)][2];
            while (rs2.next()){
                y[i][j++] = rs2.getString("cid");
                y[i][j++] = rs2.getString("title");
                i++;
                j=0;
            }
            table = new JTable(y,x);
            table.setGridColor(Color.orange);

        }catch (Exception e){
            e.printStackTrace();
        }

        sp = new JScrollPane(table);
        sp.setBounds(20,20,450,150);

        table.setEnabled(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
        this.setVisible(true);
    }

    public int getCourseCount(int id) {
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
}
