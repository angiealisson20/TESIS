package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opcionperfil database table.
 * 
 */
@Entity
@NamedQuery(name="Opcionperfil.findAll", query="SELECT o FROM Opcionperfil o")
public class Opcionperfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_opcion_perfil")
	private Integer idOpcionPerfil;

	private String estado;

	//bi-directional many-to-one association to Opcion
	@ManyToOne
	@JoinColumn(name="id_opcion")
	private Opcion opcion;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

	public Opcionperfil() {
	}

	public Integer getIdOpcionPerfil() {
		return this.idOpcionPerfil;
	}

	public void setIdOpcionPerfil(Integer idOpcionPerfil) {
		this.idOpcionPerfil = idOpcionPerfil;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}