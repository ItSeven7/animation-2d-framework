import cv2
import os
import sys

directorio_imagenes = "frames"  # valor por defecto

nombre_video_salida = 'video_desplazamiento.avi'
fps = 24

# Obtener la lista de imágenes
archivos_imagenes = [img for img in os.listdir(directorio_imagenes) if img.endswith(".png")]
archivos_imagenes.sort()

# Leer la primera imagen
imagen_ejemplo = cv2.imread(os.path.join(directorio_imagenes, archivos_imagenes[0]))
altura, ancho, capas = imagen_ejemplo.shape

fourcc = cv2.VideoWriter_fourcc(*'MP42')
video_salida = cv2.VideoWriter(nombre_video_salida, fourcc, fps, (ancho, altura))

for archivo in archivos_imagenes:
    imagen = cv2.imread(os.path.join(directorio_imagenes, archivo))
    video_salida.write(imagen)

video_salida.release()
print("El video ha sido creado exitosamente.")
