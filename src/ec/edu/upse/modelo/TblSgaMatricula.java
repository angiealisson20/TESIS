package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_matricula database table.
 * 
 */
@Entity
@Table(name="tbl_sga_matricula")
@NamedQuery(name="TblSgaMatricula.findAll", query="SELECT t FROM TblSgaMatricula t")
public class TblSgaMatricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="matric_id")
	private Integer matricId;

	@Column(name="matric_desc")
	private String matricDesc;

	@Column(name="matric_estado")
	private String matricEstado;

	//bi-directional many-to-one association to TblSgaAsistencia
	@OneToMany(mappedBy="tblSgaMatricula")
	private List<TblSgaAsistencia> tblSgaAsistencias;

	//bi-directional many-to-one association to TblSgaEstudiante
	@ManyToOne
	@JoinColumn(name="est_fk")
	private TblSgaEstudiante tblSgaEstudiante;

	//bi-directional many-to-one association to TblSgaCursoparalelo
	@ManyToOne
	@JoinColumn(name="curparal_fk")
	private TblSgaCursoparalelo tblSgaCursoparalelo;

	public TblSgaMatricula() {
	}

	public Integer getMatricId() {
		return this.matricId;
	}

	public void setMatricId(Integer matricId) {
		this.matricId = matricId;
	}

	public String getMatricDesc() {
		return this.matricDesc;
	}

	public void setMatricDesc(String matricDesc) {
		this.matricDesc = matricDesc;
	}

	public String getMatricEstado() {
		return this.matricEstado;
	}

	public void setMatricEstado(String matricEstado) {
		this.matricEstado = matricEstado;
	}

	public List<TblSgaAsistencia> getTblSgaAsistencias() {
		return this.tblSgaAsistencias;
	}

	public void setTblSgaAsistencias(List<TblSgaAsistencia> tblSgaAsistencias) {
		this.tblSgaAsistencias = tblSgaAsistencias;
	}

	public TblSgaAsistencia addTblSgaAsistencia(TblSgaAsistencia tblSgaAsistencia) {
		getTblSgaAsistencias().add(tblSgaAsistencia);
		tblSgaAsistencia.setTblSgaMatricula(this);

		return tblSgaAsistencia;
	}

	public TblSgaAsistencia removeTblSgaAsistencia(TblSgaAsistencia tblSgaAsistencia) {
		getTblSgaAsistencias().remove(tblSgaAsistencia);
		tblSgaAsistencia.setTblSgaMatricula(null);

		return tblSgaAsistencia;
	}

	public TblSgaEstudiante getTblSgaEstudiante() {
		return this.tblSgaEstudiante;
	}

	public void setTblSgaEstudiante(TblSgaEstudiante tblSgaEstudiante) {
		this.tblSgaEstudiante = tblSgaEstudiante;
	}

	public TblSgaCursoparalelo getTblSgaCursoparalelo() {
		return this.tblSgaCursoparalelo;
	}

	public void setTblSgaCursoparalelo(TblSgaCursoparalelo tblSgaCursoparalelo) {
		this.tblSgaCursoparalelo = tblSgaCursoparalelo;
	}

}