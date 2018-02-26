<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi di studio</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista Corsi di studio</h3>
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
                <th>Codice Corso di studio</th>
                <th>Nome Corso di studio </th>
                <th> - </th>
            </tr>
            </thead>

            <tbody>
        <#list studyCourses as studyCourse>
        <tr>
            <td>${studyCourse.code}</td>
            <td>${studyCourse.name}</td>
            <td>
                <a href="AdmModStudyCourse?id=${studyCourse.id}">Modifica Corso</a>
            </td>
        </tr>
        </#list>
            </tbody>
        </table>
    </div>
</div>
<#include "tail.ftl">

</body>
</html>