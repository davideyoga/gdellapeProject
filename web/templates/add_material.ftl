<div class="create_user_form">
    <h1><a href="create_user">PortaleDell'Universita'</a></h1>
    <div class="create-user-button">

        <p><#if message??>${message}<#else></#if></p>

        <p><#if course.id??>${course.id}<#else></#if></p>
        <p><#if course.name??>${course.name}<#else></#if></p>

        <h2>Add Material</h2>
        <form method="POST" action="AddMaterial" enctype="multipart/form-data">
            <div class="col-md-6">

                <div class="create-group-name">
                    Name
                    <input type="text" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <p>Select the file to upload <input type='file' name='filetoupload'/></p>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Add Material" ajax="false">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>

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
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#basic">Aggiungi nuovo materiale</a></li>
            </ul>
            <button type="submit" form="create" class="btn btn-default">Aggiungi</button>
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
                            <label for="file">Seleziona il file da caricare:</label>
                            <input type='file' class="form-control" id="file" name='filetoupload'/>
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
