<?php

	include_once('connection.php');

	$query = "SELECT * FROM tabel_makanan";
	$result = mysqli_query($koneksi,$query);

	$arraydata = array();

	while($baris = mysqli_fetch_assoc($result))
	{
		$arraydata[]=$baris;
	}

	echo json_encode($arraydata);

?>