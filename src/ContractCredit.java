import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContractCredit implements Serializable {
    private long nrCredit;
    private double SumaCreditului;
    private int termen;
    private LocalDate DataIntemeierii;
    private List<LocalDate> PlanRambursare;

    public ContractCredit() {
        PlanRambursare = new ArrayList<>();
    }

    public int getTermen() {
        return termen;
    }

    public void setTermen(int termen) {
        this.termen = termen;
    }

    public long getNrCredit() {
        return nrCredit;
    }

    public void setNrCredit(long nrCredit) {
        this.nrCredit = nrCredit;
    }

    public double getSumaCreditului() {
        return SumaCreditului;
    }

    public void setSumaCreditului(double sumaCreditului) {
        SumaCreditului = sumaCreditului;
    }

    public LocalDate getDataIntemeierii() {
        return DataIntemeierii;
    }

    public void setDataIntemeierii(LocalDate dataIntemeierii) {
        DataIntemeierii = dataIntemeierii;
    }

    public List<LocalDate> getPlanRambursare() {
        return PlanRambursare;
    }

    public void setPlanRambursare(List<LocalDate> planRambursare, int termen) {
        PlanRambursare.clear();

        LocalDate dataCurenta = DataIntemeierii.plusMonths(1);

        for (int i = 0; i < termen; i++) {
            PlanRambursare.add(dataCurenta);
            dataCurenta = dataCurenta.plusMonths(1);
        }
    }

    public void citesteContract(Scanner in) {
        System.out.println("\n==============================");
        System.out.println("    CITIRE DATE CONTRACT      ");
        System.out.println("==============================");

        System.out.print("Introduceți suma creditului: ");
        double sumaCreditului = in.nextDouble();

        System.out.print("Introduceți termenul (în luni): ");
        int termen = in.nextInt();
        in.nextLine();

        System.out.print("Introduceți data întemeierii (YYYY-MM-DD): ");
        String data = in.nextLine();
        LocalDate dataIntemeierii = LocalDate.parse(data);

        setSumaCreditului(sumaCreditului);
        setTermen(termen);
        setDataIntemeierii(dataIntemeierii);

        setPlanRambursare(PlanRambursare, termen);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n")
                .append("       DETALII CONTRACT       \n")
                .append("==============================\n")
                .append("Număr Credit: ").append(nrCredit).append('\n')
                .append("Suma Creditului: ").append(SumaCreditului).append('\n')
                .append("Data Întemeierii: ").append(DataIntemeierii.format(formatter)).append('\n')
                .append("Plan de Rambursare:\n");

        for (int i = 0; i < PlanRambursare.size(); i++) {
            sb.append("Rata ").append(i + 1).append(": ").append(PlanRambursare.get(i).format(formatter)).append('\n');
        }

        sb.append("==============================\n");

        return sb.toString();
    }
}
