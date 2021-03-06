<!doctype html>
<html class="no-js">

<head>
	<meta charset="utf-8"/>
	<title><?php echo ($title != null ? $title : 'E-Movie'); ?></title>

	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<link rel="stylesheet" media="all" href="css/style.css"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<!-- Adding "maximum-scale=1" fixes the Mobile Safari auto-zoom bug: http://filamentgroup.com/examples/iosScaleBug/ -->

	<!-- JS -->
	<script src="js/jquery-1.7.1.min.js"></script>

	<?php
		if($home == true) {
			echo '<script src="js/emovie/index-movie-list.js"></script> <!-- Movie list querying -->';
		} else {
			echo '<script src="js/custom.js"></script>';
		}
	?>

	<script src="js/tabs.js"></script>
	<script src="js/emovie/crud.js"></script>
	<script src="js/emovie/common.js"></script>
	<script src="js/css3-mediaqueries.js"></script>
	<script src="js/jquery.columnizer.min.js"></script>

	<!-- Isotope -->
	<script src="js/jquery.isotope.min.js"></script>

	<!-- Lof slider -->
	<script src="js/jquery.easing.js"></script>
	<script src="js/lof-slider.js"></script>
	<link rel="stylesheet" href="css/lof-slider.css" media="all"  />
	<!-- ENDS slider -->

	<!-- Tweet -->
	<link rel="stylesheet" href="css/jquery.tweet.css" media="all"  />
	<script src="js/tweet/jquery.tweet.js" ></script>
	<!-- ENDS Tweet -->

	<!-- superfish -->
	<link rel="stylesheet" media="screen" href="css/superfish.css" />
	<script  src="js/superfish-1.4.8/js/hoverIntent.js"></script>
	<script  src="js/superfish-1.4.8/js/superfish.js"></script>
	<script  src="js/superfish-1.4.8/js/supersubs.js"></script>
	<!-- ENDS superfish -->

	<!-- prettyPhoto -->
	<script  src="js/prettyPhoto/js/jquery.prettyPhoto.js"></script>
	<link rel="stylesheet" href="js/prettyPhoto/css/prettyPhoto.css"  media="screen" />
	<!-- ENDS prettyPhoto -->

	<!-- poshytip -->
	<link rel="stylesheet" href="js/poshytip-1.1/src/tip-twitter/tip-twitter.css"  />
	<link rel="stylesheet" href="js/poshytip-1.1/src/tip-yellowsimple/tip-yellowsimple.css"  />
	<script  src="js/poshytip-1.1/src/jquery.poshytip.min.js"></script>
	<!-- ENDS poshytip -->

	<!-- JCarousel -->
	<script type="text/javascript" src="js/jquery.jcarousel.min.js"></script>
	<link rel="stylesheet" media="screen" href="css/carousel.css" />
	<!-- ENDS JCarousel -->

	<!-- GOOGLE FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Voltaire' rel='stylesheet' type='text/css'>

	<!-- modernizr -->
	<script src="js/modernizr.js"></script>

	<!-- SKIN -->
	<link rel="stylesheet" media="all" href="css/skin.css"/>

	<!-- Less framework -->
	<link rel="stylesheet" media="all" href="css/lessframework.css"/>

	<!-- flexslider -->
	<link rel="stylesheet" href="css/flexslider.css" >
	<script src="js/jquery.flexslider.js"></script>

	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="css/emovie/custom.css">
</head>

<body class= <?php echo ($home ? "home" : "page");?>>
	<!-- HEADER -->
	<header>
		<div class="wrapper cf">
			<div id="logo">
				<a href="index.php"><img  src="img/logoF.png" alt="Simpler"></a>
			</div>

			<!-- nav -->
			<ul id="nav" class="sf-menu">
				<li class="current-menu-item"><a href="index.php">INÍCIO</a></li>
				<li><a href="horarios.php">HORÁRIOS</a></li>
				<li><a href="#">COMPRAS</a>
					<ul>
						<li><a href="retiraringresso.php">RETIRAR INGRESSO</a></li>
						<li><a href="trocasessao.php">TROCA DE SESSÃO</a></li>
						<li><a href="cancelarcompra.php">CANCELAR COMPRA</a></li>
					</ul>
				</li>
				<li><a href="cadastro.php">CADASTRO</a></li>
				<li><a href="login.php">LOGIN</a></li>
			</ul>
			<div id="combo-holder"></div>
			<!-- ends nav -->

			<?php
			if($home) {
				include_once 'slider.php';
			}
			?>

		</div>
	</header>
	<!-- ENDS HEADER -->