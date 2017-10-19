<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="title">
    Lista Corsi di studio
</div>

<div class="container">

    <!--Eventuale messaggio di errore, blu-->
    <p><#if message??>${message}<#else></#if></p>

    <table class="responsive">
        <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

        <#if user??>
        <table>
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
                        <td>
                            <a href="AdmModStudyCourse?code=${studyCourse.code}">pippoblu</a>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
        </div>

        <#else>
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
                        <td> ${studyCourse.name}</td>
                        <td><a href="StudyCourseProfile?code=${studyCourse.code}">Leggi di piu'</a></td>
                    </tr>
                </#list>
            </tbody>
        </#if>





    </table>
</div>

<#include "tail.ftl">

</body>
</html>