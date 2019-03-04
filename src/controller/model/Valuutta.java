package controller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jerry Salonen
 */

@Entity
@Table(name="valuuttaHibernate")
public class Valuutta {
	
	@Id
	@Column
	private String tunnus;
	
	@Column
	private double vaihtokurssi;
	
	@Column
	private String nimi;
	
	public Valuutta() {}
	
	public Valuutta(String tunnus, double vaihtokurssi, String nimi) {
		this.tunnus = tunnus;
		this.vaihtokurssi = vaihtokurssi;
		this.nimi = nimi;
	}

	public String getTunnus() {
		return tunnus;
	}
	
	public double getVaihtokurssi() {
		return vaihtokurssi;
	}
	
	public String getNimi() {
		return nimi;
	}

	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}

	public void setVaihtokurssi(double vaihtokurssi) {
		this.vaihtokurssi = vaihtokurssi;
	}
	
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
}