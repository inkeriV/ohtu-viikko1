package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusOnNollaJosAnnettuTilavuusOnNegatiivinen() {
        Varasto v = new Varasto(-1);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
        
        Varasto w = new Varasto(-0.1, 56);
        assertEquals(0.0, w.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusOnAnnettuTilavuusJosSeOnSuurempiKuinNolla(){
        Varasto v = new Varasto(34, 21);
        assertEquals(34, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoksiLaitetaanNollaJosAnnettuSaldoOnNegatiivinen() {
        Varasto v = new Varasto(1, -0.8);
        assertEquals(0.0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoksiLaitetaanAlkuSaldoJosSeOnSuurempiKuinNolla() {
        Varasto v = new Varasto(1, 1);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiLisätäMitäänJosAnnettuSyöteOnNegatiivinen() {
        varasto.lisaaVarastoon(-3);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoLaitetaanTäyteenJaLoputLisättävätPois() {
        varasto.lisaaVarastoon(11);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoOnTilavuusJosAnnettuMääräOnPienempiKuinJäljelläOlevaTila() {
        Varasto v = new Varasto(0.1, 0.3);
        v.lisaaVarastoon(-0.3);
        assertEquals(0.1, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiOtetaMitäänJosAnnettuMääräOnNegatiivinen() {
        assertEquals(0.0, varasto.otaVarastosta(-4.22), vertailuTarkkuus);
        assertEquals(0.0, varasto.otaVarastosta(-0.000001), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaAnnetaanKaikkiJosSieltäHalutaanEnemmänKuinMitäSielläOn() {
        Varasto v = new Varasto(1.001, 1.001);
        Varasto w = new Varasto(23, 24);
        
        //varastoista annetaan kaikki
        assertEquals(1.001, v.otaVarastosta(1.002), vertailuTarkkuus);
        assertEquals(23, w.otaVarastosta(70000), vertailuTarkkuus);
                
        //varastot merkitään tyhjiksi
        assertEquals(0.0, v.getSaldo(), vertailuTarkkuus);
        assertEquals(0.0, w.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toimiikoToString() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0 KAKKA", varasto.toString());
        
        Varasto v = new Varasto(11, 12);
        assertEquals("saldo = 11.0, vielä tilaa 0.0", v.toString());
        
    }
}