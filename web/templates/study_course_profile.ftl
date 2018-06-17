<!DOCTYPE html>
<html lang="it">
<head>
<#--sono le informazioni specifiche del corso di studi-->
    <title>Corso di studio</title>

    <#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('desc').classList.remove('in');
            document.getElementById('desc').classList.remove('active');
            document.getElementById('course').classList.remove('in');
            document.getElementById('course').classList.remove('active');
        }
    </script>

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Infomazioni sul corso di studi in ${studyCourse.name}</h3>
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
            <a href="ListStudyCourses?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna alla lista dei corsi di studio</a>
        </div>
    </div>


    <div class="container">

        <div class="text-center">
            <h4>Anno Accademico di riferimento</h4>
            <ul class="pagination">
                <li><a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear - 1)?string.computer}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>&code=${studyCourse.code} ">« Anno precedente</a></li>
                <li class="disabled"><a href="#">${accademicYear}</a></li>
                <li><a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear + 1)?string.computer}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>&code=${studyCourse.code} ">Anno successivo »</a></li>
            </ul>
        </div>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#basic">Dati di base</a></li>
            <li><a data-toggle="tab" href="#desc">Descrizione</a></li>
            <li><a data-toggle="tab" href="#course">Materie collegate</a></li>
        </ul>

        <div class="tab-content">
            <div id="basic" class="tab-pane fade in active">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">Dati di base</h4></div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <h4>Nome percorso: </h4>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.name}</p>
                                    </div>
                                </div>
                                <br><br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Codice:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.code}</p>
                                    </div>
                                </div>
                                <br>

                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Lingua principale:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.language_ita}</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Durata:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.duration} anni</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Dipartimento:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.department_ita}</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Livello EQF:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.level_ita}</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Modalit&agrave d'accesso</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.accessType_ita}</p>
                                    </div>
                                </div>
                                <br>
                            <#--<#if studyCourse.classes??>-->
                            <#--<div class="col-md-12 col-xs-12">-->
                            <#--<div class="col-md-3 col-xs-3 col-sm-3">-->
                            <#--<p>Classe</p>-->
                            <#--</div>-->
                            <#--<div class="col-md-9 col-xs-9 col-sm-9">-->
                            <#--<p> ${studyCourse.classes}</p>-->
                            <#--</div>-->
                            <#--</div>-->
                            <#--<br>-->
                            <#--</#if>-->
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Posti disponibili</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.seat}</p>
                                    </div>
                                </div>
                                <br>
                            </div>
                        </div>

                    </div> <#--end panel default-->

                </div> <#--div panel-group-->
            </div> <#--div id basic-->

            <div id="desc" class="tab-pane fade in active">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">Descrizione</h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-xs-12">
                                    <p>${studyCourse.description_ita}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <#--div panel-group-->
            </div> <#--div id desc-->

            <div id="course" class="tab-pane fade in active">
                <div class="panel-group">

                <#list courses>
                    <div class="table-responsive" >
                        <table class="table table-hover table-bordered table-striped">
                            <thead>
                            <tr>
                                <th colspan="3">Insegnamenti associati a ${studyCourse.name}</th>
                                <#--<th colspan="3">Insegnamenti associati a ${studyCourse.name}</th>-->
                            </tr>
                            </thead>
                            <#items as  course>
                            <tr>
                                <td >${course.name}</td>
                                <td ><a href="CourseProfile?id=${course.idCourse}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">Leggi di piu'</a></td>
                            </tr>

                            </#items>
                        </table>
                    </div>
                <#else>
                    <h3>Attualmente non ci sono insegnamenti associati al corso di studi</h3>
                </#list>

                </div> <#--div panel-group-->
            </div> <#--div id course-->
        </div> <#--div tab-content-->
    </div>  <#--div container-->

</div>



<#include "tail.ftl">
</body>
</html>