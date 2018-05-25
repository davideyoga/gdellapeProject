<!--questa pagina serve per vedere più dettagli di un docente-->
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Profilo Utente</title>

    <!--librerie-->
<#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('curr').classList.remove('in');
            document.getElementById('curr').classList.remove('active');
            document.getElementById('course').classList.remove('in');
            document.getElementById('course').classList.remove('active');
        }
    </script>
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>${userCurrent.name} ${userCurrent.surname}</h3>
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
            <a href="ListUser?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna alla lista dei docenti</a>
        </div>
    </div>

    <div class="container">

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#cont">Contatti</a></li>
            <li><a data-toggle="tab" href="#curr">Curriculum</a></li>
            <li><a data-toggle="tab" href="#course">Corsi</a></li>
        </ul>

        <div class="tab-content">
            <div id="cont" class="tab-pane fade in active">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">Contatti</h4></div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Email: </p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${userCurrent.email}</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Numero di telefono:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p><#if userCurrent.number??>
                                            ${userCurrent.number?string.computer}
                                        <#else>Non presente</#if></p>
                                    </div>
                                </div>
                                <br>

                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Ricevimento:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p><#if userCurrent.receprion_hours_ita??>
                                            ${userCurrent.receprion_hours_ita}
                                        <#else>
                                        </#if></p>
                                    </div>
                                </div>
                                <br>
                            </div> <#--div row-->
                        </div><#--div panel-body-->
                    </div> <#--div panel-default-->
                </div> <#--div panel-group-->
            </div> <#--div id contact-->

            <div id="curr" class="tab-pane fade in active">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">Curriculum</h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-xs-12">
                                    <#if userCurrent.curriculum_ita??>
                                        ${userCurrent.curriculum_ita}
                                    <#else>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <#--div panel-group-->
            </div> <#--div id desc-->

            <div id="course" class="tab-pane fade in active">
                <div class="panel-group">

                    <div class="table-responsive">
                        <table id="course_table" class="table table-hover table-bordered table-striped">
                            <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                            <thead>
                            <tr>
                                <th>Codice</th>
                                <th>Nome </th>
                                <th> - </th>
                            </tr>
                            </thead>

                            <tbody>
                        <#list courses as course>
                        <tr>
                            <td>${course.code}</td>
                            <td>${course.name}</td>
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

                </div> <#--div panel-group-->
            </div> <#--div id course-->


        </div> <#--div tab-content-->


    </div>  <#--div container-->
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