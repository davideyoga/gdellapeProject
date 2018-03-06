<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi</title>

<#include "import.ftl">
</head>
<body>
<#include "navbar.ftl">
<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Corsi erogati</h3>
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

    <div class="table-responsive">
        <table id="course_table" class="table table-hover table-bordered table-striped">
            <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

            <thead>
            <tr>
                <th>Codice</th>
                <th>Nome </th>
                <th>Descrizione</th>
                <th>Semestre</th>
                <th>Lingua</th>
                <th> - </th>
            </tr>
            </thead>

            <tbody>
                    <#list materials as course>
                    <tr>
                        <td>${course.code}</td>
                        <td>${course.name}</td>
                        <td>${course.description_ita}</td>
                        <td>${course.data}</td>
                        <td>${course.language_ita}</td>
                        <td>
                            <a href="CourseProfile?id=${course.idCourse}">Leggi di piu'</a>
                        </td>
                    </tr>
                    </#list>
            </tbody>
        </table>
        <div>
        </div>
    </div>
</div>
<#--tutti questi div sono inutili ma ci devono stare-->

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