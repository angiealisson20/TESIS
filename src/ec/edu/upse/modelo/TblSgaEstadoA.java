package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_estado_a database table.
 * 
 */
@Entity
@Table(name="tbl_sga_estado_a")
@NamedQuery(name="TblSgaEstadoA.findAll", query="SELECT t FROM TblSgaEstadoA t")
public class TblSgaEstadoA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="estado_id")
	private Integer estadoId;

	@Column(name="estado_desc")
	private String estadoDesc;

	@Column(name="estado_estado")
	private String estadoEstado;

	@Column(name="just_fk")
	private Integer justFk;

	//bi-directional many-to-one association to TblSgaAsistencia
	@OneToMany(mappedBy="tblSgaEstadoA")
	private List<TblSgaAsistencia> tblSgaAsistencias;

	public TblSgaEstadoA() {
	}

	public Integer getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstadoDesc() {
		return this.estadoDesc;
	}

	public void setEstadoDesc(String estadoDesc) {
		this.estadoDesc = estadoDesc;
	}

	public String getEstadoEstado() {
		return this.estadoEstado;
	}

	public void setEstadoEstado(String estadoEstado) {
		this.estadoEstado = estadoEstado;
	}

	public Integer getJustFk() {
		return this.justFk;
	}

	public void setJustFk(Integer justFk) {
		this.justFk = justFk;
	}

	public List<TblSgaAsistencia> getTblSgaAsistencias() {
		return this.tblSgaAsistencias;
	}

	public void setTblSgaAsistencias(List<TblSgaAsistencia> tblSgaAsistencias) {
		this.tblSgaAsistencias = tblSgaAsistencias;
	}

	public TblSgaAsistencia addTblSgaAsistencia(TblSgaAsistencia tblSgaAsistencia) {
		getTblSgaAsistencias().add(tblSgaAsistencia);
		tblSgaAsistencia.setTblSgaEstadoA(this);

		return tblSgaAsistencia;
	}

	public TblSgaAsistencia removeTblSgaAsistencia(TblSgaAsistencia tblSgaAsistencia) {
		getTblSgaAsistencias().remove(tblSgaAsistencia);
		tblSgaAsistencia.setTblSgaEstadoA(null);

		return tblSgaAsistencia;
	}

}