  <div class="col-md-12" style="padding:0px">
  	<ol class="breadcrumb" style="margin:0;border-radius:0;">
  		<li><a href="index.php?pesan=daftarTransaksi&halaman=1">Home</a></li>
  		<li class="active">Daftar Transaksi</li>
  	</ol>
  </div>
  <div class="col-md-12" style="padding:10px; padding-left:0;padding-right:0;min-height:600px">

  	<?php
  	include '../connection.php';
  	?>

  	<table class="table table-bordered">

  		<tr>
  			<th class="info">No</th>
  			<th class="info">Id Transaksi</th>
  			<th class="info">Nama Pemesan</th>
  			<th class="info">Tanggal Transaksi</th>
  			<th class="info">No Meja</th>
        <th class="info">Status</th>
  			<th class="info">Total Harga</th>
  			<th class="info">Total Bayar</th> 
  			<th class="info" colspan="2">Action</th>                         
  		</tr>

  		<?php 
  		$halaman = 10;
  		$page = isset($_GET["halaman"]) ? (int)$_GET["halaman"] : 1;
  		$mulai = ($page>1) ? ($page * $halaman) - $halaman : 0;
  		$result = mysqli_query($koneksi,"SELECT * FROM tabel_transaksi");
  		$total = mysqli_num_rows($result);
  		$pages = ceil($total/$halaman);            
  		$query = mysqli_query($koneksi,"select * from tabel_transaksi LIMIT $mulai, $halaman")or die(mysql_error);
  		$no =$mulai+1;


  		while ($data = mysqli_fetch_assoc($query)) {
  			?>
  			<tr>
  				<td><?php echo $no++; ?></td>                  
  				<td><?php echo $data['id_transaksi']; ?></td>
  				<td><?php echo $data['nama_pemesan']; ?></td>
  				<td><?php echo $data['tanggal_transaksi']; ?></td>
  				<td><?php echo $data['no_meja']; ?></td>
          <td><?php echo $data['status']; ?></td>
  				<td><?php echo $data['total_harga']; ?></td>
  				<td><?php echo $data['total_bayar']; ?></td>
  				<td><a href="index.php?pesan=detail1&id=<?php echo $data['id_transaksi'];?>&status=<?php echo $data['status'];?>">detail</a></td>
  				<td><a href="">delete</a></td>              
  			</tr>

  			<?php               
  		} 
  		?>
  		
  	</table>

  	<div class="col-md-12">
  		<nav align="center">
  			
  			<?php for ($i=1; $i<=$pages ; $i++){ ?>
  				<th><a href="index.php?pesan=daftarTransaksi&halaman=<?php echo $i; ?>"><?php echo $i; ?></a></th>

  			<?php } ?>
  			
  		</nav>

  	</div>
  </div>