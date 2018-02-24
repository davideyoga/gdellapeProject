<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="container">
    <div class="title">
        Lista utenti
    </div>
        <p><#if message??>${message}<#else></#if></p>

    <div class="table-responsive" >
        <table class="table table-hover table-bordered table-striped">

            <thead>
            <tr>
                <th>ID</th>
                <th>Email utente </th>
                <th>  </th>
                <th>  </th>
            </tr>
            </thead>

            <tbody>
            <#list users as utente>
            <tr>
                <td>${utente.id}</td>
                <td>${utente.email}</td>
                <td><a href="AdmModUser?id=${utente.id}">Modifica utente</a></td>
                <td><a href="DeleteUser?id=${utente.id}" onclick="return ConfirmDelete()">Cancella Utente</a></td>
            </tr>
            </#list>
            </tbody>
        </table>
        </div>
</div>
<#include "tail.ftl">

</body>
</html>