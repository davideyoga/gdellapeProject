<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Creazione gruppo</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Creazione di un nuovo gruppo</h3>
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

<form action="CreateGroups" method="POST" id="create" class="my-form" >
<#--<div class="row" style="border: #00d21f 5px solid">-->
    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#basic">Informazioni di base</a></li>
            </ul>
            <br>
            <button type="submit" form="create" class="btn btn-default">Crea gruppo</button>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center"><a href="/HomeBackOffice" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
        </div>


        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content">


                    <div id="basic" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" placeholder="Nome del gruppo">
                        </div>
                        <div class="form-group">
                            <label for="desc">Descrizione:</label>
                            <textarea class="form-control" rows="5" id="desc" name="description"></textarea>
                        </div>
                    </div>
                <#--end basic-->


            </div>
        </div>
    </div>
    </div>

</form>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>
