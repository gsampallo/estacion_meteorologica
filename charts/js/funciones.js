


function ocultar(id,id1) {
	$(id).hide();
	$(id1).show();
}
var filas = 0;
var cantidadFilas = 0;

function agregarMaterial() {
	
	var control = true;
	var material = $("#material").val();
	var cantidad = $("#cantidad").val();
	
	if(material == 0) {
		control = false;
		$("#errorFormMateriales").show();
		$("#errorFormMateriales").html("Error en la descripcion de los materiales o en la cantidad de materiales");
	}
	
	if(cantidad == 0) {
		control = false;
		$("#errorFormMateriales").show();
		$("#errorFormMateriales").html("Error en la descripcion de los materiales o en la cantidad de materiales");
	}
	
	
	if(control) {
		filas = filas + 1;
		cantidadFilas = cantidadFilas + 1;
		$("#errorFormMateriales").hide();
		$("#material").val("");
		$("#cantidad").val("");
		$("#tablaMateriales").show();
		$("#tablaMateriales tbody").append(
			
			"<tr id=\"fila"+filas+"\">"+
				"<td>"+cantidad+"<input type=\"hidden\" name=\"cantidad[]\" value=\""+cantidad+"\" /></td>"+
				"<td>"+material+"<input type=\"hidden\" name=\"materiales[]\" value=\""+material+"\" /></td>"+
				"<td><a href=\"javascript:removerMaterial("+filas+")\"><i class=\"fa fa-fw fa-minus-square\"></i></a></td>"+
			"</tr>"
		);
	}
}

function removerMaterial(fila) {
	$("#fila"+fila).remove();
	cantidadFilas = cantidadFilas - 1;
	if(cantidadFilas == 0) {
		//$("#tabla_materiales").hide();
	}
}


function findValue(li) {
	console.log(li);
	// if coming from an AJAX call, let's use the CityId as the value
	if( !!li.extra ) var sValue = li.extra[0];

	// otherwise, let's just display the value in the text box
	else var sValue = li.selectValue;

}

function selectItem(li) {
	findValue(li);
}

function formatItem(row) {
	//return row[0] + " (id: " + row[1] + ")";
	return row[0];
}


