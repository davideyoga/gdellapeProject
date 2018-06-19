<!--questa pagina serve per vedere tutti i professori, da anonimi, cioè non loggati-->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Docents List</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar_en.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Our Docent</h3>
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
            <a href="home?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>" class="btn btn-warning my-text center-block" role="button">Back to home</a>
        </div>
    </div>
    <div class="container">

        <div class="table-responsive" >
            <table id="user_table" class="table table-hover table-bordered table-striped">
                <!--Come un for-each, cicla sulla lista di users estraendo ogni volta l'utente della lista-->

                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th> - </th>
                </tr>
                </thead>

                <tbody>
                <#list users as user>
                <tr>
                    <td>${user.name} ${user.surname}</td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-default" href="UserProfile?email=${user.email}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>">Read more</a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!--modulo contatti, email, conclusione-->
<#include "tail_en.ftl">
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