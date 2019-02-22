package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the opcion database table.
 * 
 */
@Entity
@NamedQuery(name="Opcion.findAll", query="SELECT o FROM Opcion o")
public class Opcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_opcion")
	private Integer idOpcion;

	private String estado;

	@Column(name="id_opcion_padre")
	private Integer idOpcionPadre;

	private String imagen;

	private String titulo;

	private String url;

	//bi-directional many-to-one association to Opcionperfil
	@OneToMany(mappedBy="opcion")
	private List<Opcionperfil> opcionperfils;

	public Opcion() {
	}

	public Integer getIdOpcion() {
		return this.idOpcion;
	}

	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIdOpcionPadre() {
		return this.idOpcionPadre;
	}

	public void setIdOpcionPadre(Integer idOpcionPadre) {
		this.idOpcionPadre = idOpcionPadre;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Opcionperfil> getOpcionperfils() {
		return this.opcionperfils;
	}

	public void setOpcionperfils(List<Opcionperfil> opcionperfils) {
		this.opcionperfils = opcionperfils;
	}

	public Opcionperfil addOpcionperfil(Opcionperfil opcionperfil) {
		getOpcionperfils().add(opcionperfil);
		opcionperfil.setOpcion(this);

		return opcionperfil;
	}

	public Opcionperfil removeOpcionperfil(Opcionperfil opcionperfil) {
		getOpcionperfils().remove(opcionperfil);
		opcionperfil.setOpcion(null);

		return opcionperfil;
	}

}