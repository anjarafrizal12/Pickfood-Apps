<?php
include '../connection.php';
?>

<script>
  function printContent(el){
    var restorepage = document.body.innerHTML;
    var printcontent = document.getElementById(el);
    var WinPrint = window.open('', '', 'left=0,top=0,width=600,toolbar=1,scrollbars=1,status=0');
    WinPrint.document.write(printcontent.innerHTML);
    WinPrint.document.close();
    WinPrint.focus();
    WinPrint.print();
  }
</script>
<div class="col-md-12" style="padding:0px">
  <ol class="breadcrumb" style="margin:0;border-radius:0;">
    <li><a href="index.php?pesan=daftarTransaksi&halaman=1">Home</a></li>
    <li class="active">Detail Transaksi</li>
  </ol>
</div>
<?php
if (isset($_GET["id"])) {
  $id = $_GET["id"];
}


$result = mysqli_query($koneksi,"select * from tabel_transaksi where id_transaksi='$id'");
$total = mysqli_num_rows($result);
$query = mysqli_query($koneksi,"select * from tabel_detail_transaksi where id_transaksi='$id'")or die(mysql_error);
$total1 = mysqli_num_rows($query);

?>
<div id="div1" class="col-md-10" align="center" style="min-height: 500px;">
  <h5 align="center">======================================<br></h5>
  <h3 align="center"><b>Order Food</b> <br></h3>
  <h6 align="center">Jln Telekomunikasi no 1, Kota Bandung<br></h6>
  <h5 align="center">Nota Penjualan<br><br></h5>

  <table align="center" width="300" border="0" style="padding: 10px">

    <?php
    while ($data1 = mysqli_fetch_assoc($result)) {
      $totalTagihan = $data1['total_harga'];
      $totalBayar = $data1['total_bayar'];
      ?>
      <tr>                
        <td width="38%" align="left">id transaksi</td>
        <td width="2%">:</td>
        <td width="60%"><?php echo $data1['id_transaksi']; ?></td>             
      </tr>
      <tr>
        <td width="38%" align="left">Tanggal transaksi</td>
        <td width="2%">:</td>
        <td width="60%"><?php echo $data1['tanggal_transaksi']; ?></td>
      </tr>
      <tr>
        <td width="38%" align="left">nama Pemesan</td>
        <td width="2%">:</td>
        <td width="60%"><?php echo $data1['nama_pemesan']; ?></td>
      </tr>

      <?php               
    } 
    ?>
  </table>
  <h5 align="center">======================================<br></h5>

  
  <table align="center" width="300" border="0" style="padding: 10px">

    <?php
    while ($data = mysqli_fetch_assoc($query)) {
      ?>
      <tr>                 
        <td width="10%"><?php echo $data['qty_makanan']; ?></td>
        <td width="65%"><?php echo $data['nama_makanan']; ?></td>
        <td align="right" width="25%"><?php echo $data['harga_makanan']; ?></td>      
      </tr>

      <?php               
    } 
    ?>

  </table>

  <h5 align="center">======================================<br></h5>

  <table align="center" width="300" border="0" style="padding: 10px; margin-bottom: 50px">
    <tr>
      <td width="100%">Jumlah Item : 7</td>
    </tr>
    <tr>
      <td align="left" width="50%">Total Tagihan</td>
      <td align="right" width="50%"><b><?php echo $totalTagihan; ?></b></td>
    </tr>
    <tr>
      <td align="left" width="50%">Total Bayar</td>
      <td align="right" width="50%"><?php echo $totalBayar; ?></td>
    </tr>
    <tr>
      <td align="left" width="50%">Kembali</td>
      <td align="right" width="50%"><?php echo $totalBayar-$totalTagihan;?></td>
    </tr>
  </table>

  <h5 align="center">============== Terima Kasih ==============<br></h5>
</div>
<div id="div2" class="col-md-10" align="center">
  <button class="btn btn-info" onclick="printContent('div1')">Print</button>
</div>