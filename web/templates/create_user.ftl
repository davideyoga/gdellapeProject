<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Creazione Utente</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Creazione di un nuovo utente</h3>
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


<form action="CreateUser" method="POST" id="identicalForm" class="my-form" >
<#--<div class="row" style="border: #00d21f 5px solid">-->
    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#access">Dati di accesso</a></li>
            </ul>
            <br>
            <button type="submit" form="identicalForm" class="btn btn-default">Crea utente</button>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center"><a href="/HomeBackOffice" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
        </div>

<#--commento a caso-->
        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content">


                    <div id="access" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Email@host.com" required pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}">

                        </div>
                        <div class="form-group">
                            <label for="pwd">Password: <span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-html="true" title="la password deve essere composta da: &#13;&#10;-almeno 8 caratteri &#13;&#10;-almeno una maiuscola&#13;&#10;-almeno una minuscola&#13;&#10;-almeno un numero"></span></label>
                            <input type="password" class="form-control" id="pwd" name="password" placeholder="Password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                        <#--data-toggle="tooltip" data-html="true" title="la password deve essere composta da almeno 8 caratteri, almeno una maiuscola, una minuscola e 1 numero"-->
                        </div>
                        <div class="form-group">
                            <label for="pwd2">Ripetere la password:</label>
                            <input type="password" class="form-control" id="pwd2" name="r-password" placeholder="Password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                        <#--data-toggle="tooltip" data-html="true" title="la password deve essere composta da almeno 8 caratteri, almeno una maiuscola, una minuscola e 1 numero"-->
                        </div>
                    </div>
                <#--end access-->

                </div>
            </div>
        </div>
    </div>

</form>


<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>