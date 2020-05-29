package Modify;

import java.io.Serializable;

/**
 *
 * @author angelrg
 */
public enum ModificationType implements Serializable {
    CANTIDAD,
    ATRIBUTOS;
    
    public String dropDownName(){
        switch(this){
            case CANTIDAD:
                return "Por Faltante";
            default:
                return "Por Atributos";
        }
    }
    
    
}
