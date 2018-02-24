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

    <div class="title" >
        Lista Docenti
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