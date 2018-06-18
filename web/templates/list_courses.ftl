<!DOCTYPE html>
<html lang="it">
<head>
    <title>Lista corsi</title>

    <!--librerie-->
<#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            carica();
        }
    </script>
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista dei corsi</h3>
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
        <div class="text-center"><a href="/home" class="btn btn-warning my-text center-block" role="button">torna al back office</a></div>
    </div>

    <div class="col-md-9 col-xs-9">

        <div>
            <form class="" action="ListCourse" method="GET">

                <div id="Accyear" class="form-group col-xs-2">
                    <label for="selectElementId">anno accademico:</label>
                    <select id="selectElementId" name="year" class="form-control">

                    </select>
                </div>

                <br>

                <div class="form-group">
                    <button type="submit" class="btn btn-default">Cerca</button>
                </div>

            </form>

        </div>

                <div class="table-responsive" >
                    <table class="table table-hover table-bordered table-striped">

                        <thead>
                        <tr>
                            <th>nome materia</th>
                            <th>codice materia</th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                            <#list courses as course>
                                <tr>
                                    <td>${course.name?lower_case}</td>
                                    <td>${course.code}</td>
                                    <td>
                                        <a class="btn btn-default" href="ModCourse?id=${course.idCourse}">modifica corso</a>
                                    </td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div><#--end table responsive-->


        </div><#--div tabcontent-->
    </div><#--end col md 12-->
</div><#--end row-->


<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>