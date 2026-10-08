/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Property;
import Model.Request;
import Model.Request.CommandType;
import Model.Request.ViewType;
import Model.Room;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author colan
 */
public class ServerControl {
    private ServerSocket myServer = null;
    
    private SQLFunction sqlfun = new SQLFunction();
    
    private int portNumber = 7777;
    
    public ServerControl(){
        sqlfun.getConnection();
        openServer(portNumber);
        while(true){
            listenning();
        }
    }
    
    //Function List:
    private List<Room> getAllRoom(){
        return sqlfun.getAllRoom();
    }
    
    private List<Property> getAllProperty(Room room){
        return sqlfun.getAllProperty(room);
    }
    
    private boolean updateRoom(Room room){
        return sqlfun.updateRoom(room);
    }
    
    private void updateProperty(Property property){
        sqlfun.updateProperty(property);
    }
    
    private Room searchRoomById(int id){
        return sqlfun.searchRoomById(id);
    }
    private void closeDb(){
        sqlfun.closeDb();
    }
    //Start server
    private void openServer(int portNumber){
        try 
        {
            myServer = new ServerSocket(portNumber);
            if (myServer==null) 
                System.out.println("Mo cong khong thanh cong!");
            else 
                System.out.println("Mo cong thanh cong!");
        }
        catch(IOException e) {
                e.printStackTrace();
        }
}
    private void listenning(){
        System.out.println("Goi ham Listening");
        try {    
            Socket clientSocket = myServer.accept();
            System.out.println(clientSocket.getRemoteSocketAddress()+" connected\n");
            ObjectInputStream ois = new 
            ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new 
            ObjectOutputStream(clientSocket.getOutputStream());
            oos.flush();
            while(true){
                Request o;
                try {
                    o = (Request) ois.readObject();
                } catch (java.io.IOException closed) {
                    // Client da dong ket noi
                    System.out.println(clientSocket.getRemoteSocketAddress() + " disconnected");
                    break;
                }
                ViewType view = o.getView();
                CommandType command = o.getCommand();
                //Doc request tu client:
                System.out.println(o.toString());
                if (view == Request.ViewType.ROOM){                
                        switch (command) {
                            case GET_ALL:
                                oos.writeObject(getAllRoom());
                                oos.flush();
                                break;   
                            case UPDATE:
                                updateRoom((Room)o.getPayload());
                                oos.flush();
                                break;
                            case SEARCH:
                                oos.writeObject(searchRoomById((int)o.getPayload()));
                                oos.flush();
                                break;
                            default:
                                throw new AssertionError();
                        }
                }
                else if (view == Request.ViewType.PROPERTY){                
                    switch (command) {
                        case GET_ALL:
                            Room room = (Room)o.getPayload();
                            System.out.println("Ket noi toi phong" + room.getNameRoom());
                            oos.writeObject(getAllProperty(room));
                            oos.flush();
                            break;               
                        default:
                            throw new AssertionError();
                        }
                }


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void closeServer(){
//        try {
//            closeDb();
//            myServer.close();
//        } catch (IOException ex) {
//            System.getLogger(ServerControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
//        }
//    }
    
    //IO 
    
    
}
