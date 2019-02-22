package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_materia database table.
 * 
 */
@Entity
@Table(name="tbl_sga_materia")
@NamedQuery(name="TblSgaMateria.findAll", query="SELECT t FROM TblSgaMateria t")
public class TblSgaMateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mat_id")
	private Integer matId;

	@Column(name="mat_desc")
	private String matDesc;

	@Column(name="mat_estado")
	private String matEstado;

	//bi-directional many-to-one association to TblSgaMatcurso
	@OneToMany(mappedBy="tblSgaMateria")
	private List<TblSgaMatcurso> tblSgaMatcursos;

	public TblSgaMateria() {
	}

	public Integer getMatId() {
		return this.matId;
	}

	public void setMatId(Integer matId) {
		this.matId = matId;
	}

	public String getMatDesc() {
		return this.matDesc;
	}

	public void setMatDesc(String matDesc) {
		this.matDesc = matDesc;
	}

	public String getMatEstado() {
		return this.matEstado;
	}

	public void setMatEstado(String matEstado) {
		this.matEstado = matEstado;
	}

	public List<TblSgaMatcurso> getTblSgaMatcursos() {
		return this.tblSgaMatcursos;
	}

	public void setTblSgaMatcursos(List<TblSgaMatcurso> tblSgaMatcursos) {
		this.tblSgaMatcursos = tblSgaMatcursos;
	}

	public TblSgaMatcurso addTblSgaMatcurso(TblSgaMatcurso tblSgaMatcurso) {
		getTblSgaMatcursos().add(tblSgaMatcurso);
		tblSgaMatcurso.setTblSgaMateria(this);

		return tblSgaMatcurso;
	}

	public TblSgaMatcurso removeTblSgaMatcurso(TblSgaMatcurso tblSgaMatcurso) {
		getTblSgaMatcursos().remove(tblSgaMatcurso);
		tblSgaMatcurso.setTblSgaMateria(null);

		return tblSgaMatcurso;
	}

}