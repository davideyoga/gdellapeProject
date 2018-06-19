<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista gruppi</title>

    <!--librerie-->
    <#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista dei gruppi</h3>
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
        <div class="text-center"><a href="/HomeBackOffice" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
    </div>


    <div class="container">
        <div class="col-md-12 col-xs-12">

            <div class="container">
                <div class="table-responsive" >
                    <table id="group_table" class="table table-hover table-bordered table-striped">
                        <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                        <thead>
                        <tr>
                            <th>nome gruppo</th>
                            <th>descrizione gruppo</th>
                            <th> - </th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                <#list groups as group>
                <tr>
                    <td>${group.name}</td>
                    <td>${group.description}</td>
                    <td>
                        <a class="btn btn-default" href="AdmModGroups?id=${group.id}">modifica</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="DeleteGroups?id=${group.id}">elimina</a>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <#--end container-->
        </div>
    <#--end col md 12-->
    </div>
<#--end container-->
</div>
<#--end row-->


<!--modulo contatti, email, conclusione-->
    <#include "tail.ftl">
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#group_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>