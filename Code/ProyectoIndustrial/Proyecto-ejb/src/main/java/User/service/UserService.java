package User.service;

import User.User;
import User.exception.UserException;
import User.repository.UserRepository;
import static config.Constants.JAVA_MAIL_SESSION;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Stateless
@LocalBean
public class UserService {

    public static final String CONTENT_TYPE = "text/html; charset=utf-8";

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    @Resource(name = JAVA_MAIL_SESSION)
    private Session emailSession;

    @Resource
    SessionContext securityContext;

    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;

    @EJB
    UserRepository userRepository;

    public void setPbkdf2PasswordHash(Pbkdf2PasswordHash pbkdf2PasswordHash) {
        this.pbkdf2PasswordHash = pbkdf2PasswordHash;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User createUser(User user) throws UserException {
        if (user == null) {
            throw new UserException("User is null");
        }
        user.setPassword(encryptPass(user.getPassword()));
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            System.out.println("error persistencia");
        }
        return user;
    }

    public User updateUser(User user) throws UserException {
        if (user == null) {
            throw new UserException("User is null");
        }
        User updateUser = entityManager.find(User.class, user.getCarnet());
        if (user.getName() != null) {
            updateUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            updateUser.setEmail(user.getEmail());
        }
        if (user.getPhone() != null) {
            updateUser.setPhone(user.getPhone());
        }
        if (user.getPassword() != null) {
            updateUser.setPassword(encryptPass(user.getPassword()));
        }
        if (user.getState() != null) {
            updateUser.setState(user.getState());
        }
        if (user.getRolUser() != null) {
            updateUser.setRolUser(user.getRolUser());
        }
        if (user.getCareer() != null) {
            updateUser.setCareer(user.getCareer());
        }

        return updateUser;
    }

    public List<User> getAuthenticatedUser() throws UserException {
        String carnet = securityContext.getCallerPrincipal().getName();
        return userRepository.getUser(new User(Integer.parseInt(carnet), null, null, null, null, null, null, null));
    }

    /**
     * Require the user, to reset the password
     *
     * Use UUID strategy to generate the password
     *
     * @param user
     * @return
     * @throws UserException
     */
    public User resetPassword(User user) throws UserException {

        if (user != null) {

            String pass = newPassword();
            user.setPassword(pass);
            User found = updateUser(user);
            sendEmail(found.getEmail(), "Nueva Contraseña", emailBody(pass));
            return found;

        } else {
            throw new UserException("Debe indicar un usuario");
        }
    }

    /**
     * require the user ID and the Mail to validate the user, to create a new
     * password is generated with UUID strategy.
     *
     * Designed to work al login page
     *
     * @param userID
     * @param userMail
     * @return
     * @throws UserException
     */
    public boolean resetPassword(Integer userID, String userMail) throws UserException {
        Optional<User> user = userRepository.getUserByID(userID);

        if (user.isPresent()) {

            if (!userMail.isEmpty() & user.get().getEmail().equals(userMail)) {

                System.out.println("Actual pass: " + user.get().getPassword());
                String pass = newPassword();
                System.out.println("New pass:" + pass);
                user.get().setPassword(pass);
                User found = updateUser(user.get());
                System.out.println("Actual pass: " + found.getPassword());
                sendEmail(found.getEmail(), "Nueva Contraseña", emailBody(pass));
                return true;

            } else {
                throw new UserException("Usuario o Correo Incorrecto");
            }
        } else {
            throw new UserException("No existe el usuario " + userID);
        }
    }

    @Asynchronous
    private Future<Void> sendEmail(final String to, final String subject, final String body) {
        try {
            Message message = new MimeMessage(emailSession);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, CONTENT_TYPE);
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AsyncResult<>(null);
    }

    /**
     * This method return a random password base o UUID, it most be unique
     *
     * @return
     */
    public String newPassword() {
        return UUID.randomUUID().toString();
    }

    private String encryptPass(String pass) {
        char passwordInput[] = pass.toCharArray();
        Map<String, String> map = new HashMap<>();
        map.put("Pbkdf2PasswordHash.Iterations", "3072");
        map.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
        map.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        pbkdf2PasswordHash.initialize(map);
        return pbkdf2PasswordHash.generate(passwordInput);
    }

    private String emailBody(String password) {
        return ("<h2><strong>Se ha requerido un cambio de Contrase&ntilde;a</strong></h2>"
                + "<p>se ha reiniciado tu contrase&ntilde;a del Laboratorio de Dise&ntilde;o de la Produccion de la Division de Ciencias de la Ingenieria. Tu nueva contrase&ntilde;a es:</p>"
                + "<p><span style=\"color: #ff0000;\">" + password + "</span></p>"
                + "<p>Ahora puedes ingresar con tu numero de carnet e ingresar esta nueva contrase&ntilde;a. Se recomienda cambiarla inmediatamente luego de ingresar al portal.</p>"
                + "<p>Divisi&oacute;n de Ciencias de la Ingenieria - Centro Universitario de Occidente</p>");
    }

}
