
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

    static Scanner in = new Scanner(System.in);
    static ArrayList<Credit> lista = new ArrayList<>();

    static void AdaugaInFisier() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Credite.ser"))) {
            out.writeObject(lista);
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Datele nu au putut fi inscrise in fisier");
        }
    }

    static void DescarcaDinFisier() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Credite.ser"))) {
            lista = (ArrayList<Credit>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul este gol.");
        } catch (IOException e) {
            System.out.println("Eroare la descarcarea datelor din fisier.");
        } catch (ClassNotFoundException e) {
            System.out.println("Datele din fisier nu coincid cu clasa.");
        }
    }



    static void adaugaPersoanaFizica(Filiala filiala1, Filiala filiala2) {
        PersoanaFizica persoanaFizica = new PersoanaFizica();
        persoanaFizica.CitesteClient();
        persoanaFizica.setFiliala(selecteazaFiliala(filiala1, filiala2));
        System.out.println("Daca dispuneti de mai mult de 50000 de lei, puteti dispune de un credit premium, doriti sa continuati cu oferta PREMIUM?");
        String raspuns=in.next();
        Credit credit;
        if(raspuns.equalsIgnoreCase("Da")){
            credit=new CreditPremium(persoanaFizica);}
        else {
       credit = new CreditStandard(persoanaFizica);}
        credit.CereCredit();
        credit.setClient(persoanaFizica);
        lista.add(credit);
    }

    static void adaugaPersoanaJuridica(Filiala filiala1, Filiala filiala2) {
        PersoanaJuridica persoanaJuridica = new PersoanaJuridica();
        persoanaJuridica.CitesteClient();
        persoanaJuridica.setFiliala(selecteazaFiliala(filiala1, filiala2));
        System.out.println();
        System.out.println("Daca dispuneti de mai mult de 50000 de lei, puteti dispune de un credit premium, doriti sa continuati cu oferta PREMIUM?");
        String raspuns=in.next();
        Credit credit;
        if(raspuns.equalsIgnoreCase("Da")){
            credit=new CreditPremium(persoanaJuridica);}
        else {
            credit = new CreditStandard(persoanaJuridica);}
        credit.CereCredit();
        credit.setClient(persoanaJuridica);
        lista.add(credit);
    }

    static Filiala selecteazaFiliala(Filiala filiala1, Filiala filiala2) {
        System.out.println("\n\033[1;32mAlegeți filiala:\033[0m");
        System.out.println("1. " + filiala1.getNume());
        System.out.println("2. " + filiala2.getNume());
        int optiune = in.nextInt();
        in.nextLine();
        switch (optiune) {
            case 1:
                return filiala1;
            case 2:
                return filiala2;
            default:
                System.out.println("Opțiune invalidă. Alegeți o opțiune validă.");
                return selecteazaFiliala(filiala1, filiala2);
        }
    }

    static void afiseazaListaCredite() {
        if (lista.isEmpty()) {
            System.out.println("Nu exista credite inregistrate.");
        } else {
            for (Credit credit : lista) {
                System.out.println(credit);
            }
        }
    }

    static void CalculeazaSalariu(int optiune) {
        double salariu = 0;
        double venit = 0;
        double profit = 0;
        double procent = 0.07;



            double sumaTotala = 0;
            for (Credit credit : Test.lista) {
                if(!credit.getValuta().equalsIgnoreCase("lei"))
                        sumaTotala+=CreditPremium.ConvertireValuta(credit.getValuta(),credit.getContract().getSumaCreditului());

                else sumaTotala+=credit.getContract().getSumaCreditului();

                venit += credit.SumaTotalaReintoarsa(sumaTotala, credit.getProcentAnual(), credit.getContract().getTermen());
                profit += sumaTotala;
                salariu += sumaTotala * procent;


            }




        profit = venit - profit;

        if (optiune == 4) {
            System.out.printf("\033[1;34mVenitul angajatului în urma perfectării contractelor este de %.2f lei\033[0m\n", salariu);
        }
        if (optiune == 5) {
            System.out.printf("\033[1;34mVenitul curat al băncii în urma acordării contractelor este de %.2f lei\033[0m\n", profit);
        }
    }


    public static void main(String[] args) {
        DescarcaDinFisier();

        Filiala filiala1 = new Filiala("Banca de Economii", "Strada Stefan cel Mare, Nr. 1", "022123456");
        Filiala filiala2 = new Filiala("Moldindconbank", "Strada Stefan cel Mare, Nr. 2", "022654321");



        boolean exit = false;
        while (!exit) {
            System.out.println("\n\033[1;34mMeniu Principal:\033[0m");
            System.out.println("1. Adauga Persoana Fizica");
            System.out.println("2. Adauga Persoana Juridica");
            System.out.println("3. Afiseaza Lista Credite");
            System.out.println("4. Calculeaza Venit Angajat");
            System.out.println("5. Calculeaza Venit Banca");
            System.out.println("6. Salveaza si Iesi");

            int optiune;
            try {
                optiune = in.nextInt();
                in.nextLine();

                switch (optiune) {
                    case 1:
                        adaugaPersoanaFizica(filiala1, filiala2);
                        break;
                    case 2:
                        adaugaPersoanaJuridica(filiala1, filiala2);
                        break;
                    case 3:
                        afiseazaListaCredite();
                        break;
                    case 4:
                        CalculeazaSalariu(4);
                        break;
                    case 5:
                        CalculeazaSalariu(5);
                        break;
                    case 6:
                        AdaugaInFisier();
                        exit = true;
                        break;
                    default:
                        System.out.println("Opțiune invalidă. Vă rugăm să alegeți o opțiune validă.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opțiune invalidă. Vă rugăm să introduceți un număr.");
                in.nextLine();
            }
        }
    }


}


