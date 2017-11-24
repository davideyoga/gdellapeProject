<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi di studio</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="title">
    Aggiungi materia
</div>

<div class="container">
<#--giovanni-->

<#if message??>
    <div class="title">
        <h2>ATTENZIONE</h2>
            <div class="modalContent">
                <p>${message}</p>
            </div>
    </div>
<#else>

</#if>


    <div class="w3layouts-grids">

        <div class="col-md-8 contact-form">
        <form method="POST" action="CreateCourse">
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
