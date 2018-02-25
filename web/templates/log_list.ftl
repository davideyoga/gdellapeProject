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
    <div class="title"> Visualizza Log</div>

    <p><#if message??>${message}<#else></#if></p>

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