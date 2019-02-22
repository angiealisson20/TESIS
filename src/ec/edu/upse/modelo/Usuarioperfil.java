package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuarioperfil database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="UsuarioPerfil.findAll", query="SELECT u FROM Usuarioperfil u"),
@NamedQuery(name="UsuarioPerfil.buscaUsuarioRol", query="SELECT u.perfil.idPerfil FROM Usuarioperfil u WHERE u.usuario.usuario = :nombreUsuario"),
@NamedQuery(name="UsuarioPerfil.buscarPorPatron", query="SELECT u FROM Usuarioperfil u WHERE lower(u.usuario.usuario) like lower(:patron)")
})
public class Usuarioperfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usu_perfil")
	private Integer idUsuPerfil;

	private String estado;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Usuarioperfil() {
	}

	public Integer getIdUsuPerfil() {
		return this.idUsuPerfil;
	}

	public void setIdUsuPerfil(Integer idUsuPerfil) {
		this.idUsuPerfil = idUsuPerfil;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}