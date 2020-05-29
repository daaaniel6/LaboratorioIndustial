/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template image, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production.design.view;

import Design.Design;
import Design.DesignData;
import Production.NecessarySupply;
import Production.Product;
import Production.facade.ProductionFacadeLocal;
import Supply.Supply;
import Supply.facade.SupplyFacadeLocal;
import static config.Constants.MAIN_PAGE;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author daniel
 */
@Named
@ViewScoped
public class designView implements Serializable {

    private static final String DESIGN_CREATED = "Diseño Creado Correctamente";
    private static final String ERROR_NECESSARY_SUPPLYS = "No hay insumos";
    private static final String ERROR_PICTURE = "No Se selecciono ninguna imagen";

    private List<Supply> supplies;
    private List<Supply> suppliesSelected;
    private List<NecessarySupply> necessarySupplys;

    //producto seleccionado y lisado de productos para el comboBox
    private List<Product> products;
    private Product productSelect;

    private UploadedFile image;
    private StreamedContent imageStream;
    String nameImage = "";

    private Supply selectedSupply;
    private Design designCreate;
    private DesignData designDataCreate;

    @EJB
    private SupplyFacadeLocal supplyFacade;
    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    @Inject
    private ExternalContext externalContext;

    @PostConstruct
    public void init() {
        //Productos del Combobox
        products = productionFacadeLocal.getProduct();
        productSelect = new Product();

        designCreate = new Design();

        designDataCreate = new DesignData();

        supplies = supplyFacade.getSupplyAvailable();

        suppliesSelected = new ArrayList<>();
        necessarySupplys = new ArrayList<>();

    }

    public void createDesign() {
        try {
            if (!necessarySupplys.isEmpty()) {
                if (image != null) {
                    //getContents Devuelve el arreglo de bytes
                    designDataCreate.setPicture(image.getContents());
                    productionFacadeLocal.createDesign(designCreate, designDataCreate, necessarySupplys);
                    MessageUtils.addSuccessMessage(DESIGN_CREATED);
                    externalContext.getFlash().setKeepMessages(true);
                    externalContext.redirect(externalContext.getRequestContextPath() + MAIN_PAGE);
                } else {
                    MessageUtils.addErrorMessage(ERROR_PICTURE);
                }

            } else {
                MessageUtils.addErrorMessage(ERROR_NECESSARY_SUPPLYS);
            }
        } catch (Exception e) {
            MessageUtils.addErrorMessage(e.getMessage());
        }
    }

    public void addSupplyCreate(Supply supply) {
        NecessarySupply necessarySupply = new NecessarySupply(null, 0.1);
        necessarySupply.setDesignId(designCreate);
        necessarySupply.setSupplyCode(supply);

        necessarySupplys.add(necessarySupply);

        supplies.remove(supply);
        suppliesSelected.add(supply);
    }

    public void removeSupplyCreate(NecessarySupply supplyNecessary) {
        necessarySupplys.remove(supplyNecessary);

        suppliesSelected.remove(supplyNecessary);
        supplies.add(supplyNecessary.getSupplyCode());
    }

    public void upload() {
        if (image != null) {
            FacesMessage message = new FacesMessage("Successful", image.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleImage(FileUploadEvent event) {
        image = event.getFile();
        nameImage = event.getFile().getFileName();
    }

    public void view() {

        try {
            if (designDataCreate.getPicture() != null) {
                byte[] arreglo = designDataCreate.getPicture();

                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.getOutputStream().write(arreglo);
                response.getOutputStream().close();

                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (IOException ex) {
            FacesMessage message = new FacesMessage("Error");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public List<Supply> getSuppliesSelected() {
        return suppliesSelected;
    }

    public void setSuppliesSelected(List<Supply> suppliesSelected) {
        this.suppliesSelected = suppliesSelected;
    }

    public Supply getSelectedSupply() {
        return selectedSupply;
    }

    public void setSelectedSupply(Supply selectedSupply) {
        this.selectedSupply = selectedSupply;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public List<NecessarySupply> getNecessarySupplys() {
        return necessarySupplys;
    }

    public void setNecessarySupplys(List<NecessarySupply> necessarySupplys) {
        this.necessarySupplys = necessarySupplys;
    }

    public Design getDesignCreate() {
        return designCreate;
    }

    public void setDesignCreate(Design designCreate) {
        this.designCreate = designCreate;
    }

    public DesignData getDesignDataCreate() {
        return designDataCreate;
    }

    public void setDesignDataCreate(DesignData designDataCreate) {
        this.designDataCreate = designDataCreate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProductSelect() {
        return productSelect;
    }

    public void setProductSelect(Product productSelect) {
        this.productSelect = productSelect;
    }

    public StreamedContent getImageStream() {
        return imageStream;
    }

    public void setImageStream(StreamedContent imageStream) {
        this.imageStream = imageStream;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

}
