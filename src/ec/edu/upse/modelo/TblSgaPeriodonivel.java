package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_periodonivel database table.
 * 
 */
@Entity
@Table(name="tbl_sga_periodonivel")
@NamedQueries({
	@NamedQuery(name="TblSgaPeriodonivel.findAll", query="SELECT t FROM TblSgaPeriodonivel t"),
	@NamedQuery(name="TblSgaPeriodonivel.buscarPorPeriodo", query="SELECT t FROM TblSgaPeriodonivel t where t.tblSgaPeriodolectivo.perId = :idPeriodo"),
})
public class TblSgaPeriodonivel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="perniv_id")
	private Integer pernivId;

	@Column(name="perniv_estado")
	private String pernivEstado;

	//bi-directional many-to-one association to TblSgaPeriodoncurso
	@OneToMany(mappedBy="tblSgaPeriodonivel")
	private List<TblSgaPeriodoncurso> tblSgaPeriodoncursos;

	//bi-directional many-to-one association to TblSgaNivel
	@ManyToOne
	@JoinColumn(name="nivel_fk")
	private TblSgaNivel tblSgaNivel;

	//bi-directional many-to-one association to TblSgaPeriodolectivo
	@ManyToOne
	@JoinColumn(name="periodo_fk")
	private TblSgaPeriodolectivo tblSgaPeriodolectivo;

	public TblSgaPeriodonivel() {
	}

	public Integer getPernivId() {
		return this.pernivId;
	}

	public void setPernivId(Integer pernivId) {
		this.pernivId = pernivId;
	}

	public String getPernivEstado() {
		return this.pernivEstado;
	}

	public void setPernivEstado(String pernivEstado) {
		this.pernivEstado = pernivEstado;
	}

	public List<TblSgaPeriodoncurso> getTblSgaPeriodoncursos() {
		return this.tblSgaPeriodoncursos;
	}

	public void setTblSgaPeriodoncursos(List<TblSgaPeriodoncurso> tblSgaPeriodoncursos) {
		this.tblSgaPeriodoncursos = tblSgaPeriodoncursos;
	}

	public TblSgaPeriodoncurso addTblSgaPeriodoncurso(TblSgaPeriodoncurso tblSgaPeriodoncurso) {
		getTblSgaPeriodoncursos().add(tblSgaPeriodoncurso);
		tblSgaPeriodoncurso.setTblSgaPeriodonivel(this);

		return tblSgaPeriodoncurso;
	}

	public TblSgaPeriodoncurso removeTblSgaPeriodoncurso(TblSgaPeriodoncurso tblSgaPeriodoncurso) {
		getTblSgaPeriodoncursos().remove(tblSgaPeriodoncurso);
		tblSgaPeriodoncurso.setTblSgaPeriodonivel(null);

		return tblSgaPeriodoncurso;
	}

	public TblSgaNivel getTblSgaNivel() {
		return this.tblSgaNivel;
	}

	public void setTblSgaNivel(TblSgaNivel tblSgaNivel) {
		this.tblSgaNivel = tblSgaNivel;
	}

	public TblSgaPeriodolectivo getTblSgaPeriodolectivo() {
		return this.tblSgaPeriodolectivo;
	}

	public void setTblSgaPeriodolectivo(TblSgaPeriodolectivo tblSgaPeriodolectivo) {
		this.tblSgaPeriodolectivo = tblSgaPeriodolectivo;
	}

}