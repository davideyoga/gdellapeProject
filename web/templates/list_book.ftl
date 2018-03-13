<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi</title>

<#include "import.ftl">
    <script type="text/javascript">
        $(document).ready(function() {
            $('.associate').on('change',function() {
                console.log($(this).attr('id'));  //-->this will alert name of checked checkbox.
                if(this.checked){
                    $.ajax({
                        type: "GET",
                        url: 'AddBookByExisting',
                        data: {
                            idBook: $(this).attr('id'),
                            idCourse: ${course.idCourse}
                        },
                        success: function(data) {
                            alert('it worked');
                            alert(data);
                            $('#container').html(data);
                        },
                        error: function() {
                            alert('it broke');
                        },
                        complete: function() {
                            alert('it completed');
                        }
                    });

                }
            });
        });
    </script>
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
                <th>associa</th>
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
                        <td><input type="checkbox" class="associate" id="${book.id}" name="${book.id}" value="${book.id}"> ${course.name}, ${book.id}</td>
                    </tr>
                    </#list>
            </tbody>
        </table>
        <div>
        </div>
    </div>
</div>

<div id="container"></div>
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