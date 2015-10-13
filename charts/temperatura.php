<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Sensores meteorologicos</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
	
    <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />   	
    <!-- Theme style -->
    <link href="../../dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="../../dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="skin-blue sidebar-mini">

      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Sensores meteorologicos
            
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Sensores meteorologicos</a></li>
            <li><a href="#"></a></li>
            <li class="active"></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">

			<div class="row">
			  <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3>
				  <?php 
					require("conectar.php");
					$bd =  mysql_connect($bd_host,$bd_usuario,$bd_password); 
					if (!$bd) {
						die('No pudo conectarse: ' . mysql_error());
					}
					mysql_select_db($bd_base,$bd);

					$query = "SELECT temperatura,humedad FROM clima ORDER BY id DESC LIMIT 1";
					$rs = mysql_query($query);
					$row = mysql_fetch_array($rs);
					
					echo $row["temperatura"]."°";
				  ?></h3>
                  <p></p>
                </div>
                <div class="icon">
                  <i class="fa fa-shopping-cart"></i>
                </div>
                <a href="#" class="small-box-footer">
                   <i class="fa fa-arrow-circle-right"></i>
                </a>
              </div>
            </div><!-- ./col -->
			
			  <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3>
				  <?php 				
					echo $row["humedad"]."%";
				  ?></h3>
                  <p></p>
                </div>
                <div class="icon">
                  <i class="fa fa-shopping-cart"></i>
                </div>
                <a href="#" class="small-box-footer">
                   <i class="fa fa-arrow-circle-right"></i>
                </a>
              </div>
            </div><!-- ./col -->			
			
          </div><!-- /.row -->
		  
			<div class="row">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <i class="fa fa-bar-chart-o"></i>
                  <h3 class="box-title">Temperatura</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                  </div>
                </div>
                <div class="box-body">
                  <div id="temperatura" style="height: 300px;"></div>
                </div><!-- /.box-body-->
              </div><!-- /.box -->
          </div><!-- /.row -->
		  
			<div class="row">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <i class="fa fa-bar-chart-o"></i>
                  <h3 class="box-title">Humedad</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                  </div>
                </div>
                <div class="box-body">
                  <div id="humedad" style="height: 300px;"></div>
                </div><!-- /.box-body-->
              </div><!-- /.box -->
          </div><!-- /.row -->		  
		  
		  
		  
        </section><!-- /.content -->

      </div><!-- /.content-wrapper -->


    <!-- jQuery 2.1.4 -->
    <script src="../../plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="../../bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='../../plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="../../dist/js/app.min.js" type="text/javascript"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../../dist/js/demo.js" type="text/javascript"></script>
    <!-- FLOT CHARTS -->
    <script src="../../plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
    <!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
    <script src="../../plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
    <!-- FLOT PIE PLUGIN - also used to draw donut charts -->
    <script src="../../plugins/flot/jquery.flot.pie.min.js" type="text/javascript"></script>
    <!-- FLOT CATEGORIES PLUGIN - Used to draw bar charts -->
    <script src="../../plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
    <script src="js/raphael-min.js"></script>
    <script src="js/plugins/morris/morris.min.js" type="text/javascript"></script>     
    <!-- Page script -->
    <script type="text/javascript">

      $(function () {
            var bar = new Morris.Bar({
                    element: 'temperatura',
                    resize: false,
                    data: <?php include("buscarTemperatura.php"); ?>,
                    xkey: 'x',
                    ykeys: ['a'],
                    labels: ['Temperatura'],
                    hideHover: 'auto'
                });

            var humedad = new Morris.Bar({
                    element: 'humedad',
                    resize: false,
                    data: <?php include("buscarHumedad.php"); ?>,
                    xkey: 'x',
                    ykeys: ['a'],
                    labels: ['Humedad'],
                    hideHover: 'auto'
                });				
				
      });
    </script>
  </body>
</html>
