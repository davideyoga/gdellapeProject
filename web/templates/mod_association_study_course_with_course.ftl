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
<div class="row">
    <div class="container">
        <#assign x=currentYear>

        <div>Study Course: ${studyCourse.name}</div>
        <div>AGE: ${currentYear}</div>
        <a href="ModAssociationStudyCourseWithCourse?idStudyCourse=${studyCourse.id}&age=${(currentFirstYear - 1)?string.computer} ">Previous year</a>
        <a href="ModAssociationStudyCourseWithCourse?idStudyCourse=${studyCourse.id}&age=${(currentFirstYear + 1)?string.computer} ">Next year</a>

    </div>

    <form method="POST" action="ModAssociationStudyCourseWithCourse" id="ass">


            <div class="col-md-2 col-xs-2 my-menu">
                <ul class="nav nav-pills nav-stacked text-center">
                    <li class="active"><a data-toggle="pill" href="#">lista corsi</a></li>
                </ul>
                <br>
                <div class="text-center">
                    <button type="submit" form="ass" class="btn btn-default">Salva</button>
                </div>
                <div class="w3ls-heading page-header">
                </div>
                <div class="text-center">
                    <a href="/ListCourse" class="btn btn-warning my-text center-block" role="button">torna alla lista dei corsi</a>
                </div>
            </div>


            <div class="container">

                <div class="col-md-12 col-xs-12">
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

                </div>
            </div>


        </div>
    </form>

</div>
<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>