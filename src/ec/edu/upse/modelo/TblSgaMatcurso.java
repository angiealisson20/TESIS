package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_matcurso database table.
 * 
 */
@Entity
@Table(name="tbl_sga_matcurso")
@NamedQuery(name="TblSgaMatcurso.findAll", query="SELECT t FROM TblSgaMatcurso t")
public class TblSgaMatcurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="matcurso_id")
	private Integer matcursoId;

	@Column(name="matcurso_estado")
	private String matcursoEstado;

	//bi-directional many-to-one association to TblSgaDistributivo
	@OneToMany(mappedBy="tblSgaMatcurso")
	private List<TblSgaDistributivo> tblSgaDistributivos;

	//bi-directional many-to-one association to TblSgaMateria
	@ManyToOne
	@JoinColumn(name="materia_fk")
	private TblSgaMateria tblSgaMateria;

	//bi-directional many-to-one association to TblSgaPeriodoncurso
	@ManyToOne
	@JoinColumn(name="pernivcur_id_fk")
	private TblSgaPeriodoncurso tblSgaPeriodoncurso;

	public TblSgaMatcurso() {
	}

	public Integer getMatcursoId() {
		return this.matcursoId;
	}

	public void setMatcursoId(Integer matcursoId) {
		this.matcursoId = matcursoId;
	}

	public String getMatcursoEstado() {
		return this.matcursoEstado;
	}

	public void setMatcursoEstado(String matcursoEstado) {
		this.matcursoEstado = matcursoEstado;
	}

	public List<TblSgaDistributivo> getTblSgaDistributivos() {
		return this.tblSgaDistributivos;
	}

	public void setTblSgaDistributivos(List<TblSgaDistributivo> tblSgaDistributivos) {
		this.tblSgaDistributivos = tblSgaDistributivos;
	}

	public TblSgaDistributivo addTblSgaDistributivo(TblSgaDistributivo tblSgaDistributivo) {
		getTblSgaDistributivos().add(tblSgaDistributivo);
		tblSgaDistributivo.setTblSgaMatcurso(this);

		return tblSgaDistributivo;
	}

	public TblSgaDistributivo removeTblSgaDistributivo(TblSgaDistributivo tblSgaDistributivo) {
		getTblSgaDistributivos().remove(tblSgaDistributivo);
		tblSgaDistributivo.setTblSgaMatcurso(null);

		return tblSgaDistributivo;
	}

	public TblSgaMateria getTblSgaMateria() {
		return this.tblSgaMateria;
	}

	public void setTblSgaMateria(TblSgaMateria tblSgaMateria) {
		this.tblSgaMateria = tblSgaMateria;
	}

	public TblSgaPeriodoncurso getTblSgaPeriodoncurso() {
		return this.tblSgaPeriodoncurso;
	}

	public void setTblSgaPeriodoncurso(TblSgaPeriodoncurso tblSgaPeriodoncurso) {
		this.tblSgaPeriodoncurso = tblSgaPeriodoncurso;
	}

}