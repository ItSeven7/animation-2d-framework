import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Animacion {
    public static void main(String[] args) {
        // Cargar imagen base
        Imagen base = new Imagen("src/assets/prueba.jpg");

        // Definir el área a recortar de la imagen base (x, y, ancho, alto)
        int posicionX = 0, posicionY = 0, ancho = 360, alto = 360;
        Recorte recorte = new Recorte(base, posicionX, posicionY, ancho, alto);

        // Crear carpeta "img" para almacenar las nuevas imágenes
        boolean a = new File("frames").mkdirs();

        // Generar 24 frames con desplazamiento
        int numeroFrames = 60;

        Frame frame1 = new Frame(base.imagen.getWidth(), base.imagen.getHeight());
        frame1.generarFrames(base, recorte, numeroFrames);

        System.out.println("¡Frames generados con éxito!");

        // Ejecuta el script de Python
        ProcessBuilder render = new ProcessBuilder(
                "python",
                "src/module-python/render.py"
        );
        render.inheritIO();

        try {
            Process scriptPython = render.start();
            System.out.println("Ejecutando script de Python...");
            scriptPython.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Video generado por Python.");
    }
}
