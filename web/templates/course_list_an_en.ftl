<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi</title>

<#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('search').classList.remove('in');
            document.getElementById('search').classList.remove('active');
        }
    </script>
</head>
<body>
<#include "navbar_en.ftl">
<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Available Courses</h3>
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
            <a href="home?<#if lng == 'EN'>lng=EN<#elseif lng == 'IT'>lng=IT<#else>lng=EN</#if>" class="btn btn-warning my-text center-block" role="button">Back to home</a>
        </div>
        <div class="w3ls-heading page-header">
        </div>
        <ul class="nav nav-pills nav-stacked text-center">
            <li class="active"><a data-toggle="pill" href="#list">Courses list</a></li>
            <li><a data-toggle="pill" href="#search">Advanced search</a></li>
        </ul>
    </div>

    <div class="container">

        <div class="col-md-12 col-xs-12">
            <div class="tab-content">

                <div id="list" class="tab-pane fade in active">
                    <div class="table-responsive">
                        <table id="course_table" class="table table-hover table-bordered table-striped">
                            <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                            <thead>
                            <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Sector</th>
                                <th>Semester</th>
                                <th>Language</th>
                                <th> - </th>
                            </tr>
                            </thead>

                            <tbody>
                                <#list listCourse as course>
                                <tr>
                                    <td>${course.code}</td>
                                    <td>${course.name}</td>
                                    <td>${course.sector}</td>
                                    <td>${course.semester}</td>
                                    <td>${course.language}</td>
                                    <td>
                                        <a href="CourseProfile?id=${course.idCourse}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>">Leggi di piu'</a>
                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>

                    </div>
                </div><#--div list-->

                <div id="search" class="tab-pane fade">
                    <form class="my-form" action="ListCourseAn" method="GET">

                        <div class="form-group">
                            <label for="docent">Teacher name:</label>
                            <input type="text" class="form-control" id="docent" name="docent" value="">
                        </div>
                        <div class="form-group">
                            <label for="studyCourse">Degree name:</label>
                            <input type="text" class="form-control" id="studyCourse" name="studyCourse" value="">
                        </div>
                        <button type="submit" class="btn btn-default my-text center-block">Search</button>

                    </form>

                </div><#--div search-->

            </div><#--div tabcontent-->
        </div> <#--div col md 12-->
    </div><#--div container-->
</div><#--div row-->

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