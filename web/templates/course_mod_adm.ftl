<!DOCTYPE html>
<html lang="it">
<head>
    <title>Modifica corso</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
    <!--menù navigazione-->
    <#include "navbar.ftl">

    <div class="container">
        <div class="title"> Modifica corso</div>

        <p><#if message??>${message}<#else></#if></p>

        <div class="w3layouts-grids">

            <div class="col-md-8 contact-form">
                <form action="CreateCourse" method="post">
                    <input name="Name" placeholder="nome" required="" type="text">
                    <input name="code" placeholder="codice" required="" type="text">
                <#--Year(Prima parte dell'anno accademico)-->
                    <input name="year" placeholder="anno" required="" type="number">
                    <div class="clearfix"> </div>
                    <input value="modifica corso" type="submit">
                </form>
            </div>
            <div class="clearfix"> </div>
        </div>

    </div>

    <!--modulo contatti, email, conclusione-->
    <#include "tail.ftl">
</body>
</html>