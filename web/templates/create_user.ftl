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
                <li><a data-toggle="pill" href="#curr">Curriculum</a></li>
                <li><a data-toggle="pill" href="#ric">Ricevimento</a></li>
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
                        <div class="form-group">
                            <label for="numer">Numero:</label>
                            <input type="text" class="form-control" id="numer" name="number" placeholder="Numero">
                        </div>
                    </div>
                <#--end anag-->


                    <div id="curr" class="tab-pane fade">
                        <div class="form-group">
                            <label for="curr_it">Curriculum:</label>
                            <textarea class="form-control" rows="5" id="curr_it" name="curriculum_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="curr_en">Curriculum inglese:</label>
                            <textarea class="form-control" rows="5" id="curr_en" name="curriculum_eng"></textarea>
                        </div>
                    </div>
                <#--end curr-->


                    <div id="ric" class="tab-pane fade">
                        <div class="form-group">
                            <label for="receprion_ita">Orario di ricevimento:</label>
                            <textarea class="form-control" rows="5" id="receprion_ita" name="receprion_hours_ita"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="receprion_eng">Orario di ricevimento:</label>
                            <textarea class="form-control" rows="5" id="receprion_eng" name="receprion_hours_eng"></textarea>
                        </div>

                    </div>


                    <div id="perm" class="tab-pane fade">
                        <#list listGroups as group>

                        </#list>
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