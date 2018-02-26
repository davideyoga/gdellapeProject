<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista gruppi</title>

    <!--librerie-->
    <#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista dei gruppi</h3>
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