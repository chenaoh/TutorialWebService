<?PHP
$hostname_localhost ="localhost";
$database_localhost ="bd_usuario";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["documento"])){
		$documento=$_GET["documento"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from usuario where documento= '{$documento}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["documento"]=$registro['documento'];
			$result["nombre"]=$registro['nombre'];
			$result["profesion"]=$registro['profesion'];
			$result["ruta_imagen"]=$registro['ruta_imagen'];
			$json['usuario'][]=$result;
		}else{
			$resultar["documento"]=0;
			$resultar["nombre"]='no registra';
			$resultar["profesion"]='no registra';
			$result["ruta_imagen"]='no registra';
			$json['usuario'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['usuario'][]=$resultar;
		echo json_encode($json);
	}
?>