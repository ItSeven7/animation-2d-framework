import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame {
    int ancho, alto;

    public Frame(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
    }

    public void generarFrames(Imagen base, Recorte recorte, int numeroFrames){
        int dx = 0, dy = 0;

        for (int i = 0; i < numeroFrames; i++){
            BufferedImage frame = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = frame.createGraphics();
            graphics.drawImage(base.imagen, 0, 0, null);

            /*
            if (i > (numeroFrames / 2)) {
                dx += 5;
                dy += 10;
            } else {
                dx = i * 5;
            }
            */

            int nuevaPosicionX = recorte.xIncial + i;
            int nuevaPosicionY = recorte.yIncial + i;

            graphics.drawImage(recorte.imagen, nuevaPosicionX, nuevaPosicionY, null);
            graphics.dispose();

            String nombreArchivo = String.format("frames/frame_%02d.png", i);
            System.out.println("Generado: " + nombreArchivo);
            try {
                ImageIO.write(frame, "png", new File(nombreArchivo));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
