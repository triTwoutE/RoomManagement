/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Property;
import Model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author colan
 */
public class SQLFunction{
    private Connection con = null;
    //Connect db
    String DB_Class = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://127.0.0.1:3306/roomdata";
    String USER_NAME = "root";
    String PASSWORD = "";
    public void getConnection(){            
            try {
                Class.forName(DB_Class);
                con = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
                if (con == null)
                    System.out.println("Connect to fail!");
                else
                    System.out.println("Connect to DB successful!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }                
    }
    
    //Get database data
    public List<Room> getAllRoom(){
        List<Room> listRoom = new ArrayList<>();
        try {
            
            String sqlQuere = "SELECT * FROM room";
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sqlQuere);
            while(res.next())              
                listRoom.add(new Room(res.getInt("id"),res.getString("name"),res.getString("description")));
                
            
        } catch (SQLException ex) {
            System.getLogger(ServerControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return listRoom;
    }
    
    List<Property> getAllProperty(Room room){
        List<Property> listProperty = new ArrayList<>();
        try {
            
            String idRoom = Integer.toString(room.getIdRoom());
            String sqlQuere = "SELECT * FROM property WHERE roomid = " + idRoom;
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sqlQuere);
            while(res.next())              
                listProperty.add(new Property(res.getInt("id"),res.getString("name"),res.getString("category"),res.getString("position"),res.getLong("value")));                           
        } catch (SQLException ex) {
            System.getLogger(ServerControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return listProperty;
    }
    
    //Update function
    public boolean updateRoom(Room room){
        String sqlString = "UPDATE room SET name = ?, description = ? WHERE id = ?";
        try{
            
            PreparedStatement pstm = con.prepareStatement(sqlString);
            pstm.setString(1, room.getNameRoom());
            pstm.setString(2, room.getDescriptionRoom());
            pstm.setInt(3, room.getIdRoom());
            pstm.executeUpdate();
        }
        catch (SQLException ex) {
            System.getLogger(ServerControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updateProperty(Property property){
        String sqlString = "UPDATE property SET name = ?, category = ?, position =?, value= ? WHERE id = ?";
        try{
            
            PreparedStatement pstm = con.prepareStatement(sqlString);
            pstm.setString(1, property.getNamePro());
            pstm.setString(2, property.getCategoryPro());
            pstm.setString(3, property.getPositionPro());
            pstm.setLong(4, property.getValuePro());
            pstm.setInt(5, property.getIdPro());
            pstm.executeUpdate();
        }
        catch (SQLException ex) {
            System.getLogger(ServerControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
        return true;
    }
    
    public Room searchRoomById(int id){
        String sqlString = "SELECT * FROM room WHERE id = " + id;
        Room room = null;
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sqlString);
            res.next();
            room = new Room(res.getInt("id"),res.getString("name"),res.getString("description"));
        } catch (SQLException ex) {
            System.getLogger(SQLFunction.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return room;
    }
    
    public void closeDb(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.getLogger(SQLFunction.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}