<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Aggiungi materiale</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Aggiunta di materiale al corso <#if course.name??>${course.name}<#else></#if></h3>
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

<form action="AddMaterial" method="POST" id="create" class="my-form" enctype="multipart/form-data">
<#--<div class="row" style="border: #00d21f 5px solid">-->
    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked text-center">
                <li class="active text-center"><a data-toggle="pill" href="#basic">Aggiungi nuovo materiale</a></li>
            </ul>
            <br>
            <div class="text-center"><button type="submit" form="create" class="btn btn-default">Aggiungi</button></div>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center">
                <a href="/ModAdmCourse?id=${course.idCourse}" class="btn btn-warning my-text center-block" role="button">torna a ${course.name}</a>
            </div>
        </div>


        <div class="container">
            <div class="col-md-12 col-xs-12">
                <div class="tab-content">

                    <div id="basic" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" placeholder="Nome della risorsa">
                        </div>
                        <div class="form-group">
                            <label>Seleziona il file da caricare:</label><br>
                            <label class="custom-file">
                                <input type="file" id="file" class="custom-file-input" name="filetoupload">
                                <span class="custom-file-control"></span>
                            </label>
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
