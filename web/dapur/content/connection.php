<?php

	date_default_timezone_set('Asia/Jakarta');
	
	define('HOSTNAME', 'localhost');
	define('USERNAME', 'root');
	define('PASSWORD', '');
	define('DB_SELECT', 'pickfood');

	$koneksi = new mysqli(HOSTNAME, USERNAME, PASSWORD, DB_SELECT) or die (mysqli_errno());

?>