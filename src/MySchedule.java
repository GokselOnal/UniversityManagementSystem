import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MySchedule extends JFrame {
    JTable table;
    JScrollPane sp;
    String x[] = {" ","Monday","Tuesday","Wednesday","Thursday","Friday"};
    String y[][];
    MySchedule(){
        this.setTitle("My Schedule");
        this.setSize(1000,800);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        y = new String[11][6];
        y[0][0] = "08:40:00";
        y[1][0] = "09:40:00";
        y[2][0] = "10:40:00";
        y[3][0] = "11:40:00";
        y[4][0] = "12:40:00";
        y[5][0] = "13:40:00";
        y[6][0] = "14:40:00";
        y[7][0] = "15:40:00";
        y[8][0] = "16:40:00";
        y[9][0] = "17:40:00";
        y[10][0] = "18:40:00";

        String userName = Login.Username;
        try {
            conn connection = new conn();
            String selectQuery = "select uid from user where name = '" + userName + "'";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            rs.next();
            int userId = rs.getInt("uid");

            conn connection2 = new conn();
            String selectCourses = "select distinct(course_id) as courses from schedule where user_id ='"+userId+"'";
            ResultSet rs2 = connection2.statement.executeQuery(selectCourses);
            ArrayList<String> courseTaken = new ArrayList<String>();
            while (rs2.next()){
                courseTaken.add(rs2.getString("courses"));
            }
            /*for(int i = 0 ; i < courseTaken.size(); i++){
                System.out.println(courseTaken.get(i));
            }*/

            //sqlden indexleri alıp programda celleri doldurr
            table = new JTable(y, x);
            table.setGridColor(Color.orange);
        }catch (Exception e){
            e.printStackTrace();
        }
        sp = new JScrollPane(table);
        sp.setBounds(20,20,900,210);

        table.setEnabled(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MySchedule();
    }
}
