package controller.model;

import java.util.ArrayList;

public class Valuuttakone implements IValuuttakone {
	
	private ArrayList<Valuutta> valuutat = new ArrayList<>();
	private ValuuttaAccessObject valuuttaDAO;
	
	public Valuuttakone() {
		valuuttaDAO = new ValuuttaAccessObject();
		Valuutta[] valuuttaArr = valuuttaDAO.readValuutat();
		for (Valuutta v : valuuttaArr) {
			valuutat.add(v);
		}
	}

	@Override
	public String[] getVaihtoehdot() {
		String[] valuuttataulu = new String[valuutat.size()];
		for (int i = 0; i < valuutat.size(); i++) {
			valuuttataulu[i] = valuutat.get(i).getNimi();
		}
		return valuuttataulu;
	}

	@Override
	public double muunna(int mistäIndeksi, int mihinIndeksi, double määrä) {
		double tulos = määrä/(valuutat.get(mistäIndeksi).getVaihtokurssi());
		
		tulos *= valuutat.get(mihinIndeksi).getVaihtokurssi();
		
		return tulos;
	}

}
