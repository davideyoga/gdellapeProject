<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Modifica gruppo</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Modifica <#if groups.name??>${groups.name}<#else> gruppo</#if> </h3>
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


<form action="admModGroups" method="POST" id="mod" class="my-form" >
<#--<div class="row" style="border: #00d21f 5px solid">-->
    <div class="row">
        <div class="col-md-2 col-xs-2 my-menu">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#basic">Informazioni di base</a></li>
                <li><a data-toggle="pill" href="#serv">Associa servizi</a></li>
            </ul>
            <br>
            <button type="submit" form="mod" class="btn btn-default">Modifica gruppo</button>
            <div class="w3ls-heading page-header">
            </div>
            <div class="text-center"><a href="/AdmGetListGroups" class="btn btn-warning my-text center-block" role="button">torna alla lista dei gruppi</a></div>
        </div>


        <div class="container">
        <#if message??>
            <div class="title">
                <h2>ATTENZIONE</h2>
                <div class="modalContent">
                    <p>${message}</p>
                </div>
            </div>
        <#else>

        </#if>

            <div class="col-md-12 col-xs-12">
                <div class="tab-content">

                    <div id="basic" class="tab-pane fade in active">
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" id="nome" name="name" value="<#if groups.name??>${groups.name}<#else></#if>">
                        </div>
                        <div class="form-group">
                            <label for="desc">Descrizione:</label>
                            <textarea class="form-control" rows="5" id="desc" name="description"><#if groups.description??>${groups.description}<#else></#if></textarea>
                        </div>
                    </div>
                <#--end basic-->

                    <div id="serv" class="tab-pane fade">
                        <div class="table-responsive" >
                            <table id="groups_table" class="table table-hover table-bordered table-striped">
                                <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                                <thead>
                                <tr>
                                    <th>nome</th>
                                    <th>descrizione</th>
                                    <th> associa </th>
                                </tr>
                                </thead>

                                <tbody>
                                    <#list listService as service>
                                    <tr>
                                        <td>${service.name}</td>
                                        <td>${service.description}</td>
                                        <td>
                                            <input type="checkbox" name="${service.name}" value="${service.name}" <#if listGroupsService?seq_contains(service) >checked<#else></#if> >
                                        </td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                <#--end serv-->


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
        $("#groups_table").dataTable({
            "iDisplayLength": -1,
            "aLengthMenu": [[-1], ["All"]]
        });
    });
</script>
</body>
</html>
