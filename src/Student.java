
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Student {
    
    public void insertUpdateDeleteStudent(char operation, Integer id, String fname, String lname, String sex, String phone, String address)
            
    {
        
        Connection con = MyConnection.getconnection();
        PreparedStatement ps;
        
        if(operation == 'i')
        {
            try {
                ps = con.prepareStatement("INSERT INTO student( First_name, Last_name, sex, phone, Address) VALUES (?,?,?,?,?)");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);             
                ps.setString(4, phone);
                ps.setString(5, address);
                
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"New Student Added");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(operation == 'u')
        {
            try {
                ps = con.prepareStatement("UPDATE student SET first_name = ?, last_name = ?, sex = ?, phone = ?, address = ?  WHERE id = ?");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);             
                ps.setString(4, phone);
                ps.setString(5, address);
                ps.setInt(6, id);
                
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null," Student Data Updated");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(operation == 'd')
        {
            int YesOrNo = JOptionPane.showConfirmDialog(null, "The Score Will Be Also Deleted ","Delete Student",JOptionPane.OK_CANCEL_OPTION,0);
            if(YesOrNo == JOptionPane.OK_OPTION)
            {
              try {
                ps = con.prepareStatement("DELETE FROM `student` WHERE `id` = ?");   
                ps.setInt(1, id);
                
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null," Student Deleted");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }  
            }
            
        }
        
    }
    
    public void fillStudentJtable(JTable table, String valueToSearch)
    {
        Connection con = MyConnection.getconnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM student WHERE CONCAT(first_name,last_name,phone,address)LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object [] row;
            while(rs.next())
            {
                row = new Object [6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                
                model.addRow(row);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
