<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Creazione Utente</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="title">
    Create User
</div>

<div class="container">

    <#if message??>
        <div class="title">
            <h2>ATTENZIONE</h2>
            <div class="modalContent">
                <p>${message}</p>
            </div>
        </div>
    <#else>

    </#if>

    <div class="w3layouts-grids">

        <div class="col-md-8 contact-form">
            <form method="POST" action="CreateUser">
                <p>email</p>
                <input type="email" name="email" >

                <p>password</p>
                <input type="password" name="password" >

                <div class="clearfix"> </div>

                <input type="submit" value="Crea utente">
            </form>
        </div>
    </div>
</div>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>