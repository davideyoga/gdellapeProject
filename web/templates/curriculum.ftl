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

<p><#if message??>${message}<#else></#if></p>

<div class="container">
    ${user.curriculum_ita}

</div>
<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>