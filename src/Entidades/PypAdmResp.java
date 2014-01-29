/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "pyp_adm_resp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PypAdmResp.findAll", query = "SELECT p FROM PypAdmResp p"),
    @NamedQuery(name = "PypAdmResp.findById", query = "SELECT p FROM PypAdmResp p WHERE p.id = :id"),
    @NamedQuery(name = "PypAdmResp.findByIdentificacion", query = "SELECT p FROM PypAdmResp p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "PypAdmResp.findByNombres", query = "SELECT p FROM PypAdmResp p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "PypAdmResp.findByParentesco", query = "SELECT p FROM PypAdmResp p WHERE p.parentesco = :parentesco"),
    @NamedQuery(name = "PypAdmResp.findByTelefono", query = "SELECT p FROM PypAdmResp p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "PypAdmResp.findByEstado", query = "SELECT p FROM PypAdmResp p WHERE p.estado = :estado")})
public class PypAdmResp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "parentesco")
    private Character parentesco;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_admpyp", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PypAdmAdmision idAdmpyp;

    public PypAdmResp() {
    }

    public PypAdmResp(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Character getParentesco() {
        return parentesco;
    }

    public void setParentesco(Character parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public PypAdmAdmision getIdAdmpyp() {
        return idAdmpyp;
    }

    public void setIdAdmpyp(PypAdmAdmision idAdmpyp) {
        this.idAdmpyp = idAdmpyp;
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
        if (!(object instanceof PypAdmResp)) {
            return false;
        }
        PypAdmResp other = (PypAdmResp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PypAdmResp[ id=" + id + " ]";
    }
    
}
