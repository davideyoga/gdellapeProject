<!DOCTYPE html>
<html lang="it">
<head>
    <#--sono le informazioni specifiche del corso di studi-->
    <title>
        Home
    </title>

    <#include "import.ftl">

</head>
<body>

    <#include "navbar.ftl">

    <div class="container">

        <p><#if message??>${message}<#else></#if></p>

        <div class="table-responsive" >
            <table class="table table-hover table-bordered table-striped">
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
        </table>
        </div>
            <!--Tutta la descrizione del corso, per prendere spunto guardare user_profile.ftl-->
    <#--commento cmmento-->
            <#--<p>CORSI APPARTENENTI AL CORSO DI STUDI (DA SCRIVERE IN INGLESE)</p>-->
        <br>
        <br>
        <br>

        <#list courses>
            <div class="table-responsive" >
            <table class="table table-hover table-bordered table-striped">
                <thead>
                <tr>
                    <th colspan="3">Insegnamenti associati a ${studyCourse.name}</th>
                </tr>
                </thead>
            <#items as  course>
            <tr>
                <td>Nome corso</td>
                <td >${course.name}</td>
                <td ><a href="CourseProfile?id=${course.idCourse}">Leggi di piu'</a></td>
            </tr>

            </#items>
        </table>
            </div>
        <#else>
            Attualmente non ci sono insegnamenti associati al corso di studi
        </#list>

        <br>
        <div class="title">Descrizione Corso di Studi:</div>
         ${studyCourse.description_ita}

    </div>

    <#include "tail.ftl">

</body>
</html>