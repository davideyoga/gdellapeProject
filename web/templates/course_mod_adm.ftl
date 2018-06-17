<!DOCTYPE html>
<html lang="it">
<head>
    <title>Modifica corso</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Modifica corso <#if course.name??>${course.name}<#else></#if></h3>
        </div>
    </div>
</div>

<div class="container">
    <#if message??>
        <div class="title">
            <h2>ATTENZIONE</h2>
            <div class="modalContent">
                <p>${message}</p>
            </div>
        </div>
    <#else>
    </#if>
</div>


<form action="ModAdmCourse" method="POST" id="mod" class="my-form" >

    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked text-center">
                <li class="active"><a data-toggle="pill" href="#basic">Informazioni di base</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Informazioni aggiuntive
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a data-toggle="pill" href="#aggiuntive">Italiano</a></li>
                        <li><a data-toggle="pill" href="#aggiuntive_en">Inglese</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Descrittori di Dublino
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a data-toggle="pill" href="#dub">Italiano</a></li>
                        <li><a data-toggle="pill" href="#dub_en">Inglese</a></li>
                    </ul>
                </li>
                <li><a data-toggle="pill" href="#notes_page">Note</a></li>
            </ul>
            <br>
            <div class="text-center"><button type="submit" form="mod" class="btn btn-default">Modifica corso</button></div>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center">
                <#list services as service>
                    <#if service.name == 'addMaterial'>
                        <#assign addMaterial= true>
                    </#if>
                    <#if service.name == 'addBook'>
                        <#assign addBook= true>
                    </#if>
                    <#if service.name == 'modAllCourse'>
                        <#assign mod= true>
                    </#if>
                </#list>
                <#if addMaterial>
                    <a href="ListMaterialNotAnonymous?id=${course.idCourse}" class="btn btn-warning my-text center-block m-b-5" role="button">gestione materiale esterno</a>
                    <#--<a href="AddMaterial?idCourse=${course.idCourse}" class="btn btn-warning my-text center-block m-b-5" role="button">gestione materiale esterno</a>-->
                </#if>
                <#if addBook>
                    <a href="GetListBook?idCourse=${course.idCourse}" class="btn btn-warning my-text center-block m-b-5" role="button">gestione libri</a>
                </#if>
                <#if mod>
                    <a href="/modCourseRelation?idCourse=${course.idCourse}&mode=module" class="btn btn-warning my-text center-block m-b-5" role="button">associa moduli</a>
                </#if>
                <#if mod>
                    <a href="/modCourseRelation?idCourse=${course.idCourse}&mode=borrowed" class="btn btn-warning my-text center-block m-b-5" role="button">mutua corso</a>
                </#if>
                <#if mod>
                    <a href="/modCourseRelation?idCourse=${course.idCourse}&mode=preparatory" class="btn btn-warning my-text center-block m-b-5" role="button">corsi propedeutici</a>
                </#if>
                <a href="ListCourse" class="btn btn-warning my-text center-block" role="button">Torna alla lista dei corsi</a>
            </div>
        </div>


        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content">


                    <div id="basic" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="codice">Codice:</label>
                            <input type="text" class="form-control" id="codice" name="code" value="<#if course.code??>${course.code}<#else></#if>">
                            <span class="help-block">Codice univoco che identifica il corso</span>
                        </div>
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" value="<#if course.name??>${course.name}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="cfu">Cfu:</label>
                            <input type="text" class="form-control" id="cfu" name="cfu" value="<#if course.cfu??>${course.cfu}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="settore">Settore:</label>
                            <input type="text" class="form-control" id="settore" name="sector" value="<#if course.sector??>${course.sector}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="lingua">Lingua:</label>
                            <input type="text" class="form-control" id="lingua" name="language" value="<#if course.language??>${course.language}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="semestre">Semestre:</label>
                            <select class="form-control" id="semestre" name="semester">
                                <option <#if course.semester == 1> selected</#if>>1</option>
                                <option <#if course.semester == 2> selected</#if>>2</option>
                            </select>
                        </div>
                    </div>
                <#--end basic-->


                    <div id="aggiuntive" class="tab-pane fade">
                        <div class="form-group">
                            <label for="pre">Prerequisiti:</label>
                            <textarea class="form-control" rows="5" id="pre" name="prerequisite_ita"><#if course.prerequisite_ita??>${course.prerequisite_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="obiettivi">Obiettivi:</label>
                            <textarea class="form-control" rows="5" id="obiettivi" name="goals_ita"><#if course.goals_ita??>${course.goals_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="esame">Modalità d'esame:</label>
                            <textarea class="form-control" rows="5" id="esame" name="exame_mode_ita"><#if course.exame_mode_ita??>${course.exame_mode_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="insegnamento">Modalità d'insegnamento:</label>
                            <textarea class="form-control" rows="5" id="insegnamento" name="teaching_mode_ita"><#if course.teaching_mode_ita??>${course.teaching_mode_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="sillabo">Sillabo:</label>
                            <textarea class="form-control" rows="5" id="sillabo" name="syllabus_ita"><#if course.syllabus_ita??>${course.syllabus_ita}</#if></textarea>
                        </div>
                    </div>
                <#--end aggiuntive-->


                    <div id="aggiuntive_en" class="tab-pane fade">
                        <div class="form-group">
                            <label for="pre_e">Prerequisite:</label>
                            <textarea class="form-control" rows="5" id="pre_e" name="prerequisite_eng"><#if course.prerequisite_eng??>${course.prerequisite_eng}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="goals">Goals:</label>
                            <textarea class="form-control" rows="5" id="goals" name="goals_eng"><#if course.goals_eng??>${course.goals_eng}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="esame_eng">Exame mode:</label>
                            <textarea class="form-control" rows="5" id="esame_eng" name="exame_mode_eng"><#if course.exame_mode_eng??>${course.exame_mode_eng}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="teaching">Teaching mode:</label>
                            <textarea class="form-control" rows="5" id="teaching" name="teaching_mode_eng"><#if course.teaching_mode_eng??>${course.teaching_mode_eng}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="syllabus">Syllabus:</label>
                            <textarea class="form-control" rows="5" id="syllabus" name="syllabus_eng"><#if course.syllabus_eng??>${course.syllabus_eng}</#if></textarea>
                        </div>
                    </div>
                <#--end aggiuntive_en-->


                    <div id="dub" class="tab-pane fade">
                        <div class="form-group">
                            <label for="know">Risultati di apprendimento:</label>
                            <textarea class="form-control" rows="5" id="know" name="knowledge_ita"><#if course.knowledge_ita??>${course.knowledge_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="appl">Campi d'utilizzo:</label>
                            <textarea class="form-control" rows="5" id="appl" name="application_ita"><#if course.application_ita??>${course.application_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="eva">Capacità di giudizio:</label>
                            <textarea class="form-control" rows="5" id="eva" name="evaluation_ita"><#if course.evaluation_ita??>${course.evaluation_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="comu">Capacità di comunicazione:</label>
                            <textarea class="form-control" rows="5" id="comu" name="communication_ita"><#if course.communication_ita??>${course.communication_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="lifelog">Lifelog learning skills:</label>
                            <textarea class="form-control" rows="5" id="lifelog" name="lifelog_learning_skills_ita"><#if course.lifelog_learning_skills_ita??>${course.lifelog_learning_skills_ita}</#if></textarea>
                        </div>
                    </div>
                <#--end dub-->


                    <div id="dub_en" class="tab-pane fade">
                        <div class="form-group">
                            <label for="know_en">Learnign Outcomes:</label>
                            <textarea class="form-control" rows="5" id="know_en" name="knowledge_eng"><#if course.knowledge_eng??>${course.knowledge_eng}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="appl_en">Application:</label>
                            <textarea class="form-control" rows="5" id="appl_en" name="application_eng"><#if course.application_eng??>${course.application_eng}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="eva_en">Evaluation:</label>
                            <textarea class="form-control" rows="5" id="eva_en" name="evaluation_eng"><#if course.evaluation_eng??>${course.evaluation_eng}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="comu_en">Communication:</label>
                            <textarea class="form-control" rows="5" id="comu_en" name="communication_eng"><#if course.communication_eng??>${course.communication_eng}</#if></textarea>
                        </div>
                        <div class="form-group"_en>
                            <label for="lifelog">Lifelog learning skills:</label>
                            <textarea class="form-control" rows="5" id="lifelog_en" name="lifelog_learning_skills_eng"><#if course.lifelog_learning_skills_eng??>${course.lifelog_learning_skills_eng}</#if></textarea>
                        </div>
                    </div>
                <#--end dub_en-->

                    <div id="notes_page" class="tab-pane fade">
                        <div class="form-group">
                            <label for="note">Note (in italiano):</label>
                            <textarea class="form-control" rows="5" id="note" name="note_ita"><#if course.note_ita??>${course.note_ita}</#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="notes">Notes (english):</label>
                            <textarea class="form-control" rows="5" id="notes" name="note_eng"><#if course.note_eng??>${course.note_eng}</#if></textarea>
                        </div>
                    </div>
                <#--end note-->

            </div>
        </div>
    </div>
    </div>

</form>

<!--modulo contatti, email, conclusione-->
    <#include "tail.ftl">
</body>