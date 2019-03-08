package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_distributivo database table.
 * 
 */
@Entity
@Table(name="tbl_sga_distributivo")
@NamedQueries({
	@NamedQuery(name="TblSgaDistributivo.findAll", query="SELECT t FROM TblSgaDistributivo t"),
	@NamedQuery(name="TblSgaDistributivo.findByDocenteAndMateria", query="SELECT t FROM TblSgaDistributivo t where t.tblSgaDocente = :doc and t.tblSgaMatcurso.tblSgaMateria = :mat"),
	@NamedQuery(name="TblSgaDistributivo.buscarPorMateria", query="SELECT t FROM TblSgaDistributivo t where t.tblSgaMatcurso.tblSgaMateria.matId = :idMateria"),
	@NamedQuery(name="TblSgaDistributivo.buscarPorPatron", query="SELECT t FROM TblSgaDistributivo t WHERE lower(t.tblSgaMatcurso.tblSgaMateria.matDesc) like lower(:patron)")
})

public class TblSgaDistributivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dist_id")
	private Integer distId;

	@Column(name="dist_estado")
	private String distEstado;

	//bi-directional many-to-one association to TblSgaDocente
	@ManyToOne
	@JoinColumn(name="docente_fk")
	private TblSgaDocente tblSgaDocente;

	//bi-directional many-to-one association to TblSgaHorario
	@OneToMany(mappedBy="tblSgaDistributivo")
	private List<TblSgaHorario> tblSgaHorarios;

	//bi-directional many-to-one association to TblSgaMatcurso
	@ManyToOne
	@JoinColumn(name="matcurso_fk")
	private TblSgaMatcurso tblSgaMatcurso;

	public TblSgaDistributivo() {
	}

	public Integer getDistId() {
		return this.distId;
	}

	public void setDistId(Integer distId) {
		this.distId = distId;
	}

	public String getDistEstado() {
		return this.distEstado;
	}

	public void setDistEstado(String distEstado) {
		this.distEstado = distEstado;
	}

	public TblSgaDocente getTblSgaDocente() {
		return this.tblSgaDocente;
	}

	public void setTblSgaDocente(TblSgaDocente tblSgaDocente) {
		this.tblSgaDocente = tblSgaDocente;
	}

	public List<TblSgaHorario> getTblSgaHorarios() {
		return this.tblSgaHorarios;
	}

	public void setTblSgaHorarios(List<TblSgaHorario> tblSgaHorarios) {
		this.tblSgaHorarios = tblSgaHorarios;
	}

	public TblSgaHorario addTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().add(tblSgaHorario);
		tblSgaHorario.setTblSgaDistributivo(this);

		return tblSgaHorario;
	}

	public TblSgaHorario removeTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().remove(tblSgaHorario);
		tblSgaHorario.setTblSgaDistributivo(null);

		return tblSgaHorario;
	}

	public TblSgaMatcurso getTblSgaMatcurso() {
		return this.tblSgaMatcurso;
	}

	public void setTblSgaMatcurso(TblSgaMatcurso tblSgaMatcurso) {
		this.tblSgaMatcurso = tblSgaMatcurso;
	}

}