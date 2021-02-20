import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TeacherDetails extends JFrame implements ActionListener{

    JTable table;
    String x[] = {"Name","Age","E-mail","Address","Phone","Date of Birth","Program"};
    String y[][];
    JLabel label_dep;
    JComboBox combo_deparment;
    JButton search;
    JScrollPane sp;

    TeacherDetails(){
        this.setTitle("Teacher Details");
        this.setSize(1250,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        label_dep = new JLabel("Department");
        label_dep.setBounds(25,20,80,30);
        label_dep.setFont(new Font("serif",Font.BOLD,15));

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
        combo_deparment = new JComboBox(departmentsArr);
        combo_deparment.setBounds(120,20,150,30);
        combo_deparment.setBackground(Color.white);
        combo_deparment.insertItemAt("(None)",0);
        combo_deparment.setSelectedIndex(0);

        search = new JButton("Search");
        search.setBounds(300,20,120,30);
        search.setFont(new Font("serif",Font.BOLD,15));
        search.addActionListener(this);
        search.setBackground(Color.white);
        search.setForeground(Color.BLACK);
        search.setFocusable(false);

        createTable("select * from user where type = 2",getTeacherCount());

        table.setEnabled(false);
        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(label_dep);
        this.add(combo_deparment);
        this.add(search);
        this.add(sp);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            String selectedDep_name = (String)combo_deparment.getSelectedItem();

            String didCombo = "";
            try {
                if(!selectedDep_name.equals("(None)")) {
                    conn connection = new conn();
                    String selectQuery = "select did from department where department_name = '" + selectedDep_name + "'";
                    ResultSet rs5 = connection.statement.executeQuery(selectQuery);
                    rs5.next();
                    didCombo += rs5.getString("did");

                    this.remove(sp);
                    createTable("select * from user where type = 2 and department_id = '" + didCombo + "'", getSearchDepCount());
                }else{
                    createTable("select * from user where type = 2",getTeacherCount());
                }
            }
            catch (Exception ee){
                ee.printStackTrace();
            }
        }
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

    public void createTable(String query,int count){
        try{
            conn connection = new conn();
            ResultSet rs = connection.statement.executeQuery(query);

            int i = 0;
            int j = 0;
            y = new String[count][7];
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

        sp = new JScrollPane(table);
        sp.setBounds(20,100,1200,330);
        this.add(sp);
    }

    public int getSearchDepCount(){
        int studentCount = 0;
        String selectedDep_name = (String)combo_deparment.getSelectedItem();

        String didCombo = "";
        try{
            conn connection5 = new conn();
            String selectQuery5 = "select did from department where department_name = '"+selectedDep_name+"'";
            ResultSet rs5 = connection5.statement.executeQuery(selectQuery5);
            rs5.next();
            didCombo += rs5.getString("did");

            conn connection = new conn();
            String selectQuery = "select count(uid) as count from user where type = 2 and department_id = '"+didCombo+"'";
            ResultSet rs = connection.statement.executeQuery(selectQuery);
            rs.next();
            studentCount += rs.getInt("count");

        }catch (Exception e){
            e.printStackTrace();
        }
        return studentCount;
    }
}