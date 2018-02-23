<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Creazione Corso</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="title">
    Creazione di un nuovo corso
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


    <div class="row">

        <div class="col-md-12 col-xs-12 contact-form">

            <form action="CreateCourse" method="POST">
                <div class="form-group">
                    <label for="codice">Codice:</label>
                    <input type="number" class="form-control" id="codice">
                </div>
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome">
                </div>
                <div class="form-group">
                    <label for="cfu">Cfu:</label>
                    <input type="number" class="form-control" id="cfu">
                </div>
                <div class="form-group">
                    <label for="settore">Settore:</label>
                    <input type="text" class="form-control" id="settore">
                </div>
                <div class="form-group">
                    <label for="lingua">Lingua:</label>
                    <input type="text" class="form-control" id="lingua">
                </div>
                <#--<div class="checkbox">-->
                    <#--<label><input type="checkbox"> Remember me</label>-->
                <#--</div>-->
                <button type="submit" class="btn btn-default">Submit</button>
            </form>


            <form  action="CreateCourse">
                <p>Nome</p>
                <input type="text" name="name" >

                <p>Codice</p>
                <input type="text" name="code" >

                <div class="clearfix"> </div>

                <input type="submit" value="Crea corso">
            </form>
        </div>
    </div>
</div>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>
