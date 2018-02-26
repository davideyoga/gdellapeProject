<!--questa pagina serve per vedere più dettagli di un docente-->
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Pagina Docente</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

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

    <div class="page-header personal-title">
        ${userCurrent.name} ${userCurrent.surname}
    </div>

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
                                    <#elseif userCurrent.receprion_hours_eng??>
                                        ${userCurrent.receprion_hours_eng}
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

        <div id="curr" class="tab-pane fade">
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
                                <#elseif userCurrent.curriculum_eng??>
                                    ${userCurrent.curriculum_eng}
                                <#else>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <#--div panel-group-->
        </div> <#--div id desc-->

        <div id="course" class="tab-pane fade">
            <div class="panel-group">

                <#--<#list courses>-->
                    <#--<div class="table-responsive" >-->
                        <#--<table class="table table-hover table-bordered table-striped">-->
                            <#--<thead>-->
                            <#--<tr>-->
                                <#--<th colspan="3">Insegnamenti associati a ${userCurrent.name}</th>-->
                            <#--</tr>-->
                            <#--</thead>-->
                            <#--<#items as  course>-->
                            <#--<tr>-->
                                <#--<td>Nome corso</td>-->
                                <#--<td >${course.name}</td>-->
                                <#--<td ><a href="CourseProfile?id=${course.idCourse}">Leggi di piu'</a></td>-->
                            <#--</tr>-->

                            <#--</#items>-->
                        <#--</table>-->
                    <#--</div>-->
                <#--<#else>-->
                    <#--<h3>Attualmente questo docente non ha corsi associati</h3>-->
                <#--</#list> -->

            </div> <#--div panel-group-->
        </div> <#--div id course-->


    </div> <#--div tab-content-->


</div>  <#--div container-->

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>