import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class SalutApp {

    public static void main(String[] args) {
        new SalutApp().iniciar();
    }
    class DadesPersona {
        String nom="";
        int edat=0;
        double pes=0;
        double alcada=0;
    }
    int iniciar() {
        Scanner scanner = new Scanner(System.in);
        DadesPersona dades = new DadesPersona();
        int opcio=0;
        while (opcio != 4) {
            opcio = mostrarMenu(scanner);
            if (opcio == 1) {
                dades=introduirDades(scanner, dades);
            } else if (opcio == 2) {
                dades=modificarDades(scanner, dades);
            } else if (opcio == 3) {
                mostrarDades(dades);
            } else if (opcio == 4) {
                System.out.println("Sortint del programa...");
            }
        }
        return 0;
    }
    int mostrarMenu(Scanner scanner) {
        while (true) {
            System.out.println("Menú " );
            System.out.println("1. Introduir dades");
            System.out.println("2. Modificar dades");
            System.out.println("3. Visualitzar dades");
            System.out.println("4. Sortir");
            System.out.print("Selecciona una opció: ");
            String linia=scanner.nextLine();
            try {
                int opcio=Integer.parseInt(linia);
                if (opcio >= 1 && opcio <= 4) {
                    return opcio;
                } else {
                    System.out.println("Opció no vàlida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opció no vàlida.");
            }
        }
    }
    DadesPersona introduirDades(Scanner scanner, DadesPersona dades) {
        System.out.print("Nom complet: ");
        String nom=scanner.nextLine();
        if (nom.equals("") || nom.replace(" ", "").equals("")) {
            System.out.println("Error: El nom no pot ser buit.");
            return dades;
        }
        System.out.print("Edat: ");
        try {
            int edat=Integer.parseInt(scanner.nextLine());
            if (edat <= 0 || edat > 120) {
                System.out.println("Error: Edat no vàlida.");
                return dades;
            }
            dades.edat=edat;
        } catch (Exception e) {
            System.out.println("Error de format.");
            return dades;
        }
        System.out.print("Pes (kg): ");
        try {
            String pesString=scanner.nextLine().replace(',', '.');
            double pes=Double.parseDouble(pesString);
            if (pes <= 0 || pes > 400) {
                System.out.println("Error: Pes no vàlid.");
                return dades;
            }
            dades.pes = pes;
        } catch (Exception e) {
            System.out.println("Error de format.");
            return dades;
        }
        System.out.print("Alçada (m): ");
        try {
            String alcadaString=scanner.nextLine().replace(',', '.');
            double alcada=Double.parseDouble(alcadaString);
            if (alcada <= 0.5 || alcada > 2.5) {
                System.out.println("Error: Alçada no vàlida.");
                return dades;
            }
            dades.alcada=alcada;
        } catch (Exception e) {
            System.out.println("Error de format.");
            return dades;
        }
        dades.nom=nom;
        System.out.println("Dades introduïdes correctament.");
        return dades;
    }
    DadesPersona modificarDades(Scanner scanner, DadesPersona dades) {
        if (dades.nom.equals("") || dades.nom.replace(" ", "").equals("")) {
            System.out.println("No hi ha dades a modificar.");
            return dades;
        }
        System.out.println("1. Nom (" + dades.nom + ")");
        System.out.println("2. Edat (" + dades.edat + ")");
        System.out.println("3. Pes (" + dades.pes + ")");
        System.out.println("4. Alçada (" + dades.alcada + ")");
        System.out.println("5. Tornar");
        System.out.print("Opció: ");
        String linia=scanner.nextLine();
        int opcio= -1;
        try {
            opcio=Integer.parseInt(linia);
        } catch (NumberFormatException e) {
            System.out.println("Opció no vàlida.");
            return dades;
        }
        if (opcio == 1) {
            System.out.print("Nou nom: ");
            String nomNou=scanner.nextLine();
            if (!nomNou.equals("") && !nomNou.replace(" ", "").equals("")) {
                dades.nom=nomNou;
                System.out.println("Nom modificat.");
            } else {
                System.out.println("Nom no vàlid.");
            }
        } else if (opcio == 2) {
            System.out.print("Nova edat: ");
            try {
                int edat=Integer.parseInt(scanner.nextLine());
                if (edat > 0 && edat <= 120) {
                    dades.edat= edat;
                    System.out.println("Edat modificada.");
                } else {
                    System.out.println("Edat no vàlida.");
                }
            } catch (Exception e) {
                System.out.println("Error de format.");
            }
        } else if (opcio == 3) {
            System.out.print("Nou pes: ");
            try {
                String pesStr=scanner.nextLine().replace(',', '.');
                double pes=Double.parseDouble(pesStr);
                if (pes > 0 && pes <= 400) {
                    dades.pes = pes;
                    System.out.println("Pes modificat.");
                } else {
                    System.out.println("Pes no vàlid.");
                }
            } catch (Exception e) {
                System.out.println("Error de format.");
            }
        } else if (opcio == 4) {
            System.out.print("Nova alçada: ");
            try {
                String alcadaString= scanner.nextLine().replace(',', '.');
                double alcada = Double.parseDouble(alcadaString);
                if (alcada > 0.5 && alcada < 2.5) {
                    dades.alcada= alcada;
                    System.out.println("Alçada modificada.");
                } else {
                    System.out.println("Alçada no vàlida.");
                }
            } catch (Exception e) {
                System.out.println("Error de format.");
            }
        } else {
            System.out.println("Tornant al menú...");
        }
        return dades;
    }
    int mostrarDades(DadesPersona dades) {
        if (dades.nom.equals("") || dades.nom.replace(" ", "").equals("")) {
            System.out.println("No hi ha dades per mostrar.");
            return 1;
        }
        String nomFormatat = formatarNom(dades.nom);
        double imc=dades.pes / (dades.alcada * dades.alcada);
        String categoria;
        if (imc < 18.5) {
            categoria= "Baix pes";
        } else if (imc < 25) {
            categoria= "Pes normal";
        } else if (imc < 30) {
            categoria= "Sobrepès";
        } else {
            categoria= "Obesitat";
        }
        int fCMax= 220 - dades.edat;
        int fCMin= (int) (fCMax * 0.5);
        int fCAlt= (int) (fCMax * 0.85);
        double aigua= dades.pes * 35 / 1000.0;
        int anyNaixement= java.time.Year.now().getValue() - dades.edat;
        System.out.println("Hola, " + nomFormatat);
        System.out.println("Edat: " + dades.edat + " anys");
        System.out.println("Pes: " + dades.pes + " kg");
        System.out.println("Alçada: " + dades.alcada + " m");
        System.out.printf("IMC: %.2f (%s)%n", imc, categoria);
        System.out.println("FC màxima: " + fCMax + " bpm");
        System.out.println("Zona objectiu: " + fCMin + " - " + fCAlt + " bpm");
        System.out.printf("Aigua recomanada: %.2f L/dia%n", aigua);
        System.out.println("Any de naixement: " + anyNaixement);
        return 0;
    }
  String formatarNom(String nom) {
    try {
        if (nom == null) {
            throw new IllegalArgumentException("El nom no pot ser null");
        }
        return Arrays.stream(nom.split(" "))
            .filter(part -> !part.isEmpty())
            .map(part -> Character.toUpperCase(part.charAt(0)) +
                    (part.length() > 1 ? part.substring(1).toLowerCase() : ""))
            .collect(Collectors.joining(" "));
    } catch (Exception e) {
        System.err.println("Error al formatar el nom: " + e.getMessage());
        return ""; 
    }
}
    }
