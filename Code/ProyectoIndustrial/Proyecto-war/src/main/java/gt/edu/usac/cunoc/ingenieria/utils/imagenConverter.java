package gt.edu.usac.cunoc.ingenieria.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author angelrg
 */
@Named
@ViewScoped
public class imagenConverter implements Serializable {

    public StreamedContent convertFichier(byte[] bytes) {

        if (bytes != null) {
            InputStream is = new ByteArrayInputStream(bytes);
            System.out.println("size file : " + bytes.length);
            StreamedContent image = new DefaultStreamedContent(is);
            System.out.println("dans le convertisseur : " + image.getContentType());
            return image;
        } else {
            try {
//                return new DefaultStreamedContent(new FileInputStream("../../../../../../../webapp/resources/img/usac-logo.png"));
                return new DefaultStreamedContent(new FileInputStream("/home/angelrg/Pictures/usac-logo.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error");
                return null;
            }
        }

    }
}
