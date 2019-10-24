import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class VendaImoveisTest2 {
	
	VendaImoveis v1;
	HashSet<String> imoveis1;
	
	@Before
	public void init () {
		v1 = new VendaImoveis("./src/main/resources/Vizinhanca2.csv");
		imoveis1 = new HashSet<String>();
		imoveis1.add("I1"); imoveis1.add("I2"); imoveis1.add("I4"); imoveis1.add("I5");
		imoveis1.add("I6"); imoveis1.add("I7");
	}
	
	@Test
	public void test1() { 
		String imovel = v1.localizaImovel("ESCOLA",imoveis1);
		assertEquals("I1",imovel);

	}
	
	@Test 
	public void test2 () { 
		String imovel = v1.localizaImovel("HOSPITAL",imoveis1);
		assertTrue(imovel.equals("I2") || imovel.equals("I6") || imovel.contentEquals("I1"));
		
	}
	
	@Test
	public void test3 () { 
		String imovel = v1.localizaImovel("SHOPPING",imoveis1);
		assertEquals("I7",imovel);
	}

	
	@Test 
	public void test4 () { //TESTE FARMACIA
		String imovel = v1.localizaImovel("FARMACIA",imoveis1);
		assertEquals("I2",imovel);
	}
	
	
	@Test 
	public void test5 () { //TESTE Q1, TAMANHOS IGUALS 180.0
		String imovel = v1.localizaImovel("Q1",imoveis1);
		assertTrue(imovel.equals("I2") || imovel.equals("I1"));
	}
	
	@Test 
	public void test6 () {
		String imovel = v1.localizaImovel("POSTO",imoveis1);
		assertEquals("I4",imovel);
	}

	@Test 
	public void test7 () {
		String imovel = v1.localizaImovel("Q6",imoveis1);
		assertEquals("I5", imovel);
	}

	@Test 
	public void test8 () {
		String imovel = v1.localizaImovel("Q8",imoveis1);
		assertEquals("I5",imovel);
	}
	
	@Test
	public void test9 () {
		String imovel = v1.localizaImovel("MERCADO", imoveis1);
		assertEquals("I5", imovel);
	}
	
	@Test
	public void test10 () {
		String imovel = v1.localizaImovel("PRACA2", imoveis1);
		assertEquals("I7", imovel);
	}
	
	@Test
	public void test11 () {
		String imovel = v1.localizaImovel("PRACA1", imoveis1);
		assertEquals("I2", imovel);
	}
	
	@Test
	public void test12 () {
		String imovel = v1.localizaImovel("PRACA1", imoveis1);
		assertEquals("I2", imovel);
	}
	
	@Test
	public void test13 () {
		String imovel = v1.localizaImovel("ESCOLA", null);
		assertNull(imovel);
	}
	
	@Test 
	public void test14 () {
		String imovel = v1.localizaImovel(null,imoveis1);
		assertNull(imovel);
	}
	
	@Test
	public void test15() {
		String imovel = v1.localizaImovel(null,null);
		assertNull(imovel);
	}
	
	@Test
	public void test16() {
		String imovel = v1.localizaImovel("WHATEVER",null);
		assertNull(imovel);
	}
	
	@Test
	public void test17() {
		String imovel = v1.localizaImovel("WHATEVER", imoveis1);
		assertNull(imovel);
	}
	
	@Test 
	public void test18 () {
		String imovel = v1.localizaImovel("Q4", imoveis1);
		assertEquals("I2",imovel);
	}
	
	@Test 
	public void test19 () {
		String imovel = v1.localizaImovel("Q11", imoveis1);
		assertEquals("I7",imovel);
	}
	
	@Test 
	public void test20 () {
		String imovel = v1.localizaImovel("Q7", imoveis1);
		assertEquals("I2",imovel);
	}
	
	@Test 
	public void test21 () {
		String imovel = v1.localizaImovel("HAMBURGUERIAMUITOBOA", imoveis1);
		assertEquals("I4",imovel);
	}
	
	@Test 
	public void test22 () {
		String imovel = v1.localizaImovel("FACULDADE", imoveis1);
		assertEquals("I7",imovel);
	}
	
	@Test 
	public void test23 () {
		String imovel = v1.localizaImovel("PADARIA", imoveis1);
		assertEquals("I7",imovel);
	}
	
	@Test 
	public void test24 () {
		String imovel = v1.localizaImovel("DISCOTECA", imoveis1);
		assertEquals("I7",imovel);
	}
	
	@Test 
	public void test25 () {
		String imovel = v1.localizaImovel("SESC", imoveis1);
		assertEquals("I5",imovel);
	}
	
	@Test 
	public void test26 () {
		String imovel = v1.localizaImovel("PETSHOP", imoveis1);
		assertEquals("I4",imovel);
	}
}
