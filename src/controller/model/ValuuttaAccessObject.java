package controller.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ValuuttaAccessObject implements IValuuttaDAO {

	private static SessionFactory istuntotehdas;

	public ValuuttaAccessObject() {
		istuntotehdas = null;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		try {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Virhe!");
			StandardServiceRegistryBuilder.destroy(registry);
			System.exit(-1);
		}
	}

	@Override
	public boolean createValuutta(Valuutta valuutta) {

		Transaction transaktio = null;
		try (Session istunto = istuntotehdas.openSession()) {
			if (valuutta != null) {
				transaktio = istunto.beginTransaction();
				istunto.saveOrUpdate(valuutta);
				transaktio.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			System.err.println("Virhe!");
		}

		return false;
	}

	@Override
	public Valuutta readValuutta(String tunnus) {

		Transaction transaktio = null;
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			Valuutta valuutta = new Valuutta();
			istunto.load(valuutta, tunnus);

			istunto.getTransaction().commit();

			return valuutta;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return null;
		}
	}

	@Override
	public Valuutta[] readValuutat() {
		Transaction transaktio = null;
		Valuutta[] valuutat;
		int index = 0;
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			List<Valuutta> valuuttaList = istunto.createQuery("from Valuutta").getResultList();
			valuutat = new Valuutta[valuuttaList.size()];

			for(Valuutta v : valuuttaList) {
				valuutat[index] = v;
				index++;
			}

		} catch (Exception e) {
			System.err.println("Virhe!");
			valuutat = null;
		}
		return valuutat;
	}

	@Override
	public boolean updateValuutta(Valuutta valuutta) {

		try (Session istunto = istuntotehdas.openSession()) {

			String nimi = valuutta.getNimi();
			double kurssi = valuutta.getVaihtokurssi();

			istunto.beginTransaction();

			valuutta = (Valuutta)istunto.get(Valuutta.class, valuutta.getTunnus());

			if (valuutta != null) {
				valuutta.setNimi(nimi);
				valuutta.setVaihtokurssi(kurssi);
			} else {
				System.out.println("Päivitys epäonnistui.");
				return false;
			}

			istunto.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}
	}

	@Override
	public boolean deleteValuutta(String tunnus) {
		
		Valuutta valuutta = readValuutta(tunnus);
		
		try (Session istunto = istuntotehdas.openSession()) {
			
			istunto.beginTransaction();
			
			if (valuutta != null) {
				istunto.delete(valuutta);
			} else {
				return false;
			}

			istunto.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}

	}
}