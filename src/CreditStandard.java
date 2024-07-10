import java.time.LocalDate;
import java.util.*;

public class CreditStandard extends Credit {

    {
        this.setProcentAnual(20);
    }

    CreditStandard(Client client) {
        super(client);
    }


    @Override
    void CereCredit() {
        Scanner in = new Scanner(System.in);

        System.out.println("\n=====================================");
        System.out.println("    CERERE CREDIT - DETALII CONTRACT  ");
        System.out.println("=====================================");

        String valuta = "";
        boolean validValuta = false;
        while (!validValuta) {
            System.out.print("Alegeți valuta (Lei/Euro): ");
            valuta = in.next();
            if (valuta.equalsIgnoreCase("Lei") || valuta.equalsIgnoreCase("Euro")) {
                validValuta = true;
            } else {
                System.out.println("\u001B[31mValuta invalidă. Te rugăm să alegi între Lei și Euro.\u001B[37m");
            }
        }
        setValuta(valuta);


        System.out.print("Suma creditului (" + valuta + "): ");
        double sumaCredit = 0;
        boolean validSuma = false;
        while (!validSuma) {
            try {
                sumaCredit = in.nextDouble();

                if (getClient() instanceof PersoanaFizica) {
                    if (valuta.equalsIgnoreCase("Lei") && sumaCredit > 50000) {
                        throw new IllegalArgumentException("Suma maximă pentru persoane fizice este de 50,000 lei.");
                    } else if (valuta.equalsIgnoreCase("Euro") && sumaCredit > 3000) {
                        throw new IllegalArgumentException("Suma maximă pentru persoane fizice este de 3,000 euro.");
                    }
                }

                if (sumaCredit <= 0) {
                    throw new IllegalArgumentException("Suma trebuie să fie mai mare decât zero.");
                }
                validSuma = true;
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mEroare la introducerea sumei.\u001B[37m");
                System.out.print("Introduceți din nou suma: ");
                in.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[37m");
                System.out.print("Introduceți din nou suma: ");
                in.nextLine();
            }
        }


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
                in.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[37m");
                System.out.print("Introduceți din nou termenul (în luni): ");
                in.nextLine();
            }
        }

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
            default:
                System.out.println("Valuta introdusă nu este suportată pentru conversie în Lei.");
                break;
        }

        return rezultat;
    }



    @Override
    double SumaTotalaReintoarsa(double SumaInitiala,double procent, int termen) {

        double sumaLunara= calculRataLunara(SumaInitiala, termen, procent);
        return sumaLunara*termen+SumaInitiala;
    }



}
