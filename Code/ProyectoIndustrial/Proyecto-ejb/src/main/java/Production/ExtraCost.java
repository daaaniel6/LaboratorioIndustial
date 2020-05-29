/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Production;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author angelrg
 */
@Entity
@Table(name = "extra_cost")
public class ExtraCost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_extra_cost")
    private Integer idExtraCost;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "cost")
    private double cost;
    @JoinColumn(name = "id_production", referencedColumnName = "id_production")
    @ManyToOne(optional = false)
    private Production idProduction;

    public ExtraCost() {
    }

    public ExtraCost(Integer idExtraCost) {
        this.idExtraCost = idExtraCost;
    }

    public ExtraCost(Integer idExtraCost, String description, double cost) {
        this.idExtraCost = idExtraCost;
        this.description = description;
        this.cost = cost;
    }

    public Integer getIdExtraCost() {
        return idExtraCost;
    }

    public void setIdExtraCost(Integer idExtraCost) {
        this.idExtraCost = idExtraCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Production getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(Production idProduction) {
        this.idProduction = idProduction;
    }

}
