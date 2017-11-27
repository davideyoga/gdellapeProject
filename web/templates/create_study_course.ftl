<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Corso di Laurea</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="title">
    Creazione Corso di Laurea
</div>

<div class="container">

    <!--Eventuale messaggio di errore-->
    <p><#if message??>${message}<#else></#if></p>

    <div class="w3layouts-grids">

        <div class="col-md-8 contact-form">
            <form method="POST" action="CreateStudyCourse">
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
