import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class AcademicInformation extends JFrame {
    //ortalama,transkript(harf notları)
    JLabel gpa, gpaRes, status, statusRes;

    AcademicInformation(){
        this.setTitle("Academic Information");
        this.setSize(500,300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        double GPA = calculateGPA();

        if(GPA >= 3.0){
            String stat = defineStatus(GPA);
            status = new JLabel("Status:");
            status.setBounds(175,20,250,30);
            status.setFont(new Font(",Courier",Font.ITALIC,15));
            status.setForeground(Color.black);

            statusRes = new JLabel(stat);
            statusRes.setBounds(225,20,250,30);
            statusRes.setFont(new Font(",Courier",Font.ITALIC,15));
            statusRes.setForeground(Color.black);
        }

        gpa = new JLabel("GPA:");
        gpa.setBounds(185,50,250,30);
        gpa.setFont(new Font(",Courier",Font.CENTER_BASELINE,25));
        gpa.setForeground(Color.black);



        gpaRes = new JLabel(String.valueOf(GPA));
        gpaRes.setBounds(245,50,250,30);
        gpaRes.setFont(new Font(",Courier",Font.CENTER_BASELINE,25));
        gpaRes.setForeground(Color.black);

        this.add(status);
        this.add(statusRes);
        this.add(gpa);
        this.add(gpaRes);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.setVisible(true);
    }
    public double convertGrade(String grade){
        double result;
        if(grade.equals("A"))
            result = 4.0;
        else if(grade.equals("A-"))
            result = 3.7;
        else if(grade.equals("B+"))
            result = 3.3;
        else if(grade.equals("B"))
            result = 3.0;
        else if(grade.equals("B-"))
            result = 2.7;
        else if(grade.equals("C+"))
            result = 2.3;
        else if(grade.equals("C"))
            result = 2.0;
        else if(grade.equals("C-"))
            result = 1.7;
        else if(grade.equals("D+"))
            result = 1.3;
        else if(grade.equals("D"))
            result = 1.0;
        else{
            result = 0.0;
        }
        return result;
    }
    public double calculateGPA(){
        String userName = Login.Username;
        double gpa = 0.0;
        try{
            conn connection = new conn();
            String selectQuery = "select * from notes where user_id = (select uid from user where name = '"+userName+"')";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            double point = 0.0;
            double creditSum = 0.0;
            while(rs.next()){
                String course = rs.getString("course_id");
                String gradeAlph = rs.getString("grade");
                conn connection2 = new conn();
                String selectQuery2 = "select credit from course where cid = '"+course+"'";
                ResultSet rs2 = connection2.statement.executeQuery(selectQuery2);
                rs2.next();
                String credit = rs2.getString("credit");
                point += Double.valueOf(credit) * convertGrade(gradeAlph);
                creditSum += Double.valueOf(credit);
            }
            gpa += point / creditSum;

        }catch (Exception e){
            e.printStackTrace();
        }
        return  gpa;
    }

    public String defineStatus(double gpu){
        String result = "";
        if(gpu >= 3.5)
            result = "High Honor";
        else if (gpu >= 3.0)
            result = "Honor";
        return result;
    }

    public static void main(String[] args) {
        new AcademicInformation().setVisible(true);
    }
}
