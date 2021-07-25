import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UpdateTeacher extends JFrame implements ActionListener {
    JTable table;
    String x[] = {"Name","Age","E-mail","Address","Phone","Date of Birth","Program"};
    String y[][];
    JComboBox departments;
    JButton changeMail,changePhone,changeProgram,delTeacher;

    UpdateTeacher(){
        this.setTitle("Update Teacher");
        this.setSize(1250,530);
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

        changeProgram = new JButton("Change Program");
        changeProgram.setBounds(25,380,150,40);
        changeProgram.setFont(new Font("serif",Font.BOLD,15));
        changeProgram.setBackground(Color.white);
        changeProgram.setFocusable(false);
        changeProgram.addActionListener(this);

        ArrayList<String> programs = new ArrayList<String>();
        try {
            conn connection = new conn();
            String selectQuery = "select department_name from department";
            ResultSet rs = connection.statement.executeQuery(selectQuery);

            while(rs.next()){
                String department_name = rs.getString("department_name");
                programs.add(department_name);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        String[] programsArr = new String[programs.size()];
        for(int i = 0 ; i < programs.size(); i++){
            programsArr[i] = programs.get(i);
        }
        departments = new JComboBox(programsArr);
        departments.setBounds(200,380,160,40);
        departments.setBackground(Color.white);

        changeMail = new JButton("Change E-mail");
        changeMail.setBounds(400,380,160,40);
        changeMail.setFont(new Font("serif",Font.BOLD,15));
        changeMail.setBackground(Color.white);
        changeMail.setFocusable(false);
        changeMail.addActionListener(this);

        changePhone = new JButton("Change Phone");
        changePhone.setBounds(600,380,160,40);
        changePhone.setFont(new Font("serif",Font.BOLD,15));
        changePhone.setBackground(Color.white);
        changePhone.setFocusable(false);
        changePhone.addActionListener(this);

        delTeacher = new JButton("Delete Teacher");
        delTeacher.setBounds(800,380,160,40);
        delTeacher.setFont(new Font("serif",Font.BOLD,15));
        delTeacher.setBackground(Color.white);
        delTeacher.setFocusable(false);
        delTeacher.addActionListener(this);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,20,1200,330);

        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
        this.add(changeProgram);
        this.add(changeMail);
        this.add(changePhone);
        this.add(delTeacher);
        this.add(departments);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeProgram){
            try{
                String selectedTeacherName = (String)table.getModel().getValueAt(table.getSelectedRow(),0);
                String selected_departmentName = (String)departments.getSelectedItem();

                conn connection = new conn();
                String selectQuery = "select uid from user where name = '"+selectedTeacherName+"'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                rs.next();
                int selected_userId = rs.getInt("uid");

                conn connection2 = new conn();
                String selectQuery2 = "select did from department where department_name = '"+selected_departmentName+"'";
                ResultSet rs2 = connection2.statement.executeQuery(selectQuery2);
                rs2.next();
                int selected_departmentId = rs2.getInt("did");

                String updateQuery = "update user set department_id = "+String.valueOf(selected_departmentId)+" where uid ="+String.valueOf(selected_userId);
                connection.statement.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null,"Program have been changed succsessfully");
                this.setVisible(false);
                new UpdateTeacher().setVisible(true);
            }catch (Exception ee){
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == changeMail){
            try{
                String selectedTeacherName = (String)table.getModel().getValueAt(table.getSelectedRow(),0);
                String selectedMail = (String)table.getModel().getValueAt(table.getSelectedRow(),2);

                conn connection = new conn();
                String selectQuery = "select uid from user where name = '"+selectedTeacherName+"'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                rs.next();
                int selected_userId = rs.getInt("uid");

                String updateQuery= "update user set e_mail = '"+selectedMail+"' where uid ="+String.valueOf(selected_userId);
                connection.statement.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null,"E-mail have been changed succsessfully");
            }catch (Exception ee){
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == changePhone){
            try{
                String selectedTeacherName = (String)table.getModel().getValueAt(table.getSelectedRow(),0);
                String selectedPhone = (String)table.getModel().getValueAt(table.getSelectedRow(),4);

                conn connection = new conn();
                String selectQuery = "select uid from user where name = '"+selectedTeacherName+"'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                rs.next();
                int selected_userId = rs.getInt("uid");

                String updateQuery= "update user set e_mail = '"+selectedPhone+"' where uid ="+String.valueOf(selected_userId);
                connection.statement.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null,"Phone have been changed succsessfully");
            }catch (Exception ee){
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == delTeacher){
            try{
                String selectedTeacherName = (String)table.getModel().getValueAt(table.getSelectedRow(),0);

                conn connection = new conn();
                String selectQuery = "select uid from user where name = '"+selectedTeacherName+"'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                rs.next();
                int selected_userId = rs.getInt("uid");

                String delQuery= "delete from user where uid ="+String.valueOf(selected_userId);
                connection.statement.executeUpdate(delQuery);

                JOptionPane.showMessageDialog(null,"Teacher have been deleted succsessfully");
                this.setVisible(false);
                new UpdateStudent().setVisible(true);
            }catch (Exception ee){
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
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
}
