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
public class Property implements Serializable{
    
    private int idPro;
    private String namePro, categoryPro, positionPro;
    private long valuePro;
    
    public Property(){}

    public Property(int idPro, String namePro, String categoryPro, String positionPro, long valuePro) {
        this.idPro = idPro;
        this.namePro = namePro;
        this.categoryPro = categoryPro;
        this.positionPro = positionPro;
        this.valuePro = valuePro;
    }

    public int getIdPro() {
        return idPro;
    }

    public String getNamePro() {
        return namePro;
    }

    public String getCategoryPro() {
        return categoryPro;
    }

    public String getPositionPro() {
        return positionPro;
    }

    public long getValuePro() {
        return valuePro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public void setNamePro(String namePro) {
        this.namePro = namePro;
    }

    public void setCategoryPro(String categoryPro) {
        this.categoryPro = categoryPro;
    }

    public void setPositionPro(String positionPro) {
        this.positionPro = positionPro;
    }

    public void setValuePro(long valuePro) {
        this.valuePro = valuePro;
    }
    
    
}
