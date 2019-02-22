package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_sga_asistencia database table.
 * 
 */
@Entity
@Table(name="tbl_sga_asistencia")
@NamedQuery(name="TblSgaAsistencia.findAll", query="SELECT t FROM TblSgaAsistencia t")
public class TblSgaAsistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="asis_id")
	private Integer asisId;

	@Column(name="asis_estado")
	private String asisEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="asis_fecha")
	private Date asisFecha;

	@Column(name="asis_hora")
	private Time asisHora;

	@Column(name="asis_observ")
	private String asisObserv;

	@Column(name="asis_usuario")
	private String asisUsuario;

	//bi-directional many-to-one association to TblSgaEstadoA
	@ManyToOne
	@JoinColumn(name="estado_fk")
	private TblSgaEstadoA tblSgaEstadoA;

	//bi-directional many-to-one association to TblSgaHorario
	@ManyToOne
	@JoinColumn(name="horario_fk")
	private TblSgaHorario tblSgaHorario;

	//bi-directional many-to-one association to TblSgaMatricula
	@ManyToOne
	@JoinColumn(name="matric_fk")
	private TblSgaMatricula tblSgaMatricula;

	//bi-directional many-to-one association to TblSgaJustifA
	@OneToMany(mappedBy="tblSgaAsistencia")
	private List<TblSgaJustifA> tblSgaJustifAs;

	public TblSgaAsistencia() {
	}

	public Integer getAsisId() {
		return this.asisId;
	}

	public void setAsisId(Integer asisId) {
		this.asisId = asisId;
	}

	public String getAsisEstado() {
		return this.asisEstado;
	}

	public void setAsisEstado(String asisEstado) {
		this.asisEstado = asisEstado;
	}

	public Date getAsisFecha() {
		return this.asisFecha;
	}

	public void setAsisFecha(Date asisFecha) {
		this.asisFecha = asisFecha;
	}

	public Time getAsisHora() {
		return this.asisHora;
	}

	public void setAsisHora(Time asisHora) {
		this.asisHora = asisHora;
	}

	public String getAsisObserv() {
		return this.asisObserv;
	}

	public void setAsisObserv(String asisObserv) {
		this.asisObserv = asisObserv;
	}

	public String getAsisUsuario() {
		return this.asisUsuario;
	}

	public void setAsisUsuario(String asisUsuario) {
		this.asisUsuario = asisUsuario;
	}

	public TblSgaEstadoA getTblSgaEstadoA() {
		return this.tblSgaEstadoA;
	}

	public void setTblSgaEstadoA(TblSgaEstadoA tblSgaEstadoA) {
		this.tblSgaEstadoA = tblSgaEstadoA;
	}

	public TblSgaHorario getTblSgaHorario() {
		return this.tblSgaHorario;
	}

	public void setTblSgaHorario(TblSgaHorario tblSgaHorario) {
		this.tblSgaHorario = tblSgaHorario;
	}

	public TblSgaMatricula getTblSgaMatricula() {
		return this.tblSgaMatricula;
	}

	public void setTblSgaMatricula(TblSgaMatricula tblSgaMatricula) {
		this.tblSgaMatricula = tblSgaMatricula;
	}

	public List<TblSgaJustifA> getTblSgaJustifAs() {
		return this.tblSgaJustifAs;
	}

	public void setTblSgaJustifAs(List<TblSgaJustifA> tblSgaJustifAs) {
		this.tblSgaJustifAs = tblSgaJustifAs;
	}

	public TblSgaJustifA addTblSgaJustifA(TblSgaJustifA tblSgaJustifA) {
		getTblSgaJustifAs().add(tblSgaJustifA);
		tblSgaJustifA.setTblSgaAsistencia(this);

		return tblSgaJustifA;
	}

	public TblSgaJustifA removeTblSgaJustifA(TblSgaJustifA tblSgaJustifA) {
		getTblSgaJustifAs().remove(tblSgaJustifA);
		tblSgaJustifA.setTblSgaAsistencia(null);

		return tblSgaJustifA;
	}

}