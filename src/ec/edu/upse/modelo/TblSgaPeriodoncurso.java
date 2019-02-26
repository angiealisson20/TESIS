package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_periodoncurso database table.
 * 
 */
@Entity
@Table(name="tbl_sga_periodoncurso")
@NamedQueries({
	@NamedQuery(name="TblSgaPeriodoncurso.findAll", query="SELECT t FROM TblSgaPeriodoncurso t"),
	@NamedQuery(name="TblSgaPeriodoncurso.buscarPorNivel", query="SELECT t FROM TblSgaPeriodoncurso t where t.tblSgaPeriodonivel.tblSgaNivel.nivelId = :idNivel")
})
public class TblSgaPeriodoncurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pernivcur_id")
	private Integer pernivcurId;

	@Column(name="pernivcur_estado")
	private String pernivcurEstado;

	//bi-directional many-to-one association to TblSgaCursoparalelo
	@OneToMany(mappedBy="tblSgaPeriodoncurso")
	private List<TblSgaCursoparalelo> tblSgaCursoparalelos;

	//bi-directional many-to-one association to TblSgaMatcurso
	@OneToMany(mappedBy="tblSgaPeriodoncurso")
	private List<TblSgaMatcurso> tblSgaMatcursos;

	//bi-directional many-to-one association to TblSgaCurso
	@ManyToOne
	@JoinColumn(name="curso_fk")
	private TblSgaCurso tblSgaCurso;

	//bi-directional many-to-one association to TblSgaPeriodonivel
	@ManyToOne
	@JoinColumn(name="perniv_fk")
	private TblSgaPeriodonivel tblSgaPeriodonivel;

	public TblSgaPeriodoncurso() {
	}

	public Integer getPernivcurId() {
		return this.pernivcurId;
	}

	public void setPernivcurId(Integer pernivcurId) {
		this.pernivcurId = pernivcurId;
	}

	public String getPernivcurEstado() {
		return this.pernivcurEstado;
	}

	public void setPernivcurEstado(String pernivcurEstado) {
		this.pernivcurEstado = pernivcurEstado;
	}

	public List<TblSgaCursoparalelo> getTblSgaCursoparalelos() {
		return this.tblSgaCursoparalelos;
	}

	public void setTblSgaCursoparalelos(List<TblSgaCursoparalelo> tblSgaCursoparalelos) {
		this.tblSgaCursoparalelos = tblSgaCursoparalelos;
	}

	public TblSgaCursoparalelo addTblSgaCursoparalelo(TblSgaCursoparalelo tblSgaCursoparalelo) {
		getTblSgaCursoparalelos().add(tblSgaCursoparalelo);
		tblSgaCursoparalelo.setTblSgaPeriodoncurso(this);

		return tblSgaCursoparalelo;
	}

	public TblSgaCursoparalelo removeTblSgaCursoparalelo(TblSgaCursoparalelo tblSgaCursoparalelo) {
		getTblSgaCursoparalelos().remove(tblSgaCursoparalelo);
		tblSgaCursoparalelo.setTblSgaPeriodoncurso(null);

		return tblSgaCursoparalelo;
	}

	public List<TblSgaMatcurso> getTblSgaMatcursos() {
		return this.tblSgaMatcursos;
	}

	public void setTblSgaMatcursos(List<TblSgaMatcurso> tblSgaMatcursos) {
		this.tblSgaMatcursos = tblSgaMatcursos;
	}

	public TblSgaMatcurso addTblSgaMatcurso(TblSgaMatcurso tblSgaMatcurso) {
		getTblSgaMatcursos().add(tblSgaMatcurso);
		tblSgaMatcurso.setTblSgaPeriodoncurso(this);

		return tblSgaMatcurso;
	}

	public TblSgaMatcurso removeTblSgaMatcurso(TblSgaMatcurso tblSgaMatcurso) {
		getTblSgaMatcursos().remove(tblSgaMatcurso);
		tblSgaMatcurso.setTblSgaPeriodoncurso(null);

		return tblSgaMatcurso;
	}

	public TblSgaCurso getTblSgaCurso() {
		return this.tblSgaCurso;
	}

	public void setTblSgaCurso(TblSgaCurso tblSgaCurso) {
		this.tblSgaCurso = tblSgaCurso;
	}

	public TblSgaPeriodonivel getTblSgaPeriodonivel() {
		return this.tblSgaPeriodonivel;
	}

	public void setTblSgaPeriodonivel(TblSgaPeriodonivel tblSgaPeriodonivel) {
		this.tblSgaPeriodonivel = tblSgaPeriodonivel;
	}

}