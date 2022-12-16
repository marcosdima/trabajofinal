# Trabajo Final
  ##### Trabajo final de POO, 2do cuatrimestre de 2022.

## Datos
+ Alumno: Di Matteo Brambilla, Marcos.
+ Legajo: 166298
  
## Juego: Black Jack

### Ejecución
Para iniciar el juego hay que ejecutar **'BlackJackApp'**, que se encuentra dentro del paquete **'ar.edu.unlu.poo.trabajofinal'**. Pueden setear manualmente la interfaz de preferencia al manipular el booleano **'consola'**. Si es **'true'** se ejecutará en la consola, con **'false'** la interfaz gr

### Reglas
+ El objetivo es conseguir una mano con un puntaje lo más cercano a 21. Si te pasas de dicho número, perdés la mano. 
+ Al principio de cada mano tienes que apostar un monto mínimo. 
+ En todo momento estas compitiendo contra el crupier, la casa, que si no logra conseguir un número mayor que el tuyo deberá pagarte un determinado monto de dinero. 
+ Mientras tu dinero sea mayor o igual a la apuesta mínima podrás seguir jugando. 

### Valores de cartas
+ AS: vale 1 o 11, dependiendo de lo que le convenga al jugador.
+ CABALLERO, REINA y REY: Valen 10.
+ El resto de cartas valen lo mismo que su número.

### Comandos Principales
Hay una series de comandos que se ingresan dentro de **la ventana de seteo de apuesta**, dejo adjunto
los que son necesarios para poder acceder a todas las funciones del juego:

* 'salir': Sale del juego por completo, volviendo al menú principal, dejando la opción de guardar la
partida.
* 'renuncio': El jugador que ingresa este comando, sale de la partida, guardando su puntaje en el
ranking.
*  '0': Ingresar 0 como monto de apuesta indica que no apostas en esta mano.

### Comandos Secundarios
* 'esoyam': Le da 1000p al jugador que lo ingresa.
+ 'help': muestra el contenido del archivo 'help.txt'.

### Nombres especiales
Para propósitos de testing, hay nombres que presetean un conjunto de cartas al inicio. No es el nombre en sí, sino como empiezan. Si empieza con:
+ 'bj': BlackJack asegurado para cada mano.
+ '21': Arranca con tres cartas, dando como resultado 21 pts.
+ 'pt': Empieza con un par bajo.


# Créditos

  [Imágenes de cartas 'Default'](https://yaomon.itch.io/playing-cards)
  [Imágenes de castas 'Simple' y 'Simple shiny'](https://gamesupply.itch.io/frenchcard)

