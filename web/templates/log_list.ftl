<!DOCTYPE html>
<html lang="it">
<head>
    <title>Log</title>

    <!--librerie-->
    <#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
    <#include "navbar.ftl">

<div class="container">
    <div class="title"> Lista gruppi</div>

    <p><#if message??>${message}<#else></#if></p>

    <div class="table-responsive" >
        <table class="table table-hover table-bordered table-striped">
            <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

            <thead>
            <tr>
                <th>nome gruppo</th>
                <th>descrizione gruppo</th>
                <th> - </th>
            </tr>
            </thead>

            <tbody>
                <#list groups as group>
                <tr>
                    <td>${group.name}</td>
                    <td>${group.description}</td>
                    <td>
                        <a href="AdmModGroups?id=${group.id}">modifica</a>
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