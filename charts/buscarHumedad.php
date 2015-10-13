<?php
require("conectar.php");
$bd =  mysql_connect($bd_host,$bd_usuario,$bd_password); 
if (!$bd) {
    die('No pudo conectarse: ' . mysql_error());
}
mysql_select_db($bd_base,$bd);

//$query = "SELECT hora,humedad FROM clima WHERE fecha = curdate() AND HOUR(hora) = HOUR(curtime())";
$query = "SELECT hora,humedad FROM clima WHERE fecha = curdate() AND HOUR(hora) >= (HOUR(curtime())-1) LIMIT 60";
/*
$rs = mysql_query($query);
echo "[";
$i = 1;
$row=mysql_fetch_array($rs);
//echo "[".$row["hora"].",".$row["temperatura"]."]";
echo "[".$i.",".$row["temperatura"]."]";

while($row=mysql_fetch_array($rs)) {
	$i++;
	echo ",[".$i.",".$row["temperatura"]."]";
}
echo "]";
*/
$rs = mysql_query($query);
$coma = false;
$dataSector = "[";

while($row = mysql_fetch_array($rs)) {
    if($coma) { $dataSector = $dataSector.","; }
    $dataSector = $dataSector. "{x:'".$row["hora"]."',a:".$row["humedad"]."}";
    $coma = true;   
}
$dataSector = $dataSector."]";

echo $dataSector;

?>
