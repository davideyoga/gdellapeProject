<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Libri</title>

<#include "import.ftl">
    <script type="text/javascript">
        $(document).ready(function() {
            $('.associate').on('click',function() {
                console.log($(this).attr('id'));  //-->this will alert name of checked checkbox.
                    $.ajax({
                        type: "GET",
                        url: 'AddBookByExisting',
                        data: {
                            idBook: $(this).attr('id'),
                            idCourse: ${course.idCourse}
                        },
                        success: function(data) {
                            // book=data.idBook;
                            // $('#stat'+book).load(document.URL +  ' #stat', function () {
                            //      $("#status").replaceWith(function(){
                            //          return "<td id=\"status\"></td>";
                            //     });
                            // alert('libro associato');
                            location.reload(true);
                            // alert(data);
                            // $('#container').html(data);

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

    <script type="text/javascript">
        $(document).ready(function() {
            $('.delete').on('click',function() {
                console.log($(this).attr('id'));  //-->this will alert name of checked checkbox.
                    $.ajax({
                        type: "GET",
                        url: 'RemoveBook',
                        data: {
                            idBook: $(this).attr('id'),
                            idCourse: ${course.idCourse}
                        },
                        success: function(data) {
                            // alert('libro eliminato');
                            location.reload(true);
                            // alert(data);
                            // $('#container').html(data);
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
            <h3>Lista libri associati a ${course.name}</h3>
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
            <li class="active text-center"><a data-toggle="pill" href="#">Lista libri</a></li>
        </ul>
        <div class="w3ls-heading page-header">
        </div>
        <div class="text-center">
            <a href="AddBook?idCourse=${course.idCourse}" class="btn btn-warning my-text center-block m-b-5" role="button">Aggiugni nuovo libro</a>
            <a href="/ModAdmCourse?id=${course.idCourse}" class="btn btn-warning my-text center-block m-b-5" role="button">torna a ${course.name}</a>
        </div>
    </div>


    <div class="container">
        <div class="col-md-12 col-xs-12">


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
                            <th>elimina</th>
                            <th>stato</th>
                        </tr>
                        </thead>

                        <tbody>
                        <#assign i=1>
                    <#list books as book>
                    <tr>
                        <td>${book.code}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.volume}</td>
                        <td>${book.age}</td>
                        <td>${book.editor}</td>
                        <td>${book.link}</td>
                        <#--<td><input type="checkbox" class="associate" id="${book.id}" name="${book.id}" value="${book.id}" <#if booksByCourse?seq_contains(book) >checked<#else></#if>> ${course.name}, ${book.id}</td>-->
                        <#--<td><input type="checkbox" class="delete" id="${book.id}" name="idBook" value="${book.id}" > elimina</td>-->
                        <td><button type="button" id="${book.id}" name="${book.id}" value="${book.id}" class="btn btn-primary associate">Associa</button></td>
                        <td><button type="button" id="${book.id}" name="${book.id}" value="${book.id}" class="btn btn-danger delete">Elimina</button></td>
                        <td><#if booksByCourse?seq_contains(book) >associato <#else>non associato</#if></td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                    <div>
                    </div>
                </div>
            <#--end table responsive-->
        </div>
        <#--end col-->
    </div>
    <#--end container-->
</div>


<div id="container2"></div>
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