package controller;

import controller.model.Valuuttakone;
import controller.view.ValuuttakoneenGUI;

public class ValuuttakoneenOhjain implements IValuuttakoneenOhjain {
	
	Valuuttakone valuuttakone;
	ValuuttakoneenGUI gui;
	
	public ValuuttakoneenOhjain(Valuuttakone valuuttakone, ValuuttakoneenGUI gui) {
		this.valuuttakone = valuuttakone;
		this.gui = gui;
	}

	@Override
	public void muunnos() {
		int lahtoIndeksi = gui.getLähtöIndeksi();
		int kohdeIndeksi = gui.getKohdeIndeksi();
		double maara = gui.getMäärä();
		double muunnettu = valuuttakone.muunna(lahtoIndeksi, kohdeIndeksi, maara);
		
		gui.setTulos(muunnettu);
	}

	@Override
	public String[] getValuutat() {
		String[] valuutat = valuuttakone.getVaihtoehdot();
		return valuutat;
	}
}