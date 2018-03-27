<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Creazione Corso</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Creazione di un nuovo corso</h3>
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

<form action="CreateCourse" method="POST" id="create" class="my-form" >
<#--<div class="row" style="border: #00d21f 5px solid">-->
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
            <div class="text-center"><button type="submit" form="create" class="btn btn-default">Crea corso</button></div>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center"><a href="/HomeBackOffice" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
        </div>


        <div class="container">
            <div class="col-md-12 col-xs-12">
                <div class="tab-content">

                    <div id="basic" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="codice">Codice:</label>
                            <input type="text" class="form-control" id="codice" name="code" placeholder="Codice del corso">
                            <span class="help-block">Codice univoco che identifica il corso</span>
                        </div>
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" placeholder="Nome del corso">
                        </div>
                        <div class="form-group">
                            <label for="cfu">Cfu:</label>
                            <input type="number" min="0" max="180" value="1" class="form-control" id="cfu" name="cfu" placeholder="Es. 6">
                        </div>
                        <div class="form-group">
                            <label for="settore">Settore:</label>
                            <input type="text" class="form-control" id="settore" name="sector" placeholder="Settore del corso">
                        </div>
                        <div class="form-group">
                            <label for="lingua">Lingua:</label>
                            <input type="text" class="form-control" id="lingua" name="language" placeholder="Lingua del corso">
                        </div>
                        <div class="form-group">
                            <label for="semestre">Semestre:</label>
                            <select class="form-control" id="semestre" name="semester">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                <#--end basic-->


                    <div id="aggiuntive" class="tab-pane fade">
                        <div class="form-group">
                            <label for="pre">Prerequisiti:</label>
                            <textarea class="form-control" rows="5" id="pre" name="prerequisite_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="obiettivi">Obiettivi:</label>
                            <textarea class="form-control" rows="5" id="obiettivi" name="goals_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="esame">Modalità d'esame:</label>
                            <textarea class="form-control" rows="5" id="esame" name="exame_mode_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="insegnamento">Modalità d'insegnamento:</label>
                            <textarea class="form-control" rows="5" id="insegnamento" name="teaching_mode_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="sillabo">Sillabo:</label>
                            <textarea class="form-control" rows="5" id="sillabo" name="syllabus_ita"></textarea>
                        </div>
                    </div>
                <#--end aggiuntive-->


                    <div id="aggiuntive_en" class="tab-pane fade">
                        <div class="form-group">
                            <label for="pre_e">Prerequisite:</label>
                            <textarea class="form-control" rows="5" id="pre_e" name="prerequisite_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="goals">Goals:</label>
                            <textarea class="form-control" rows="5" id="goals" name="goals_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="esame_eng">Exame mode:</label>
                            <textarea class="form-control" rows="5" id="esame_eng" name="exame_mode_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="teaching">Teaching mode:</label>
                            <textarea class="form-control" rows="5" id="teaching" name="teaching_mode_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="syllabus">Syllabus:</label>
                            <textarea class="form-control" rows="5" id="syllabus" name="syllabus_eng"></textarea>
                        </div>
                    </div>
                <#--end aggiuntive_en-->


                    <div id="dub" class="tab-pane fade">
                        <div class="form-group">
                            <label for="know">Risultati di apprendimento:</label>
                            <textarea class="form-control" rows="5" id="know" name="knowledge_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="appl">Campi d'utilizzo:</label>
                            <textarea class="form-control" rows="5" id="appl" name="application_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="eva">Capacità di giudizio:</label>
                            <textarea class="form-control" rows="5" id="eva" name="evaluation_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="comu">Capacità di comunicazione:</label>
                            <textarea class="form-control" rows="5" id="comu" name="communication_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="lifelog">Lifelog learning skills:</label>
                            <textarea class="form-control" rows="5" id="lifelog" name="lifelog_learning_skills_ita"></textarea>
                        </div>
                    </div>
                <#--end dub-->


                    <div id="dub_en" class="tab-pane fade">
                        <div class="form-group">
                            <label for="know_en">Learnign Outcomes:</label>
                            <textarea class="form-control" rows="5" id="know_en" name="knowledge_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="appl_en">Application:</label>
                            <textarea class="form-control" rows="5" id="appl_en" name="application_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="eva_en">Evaluation:</label>
                            <textarea class="form-control" rows="5" id="eva_en" name="evaluation_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="comu_en">Communication:</label>
                            <textarea class="form-control" rows="5" id="comu_en" name="communication_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="lifelog_en">Lifelog learning skills:</label>
                            <textarea class="form-control" rows="5" id="lifelog_en" name="lifelog_learning_skills_eng"></textarea>
                        </div>
                    </div>
                <#--end dub_en-->

                    <div id="notes_page" class="tab-pane fade">
                        <div class="form-group">
                            <label for="note">Note (in italiano):</label>
                            <textarea class="form-control" rows="5" id="note" name="note_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="notes">Notes (english):</label>
                            <textarea class="form-control" rows="5" id="notes" name="note_eng"></textarea>
                        </div>
                    </div>
                <#--end note-->

                </div>
            <#--end tab content-->


            </div>
        <#--end col md 12-->
        </div>
    <#--end container-->
    </div>
<#--end row-->

</form>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>
