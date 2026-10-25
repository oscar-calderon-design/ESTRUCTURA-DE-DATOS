void main() {

    Scanner teclado = new Scanner(System.in);

    IO.println("       SISTEMA DE CALIFICACIONES");
    IO.println("==============================================");

    int cantidadEstudiantes = leerEnteroPositivo(
            teclado, "Cantidad de estudiantes: "
    );

    int cantidadCortes = leerEnteroPositivo(
            teclado, "Cantidad de cortes o evaluaciones: "
    );

    Curso curso = new Curso(
            cantidadEstudiantes, cantidadCortes, teclado
    );

    int opcionInicial;

    do {
        IO.println("1. Cargar estudiantes de ejemplo");

        opcionInicial = leerEntero(
                teclado, "Seleccione una opcion: "
        );

        if (opcionInicial != 1 && opcionInicial != 2) {
            IO.println("Seleccione 1 o 2.");
        }

    } while (opcionInicial != 1 && opcionInicial != 2);

    if (opcionInicial == 1) {
        curso.cargarEjemplo();
        IO.println(
                "Se cargaron los datos de ejemplo correctamente."
        );
    } else {
        curso.registrarEstudiantes();
        curso.registrarNotas();
    }

    int opcion;

    do {
        IO.println("          MENU PRINCIPAL");
        IO.println("1. Mostrar tabla de notas");
        IO.println("3. Mostrar aprobados y reprobados");
        IO.println("5. Ordenar por promedio");
        IO.println("7. Mostrar resumen completo");

        opcion = leerEntero(
                teclado, "Seleccione una opcion: "
        );

        switch (opcion) {

            case 1:
                curso.mostrarTablaNotas();
                break;

            case 2:
                curso.mostrarPromedios();
                break;

            case 3:
                curso.mostrarClasificacion();
                break;

            case 4:
                curso.mostrarMayorYMenorNota();
                break;

            case 5:
                curso.ordenarPorPromedio();
                break;

            case 6:
                IO.print(
                        "Escriba el nombre que desea buscar: "
                );

                String nombre = teclado.nextLine().trim();

                if (nombre.isEmpty()) {
                    IO.println(
                            "Debe escribir un nombre."
                    );
                } else {
                    curso.buscarEstudiantePorNombre(nombre);
                }
                break;

            case 7:
                curso.mostrarResumenCurso();
                break;

            case 0:
                break;

            default:
                IO.println(
                        "Opcion no valida. Intente nuevamente."
                );
        }

    } while (opcion != 0);

    teclado.close();
}

public static int leerEntero(
        Scanner teclado, String mensaje) {

    while (true) {
        IO.print(mensaje);

        String entrada = teclado.nextLine().trim();

        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            IO.println(
                    "Error: debe ingresar un numero entero."
            );
        }
    }
}

public static int leerEnteroPositivo(
        Scanner teclado, String mensaje) {

    int numero;

    do {
        numero = leerEntero(teclado, mensaje);

        if (numero <= 0) {
            IO.println(
                    "Error: el numero debe ser mayor que cero."
            );
        }

    } while (numero <= 0);

    return numero;
}
