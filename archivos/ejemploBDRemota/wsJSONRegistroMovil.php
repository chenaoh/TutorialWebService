<?PHP
$hostname_localhost="localhost";
$database_localhost="bd_usuario";
$username_localhost="root";
$password_localhost="";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

	$documento = $_POST["documento"];
	$nombre = $_POST["nombre"];
	$profesion = $_POST["profesion"];
	$imagen = $_POST["imagen"];

	$path = "imagenes/$nombre.jpg";

	$url = "http://$hostname_localhost/ejemploBDRemota/$path";

	file_put_contents($path,base64_decode($imagen));
	$bytesArchivo=file_get_contents($path);

	$sql="INSERT INTO usuario VALUES (?,?,?,?)";
	$stm=$conexion->prepare($sql);
	$stm->bind_param('isss',$documento,$nombre,$profesion,$bytesArchivo);
		
	if($stm->execute()){
		echo "registra";
	}else{
		echo "noRegistra";
	}
?>

