import java.io.Serializable;

class Filiala implements Serializable {
    private String Nume, Adresa, Telefon;

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public Filiala(String nume, String adresa, String telefon) {
        Nume = nume;
        Adresa = adresa;
        Telefon = telefon;
    }

    Filiala() {
    }

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    @Override
    public String toString() {
        int frameWidth = 50;
        StringBuilder sb = new StringBuilder();
        sb.append("Filiala").append('\n');
        sb.append("Nume: ").append(Nume).append('\n');
        sb.append("Adresa: ").append(Adresa).append('\n');
        sb.append("Telefon: ").append(Telefon).append('\n');

        String[] lines = sb.toString().split("\n");
        StringBuilder centered = new StringBuilder();
        for (String line : lines) {
            int padding = (frameWidth - line.length()) / 2;
            centered.append(" ".repeat(padding)).append(line).append('\n');
        }

        String border = ANSI_BLUE + "*".repeat(frameWidth) + ANSI_RESET;
        StringBuilder framedText = new StringBuilder(border).append('\n');
        for (String line : centered.toString().split("\n")) {
            String framedLine = ANSI_BLUE + "*" + ANSI_RESET + line + " ".repeat(frameWidth - line.length() - 2) + ANSI_BLUE + "*" + ANSI_RESET;
            framedText.append(framedLine).append('\n');
        }
        framedText.append(border);

        return framedText.toString();
    }



}
