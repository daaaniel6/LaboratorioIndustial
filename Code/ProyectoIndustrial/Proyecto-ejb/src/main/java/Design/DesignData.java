/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author angelrg
 */
@Entity
@Table(name = "design_data")
public class DesignData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddesign_data")
    private Integer iddesignData;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    
    //
    @OneToMany( mappedBy = "designData")
    private List<Design> designList;
    
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productionId")
//    private List<Stage> stageList;
    
    public DesignData() {
    }

    public DesignData(Integer iddesignData) {
        this.iddesignData = iddesignData;
    }

    public DesignData(Integer iddesignData, String name) {
        this.iddesignData = iddesignData;
        this.name = name;
    }

    public Integer getIddesignData() {
        return iddesignData;
    }

    public void setIddesignData(Integer iddesignData) {
        this.iddesignData = iddesignData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<Design> getDesignList() {
        return designList;
    }

    public void setDesignList(List<Design> designList) {
        this.designList = designList;
    }

      @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddesignData != null ? iddesignData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DesignData)) {
            return false;
        }
        DesignData other = (DesignData) object;
        if ((this.iddesignData == null && other.iddesignData != null) || (this.iddesignData != null && !this.iddesignData.equals(other.iddesignData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DesignData[ iddesignData=" + iddesignData + " ]";
    }
    
}
