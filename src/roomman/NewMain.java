/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package roomman;

import Model.Room;
import View.PropertyFrame;

/**
 *
 * @author colan
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Room room = new Room(2,"702","Co cua so");
        PropertyFrame view = new PropertyFrame(room);
        view.setVisible(true);
    }
    
}
