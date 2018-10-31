/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author inkeriv
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void palauttaaPelaajanJosSeOnOlemassa() {


        assertEquals("Semenko              EDM  4 + 12 = 16", stats.search("Semenko").toString());
    }
    
    @Test
    public void palauttaaNullJosPelaajaaEiOle() {
        assertEquals(null, stats.search("paskaa"));
    }
    
    @Test
    public void palauttaaListanPelaajista() {
        ArrayList<Player> g = new ArrayList<Player>();
        g.add(new Player("Lemieux", "PIT", 45, 54));

        
        assertEquals("["+g.get(0).toString()+"]", stats.team("PIT").toString());
        
    }
    
    @Test
    public void palauttaaTyhjänListanJosPelaajiaEiOle() {
        ArrayList al = new ArrayList();
        assertEquals(al, stats.team("sfjasf"));
    }
    
    @Test
    public void topScoresToimii() {
        assertEquals("[Gretzky              EDM 35 + 89 = 124]", stats.topScorers(0).toString());
    }
}
