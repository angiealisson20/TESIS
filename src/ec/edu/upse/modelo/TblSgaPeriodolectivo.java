package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_periodolectivo database table.
 * 
 */
@Entity
@Table(name="tbl_sga_periodolectivo")
@NamedQuery(name="TblSgaPeriodolectivo.findAll", query="SELECT t FROM TblSgaPeriodolectivo t")
public class TblSgaPeriodolectivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="per_id")
	private Integer perId;

	@Column(name="per_desc")
	private String perDesc;

	@Column(name="per_estado")
	private String perEstado;

	@Column(name="per_fin")
	private String perFin;

	@Column(name="per_inicio")
	private String perInicio;

	//bi-directional many-to-one association to TblSgaPeriodonivel
	@OneToMany(mappedBy="tblSgaPeriodolectivo")
	private List<TblSgaPeriodonivel> tblSgaPeriodonivels;

	public TblSgaPeriodolectivo() {
	}

	public Integer getPerId() {
		return this.perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public String getPerDesc() {
		return this.perDesc;
	}

	public void setPerDesc(String perDesc) {
		this.perDesc = perDesc;
	}

	public String getPerEstado() {
		return this.perEstado;
	}

	public void setPerEstado(String perEstado) {
		this.perEstado = perEstado;
	}

	public String getPerFin() {
		return this.perFin;
	}

	public void setPerFin(String perFin) {
		this.perFin = perFin;
	}

	public String getPerInicio() {
		return this.perInicio;
	}

	public void setPerInicio(String perInicio) {
		this.perInicio = perInicio;
	}

	public List<TblSgaPeriodonivel> getTblSgaPeriodonivels() {
		return this.tblSgaPeriodonivels;
	}

	public void setTblSgaPeriodonivels(List<TblSgaPeriodonivel> tblSgaPeriodonivels) {
		this.tblSgaPeriodonivels = tblSgaPeriodonivels;
	}

	public TblSgaPeriodonivel addTblSgaPeriodonivel(TblSgaPeriodonivel tblSgaPeriodonivel) {
		getTblSgaPeriodonivels().add(tblSgaPeriodonivel);
		tblSgaPeriodonivel.setTblSgaPeriodolectivo(this);

		return tblSgaPeriodonivel;
	}

	public TblSgaPeriodonivel removeTblSgaPeriodonivel(TblSgaPeriodonivel tblSgaPeriodonivel) {
		getTblSgaPeriodonivels().remove(tblSgaPeriodonivel);
		tblSgaPeriodonivel.setTblSgaPeriodolectivo(null);

		return tblSgaPeriodonivel;
	}

}