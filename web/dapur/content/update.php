<?php
include 'connection.php';

if (isset($_GET['id'])) {
	$id_pesanan = $_GET['id'];

	mysqli_query($koneksi,"UPDATE tabel_transaksi SET status='delivered' WHERE id_transaksi='$id_pesanan'")or die(mysql_error);

	header("Location:../index.php?pesan=pesanan&halaman=1&status=update");
} else {
	header("Location:../index.php?pesan=logout");
}

?>