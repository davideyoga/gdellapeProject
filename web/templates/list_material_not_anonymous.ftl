<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista</title>

    <!--librerie-->
    <#include "import.ftl">
    <script type="text/javascript">
        $(document).ready(function() {
            $('.delete').on('click',function() {
                console.log($(this).attr('id'));  //-->this will alert name of checked checkbox.
                $.ajax({
                    type: "GET",
                    url: 'DeleteMaterial',
                    data: {
                        idMaterial: $(this).attr('id')
                    },
                    success: function(data) {
                        alert('materiale rimosso');
                        location.reload(true);
                    },
                    error: function() {
                        console.log('errore');
                    },
                    complete: function() {
                        console.log('chiamata ajax completata');
                    }
                });
            });
        });
    </script>
</head>
<body>
<#include "navbar.ftl">
<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista di tutto il materiale</h3>
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
        <ul class="nav nav-pills nav-stacked">
            <li class="active text-center"><a data-toggle="pill" href="#">Lista materiale</a></li>
        </ul>
        <div class="w3ls-heading page-header">
        </div>
        <div class="text-center">
            <a href="AddMaterial?idCourse=${course.idCourse}" class="btn btn-warning my-text center-block m-b-5" role="button">Aggiugni nuovo materiale</a>
            <a href="/ModAdmCourse?id=${course.idCourse}" class="btn btn-warning my-text center-block m-b-5" role="button">torna a ${course.name}</a>
        </div>
    </div>


    <div class="container">
        <div class="col-md-12 col-xs-12">


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
                            <#list material as material>
                            <tr>
                                <td>${material.name}</td>
                                <td>${material.description_ita}</td>
                                <td>${material.data}</td>
                                <td>${material.size} kb</td>
                                <td>${material.type}</td>
                                <td><button type="button" id="${material.id}" name="${material.id}" value="${material.id}" class="btn btn-info delete">elimina</button></td>
                            </tr>
                            </#list>
                    </tbody>
                </table>
            </div>
        <#--end table responsive-->
        </div>
    <#--end col-->
    </div>
<#--end container-->
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