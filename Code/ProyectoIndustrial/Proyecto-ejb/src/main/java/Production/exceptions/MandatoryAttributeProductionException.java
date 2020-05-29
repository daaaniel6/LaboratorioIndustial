/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Production.exceptions;


import java.util.List;

/**
 *
 * @author daniel
 */
public class MandatoryAttributeProductionException extends Exception{
    private List<String> messages;

    public MandatoryAttributeProductionException() {
    }

    public MandatoryAttributeProductionException(String message) {
        super(message);
    }
    
    
}
