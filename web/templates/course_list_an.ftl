<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi</title>

    <#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('search').classList.remove('in');
            document.getElementById('search').classList.remove('active');
            carica();
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

<label>
    <select id="selectElementId" >

    </select>
</label>

<div class="row">
    <div class="col-md-2 col-xs-2 col-sm-2 my-menu">
        <div class="text-center">
            <a href="home?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna alla home</a>
        </div>
        <div class="w3ls-heading page-header">
        </div>
        <ul class="nav nav-pills nav-stacked text-center">
            <li class="active"><a data-toggle="pill" href="ListCourseAn?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>#list">Lista corsi</a></li>
            <li><a data-toggle="pill" href="#search">Cerca un corso</a></li>
        </ul>

        <div class="text-center">
            <a href="ListCourseAn?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-default" role="button">azzera risultati</a>
        </div>
    </div>

    <div class="col-md-10 col-xs-10 col-sm-10">
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
                        <label for="name">Cerca per nome:</label>
                        <input type="text" class="form-control" id="name" name="name" value="">
                    </div>

                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#advSearch">Ricerca avanzata</a>
                                </h4>
                            </div>
                            <div id="advSearch" class="panel-collapse collapse">
                                <div class="panel-body">

                                    <div class="form-group">
                                        <label for="code">cerca in base al codice del corso:</label>
                                        <input type="text" class="form-control" id="code" name="code" value="">
                                    </div>

                                <#--<div class="form-group">-->
                                <#--<label for="sel1">cerca in base al docente:</label>-->
                                <#--<select class="form-control" id="sel1" name="docent">-->
                                <#--<option value="" selected>seleziona docente</option>-->
                                <#--<#list listTheacher as teach>-->
                                <#--<option value="${teach.id}">${teach.name} ${teach.surname}</option>-->
                                <#--</#list>-->
                                <#--</select>-->
                                <#--<br>-->
                                <#--</div>-->

                                    <div class="form-group">
                                        <label for="studyCourse">cerca in base al corso di laurea:</label>
                                        <input type="text" class="form-control" id="studyCourse" name="studyCourse" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="sector">cerca in base al settore:</label>
                                        <input type="text" class="form-control" id="sector" name="sector" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="semester">cerca in base al semestre:</label>
                                        <input type="text" class="form-control" id="semester" name="semester" value="">
                                    </div>

                                    <div class="form-group">
                                        <label for="language">cerca in base alla lingua di insegnamento:</label>
                                        <input type="text" class="form-control" id="language" name="language" value="">
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-default my-text center-block">Cerca</button>

                </form>

            </div><#--div search-->

        </div><#--div tabcontent-->
    </div> <#--div col md 12-->
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