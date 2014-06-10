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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "pyp_ciou")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PypCiou.findAll", query = "SELECT p FROM PypCiou p"),
    @NamedQuery(name = "PypCiou.findById", query = "SELECT p FROM PypCiou p WHERE p.id = :id"),
    @NamedQuery(name = "PypCiou.findByCodigo", query = "SELECT p FROM PypCiou p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PypCiou.findByNombre", query = "SELECT p FROM PypCiou p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PypCiou.findByEstado", query = "SELECT p FROM PypCiou p WHERE p.estado = :estado")})
public class PypCiou implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Character estado;

    public PypCiou() {
    }

    public PypCiou(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PypCiou)) {
            return false;
        }
        PypCiou other = (PypCiou) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PypCiou[ id=" + id + " ]";
    }
    
}
