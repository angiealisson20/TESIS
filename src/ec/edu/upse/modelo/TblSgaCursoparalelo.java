package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_cursoparalelo database table.
 * 
 */
@Entity
@Table(name="tbl_sga_cursoparalelo")
@NamedQuery(name="TblSgaCursoparalelo.findAll", query="SELECT t FROM TblSgaCursoparalelo t")
public class TblSgaCursoparalelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="curparal_id")
	private Integer curparalId;

	@Column(name="curparal_estado")
	private String curparalEstado;

	//bi-directional many-to-one association to TblSgaParalelo
	@ManyToOne
	@JoinColumn(name="paralelo_fk")
	private TblSgaParalelo tblSgaParalelo;

	//bi-directional many-to-one association to TblSgaPeriodoncurso
	@ManyToOne
	@JoinColumn(name="pernivcur_fk")
	private TblSgaPeriodoncurso tblSgaPeriodoncurso;

	//bi-directional many-to-one association to TblSgaHorario
	@OneToMany(mappedBy="tblSgaCursoparalelo")
	private List<TblSgaHorario> tblSgaHorarios;

	//bi-directional many-to-one association to TblSgaMatricula
	@OneToMany(mappedBy="tblSgaCursoparalelo")
	private List<TblSgaMatricula> tblSgaMatriculas;

	public TblSgaCursoparalelo() {
	}

	public Integer getCurparalId() {
		return this.curparalId;
	}

	public void setCurparalId(Integer curparalId) {
		this.curparalId = curparalId;
	}

	public String getCurparalEstado() {
		return this.curparalEstado;
	}

	public void setCurparalEstado(String curparalEstado) {
		this.curparalEstado = curparalEstado;
	}

	public TblSgaParalelo getTblSgaParalelo() {
		return this.tblSgaParalelo;
	}

	public void setTblSgaParalelo(TblSgaParalelo tblSgaParalelo) {
		this.tblSgaParalelo = tblSgaParalelo;
	}

	public TblSgaPeriodoncurso getTblSgaPeriodoncurso() {
		return this.tblSgaPeriodoncurso;
	}

	public void setTblSgaPeriodoncurso(TblSgaPeriodoncurso tblSgaPeriodoncurso) {
		this.tblSgaPeriodoncurso = tblSgaPeriodoncurso;
	}

	public List<TblSgaHorario> getTblSgaHorarios() {
		return this.tblSgaHorarios;
	}

	public void setTblSgaHorarios(List<TblSgaHorario> tblSgaHorarios) {
		this.tblSgaHorarios = tblSgaHorarios;
	}

	public TblSgaHorario addTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().add(tblSgaHorario);
		tblSgaHorario.setTblSgaCursoparalelo(this);

		return tblSgaHorario;
	}

	public TblSgaHorario removeTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().remove(tblSgaHorario);
		tblSgaHorario.setTblSgaCursoparalelo(null);

		return tblSgaHorario;
	}

	public List<TblSgaMatricula> getTblSgaMatriculas() {
		return this.tblSgaMatriculas;
	}

	public void setTblSgaMatriculas(List<TblSgaMatricula> tblSgaMatriculas) {
		this.tblSgaMatriculas = tblSgaMatriculas;
	}

	public TblSgaMatricula addTblSgaMatricula(TblSgaMatricula tblSgaMatricula) {
		getTblSgaMatriculas().add(tblSgaMatricula);
		tblSgaMatricula.setTblSgaCursoparalelo(this);

		return tblSgaMatricula;
	}

	public TblSgaMatricula removeTblSgaMatricula(TblSgaMatricula tblSgaMatricula) {
		getTblSgaMatriculas().remove(tblSgaMatricula);
		tblSgaMatricula.setTblSgaCursoparalelo(null);

		return tblSgaMatricula;
	}

}