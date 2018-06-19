<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi</title>

<#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('search').classList.remove('in');
            document.getElementById('search').classList.remove('active');
            document.getElementById('Accyear').classList.remove('hidden');
            carica();
        }
    </script>
</head>
<body>
<#include "navbar_en.ftl">
<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Available Course</h3>
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
    <div class="col-md-2 col-xs-2 col-sm-2 my-menu">
        <div class="text-center">
            <a href="home?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-warning my-text center-block" role="button">Back to home</a>
        </div>
        <div class="w3ls-heading page-header">
        </div>
        <ul class="nav nav-pills nav-stacked text-center">
            <li class="active"><a data-toggle="pill" href="ListCourseAn?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>#list">Course list</a></li>
            <li><a data-toggle="pill" href="#search">Search a course</a></li>
        </ul>

        <div class="text-center">
            <a href="ListCourseAn?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>" class="btn btn-default" role="button">Reset</a>
        </div>
    </div>

    <div class="col-md-9 col-xs-9 col-sm-9">
        <div class="tab-content">

            <div id="list" class="tab-pane fade in active">
                <div class="table-responsive">
                    <table id="course_table" class="table table-hover table-bordered table-striped">
                        <!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->

                        <thead>
                        <tr>
                            <th>Code</th>
                            <th>Name </th>
                            <th>Sector</th>
                            <th>Semester</th>
                            <th>Language</th>
                            <td>Year</td>
                            <th> - </th>
                        </tr>
                        </thead>
<#--asdfadf-->
                        <tbody>
                        <#list listCourse as course>
                        <tr>
                            <td>${course.code}</td>
                            <td>${course.name}</td>
                            <td>${course.sector}</td>
                            <td>${course.semester}</td>
                            <td>${course.language}</td>
                            <td>${course.year}</td>
                            <td>
                                <a class="btn btn-default" href="CourseProfile?id=${course.idCourse}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">Read More</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                </div>
            </div><#--div list-->

            <div id="search" class="tab-pane fade in active">
                <form class="my-form" action="ListCourseAn" method="GET">

                <#--<div class="form-group">-->
                <#--<label for="docent">Nome docente:</label>-->
                <#--<input type="text" class="form-control" id="docent" name="docent" value="">-->
                <#--</div>-->
                <#--<div class="form-group">-->
                <#--<label for="studyCourse">Nome laurea:</label>-->
                <#--<input type="text" class="form-control" id="studyCourse" name="studyCourse" value="">-->
                <#--</div>-->
                    <div class="row">
                        <div class="form-group col-xs-3">
                            <label for="name">Search by Name</label>
                            <input type="text" class="form-control" id="name" name="name" value="">
                        </div>
                    </div>

                    <div id="Accyear" class="row hidden">
                        <div class="form-group col-xs-2">
                            <label for="selectElementId">Accademic Year:</label>
                            <select id="selectElementId" name="year" class="form-control">

                            </select>
                        </div>
                    </div>



                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#advSearch">Advanced Search</a>
                                </h4>
                            </div>
                            <div id="advSearch" class="panel-collapse collapse">
                                <div class="panel-body">

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="code">Course code</label>
                                            <input type="text" class="form-control" id="code" name="code" value="">
                                        </div>
                                    </div>

                                <#--<div class="form-group">-->
                                <#--<label for="sel1">cerca in base al docente:</label>-->
                                <#--<select class="form-control" id="sel1" name="docent">-->
                                <#--<option value="" selected>seleziona docente</option>-->
                                <#--<#list listTheacher as teach>-->
                                <#--<option value="${teach.id}">${teach.name} ${teach.surname}</option>-->
                                <#--</#list>-->
                                <#--</select>-->
                                <#--<br>-->
                                <#--</div>-->
                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="sel1">Docent:</label>
                                            <select class="form-control" id="sel1" name="docent">
                                                <option value="" selected>Select Docent:</option>
                                                <#list listTheacher as teach>
                                                    <option value="${teach.id}">${teach.name} ${teach.surname}</option>
                                                </#list>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="sel2">Study Course</label>
                                            <select class="form-control" id="sel2" name="studyCourse">
                                                <option value="" selected>Select Study course</option>
                                            <#list allStudyCourse as course>
                                                <option value="${course.id}">${course.name}</option>
                                            </#list>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="sector">Sector:</label>
                                            <input type="text" class="form-control" id="sector" name="sector" value="">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-2">
                                            <label for="sem">Semester:</label>
                                            <select class="form-control" id="sem">
                                                <option value="1">1</option>
                                                <option value="2">2</option>

                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-3">
                                            <label for="language">Language:</label>
                                            <input type="text" class="form-control" id="language" name="language" value="">
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-default my-text center-block">Search</button>

                </form>

            </div><#--div search-->

        </div><#--div tabcontent-->
    </div> <#--div col md 12-->
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