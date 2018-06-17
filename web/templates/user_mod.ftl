<!DOCTYPE html>
<html lang="it">
<head>
    <title>Modifica Utente</title>

<#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('pwd').value="";
        }
    </script>

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Modifica dati ${usermod.name} ${usermod.surname}</h3>
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

<form action="AdmModUser" method="POST" id="identicalForm" class="my-form" autocomplete="off">

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
            <button type="submit" form="identicalForm" class="btn btn-default">Modifica utente</button>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center">
                <a href="AdmGetListUser" class="btn btn-warning my-text center-block" role="button">Torna alla lista degli utenti</a>
            </div>
        </div>


        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content" >


                    <div id="access" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" value="<#if usermod.email??>${usermod.email}<#else></#if>" placeholder="Email@host.com" required pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password: <span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-html="true" title="la password deve essere composta da: &#13;&#10;-almeno 8 caratteri &#13;&#10;-almeno una maiuscola&#13;&#10;-almeno una minuscola&#13;&#10;-almeno un numero"></span></label>
                            <input type="password" class="form-control" id="pwd" name="password"  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Ripeti password:</label>
                            <input type="password" class="form-control" id="pwd2" name="ripetere-password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                        </div>
                    </div>
                <#--end access-->


                    <div id="anag" class="tab-pane fade">
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" value="<#if usermod.name??>${usermod.name}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="cognome">Cognome:</label>
                            <input type="text" class="form-control" id="cognome" name="surname" value="<#if usermod.surname??>${usermod.surname}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="numer">Numero:</label>
                            <input type="text" class="form-control" id="numer" name="number" value="<#if usermod.number??>${usermod.number?string.computer}<#else>666</#if>">
                        </div>
                    </div>
                <#--end anag-->


                    <div id="curr" class="tab-pane fade">
                        <div class="form-group">
                            <label for="curr_it">Curriculum:</label>
                            <textarea class="form-control" rows="5" id="curr_it" name="curriculum_ita"><#if usermod.curriculum_ita??>${usermod.curriculum_ita}<#else></#if></textarea>
                        <#--value="<#if usermod.curriculum_ita??>${usermod.curriculum_ita}<#else></#if>"-->
                        </div>
                        <div class="form-group">
                            <label for="curr_en">Curriculum inglese:</label>
                            <textarea class="form-control" rows="5" id="curr_en" name="curriculum_eng"><#if usermod.curriculum_eng??>${usermod.curriculum_eng}<#else></#if></textarea>
                        <#--value="<#if usermod.curriculum_eng??>${usermod.curriculum_eng}<#else></#if>"-->
                        </div>
                    </div>
                <#--end curr-->


                    <div id="ric" class="tab-pane fade">
                        <div class="form-group">
                            <label for="receprion_ita">Orario di ricevimento:</label>
                            <textarea class="form-control" rows="5" id="receprion_ita" name="receprion_hours_ita"><#if usermod.receprion_hours_ita??>${usermod.receprion_hours_ita}<#else></#if></textarea>
                        </div>
                        <div class="form-group">
                            <label for="receprion_eng">Orario di ricevimento:</label>
                            <textarea class="form-control" rows="5" id="receprion_eng" name="receprion_hours_eng"><#if usermod.receprion_hours_eng??>${usermod.receprion_hours_eng}<#else></#if></textarea>
                        </div>
                    </div>
                <#--end ric-->


                    <div id="perm" class="tab-pane fade">
                        <div class="panel panel-default">
                            <div class="panel-heading">Attualmente l'utente fa parte dei seguenti gruppi</div>
                            <div class="panel-body">
                                <#list listGroups as group>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="${group.name}" value="${group.name}" <#if listUserGroups?seq_contains(group) >checked<#else></#if>>${group.name}
                                        </label>
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
            <#--end perm-->


            </div>
        </div>
    </div>
    </div>

</form>

<#include "tail.ftl">

</body>
</html>