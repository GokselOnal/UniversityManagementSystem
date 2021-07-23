import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Transcript extends JFrame {

    JTable table;
    String x[] = {"Year","Course","Grade"};
    String y[][];

    JScrollPane sp;

    Transcript(){
        this.setTitle("Transcript");
        this.setSize(1250,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        String userName = Login.Username;
        createTable("select * from notes where user_id = (select uid from user where name = '"+userName+"') ",getCourseCount());


        table.setEnabled(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
        this.setVisible(true);
    }

    public void createTable(String query, int number){
        try{
            conn connection = new conn();
            ResultSet rs = connection.statement.executeQuery(query);

            int i = 0;
            int j = 0;
            y = new String[number][8];
            while (rs.next()){
                y[i][j++] = rs.getString("year");
                y[i][j++] = rs.getString("course_id");
                y[i][j++] = rs.getString("grade");
                i++;
                j=0;
            }
            table = new JTable(y,x);
            table.setGridColor(Color.orange);

        }catch (Exception ee){
            ee.printStackTrace();
        }
        sp = new JScrollPane(table);
        sp.setBounds(20,100,1200,330);
        this.add(sp);
    }

    public int getCourseCount(){
        int courseCount = 0;
        String userName = Login.Username;
        try{
            conn connection = new conn();
            String selectQuery = "select count(course_id) as count from notes where user_id = (select uid from user where name = '"+userName+"')";
            ResultSet rs= connection.statement.executeQuery(selectQuery);
            rs.next();
            courseCount += rs.getInt("count");

        }catch (Exception ee){
            ee.printStackTrace();
        }
        return courseCount;
    }
}
