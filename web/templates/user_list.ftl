<!--questa pagina serve per vedere tutti i professori, da anonimi, cioè non loggati-->
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>I nostri docenti</h3>
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
            <!--Come un for-each, cicla sulla lista di users estraendo ogni volta l'utente della lista-->

            <thead>
            <tr>
                <th>Nome Docente</th>
                <th>Email</th>
                <th> - </th>
            </tr>
            </thead>

            <tbody>
            <#list users as user>
            <tr>
                <td>${user.name} ${user.surname}</td>
                <td>${user.email}</td>
                <td>
                    <a href="UserProfile?email=${user.email}">Leggi di piu'</a>
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