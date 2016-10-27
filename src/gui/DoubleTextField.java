/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.TextField;

/**
 *
 * @author Robin Van den Broeck
 */
public class DoubleTextField extends TextField{
     

    public double getDoubleText() throws Exception{
        double i;
        try{
            i = Double.parseDouble(this.getText());
        }catch(Exception e){
            throw new Exception("Dit is een numeriek veld");
        }
        return i;
    }
    
}
