<!DOCTYPE HTML>
<html>
<head>
    <title>Relazion tra corsi</title>

<#include "import.ftl">
</head>

<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Associazione tra corsi</h3>
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

    <div>corso ${course.name}</div>

    <div class="profile-button">

        <form method="POST" action="ModCourseRelation">


        <#list allCourse as course>

            <!--se il corso e' presente tra i corsi associati al corso di studi preso in esame, setta il checkbox a true, false altrimenti -->
            <input type="checkbox" name="${course.idCourse}" value="${course.idCourse}" <#if courseRelated?seq_contains(course) >checked<#else></#if> > ${course.name}


        </#list>

            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Update">
                </label>
            </div>

        </form>
    </div>
</div>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>