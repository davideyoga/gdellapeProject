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
    <#--<a href="modAssociationCourseWithUser?id=${course.idCourse}&age=${(currentFirstYear - 1)?string.computer} ">Previous year</a>-->
    <#--<a href="modAssociationCourseWithUser?id=${course.idCourse}&age=${(currentFirstYear + 1)?string.computer} ">Next year</a>-->

    <form method="POST" action="modAssociationCourseWithUser?idCourseToModify=${course.idCourse}">
        <#--<div class="row">-->
            <#--<div class="col-md-6 col-lg-6 col-xs-6">-->

                <#--<div class="list-group">-->
                    <#--<ul>-->
                        <#--<#list allUser as user>-->
                        <#--<li class="list-group-item">-->
                            <#--<h4 class="list-group-item-heading">Nome: ${user.name} ${user.surname}</h4>-->
                            <#--<p class="list-group-item-text"><input type="checkbox" name="${user.name}" value="${user.name}" <#if userMatchWithCourse?seq_contains(user) >checked<#else></#if> > associa docente</p>-->
                        <#--</li>-->
                        <#--</#list>-->
                    <#--</ul>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--&lt;#&ndash;end row&ndash;&gt;-->

        <div class="table-responsive">
            <table id="user_tab" class="table table-bordered table-striped table-hover">

                <thead>
                <tr>
                    <th>Nome Docente</th>
                    <th>-</th>
                </tr>
                </thead>

                <tbody>
                    <#list allUser as user>
                    <tr>
                        <td>${user.name} ${user.surname}</td>
                        <td><input type="checkbox" name="${user.email}" value="${user.email}" <#if userMatch?seq_contains(user) >checked<#else></#if> > associa</td>
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
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#user_tab").dataTable({
            "iDisplayLength": -1,
            "aLengthMenu": [[-1], ["All"]]
        });
    });
</script>
</body>
</html>