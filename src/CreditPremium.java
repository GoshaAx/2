import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class CreditPremium extends Credit{

    {
        this.setProcentAnual(12);
    }

    CreditPremium(Client client) {
        super(client);
    }

    @Override
    void CereCredit() {

        System.out.println("\n=====================================");
        System.out.println("    CERERE CREDIT - DETALII CONTRACT  ");
        System.out.println("=====================================");

        // Alegere valuta
        String valuta = "";
        boolean validValuta = false;
        while (!validValuta) {
            System.out.print("Alegeți valuta (Lei/Euro/Dolari/Roni): ");
            valuta = in.next();
            if (valuta.equalsIgnoreCase("Lei") || valuta.equalsIgnoreCase("Euro") || valuta.equalsIgnoreCase("Dolari") || valuta.equalsIgnoreCase("Roni")) {
                validValuta = true;
            } else {
                System.out.println("\u001B[31mValuta invalidă. Te rugăm să alegi între Lei, Euro, Dolari și Roni.\u001B[37m");
            }
        }
        setValuta(valuta);

        // Suma creditului
        System.out.print("Suma creditului (" + valuta + "): ");
        double sumaCredit = 0;
        boolean validSuma = false;
        while (!validSuma) {
            try {
                sumaCredit = in.nextDouble();
                if (sumaCredit <= 0) {
                    throw new IllegalArgumentException("Suma trebuie să fie mai mare decât zero.");
                }
                validSuma = true;
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mEroare la introducerea sumei.\u001B[37m");
                System.out.print("Introduceți din nou suma: ");
                in.nextLine(); // Consumăm newline-ul rămas în buffer
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[37m");
                System.out.print("Introduceți din nou suma: ");
                in.nextLine(); // Consumăm newline-ul rămas în buffer
            }
        }

        // Termenul creditului
        System.out.print("Termenul creditului (în luni): ");
        int termenCredit = 0;
        boolean validTermen = false;
        while (!validTermen) {
            try {
                termenCredit = in.nextInt();
                if (termenCredit <= 0) {
                    throw new IllegalArgumentException("Termenul trebuie să fie mai mare decât zero.");
                }
                validTermen = true;
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mEroare la introducerea termenului.\u001B[37m");
                System.out.print("Introduceți din nou termenul (în luni): ");
                in.nextLine(); // Consumăm newline-ul rămas în buffer
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[37m");
                System.out.print("Introduceți din nou termenul (în luni): ");
                in.nextLine(); // Consumăm newline-ul rămas în buffer
            }
        }

        // Citirea detaliilor suplimentare din contract
        ContractCredit contract = new ContractCredit();
        Random random = new Random();
        long nrContract = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        contract.setNrCredit(nrContract);
        contract.setSumaCreditului(sumaCredit);
        contract.setTermen(termenCredit);
        contract.setDataIntemeierii(LocalDate.now());
        List<LocalDate> planRambursare = new ArrayList<>();
        contract.setPlanRambursare(planRambursare, termenCredit);

        setContract(contract);

        setProcentLunar(getProcentAnual()/termenCredit);

        System.out.println("\n=============================");
        System.out.println("  Cerere credit finalizată!");
        System.out.println("=============================\n");
    }

    @Override
    double calculRataLunara(double suma, int termen, double procentAnual) {
        double procentLunar = procentAnual / 100 / 12;

        double rataLunaraneto=suma/termen;

        rataLunaraneto+=rataLunaraneto*procentLunar;

        return rataLunaraneto;

    }


    static double ConvertireValuta(String valuta, double suma) {
        double rezultat = suma;

        switch (valuta.toLowerCase()) {
            case "euro":
                rezultat = suma * 19.12;
                break;
            case "dolari":
                rezultat = suma * 17.90;
                break;
            case "roni":
                rezultat = suma * 3.93;
                break;

            default:
                System.out.println("Valuta introdusă nu este suportată pentru conversie în Lei.");
                break;
        }

        return rezultat;
    }



    @Override
    double SumaTotalaReintoarsa(double SumaInitiala, double Procent, int termen) {

        double sumaLunara= calculRataLunara(SumaInitiala, termen, Procent*1.2);
        return sumaLunara*termen+SumaInitiala;
    }




}
