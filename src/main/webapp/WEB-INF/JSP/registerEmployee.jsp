<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>MyApp</title>
    <base href="/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark  bg-dark" >
    <button class="navbar-toggler" type="button"  data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a href="/init" class="nav-item nav-link" style="font-size: 20px;">Actividades</a>
            <a href="/newActivity" class="nav-item nav-link" style="font-size: 20px;">Nueva Actividad</a>
            <a href="/registerEmployee" class="nav-item nav-link" style="font-size: 20px;">Resgistrar Empleado</a>
        </div>
    </div>
</nav>
<hr>
<div class="container col-md-7">
    <div class="card">
        <div class="card-header">
            <h3>Registro de empleados</h3>
        </div>
        <form class="row g-3 needs-validation" style="margin-left: 10px;margin-top: 5px;" novalidate
              action="registroEmpleado" method="post">
            <!-- Nombre Empleado-->
            <div class="col-md-4">
                <label for="validationCustom01" class="form-label">Nombre:</label>
                <input name="empName" type="text" class="form-control" id="validationCustom01" required>
            </div>
            <!-- Apellido Empleado -->
            <div class="col-md-4">
                <label for="validationCustom02" class="form-label">Apellido:</label>
                <input name="empLastName" type="text" class="form-control" id="validationCustom02" required>
            </div>
            <!-- Numero Documento -->
            <div class="col-md-4">
                <label for="validationCustom03" class="form-label">Numero De Documento:</label>
                <input name="empDocumentNumber" type="number" class="form-control" id="validationCustom03" required>
            </div>
            <!-- Telefono -->
            <div class="col-md-4">
                <label for="validationCustom04" class="form-label">Telefono:</label>
                <input name="empPhone" type="number" class="form-control" id="validationCustom04" required>
            </div>

            <!-- boton guaradar actividad -->
            <div class="col-12" style="text-align: center;">
                <button class="btn btn-danger" style="background-color: rgb(0, 183, 255);color: black;border-color: darkgray; margin-bottom: 10px;">Registrar Empleado</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>