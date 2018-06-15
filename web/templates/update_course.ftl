<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Aggiorna Corsi</title>

<#include "import.ftl">
</head>

<body>
<#include "navbar.ftl">
<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista corsi</h3>
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
    <div class="col-md-2 col-xs-2 col-sm-2 my-menu">
        <div class="text-center">
            <a href="HomeBackOffice?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna al back office</a>
        </div>
        <div class="w3ls-heading page-header">
        </div>
    </div>

    <div class="col-md-9 col-xs-9 col-sm-9">
        <div class="tab-content">

            <div id="list" class="tab-pane fade in active">
                <div class="table-responsive">
                    <table id="course_table" class="table table-hover table-bordered table-striped">
                        <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                        <thead>
                        <tr>
                            <th>Codice</th>
                            <th>Nome </th>
                            <th>Anno</th>
                            <th> - </th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                        <#list courses as course>
                        <tr>
                            <td>${course.code}</td>
                            <td>${course.name}</td>
                            <td>${course.year}</td>
                            <td>

                                <a class="btn btn-info" role="button" href="UpdateCourse?idCourse=${course.idCourse}&mode=1&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">attualizza</a>
                            </td>
                            <td>
                                <a href="UpdateCourse?idCourse=${course.idCourse}&mode=2&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">anno nuovo</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                </div>
            </div><#--div list-->


        </div><#--div tabcontent-->
    </div> <#--div col md 12-->
</div><#--div row-->

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