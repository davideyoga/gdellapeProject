<!DOCTYPE html>
<html lang="it">
<head>

    <title>
        Home
    </title>

    <#include "import.ftl">

</head>
<body>

    <#include "navbar.ftl">

    <div class="container">

        <p><#if message??>${message}<#else></#if></p>

        <table class="responsive">
            <thead>
                <tr>
                    <th colspan="4">Infomazioni su ${studyCourse.name}</th>
                </tr>
            </thead>
            <tr>
                <td>Anno Accademico</td>
                    <td><a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear - 1)?string.computer}&<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&code=${studyCourse.code} ">Previous year</a></td>
                    <td>${accademicYear}</td>
                    <td><a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear + 1)?string.computer}&<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&code=${studyCourse.code} ">Next year</a></td>
            </tr>
            <tr>
                <td>Nome corso</td>
                    <td colspan="3">${studyCourse.name}</td>
            </tr>
            <tr>
                <td>Codice corso</td>
                    <td colspan="3">${studyCourse.code}</td>
            </tr>
            <tr>
                <td>Durata:</td>
                    <td colspan="3">${studyCourse.duration} anni</td>
            </tr>
            <#--<tr>-->
                <#--<td>Classe:</td>-->
                    <#--<td><#if studyCourse.classes??> ${studyCourse.classes} <#else>Non presente</#if></td>-->
            <#--</tr>-->
            <#--<tr>-->
                <#--<td>Posti</td>-->
                    <#--<td>${studyCourse.seat}</td>-->
            <#--</tr>-->
            <tr>
                <td>Dipartimento</td>
                    <td colspan="3">${studyCourse.department_ita}</td>
            </tr>
            <tr>
                <td>Livello EQF</td>
                    <td colspan="3">${studyCourse.level_ita}</td>
            </tr>
            <tr>
                <td>Access type</td>
                    <td colspan="3">${studyCourse.accessType_ita}</td>
            </tr>
            <tr>
                <td>Lingua corso</td>
                    <td colspan="3">${studyCourse.language_ita}</td>
            </tr>
            <br>
            <!--Tutta la descrizione del corso, per prendere spunto guardare user_profile.ftl-->

            <#--<p>CORSI APPARTENENTI AL CORSO DI STUDI (DA SCRIVERE IN INGLESE)</p>-->
            <br>
            <#list courses as course>
            <br>
            <p>Course name: ${course.name}</p>
            <p>AGGIUNGERE LINK AL CORSO </p>

            </#list>

        </table>

        <br>
        <div class="title">Descrizione Corso di Studi:</div>
         ${studyCourse.description_ita}

    </div>

    <#include "tail.ftl">

</body>
</html>