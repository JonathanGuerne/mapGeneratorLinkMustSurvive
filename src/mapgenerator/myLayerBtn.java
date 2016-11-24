/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author jonathan.guerne
 */
public class myLayerBtn extends JButton{
    
    public String code;
    
    public myLayerBtn(Icon icn,String code){
        super(icn);
        this.code = code;
    }
    
}
