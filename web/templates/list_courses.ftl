<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista corsi</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="title"> Lista corsi</div>

    <p><#if message??>${message}<#else></#if></p>

    <table class="responsive">
        <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

        <thead>
        <tr>
            <th>nome materia</th>
            <th>codice materia</th>
            <th> - </th>
        </tr>
        </thead>

        <tbody>
        <#list courses as course>
        <tr>
            <td>${course.name}</td>
            <td>${course.code}</td>
            <td>
                <a href="ModCourse?id=${course.idCourse}">modifica</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>