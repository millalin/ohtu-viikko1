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
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10,2);
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
    public void varastoonEiVoiLaittaaYliMaksimin() {
        varasto.lisaaVarastoon(11);


        // varastoon mahtuu vain 10 eli 1 hukkaan ja täynnä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaYritetaanOttaaYliSaldon() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);

        //otetaan 5 ja jäljelle ei jää mitään 
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ilmoittaaSaldonjaTilanOikein() {
        varasto.lisaaVarastoon(5);
        
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

    @Test
    public void varastolleAsetettuAlkuSaldoOnOikein() {
        
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void nollaVarastoonEiVoiLisata() {
        varasto = new Varasto(0);
        varasto.lisaaVarastoon(5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void nollaVarastoonJolleAlkusaldoNollaEiVoiLisata() {
        varasto = new Varasto(0,0);
        varasto.lisaaVarastoon(5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void varastoonEiVoiLisataNegMaaraa() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-2);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonJollaAlkusaldoEiVoiLisataNegMaaraa() {
        varasto2.lisaaVarastoon(-2);

        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kunVarastonAlkusaldoOnNegOnSaldoNolla() {
        varasto = new Varasto(5,-2);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kunVarastonAlkusaldoYliTilavuudenOnSaldoMaksimitilavuus() {
        varasto = new Varasto(10,11);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kunVarastostaYritetäänOttaaNegEiOtetaMitaan() {
        varasto = new Varasto(10,5);
        varasto.otaVarastosta(-2);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }


}