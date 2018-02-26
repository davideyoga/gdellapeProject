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
            <h3>Corsi di studio disponibili</h3>
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

    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">

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
                            <a href="StudyCourseProfile?code=${studyCourse.code}">Leggi di piu'</a>
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