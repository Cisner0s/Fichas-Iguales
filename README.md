Puntos clave a tener en cuenta

Preinicio.
	Matriz rectangular de f x c posiciones, con f >=1 ,c <= 20.
	Cada posición contiene un ficha de color rojo (R), verde (G) o azul (B) o está vacía
	No en diagonal.

Puntuación.
  La puntuación de cada juego es la siguiente. El jugador comienza con una puntuación de 0. Cuando se elimina un grupo de m fichas, la puntuación del jugador aumenta en (m-2)². 
  Se otorga una bonificación de 1000 puntos si se eliminan todas las fichas al final del juego.
  Puntos finales.
  Un movimiento vendrá siempre determinado por la ficha más a la izquierda y abajo posible de su grupo. Si hay dos soluciones diferentes con la misma puntuación máxima se dará la solución primera en el orden lexicográfico ascendente respecto al conjunto de coordenadas que forman la solución, que a su vez estarán ordenadas lexicográficamente de forma ascendente.

Entrada
	
  La primera línea de entrada contendrá un número entero positivo indicando el número de juegos.
  Una línea en blanco precede a cada juego. 
  Si hay algún error en la entrada, el programa deberá procesar correctamente todos los juegos que estén bien formados y parar de procesar en el momento en que un juego esté mal formado.


Proceso 
	
Pasos.
	En cada paso del juego, el jugador elige una ficha cuyo grupo tenga al menos dos y elimina todas las fichas del grupo del tablero.
	El tablero se “comprime” en dos pasos:
  Se mueven las fichas restantes en cada columna hacia abajo para llenar los espacios vacíos. El orden de las fichas en cada columna se conserva. 
  Si una columna queda vacía, se mueven las columnas restantes hacia la izquierda tanto como sea posible. El orden de las columnas se conserva.


  El juego termina cuando todas las fichas se han retirado o cuando cada grupo tiene una sola ficha.


Salida
	Juego n:
  donde n es el número del juego, seguido de tantas líneas como los movimientos que configuran la solución óptima, dada movimiento por movimiento, seguido finalmente por la puntuación final. Cada movimiento debe imprimirse en el formato: 
    Movimiento x en (f, c): eliminó b fichas de color C y obtuvo s puntos.
  donde x es el número de movimiento, f y c son el número de fila y el número de columna de la ficha elegida, respectivamente. Las filas están numeradas del 1 al número máximo de filas del tablero desde abajo y las columnas están numeradas del 1 al número máximo de columnas desde la izquierda; b es el número de fichas del grupo eliminadas y C es uno de "R", "V" o "A", lo que indica el color de las fichas eliminadas; s es la puntuación de este movimiento. La puntuación de cada movimiento no incluye los 1000 puntos de bonificación si se eliminan todas las bolas después de ese movimiento.
  La puntuación final debe informarse de la siguiente manera:
  Puntuación final: s, quedando b fichas.
  Insertar una línea en blanco entre la salida de cada juego. Al último juego no le sigue una línea en blanco.
  Utilizar las formas singulares "ficha" y "punto" si el valor correspondiente es 1.

EJEMPLO:
Entrada
2

AVR
AAR
ARR
VVR

VRA
RAR
AAR
RVV
Salida
Juego 1:
Movimiento 1 en (1, 3): eliminó 5 fichas de color R y obtuvo 9 puntos.
Movimiento 2 en (2, 1): eliminó 4 fichas de color A y obtuvo 4 puntos.
Movimiento 3 en (1, 1): eliminó 3 fichas de color V y obtuvo 1 punto.
Puntuación final: 1014, quedando 0 fichas.

Juego 2:
Movimiento 1 en (2, 3): eliminó 2 fichas de color R y obtuvo 0 puntos.
Movimiento 2 en (2, 1): eliminó 4 fichas de color A y obtuvo 4 puntos.
Movimiento 3 en (1, 1): eliminó 3 fichas de color R y obtuvo 1 punto.
Movimiento 4 en (1, 1): eliminó 3 fichas de color V y obtuvo 1 punto.
Puntuación final: 1006, quedando 0 fichas.





