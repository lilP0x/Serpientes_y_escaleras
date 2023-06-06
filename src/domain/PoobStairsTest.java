package domain;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PoobStairsTest {

   @Test
    public void numeroDeCasiilas(){
        SnakesAndStairs juego = new SnakesAndStairs("a", "b", "red", "blue" ,11,3,3,0.2,1);
        Tablero tablero = juego.getTablero();
        assertEquals(121,tablero.getCasillas(0,10));
        assertEquals(1,tablero.getCasillas(10,0));
        assertFalse((2 == tablero.getCasillas(10, 0)));
    }
  
   @Test
   public void jugarTurno(){
       SnakesAndStairs juego = new SnakesAndStairs("a", "b", "red", "blue" ,11,3,3,0.1,0);
       Tablero tablero = juego.getTablero();
       tablero.jugarTurno();
       int turno = tablero.getTurno();
       assertEquals(turno, 1);
       tablero.jugarTurno();
       turno = tablero.getTurno();
       assertEquals(turno, 2);
       tablero.jugarTurno();
       turno = tablero.getTurno();
       assertEquals(turno, 3);
   }
  
   @Test
   public void verificarPvpPve(){
       SnakesAndStairs juego = new SnakesAndStairs("a", "b", "red", "blue" ,11,3,3,0.1,0, true);
       assertEquals(true,juego.getPve());
       SnakesAndStairs juego1 = new SnakesAndStairs("a", "b", "red", "blue" ,11,3,3,0.1,0);
       assertEquals(false,juego1.getPve());

   }
   
   @Test
   public void verificarColorJugador(){
       SnakesAndStairs juego = new SnakesAndStairs("a", "b", "red", "blue" ,11,3,3,0.1,0, true);
       String[] names  = juego.getNames();
       String[] colors = juego.getColors();
       assertEquals("a",names[0]);
       assertEquals("b",names[1]);
       assertEquals("red",colors[0]);
       assertEquals("blue",colors[1]);
   }
 
   @Test
   public void verificarTamaño(){
       SnakesAndStairs juego = new SnakesAndStairs("a", "b", "red", "blue" ,11,3,3,0.1,0, true);
       SnakesAndStairs juego1 = new SnakesAndStairs("a", "b", "red", "blue" ,18,3,3,0.1,0, true);
       Tablero tb = juego.getTablero();
       int width = tb.getWidth();
       Tablero tbb = juego1.getTablero();
       int width1 = tbb.getWidth();
       assertEquals(11, width);
       assertEquals(18, width1);
       assertFalse(8 == width);
       assertFalse(38 == width1);
   }
   
   @Test
   public void numberSnakesAndStairs() {
       SnakesAndStairs juego = new SnakesAndStairs("a", "b", "red", "blue" ,11,3,15,0.1,0, true);
       SnakesAndStairs juego1 = new SnakesAndStairs("a", "b", "red", "blue" ,18,15,3,0.1,0, true); 
       Snake[] s = juego.getSnakesI();
       Stair[] e = juego.getStairI();
       Snake[] s1 = juego1.getSnakesI();
       Stair[] e1 = juego1.getStairI();
       int snakes = s.length, stairs = e.length, snakes1 = s1.length, stairs1 = e1.length;
       
	   assertEquals(3, snakes);
	   assertEquals(15, stairs);
	   assertEquals(15, snakes1);
	   assertEquals(3, stairs1);
   }
   
   @Test
   public void modificadores() {
	   Modifier modAvance = new ModAvance(1);
	   Modifier modRetroceso = new ModRetroceso(2);
	   Tablero tb = new Tablero("a", "b", "red", "blue" ,116,3,3,0,0.1);
	   Fichas ficha_turn = new Fichas("red",tb);
	   Fichas ficha_turn1 = new Fichas("blue",tb);
	   ficha_turn.setPosition(1);
	   ficha_turn1.setPosition(9);
	   modAvance.doAction(ficha_turn);
	   modRetroceso.doAction(ficha_turn1);
	   assertEquals(2, ficha_turn.getPosition());
	   assertFalse(9 == ficha_turn.getPosition());
	   assertEquals(8, ficha_turn1.getPosition());
	   assertFalse(10 == ficha_turn1.getPosition());
   }
   
   @Test
   public void modCambio() {
	   Modifier modCambio = new ModCambio(3); 
	   Tablero tb = new Tablero("a", "b", "red", "blue" ,116,0,0,0,0.1);
	   Fichas ficha_turn = new Fichas("red",tb);
	   tb.jugarTurno();
	   int valor = tb.getValorDado();
	   ficha_turn.setPosition(valor + 1);
	   Fichas ficha = tb.getFicha(0);
	   modCambio.doAction(ficha_turn);
	   assertEquals(1, ficha_turn.getPosition());
	   assertEquals(valor + 1, ficha.getPosition());
	   
   }
   

   
   @Test
   public void lanzarDado() {
	   SnakesAndStairs juego = new SnakesAndStairs("a", "b", "red", "blue" ,11,0,0,0.1,0);
	   juego.jugar();
	   int dado = juego.dado();
	   Tablero tb = juego.getTablero();
	   int po = tb.getFichaPosition1("red");
	   assertEquals(dado+1, po);
	   assertFalse(8 == dado+1);
   }
   
   @Test
   public void moverJugador(){
       SnakesAndStairs juego =  new SnakesAndStairs("a", "b", "red", "blue" ,11,0,0,0.0,0);
       Tablero tb = juego.getTablero();
       for (int i = 0; i < 1; i++){
           tb.jugarTurno();
           int valorDado = tb.getDado(), turno = tb.getTurno(), position;
           if (turno % 2 == 0){
               position = tb.getFichaPosition1("red");    
           }else{
               position = tb.getFichaPosition1("red");
           }
           assertEquals((valorDado + 1),position);
       }
   }
   
   @Test
   public void informacionJuego() {
	   SnakesAndStairs juego =  new SnakesAndStairs("a", "b", "red", "blue" ,11,7,3,0.0,0);
	   
	   
   }
   
   @Test
   public void casillaSaltarina() {
	   Casilla cas = new CasillaSaltarina(1);
	   Fichas ficha = new Fichas("red");
	   cas.action(ficha);
	   assertEquals(6, ficha.getPosition());
	   assertFalse(27 ==  ficha.getPosition());
	   cas.action(ficha);
	   assertEquals(11, ficha.getPosition());
	   assertTrue(11 ==  ficha.getPosition());
	   
   }
   
   @Test
   public void casillaSaltarinaInversa() {
	   Casilla cas = new CasillaSaltariaInversa(2);
	   Fichas ficha = new Fichas("red");
	   ficha.setPosition(17);
	   cas.action(ficha);
	   assertEquals(12, ficha.getPosition());
	   assertFalse(27 ==  ficha.getPosition());
	   cas.action(ficha);
	   assertEquals(7, ficha.getPosition());
	   assertTrue(7 ==  ficha.getPosition());
	   
   }
   
   @Test
   public void casillaMuerte() {
	   Casilla cas = new CasillaMuerte(3);
	   Fichas ficha = new Fichas("red");
	   ficha.setPosition(17);
	   cas.action(ficha);
	   assertEquals(1, ficha.getPosition());
	   ficha.setPosition(21);
	   cas.action(ficha);
	   assertEquals(1, ficha.getPosition());
	   assertTrue(1 ==  ficha.getPosition()); 
   }
   
   
   @Test
   public void casillaAvance() {
	   SnakesAndStairs juego =  new SnakesAndStairs("a", "b", "red", "blue" ,11,0,0,0.0,0);
	   Tablero tb = juego.getTablero();
	   Casilla cas = new CasillaAvance(5, tb);
	   Fichas ficha_turn = new Fichas("red");
	   ficha_turn.setPosition(5);
	   tb.makeStairStatic(10, 25);
	   tb.makeStairStatic(17, 23);
	   cas.action(ficha_turn);
	   assertEquals(10, ficha_turn.getPosition());  
   } 
   
   @Test
   public void casillaRetroceso() {
	   SnakesAndStairs juego =  new SnakesAndStairs("a", "b", "red", "blue" ,11,0,0,0.0,0);
	   Tablero tb = juego.getTablero();
	   Casilla cas = new CasillaRetroceso(33, tb);
	   Fichas ficha_turn = new Fichas("red");
	   ficha_turn.setPosition(33);
	   tb.makeSnakesStatic(10, 25);
	   tb.makeSnakesStatic(17, 40);
	   cas.action(ficha_turn);
	   assertEquals(40, ficha_turn.getPosition());  
	   assertFalse(41 == ficha_turn.getPosition());  
	   
   } 
   
   @Test
   public void win() {
	   boolean win = false;
	   SnakesAndStairs juego =  new SnakesAndStairs("a", "b", "red", "blue" ,11,0,0,0.0,0);
	   Tablero tb = juego.getTablero();
	   while(win != true) {
		 tb.jugarTurno();
		 win = tb.getWin();
		   
	   }
	   assertTrue(true == win);
	   assertEquals(true, win);
   }
   
   @Test
   public void snakes() {
	   SnakesAndStairs juego =  new SnakesAndStairs("a", "b", "red", "blue" ,11,0,0,0.0,0);
	   Tablero tb = juego.getTablero();
	   Snake s = new Snake(tb, 25, 80);
	   Fichas f = new Fichas("red");
	   f.setPosition(80);
	   if (s.getCasillaFinal() == f.getPosition()) {
		   s.action(f);
	   }
	   
	   assertTrue(25 == f.getPosition());
	   assertEquals(25, f.getPosition());
   }
   
   @Test
   public void stairs() {
	   SnakesAndStairs juego =  new SnakesAndStairs("a", "b", "red", "blue" ,11,0,0,0.0,0);
	   Tablero tb = juego.getTablero();
	   Stair s = new Stair(tb, 25, 80);
	   Fichas f = new Fichas("red");
	   f.setPosition(25);
	   if (s.getCasillaInicial() == f.getPosition()) {
		   s.action(f);
	   }
	   
	   assertTrue(80 == f.getPosition());
	   assertEquals(80, f.getPosition());
	   assertFalse(313478 == f.getPosition());
   }
   
   @Test 
   public void StairCambio() {
	   Tablero tb = new Tablero("a", "b", "red", "blue", 0, 0, 10, 0, 0.0);
	   Fichas f = new Fichas("red");
	   f.setPosition(20);
	   tb.makeStairStatic(5, 21);
	   tb.makeStairStatic(34, 25);
	   assertEquals(0, tb.getArraySnakes().length);
	   Stair e = new StairCambio(tb, 20, 30);
	   e.action(f);
	   assertEquals(1, tb.getArraySnakes().length);
	   Snake s = tb.getArraySnakes()[0];
	   int casillaFinal = s.getCasillaFinal(), casillaInicial = s.getCasillaInicial();
	   assertEquals(30, casillaFinal);
	   assertEquals(20, casillaInicial);
	     
   }
   
   @Test
   public void SnakeCambio() {
	   Tablero tb = new Tablero("a", "b", "red", "blue", 0, 0, 10, 0, 0.0);
	   Fichas f = new Fichas("red");
	   f.setPosition(30);
	   tb.makeSnakesStatic(5, 21);
	   tb.makeSnakesStatic(34, 25);
	   assertEquals(0, tb.getArrayStairs().length);
	   Snake s = new SnakeCambio(tb, 20, 30);
	   s.action(f);
	   assertEquals(1, tb.getArrayStairs().length);
	   Stair e = tb.getArrayStairs()[0];
	   int casillaFinal = e.getCasillaFinal(), casillaInicial = e.getCasillaInicial();
	   assertEquals(30, casillaFinal);
	   assertEquals(20, casillaInicial);
	   
   }
   
}
