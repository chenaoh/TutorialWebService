<?PHP
$hostname_localhost ="localhost";
$database_localhost ="bd_usuario";
$username_localhost ="root";
$password_localhost ="";

$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from usuario";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$result["documento"]=$registro['documento'];
			$result["nombre"]=$registro['nombre'];
			$result["profesion"]=$registro['profesion'];
			$result["ruta_imagen"]=$registro['ruta_imagen'];
			$json['usuario'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>

