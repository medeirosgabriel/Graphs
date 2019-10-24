import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.util.Pair;
import org.junit.Test;

public class CirculoAmigosTest {

	@Test
	public void test1() { //Varios componentes
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a","b"));
		listaAmigos.add(new Pair<String, String>("c","d"));
		listaAmigos.add(new Pair<String, String>("e","f"));
		listaAmigos.add(new Pair<String, String>("f","g"));
		listaAmigos.add(new Pair<String, String>("i","j"));
		
		Set <String> c1 = new HashSet<String> (); c1.add("a"); c1.add("b");
		Set <String> c2 = new HashSet<String> (); c2.add("c"); c2.add("d");
		Set <String> c3 = new HashSet<String> (); c3.add("e"); c3.add("f"); c3.add("g"); 
		Set <String> c4 = new HashSet<String> (); c4.add("i"); c4.add("j");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));
		assertTrue(circuloAmigos.contains(c4));
		assertEquals(circuloAmigos.size(),4);		
	}
	
	@Test 
	public void test2() { //Um componente em ciclo
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a","b"));
		listaAmigos.add(new Pair<String, String>("c","b"));
		listaAmigos.add(new Pair<String, String>("e","a"));
		listaAmigos.add(new Pair<String, String>("f","b"));
		listaAmigos.add(new Pair<String, String>("f","a"));		
		
		Set <String> c1 = new HashSet<String> (); c1.add("a"); c1.add("b"); c1.add("c"); c1.add("e"); c1.add("f");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertEquals(circuloAmigos.size(),1);	
	}
	
	@Test
	public void test3() { //Nenhum vertice
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();

		// Calculando circulos
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

		assertEquals(circuloAmigos.size(), 0);	
	}
	
	@Test
	public void test4() { //Um unico componente, com k como no central
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a","k"));
		listaAmigos.add(new Pair<String, String>("c","k"));
		listaAmigos.add(new Pair<String, String>("e","k"));
		listaAmigos.add(new Pair<String, String>("f","k"));
		listaAmigos.add(new Pair<String, String>("b","k"));
		
		Set <String> c1 = new HashSet<String> (); c1.add("a"); c1.add("e"); c1.add("f"); c1.add("b"); c1.add("c"); c1.add("k");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertEquals(circuloAmigos.size(), 1);	
	}
	
	@Test
	public void test5() { //Grafo desconectado
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("k","-"));
		listaAmigos.add(new Pair<String, String>("a",""));
		listaAmigos.add(new Pair<String, String>("b",null));
		
		Set <String> c1 = new HashSet<String>(); c1.add("k");
		Set <String> c2 = new HashSet<String>(); c2.add("a");
		Set <String> c3 = new HashSet<String>(); c3.add("b");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));
		
		assertEquals(circuloAmigos.size(), 3);		
	}
	
	@Test
	public void test6() { //Dois componentes, 2 nos centrais
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a", "t"));
		listaAmigos.add(new Pair<String, String>("b", "t"));
		listaAmigos.add(new Pair<String, String>("c", "t"));
		listaAmigos.add(new Pair<String, String>("d", "k"));
		listaAmigos.add(new Pair<String, String>("e", "k"));
		listaAmigos.add(new Pair<String, String>("f", "k"));
		
		Set <String> c1 = new HashSet<String>(); 
		c1.add("t"); c1.add("a"); c1.add("b"); c1.add("c"); 
		Set <String> c2 = new HashSet<String>();
		c2.add("k"); c2.add("d"); c2.add("e"); c2.add("f");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		
		assertEquals(circuloAmigos.size(), 2);
	}
	
	@Test
	public void test7() { //dois componentes com um vertice desconectado
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a", "b"));
		listaAmigos.add(new Pair<String, String>("b", "c"));
		listaAmigos.add(new Pair<String, String>("d", "e"));
		listaAmigos.add(new Pair<String, String>("e", "f"));
		listaAmigos.add(new Pair<String, String>("f", "g"));
		listaAmigos.add(new Pair<String, String>("p", ""));
		
		Set <String> c1 = new HashSet<String>(); 
		c1.add("a"); c1.add("b"); c1.add("c"); 
		Set <String> c2 = new HashSet<String>();
		c2.add("d"); c2.add("e"); c2.add("f"); c2.add("g");
		Set <String> c3 = new HashSet<String>();
		c3.add("p");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));
		
		assertEquals(circuloAmigos.size(), 3);
	}
	
	@Test
	public void test8() { //ultimo vertice so com uma ligacao
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a", "b"));
		listaAmigos.add(new Pair<String, String>("b", ""));
		listaAmigos.add(new Pair<String, String>("d", "e"));
		listaAmigos.add(new Pair<String, String>("e", "f"));
		listaAmigos.add(new Pair<String, String>("f", ""));
		
		Set <String> c1 = new HashSet<String>(); 
		c1.add("a"); c1.add("b");
		Set <String> c2 = new HashSet<String>();
		c2.add("d"); c2.add("e"); c2.add("f");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		
		assertEquals(circuloAmigos.size(), 2);
		
	}
	
	@Test
	public void test9() { //dois componentes com mais de um vertice, e varios apenas com um vertice
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a", "b"));
		listaAmigos.add(new Pair<String, String>("b", "c"));
		listaAmigos.add(new Pair<String, String>("d", "e"));
		listaAmigos.add(new Pair<String, String>("e", "f"));
		listaAmigos.add(new Pair<String, String>("g", ""));
		listaAmigos.add(new Pair<String, String>("h", null));
		listaAmigos.add(new Pair<String, String>("i", "-"));
		
		Set <String> c1 = new HashSet<String>(); 
		c1.add("a"); c1.add("b"); c1.add("c");
		Set <String> c2 = new HashSet<String>();
		c2.add("d"); c2.add("e"); c2.add("f");
		Set <String> c3 = new HashSet<String>();
		c3.add("g");
		Set <String> c4 = new HashSet<String>();
		c4.add("h");
		Set <String> c5 = new HashSet<String>();
		c5.add("i");
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		assertTrue(circuloAmigos.contains(c2));
		assertTrue(circuloAmigos.contains(c3));
		assertTrue(circuloAmigos.contains(c4));
		assertTrue(circuloAmigos.contains(c5));
		
		assertEquals(circuloAmigos.size(), 5);
	}
	
	@Test
	public void test10() { //um componente, sem ciclo.
		ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
		listaAmigos.add(new Pair<String, String>("a", "b"));
		listaAmigos.add(new Pair<String, String>("b", "c"));
		listaAmigos.add(new Pair<String, String>("c", "d"));
		listaAmigos.add(new Pair<String, String>("d", "e"));
		
		Set <String> c1 = new HashSet<String>(); 
		c1.add("a"); c1.add("b"); c1.add("c"); c1.add("d"); c1.add("e"); 
		
		List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
		
		assertTrue(circuloAmigos.contains(c1));
		
		assertEquals(circuloAmigos.size(), 1);
	}
}