import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Curso {

    private final Estudiante[] estudiantes;
    private final double[][] notas;
    private final int cantidadCortes;
    private final Scanner teclado;

    public Curso(int n, int m, Scanner teclado) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException(
                    "Las cantidades deben ser mayores que cero."
            );
        }

        estudiantes = new Estudiante[n];
        notas = new double[n][m];
        cantidadCortes = m;
        this.teclado = teclado;
    }

    private String formato(double numero) {
        DecimalFormatSymbols simbolos =
                new DecimalFormatSymbols(new Locale("es", "CO"));

        DecimalFormat decimal = new DecimalFormat(
                "0.0", simbolos
        );

        return decimal.format(numero);
    }

    private double leerNota(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            String entrada = teclado.nextLine()
                    .trim()
                    .replace(',', '.');

            try {
                double nota = Double.parseDouble(entrada);

                if (Double.isNaN(nota) || Double.isInfinite(nota)
                        || nota < 0.0 || nota > 5.0) {
                    System.out.println(
                            "ERROR: la nota debe estar entre 0,0 y 5,0."
                    );
                    continue;
                }

                return nota;

            } catch (NumberFormatException e) {
                System.out.println(
                        "ERROR: escriba una nota valida, por ejemplo 4,5."
                );
            }
        }
    }

    public void registrarEstudiantes() {
        for (int i = 0; i < estudiantes.length; i++) {
            String nombre;

            do {
                System.out.print(
                        "Nombre del estudiante " + (i + 1) + ": "
                );

                nombre = teclado.nextLine().trim();

                if (nombre.isEmpty()) {
                    System.out.println(
                            "El nombre no puede estar vacio."
                    );
                }
            } while (nombre.isEmpty());

            estudiantes[i] =
                    new Estudiante(nombre, cantidadCortes);
        }
    }

    public void registrarNotas() {
        for (int i = 0; i < estudiantes.length; i++) {
            System.out.println(
                    "\nNotas de " + estudiantes[i].getNombre()
            );

            for (int j = 0; j < cantidadCortes; j++) {
                double nota = leerNota(
                        "Nota del corte " + (j + 1)
                                + " (0,0 a 5,0): "
                );

                estudiantes[i].registrarNota(j, nota);
                notas[i][j] = nota;
            }
        }
    }

    public void cargarEjemplo() {
        String[] nombres = {
                "Elena Gilbert",
                "Stefan Salvatore",
                "Damon Salvatore",
                "Bonnie Bennett",
                "Caroline Forbes",
                "Matt Donovan",
                "Jeremy Gilbert",
                "Alaric Saltzman",
                "Katherine Pierce",
                "Niklaus Mikaelson",
                "Elijah Mikaelson",
                "Lorenzo St. John",
                "Sofia Martinez",
                "Daniel Garcia",
                "Sofia Ramirez",
                "Daniel Lopez",
                "Valentina Torres",
                "Samuel Ramirez",
                "Isabella Castro",
                "Mateo Herrera",
                "Camila Vargas",
                "Juan Perez",
                "Laura Gomez",
                "Andres Sanchez",
                "Mariana Diaz",
                "Sebastian Munoz",
                "Natalia Restrepo",
                "David Cardenas",
                "Sofia Torres",
                "Daniel Martinez"
        };

        double[][] ejemplo = {
                {4.5, 4.7, 4.8},
                {3.8, 4.0, 4.2},
                {2.5, 2.8, 3.0},
                {4.8, 4.9, 5.0},
                {4.2, 4.4, 4.5},
                {2.0, 2.5, 2.8},
                {3.2, 3.5, 3.4},
                {4.0, 3.8, 4.2},
                {2.8, 3.0, 3.2},
                {3.5, 3.7, 3.9},
                {4.6, 4.8, 4.7},
                {3.0, 3.2, 3.4},
                {4.0, 4.1, 4.3},
                {2.2, 2.5, 2.7},
                {4.8, 4.7, 4.9},
                {3.6, 3.8, 4.0},
                {4.1, 4.2, 4.4},
                {2.7, 2.8, 2.9},
                {3.3, 3.5, 3.7},
                {4.5, 4.6, 4.8},
                {3.0, 3.1, 3.2},
                {2.0, 2.2, 2.5},
                {4.7, 4.8, 4.9},
                {3.8, 3.9, 4.0},
                {4.2, 4.4, 4.3},
                {2.5, 2.7, 2.8},
                {3.4, 3.6, 3.8},
                {4.9, 4.8, 5.0},
                {3.1, 3.3, 3.5},
                {4.0, 4.2, 4.1}
        };

        for (int i = 0; i < estudiantes.length; i++) {
            estudiantes[i] =
                    new Estudiante(nombres[i % nombres.length],
                            cantidadCortes);

            for (int j = 0; j < cantidadCortes; j++) {
                double nota;

                if (j < 3) {
                    nota = ejemplo[i % ejemplo.length][j];
                } else {
                    nota = 2.5
                            + ((i * 2 + j * 3) % 26) / 10.0;
                }

                estudiantes[i].registrarNota(j, nota);
                notas[i][j] = nota;
            }
        }
    }

    public double[] calcularPromediosPorEstudiante() {
        double[] promedios = new double[estudiantes.length];

        for (int i = 0; i < estudiantes.length; i++) {
            promedios[i] = estudiantes[i].calcularPromedio();
        }

        return promedios;
    }

    public double[] calcularPromediosPorCorte() {
        double[] promedios = new double[cantidadCortes];

        for (int j = 0; j < cantidadCortes; j++) {
            double suma = 0.0;

            for (int i = 0; i < estudiantes.length; i++) {
                suma += notas[i][j];
            }

            promedios[j] = suma / estudiantes.length;
        }

        return promedios;
    }

    public void mostrarTablaNotas() {
        System.out.println("\n========== TABLA DE NOTAS ==========");
        System.out.printf("%-24s", "ESTUDIANTE");

        for (int j = 0; j < cantidadCortes; j++) {
            System.out.printf("%10s", "Corte " + (j + 1));
        }

        System.out.println();

        for (int i = 0; i < estudiantes.length; i++) {
            System.out.printf(
                    "%-24s", estudiantes[i].getNombre()
            );

            for (int j = 0; j < cantidadCortes; j++) {
                System.out.printf(
                        "%10s", formato(notas[i][j])
                );
            }

            System.out.println();
        }
    }

    public void mostrarPromedios() {
        double[] promedios = calcularPromediosPorEstudiante();
        double[] cortes = calcularPromediosPorCorte();

        System.out.println("\n===== PROMEDIOS POR ESTUDIANTE =====");

        for (int i = 0; i < estudiantes.length; i++) {
            System.out.println(
                    estudiantes[i].getNombre() + ": "
                            + formato(promedios[i])
            );
        }

        System.out.println("\n===== PROMEDIOS POR CORTE =====");

        for (int j = 0; j < cortes.length; j++) {
            System.out.println(
                    "Corte " + (j + 1) + ": " + formato(cortes[j])
            );
        }
    }

    public void mostrarClasificacion() {
        int aprobados = 0;
        int reprobados = 0;
        int riesgo = 0;
        int destacados = 0;

        System.out.println("\n===== CLASIFICACION =====");

        for (Estudiante estudiante : estudiantes) {
            double promedio = estudiante.calcularPromedio();

            if (promedio >= 3.0) {
                aprobados++;
                System.out.println(
                        "APROBADO: " + estudiante.getNombre()
                                + " - " + formato(promedio)
                );
            } else {
                reprobados++;
                System.out.println(
                        "REPROBADO: " + estudiante.getNombre()
                                + " - " + formato(promedio)
                );
            }

            if (promedio >= 2.0 && promedio < 3.0) {
                riesgo++;
            }

            if (promedio >= 4.5) {
                destacados++;
            }
        }

        System.out.println("\nTotal aprobados: " + aprobados);
        System.out.println("Total reprobados: " + reprobados);
        System.out.println("En riesgo: " + riesgo);
        System.out.println("Destacados: " + destacados);
    }

    public void mostrarMayorYMenorNota() {
        double mayor = notas[0][0];
        double menor = notas[0][0];
        String nombreMayor = estudiantes[0].getNombre();
        String nombreMenor = estudiantes[0].getNombre();

        for (int i = 0; i < estudiantes.length; i++) {
            for (int j = 0; j < cantidadCortes; j++) {
                if (notas[i][j] > mayor) {
                    mayor = notas[i][j];
                    nombreMayor = estudiantes[i].getNombre();
                }

                if (notas[i][j] < menor) {
                    menor = notas[i][j];
                    nombreMenor = estudiantes[i].getNombre();
                }
            }
        }

        System.out.println("\n===== NOTAS EXTREMAS =====");
        System.out.println(
                "Nota mayor: " + formato(mayor)
                        + " - " + nombreMayor
        );
        System.out.println(
                "Nota menor: " + formato(menor)
                        + " - " + nombreMenor
        );
    }

    public void ordenarPorPromedio() {
        for (int i = 0; i < estudiantes.length - 1; i++) {
            for (int j = 0; j < estudiantes.length - 1 - i; j++) {
                if (estudiantes[j].calcularPromedio()
                        > estudiantes[j + 1].calcularPromedio()) {

                    Estudiante temporal = estudiantes[j];
                    estudiantes[j] = estudiantes[j + 1];
                    estudiantes[j + 1] = temporal;

                    double[] filaTemporal = notas[j];
                    notas[j] = notas[j + 1];
                    notas[j + 1] = filaTemporal;
                }
            }
        }

        System.out.println("\n===== ORDEN DE MENOR A MAYOR =====");

        for (Estudiante estudiante : estudiantes) {
            System.out.println(
                    estudiante.getNombre() + ": "
                            + formato(estudiante.calcularPromedio())
            );
        }
    }

    public void buscarEstudiantePorNombre(String nombre) {
        String buscado = normalizar(nombre);
        int encontrados = 0;

        System.out.println("\n===== RESULTADOS DE BUSQUEDA =====");

        for (Estudiante estudiante : estudiantes) {
            if (normalizar(estudiante.getNombre()).contains(buscado)) {
                estudiante.mostrarInfo();
                encontrados++;
            }
        }

        if (encontrados == 0) {
            System.out.println("No se encontraron estudiantes.");
        } else {
            System.out.println(
                    "\nCantidad de coincidencias: " + encontrados
            );
        }
    }

    private String normalizar(String texto) {
        return java.text.Normalizer.normalize(
                texto.toLowerCase(new Locale("es", "CO")),
                java.text.Normalizer.Form.NFD
        ).replaceAll("\\p{M}", "").trim();
    }

    public void mostrarResumenCurso() {
        mostrarTablaNotas();
        mostrarPromedios();
        mostrarClasificacion();
        mostrarMayorYMenorNota();
    }
}


