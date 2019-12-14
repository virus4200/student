
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class Score {
    
    
    
    public void insertUpdateDeleteStudent(char operation, Integer sid, Integer Cid,double scr, String descp)
            
    {        
        Connection con = MyConnection.getconnection();
        PreparedStatement ps;
        
        if(operation == 'i')
        {
            try {
                ps = con.prepareStatement("INSERT INTO `score`(`student_id`, `course_id`, `student_score`, `Description`) VALUES (?,?,?,?)");
                ps.setInt(1, sid);
                ps.setInt(2, Cid);
                ps.setDouble(3, scr);
                ps.setString(4, descp);
           
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null,"Score Added");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(operation == 'u')
        {
            try {
                ps = con.prepareStatement("UPDATE `score` SET `student_score`= ?,`Description`= ? WHERE `student_id`= ? AND `course_id`= ?");
                ps.setDouble(1, scr);
                ps.setString(2, descp);
                ps.setInt(3, sid);
                ps.setInt(4, Cid);
                
                
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null," Score Data Updated");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(operation == 'd')
        {
            try {
                ps = con.prepareStatement("DELETE FROM `score` WHERE `student_id`= ? AND `course_id` = ?");   
                ps.setInt(1, sid);
                ps.setInt(2, Cid);
                
                if(ps.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null," Score Deleted");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }      
}
    }
    
    public void fillScoreJtable(JTable table)
    {
        Connection con = MyConnection.getconnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM Score");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object [] row;
            while(rs.next())
            {
                row = new Object [4];
                
                row[0] = rs.getInt(1);  
                row[1] = rs.getInt(2);
                row[2] = rs.getDouble(3);
                row[3] = rs.getString(4);
                
                model.addRow(row);
            }
     
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void showAllScores(JTable table)
    {
        Connection con = MyConnection.getconnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT `student_id`,first_name,last_name,label,student_score\n" +
                                      "FROM `score` \n" +
                                      "INNER JOIN student AS stab ON stab.id= `student_id`\n" +
                                      "INNER JOIN course AS ctab ON ctab.id= `course_id`");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object [] row;
            while(rs.next())
            {
                row = new Object [5];
                
                row[0] = rs.getInt(1);  
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                
                model.addRow(row);
            }
     
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}

