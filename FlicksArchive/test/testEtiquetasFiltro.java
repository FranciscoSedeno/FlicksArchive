import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.*;

import flicksArchive.Conexion;
import flicksArchive.Elemento;
import flicksArchive.Etiqueta;
import flicksArchive.Filtro;
import flicksArchive.Lista;

public class testEtiquetasFiltro {
	
	private Conexion con = mock(Conexion.class);
	private Filtro filtro;
	private Date d = new Date(0);
	private Elemento e1 ;
	private Elemento e2 ;
	private Elemento e3 ;
	private Elemento e4 ;
	private String s[] = {"DRAMA","COMEDIA","ACCION","FANTASIA","ADULTOS","INFANTIL","CIENCIA-FICCION","FUTURISTA"};
	@BeforeEach
	public void inicializar() {
		e1 = new Elemento("CCCCC", d, d, "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		e2 = new Elemento("BBBBB", d, d, "Falta daño", "", 1, "HBO", 10, 1, true, 5, 1);
		e3 = new Elemento("AAAAA", d, d, "Que tal", "", 2, "NETFLIX", 5, 2, false, 3, 1);
		e4 = new Elemento("CAAAA", d, d, "Que tal", "", 3, "NETFLIX", 5, 0, false, 0, 1);
		filtro = new Filtro();
	}
	
	@Test
	public void testcrearFiltro() {
		assertTrue(filtro.getEtiquetasUsuario().isEmpty());
		assertFalse(filtro.etiquetas().isEmpty());
		assertTrue(filtro.etiquetasSinUso().isEmpty());
	}
	@Test 
	public void testEtiquetasSinUso() {
		List<Etiqueta> l = filtro.etiquetasSinUso();
		assertTrue(l.isEmpty());
		
		Etiqueta e = filtro.pedirEtiqueta("a");
		l = filtro.etiquetasSinUso();
		assertFalse(l.isEmpty());
		assertTrue(l.contains(e));
		
		e1.anadirEtiqueta(filtro, "A");
		l = filtro.etiquetasSinUso();
		assertTrue(l.isEmpty());
		
		e1.resetearEtiquetas();
		l = filtro.etiquetasSinUso();
		assertFalse(l.isEmpty());
		assertTrue(l.contains(e));
	}
	@Test
	public void testBuscaEtiquetasNuevoFiltro() {
		
		assertNull(filtro.buscaEtiqueta("A"));
		assertNull(filtro.buscaEtiqueta("B"));
		
		e1.anadirEtiqueta(filtro, "A");
		
		assertNotNull(filtro.buscaEtiqueta("A"));
		assertNull(filtro.buscaEtiqueta("B"));
	}
	@Test
	public void testEtiquetaUsuario() {
		
		Etiqueta e=filtro.pedirEtiqueta("PRUEBAS");
		assertEquals(0,e.getContador());
		e1.anadirEtiqueta(filtro, "pruebas");
		assertEquals(1, e1.getContEtiqUsu());
		assertSame(e1.etiquetasUsuario()[0],e);
		assertEquals(1,e.getContador());
		
	}
	@Test 
	public void testEtiquetaUsuarioExcepciones() {
		
		
		e1.anadirEtiqueta(filtro,"PRUEBA");
		assertThrows(IllegalArgumentException.class, ()->e1.anadirEtiqueta(filtro,"prueba"));
		e1.anadirEtiqueta(filtro, "prueba2");
		e1.anadirEtiqueta(filtro, "prueba3");
		assertThrows(IllegalArgumentException.class, ()->e1.anadirEtiqueta(filtro,"prueba4"),"ERROR: intentado añadir más etiquetas de las posibles");
	}
	@Test 
	public void testEtiquetasPorDefecto() {
		
		Etiqueta e;
		for (String string : s) {
			e=filtro.buscaEtiqueta(string);
			assertNotNull(e);
			assertEquals(0,e.getContador());
		}
		
	}
	@Test 
	public void testComparadorEtiquetas() {
		Etiqueta et1 =filtro.pedirEtiqueta("a");
		Etiqueta et2 =filtro.pedirEtiqueta("b");
		assertEquals(0, et1.compareTo(et2));
		e1.anadirEtiqueta(filtro, "A");
		assertEquals(1, et1.compareTo(et2));
		e1.anadirEtiqueta(filtro, "B");
		e2.anadirEtiqueta(filtro, "B");
		assertEquals(-1, et1.compareTo(et2));
	}
	@Test
	public void testPedirYBorrarEtiquetaDefectoComoUsuario() {
		
		int n= (new Random()).nextInt(s.length);
		e1.anadirEtiquetaDefecto(filtro, s[n]);
		Etiqueta e=filtro.buscaEtiqueta(s[n]);
		assertEquals(1,e.getContador());
		e4.anadirEtiqueta(filtro,s[n]);
		assertEquals(2,e.getContador());
		e1.resetearEtiquetas();
		assertEquals(2,e.getContador());
		e4.resetearEtiquetas();
		assertEquals(1,e.getContador());
	}
	
	@Test
	public void testBorrarEtiqueta() {
		assertNull(filtro.buscaEtiqueta("A"));
		Etiqueta e=filtro.pedirEtiqueta("A");
		assertNotNull(filtro.buscaEtiqueta("A"));
		filtro.borrarEtiquetaUsuario(e);
		assertNull(filtro.buscaEtiqueta("A"));
	}
	
	@Test
	public void testEtiquetasEnListas() throws SQLException {
		when(con.buscarElemento(0, filtro)).thenReturn(e1);
		when(con.buscarElemento(1, filtro)).thenReturn(e2);
		when(con.buscarElemento(2, filtro)).thenReturn(e3);
		when(con.buscarElemento(3, filtro)).thenReturn(e4);
		Lista lista =new Lista("Prueba", con, filtro);
		e1.anadirEtiqueta(filtro, "A");
		e1.anadirEtiqueta(filtro, "B");
		e2.anadirEtiqueta(filtro, "B");
		e2.anadirEtiqueta(filtro, "C");
		e3.anadirEtiqueta(filtro, "D");
		e3.anadirEtiqueta(filtro, "C");
		e4.anadirEtiqueta(filtro, "D");
		e4.anadirEtiqueta(filtro, "E");
		
		lista = new Lista("Prueba",con,filtro);
		lista.añadirElemento(lista.extraerElementoBD(0));
		lista.añadirElemento(lista.extraerElementoBD(1));
		lista.añadirElemento(lista.extraerElementoBD(2));
		lista.añadirElemento(lista.extraerElementoBD(3));
		
		assertEquals(1, lista.getFiltro().buscaEtiqueta("A").getContador());
		assertEquals(2, lista.getFiltro().buscaEtiqueta("B").getContador());
		assertEquals(2, lista.getFiltro().buscaEtiqueta("C").getContador());
		assertEquals(2, lista.getFiltro().buscaEtiqueta("D").getContador());
		assertEquals(1, lista.getFiltro().buscaEtiqueta("E").getContador());
		
		lista.eliminarElemento(0);
		assertEquals(0, lista.getFiltro().buscaEtiqueta("A").getContador());
		assertEquals(1, lista.getFiltro().buscaEtiqueta("B").getContador());
		
		lista.eliminarElemento(1);
		lista.eliminarElemento(2);
		lista.eliminarElemento(3);
		
		assertEquals(0, lista.getFiltro().buscaEtiqueta("A").getContador());
		assertEquals(0, lista.getFiltro().buscaEtiqueta("B").getContador());
		assertEquals(0, lista.getFiltro().buscaEtiqueta("C").getContador());
		assertEquals(0, lista.getFiltro().buscaEtiqueta("D").getContador());
		assertEquals(0, lista.getFiltro().buscaEtiqueta("E").getContador());
	}
	
	@Test 
	public void testElementoErrores(){
		String s="INveNtada";
		assertThrows(IllegalArgumentException.class, ()->e1.anadirEtiquetaDefecto(filtro, s),"ETIQUETA POR DEFECTO NO RECOGIDA");
		e2.anadirEtiquetaDefecto(filtro, this.s[3]);
		assertThrows(IllegalArgumentException.class,()->e2.anadirEtiquetaDefecto(filtro, this.s[3]) ,"Has intentado añadir la etiqueta"+s.toUpperCase()+"pero ya estaba añadida.");
		e3.anadirEtiquetaDefecto(filtro, this.s[0]);
		e3.anadirEtiquetaDefecto(filtro, this.s[1]);
		e3.anadirEtiquetaDefecto(filtro, this.s[2]);
		assertThrows(IllegalArgumentException.class,()->e3.anadirEtiquetaDefecto(filtro, this.s[3]) ,"INTENTO DE AÑADIR MÁS ETIQUETAS DE LAS POSIBLES");
		
		
	}
	
}
