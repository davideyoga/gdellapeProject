<!DOCTYPE HTML>
<html>
<head>
    <title>Associa corsi</title>
<#include "import.ftl">
</head>

<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Associa corsi e corsi di studio</h3>
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

    <div>Study Course: ${studyCourse.name}</div>
    <div>AGE: ${currentYear}</div>
    <a href="ModAssociationStudyCourseWithCourse?idStudyCourse=${studyCourse.id}&age=${(currentFirstYear - 1)?string.computer} ">Previous year</a>
    <a href="ModAssociationStudyCourseWithCourse?idStudyCourse=${studyCourse.id}&age=${(currentFirstYear + 1)?string.computer} ">Next year</a>

    <form method="POST" action="ModAssociationStudyCourseWithCourse">
        <div class="table-responsive">
            <table id="sc_tab" class="table table-bordered table-striped table-hover">

                <thead>
                <tr>
                    <th>Codice Corso di studio</th>
                    <th>Nome Corso di studio </th>
                    <th> tipo cfu </th>
                </tr>
                </thead>

                <tbody>
                    <#list allCourses as course>
                    <tr>
                        <td>${course.code}</td>
                        <td><input type="checkbox" name="${course.name}" value="${course.name}" <#if coursesMatch?seq_contains(course) >checked<#else></#if> > ${course.name}</td>
                        <td>
                            <input type="text" name="cfuType${course.name}">
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>

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