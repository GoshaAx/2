import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class PersoanaJuridica extends Client{

    private String codFiscal, Denumire, NumeAdministrator, PersoanaDeContact;

    public static List<String> tipuriProprietati = new ArrayList<>();

    static{
        tipuriProprietati.add("Proprietăți rezidențiale");
        tipuriProprietati.add("Proprietăți comerciale");
        tipuriProprietati.add("Proprietăți industriale");
        tipuriProprietati.add("Proprietăți pentru uz special");
        tipuriProprietati.add("Terenuri");

        tipuriProprietati.add("Acțiuni");
        tipuriProprietati.add("Obligațiuni");
        tipuriProprietati.add("Instrumente de piață monetară");
        tipuriProprietati.add("Fonduri de investiții");
        tipuriProprietati.add("Derivate financiare");
        tipuriProprietati.add("Proprietăți necorporale");
        tipuriProprietati.add("Numerar și echivalente de numerar");
    }
    private String tipProprietate;

    public String getTipProprietate() {
        return tipProprietate;
    }

    public void setTipProprietate(String tipProprietate) {
        this.tipProprietate = tipProprietate;
    }

    @Override
    public void MotivulCreditarii() {
        System.out.println("\033[1;36mCare este motivul pentru luarea acestui credit?\033[0m");
        System.out.println("1. Investiție");
        System.out.println("2. Grant");
        System.out.println("3. Altele.");

        int optiune;
        try {
            optiune = in.nextInt();
            in.nextLine();

            switch (optiune) {
                case 1:
                    setTipCredit("Investiție");
                    break;
                case 2:
                    setTipCredit("Grant");
                    break;
                case 3:
                    System.out.print("Introduceți motivul creditării: ");
                    setTipCredit(in.nextLine());
                    break;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să alegeți o opțiune validă.");
                    MotivulCreditarii();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Opțiune invalidă. Vă rugăm să introduceți un număr.");
            in.nextLine();
            MotivulCreditarii();
        }
    }

    public String getCodFiscal() {
        return codFiscal;
    }

    public void setCodFiscal(String codFiscal) {
        this.codFiscal = codFiscal;
    }

    public String getDenumire() {
        return Denumire;
    }

    public void setDenumire(String denumire) {
        Denumire = denumire;
    }

    public String getNumeAdministrator() {
        return NumeAdministrator;
    }

    public void setNumeAdministrator(String numeAdministrator) {
        NumeAdministrator = numeAdministrator;
    }

    public String getPersoanaDeContact() {
        return PersoanaDeContact;
    }

    public void setPersoanaDeContact(String persoanaDeContact) {
        PersoanaDeContact = persoanaDeContact;
    }


    private void afișațiMeniul() {
        String meniu = """
                      Alege una din următoarele opțiuni de proprietăți:
                      1. Proprietăți rezidențiale
                      2. Proprietăți comerciale
                      3. Proprietăți industriale
                      4. Proprietăți pentru uz special
                      5. Terenuri
                      6. Acțiuni
                      7. Obligațiuni
                      8. Instrumente de piață monetară
                      9. Fonduri de investiții
                      10. Derivate financiare
                      11. Proprietăți necorporale
                      12. Numerar și echivalente de numerar
                      Introdu numărul opțiunii dorite:""";

        System.out.println(meniu);
    }
    @Override
    void CitesteClient() {
        try {
            System.out.print("\033[1;32mIntroduceți codul fiscal:\033[0m ");
            setCodFiscal(in.nextLine());

            System.out.print("\033[1;32mIntroduceți denumirea:\033[0m ");
            setDenumire(in.nextLine());

            System.out.print("\033[1;32mIntroduceți numele administratorului:\033[0m ");
            setNumeAdministrator(in.nextLine());

            System.out.print("\033[1;32mIntroduceți persoana de contact:\033[0m ");
            setPersoanaDeContact(in.nextLine());

            System.out.print("\033[1;32mAlegeți tipul de proprietate din următoarele opțiuni:\033[0m\n");
            afișațiMeniul();

            int optiune;
            try {
                optiune = in.nextInt();
                in.nextLine();

                if (optiune >= 1 && optiune <= tipuriProprietati.size()) {
                    tipProprietate = tipuriProprietati.get(optiune - 1);
                } else {
                    System.out.println("Opțiune invalidă. Vă rugăm să selectați un număr valid.");
                    CitesteClient();
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opțiune invalidă. Vă rugăm să introduceți un număr.");
                in.nextLine();
                CitesteClient();
                return;
            }

            System.out.print("\033[1;32mIntroduceți adresa:\033[0m ");
            setAdresa(in.nextLine());

            System.out.print("\033[1;32mIntroduceți telefonul:\033[0m ");
            setTelefon(in.nextLine());

            System.out.println("\033[1;32mIntroduceți detaliile filialei:\033[0m");
            filiala = new Filiala();

            MotivulCreditarii();

        } catch (Exception e) {
            System.out.println("A apărut o eroare la citirea datelor. Vă rugăm să încercați din nou.");
            e.printStackTrace();
        }
    }





    @Override
    public String toString() {
        return String.format(
                "\033[1;31mPersoana Juridica:\033[0m\n" +
                        "\033[1;33m  Cod Fiscal:\033[0m %s\n" +
                        "\033[1;33m  Denumire:\033[0m %s\n" +
                        "\033[1;33m  Nume Administrator:\033[0m %s\n" +
                        "\033[1;33m  Persoana de Contact:\033[0m %s\n" +
                        "\033[1;33m  Adresa:\033[0m %s\n" +
                        "\033[1;33m  Telefon:\033[0m %s\n" +
                        "\033[1;33m  Tip Proprietate:\033[0m %s\n%s",
                codFiscal, Denumire, NumeAdministrator, PersoanaDeContact, Adresa, telefon, tipProprietate, super.toString()
        );
    }
}
