import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.*;

import flicksArchive.Conexion;
import flicksArchive.Elemento;
import flicksArchive.Lista;
import flicksArchive.Notificacion;

public class TestFuncionesEstaticas {
	

	@RepeatedTest(value = 100)
	public void testEncriptado() {
		
		String s = "";
		
		Random rnd = new Random();
		for(int i=0;i<20;i++) {
			if(i%2==0) {
				s = s + ((char) (rnd.nextInt(26)+97));
			}else {
				s = s + ((char) (rnd.nextInt(26)+65));
			}
			if(rnd.nextBoolean()) {
				s = s + rnd.nextInt(10);
			}
		}
		
		assertEquals(Conexion.encriptarMD5(s),Conexion.encriptarMD5(s));
		assertNotEquals(Conexion.encriptarMD5(s),Conexion.encriptarMD5(s.toUpperCase()));
		assertNotEquals(Conexion.encriptarMD5(s),Conexion.encriptarMD5(s.toLowerCase()));
		assertNotEquals(Conexion.encriptarMD5(s.toUpperCase()),Conexion.encriptarMD5(s.toLowerCase()));
	}
	
	@Test
	public void testActualizarNotificaciones() {
		List<Notificacion> li = mock(List.class);
		Date today=new Date(System.currentTimeMillis());
		Date monthlater = Date.valueOf(today.toLocalDate().plusMonths(1));
		Date weekago = Date.valueOf(today.toLocalDate().minusWeeks(1));
		Elemento e = new Elemento("CCCCC", today, today, "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		Lista.actualizarNotificaciones(today, e, li);
		verify(li,times(0)).add(any());
		Elemento e0 = new Elemento("CCCCC", today, weekago, "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		Lista.actualizarNotificaciones(today, e0, li);
		verify(li,times(0)).add(any());
		Elemento e01 = new Elemento("CCCCC", Date.valueOf(today.toLocalDate().minusWeeks(2)),today , "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		Lista.actualizarNotificaciones(today, e01, li);
		verify(li,times(0)).add(any());
		Elemento e02 = new Elemento("CCCCC", monthlater,monthlater , "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		Lista.actualizarNotificaciones(today, e02, li);
		verify(li,times(0)).add(any());
		Elemento e1 = new Elemento("CCCCC", today, monthlater, "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		Lista.actualizarNotificaciones(today, e1, li);
		verify(li,times(0)).add(any());
		Elemento e2 = new Elemento("CCCCC", today, Date.valueOf(monthlater.toLocalDate().minusWeeks(1)) , "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		Lista.actualizarNotificaciones(today, e2, li);
		verify(li,times(1)).add(any());
		Elemento e3 = new Elemento("CCCCC", Date.valueOf(weekago.toLocalDate().plusDays(1)), Date.valueOf(monthlater.toLocalDate().minusWeeks(1)) , "Hola que tal", "", 0, "NETFLIX", 20, 0, true, 10, 1);
		Lista.actualizarNotificaciones(today, e3, li);
		verify(li,times(3)).add(any());
		
		
	
	}
}
