package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_espaciofisico database table.
 * 
 */
@Entity
@Table(name="tbl_sga_espaciofisico")
@NamedQuery(name="TblSgaEspaciofisico.findAll", query="SELECT t FROM TblSgaEspaciofisico t")
public class TblSgaEspaciofisico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="esf_id_pk")
	private Integer esfIdPk;

	@Column(name="esf_capacidad")
	private String esfCapacidad;

	@Column(name="esf_descripcion")
	private String esfDescripcion;

	@Column(name="esf_foto")
	private String esfFoto;

	private String estado;

	//bi-directional many-to-one association to TblSgaHorario
	@OneToMany(mappedBy="tblSgaEspaciofisico", cascade = CascadeType.ALL)
	private List<TblSgaHorario> tblSgaHorarios;

	public TblSgaEspaciofisico() {
	}

	public Integer getEsfIdPk() {
		return this.esfIdPk;
	}

	public void setEsfIdPk(Integer esfIdPk) {
		this.esfIdPk = esfIdPk;
	}

	public String getEsfCapacidad() {
		return this.esfCapacidad;
	}

	public void setEsfCapacidad(String esfCapacidad) {
		this.esfCapacidad = esfCapacidad;
	}

	public String getEsfDescripcion() {
		return this.esfDescripcion;
	}

	public void setEsfDescripcion(String esfDescripcion) {
		this.esfDescripcion = esfDescripcion;
	}

	public String getEsfFoto() {
		return this.esfFoto;
	}

	public void setEsfFoto(String esfFoto) {
		this.esfFoto = esfFoto;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<TblSgaHorario> getTblSgaHorarios() {
		return this.tblSgaHorarios;
	}

	public void setTblSgaHorarios(List<TblSgaHorario> tblSgaHorarios) {
		this.tblSgaHorarios = tblSgaHorarios;
	}

	public TblSgaHorario addTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().add(tblSgaHorario);
		tblSgaHorario.setTblSgaEspaciofisico(this);

		return tblSgaHorario;
	}

	public TblSgaHorario removeTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().remove(tblSgaHorario);
		tblSgaHorario.setTblSgaEspaciofisico(null);

		return tblSgaHorario;
	}

}