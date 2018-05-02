<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
<#--sono le informazioni specifiche della materia-->
    <title>
        Dettagli corso
    </title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Dettagli <#if course.name??>${course.name}<#else> corso</#if></h3>
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
    <div class="col-md-2 col-xs-2 my-menu text-center">
        <div class="text-center">
            <a href="ListCourseAn?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna alla lista dei corsi</a>
        </div>
    </div>

    <div class="container">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#basic">Dati di base</a></li>
            <li><a data-toggle="tab" href="#dublin">Descrittori di Dublino</a></li>
            <li><a data-toggle="tab" href="#material">Materiale</a></li>
        </ul>

        <div class="tab-content">
            <div id="basic" class="tab-pane fade in active">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#base">Dati di base</a>
                            </h4>
                        </div>
                        <div id="base" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <h4>Nome corso: </h4>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.name}</p>
                                        </div>
                                    </div>
                                    <br><br>
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Codice:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.code}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Anno:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.year}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Cfu:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.cfu}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Settore:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.sector}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Lingua:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.language}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Semestre:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.semester}</p>
                                        </div>
                                    </div>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>

                    <#--prerequisiti-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#pre">Prerequisiti</a>
                            </h4>
                        </div>
                        <div id="pre" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <p>${course.prerequisite_ita}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <#--obiettivi-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#goals">Obiettivi</a>
                            </h4>
                        </div>
                        <div id="goals" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <p>${course.goals_ita}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <#--exame mode-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#exam">Modalit&agrave d'esame</a>
                            </h4>
                        </div>
                        <div id="exam" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <p>${course.exame_mode_ita}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <#--teaching mode-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#teach">Modalit&agrave d'insegnamento</a>
                            </h4>
                        </div>
                        <div id="teach" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <p>${course.teaching_mode_ita}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <#--sillabo-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#syl">Sillabo</a>
                            </h4>
                        </div>
                        <div id="syl" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <p>${course.syllabus_ita}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                <#--note-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#note">Note</a>
                            </h4>
                        </div>
                        <div id="note" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <p>${course.note_ita}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                <#--div note-->

                </div> <#--div panel-group-->
            </div> <#--div id basic-->

            <div id="dublin" class="tab-pane fade">
                <div class="panel-group">

                    <#--Risultati di apprendimento-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#know">Risultati di apprendimento</a>
                                </h4>
                            </div>
                            <div id="know" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12">
                                            <#if course.knowledge_ita??>${course.knowledge_ita}</#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    <#--Campi d'utilizzo-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#appl">Campi d'utilizzo</a>
                            </h4>
                        </div>
                        <div id="appl" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <#if course.application_ita??>${course.application_ita}</#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                        <#--Capacità di giudizio-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#eva">Capacità di giudizio</a>
                                </h4>
                            </div>
                            <div id="eva" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12">
                                            <#if course.evaluation_ita??>${course.evaluation_ita}</#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <#--Capacità di comunicazione-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#comu">Capacità di comunicazione</a>
                                </h4>
                            </div>
                            <div id="comu" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12">
                                            <#if course.communication_ita??>${course.communication_ita}</#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <#--Lifelog learning skills-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#life">Lifelog learning skills</a>
                                </h4>
                            </div>
                            <div id="life" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12">
                                            <#if course.lifelog_learning_skills_ita??>${course.lifelog_learning_skills_ita}</#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                </div> <#--div panel-group-->
            </div> <#--div id dublin-->

            <div id="material" class="tab-pane fade">
                <div class="panel-group">

                <#--Materiale it-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <p>Materiale disponibile</p>
                            </h4>
                        </div>
                        <div id="mat" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-xs-12">
                                        <a href="ListMaterial?code=${course.code}&lng=IT" class="btn btn-link my-text center-block m-b-5" role="button">Vai al materiale esterno</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div> <#--div panel-group-->
            </div> <#--div id dublin-->


        </div> <#--div tab-content-->


    </div>  <#--div container-->
</div> <#--div row-->

<#include "tail.ftl">
</body>
</html>