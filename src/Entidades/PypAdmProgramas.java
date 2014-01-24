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
@Table(name = "pyp_adm_programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PypAdmProgramas.findAll", query = "SELECT p FROM PypAdmProgramas p"),
    @NamedQuery(name = "PypAdmProgramas.findById", query = "SELECT p FROM PypAdmProgramas p WHERE p.id = :id"),
    @NamedQuery(name = "PypAdmProgramas.findByNombre", query = "SELECT p FROM PypAdmProgramas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PypAdmProgramas.findByEstado", query = "SELECT p FROM PypAdmProgramas p WHERE p.estado = :estado")})
public class PypAdmProgramas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrograma")
    private List<PypAdmAgend> pypAdmAgendList;

    public PypAdmProgramas() {
    }

    public PypAdmProgramas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<PypAdmAgend> getPypAdmAgendList() {
        return pypAdmAgendList;
    }

    public void setPypAdmAgendList(List<PypAdmAgend> pypAdmAgendList) {
        this.pypAdmAgendList = pypAdmAgendList;
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
        if (!(object instanceof PypAdmProgramas)) {
            return false;
        }
        PypAdmProgramas other = (PypAdmProgramas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PypAdmProgramas[ id=" + id + " ]";
    }
    
}
