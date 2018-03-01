<!--questa pagina serve per vedere tutti i professori, da anonimi, cioè non loggati-->
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista docenti</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Aggiungi libro</h3>
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


    <form action="AddBook" method="POST" id="Add" class="my-form">
    <#--<div class="row" style="border: #00d21f 5px solid">-->
        <div class="row">
            <div class="col-md-2 col-xs-2 my-menu">
                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a data-toggle="pill" href="#basic">Aggiungi nuovo libro</a></li>
                </ul>
                <br>
                <button type="submit" form="Add" class="btn btn-default">Aggiungi</button>
            </div>


            <div class="container">
                <div class="col-md-12 col-xs-12">
                    <div class="tab-content">

                        <div id="basic" class="tab-pane fade in active">
                            <div class="form-group">
                                <label for="code">Codice:</label>
                                <input type="text" class="form-control" id="code" name="code" placeholder="Codice del libro">
                            </div>
                            <div class="form-group">
                                <label for="title">Titolo:</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="Titolo del libro">
                            </div>
                            <div class="form-group">
                                <label for="author">Autore:</label>
                                <input type="text" class="form-control" id="author" name="author" placeholder="Autore del libro">
                            </div>
                            <div class="form-group">
                                <label for="age">Anno di pubblicazione:</label>
                                <input type="text" class="form-control" id="age" name="age" placeholder="Anno del libro">
                            </div>
                            <div class="form-group">
                                <label for="volume">Volume:</label>
                                <input type="text" class="form-control" id="volume" name="volume" placeholder="Volume">
                            </div>
                            <div class="form-group">
                                <label for="editor">Editore:</label>
                                <input type="text" class="form-control" id="editor" name="editor" placeholder="Editore">
                            </div>
                            <div class="form-group">
                                <label for="link">Link:</label>
                                <input type="text" class="form-control" id="link" name="link" placeholder="link">
                            </div>
                        </div>
                    <#--end basic-->
                    </div>
                </div>
            </div>
        </div>
    </form>

<a href="ModAdmCourse?id=${course.idCourse}">torna indietro</a>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">

</body>
</html>
