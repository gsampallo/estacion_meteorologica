CREATE TABLE `clima` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `fecha_sensor` date DEFAULT NULL,
  `hora_sensor` time DEFAULT NULL,
  `temperatura` float(5,2) DEFAULT NULL,
  `humedad` float(5,2) DEFAULT NULL,
  `temperatura2` float(5,2) DEFAULT NULL,
  `lluvia` int(5) DEFAULT NULL,
  `suelo` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=386 DEFAULT CHARSET=latin1;