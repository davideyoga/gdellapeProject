<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Corso di Laurea</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="title">

</div>

<div class="container">

    <!--Eventuale messaggio di errore-->
    <p><#if message??>${message}<#else></#if></p>

</div>




    <!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
