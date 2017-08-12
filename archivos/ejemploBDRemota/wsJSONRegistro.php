<?PHP
$hostname_localhost="localhost";
$database_localhost="bd_usuario";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["documento"]) && isset($_GET["nombre"]) && isset($_GET["profesion"])){
		$documento=$_GET['documento'];
		$nombre=$_GET['nombre'];
		$profesion=$_GET['profesion'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO usuario(documento, nombre, profesion) VALUES ('{$documento}','{$nombre}','{$profesion}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM usuario WHERE documento = '{$documento}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['usuario'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["documento"]=0;
			$resulta["nombre"]='No Registra';
			$resulta["profesion"]='No Registra';
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["documento"]=0;
			$resulta["nombre"]='WS No retorna';
			$resulta["profesion"]='WS No retorna';
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}

?>

