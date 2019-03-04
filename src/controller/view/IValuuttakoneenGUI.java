package controller.view;
/**
 * @author silas
 */

public interface IValuuttakoneenGUI {
	public abstract int getLähtöIndeksi();
	public abstract int getKohdeIndeksi();
	public abstract double getMäärä();
	public abstract void setTulos(double määrä);
}
