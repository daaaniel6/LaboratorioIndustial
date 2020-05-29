package gt.edu.usac.cunoc.ingenieria.config;

import static config.Constants.ADMINISTRADOR;
import static config.Constants.DOCENTE;
import static config.Constants.ESTUDIANTE;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author angelrg
 */
@Named
@ViewScoped
public class Constants implements Serializable {

    public String docente() {
        return DOCENTE;
    }

    public String estudiante() {
        return ESTUDIANTE;
    }

    public String admin() {
        return ADMINISTRADOR;
    }

    public String adminDocente() {
        return (ADMINISTRADOR + "," + DOCENTE);
    }

    public String preProceso() {
        return ("Pre-Proceso");
    }

    public String proceso() {
        return ("Proceso");
    }

    public String postProceso() {
        return ("Post-Proceso");
    }

}
