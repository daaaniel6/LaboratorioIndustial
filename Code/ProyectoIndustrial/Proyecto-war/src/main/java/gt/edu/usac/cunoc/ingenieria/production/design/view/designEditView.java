/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production.design.view;

import Design.Design;
import Production.NecessarySupply;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.facade.ProductionFacadeLocal;
import Supply.Supply;
import Supply.facade.SupplyFacadeLocal;
import static config.Constants.MAIN_PAGE;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author daniel
 */
@Named
@ViewScoped
public class designEditView implements Serializable {

    private static final String DESIGN_EDIT = "Diseño Editado Correctamente";

    @EJB
    private SupplyFacadeLocal supplyFacade;
    @EJB
    private ProductionFacadeLocal productionFacadeLocal;
    @Inject
    private ExternalContext externalContext;

    private Design designSelectedEdit;
    private List<Design> listDesignEdit;

    private List<Supply> supplies;
    private List<NecessarySupply> suppliesSelected;

    private StreamedContent imageSelect;

    private UploadedFile image;
    private StreamedContent imageStream;
    String nameImage = "";

    @PostConstruct
    public void init() {
        designSelectedEdit = new Design();
        listDesignEdit = new ArrayList<>();

        listDesignEdit = productionFacadeLocal.AllDesigns();

    }

    public void editDesign() {
        try {
            //designSelectedEdit.getDesignData().setPicture(image.getContents());
            designSelectedEdit.setNecessarySupplyList(suppliesSelected);
            productionFacadeLocal.editDesign(designSelectedEdit);
            MessageUtils.addSuccessMessage(DESIGN_EDIT);
            listDesignEdit = productionFacadeLocal.AllDesigns();
            externalContext.getFlash().setKeepMessages(true);
            externalContext.redirect(externalContext.getRequestContextPath() + MAIN_PAGE);
        } catch (MandatoryAttributeProductionException ex) {
            MessageUtils.addErrorMessage(ex.getMessage());
            listDesignEdit = productionFacadeLocal.AllDesigns();
            //Logger.getLogger(designEditView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(designEditView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StreamedContent convertFichier(byte[] bytes) {

        InputStream is = new ByteArrayInputStream(bytes);
        System.out.println("size file : " + bytes.length);
        StreamedContent image = new DefaultStreamedContent(is);
        System.out.println("dans le convertisseur : " + image.getContentType());
        return image;

    }

    public void selectDesignEdit(Design design) {
        designSelectedEdit = design;
        suppliesSelected = new ArrayList<>();
        suppliesSelected = design.getNecessarySupplyList();
        List<Supply> suppliesAux = supplyFacade.getSupplyAvailable();

        for (int i = 0; i < suppliesAux.size(); i++) {

            for (int j = 0; j < suppliesSelected.size(); j++) {
                if (suppliesAux.get(i).getCode() == suppliesSelected.get(j).getSupplyCode().getCode()) {
                    suppliesAux.remove(suppliesAux.get(i));
                }
            }

        }

        if (design.getDesignData().getPicture() != null) {
            imageSelect = convertFichier(design.getDesignData().getPicture());

        }
        if (imageSelect == null) {
            imageSelect = new ByteArrayContent();
        }
        this.setSupplies(suppliesAux);
    }

    public void handleImage(FileUploadEvent event) {
        image = event.getFile();
        nameImage = event.getFile().getFileName();

        designSelectedEdit.getDesignData().setPicture(image.getContents());

        if (designSelectedEdit.getDesignData().getPicture() != null) {
            imageSelect = convertFichier(designSelectedEdit.getDesignData().getPicture());

        }
        if (imageSelect == null) {
            imageSelect = new ByteArrayContent();
        }

    }

    public void addSupplyEdit(Supply supply) {
        NecessarySupply necessarySupply = new NecessarySupply(null, 0.1);
        necessarySupply.setDesignId(designSelectedEdit);
        necessarySupply.setSupplyCode(supply);

        suppliesSelected.add(necessarySupply);

        supplies.remove(supply);
        //suppliesSelected.add(supply);
    }

    public void removeSupplyEdit(NecessarySupply supplyNecessary) {
        suppliesSelected.remove(supplyNecessary);
        supplies.add(supplyNecessary.getSupplyCode());
    }

    public void updateDesigns() {
        listDesignEdit = productionFacadeLocal.AllDesigns();
    }

    public Design getDesignSelectedEdit() {
        return designSelectedEdit;
    }

    public void setDesignSelectedEdit(Design designSelectedEdit) {
        this.designSelectedEdit = designSelectedEdit;
    }

    public List<Design> getListDesignEdit() {
        return listDesignEdit;
    }

    public void setListDesignEdit(List<Design> listDesignEdit) {
        this.listDesignEdit = listDesignEdit;
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public List<NecessarySupply> getSuppliesSelected() {
        return suppliesSelected;
    }

    public void setSuppliesSelected(List<NecessarySupply> suppliesSelected) {
        this.suppliesSelected = suppliesSelected;
    }

//    public List<NecessarySupply> getNecessarySupplys() {
//        return necessarySupplys;
//    }
//
//    public void setNecessarySupplys(List<NecessarySupply> necessarySupplys) {
//        this.necessarySupplys = necessarySupplys;
//    }
    public StreamedContent getImageSelect() {
        return imageSelect;
    }

    public void setImageSelect(StreamedContent imageSelect) {
        this.imageSelect = imageSelect;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public StreamedContent getImageStream() {
        return imageStream;
    }

    public void setImageStream(StreamedContent imageStream) {
        this.imageStream = imageStream;
    }

}
