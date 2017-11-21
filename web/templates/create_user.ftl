<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi di studio</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="title">

<h2>Create User</h2>
<form method="POST" action="CreateUser">
    <div class="col-md-6">


        <div class="create-user-email">
            email
            <input type="email" name="email" >
            <i class="fa fa-lock"></i>
        </div>

        <div class="create-user-password">
            Password
            <input type="password" name="password" >
            <i class="fa fa-lock"></i>
        </div>

    </div>
    <div class="col-md-6 login-do">
        <label class="hvr-shutter-in-horizontal login-sub">
            <input type="submit" value="Create User">
        </label>
    </div>

    <div class="clearfix"> </div>
</form>

</body>
</html>