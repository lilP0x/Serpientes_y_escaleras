package domain;
/**
 * Write a description of interface Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Item
{
    void action(Fichas ficha_turn);
    
    void comportamiento(Fichas ficha_turn);
}
