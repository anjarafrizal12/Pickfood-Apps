  <head>
    <script type="text/javascript">
    function delivered(el){
      var id = el;
      
      if (confirm("Ubah status pesanan?")) {
        alert(status)
        window.location.href = '../dapur/content/update.php?id='+id;
      } 
    }
  </script>

  </head>
  <div class="col-md-12" style="padding:0px">
  	<ol class="breadcrumb" style="margin:0;border-radius:0;">
  		<li><a href="index.php?pesan=daftarTransaksi&halaman=1">Home</a></li>
  		<li class="active">Daftar Transaksi</li>
  	</ol>
  </div>
  <div class="col-md-12" style="padding:10px; padding-left:0;padding-right:0;">
    <div class="row">

     <?php
     include '../connection.php';
     ?>

     <?php 
     $halaman = 4;
     $page = isset($_GET["halaman"]) ? (int)$_GET["halaman"] : 1;
     $mulai = ($page>1) ? ($page * $halaman) - $halaman : 0;
     $result = mysqli_query($koneksi,"SELECT * FROM tabel_transaksi where status='cooked'");
     $total = mysqli_num_rows($result);
     $pages = ceil($total/$halaman);
     $query = mysqli_query($koneksi,"select * from tabel_transaksi where status='cooked' LIMIT $mulai, $halaman")or die(mysql_error);
     $no =$mulai+1;


     while ($data = mysqli_fetch_assoc($query)) {
      $value = $data['id_transaksi'];

      echo "<div class='column'>";
      echo "<div class='card'>";
      echo "<img src='../image/ceklis.png' width=35px height=35px align=right onclick=delivered($value)></img>";
      echo "<table border='0'>";
      echo "<tr>";
      echo "<td width='55%' align='left'>Id Transaksi</td>";
      echo "<td width='5%' align='left'> : </td>";
      echo "<td width='40%' align='left'>$data[id_transaksi]</td>";
      echo "</tr>";
      echo "<tr>";
      echo "<td width='55%' align='left'>Nama Pemesan</td>";
      echo "<td width='5%' align='left'> : </td>";
      echo "<td width='40%' align='left'>$data[nama_pemesan]</td>";
      echo "</tr>";
      echo "<tr>";
      echo "<td width='55%' align='left'>No Meja</td>";
      echo "<td width='5%' align='left'> : </td>";
      echo "<td width='40%' align='left'>$data[no_meja]</td>";
      echo "</tr>";
      echo "</table>";
      echo "============================";

      $query2 = mysqli_query($koneksi,"select * from tabel_detail_transaksi where id_transaksi=$data[id_transaksi]")or die(mysql_error);

      echo "<table border='0'>";
      echo "<tr>";
      echo "<td width='50%' align='left'><b>NamaMakanan</b></td>";
      echo "<td width='50%' align='right'><b>Qty</b></td>";                         
      echo "</tr>";


      while ($data2 = mysqli_fetch_assoc($query2)) {


        echo "<tr>";
        echo "<td width='50%' align='left'>$data2[nama_makanan]</td>";
        echo "<td width='50%' align='right'>$data2[qty_makanan]</td>";        
        echo "</tr>";

      }
      
      echo "</table>";
      echo "<div id='result'></div>";
      echo "</div>";
      echo "</div>";
    }

    ?>

  </div>

  <div class="col-md-12">
    <nav align="center">

      <?php for ($i=1; $i<=$pages ; $i++){ ?>
        <th><a href="index.php?pesan=pesanan&halaman=<?php echo $i; ?>"><?php echo $i; ?></a></th>

      <?php } ?>

    </nav>

  </div>



</div>