import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Imagen {
    String ruta;
    BufferedImage imagen;

    public Imagen (String ruta) {
        this.ruta = ruta;

        try {
            imagen = ImageIO.read(new File(ruta));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
