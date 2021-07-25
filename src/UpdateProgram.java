import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateProgram extends JFrame implements ActionListener {

    JTable table;
    String x[] = {"Department name"};
    String y[][];
    JButton changeName,del;

    UpdateProgram(){
        this.setTitle("Update Program");
        this.setSize(500,700);
        this.setLayout(null);

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

        }catch (Exception ee){
            ee.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(65,20,340,440);


        changeName = new JButton("Change name");
        changeName.setBounds(160,500,160,40);
        changeName.setFont(new Font("serif",Font.BOLD,15));
        changeName.setBackground(Color.white);
        changeName.setFocusable(false);
        changeName.addActionListener(this);

        del = new JButton("Delete department");
        del.setBounds(160,560,160,40);
        del.setFont(new Font("serif",Font.BOLD,15));
        del.setBackground(Color.white);
        del.setFocusable(false);
        del.addActionListener(this);

        this.getContentPane().setBackground(new Color(255,140,0));
        this.add(sp);
        this.add(changeName);
        this.add(del);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeName){

            try{
                String selectedDep = (String)table.getModel().getValueAt(table.getSelectedRow(),0);
                int selectedRowIndex = table.getSelectedRow();
                ProgramDetails pd = new ProgramDetails();
                String selectedName = (String)pd.table.getModel().getValueAt(selectedRowIndex,0); // eski isim

                conn connection = new conn();
                String selectQuery = "select * from department where department_name = '"+selectedDep+"'";
                ResultSet rs = connection.statement.executeQuery(selectQuery);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Change the department name on selected row please");
                }
                else{
                    conn connection2 = new conn();

                    String updateQuery = "update department set department_name = '"+selectedDep+"' where department_name = '"+selectedName+"'";
                    connection2.statement.executeUpdate(updateQuery);
                    JOptionPane.showMessageDialog(null,"Program have been updated sucsessfully");
                }
            }catch (Exception ee){
                ee.printStackTrace();
                if(String.valueOf(ee).startsWith("java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds")){
                    JOptionPane.showMessageDialog(null,"Select a row for update please");
                }
            }
        }
        else if(e.getSource() == del){
            try{
                String selectedDep = (String)table.getModel().getValueAt(table.getSelectedRow(),0);
                conn connection = new conn();
                String deleteQuery = "delete from department where department_name = '"+selectedDep+"'";
                connection.statement.executeUpdate(deleteQuery);
                JOptionPane.showMessageDialog(null,"Program have been deleted sucsessfully");
                this.setVisible(false);
                new UpdateProgram();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }
}
