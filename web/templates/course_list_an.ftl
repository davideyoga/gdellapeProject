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
<div class="row">
    <div class="col-md-2 col-xs-2 my-menu">
        <div class="text-center">
            <a href="home?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna alla home</a>
        </div>
        <div class="w3ls-heading page-header">
        </div>
        <div class="text-center">
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle btn-block" type="button" data-toggle="dropdown">Ricerca in base al docente
                    <span class="caret"></span>
                </button>
                <form class="dropdown-menu" action="ListCourseAn" method="GET" id="search">
                    <div class="my-background p-1 text-white">
                        <div class="form-group">
                            <label for="docent">Nome docente:</label>
                            <input type="text" class="form-control" id="docent" name="docent" value="">
                        </div>
                        <button type="submit" class="btn btn-default my-text center-block">Cerca</button>
                    </div>
                </form>
            </div>
            <br>
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle btn-block" type="button" data-toggle="dropdown">Ricerca in base al corso di laurea
                    <span class="caret"></span>
                </button>
                <form class="dropdown-menu" action="ListCourseAn" method="GET" id="search">
                    <div class="my-background p-1 text-white">
                        <div class="form-group">
                            <label for="studyCourse">Nome laurea:</label>
                            <input type="text" class="form-control" id="studyCourse" name="studyCourse" value="">
                        </div>
                        <button type="submit" class="btn btn-default my-text center-block">Cerca</button>
                    </div>
                </form>
            </div>
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
                    <th>Settore</th>
                    <th>Semestre</th>
                    <th>Lingua</th>
                    <th> - </th>
                </tr>
                </thead>

                <tbody>
                    <#list listCourse as course>
                    <tr>
                        <td>${course.code}</td>
                        <td>${course.name}</td>
                        <td>${course.sector}</td>
                        <td>${course.semester}</td>
                        <td>${course.language}</td>
                        <td>
                            <a href="CourseProfile?id=${course.idCourse}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">Leggi di piu'</a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <div>
            </div>
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