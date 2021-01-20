<?php 
// mengaktifkan session php
session_start();
 
// menghubungkan dengan koneksi
include 'connection.php';
 
// menangkap data yang dikirim dari form
$username = $_POST['username'];
$password = $_POST['password'];
 
// menyeleksi data admin dengan username dan password yang sesuai
$data = mysqli_query($koneksi,"select * from tabel_user where username='$username' and password='$password'");
 
// menghitung jumlah data yang ditemukan
$cek = mysqli_num_rows($data);

$row=mysqli_fetch_row($data);
 
if($cek > 0){
	if ($row[3] == "kasir") {
		$_SESSION['username'] = $username;
		$_SESSION['jabatan'] = $row[3];
		$_SESSION['status'] = "login";
		header("location:kasir/index.php?pesan=daftarTransaksi&halaman=1");
	} else if ($row[3] == "koki") {
		$_SESSION['username'] = $username;
		$_SESSION['jabatan'] = $row[3];
		$_SESSION['status'] = "login";
		header("location:dapur/index.php?pesan=pesanan&halaman=1");
	} else {
		header("location:index.php?pesan=gagal");
	}
}else{
	header("location:index.php?pesan=gagal");
}
?>