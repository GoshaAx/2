import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

abstract public class Credit implements Serializable {
    transient Scanner in=new Scanner(System.in);

    Filiala filiala;
    ContractCredit contract;
    Client client;
    private String Valuta;
    private double procentAnual;
    private double procentLunar;

    public Filiala getFiliala() {
        return filiala;
    }

    public void setFiliala(Filiala filiala) {
        this.filiala = filiala;
    }

    public double getProcentLunar() {
        return procentLunar;
    }

    public void setProcentLunar(double procentLunar) {
        this.procentLunar=procentLunar;
    }

    abstract void CereCredit();

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }



    abstract double calculRataLunara(double suma, int termen,double procentAnual);

    static double ConvertireValuta(String valuta, double suma) {
        return 0;
    }

    abstract double SumaTotalaReintoarsa(double SumaInitiala,double Procent,int termen);


    public ContractCredit getContract() {
        return contract;
    }

    public void setContract(ContractCredit contract) {
        this.contract = contract;
    }



    public String getValuta() {
        return Valuta;
    }

    public void setValuta(String valuta) {
        Valuta = valuta;
    }

    public double getProcentAnual() {
        return procentAnual;
    }

    public void setProcentAnual(double procentAnual) {
        this.procentAnual = procentAnual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n")
                .append("         DETALII CREDIT         \n")
                .append("==============================\n")
                .append("Client: ").append(client != null ? client.toString() : "N/A").append('\n')
                .append("Valuta: ").append(getValuta()).append('\n')
                .append("Procent Anual: ").append(getProcentAnual()).append('%').append('\n')
                .append("Procent Lunar: ").append(getProcentLunar()).append('%').append('\n')
                .append("Contract: ").append(contract != null ? contract.toString() : "N/A").append('\n')
                .append("Rata Lunară: ").append(String.format("%.2f %s", calculRataLunara(contract.getSumaCreditului(), contract.getTermen(), getProcentAnual()), getValuta())).append('\n')
                .append("Suma Totală de Reîntoarcere: ").append(String.format("%.2f %s", SumaTotalaReintoarsa(contract.getSumaCreditului(), getProcentAnual(), contract.getTermen()), getValuta())).append('\n')
                .append("==============================\n");
        return sb.toString();
    }

    Credit(Client client){
        this.client=client;
    }
}
