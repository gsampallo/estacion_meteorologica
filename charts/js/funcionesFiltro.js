$(function() {
	$("#example1").dataTable();
	$('#example2').dataTable({
		"bPaginate": true,
		"bLengthChange": false,
		"bFilter": false,
		"bSort": true,
		"bInfo": true,
		"bAutoWidth": false
	});

	$('#dtFechaCreacion').daterangepicker(
			{
				ranges: {
					'Hoy': [moment(), moment()],
					'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1)],
					'ultimos 7 dias': [moment().subtract('days', 6), moment()],
					'ultimos 30 dias': [moment().subtract('days', 29), moment()],
					'Esta mes': [moment().startOf('month'), moment().endOf('month')],
					'El mes anterior': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
				},
				startDate: moment().subtract('days', 29),
				endDate: moment()
			},
		function(start, end) {
			$('#dtFechaCreacion span').html(start.format('DD-MM-YYYY') + ' - ' + end.format('DD-MM-YYYY'));
		}
		);				
	

	$('#dtFechaNecesidad').daterangepicker(
			{
				ranges: {
					'Hoy': [moment(), moment()],
					'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1)],
					'ultimos 7 dias': [moment().subtract('days', 6), moment()],
					'ultimos 30 dias': [moment().subtract('days', 29), moment()],
					'Esta mes': [moment().startOf('month'), moment().endOf('month')],
					'El mes anterior': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
				},
				startDate: moment().subtract('days', 29),
				endDate: moment()
			},
		function(start, end) {
			$('#dtFechaNecesidad span').html(start.format('DD-MM-YYYY') + ' - ' + end.format('DD-MM-YYYY'));
		}
		);				
			

	//Deshabilitar input
	$('#dtNroTicket').attr('disabled','disabled');
	$('#dtFechaCreacion').attr('disabled','disabled');
	$('#dtFechaNecesidad').attr('disabled','disabled');
	$('#dtAsunto').attr('disabled','disabled');
	$('#dtPersona').attr('disabled','disabled');
	
	//Manejo de eventos en los checkbox
	
	$('#filtroNroTicket').click(function() {
		if($(this).prop('checked')){
		console.log("habilitar");
		 $('#dtNroTicket').removeAttr('disabled');
	} else {
		console.log("deshabilitar");
		$('#dtNroTicket').attr('disabled','disabled');
	}
	});
});	