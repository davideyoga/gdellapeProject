<!DOCTYPE HTML>
<html>
<head>
    <title>Associa corsi e utenti</title>
<#include "import.ftl">
</head>

<body>
<#include "navbar.ftl">


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
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Associa docenti a <#if course.name??>${course.name}<#else> </#if></h3>
            <h4 class="text-center"> <#if course.year??> anno accademico ${course.year}<#else> </#if></h4>
        </div>
    </div>
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
                                    <th>Email Docente</th>
                                    <th>Nome</th>
                                    <th>-</th>
                                </tr>
                                </thead>

                                <tbody>
                                    <#list allUser as user>
                                    <tr>
                                        <td>${user.email}</td>
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