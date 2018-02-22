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

<p><#if message??>${message}<#else><br></#if></p>

<div class="container">

    <div class="title">
        Profilo ${userCurrent.name} ${userCurrent.surname}
    </div>

    <table class="responsive">
        <#--Come un for-each, cicla sulla lista di users estraendo ogni volta l'utente della lista&ndash;&gt;-->

            <div class="row">
                <div class="col-md-12 col-xs-12 fa-border-course background">
                    <div class="col-md-2 col-xs-4">
                        <div class="contact-info-right">
                            <h5> <br> </h5>

                            <p>Nome:<br>
                            </p>

                            <p>Cognome: <br>
                            </p>

                            <p>Email: <br>
                            </p>

                            <p>Telefono: <br>
                            </p>

                            <p>Curriculum italiano: <br>
                            </p>

                            <p>Curriculum inglese: <br>
                            </p>

                            <p>ora ricevimento ita<br>
                            </p>

                            <p>ora ricevimento ing<br>
                            </p>

                        </div>
                    </div>

                    <div class="col-md-10 col-xs-4">
                        <div class="contact-info-right">
                            <h5> <br> </h5>
                            <form action="/profileManagement" method="POST">
                                <p>
                                    ${userCurrent.name}
                                </p>

                                <p>
                                    ${userCurrent.surname}
                                </p>

                                <p>
                                    ${userCurrent.email}
                                </p>

                                <p><#if userCurrent.number != 0>
                                        ${userCurrent.number}
                                    <#else>
                                        numero non inserito"
                                    </#if>
                                </p>

                                <p>
                                    <#if userCurrent.curriculum_ita != "">
                                        ${userCurrent.curriculum_ita}
                                    <#else>
                                            curriculum non inserito
                                    </#if>
                                </p>

                                <p>
                                <#if userCurrent.curriculum_eng != "">
                                    ${userCurrent.curriculum_eng}
                                <#else>
                                        curriculum non presente
                                </#if>
                                </p>

                                <p>
                                <#if userCurrent.receprion_hours_ita != "">
                                    ${userCurrent.receprion_hours_ita}
                                <#else>
                                        ricevimento non definito
                                </#if>
                                </p>

                                <p> <#if userCurrent.receprion_hours_eng != "">
                                    ${userCurrent.receprion_hours_eng}
                                <#else>
                                    ricevimento non definito
                                </#if>
                                </p>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
</div>
<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>