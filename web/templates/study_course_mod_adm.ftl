<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Modifica dati della laurea: ${studyCourse.name}</h3>
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

<form action="AdmModStudyCourse" method="POST" id="mod" class="my-form" >

    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu text-center">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#base">Dati di base</a></li>
                <li><a data-toggle="pill" href="#extra_it">Informazioni aggiuntive</a></li>
                <li><a data-toggle="pill" href="#extra_en">Informazioni in inglese</a></li>
                <#--<li><a data-toggle="pill" href="#association">Associazioni</a></li>-->
            </ul>
            <br>
            <button type="submit" form="mod" class="btn btn-default">Modifica corso di Laurea</button>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center">
                <a href="AdmGetListStudyCourse" class="btn btn-warning my-text center-block" role="button">Torna alla lista dei corsi di studio</a>
            </div>
        </div>


        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content" >


                    <div id="base" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="cod">Codice:</label>
                            <input type="text" class="form-control" id="cod" name="code" value="<#if studyCourse.code??>${studyCourse.code}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" value="<#if studyCourse.name??>${studyCourse.name}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="dur">Durata:</label>
                            <input type="number" min="1" max="10" class="form-control" id="dur" name="duration" value= "<#if studyCourse.duration??>${studyCourse.duration}<#else></#if>">
                        </div>
                        <#--<div class="form-group">-->
                            <#--<label for="class">Classe:</label>-->
                            <#--<input type="text" class="form-control" id="class" name="class" value="<#if studyCourse.class??>${studyCourse.class}<#else></#if>">-->
                        <#--</div>-->
                        <div class="form-group">
                            <label for="seat">posti disponibili:</label>
                            <input type="text" class="form-control" id="seat" name="seat" value="<#if studyCourse.seat??>${studyCourse.seat}<#else></#if>">
                        </div>
                    </div>
                <#--end access-->


                    <div id="extra_it" class="tab-pane fade">
                        <div class="form-group">
                            <label for="lang">Lingua:</label>
                            <input type="text" class="form-control" id="lang" name="language_ita" value="<#if studyCourse.language_ita??>${studyCourse.language_ita}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="descr_it">Descrizione:</label>
                            <textarea class="form-control" rows="5" id="descr_it" name="descpription_ita"><#if studyCourse.description_ita??>${studyCourse.description_ita}<#else></#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="dep_it">Dipartimento:</label>
                            <input type="text" class="form-control" id="dep_it" name="department_ita" value="<#if studyCourse.department_ita??>${studyCourse.department_ita}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="level_it">Livello:</label>
                            <input type="text" class="form-control" id="level_it" name="level_ita" value="<#if studyCourse.level_ita??>${studyCourse.level_ita}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="acc_type_it">Modalità d'accesso:</label>
                            <input type="text" class="form-control" id="acc_type_it" name="access_type_ita" value="<#if studyCourse.accessType_ita??>${studyCourse.accessType_ita}<#else></#if>">
                        </div>
                    </div>
                <#--end extra_it-->


                    <div id="extra_en" class="tab-pane fade">
                        <div class="form-group">
                            <label for="lang_en">Language:</label>
                            <input type="text" class="form-control" id="lang_en" name="language_eng" value="<#if studyCourse.language_eng??>${studyCourse.language_eng}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="descr_en">Description:</label>
                            <textarea class="form-control" rows="5" id="descr_en" name="descpription_eng"><#if studyCourse.description_eng??>${studyCourse.description_eng}<#else></#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="dep_en">Department:</label>
                            <input type="text" class="form-control" id="dep_en" name="department_eng" value="<#if studyCourse.department_eng??>${studyCourse.department_eng}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="level_en">Level:</label>
                            <input type="text" class="form-control" id="level_en" name="level_eng" value="<#if studyCourse.level_eng??>${studyCourse.level_eng}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="acc_type_en">Access type:</label>
                            <input type="text" class="form-control" id="acc_type_en" name="access_type_eng" value="<#if studyCourse.accessType_eng??>${studyCourse.accessType_eng}<#else></#if>">
                        </div>
                    </div>
                <#--end extra_en-->


                    <#--<div id="association" class="tab-pane fade">-->
                        <#--<a href="ModAssociationStudyCourseWithCourse?idStudyCourse=${studyCourse.id}">Mod Association With Course</a>-->
                        <#--<div class="panel panel-default">-->
                            <#--<div class="panel-heading">Attualmente il corso di laurea include i seguenti corsi</div>-->
                            <#--<div class="panel-body">-->
                                <#--<#list listCourses as course>-->
                                    <#--<div class="checkbox">-->
                                        <#--<label><input type="checkbox" value="${course.name}" <#if listCourseByStudyCourse?seq_contains(course) >checked<#else></#if>>${course.name}</label>-->
                                    <#--</div>-->
                                <#--</#list>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->

                </div>
            </div>
        </div>
    </div>

</form>


<#include "tail.ftl">
</body>
</html>