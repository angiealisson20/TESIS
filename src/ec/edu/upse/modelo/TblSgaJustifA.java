package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tbl_sga_justif_as database table.
 * 
 */
@Entity
@Table(name="tbl_sga_justif_as")
@NamedQuery(name="TblSgaJustifA.findAll", query="SELECT t FROM TblSgaJustifA t")
public class TblSgaJustifA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="jas_id")
	private Integer jasId;

	@Column(name="jas_estado")
	private String jasEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="jas_fechajust")
	private Date jasFechajust;

	@Column(name="jas_horajust")
	private Time jasHorajust;

	//bi-directional many-to-one association to TblSgaAsistencia
	@ManyToOne
	@JoinColumn(name="asis_fk")
	private TblSgaAsistencia tblSgaAsistencia;

	//bi-directional many-to-one association to TblSgaJustificacion
	@ManyToOne
	@JoinColumn(name="just_fk")
	private TblSgaJustificacion tblSgaJustificacion;

	public TblSgaJustifA() {
	}

	public Integer getJasId() {
		return this.jasId;
	}

	public void setJasId(Integer jasId) {
		this.jasId = jasId;
	}

	public String getJasEstado() {
		return this.jasEstado;
	}

	public void setJasEstado(String jasEstado) {
		this.jasEstado = jasEstado;
	}

	public Date getJasFechajust() {
		return this.jasFechajust;
	}

	public void setJasFechajust(Date jasFechajust) {
		this.jasFechajust = jasFechajust;
	}

	public Time getJasHorajust() {
		return this.jasHorajust;
	}

	public void setJasHorajust(Time jasHorajust) {
		this.jasHorajust = jasHorajust;
	}

	public TblSgaAsistencia getTblSgaAsistencia() {
		return this.tblSgaAsistencia;
	}

	public void setTblSgaAsistencia(TblSgaAsistencia tblSgaAsistencia) {
		this.tblSgaAsistencia = tblSgaAsistencia;
	}

	public TblSgaJustificacion getTblSgaJustificacion() {
		return this.tblSgaJustificacion;
	}

	public void setTblSgaJustificacion(TblSgaJustificacion tblSgaJustificacion) {
		this.tblSgaJustificacion = tblSgaJustificacion;
	}

}