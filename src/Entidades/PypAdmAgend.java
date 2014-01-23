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
@Table(name = "pyp_adm_agend")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PypAdmAgend.findAll", query = "SELECT p FROM PypAdmAgend p"),
    @NamedQuery(name = "PypAdmAgend.findById", query = "SELECT p FROM PypAdmAgend p WHERE p.id = :id"),
    @NamedQuery(name = "PypAdmAgend.findByFecha", query = "SELECT p FROM PypAdmAgend p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PypAdmAgend.findByHora", query = "SELECT p FROM PypAdmAgend p WHERE p.hora = :hora"),
    @NamedQuery(name = "PypAdmAgend.findByEstado", query = "SELECT p FROM PypAdmAgend p WHERE p.estado = :estado")})
public class PypAdmAgend implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgend")
    private List<PypAdmAsistCon> pypAdmAsistConList;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoPaciente idPaciente;
    @JoinColumn(name = "id_programa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PypAdmProgramas idPrograma;

    public PypAdmAgend() {
    }

    public PypAdmAgend(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<PypAdmAsistCon> getPypAdmAsistConList() {
        return pypAdmAsistConList;
    }

    public void setPypAdmAsistConList(List<PypAdmAsistCon> pypAdmAsistConList) {
        this.pypAdmAsistConList = pypAdmAsistConList;
    }

    public InfoPaciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(InfoPaciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public PypAdmProgramas getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(PypAdmProgramas idPrograma) {
        this.idPrograma = idPrograma;
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
        if (!(object instanceof PypAdmAgend)) {
            return false;
        }
        PypAdmAgend other = (PypAdmAgend) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PypAdmAgend[ id=" + id + " ]";
    }
    
}
