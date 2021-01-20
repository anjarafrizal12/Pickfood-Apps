

<!DOCTYPE html>
<html>
<head>
	<title>Membuat Login Multi User Level Dengan PHP dan MySQLi - www.malasngoding.com</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
 
	<?php 
	
	session_start();
    if (isset($_SESSION['username'])){
    	if ($_SESSION['jabatan'] == "kasir") {
    		header("Location:../kasir/index.php?pesan=daftarTransaksi&halaman=1");
    	} else if ($_SESSION['jabatan'] == "koki") {
    		header("Location:../dapur/index.php?pesan=pesanan&halaman=1");
    	}
	} else if (isset($_GET['pesan'])) {
		if($_GET['pesan'] == "gagal"){
			echo "<div class='alert'>Login gagal! username dan password salah!</div>";
		}else if($_GET['pesan'] == "logout"){
			echo "<div class='alert'>Anda telah logout silahkan login kembali</div>";
		}else if($_GET['pesan'] == "belum_login"){
			echo "<div class='alert'>Anda harus login untuk mengakses halaman User</div>";
		}
	}
	?>
 
	<div class="kotak_login">
		<img class="center" src="../image/logo.png">
		<p class="tulisan_login">Permudah Pesanan Anda</p>
 
		<form action="logincheck.php" method="post">
			<label>Username</label>
			<input type="text" name="username" class="form_login" placeholder="Masukan username anda" required="required">
 
			<label>Password</label>
			<input type="password" name="password" class="form_login" placeholder="Masukan password anda" required="required">
 
			<input type="submit" class="tombol_login" value="LOGIN">
		</form>
		
	</div>
 
 
</body>
</html>