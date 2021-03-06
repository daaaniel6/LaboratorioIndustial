package gt.edu.usac.cunoc.ingenieria.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;


@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "",
                useForwardToLogin = false
        )
)
@ApplicationScoped
@FacesConfig
public class SecurityConfig {

    /**
     * Creates a new instance of ApplicationConfig
     */
    public SecurityConfig() {
    }

}
