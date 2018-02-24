<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Creazione Utente</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="title">
    Creazione nuovo utente
</div>


<form action="CreateUser" method="POST" id="create" class="my-form" >
<#--<div class="row" style="border: #00d21f 5px solid">-->
    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#access">Dati di accesso</a></li>
                <li><a data-toggle="pill" href="#anag">Anagrafica</a></li>
                <li><a data-toggle="pill" href="#extra">Informazioni aggiuntive</a></li>
                <li><a data-toggle="pill" href="#perm">Permessi</a></li>
            </ul>
            <br>
            <button type="submit" form="create" class="btn btn-default">Crea utente</button>
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

            <div class="col-md-12 col-xs-12">
                <div class="tab-content">


                    <div id="access" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                            <span class="help-block">Email d'accesso</span>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="pwd" name="password" placeholder="Password">
                        </div>
                    </div>
                <#--end access-->


                    <div id="anag" class="tab-pane fade">
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" placeholder="Nome">
                        </div>
                        <div class="form-group">
                            <label for="cognome">Cognome:</label>
                            <input type="text" class="form-control" id="cognome" name="surname" placeholder="Cognome">
                        </div>
                    </div>
                <#--end aggiuntive-->


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
                    <div id="ext_mat" class="tab-pane fade">
                        <div class="form-group">
                            <label for="material">Materiale disponibile:</label>
                            <textarea class="form-control" rows="5" id="material" name="external_material_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="material_en">External material:</label>
                            <textarea class="form-control" rows="5" id="material_en" name="external_material_eng"></textarea>
                        </div>
                    </div>
                </div>
            <#--end ext_mat-->


            </div>
        </div>
    </div>
    </div>

</form>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>