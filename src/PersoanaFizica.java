import java.util.ArrayList;

public class PersoanaFizica extends Client{
        private String nume;
        private long codPersonal;



        @Override
        public void MotivulCreditarii() {
                System.out.println("\033[1;36mCare este motivul creditarii?\033[0m");
                System.out.println("1. Vacanta.");
                System.out.println("2. Bunuri materiale.");
                System.out.println("3. Achizitioarea unui automobil / locuinte.");
                System.out.println("4. Altele.");

                int optiune = in.nextInt();
                in.nextLine();

                switch (optiune) {
                        case 1:
                                setTipCredit("Vacanta");
                                break;
                        case 2:
                                setTipCredit("Bunuri materiale");
                                break;
                        case 3:
                                setTipCredit("Achizitioarea unui automobil / locuinte");
                                break;
                        case 4:
                                System.out.print("Introduceți motivul creditării: ");
                                setTipCredit(in.nextLine());
                                break;
                        default:
                                System.out.println("Opțiune invalidă. Vă rugăm să alegeți o opțiune validă.");
                                MotivulCreditarii();
                                break;
                }
        }



        public String getNume() {
                return nume;
        }

        public void setNume(String nume) {
                this.nume = nume;
        }

        public long getCodPersonal() {
                return codPersonal;
        }

        public void setCodPersonal(long codPersonal) {
                this.codPersonal = codPersonal;
        }

        @Override
        void CitesteClient() {
                System.out.print("\033[1;32mIntroduceți numele:\033[0m ");
                setNume(in.nextLine());

                System.out.print("\033[1;32mIntroduceți codul personal:\033[0m ");
                setCodPersonal(in.nextLong());
                in.nextLine();

                System.out.print("\033[1;32mIntroduceți adresa:\033[0m ");
                setAdresa(in.nextLine());

                System.out.print("\033[1;32mIntroduceți telefonul:\033[0m ");
                setTelefon(in.nextLine());

                System.out.println("\033[1;32mIntroduceți detaliile filialei:\033[0m");
                filiala = new Filiala();

                MotivulCreditarii();
        }


        @Override
        public String toString() {
                return String.format(
                        "\033[1;31mPersoana Fizica:\033[0m\n" +
                                "\033[1;33m  Nume:\033[0m %s\n" +
                                "\033[1;33m  Cod Personal:\033[0m %d\n%s",
                        nume, codPersonal, super.toString()
                );
        }
}
