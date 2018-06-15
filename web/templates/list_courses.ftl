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
        <div class="text-center"><a href="/home" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
        <div class="w3ls-heading page-header">
        </div>
        <ul class="nav nav-pills nav-stacked text-center">
            <li class="active"><a data-toggle="pill" href="#list">Lista corsi</a></li>
            <li><a data-toggle="pill" href="#search">Cerca un corso</a></li>
        </ul>

        <div class="text-center">
            <a href="ListCourse" class="btn btn-default" role="button">azzera risultati</a>
        </div>
    </div>

    <div class="col-md-12 col-xs-12">

        <div class="tab-content">

            <div id="list" class="tab-pane fade in active">
                <div class="table-responsive" >
                    <table class="table table-hover table-bordered table-striped">

                        <thead>
                        <tr>
                            <th>nome materia</th>
                            <th>codice materia</th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                            <#list courses as course>
                                <tr>
                                    <td>${course.name}</td>
                                    <td>${course.code}</td>
                                    <td>
                                        <a href="ModCourse?id=${course.idCourse}">modifica corso</a>
                                    </td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div><#--end table responsive-->
            </div><#--div list-->

            <div id="search" class="tab-pane fade">
                <form class="my-form" action="ListCourse" method="GET">

                    <div class="row">
                        <div class="form-group col-xs-3">
                            <label for="name">Cerca per nome:</label>
                            <input type="text" class="form-control" id="name" name="name" value="">
                        </div>
                    </div>

                    <div id="Accyear" class="row hidden">
                        <div class="form-group col-xs-2">
                            <label for="selectElementId">anno accademico:</label>
                            <select id="selectElementId" name="year" class="form-control">

                            </select>
                        </div>
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

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="code">codice del corso:</label>
                                            <input type="text" class="form-control" id="code" name="code" value="">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="sel1">Docente:</label>
                                            <select class="form-control" id="sel1" name="docent">
                                                <option value="" selected>seleziona docente</option>
                                                <#list listTheacher as teach>
                                                    <option value="${teach.id}">${teach.name} ${teach.surname}</option>
                                                </#list>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="sel2">corso di laurea:</label>
                                            <select class="form-control" id="sel2" name="studyCourse">
                                                <option value="" selected>seleziona laurea</option>
                                            <#list allStudyCourse as course>
                                                <option value="${course.id}">${course.name}</option>
                                            </#list>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="sector">settore:</label>
                                            <input type="text" class="form-control" id="sector" name="sector" value="">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-2">
                                            <label for="sem">semestre:</label>
                                            <select class="form-control" id="sem">
                                                <option value="1">1</option>
                                                <option value="2">2</option>

                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="language">lingua:</label>
                                            <input type="text" class="form-control" id="language" name="language" value="">
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-default my-text center-block">Cerca</button>

                </form>

            </div><#--div search-->

        </div><#--div tabcontent-->
    </div><#--end col md 12-->
</div><#--end row-->


<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>