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
</div>


<form action="modAssociationCourseWithUser" method="POST" id="mod" class="my-form" >

    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked text-center">
                <li class="active"><a data-toggle="pill" href="#list">lista docenti</a></li>
            </ul>
            <br>
            <div class="text-center"><button type="submit" form="mod" class="btn btn-default">Salva</button></div>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center">
                <a href="/ListCourse" class="btn btn-warning my-text center-block" role="button">torna alla lista dei corsi</a>
            </div>
        </div>


        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content" >


                    <div id="list" class="tab-pane fade in active">
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
                                        <td>${user.surname} ${user.name}</td>
                                        <td><input type="checkbox" name="${user.email}" value="${user.email}" <#if userMatch?seq_contains(user) >checked<#else></#if> > associa</td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>

                    </div>
                <#--end access-->

                </div>
            </div>
        </div>
    </div>

</form>


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