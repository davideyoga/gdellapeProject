<!--questa pagina serve per vedere più dettagli di un docente-->
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">


<form action="ProfileManagement" method="POST" id="mod" class="my-form" >

    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#access">Dati di accesso</a></li>
                <li><a data-toggle="pill" href="#anag">Anagrafica</a></li>
                <li><a data-toggle="pill" href="#curr">Curriculum</a></li>
                <li><a data-toggle="pill" href="#ric">Ricevimento</a></li>
            </ul>
            <br>
            <button type="submit" form="mod" class="btn btn-default">Modifica dati</button>
        </div>


        <div class="container">
            <div class="title">
                Profilo ${userCurrent.name} ${userCurrent.surname}
            </div>
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
                <div class="tab-content" >


                    <div id="access" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" value="<#if userCurrent.email??>${userCurrent.email}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="pwd" name="password" value="<#if userCurrent.password??>${userCurrent.password}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Ripeti password:</label>
                            <input type="password" class="form-control" id="pwd" name="ripetere-password" value="<#if userCurrent.password??>${userCurrent.password}<#else></#if>">
                        </div>
                    </div>
                <#--end access-->


                    <div id="anag" class="tab-pane fade">
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" value="<#if userCurrent.name??>${userCurrent.name}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="cognome">Cognome:</label>
                            <input type="text" class="form-control" id="cognome" name="surname" value="<#if userCurrent.surname??>${userCurrent.surname}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="numer">Numero:</label>
                            <input type="text" class="form-control" id="numer" name="number" value="<#if userCurrent.number??>${userCurrent.number?string.computer}<#else>666</#if>">
                        </div>
                    </div>
                <#--end anag-->


                    <div id="curr" class="tab-pane fade">
                        <div class="form-group">
                            <label for="curr_it">Curriculum:</label>
                            <textarea class="form-control" rows="5" id="curr_it" name="curriculum_ita"></textarea>
                        <#--value="<#if usermod.curriculum_ita??>${usermod.curriculum_ita}<#else></#if>"-->
                        </div>
                        <div class="form-group">
                            <label for="curr_en">Curriculum inglese:</label>
                            <textarea class="form-control" rows="5" id="curr_en" name="curriculum_eng"></textarea>
                        <#--value="<#if usermod.curriculum_eng??>${usermod.curriculum_eng}<#else></#if>"-->
                        </div>
                    </div>
                <#--end curr-->


                    <div id="ric" class="tab-pane fade">
                        <div class="form-group">
                            <label for="receprion_ita">Orario di ricevimento:</label>
                            <textarea class="form-control" rows="5" id="receprion_ita" name="receprion_hours_ita"><#if userCurrent.receprion_hours_ita??>${userCurrent.receprion_hours_ita}<#else></#if>
                            </textarea>
                        </div>
                        <div class="form-group">
                            <label for="receprion_eng">Orario di ricevimento:</label>
                            <textarea class="form-control" rows="5" id="receprion_eng" name="receprion_hours_eng"><#if userCurrent.receprion_hours_eng??>${userCurrent.receprion_hours_eng}<#else></#if>
                            </textarea>
                        </div>
                    </div>
                <#--end ric-->


                </div>
            </div>
        </div>
    </div>

</form>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>