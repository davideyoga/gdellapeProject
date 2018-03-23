<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Creazione corso di Laurea</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Creazione Corso di Laurea</h3>
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

<form action="CreateStudyCourse" method="POST" id="create" class="my-form" >
<#--<div class="row" style="border: #00d21f 5px solid">-->
    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#base">Dati di base</a></li>
                <li><a data-toggle="pill" href="#extra_it">Informazioni aggiuntive</a></li>
                <li><a data-toggle="pill" href="#extra_en">Informazioni in inglese</a></li>
            </ul>
            <br>
            <button type="submit" form="create" class="btn btn-default">Crea Corso di Laurea</button>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center"><a href="/HomeBackOffice" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
        </div>


        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content">


                    <div id="base" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="cod">Codice:</label>
                            <input type="text" class="form-control" id="cod" name="code" placeholder="Es. 1">
                        </div>
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" placeholder="Es. Scienze Informatiche">
                        </div>
                        <div class="form-group">
                            <label for="dur">Durata:</label>
                            <input type="number" min="1" max="10" class="form-control" id="dur" name="duration" value="1">
                        </div>
                        <#--<div class="form-group">-->
                            <#--<label for="class">Classe:</label>-->
                            <#--<input type="text" class="form-control" id="class" name="class">-->
                        <#--</div>-->
                        <div class="form-group">
                            <label for="seat">posti disponibili:</label>
                            <input type="text" class="form-control" id="seat" name="seat">
                        </div>
                    </div>
                <#--end access-->


                    <div id="extra_it" class="tab-pane fade">
                        <div class="form-group">
                            <label for="lang">Lingua:</label>
                            <input type="text" class="form-control" id="lang" name="language_ita">
                        </div>
                        <div class="form-group">
                            <label for="descr_it">Descrizione:</label>
                            <textarea class="form-control" rows="5" id="descr_it" name="descpription_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="dep_it">Dipartimento:</label>
                            <input type="text" class="form-control" id="dep_it" name="department_ita">
                        </div>
                        <div class="form-group">
                            <label for="level_it">Livello:</label>
                            <input type="number" min="1" max="5" value="1" class="form-control" id="level_it" name="level_ita">
                        </div>
                        <div class="form-group">
                            <label for="acc_type_it">Modalità d'accesso:</label>
                            <input type="text" class="form-control" id="acc_type_it" name="access_type_ita">
                        </div>
                    </div>
                <#--end extra_it-->


                    <div id="extra_en" class="tab-pane fade">
                        <div class="form-group">
                            <label for="lang_en">Language:</label>
                            <input type="text" class="form-control" id="lang_en" name="language_eng">
                        </div>
                        <div class="form-group">
                            <label for="descr_en">Description:</label>
                            <textarea class="form-control" rows="5" id="descr_en" name="descpription_eng"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="dep_en">Department:</label>
                            <input type="text" class="form-control" id="dep_en" name="department_eng">
                        </div>
                        <div class="form-group">
                            <label for="level_en">Level:</label>
                            <input type="number" min="1" max="5" value="1" class="form-control" id="level_en" name="level_eng">
                        </div>
                        <div class="form-group">
                            <label for="acc_type_en">Access type:</label>
                            <input type="text" class="form-control" id="acc_type_en" name="access_type_eng">
                        </div>
                    </div>
                <#--end extra_en-->

                </div>
            <#--end tab-content-->

            </div>
        </div>
    </div>

</form>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
