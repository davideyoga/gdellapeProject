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

        <table class="responsive">

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
                <td> ${utente.email}</td>
                <td><a href="AdmModUser?id=${utente.id}">Mod User</a></td>
                <td><a href="DeleteUser?id=${utente.id}">Delete User</a></td>
            </tr>
            </#list>
            </tbody>
        </table>
</div>
<#include "tail.ftl">

</body>
</html>