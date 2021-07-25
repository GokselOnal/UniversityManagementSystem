import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MyScheduleTeacher extends JFrame {
    JTable table;
    JScrollPane sp;
    String x[] = {" ","Monday","Tuesday","Wednesday","Thursday","Friday"};
    String y[][];
    MyScheduleTeacher(){
        this.setTitle("My Schedule");
        this.setSize(1000,300);
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

        int teacher_id = 0;
        try {
            conn connection = new conn();
            String userName = Login.Username;
            ResultSet rs = connection.statement.executeQuery("select uid from user where name = '" + userName + "'");
            rs.next();
            teacher_id += rs.getInt("uid");

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

                conn connection3 = new conn();
                String selectQuery = "select * from course where teacher = '" +teacher_id + "'";
                ResultSet rs3 = connection3.statement.executeQuery(selectQuery);

                while(rs3.next()) {
                    String courseId = rs3.getString("cid");
                    String courseDay = rs3.getString("day");
                    String courseStartTime = rs3.getString("start_time");
                    int courseDuration = rs3.getInt("duration");
                    int selectedDayIndex = 0;
                    for (int i = 0; i < program[0].length; i++) {
                        if (program[0][i].equals(courseDay)) {
                            selectedDayIndex += i;
                        }
                    }

                    int selectedTimeIndex = 0;
                    for (int i = 0; i < program.length; i++) {
                        if (program[i][selectedDayIndex].equals(courseStartTime)) {
                            selectedTimeIndex += i;
                        }
                    }
                    while (courseDuration > 0) {
                        y[selectedTimeIndex - 1][selectedDayIndex + 1] = courseId;
                        selectedTimeIndex++;
                        courseDuration--;
                    }
                }

            table = new JTable(y, x);
            table.setGridColor(Color.orange);
        }catch (Exception e){
            e.printStackTrace();
        }
        sp = new JScrollPane(table);
        sp.setBounds(40,20,900,205);

        table.setEnabled(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
        this.setVisible(true);
    }
}
