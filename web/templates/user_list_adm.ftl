<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista utenti</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista utenti</h3>
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

    <div class="table-responsive" >
        <table id="user_table" class="table table-hover table-bordered table-striped">

            <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>  </th>
                <th>  </th>
            </tr>
            </thead>

            <tbody>
            <#list users as utente>
            <tr>
                <td>${utente.id}</td>
                <td>${utente.email}</td>
                <td>
                    <a href="AdmModUser?id=${utente.id}">Modifica utente</a>
                </td>
                <td>
                    <a href="DeleteUser?id=${utente.id}" onclick="return ConfirmDelete(${utente.surname},${utente.name})">Cancella Utente</a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<#include "tail.ftl">
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#user_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>