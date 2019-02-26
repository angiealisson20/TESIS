package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_sga_horario database table.
 * 
 */
@Entity
@Table(name="tbl_sga_horario")
@NamedQueries({
	@NamedQuery(name="TblSgaHorario.findAll", query="SELECT t FROM TblSgaHorario t"),
	@NamedQuery(name="TblSgaHorario.BuscaPorConsulta", query="SELECT t FROM TblSgaHorario t "
			+ "where t.tblSgaCursoparalelo.tblSgaPeriodoncurso.tblSgaPeriodonivel.tblSgaPeriodolectivo.perId = :idPeriodo and "
			+ "t.tblSgaCursoparalelo.tblSgaPeriodoncurso.tblSgaPeriodonivel.tblSgaNivel.nivelId = :idNivel and "
			+ "t.tblSgaCursoparalelo.tblSgaPeriodoncurso.tblSgaCurso.curId = :idCurso and t.tblSgaCursoparalelo.tblSgaParalelo.paralId = :idParalelo")
})

public class TblSgaHorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hor_id_pk")
	private Integer horIdPk;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fechaingreso;

	@Column(name="hor_observacion")
	private String horObservacion;

	private String usuario;

	//bi-directional many-to-one association to TblSgaAsistencia
	@OneToMany(mappedBy="tblSgaHorario")
	private List<TblSgaAsistencia> tblSgaAsistencias;

	//bi-directional many-to-one association to TblSgaDia
	@ManyToOne
	@JoinColumn(name="hor_dia_id_fk")
	private TblSgaDia tblSgaDia;

	//bi-directional many-to-one association to TblSgaDistributivo
	@ManyToOne
	@JoinColumn(name="hor_distributivo_fk")
	private TblSgaDistributivo tblSgaDistributivo;

	//bi-directional many-to-one association to TblSgaEspaciofisico
	@ManyToOne
	@JoinColumn(name="hor_esf_id_fk")
	private TblSgaEspaciofisico tblSgaEspaciofisico;

	//bi-directional many-to-one association to TblSgaHora
	@ManyToOne
	@JoinColumn(name="hor_hrs_id_fk")
	private TblSgaHora tblSgaHora;

	//bi-directional many-to-one association to TblSgaCursoparalelo
	@ManyToOne
	@JoinColumn(name="hor_curparal_fk")
	private TblSgaCursoparalelo tblSgaCursoparalelo;

	public TblSgaHorario() {
	}

	public Integer getHorIdPk() {
		return this.horIdPk;
	}

	public void setHorIdPk(Integer horIdPk) {
		this.horIdPk = horIdPk;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public String getHorObservacion() {
		return this.horObservacion;
	}

	public void setHorObservacion(String horObservacion) {
		this.horObservacion = horObservacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<TblSgaAsistencia> getTblSgaAsistencias() {
		return this.tblSgaAsistencias;
	}

	public void setTblSgaAsistencias(List<TblSgaAsistencia> tblSgaAsistencias) {
		this.tblSgaAsistencias = tblSgaAsistencias;
	}

	public TblSgaAsistencia addTblSgaAsistencia(TblSgaAsistencia tblSgaAsistencia) {
		getTblSgaAsistencias().add(tblSgaAsistencia);
		tblSgaAsistencia.setTblSgaHorario(this);

		return tblSgaAsistencia;
	}

	public TblSgaAsistencia removeTblSgaAsistencia(TblSgaAsistencia tblSgaAsistencia) {
		getTblSgaAsistencias().remove(tblSgaAsistencia);
		tblSgaAsistencia.setTblSgaHorario(null);

		return tblSgaAsistencia;
	}

	public TblSgaDia getTblSgaDia() {
		return this.tblSgaDia;
	}

	public void setTblSgaDia(TblSgaDia tblSgaDia) {
		this.tblSgaDia = tblSgaDia;
	}

	public TblSgaDistributivo getTblSgaDistributivo() {
		return this.tblSgaDistributivo;
	}

	public void setTblSgaDistributivo(TblSgaDistributivo tblSgaDistributivo) {
		this.tblSgaDistributivo = tblSgaDistributivo;
	}

	public TblSgaEspaciofisico getTblSgaEspaciofisico() {
		return this.tblSgaEspaciofisico;
	}

	public void setTblSgaEspaciofisico(TblSgaEspaciofisico tblSgaEspaciofisico) {
		this.tblSgaEspaciofisico = tblSgaEspaciofisico;
	}

	public TblSgaHora getTblSgaHora() {
		return this.tblSgaHora;
	}

	public void setTblSgaHora(TblSgaHora tblSgaHora) {
		this.tblSgaHora = tblSgaHora;
	}

	public TblSgaCursoparalelo getTblSgaCursoparalelo() {
		return this.tblSgaCursoparalelo;
	}

	public void setTblSgaCursoparalelo(TblSgaCursoparalelo tblSgaCursoparalelo) {
		this.tblSgaCursoparalelo = tblSgaCursoparalelo;
	}

}