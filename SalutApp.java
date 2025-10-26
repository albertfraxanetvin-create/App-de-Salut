import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class SalutApp {

    public static void main(String[] args) {
        new SalutApp().iniciar();
    }
    class DadesPersona {
        String nom = "";
        int edat = 0;
        double pes = 0;
        double alcada = 0;
    }
    int iniciar() {
        Scanner scanner = new Scanner(System.in);
        DadesPersona dades = new DadesPersona();
        int opcio = 0;
        while (opcio != 4) {
            opcio = mostrarMenu(scanner);
            if (opcio == 1) {
                dades = introduirDades(scanner, dades);
            } else if (opcio == 2) {
                dades = modificarDades(scanner, dades);
            } else if (opcio == 3) {
                mostrarDades(dades);
            } else if (opcio == 4) {
                System.out.println("Sortint del programa...");
            }
        }
        scanner.close();
        return 0;
    }
    int mostrarMenu(Scanner scanner) {
        while (true) {
            System.out.println("Menú");
            System.out.println("1. Introduir dades");
            System.out.println("2. Modificar dades");
            System.out.println("3. Visualitzar dades");
            System.out.println("4. Sortir");
            System.out.print("Selecciona una opció: ");
            if (scanner.hasNextInt()) {
                int opcio = scanner.nextInt();
                scanner.nextLine(); // neteja buffer
                if (opcio >= 1 && opcio <= 4) {
                    return opcio;
                } else {
                    System.out.println("Opció no vàlida.");
                }
            } else {
                System.out.println("Opció no vàlida.");
                scanner.nextLine(); // descarta entrada incorrecta
            }
        }
    }
    DadesPersona introduirDades(Scanner scanner, DadesPersona dades) {
        System.out.print("Nom complet: ");
        String nom = scanner.nextLine();
        if (nom.trim().isEmpty()) {
            System.out.println("Error: El nom no pot ser buit.");
            return dades;
        }
        System.out.print("Edat: ");
        if (scanner.hasNextInt()) {
            int edat = scanner.nextInt();
            scanner.nextLine();
            if (edat <= 0 || edat > 120) {
                System.out.println("Error: Edat no vàlida.");
                return dades;
            }
            dades.edat = edat;
        } else {
            System.out.println("Error: Valor no vàlid per a l'edat.");
            scanner.nextLine();
            return dades;
        }
        System.out.print("Pes (kg): ");
        if (scanner.hasNextDouble()) {
            double pes = scanner.nextDouble();
            scanner.nextLine();
            if (pes <= 0 || pes > 400) {
                System.out.println("Error: Pes no vàlid.");
                return dades;
            }
            dades.pes = pes;
        } else {
            System.out.println("Error: Valor no vàlid per al pes.");
            scanner.nextLine();
            return dades;
        }
        System.out.print("Alçada (m): ");
        if (scanner.hasNextDouble()) {
            double alcada = scanner.nextDouble();
            scanner.nextLine();
            if (alcada <= 0.5 || alcada > 2.5) {
                System.out.println("Error: Alçada no vàlida.");
                return dades;
            }
            dades.alcada = alcada;
        } else {
            System.out.println("Error: Valor no vàlid per a l'alçada.");
            scanner.nextLine();
            return dades;
        }
        dades.nom = nom;
        System.out.println("Dades introduïdes correctament.");
        return dades;
    }
    DadesPersona modificarDades(Scanner scanner, DadesPersona dades) {
        if (dades.nom.trim().isEmpty()) {
            System.out.println("No hi ha dades a modificar.");
            return dades;
        }
        System.out.println("1. Nom (" + dades.nom + ")");
        System.out.println("2. Edat (" + dades.edat + ")");
        System.out.println("3. Pes (" + dades.pes + ")");
        System.out.println("4. Alçada (" + dades.alcada + ")");
        System.out.println("5. Tornar");
        System.out.print("Opció: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Opció no vàlida.");
            scanner.nextLine();
            return dades;
        }
        int opcio = scanner.nextInt();
        scanner.nextLine();
        switch (opcio) {
            case 1:
                System.out.print("Nou nom: ");
                String nomNou = scanner.nextLine();
                if (!nomNou.trim().isEmpty()) {
                    dades.nom = nomNou;
                    System.out.println("Nom modificat.");
                } else {
                    System.out.println("Nom no vàlid.");
                }
                break;
            case 2:
                System.out.print("Nova edat: ");
                if (scanner.hasNextInt()) {
                    int novaEdat = scanner.nextInt();
                    scanner.nextLine();
                    if (novaEdat > 0 && novaEdat <= 120) {
                        dades.edat = novaEdat;
                        System.out.println("Edat modificada.");
                    } else {
                        System.out.println("Edat no vàlida.");
                    }
                } else {
                    System.out.println("Error de format.");
                    scanner.nextLine();
                }
                break;
            case 3:
                System.out.print("Nou pes: ");
                if (scanner.hasNextDouble()) {
                    double nouPes = scanner.nextDouble();
                    scanner.nextLine();
                    if (nouPes > 0 && nouPes <= 400) {
                        dades.pes = nouPes;
                        System.out.println("Pes modificat.");
                    } else {
                        System.out.println("Pes no vàlid.");
                    }
                } else {
                    System.out.println("Error de format.");
                    scanner.nextLine();
                }
                break;
            case 4:
                System.out.print("Nova alçada: ");
                if (scanner.hasNextDouble()) {
                    double novaAlcada = scanner.nextDouble();
                    scanner.nextLine();
                    if (novaAlcada > 0.5 && novaAlcada <= 2.5) {
                        dades.alcada = novaAlcada;
                        System.out.println("Alçada modificada.");
                    } else {
                        System.out.println("Alçada no vàlida.");
                    }
                } else {
                    System.out.println("Error de format.");
                    scanner.nextLine();
                }
                break;

            default:
                System.out.println("Tornant al menú...");
        }
        return dades;
    }
    int mostrarDades(DadesPersona dades) {
        if (dades.nom.trim().isEmpty()) {
            System.out.println("No hi ha dades per mostrar.");
            return 1;
        }
        String nomFormatat = formatarNom(dades.nom);
        double imc = dades.pes / (dades.alcada * dades.alcada);
        String categoria;
        if (imc < 18.5) {
            categoria = "Baix pes";
        } else if (imc < 25) {
            categoria = "Pes normal";
        } else if (imc < 30) {
            categoria = "Sobrepès";
        } else {
            categoria = "Obesitat";
        }
        int fCMax = 220 - dades.edat;
        int fCMin = (int) (fCMax * 0.5);
        int fCAlt = (int) (fCMax * 0.85);
        double aigua = dades.pes * 35 / 1000.0;
        int anyNaixement = java.time.Year.now().getValue() - dades.edat;
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
            if (nom == null) throw new IllegalArgumentException("El nom no pot ser null");
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