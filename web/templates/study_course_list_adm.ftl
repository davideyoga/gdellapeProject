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
            <h3>Lista Corsi di studio</h3>
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
            <a href="homeBackOffice" class="btn btn-warning my-text center-block" role="button">Torna al back office</a>
        </div>
    </div>


    <div class="container">

        <div class="col-md-12 col-xs-12">
            <div class="container">

                <div class="table-responsive" >
                    <table id="sc_table" class="table table-hover table-bordered table-striped">
                        <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->


                        <thead>
                        <tr>
                            <th>Codice Corso di studio</th>
                            <th>Nome Corso di studio </th>
                            <th> - </th>
                            <th> - </th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                            <#list studyCourses as studyCourse>
                            <tr>
                                <td>${studyCourse.code}</td>
                                <td>${studyCourse.name}</td>
                                <td>
                                    <a class="btn btn-default" href="AdmModStudyCourse?id=${studyCourse.id}">Modifica</a>
                                </td>
                                <td>
                                    <a class="btn btn-primary" href="modAssociationStudyCourseWithCourse?idStudyCourse=${studyCourse.id}">Associa corsi</a>
                                </td>
                                <td>
                                    <a class="btn btn-danger" href="DeleteStudyCourse?id=${studyCourse.id}" onclick="return ConfirmDeleteStudyCourse(${studyCourse.code},${studyCourse.name})">Cancella</a>
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


<#include "tail.ftl">
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#sc_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>