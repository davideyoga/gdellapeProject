<!DOCTYPE HTML>
<html>
<head>
    <title>gdellapeProject - List Your Course</title>

<#--questa va sistemata per l'admin, per mo fa le cose base-->
</head>
<body>

<div>
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div>

        <p><#if message??>${message}<#else></#if></p>

    <#list courses as course>

        <p>Name Course: ${course.name}</p>
        <p>Code Course: ${course.code}</p>

        <a href="ModCourse?id=${course.idCourse}">Mod Course</a>

    </#list>

    </div>
</div>

</body>
</html>