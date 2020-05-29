package Production;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commentary")
public class Commentary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_commentary")
    private Integer idCommentary;
    @Basic(optional = false)
    @Column(name = "commentary")
    private String commentary;
    @JoinColumn(name = "id_step", referencedColumnName = "id_step")
    @ManyToOne(optional = false)
    private Step idStep;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commentary)) {
            return false;
        }
        Commentary commentary = (Commentary) o;
        return Objects.equals(getIdCommentary(), commentary.getIdCommentary());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCommentary());

    }

    public Commentary() {
    }

    public Commentary(Integer idCommentary) {
        this.idCommentary = idCommentary;
    }

    public Commentary(Integer idCommentary, String commentary) {
        this.idCommentary = idCommentary;
        this.commentary = commentary;
    }

    public Integer getIdCommentary() {
        return idCommentary;
    }

    public void setIdCommentary(Integer idCommentary) {
        this.idCommentary = idCommentary;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Step getIdStep() {
        return idStep;
    }

    public void setIdStep(Step idStep) {
        this.idStep = idStep;
    }
}
