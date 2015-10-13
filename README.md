# estacion_meteorologica

Consta de tres partes:
1. Arduino, falta carga el esquema para que se pueda construir la electronica; el programa recolecta información de varios sensores, los almacena en una tarjeta minisd, y a la vez los publica mediante la interfaz ethernet.
2. Servidor, una aplicacion en java, que cada cierto tiempo lee la ip del la interfaz ethernet del arduino, captura los datos y los guarda en una base de datos.
3. Visualización. Lee la base de datos, y muestra de una manera amigable al usuario los datos que fueron guardados, por ahora tiene temperatura interior/exterior, humedad y "lluvia", aunque este ultimo sensor aún no tiene una interpretación valida.

El proximo paso, es publicar los demas sensores; y que arduino tenga un metodo para publicar la información guardada en la minisd; o un mecanismo rapido para leer esa informacion sin que afecte la funcionalidad de sensar.
