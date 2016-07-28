<?php

isset($_POST['id']) ? $id = $_POST['id'] : $id = "";
isset($_POST['codigo']) ? $codigo = $_POST['codigo'] : $codigo = "";
isset($_POST['nombre']) ? $nombre = $_POST['nombre'] : $nombre = "";
isset($_POST['apellido']) ? $apellido = $_POST['apellido'] : $apellido = "";
isset($_POST['cedula']) ? $cedula = $_POST['cedula'] : $cedula = "";
isset($_POST['edad']) ? $edad = $_POST['edad'] : $edad = "";
isset($_POST['semestre']) ? $semestre = $_POST['semestre'] : $semestre = "";
isset($_POST['type']) ? $accion = $_POST['type'] : $accion = "";


echo '[{"res" : "' . $codigo . '"}]';
?>