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
public class Request implements Serializable{
    public enum ViewType{ROOM,PROPERTY};
    public enum CommandType{GET_ALL,UPDATE,DELETE,INSERT,SEARCH};
    private ViewType view;
    private CommandType command;
    private Object payload;
    
    public Request (ViewType view, CommandType command, Object payload){
        this.view = view;
        this.command = command;
        this.payload = payload;
    }

    public ViewType getView() {
        return view;
    }
    
    public CommandType getCommand() {
        return command;
    }

    public Object getPayload() {
        return payload;
    }

    public void setView(ViewType view) {
        this.view = view;
    }
    
    public void setCommand(CommandType command) {
        this.command = command;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Request{" + "view=" + view + ", command=" + command + ", payload=" + payload + '}';
    }
    
    
}

