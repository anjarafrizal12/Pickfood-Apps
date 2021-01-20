<?php

	include_once('connection.php');


	$nama_pemesan = $_POST['nama_pemesan'];
 	$no_meja = $_POST['nomeja'];
 	$totalHarga = $_POST['totalHarga'];
 	$jmlBayar	= $_POST['jumlahBayar'];
 	$list = $_POST['list'];


 	$someArray = json_decode($list, true);

 	date_default_timezone_set('Asia/jakarta');
	$date = date('Y/m/d H:i:s', time());

	$sql_query = "insert into tabel_transaksi (nama_pemesan,tanggal_transaksi,no_meja,total_harga,total_bayar) values ('$nama_pemesan','$date','$no_meja','$totalHarga','$jmlBayar')";

	if (mysqli_query($koneksi,$sql_query)) {
		$query = "SELECT id_transaksi FROM tabel_transaksi WHERE nama_pemesan='$nama_pemesan'";
		$result = mysqli_query($koneksi,$query);

		if (mysqli_num_rows($result) > 0) {
		// output data of each row
			while($row = mysqli_fetch_assoc($result)) {
				$id = $row["id_transaksi"];
			}
			foreach ($someArray as $key => $value) {
				$namamakanan = $value["namamakanan"];
				$qtymakanan = $value["qtymakanan"];
				$hgmakanan = $value["hargamakanan"];

				$sql_query2 = "insert into tabel_detail_transaksi (nama_makanan,qty_makanan,harga_makanan,id_transaksi) values ('$namamakanan','$qtymakanan','$hgmakanan','$id')";

				if (mysqli_query($koneksi,$sql_query2)) {
					$stat = true;
				} else {
					$stat = false;
				}
			}

			if ($stat) {
				echo "Succesfull";
			} else {
				echo "Failed";
			}

		} else {
			echo "0 results";
		}
	} else {
		echo "Try Again1";
	}
mysqli_close($koneksi);
?>
