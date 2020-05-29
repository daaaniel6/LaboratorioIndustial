/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory.objects;

import Design.Design;
import java.io.Serializable;

/**
 *
 * @author angelrg
 */
public class DesignUnits implements Serializable {

    Design design;
    int units;

    public DesignUnits() {
    }

    public DesignUnits(Design design, int units) {
        this.design = design;
        this.units = units;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

}
