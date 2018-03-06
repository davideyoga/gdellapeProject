<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista corsi</title>

    <!--librerie-->
    <#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista dei corsi</h3>
        </div>
    </div>
</div>

<div class="container">
    <div>
        <#if message??>
            <div class="jumbotron">
                ${message}
            </div>
        <#else>
        </#if>
    </div>
</div>

<div class="container">

    <div class="table-responsive" >
        <table id="course_table" class="table table-hover table-bordered table-striped">
            <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

            <thead>
            <tr>
                <th>Codice</th>
                <th>Nome </th>
                <th>Settore</th>
                <th>Semestre</th>
                <th>Lingua</th>
                <th> - </th>
                <th> - </th>
                <th> - </th>
            </tr>
            </thead>

            <tbody>
                    <#list courses as course>
                    <tr>
                        <td>${course.code}</td>
                        <td>${course.name}</td>
                        <td>${course.sector}</td>
                        <td>${course.semester}</td>
                        <td>${course.language}</td>
                        <td>
                            <a href="ModAdmCourse?id=${course.idCourse}">modifica corso</a>
                        </td>
                        <td>
                            <a href="DeleteCourse?id=${course.idCourse}" onclick="return ConfirmDeleteCourse(${course.code},${course.name})">Cancella Corso</a>
                        </td>
                        <td>
                            <a href="modAssociationCourseWithUser?id=${course.idCourse}">associa docente</a>
                        </td>

                    </tr>
                    </#list>
            </tbody>
        </table>
    </div>
</div>

<!--modulo contatti, email, conclusione-->
    <#include "tail.ftl">
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#course_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>