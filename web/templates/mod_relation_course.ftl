<!DOCTYPE HTML>
<html>
<head>
    <title>Relazione tra corsi</title>

<#include "import.ftl">
</head>

<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>${course.name}</h3>
            <h4><#if mode=='borrowed'>borrowed</#if>
                <#if mode=='preparatory'>corsi propedeutici</#if>
                <#if mode=='module'>associa moduli</#if>
            </h4>
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


<form action="ModCourseRelation" method="POST" id="mod" class="my-form" >

    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#list">lista corsi</a></li>
            </ul>
            <br>
            <button type="submit" form="mod" class="btn btn-default">Salva</button>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center">
                <a href="ModAdmCourse?id=${course.idCourse}" class="btn btn-warning my-text center-block" role="button">Torna al corso ${course.name}</a>
            </div>
        </div>


        <div class="container">

            <div class="col-md-12 col-xs-12">
                <div class="tab-content" >


                    <div id="list" class="tab-pane fade in active">
                        <div class="table-responsive" >
                            <table id="course_table" class="table table-hover table-bordered table-striped">
                                <!--Come un for-each, cicla sulla lista di users estraendo ogni volta l'utente della lista-->

                                <thead>
                                <tr>
                                    <th>Codice</th>
                                    <th>nome</th>
                                    <th>anno</th>
                                    <th><#if mode=='borrowed'>borrowed</#if>
                                        <#if mode=='preparatory'>corsi propedeutici</#if>
                                        <#if mode=='module'>associa moduli</#if></th>
                                </tr>
                                </thead>

                                <tbody>
                                    <#list allCourse as course>
                                    <tr>
                                        <td>${course.code}</td>
                                        <td>${course.name}</td>
                                        <td>${course.year}</td>
                                        <td>
                                            <input type="checkbox" name="${course.idCourse}" value="${course.idCourse}" <#if courseRelated?seq_contains(course)>checked<#else></#if> >
                                        </td>
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
        $("#course_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>