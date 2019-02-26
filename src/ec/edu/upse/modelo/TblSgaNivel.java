
package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_nivel database table.
 * 
 */
@Entity
@Table(name="tbl_sga_nivel")
@NamedQuery(name="TblSgaNivel.findAll", query="SELECT t FROM TblSgaNivel t")
public class TblSgaNivel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nivel_id")
	private Integer nivelId;

	@Column(name="nivel_desc")
	private String nivelDesc;

	@Column(name="nivel_estado")
	private String nivelEstado;

	//bi-directional many-to-one association to TblSgaPeriodonivel
	@OneToMany(mappedBy="tblSgaNivel")
	private List<TblSgaPeriodonivel> tblSgaPeriodonivels;

	public TblSgaNivel() {
	}

	public Integer getNivelId() {
		return this.nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	public String getNivelDesc() {
		return this.nivelDesc;
	}

	public void setNivelDesc(String nivelDesc) {
		this.nivelDesc = nivelDesc;
	}

	public String getNivelEstado() {
		return this.nivelEstado;
	}

	public void setNivelEstado(String nivelEstado) {
		this.nivelEstado = nivelEstado;
	}

	public List<TblSgaPeriodonivel> getTblSgaPeriodonivels() {
		return this.tblSgaPeriodonivels;
	}

	public void setTblSgaPeriodonivels(List<TblSgaPeriodonivel> tblSgaPeriodonivels) {
		this.tblSgaPeriodonivels = tblSgaPeriodonivels;
	}

	public TblSgaPeriodonivel addTblSgaPeriodonivel(TblSgaPeriodonivel tblSgaPeriodonivel) {
		getTblSgaPeriodonivels().add(tblSgaPeriodonivel);
		tblSgaPeriodonivel.setTblSgaNivel(this);

		return tblSgaPeriodonivel;
	}

	public TblSgaPeriodonivel removeTblSgaPeriodonivel(TblSgaPeriodonivel tblSgaPeriodonivel) {
		getTblSgaPeriodonivels().remove(tblSgaPeriodonivel);
		tblSgaPeriodonivel.setTblSgaNivel(null);

		return tblSgaPeriodonivel;
	}

}