<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi</title>

    <#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('search').classList.remove('in');
            document.getElementById('search').classList.remove('active');
        }
    </script>
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
        <ul class="nav nav-pills nav-stacked text-center">
            <li class="active"><a data-toggle="pill" href="#list">Lista corsi</a></li>
            <li><a data-toggle="pill" href="#search">Ricerca avanzata</a></li>
        </ul>
    </div>

    <div class="container">

        <div class="col-md-12 col-xs-12">
            <div class="tab-content">

                <div id="list" class="tab-pane fade in active">
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

                    </div>
                </div><#--div list-->

                <div id="search" class="tab-pane fade in active">
                    <form class="my-form" action="ListCourseAn" method="GET">

                    <#--<div class="form-group">-->
                    <#--<label for="docent">Nome docente:</label>-->
                    <#--<input type="text" class="form-control" id="docent" name="docent" value="">-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                    <#--<label for="studyCourse">Nome laurea:</label>-->
                    <#--<input type="text" class="form-control" id="studyCourse" name="studyCourse" value="">-->
                    <#--</div>-->
                        <div class="form-group">
                            <label for="sel1">Seleziona Docente:</label>
                            <select class="form-control" id="sel1" name="docent">
                                <option value="" selected>seleziona docente</option>
                                <#list listTheacher as teach>
                                    <option value="${teach.id}">${teach.name} ${teach.surname}</option>
                                    <#--<option value="${teach.name}">${teach.name} ${teach.surname}</option>-->
                                </#list>
                            </select>
                            <br>
                        </div>

                        <button type="submit" class="btn btn-default my-text center-block">Cerca</button>

                    </form>

                </div><#--div search-->

            </div><#--div tabcontent-->
        </div> <#--div col md 12-->
    </div><#--div container-->
</div><#--div row-->

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