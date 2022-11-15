<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.sql.*" %>
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
            <a href="/registerEmployye" class="nav-item nav-link" style="font-size: 20px;">Resgistrar Empleado</a>
        </div>
    </div>
</nav>
<hr>

<div class="container col-md-7">
    <div class="card">
        <div class="card-header">
            <h3>Actualizar Actividad</h3>
        </div>

        <form class="row g-3 needs-validation" style="margin-left: 10px;margin-top: 5px;" action="/updateActivity/${id}" method="post"  novalidate>
            <!-- Descripcion de la actividad -->
            <div class="col-md-5">
                <label class="form-label">Descripcion:</label>
                <input name="descripcion"  type="text" class="form-control" required>
            </div>
            <!-- Estado de la actividad -->
            <div class="col-md-4">
                <label class="form-label">Estado De La Actividad:</label>
                <select class="form-select" formControlName="estado" name="estado" required>
                    <option selected disabled value="Seleccione">Seleccione</option>
                    <option value="PENDIENTE">PENDIENTE</option>
                    <option value="REALIZADA">REALIZADA</option>
                </select>
            </div>
            <!-- Fecha de finalizacion -->
            <div class="col-md-4">
                <label class="form-label">Fecha De Finalizacion:</label>
                <input name="fechaFinal" type="date" class="form-control" required>
            </div>

            <!-- Empleado Acardo de la actividad -->
            <div class="col-md-5">
                <label class="form-label">Empleado Designado:</label>
                <select class="form-select" name="id_empleado" required>
                    <option selected disabled value="Seleccione">Seleccione</option>
                    <%

                        Connection cnx = null;
                        Statement sta = null;
                        ResultSet rs = null;

                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            cnx = DriverManager.getConnection("jdbc:postgresql://localhost:5432/actividades");

                            sta = cnx.createStatement();

                            rs = sta.executeQuery("select * from employye");

                            while (rs.next()){
                    %>

                    <option value="<%=rs.getString(1)%>"><%=rs.getString(4)%> - <%=rs.getString(3)%> <%=rs.getString(2)%></option>

                    <%
                            }

                            sta.close();
                            rs.close();
                            cnx.close();

                        }catch (Exception e){

                        }

                    %>

                </select>
            </div>
            <!-- boton guaradar actividad -->
            <div class="col-12" style="text-align: center;">
                <button class="btn btn-danger" style="background-color: rgb(0, 255, 128);color: black;border-color: darkgray; margin-bottom: 10px;">Actualizar Actividad</button>
            </div>
        </form>
    </div>
</div>


</body>
</html>