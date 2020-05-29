/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import Production.NecessarySupply;
import Production.Product;
import Production.Production;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "design")
public class Design implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_design")
    private Integer idDesign;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "designId")
    private List<NecessarySupply> necessarySupplyList;

    @JoinColumn(name = "design_data", referencedColumnName = "iddesign_data")
    @ManyToOne(cascade = CascadeType.ALL)
    private DesignData designData;

    @JoinColumn(name = "product_id_product", referencedColumnName = "id_product")
    @ManyToOne
    private Product productIdProduct;

    public Design() {
    }

    public Design(Integer idDesign) {
        this.idDesign = idDesign;
    }

    public Integer getIdDesign() {
        return idDesign;
    }

    public void setIdDesign(Integer idDesign) {
        this.idDesign = idDesign;
    }

    public List<NecessarySupply> getNecessarySupplyList() {
        return necessarySupplyList;
    }

    public void setNecessarySupplyList(List<NecessarySupply> necessarySupplyList) {
        this.necessarySupplyList = necessarySupplyList;
    }

    public DesignData getDesignData() {
        return designData;
    }

    public void setDesignData(DesignData designData) {
        this.designData = designData;
    }

    public Product getProductIdProduct() {
        return productIdProduct;
    }

    public void setProductIdProduct(Product productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDesign != null ? idDesign.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Design)) {
            return false;
        }
        Design other = (Design) object;
        if ((this.idDesign == null && other.idDesign != null) || (this.idDesign != null && !this.idDesign.equals(other.idDesign))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Design[ idDesign=" + idDesign + " ]";
    }

}
