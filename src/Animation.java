import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation {
    public static void main(String[] args) {
        try {
            // Cargar imagen base
            BufferedImage imagenBase = ImageIO.read(new File("src/assets/prueba.jpg"));

            // Definir el área a recortar de la imagen base (x, y, ancho, alto)
            int posicionX = 0, posicionY = 0, ancho = 360, alto = 360;
            BufferedImage recorte = imagenBase.getSubimage(posicionX, posicionY, ancho, alto);

            // Crear carpeta "img" para almacenar las nuevas imágenes
            boolean a = new File("img").mkdirs();

            // Generar 24 frames con desplazamiento
            int numeroFrames = 60;

            int dx = 0;
            int dy = 0;
            for (int i = 0; i < numeroFrames; i++) {
                BufferedImage frame = new BufferedImage(
                        imagenBase.getWidth(),
                        imagenBase.getHeight(),
                        BufferedImage.TYPE_INT_ARGB
                );

                Graphics2D graphics = frame.createGraphics();
                graphics.drawImage(imagenBase, 0, 0,null);


                if (i > (numeroFrames / 2)) {
                    dx -= 5;
                    dy += 10;
                } else {
                    dx = i * 5;
                }

                graphics.drawImage(recorte, posicionX + dx, posicionY + dy,null);
                graphics.dispose();

                String nombreArchivo = String.format("img/frame_%02d.png", i);
                ImageIO.write(frame, "png", new File(nombreArchivo));
                System.out.println("Generado: " + nombreArchivo);
            }

            System.out.println("¡Frames generados con éxito!");

        } catch (IOException e) {
            e.printStackTrace();
        }

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
