package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_paralelo database table.
 * 
 */
@Entity
@Table(name="tbl_sga_paralelo")
@NamedQuery(name="TblSgaParalelo.findAll", query="SELECT t FROM TblSgaParalelo t")
public class TblSgaParalelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="paral_id")
	private Integer paralId;

	@Column(name="paral_desc")
	private String paralDesc;

	@Column(name="paral_estado")
	private String paralEstado;

	//bi-directional many-to-one association to TblSgaCursoparalelo
	@OneToMany(mappedBy="tblSgaParalelo")
	private List<TblSgaCursoparalelo> tblSgaCursoparalelos;

	public TblSgaParalelo() {
	}

	public Integer getParalId() {
		return this.paralId;
	}

	public void setParalId(Integer paralId) {
		this.paralId = paralId;
	}

	public String getParalDesc() {
		return this.paralDesc;
	}

	public void setParalDesc(String paralDesc) {
		this.paralDesc = paralDesc;
	}

	public String getParalEstado() {
		return this.paralEstado;
	}

	public void setParalEstado(String paralEstado) {
		this.paralEstado = paralEstado;
	}

	public List<TblSgaCursoparalelo> getTblSgaCursoparalelos() {
		return this.tblSgaCursoparalelos;
	}

	public void setTblSgaCursoparalelos(List<TblSgaCursoparalelo> tblSgaCursoparalelos) {
		this.tblSgaCursoparalelos = tblSgaCursoparalelos;
	}

	public TblSgaCursoparalelo addTblSgaCursoparalelo(TblSgaCursoparalelo tblSgaCursoparalelo) {
		getTblSgaCursoparalelos().add(tblSgaCursoparalelo);
		tblSgaCursoparalelo.setTblSgaParalelo(this);

		return tblSgaCursoparalelo;
	}

	public TblSgaCursoparalelo removeTblSgaCursoparalelo(TblSgaCursoparalelo tblSgaCursoparalelo) {
		getTblSgaCursoparalelos().remove(tblSgaCursoparalelo);
		tblSgaCursoparalelo.setTblSgaParalelo(null);

		return tblSgaCursoparalelo;
	}

}