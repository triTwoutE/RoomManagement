/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author colan
 */
public class Room implements Serializable{
    private int idRoom;
    private String nameRoom;
    private String descriptionRoom;

    public Room(){}

    public Room(int id, String name, String des){
        this.idRoom = id;
        this.nameRoom = name;
        this.descriptionRoom = des;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public String getDescriptionRoom() {
        return descriptionRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public void setDescriptionRoom(String descriptionRoom) {
        this.descriptionRoom = descriptionRoom;
    }
        
        
}
