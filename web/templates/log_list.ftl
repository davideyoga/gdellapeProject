<!DOCTYPE html>
<html lang="it">
<head>
    <title>Log</title>

    <!--librerie-->
    <#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
    <#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Visualizza log</h3>
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
        <div class="text-center"><a href="homeBackOffice" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
    </div>


    <div class="container">

        <div class="table-responsive" >
            <table id="log_tab" class="table table-hover table-bordered table-striped">
                <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                <thead>
                <tr>
                    <th>Azione</th>
                    <th>id utente</th>
                </tr>
                </thead>

                <tbody>
                <#list logs as log>
                <tr>
                    <td>${log.description}</td>
                    <td>${log.idUser}</td>
                <#--<td>-->
                <#--<a href="AdmModGroups?id=${group.id}">modifica</a>-->
                <#--</td>-->
                </tr>
                    <#if (log?counter%10) == 0 > <#else></#if>
                </#list>
                </tbody>
            </table>
        </div>
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
        $("#log_tab").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>

</body>
</html>