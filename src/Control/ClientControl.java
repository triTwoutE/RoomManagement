/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author colan
 */
public class ClientControl {
    private Socket mySocket;
    private String serverHost = "localhost";
    private int serverPort = 7777;
    
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    public ClientControl(){
    }
    
    public Socket openConnection(){
    try {
        mySocket = new Socket(serverHost, serverPort);     
        if (mySocket==null) System.out.println("Khong the mo ket noi");
        else {
            System.out.println("Mo ket noi thanh cong");
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(mySocket.getInputStream());
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
    return mySocket;
    }
    
    public boolean sendRequest(Request o){
        try {
            
            oos.writeObject(o);
            oos.flush();
        } catch (IOException ex) {
            System.getLogger(ClientControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
        return true;        
    }
    
    public Object receiveData(){
        Object o = null;
        try {                       
            
            o = ois.readObject(); 
        } catch (IOException ex) {
            System.getLogger(ClientControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(ClientControl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return o;
    }
    
    
    
    
    
    
    public boolean closeConnection(){
        try {
         mySocket.close();
                } catch (Exception ex) {
                 ex.printStackTrace();        return false;
                }
        return true;
    }
}
