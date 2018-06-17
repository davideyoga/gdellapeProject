<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista corsi</title>

    <!--librerie-->
    <#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            carica();
        }
    </script>
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

<div class="row">
    <div class="col-md-2 col-xs-2 my-menu">
        <div class="text-center"><a href="/HomeBackOffice" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
    </div>

    <div class="col-md-9 col-xs-9">

        <div>
            <form class="" action="ListCourse" method="GET">

                <div id="Accyear" class="form-group col-xs-2">
                    <label for="selectElementId">anno accademico:</label>
                    <select id="selectElementId" name="year" class="form-control">

                    </select>
                </div>
                <br>

                <div class="form-group">
                    <button type="submit" class="btn btn-default">Cerca</button>
                </div>

            </form>

        </div>

        <div class="w3ls-heading page-header">
        </div>
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
                    <th>Anno</th>
                    <th> - </th>
                    <th> - </th>
                    <th> - </th>
                </tr>
                </thead>

                <tbody>
                    <#list courses as course>
                    <tr>
                        <td>${course.code}</td>
                        <td>${course.name?lower_case}</td>
                        <td>${course.sector?lower_case}</td>
                        <td>${course.semester?lower_case}</td>
                        <td>${course.language?lower_case}</td>
                        <td>${course.year?lower_case}</td>
                        <td>
                            <a class="btn btn-primary btn-sm" href="ModAdmCourse?id=${course.idCourse}">modifica corso</a>
                        </td>
                        <td>
                            <a class="btn btn-danger btn-sm" href="DeleteCourse?id=${course.idCourse}" onclick="return ConfirmDeleteCourse(${course.code},${course.name})">Cancella Corso</a>
                        </td>
                        <td>
                            <a class="btn btn-success btn-sm" href="modAssociationCourseWithUser?id=${course.idCourse}">associa docente</a>
                        </td>

                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>

    </div><#--end col md 10-->
</div>
<#--end row-->


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