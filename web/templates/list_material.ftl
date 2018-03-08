<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Materiale esterno</title>

<#include "import.ftl">
</head>
<body>
<#include "navbar.ftl">
<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3><#if course.name??>Materiale relativo a ${course.name}<#else>Materiale esterno</#if></h3>
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

    <div class="table-responsive">
        <table id="course_table" class="table table-hover table-bordered table-striped">


            <thead>
            <tr>
                <th>Nome</th>
                <th>Descrizione</th>
                <th>Data di creazione</th>
                <th>Dimensione</th>
                <th>Tipo</th>
                <th>-</th>
            </tr>
            </thead>

            <tbody>
                    <#list materials as material>
                    <tr>
                        <td>${material.name}</td>
                        <td>${material.description_ita}</td>
                        <td>${material.data}</td>
                        <td>${material.size} kb</td>
                        <td>${material.type}</td>
                        <td><a href="downloadMaterial?id=${material.id}">scarica</a></td>
                    </tr>
                    </#list>
            </tbody>
        </table>
        <div>
        </div>
    </div>
</div>
<#--tutti questi div sono inutili ma ci devono stare-->

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