/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "pyp_adm_admision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PypAdmAdmision.findAll", query = "SELECT p FROM PypAdmAdmision p"),
    @NamedQuery(name = "PypAdmAdmision.findById", query = "SELECT p FROM PypAdmAdmision p WHERE p.id = :id"),
    @NamedQuery(name = "PypAdmAdmision.findByEdadAdmision", query = "SELECT p FROM PypAdmAdmision p WHERE p.edadAdmision = :edadAdmision"),
    @NamedQuery(name = "PypAdmAdmision.findByCausaExterna", query = "SELECT p FROM PypAdmAdmision p WHERE p.causaExterna = :causaExterna"),
    @NamedQuery(name = "PypAdmAdmision.findByEstadoIngreso", query = "SELECT p FROM PypAdmAdmision p WHERE p.estadoIngreso = :estadoIngreso"),
    @NamedQuery(name = "PypAdmAdmision.findByMedioIngreso", query = "SELECT p FROM PypAdmAdmision p WHERE p.medioIngreso = :medioIngreso"),
    @NamedQuery(name = "PypAdmAdmision.findByEstado", query = "SELECT p FROM PypAdmAdmision p WHERE p.estado = :estado")})
public class PypAdmAdmision implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdmpyp")
    private List<PypAdmResp> pypAdmRespList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "edad_admision")
    private String edadAdmision;
    @Basic(optional = false)
    @Column(name = "causa_externa")
    private String causaExterna;
    @Basic(optional = false)
    @Column(name = "estado_ingreso")
    private char estadoIngreso;
    @Basic(optional = false)
    @Column(name = "medio_ingreso")
    private char medioIngreso;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private char estado;
    @JoinColumn(name = "id_Asistcon", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PypAdmAsistCon idAsistcon;

    public PypAdmAdmision() {
    }

    public PypAdmAdmision(Integer id) {
        this.id = id;
    }

    public PypAdmAdmision(Integer id, String edadAdmision, String causaExterna, char estadoIngreso, char medioIngreso, char estado) {
        this.id = id;
        this.edadAdmision = edadAdmision;
        this.causaExterna = causaExterna;
        this.estadoIngreso = estadoIngreso;
        this.medioIngreso = medioIngreso;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEdadAdmision() {
        return edadAdmision;
    }

    public void setEdadAdmision(String edadAdmision) {
        this.edadAdmision = edadAdmision;
    }

    public String getCausaExterna() {
        return causaExterna;
    }

    public void setCausaExterna(String causaExterna) {
        this.causaExterna = causaExterna;
    }

    public char getEstadoIngreso() {
        return estadoIngreso;
    }

    public void setEstadoIngreso(char estadoIngreso) {
        this.estadoIngreso = estadoIngreso;
    }

    public char getMedioIngreso() {
        return medioIngreso;
    }

    public void setMedioIngreso(char medioIngreso) {
        this.medioIngreso = medioIngreso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public PypAdmAsistCon getIdAsistcon() {
        return idAsistcon;
    }

    public void setIdAsistcon(PypAdmAsistCon idAsistcon) {
        this.idAsistcon = idAsistcon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PypAdmAdmision)) {
            return false;
        }
        PypAdmAdmision other = (PypAdmAdmision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PypAdmAdmision[ id=" + id + " ]";
    }

    @XmlTransient
    public List<PypAdmResp> getPypAdmRespList() {
        return pypAdmRespList;
    }

    public void setPypAdmRespList(List<PypAdmResp> pypAdmRespList) {
        this.pypAdmRespList = pypAdmRespList;
    }
    
}
