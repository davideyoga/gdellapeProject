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
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista dei corsi</h3>
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
<div class="container">

    <div class="table-responsive" >
        <table class="table table-hover table-bordered table-striped">
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
                    <a href="ModCourse?id=${course.idCourse}">modifica corso</a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>