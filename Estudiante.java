import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Estudiante {

    private final String nombre;
    private final double[] notas;

    public Estudiante(String nombre, int cantidadCortes) {
        this.nombre = nombre;
        this.notas = new double[cantidadCortes];
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadCortes() {
        return notas.length;
    }

    public double[] getNotas() {
        return notas.clone();
    }

    public void registrarNota(int corte, double nota) {
        if (corte < 0 || corte >= notas.length) {
            throw new IllegalArgumentException(
                    "El corte no existe."
            );
        }

        if (Double.isNaN(nota) || Double.isInfinite(nota)
                || nota < 0.0 || nota > 5.0) {
            throw new IllegalArgumentException(
                    "La nota debe estar entre 0,0 y 5,0."
            );
        }

        notas[corte] = nota;
    }

    public double calcularSumaNotas() {
        double suma = 0.0;

        for (double nota : notas) {
            suma += nota;
        }

        return suma;
    }

    public double calcularPromedio() {
        if (notas.length == 0) {
            return 0.0;
        }

        return calcularSumaNotas() / notas.length;
    }

    public double obtenerNotaMayor() {
        double mayor = notas[0];

        for (double nota : notas) {
            if (nota > mayor) {
                mayor = nota;
            }
        }

        return mayor;
    }

    public double obtenerNotaMenor() {
        double menor = notas[0];

        for (double nota : notas) {
            if (nota < menor) {
                menor = nota;
            }
        }

        return menor;
    }

    private String formato(double numero) {
        DecimalFormatSymbols simbolos =
                new DecimalFormatSymbols(new Locale("es", "CO"));

        DecimalFormat decimal = new DecimalFormat(
                "0.0", simbolos
        );

        return decimal.format(numero);
    }

    public void mostrarInfo() {
        System.out.println("\nNombre: " + nombre);
        System.out.println("Notas por corte:");

        for (int i = 0; i < notas.length; i++) {
            System.out.println(
                    "Corte " + (i + 1) + ": " + formato(notas[i])
            );
        }

        System.out.print("Suma: ");

        for (int i = 0; i < notas.length; i++) {
            System.out.print(formato(notas[i]));

            if (i < notas.length - 1) {
                System.out.print(" + ");
            }
        }

        System.out.println(" = " + formato(calcularSumaNotas()));

        System.out.println(
                formato(calcularSumaNotas()) + " / "
                        + notas.length + " = " + formato(calcularPromedio())
        );

        System.out.println(
                "Promedio final: " + formato(calcularPromedio())
        );
    }
}

