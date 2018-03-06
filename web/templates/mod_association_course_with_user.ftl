<!DOCTYPE HTML>
<html>
<head>
    <title>Associa corsi e utenti</title>
<#include "import.ftl">
</head>

<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Associa corso e docenti</h3>
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

        <#assign x=currentYear>

    <div>Course: ${course.name}</div>
    <div>AGE: ${currentYear}</div>
    <a href="modAssociationCourseWithUser?id=${course.idCourse}&age=${(currentFirstYear - 1)?string.computer} ">Previous year</a>
    <a href="modAssociationCourseWithUser?id=${course.idCourse}&age=${(currentFirstYear + 1)?string.computer} ">Next year</a>

    <form method="POST" action="modAssociationCourseWithUser">
        <div class="row">
            <div class="col-md-6 col-lg-6 col-xs-6">

                <div class="list-group">
                    <ul>
                        <#list allUser as user>
                        <li class="list-group-item">
                            <h4 class="list-group-item-heading">Nome: ${user.name} ${user.surname}</h4>
                            <p class="list-group-item-text"><input type="checkbox" name="${user.name}" value="${user.name}" <#if userMatchWithCourse?seq_contains(user) >checked<#else></#if> > associa docente</p>
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    <#--end row-->

    <#--<div class="table-responsive">-->
    <#--<table id="sc_tab" class="table table-bordered table-striped table-hover">-->

    <#--<thead>-->
    <#--<tr>-->
    <#--<th>Nome Docente</th>-->
    <#--<th> tipo cfu </th>-->
    <#--</tr>-->
    <#--</thead>-->

    <#--<tbody>-->
    <#--<#list allCourses as course>-->
    <#--<tr>-->
    <#--<td>${course.code}</td>-->
    <#--<td><input type="checkbox" name="${course.name}" value="${course.name}" <#if coursesMatch?seq_contains(course) >checked<#else></#if> > ${course.name}</td>-->
    <#--<td>-->
    <#--<input type="text" name="cfuType${course.name}">-->
    <#--</td>-->
    <#--</tr>-->
    <#--</#list>-->
    <#--</tbody>-->
    <#--</table>-->
    <#--</div>-->

        <div class="col-md-6 login-do">
            <label class="hvr-shutter-in-horizontal login-sub">
                <input type="submit" value="Update">
            </label>
        </div>

    </form>

</div>
<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>