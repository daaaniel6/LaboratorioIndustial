/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production.production.view;

import Supply.Measure;
import Supply.Supply;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
//@Named
//@ApplicationScoped
@Stateless
@LocalBean
public class SupplyService {

    //List<Supply> supplies = new ArrayList<Supply>();
    public List<Supply> createSupplies(int size) {
        List<Supply> list = new ArrayList<Supply>();
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();
        Measure measure = new Measure();
        measure.setName("medida 1");
//        Supply su1 = new Supply(1, "hola", date1, date2, 11.3, 1.2, true, "description", measure);
//        list.add(su1);
//        list.add(new Supply(2, "holae", date1, date2, 12.3, 5.3, false, "hola", measure));

        return list;
    }

}