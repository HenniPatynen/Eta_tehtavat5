package testi;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import model.Asiakas;
import model.dao.Dao;

@TestMethodOrder(OrderAnnotation.class)
class JUnit_testaa_autot {

	@Test
	@Order(1) 
	public void testPoistaKaikkiAsiakkaat() {
		//Poistetaan kaikki autot
		Dao dao = new Dao();
		dao.poistaKaikkiAsiakkaat("nimda");
		ArrayList<Asiakas> asiakas = dao.listaaKaikki();
		assertEquals(0, asiakas.size());
	}
	
	@Test
	@Order(2) 
	public void testLisaaAuto() {		
		//Tehd‰‰n muutama uusi testiauto
		Dao dao = new Dao();
		Asiakas asiakas_1 = new Asiakas(4,"Mikko", "Mallikas", "0400121212", "Mikko.Mallikas@MM.fi");
		Asiakas asiakas_2 = new Asiakas(5,"Jenni", "J‰nnitt‰v‰", "0501211212", "Jenni@jannittava.fi");
		Asiakas asiakas_3 = new Asiakas(6,"Minna", "Mallikas", "0400131313", "Minna.Mallikas@MM.fi");
		Asiakas asiakas_4 = new Asiakas(7,"Ville", "Vallaton", "0401212133", "Ville.Vallaton@yolo.fi");

		assertEquals(true, dao.lisaaAsiakas(asiakas_1));
		assertEquals(true, dao.lisaaAsiakas(asiakas_2));
		assertEquals(true, dao.lisaaAsiakas(asiakas_3));
		assertEquals(true, dao.lisaaAsiakas(asiakas_4));
	}
	
	@Test
	@Order(3) 
	public void testMuutaAsiakas() {
		//Muutetaan yht‰ autoa
		Dao dao = new Dao();
		Asiakas muutettava = dao.etsiAsiakas("1");
		muutettava.setEtunimi("Mikko");
		muutettava.setSukunimi("Muuttuja");
		muutettava.setPuhelin("0400232323");
		muutettava.setSposti("mikko.muuttuja@mm.fi");
		dao.muutaAsiakas(muutettava, 1);	
		assertEquals("", dao.etsiAsiakas(1).getAsiakas_id());
		assertEquals("Mikko", dao.etsiAsiakas("etunimi").getEtunimi());
		assertEquals("Muuttuja", dao.etsiAsiakas("sukunimi").getSukunimi());
		assertEquals("0400232323", dao.etsiAsiakas("sukunimi").getSukunimi());
		assertEquals("mikko.muuttuja@mm.fi", dao.etsiAsiakas("sposti").getSposti());
	}
	
	@Test
	@Order(4) 
	public void testPoistaAsiakas() {
		Dao dao = new Dao();
		dao.poistaAsiakas("A-1");
		assertEquals(null, dao.etsiAsiakas(1));
	}

}
