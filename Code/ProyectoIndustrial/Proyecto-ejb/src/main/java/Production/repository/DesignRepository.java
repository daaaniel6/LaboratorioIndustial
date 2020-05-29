/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Production.repository;

import Design.Design;
import Design.DesignData;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author daniel
 */
@Stateless
@LocalBean
public class DesignRepository {
    public static final String QUERY_ALL_DESIGNS = "SELECT d FROM Design d";
    private EntityManager entityManager;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *
     *
     * @return Lista con todos los diseños en la base de datos
     */
    public List<Design> AllDesigns() {

        TypedQuery<Design> typedQuery = entityManager.createQuery(QUERY_ALL_DESIGNS, Design.class);
        return typedQuery.getResultList();

    }


    /**
     * Realiza una busqueda basado unicamente en el codigo del diseño
     *
     * @param idDesign
     * @return
     */
    public Optional<Design> findDesignByID(Integer idDesign) {
        return Optional.ofNullable(entityManager.find(Design.class, idDesign));
    }

    /**
     * Busqueda por id y nombre asociado al diseño
     *
     * @param id
     * @param name
     * @return
     */
    public List<Design> getDesign(Integer id, String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Design> criteriaQuery = criteriaBuilder.createQuery(Design.class);
        Root<Design> design = criteriaQuery.from(Design.class);
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            System.out.println("Name: " + name);
            predicates.add(criteriaBuilder.like(design.get("designData").get("name"), "%" + name + "%"));
        }

        if (id != null) {
            System.out.println("ID: " + id);
            predicates.add(criteriaBuilder.equal(design.get("idDesign"), id));
        }

        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<Design> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
