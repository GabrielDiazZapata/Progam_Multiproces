import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.salesianos.utils.FileUtil;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 3) {
            System.out.println("Debes proporcionar los argumentos necesarios.");
            return;
        }

        String ficheroEntrada = args[0];
        String classpathUtilidades = args[1];
        String classpathProcesadorFichero = args[2];

        String classPath = classpathProcesadorFichero + ":" + classpathUtilidades;
        System.out.println("Usando classpath: " + classPath);

        String[] vocales = { "A", "E", "I", "O", "U" };
        List<Process> procesos = new ArrayList<>();

        for (String vocal : vocales) {
            String fichErrores = "Errores_" + vocal + ".txt";
            Process proceso = iniciarProceso(classPath, ficheroEntrada, vocal, vocal + ".txt", fichErrores);
            procesos.add(proceso);
        }

        for (Process proceso : procesos) {
            proceso.waitFor(3, TimeUnit.SECONDS); 
        }

        for (String vocal : vocales) {
            int resultado = leerResultado(vocal + ".txt");
            System.out.println("Vocal '" + vocal + "': " + resultado);
        }
    }

    private static Process iniciarProceso(String classPath, String ficheroEntrada, String vocal, String archivoSalida, String fichErrores)
            throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", classPath, "net.salesianos.files.lorem_ipsum.txt", ficheroEntrada, vocal, archivoSalida);
        pb.redirectError(new File(fichErrores));
        return pb.start();
    }

    private static int leerResultado(String archivoResultado) throws IOException {
        int contador = 0;
        List<String> lineas = FileUtil.getLineasFichero(archivoResultado);

        for (String linea : lineas) {
        }

        return contador;
    }
}
