import java.io.*;
import java.util.Scanner;

public class AdminMedico {
    private static final String DOC_FILE = "doctores.csv";
    private static final String PAC_FILE = "pacientes.csv";
    private static final String CITA_FILE = "citas.csv";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mostrarMenu(sc);
}

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- ADMINISTRACIÓN DE CITAS (ID AUTOMÁTICO) ---");
            System.out.println("1. Registrar Doctor");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Crear Cita");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) { opcion = 0;
            }

            switch (opcion) {
                case 1 -> registrarDoctor(sc);
                case 2 -> registrarPaciente(sc);
                case 3 -> registrarCita(sc);
                case 4 -> System.out.println("Finalizado...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void registrarDoctor(Scanner sc) {
        int proximoID = obtenerUltimoID(DOC_FILE) + 1;
        System.out.println("\n[Alta de Doctor]  ID asignado: " + proximoID);
        System.out.print("Nombre Completo: "); String nombre = sc.nextLine();
        System.out.print("Especialidad: "); String esp = sc.nextLine();

        guardarEnCSV(DOC_FILE, proximoID + "," + nombre + "," + esp);
    }

    private static void registrarPaciente(Scanner sc) {
        int proximoID = obtenerUltimoID(PAC_FILE) + 1;
        System.out.print("\n[Alta Paciente] ID asignadno: " + proximoID);
        System.out.print("Nombre Completo: "); String nombre = sc.nextLine();

        guardarEnCSV(PAC_FILE, proximoID + "," + nombre);
    }

    private static void registrarCita(Scanner sc) {
        int proximoID = obtenerUltimoID(CITA_FILE) + 1;
        System.out.println("\n[Nueva Cita] ID Asignado: " + proximoID);
        System.out.print("Fecha y Hora: "); String fecha = sc.nextLine();
        System.out.print("Motivo: "); String motivo = sc.nextLine();
        System.out.print("ID del Doctor: "); String idDoc = sc.nextLine();
        System.out.print("ID del Paciente: "); String idPac = sc.nextLine();

        guardarEnCSV(CITA_FILE, proximoID + "," + fecha + "," + motivo + "," + idDoc + "," + idPac);
    }

    private static int obtenerUltimoID(String nombreArchivo) {
        int contador = 0;
        File file = new File(nombreArchivo);
        if (!file.exists()) return 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            return 0;
        }
        return contador;
    }

    private static void guardarEnCSV(String archivo, String linea) {
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(linea);
            System.out.println("Guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar.");
        }
    }
}