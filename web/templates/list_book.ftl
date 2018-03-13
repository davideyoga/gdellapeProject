<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi</title>

<#include "import.ftl">
</head>
<body>
<#include "navbar.ftl">
<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Lista libri</h3>
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
        <table id="book_table" class="table table-hover table-bordered table-striped">
            <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

            <thead>
            <tr>
                <th>Codice</th>
                <th>Titolo</th>
                <th>Autore</th>
                <th>Volume</th>
                <th>Anno</th>
                <th>Editore</th>
                <th>link</th>
            </tr>
            </thead>

            <tbody>
                    <#list books as book>
                    <tr>
                        <td>${book.code}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.volume}</td>
                        <td>${book.age}</td>
                        <td>${book.editor}</td>
                        <td>${book.link}</td>
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
        $("#book_table").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>