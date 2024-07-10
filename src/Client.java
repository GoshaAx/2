import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

interface FormularCreditare{
    void MotivulCreditarii();

}

abstract class Client implements Serializable, FormularCreditare {
    transient Scanner in=new Scanner(System.in);
        protected String Adresa,telefon, tipCredit;

        protected Filiala filiala;



    public String getTipCredit() {
        return tipCredit;
    }

    public void setTipCredit(String tipCredit) {
        this.tipCredit = tipCredit;
    }

    abstract void CitesteClient();


    public Filiala getFiliala() {
        return filiala;
    }

    public void setFiliala(Filiala filiala) {
        this.filiala = filiala;
    }


    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return String.format(
                "\033[1;34mClient:\033[0m\n" +
                        "\033[1;33m  Adresa:\033[0m %s\n" +
                        "\033[1;33m  Telefon:\033[0m %s\n" +
                        "\033[1;33m  Filiala:\033[0m %s\n" +
                        "\033[1;33m  Tip Credit:\033[0m %s\n",
                Adresa, telefon, filiala, tipCredit
        );
    }



}
