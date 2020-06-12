import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.*;

import flicksArchive.*;
import flicksArchive.Elemento.estadoVisualizacion;
public class testFiltrosOrdenaciones {
	
	
	private Conexion con = mock(Conexion.class);
	private Date d = new Date(0);
	private Elemento e1 ;
	private Elemento e2 ;
	private Elemento e3 ;
	private Elemento e4 ;
	private Filtro filtro ;
	private Lista lista;
	
	
	@BeforeEach
	public void inicializar() throws SQLException {
		e1 = new Elemento("CCCCC", d, d, "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		e2 = new Elemento("BBBBB", d, d, "Falta daño", "", 1, "HBO", 10, 1, true, 5, 1);
		e3 = new Elemento("AAAAA", d, d, "Que tal", "", 2, "NETFLIX", 5, 2, false, 0, 1);
		e4 = new Elemento("CAAAA", d, d, "Que tal", "", 3, "NETFLIX", 5, 0, false, 0, 1);
		filtro = new Filtro();
		when(con.buscarElemento(0, filtro)).thenReturn(e1);
		when(con.buscarElemento(1, filtro)).thenReturn(e2);
		when(con.buscarElemento(2, filtro)).thenReturn(e3);
		when(con.buscarElemento(3, filtro)).thenReturn(e4);
		
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
	}
	@Test
	public void filtrarTestFavoritos() throws SQLException {
		filtro.setFavoritoActivo(true);
		Collection<Elemento> c=lista.getListaFiltrada();
		assertEquals(2,c.size());
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e2));
		
	}
	@Test
	public void filtrarTestEstado() throws SQLException {
		filtro.setEstadoBuscado(estadoVisualizacion.PENDIENTE);
		Collection<Elemento> c=lista.getListaFiltrada();
		assertEquals(2,c.size());
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e4));
			
	}
	@Test
	public void filtrarTestFragmento() throws SQLException {
		filtro.setFragmentoTitulo("A");
		Collection<Elemento> c=lista.getListaFiltrada();
		assertEquals(2,c.size());
		assertTrue(c.contains(e3));
		assertTrue(c.contains(e4));
		filtro.setFragmentoTitulo("c");
	    c=lista.getListaFiltrada();
		assertEquals(2,c.size());
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e4));
	}
	@Test
	public void filtrarTestEtiqueta1() throws SQLException {
		filtro.anadirEtiquetaFiltrada(filtro.buscaEtiqueta("A"));
		Collection<Elemento> c=lista.getListaFiltrada();
		assertEquals(1,c.size());
		assertTrue(c.contains(e1));
		filtro.anadirEtiquetaFiltrada(filtro.buscaEtiqueta("E"));
		c=lista.getListaFiltrada();
		assertTrue(c.isEmpty());
			
	}
	@Test
	public void filtrarTestEtiqueta2() throws SQLException {
		filtro.anadirEtiquetaFiltrada(filtro.buscaEtiqueta("D"));
		Collection<Elemento> c=lista.getListaFiltrada();
		assertEquals(2,c.size());
		assertTrue(c.contains(e3));
		assertTrue(c.contains(e4));
		
		filtro.anadirEtiquetaFiltrada(filtro.buscaEtiqueta("E"));
		c=lista.getListaFiltrada();
		assertTrue(c.contains(e4));
			
	}
	@Test
	public void filtrarPeliculasYSeries() {
		e2.setPelicula(true);
		e3.setPelicula(true);
		filtro.setPelicula(true);
		Collection<Elemento> c=lista.getListaFiltrada();
		assertEquals(2,c.size());
		assertTrue(c.contains(e3));
		assertTrue(c.contains(e2));
		filtro.setPelicula(false);
		c=lista.getListaFiltrada();
		assertEquals(2,c.size());
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e4));
		filtro.setPelicula(null);
		c=lista.getListaFiltrada();
		assertEquals(4,c.size());
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e2));
		assertTrue(c.contains(e3));
		assertTrue(c.contains(e4));
	}
	@Test
	public void testfiltroImposible() {
		filtro.anadirEtiquetaFiltrada(filtro.pedirEtiqueta("A"));
		filtro.anadirEtiquetaFiltrada(filtro.pedirEtiqueta("B"));
		filtro.anadirEtiquetaFiltrada(filtro.pedirEtiqueta("C"));
		filtro.anadirEtiquetaFiltrada(filtro.pedirEtiqueta("D"));
		Collection<Elemento> c = lista.getListaFiltrada();
		assertTrue(c.isEmpty());
		Etiqueta e = filtro.pedirEtiqueta("NUEVA");
		Etiqueta e2 = filtro.pedirEtiqueta("NUEVA2");
		assertEquals(0, e.getContador());
		assertEquals(0, e2.getContador());
		List<String> li= new ArrayList<>();
		li.add(e2.getNombre());
		li.add(e.getNombre());
		filtro.setEtiquetas(li);
		c = lista.getListaFiltrada();
		assertTrue(c.isEmpty());
	}
	@Test
	public void testfiltroEtiquetaSinUso() {
		String s[]= {"NUEVA","nUEVA2"};
		assertThrows(IllegalArgumentException.class, ()->filtro.setEtiquetas(Arrays.asList(s)),"Las siguientes etiquetas no están registradas: "+s.toString());
		
	}
	@Test
	public void filtrarTest() throws SQLException {
		filtro.setEstadoBuscado(estadoVisualizacion.PENDIENTE);
		filtro.setFavoritoActivo(true);
		Collection<Elemento> c=lista.getListaFiltrada();
		assertEquals(1,c.size() );
		assertTrue(c.contains(e1));
		filtro.setFavoritoActivo(false);
		c=lista.getListaFiltrada();
		assertEquals(2,c.size() );
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e4));
		filtro.setFragmentoTitulo("A");
		c=lista.getListaFiltrada();
		assertEquals(1,c.size() );
		assertTrue(c.contains(e4));
		filtro.setFragmentoTitulo("C");
		c=lista.getListaFiltrada();
		assertEquals(2,c.size() );
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e4));
		filtro.anadirEtiquetaFiltrada(filtro.buscaEtiqueta("E"));
		c=lista.getListaFiltrada();
		assertEquals(1,c.size() );
		assertTrue(c.contains(e4));
		filtro.borrarEtiquetaFiltrada(filtro.buscaEtiqueta("E"));
		filtro.setFragmentoTitulo(null);
		filtro.setEstadoBuscado(null);
		c=lista.getListaFiltrada();
		assertEquals(4,c.size() );
		assertTrue(c.contains(e1));
		assertTrue(c.contains(e2));
		assertTrue(c.contains(e3));
		assertTrue(c.contains(e4));
	}
	@Test
	public void ordenacionTestAZ() {
		lista.ordenaA_Z();
		Collection<Elemento> c = lista.getListaFiltrada();
		Iterator<Elemento> it=c.iterator();
		assertEquals(4, c.size());
		assertSame(it.next(), e3);
		assertSame(it.next(), e2);
		assertSame(it.next(), e4);
		assertSame(it.next(), e1);
	}
	@Test
	public void ordenacionTestZA() {
		lista.ordenaZ_A();
		Collection<Elemento> c = lista.getListaFiltrada();
		Iterator<Elemento> it=c.iterator();
		assertEquals(4, c.size());
		assertSame(it.next(), e1);
		assertSame(it.next(), e4);
		assertSame(it.next(), e2);
		assertSame(it.next(), e3);
	}
	@Test
	public void ordenacionTestNota() {
		lista.ordenaNota();
		Collection<Elemento> c = lista.getListaFiltrada();
		Iterator<Elemento> it=c.iterator();
		assertEquals(4, c.size());
		assertSame(it.next(), e1);
		assertSame(it.next(), e2);
		assertSame(it.next(), e3);
		assertSame(it.next(), e4);
	}
	
	
}
