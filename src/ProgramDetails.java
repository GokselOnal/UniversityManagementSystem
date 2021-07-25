import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class ProgramDetails extends JFrame {

    JTable table;
    String x[] = {"Department name"};
    String y[][];

    ProgramDetails(){
        this.setTitle("Program Details");
        this.setSize(400,600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        y = new String[getDepCount()][1];
        try{
            conn connection = new conn();
            String selectQuery = "select * from department";
            ResultSet rs = connection.statement.executeQuery(selectQuery);

            int i = 0;
            while (rs.next()){
                y[i][0] = rs.getString("department_name");
                i++;
            }
            table = new JTable(y,x);
            table.setGridColor(Color.orange);
            table.setEnabled(false);

        }catch (Exception ee){
            ee.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,20,340,440);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
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
}
