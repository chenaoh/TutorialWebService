<?PHP
$hostname_localhost ="localhost";
$database_localhost ="bd_usuario";
$username_localhost ="root";
$password_localhost ="";

$json['img']=array();

	//if(true){)
	if(isset($_POST["btn"])){
		
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$ruta="imagenes";
		$archivo=$_FILES['imagen']['tmp_name'];
		echo 'Archivo';
		echo '<br>';
		echo $archivo;
		$nombreArchivo=$_FILES['imagen']['name'];
		echo 'Nombre Archivo';
		echo '<br>';
		echo $nombreArchivo;
		move_uploaded_file($archivo,$ruta."/".$nombreArchivo);
		$ruta=$ruta."/".$nombreArchivo;
		$documento=$_POST['documento'];
		$nombre=$_POST['nombre'];
		$profesion=$_POST['profesion'];
		
		echo '<br>';
		echo 'Documento: ';
		echo $documento;
		echo '<br>';
		echo 'Nombre: ';
		echo $nombre;
		echo '<br>';
		echo 'Profesion: ';
		echo $profesion;
		echo '<br>';
		echo 'ruta :';
		echo $ruta;
		echo '<br>';
		echo 'Tipo Imagen: ';
		echo ($_FILES['imagen']['type']);
		echo '<br>';
		echo '<br>';
		echo "Imagen: <br><img src='$ruta'>";
		echo '<br>';
		echo '<br>';
		echo 'imagen en Bytes: ';
		echo '<br>';
		echo '<br>';
		//echo $bytesArchivo=file_get_contents($ruta);
		echo '<br>';
		
		$bytesArchivo=file_get_contents($ruta);
		$sql="INSERT INTO usuario(documento,nombre,profesion,imagen,ruta_imagen) VALUES (?,?,?,?,?)";
		$stm=$conexion->prepare($sql);
		$stm->bind_param('issss',$documento,$nombre,$profesion,$bytesArchivo,$ruta);
		
		if($stm->execute()){
			echo 'imagen Insertada Exitosamente ';
			$consulta="select * from usuario where documento='{$documento}'";
			$resultado=mysqli_query($conexion,$consulta);
			echo '<br>';
			while ($row=mysqli_fetch_array($resultado)){
				echo $row['documento'].' - '.$row['nombre'].'<br/>';
				array_push($json['img'],array('documento'=>$row['documento'],'nombre'=>$row['nombre'],'profesion'=>$row['profesion'],'photo'=>base64_encode($row['nombre']),'ruta'=>$row['ruta_imagen']));
			}
			mysqli_close($conexion);
			
			echo '<br>';
			echo 'Objeto JSON 2';
			echo '<br>';
			echo json_encode($json);
		}
	}
?>