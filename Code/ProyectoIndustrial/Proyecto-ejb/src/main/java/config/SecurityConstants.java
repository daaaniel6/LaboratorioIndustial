package config;

//import static com.jgranados.journals.config.ResourceConstants.JDBC_RESOURCE;
/**
 * JEE8_Tutorial-ejb
 *
 * @author jose - 29.10.2018
 * @Title: SecurityConstants
 * @Description: description
 *
 * Changes History
 */
public class SecurityConstants {

    public static final String DATASOURCE_LOOKUP = "${'" + Constants.JDBC_RESOURCE + "'}";
    public static final String CALLER_QUERY = "select password from user where carnet = ?"; //get pass by carnet
    public static final String GROUPS_QUERY = "select r.name_rol FROM rol_user r, user u "
            + "where u.carnet=? and r.id_rol = u.id_rol";
    public static final String PBKDF_ITERATIONS = "Pbkdf2PasswordHash.Iterations=3072";
    public static final String PBKDF_ALGORITHM = "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA256";
    public static final String PBKDF_SALT_SIZE = "Pbkdf2PasswordHash.SaltSizeBytes=64";
}
