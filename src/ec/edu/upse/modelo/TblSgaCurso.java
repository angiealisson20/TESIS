package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_curso database table.
 * 
 */
@Entity
@Table(name="tbl_sga_curso")
@NamedQuery(name="TblSgaCurso.findAll", query="SELECT t FROM TblSgaCurso t")
public class TblSgaCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cur_id")
	private Integer curId;

	@Column(name="cur_desc")
	private String curDesc;

	@Column(name="cur_estado")
	private String curEstado;

	//bi-directional many-to-one association to TblSgaPeriodoncurso
	@OneToMany(mappedBy="tblSgaCurso")
	private List<TblSgaPeriodoncurso> tblSgaPeriodoncursos;

	public TblSgaCurso() {
	}

	public Integer getCurId() {
		return this.curId;
	}

	public void setCurId(Integer curId) {
		this.curId = curId;
	}

	public String getCurDesc() {
		return this.curDesc;
	}

	public void setCurDesc(String curDesc) {
		this.curDesc = curDesc;
	}

	public String getCurEstado() {
		return this.curEstado;
	}

	public void setCurEstado(String curEstado) {
		this.curEstado = curEstado;
	}

	public List<TblSgaPeriodoncurso> getTblSgaPeriodoncursos() {
		return this.tblSgaPeriodoncursos;
	}

	public void setTblSgaPeriodoncursos(List<TblSgaPeriodoncurso> tblSgaPeriodoncursos) {
		this.tblSgaPeriodoncursos = tblSgaPeriodoncursos;
	}

	public TblSgaPeriodoncurso addTblSgaPeriodoncurso(TblSgaPeriodoncurso tblSgaPeriodoncurso) {
		getTblSgaPeriodoncursos().add(tblSgaPeriodoncurso);
		tblSgaPeriodoncurso.setTblSgaCurso(this);

		return tblSgaPeriodoncurso;
	}

	public TblSgaPeriodoncurso removeTblSgaPeriodoncurso(TblSgaPeriodoncurso tblSgaPeriodoncurso) {
		getTblSgaPeriodoncursos().remove(tblSgaPeriodoncurso);
		tblSgaPeriodoncurso.setTblSgaCurso(null);

		return tblSgaPeriodoncurso;
	}

}