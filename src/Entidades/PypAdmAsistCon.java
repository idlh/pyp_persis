/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "pyp_adm_asist_con")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PypAdmAsistCon.findAll", query = "SELECT p FROM PypAdmAsistCon p"),
    @NamedQuery(name = "PypAdmAsistCon.findById", query = "SELECT p FROM PypAdmAsistCon p WHERE p.id = :id"),
    @NamedQuery(name = "PypAdmAsistCon.findByPrimeraVez", query = "SELECT p FROM PypAdmAsistCon p WHERE p.primeraVez = :primeraVez"),
    @NamedQuery(name = "PypAdmAsistCon.findByFecha", query = "SELECT p FROM PypAdmAsistCon p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PypAdmAsistCon.findByHora", query = "SELECT p FROM PypAdmAsistCon p WHERE p.hora = :hora"),
    @NamedQuery(name = "PypAdmAsistCon.findByEstado", query = "SELECT p FROM PypAdmAsistCon p WHERE p.estado = :estado")})
public class PypAdmAsistCon implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsistcon")
    private List<PypAdmAdmision> pypAdmAdmisionList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "primera_vez")
    private Character primeraVez;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_control_pro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PypAdmControlProfesionales idControlPro;
    @JoinColumn(name = "id_agend", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PypAdmAgend idAgend;

    public PypAdmAsistCon() {
    }

    public PypAdmAsistCon(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(Character primeraVez) {
        this.primeraVez = primeraVez;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public PypAdmControlProfesionales getIdControlPro() {
        return idControlPro;
    }

    public void setIdControlPro(PypAdmControlProfesionales idControlPro) {
        this.idControlPro = idControlPro;
    }

    public PypAdmAgend getIdAgend() {
        return idAgend;
    }

    public void setIdAgend(PypAdmAgend idAgend) {
        this.idAgend = idAgend;
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
        if (!(object instanceof PypAdmAsistCon)) {
            return false;
        }
        PypAdmAsistCon other = (PypAdmAsistCon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PypAdmAsistCon[ id=" + id + " ]";
    }

    @XmlTransient
    public List<PypAdmAdmision> getPypAdmAdmisionList() {
        return pypAdmAdmisionList;
    }

    public void setPypAdmAdmisionList(List<PypAdmAdmision> pypAdmAdmisionList) {
        this.pypAdmAdmisionList = pypAdmAdmisionList;
    }
    
}
