package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_dia database table.
 * 
 */
@Entity
@Table(name="tbl_sga_dia")
@NamedQuery(name="TblSgaDia.findAll", query="SELECT t FROM TblSgaDia t")
public class TblSgaDia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dia_id_pk")
	private Integer diaIdPk;

	@Column(name="dia_descripcion")
	private String diaDescripcion;

	private String estado;

	//bi-directional many-to-one association to TblSgaHorario
	@OneToMany(mappedBy="tblSgaDia")
	private List<TblSgaHorario> tblSgaHorarios;

	public TblSgaDia() {
	}

	public Integer getDiaIdPk() {
		return this.diaIdPk;
	}

	public void setDiaIdPk(Integer diaIdPk) {
		this.diaIdPk = diaIdPk;
	}

	public String getDiaDescripcion() {
		return this.diaDescripcion;
	}

	public void setDiaDescripcion(String diaDescripcion) {
		this.diaDescripcion = diaDescripcion;
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
		tblSgaHorario.setTblSgaDia(this);

		return tblSgaHorario;
	}

	public TblSgaHorario removeTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().remove(tblSgaHorario);
		tblSgaHorario.setTblSgaDia(null);

		return tblSgaHorario;
	}

}