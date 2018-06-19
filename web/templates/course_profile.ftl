<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
<#--sono le informazioni specifiche della materia-->
    <title>
        Dettagli corso
    </title>

<#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('material').classList.remove('in');
            document.getElementById('material').classList.remove('active');
            document.getElementById('studyCourse').classList.remove('in');
            document.getElementById('studyCourse').classList.remove('active');
        }
    </script>

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Dettagli <#if course.name??>${course.name}<#else> </#if></h3>
            <h4 class="text-center"> <#if course.year??> anno accademico ${course.year}<#else> </#if></h4>
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
    <div class="col-md-2 col-xs-2 col-sm-2 my-menu text-center">
        <div class="text-center">
            <a href="ListCourseAn?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna alla lista dei corsi</a>
        </div>
    </div>

    <div class="container">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#basic">Dati di base</a></li>
            <li><a data-toggle="tab" href="#dublin">Descrittori di Dublino</a></li>
            <li><a data-toggle="tab" href="#material">Materiale</a></li>
            <li><a data-toggle="tab" href="#book">Libri associati</a></li>
            <li><a data-toggle="tab" href="#studyCourse">Lauree associate</a></li>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <h4>Nome corso: </h4>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.name}</p>
                                        </div>
                                    </div>
                                    <br><br>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Codice:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.code}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Anno:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.year}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Cfu:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.cfu}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Settore:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.sector}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Lingua:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.language}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Semestre:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <p>${course.semester}</p>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Docenti:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9 ">
                                            <#list listDocent as docent>
                                                <p class="personal1"><a class="btn btn-warning custom" href="UserProfile?email=${docent.email}&lng=IT">Prof.  ${docent.name} ${docent.surname}</a></p>
                                            </#list>
                                        </div>
                                    </div>



                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <div class="col-md-3 col-xs-3 col-sm-3">
                                            <p>Anni precedenti:</p>
                                        </div>
                                        <div class="col-md-9 col-xs-9 col-sm-9">
                                            <div>
                                            <#list courseRelatedByYear as anno >
                                                <p class="personal1"><a class="btn btn-info" href="CourseProfile?id=${anno.code}&year=${anno.year?right_pad(4)?substring(0, 4)?trim}&lng=IT">anno accademico ${anno.year}</a></p>
                                            </#list>
                                            </div>
                                        </div>
                                    </div>
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
                                        <p><#if course.prerequisite_ita?has_content>${course.prerequisite_ita}<#else>${course.prerequisite_eng}</#if></p>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <p><#if course.goals_ita?has_content>${course.goals_ita}<#else>${course.goals_eng}</#if></p>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <p><#if course.exame_mode_ita?has_content>${course.exame_mode_ita}<#else>${course.exame_mode_eng}</#if></p>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <p><#if course.teaching_mode_ita?has_content>${course.teaching_mode_ita}<#else>${course.teaching_mode_eng}</#if></p>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <p><#if course.syllabus_ita?has_content>${course.syllabus_ita}<#else>${course.syllabus_eng}</#if></p>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <p><#if course.note_ita?has_content>${course.note_ita}<#else>${course.note_eng}</#if></p>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                            <#if course.knowledge_ita?has_content>${course.knowledge_ita}<#else>${course.knowledge_eng}</#if>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <#if course.application_ita?has_content>${course.application_ita}<#else>${course.application_eng}</#if>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                            <#if course.evaluation_ita?has_content>${course.evaluation_ita}<#else>${course.evaluation_eng}</#if>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                            <#if course.communication_ita?has_content>${course.communication_ita}<#else>${course.communication_eng}</#if>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                            <#if course.lifelog_learning_skills_ita?has_content>${course.lifelog_learning_skills_ita}<#else>${course.lifelog_learning_skills_eng}</#if>
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
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                        <a href="ListMaterial?id=${course.idCourse}&lng=IT" class="btn btn-link my-text center-block m-b-5" role="button">Vai al materiale esterno</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div> <#--div panel-group-->
            </div> <#--div id material-->


            <div id="book" class="tab-pane fade">

                <div class="table-responsive">
                    <table id="book_table" class="table table-hover table-bordered table-striped">

                        <thead>
                        <tr>
                            <th>Codice</th>
                            <th>Titolo</th>
                            <th>Volume</th>
                            <th>Anno</th>
                            <th>editore</th>
                            <th>Link</th>
                        </tr>
                        </thead>

                        <tbody>
                        <#list listBook as book>
                        <tr>
                            <td>${book.code}</td>
                            <td>${book.title}</td>
                            <td>${book.volume}</td>
                            <td>${book.age}</td>
                            <td>${book.editor}</td>
                            <td>${book.link}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                </div><#--div id table responsive-->

        </div> <#--div id book-->

        <div id="studyCourse" class="tab-pane fade">
            <div class="tab-content">

                <div class="table-responsive">
                    <table id="Scourse_table" class="table table-hover table-bordered table-striped">
                        <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                        <thead>
                        <tr>
                            <th>Codice</th>
                            <th>Nome </th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                                <#list listStudyCourse as course>
                                <tr>
                                    <td>${course.code}</td>
                                    <td>${course.name}</td>
                                    <td>
                                        <a href="StudyCourseProfile?code=${course.code}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">Leggi di piu'</a>
                                    </td>
                                </tr>
                                </#list>
                        </tbody>
                    </table>

                </div>
            </div><#--div tabcontent-->
        </div> <#--div id studyCourse-->


    </div> <#--div tab-content-->


</div>  <#--div container-->
</div> <#--div row-->

<#include "tail.ftl">
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#book_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
<script type="text/javascript">
    $(function() {
        $("#Scourse_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>