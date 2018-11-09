package ohtu.ohtuvarasto;

/**
Luokka varasto.
*/
public class Varasto {

    /** --- piilotettu tietorakenteen toteutus: ---
    */
    private double tilavuus;  // paljonko varastoon mahtuu,  > 0
    private double saldo;     // paljonko varastossa on nyt, >= 0

    /** --- konstruktorit: ---
    * @param til on annettu tilavuus
    */
    public Varasto(final double til) {  // tilavuus on annettava
        if (til > 0.0) {
            this.tilavuus = til;
        } else  { // virheellinen, nollataan
            this.tilavuus = 0.0;  // => käyttökelvoton varasto
        }
        saldo = 0;     // oletus: varasto on tyhjä
    }
    /**
    *@param til käyttäjän syöttämä getTilavuus
    *@param alkuSaldo käyttäjän syöttämä saldo
    */
    public Varasto(final double til, final double alkuSaldo) { // kuormitetaan
        if (til > 0.0) {
            this.tilavuus = til;
        } else { // virheellinen, nollataan

            this.tilavuus = 0.0;  // => käyttökelvoton varasto
        }
        if (alkuSaldo < 0.0) {
            this.saldo = 0.0;
        } else if (alkuSaldo <= til) {
            this.saldo = alkuSaldo;
        } else {
            this.saldo = til;  // täyteen ja ylimäärä hukkaan!
        }
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {  // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo; // ei tarvita erillistä kenttää vielaTilaa tms.
    }

    /** --- asettavat aksessorit eli setterit: ---.
    *@param maara paljonko halutaan lisätä varastoon
    *
    */
    public void lisaaVarastoon(final double maara) {
        if (maara < 0) {
            return;       // tällainen pikapoistuminenkin!
        }
        if (maara <= paljonkoMahtuu()) { // omia aksessoreita voi kutsua
            saldo = saldo + maara;          // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    /** Kirjanpito kun varastosta otetaan.
    *@param maara paljonko varastosta otetaan
    */
    public double otaVarastosta(final double maara) {
        if (maara < 0) { // virhetilanteessa voidaan tehdä
            return 0.0;   // tällainen pikapoistuminenkin!
        }
        if (maara > saldo) {          // annetaan mitä voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;               // ja tyhjäksi menee
            return kaikkiMitaVoidaan;  // poistutaan saman tien
        }
        // jos tänne päästään, kaikki pyydetty voidaan antaa
        saldo = saldo - maara;  // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}
