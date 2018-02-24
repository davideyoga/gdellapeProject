<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi di studio</title>

<#include "import.ftl">

</head>
<body>

    <#include "navbar.ftl">
    <div class="title" >
        Lista Corsi di studio
    </div>

    <div class="container">

        <!--Eventuale messaggio di errore-->
        <p>
            <#if message??>
                ${message}
            <#else>

            </#if>
        </p>

        <div class="table-responsive" >
            <table class="table table-hover table-bordered table-striped">
                <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                <thead>
                <tr>
                    <th>Codice materia</th>
                    <th>Nome materia </th>
                    <th> - </th>
                </tr>
                </thead>

                <tbody>
                    <#list listCourse as course>
                    <tr>
                        <td>${course.code}</td>
                        <td>${course.name}</td>
                        <td>
                            <a href="CourseProfile?id=${course.idCourse}">Leggi di piu'</a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <div>
    </div>
  </div>
</div>
<#--tutti questi div sono inutili ma ci devono stare-->

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>