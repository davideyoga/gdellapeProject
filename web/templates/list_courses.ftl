<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi di studio</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<h1>Aggiungi materia</h1>

<div class="container">

    <p><#if message??>${message}<#else></#if></p>

    <#list courses as course>

        <p>Name Course: ${course.name}</p>
        <p>Code Course: ${course.code}</p>

        <a href="ModCourse?id=${course.idCourse}">Mod Course</a>
    </#list>

    <div class="clearfix"> </div>

</div>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>