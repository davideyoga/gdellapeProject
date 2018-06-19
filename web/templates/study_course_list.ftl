<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi di studio</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Corsi di studio disponibili</h3>
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
    <div class="col-md-2 col-xs-2 my-menu">
        <div class="text-center">
            <a href="home?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Torna alla home</a>
        </div>
    </div>


    <div class="container">


        <div class="col-md-12 col-xs-12">
            <div class="container">

                <div class="table-responsive">
                    <table id="sc_tab" class="table table-bordered table-striped table-hover">

                        <thead>
                        <tr>
                            <th>Codice Corso di studio</th>
                            <th>Nome Corso di studio </th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                    <#list studyCourses as studyCourse>
                    <tr>
                        <td>${studyCourse.code}</td>
                        <td>${studyCourse.name}</td>
                        <td>
                            <a class="btn btn-default" href="StudyCourseProfile?code=${studyCourse.code}&?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">Leggi di piu'</a>
                        </td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>


<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#sc_tab").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>