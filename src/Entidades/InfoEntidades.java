/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "info_entidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfoEntidades.findAll", query = "SELECT i FROM InfoEntidades i"),
    @NamedQuery(name = "InfoEntidades.findById", query = "SELECT i FROM InfoEntidades i WHERE i.id = :id"),
    @NamedQuery(name = "InfoEntidades.findByCodigoEntidad", query = "SELECT i FROM InfoEntidades i WHERE i.codigoEntidad = :codigoEntidad"),
    @NamedQuery(name = "InfoEntidades.findByTipo", query = "SELECT i FROM InfoEntidades i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "InfoEntidades.findByNombreEntidad", query = "SELECT i FROM InfoEntidades i WHERE i.nombreEntidad = :nombreEntidad"),
    @NamedQuery(name = "InfoEntidades.findByNombreRepresentante", query = "SELECT i FROM InfoEntidades i WHERE i.nombreRepresentante = :nombreRepresentante"),
    @NamedQuery(name = "InfoEntidades.findByIdPais", query = "SELECT i FROM InfoEntidades i WHERE i.idPais = :idPais"),
    @NamedQuery(name = "InfoEntidades.findByIdDep", query = "SELECT i FROM InfoEntidades i WHERE i.idDep = :idDep"),
    @NamedQuery(name = "InfoEntidades.findByIdMun", query = "SELECT i FROM InfoEntidades i WHERE i.idMun = :idMun"),
    @NamedQuery(name = "InfoEntidades.findByDireccion", query = "SELECT i FROM InfoEntidades i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "InfoEntidades.findByTelefonoContacto1", query = "SELECT i FROM InfoEntidades i WHERE i.telefonoContacto1 = :telefonoContacto1"),
    @NamedQuery(name = "InfoEntidades.findByTelefonoContacto2", query = "SELECT i FROM InfoEntidades i WHERE i.telefonoContacto2 = :telefonoContacto2"),
    @NamedQuery(name = "InfoEntidades.findBySitioWeb", query = "SELECT i FROM InfoEntidades i WHERE i.sitioWeb = :sitioWeb"),
    @NamedQuery(name = "InfoEntidades.findByEmail", query = "SELECT i FROM InfoEntidades i WHERE i.email = :email")})
public class InfoEntidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "codigo_entidad")
    private int codigoEntidad;
    @Column(name = "tipo")
    private Integer tipo;
    @Basic(optional = false)
    @Column(name = "nombre_entidad")
    private String nombreEntidad;
    @Column(name = "nombre_representante")
    private String nombreRepresentante;
    @Column(name = "id_pais")
    private String idPais;
    @Column(name = "id_dep")
    private String idDep;
    @Column(name = "id_mun")
    private String idMun;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono_contacto1")
    private String telefonoContacto1;
    @Column(name = "telefono_contacto2")
    private String telefonoContacto2;
    @Column(name = "sitioWeb")
    private String sitioWeb;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "contratante")
    private List<InfoPaciente> infoPacienteList;

    public InfoEntidades() {
    }

    public InfoEntidades(Integer id) {
        this.id = id;
    }

    public InfoEntidades(Integer id, int codigoEntidad, String nombreEntidad) {
        this.id = id;
        this.codigoEntidad = codigoEntidad;
        this.nombreEntidad = nombreEntidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(int codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getIdDep() {
        return idDep;
    }

    public void setIdDep(String idDep) {
        this.idDep = idDep;
    }

    public String getIdMun() {
        return idMun;
    }

    public void setIdMun(String idMun) {
        this.idMun = idMun;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoContacto1() {
        return telefonoContacto1;
    }

    public void setTelefonoContacto1(String telefonoContacto1) {
        this.telefonoContacto1 = telefonoContacto1;
    }

    public String getTelefonoContacto2() {
        return telefonoContacto2;
    }

    public void setTelefonoContacto2(String telefonoContacto2) {
        this.telefonoContacto2 = telefonoContacto2;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<InfoPaciente> getInfoPacienteList() {
        return infoPacienteList;
    }

    public void setInfoPacienteList(List<InfoPaciente> infoPacienteList) {
        this.infoPacienteList = infoPacienteList;
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
        if (!(object instanceof InfoEntidades)) {
            return false;
        }
        InfoEntidades other = (InfoEntidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.InfoEntidades[ id=" + id + " ]";
    }
    
}
