<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>Dapur</title>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">  
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="wrapper">
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Dapur</a>
			</div>
		</nav>
		<aside class="sidebar">
			<menu>
				<ul class="menu-content">
					<li><a href="index.php?pesan=pesanan&halaman=1"><i class="fa fa-shopping-basket"></i> Daftar Pesanan</a></li>
					<li><a href="#"><i class="fa fa-shopping-basket"></i> Riwayat Pesanan</a></li>
					<li><a href="logout.php"><i class="fa fa-logout"></i> <span>Logout</span></a></li>
				</ul>
			</menu>
		</aside>
		<section class="content">
			<div class="inner">
				<p>
					<?php

					session_start();

					if (isset($_SESSION['username'])){
						if (isset($_GET['pesan'])) {
							if ($_GET['pesan'] == "pesanan") {
								if (isset($_GET['status'])) {
									if ($_GET['status'] == "update") {
										echo "<script type=\"text/javascript\">window.alert('Pesanan telah di update.');
										window.location.href = '../index.php?pesan=pesanan&halaman=1';</script>"; 
									}
								} else {
									include "content/daftarPesanan.php";
								}
							} else if ($_GET['pesan'] == "daftarTransaksi") {
								include "content/daftarTransaksi.php";
							}
						} 
					} else {
						header("Location:../index.php?pesan=logout");
					}
					?>
				</p>
			</div>
		</section>
	</div>

</body>
</html>