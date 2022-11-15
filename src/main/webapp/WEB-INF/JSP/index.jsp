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
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3>ASIGNACION DE ACTIVIDADES</h3>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                <tr style="text-align: center;">
                    <th>ID</th>
                    <th>DESCRIPCION</th>
                    <th>ESTADO</th>
                    <th>FECHA FINAL</th>
                    <th>ACCIONES</th>
                </tr>
                </thead>
                <%

                    Connection cnx = null;
                    Statement sta = null;
                    ResultSet rs = null;

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        cnx = DriverManager.getConnection("jdbc:postgresql://localhost:5432/actividades");

                        sta = cnx.createStatement();

                        rs = sta.executeQuery("select * from activities");

                        while (rs.next()){
                            %>
                                <tbody>
                                <tr class="text-center">
                                    <td><%=rs.getString(1)%></td>
                                    <td><%=rs.getString(2)%></td>
                                    <td><%=rs.getString(3)%></td>
                                    <td><%=rs.getString(4)%></td>
                                    <td style="text-align: center;">
                                        <a href="/updateActivity/<%=rs.getString(1)%>">
                                            <button class="btn btn-warning">Editar</button>
                                        </a>
                                        <a href="/deleteActivity/<%=rs.getString(1)%>">
                                            <button class="btn btn-danger" style="margin-left: 15px;">Eliminar</button>
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            <%
                        }

                    sta.close();
                    rs.close();
                    cnx.close();

                    }catch (Exception e){

                    }

                %>
            </table>
        </div>
    </div>
</div>

</body>
</html>