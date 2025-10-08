import java.awt.image.BufferedImage;

public class Recorte {
    BufferedImage imagen;
    int xIncial, yIncial, ancho, alto;

    public Recorte(Imagen base, int x, int y, int ancho, int alto) {
        this.xIncial = x;
        this.yIncial = y;
        this.ancho = ancho;
        this.alto = alto;

        imagen = base.imagen.getSubimage(xIncial, yIncial, ancho, alto);
    }
}