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
        <div class="title">
            Modifica dati ${course.name}, codice corso: <#if course.code??>${course.code}<#else></#if>
        </div>

        <p><#if message??>${message}<#else></#if></p>

        <div class="w3layouts-grids">

            <div class="col-md-8 contact-form">
                <form action="CreateCourse" method="post">
                    <p>Nome</p>
                    <input name="Name" value="<#if course.name??>${course.name}<#else></#if>" required="" type="text">
                    <p>Anno</p>
                    <#--Year(Prima parte dell'anno accademico)-->
                    <input name="year" value="<#if course.year??>${course.year}<#else></#if>" required="" type="text">
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