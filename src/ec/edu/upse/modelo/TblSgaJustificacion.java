package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_sga_justificacion database table.
 * 
 */
@Entity
@Table(name="tbl_sga_justificacion")
@NamedQuery(name="TblSgaJustificacion.findAll", query="SELECT t FROM TblSgaJustificacion t")
public class TblSgaJustificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="just_id")
	private Integer justId;

	@Column(name="just_docum")
	private String justDocum;

	@Column(name="just_estado")
	private String justEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="just_fecha")
	private Date justFecha;

	@Column(name="just_hora")
	private Time justHora;

	@Column(name="just_observ")
	private String justObserv;

	//bi-directional many-to-one association to TblSgaJustifA
	@OneToMany(mappedBy="tblSgaJustificacion")
	private List<TblSgaJustifA> tblSgaJustifAs;

	public TblSgaJustificacion() {
	}

	public Integer getJustId() {
		return this.justId;
	}

	public void setJustId(Integer justId) {
		this.justId = justId;
	}

	public String getJustDocum() {
		return this.justDocum;
	}

	public void setJustDocum(String justDocum) {
		this.justDocum = justDocum;
	}

	public String getJustEstado() {
		return this.justEstado;
	}

	public void setJustEstado(String justEstado) {
		this.justEstado = justEstado;
	}

	public Date getJustFecha() {
		return this.justFecha;
	}

	public void setJustFecha(Date justFecha) {
		this.justFecha = justFecha;
	}

	public Time getJustHora() {
		return this.justHora;
	}

	public void setJustHora(Time justHora) {
		this.justHora = justHora;
	}

	public String getJustObserv() {
		return this.justObserv;
	}

	public void setJustObserv(String justObserv) {
		this.justObserv = justObserv;
	}

	public List<TblSgaJustifA> getTblSgaJustifAs() {
		return this.tblSgaJustifAs;
	}

	public void setTblSgaJustifAs(List<TblSgaJustifA> tblSgaJustifAs) {
		this.tblSgaJustifAs = tblSgaJustifAs;
	}

	public TblSgaJustifA addTblSgaJustifA(TblSgaJustifA tblSgaJustifA) {
		getTblSgaJustifAs().add(tblSgaJustifA);
		tblSgaJustifA.setTblSgaJustificacion(this);

		return tblSgaJustifA;
	}

	public TblSgaJustifA removeTblSgaJustifA(TblSgaJustifA tblSgaJustifA) {
		getTblSgaJustifAs().remove(tblSgaJustifA);
		tblSgaJustifA.setTblSgaJustificacion(null);

		return tblSgaJustifA;
	}

}