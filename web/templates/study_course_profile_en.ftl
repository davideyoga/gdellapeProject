<!DOCTYPE html>
<html lang="en">
<head>
<#--sono le informazioni specifiche del corso di studi-->
    <title>Study Course Profile</title>

    <#include "import.ftl">
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById('desc').classList.remove('in');
            document.getElementById('desc').classList.remove('active');
            document.getElementById('course').classList.remove('in');
            document.getElementById('course').classList.remove('active');
        }
    </script>

</head>
<body>
<#include "navbar_en.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Details of ${studyCourse.name}</h3>
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
    <div class="col-md-2 col-xs-2 my-menu text-center">
        <div class="text-center">
            <a href="ListStudyCourses?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>" class="btn btn-warning my-text center-block" role="button">Back to StudyCourse list</a>
        </div>
    </div>


    <div class="container">

        <div class="text-center">
            <h4>Academic year</h4>
            <ul class="pagination">
                <li><a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear - 1)?string.computer}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>&code=${studyCourse.code} ">« previous</a></li>
                <li class="disabled"><a href="#">${accademicYear}</a></li>
                <li><a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear + 1)?string.computer}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>&code=${studyCourse.code} ">next »</a></li>
            </ul>
        </div>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#basic">Basic data</a></li>
            <li><a data-toggle="tab" href="#desc">Description</a></li>
            <li><a data-toggle="tab" href="#course">Related course</a></li>
        </ul>

        <div class="tab-content">
            <div id="basic" class="tab-pane fade in active">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">Basic data</h4></div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <h4>Study course name: </h4>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.name}</p>
                                    </div>
                                </div>
                                <br><br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Code:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.code}</p>
                                    </div>
                                </div>
                                <br>

                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Main language:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.language_eng}</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Duration:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.duration} year</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Department:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.department_eng}</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>EQF level:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.level_eng}</p>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Access mode:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.accessType_eng}</p>
                                    </div>
                                </div>
                                <br>
                            <#--<#if studyCourse.classes??>-->
                            <#--<div class="col-md-12 col-xs-12">-->
                            <#--<div class="col-md-3 col-xs-3 col-sm-3">-->
                            <#--<p>Classe</p>-->
                            <#--</div>-->
                            <#--<div class="col-md-9 col-xs-9 col-sm-9">-->
                            <#--<p> ${studyCourse.classes}</p>-->
                            <#--</div>-->
                            <#--</div>-->
                            <#--<br>-->
                            <#--</#if>-->
                                <div class="col-md-12 col-xs-12">
                                    <div class="col-md-3 col-xs-3 col-sm-3">
                                        <p>Available seat:</p>
                                    </div>
                                    <div class="col-md-9 col-xs-9 col-sm-9">
                                        <p>${studyCourse.seat}</p>
                                    </div>
                                </div>
                                <br>
                            </div>
                        </div>

                    </div> <#--end panel default-->

                </div> <#--div panel-group-->
            </div> <#--div id basic-->

            <div id="desc" class="tab-pane fade in active">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">Description:</h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-xs-12">
                                    <p>${studyCourse.description_eng}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <#--div panel-group-->
            </div> <#--div id desc-->

            <div id="course" class="tab-pane fade in active">
                <div class="panel-group">

                <#list courses>
                    <div class="table-responsive" >
                        <table class="table table-hover table-bordered table-striped">
                            <thead>
                            <tr>
                                <th colspan="3">Course provided with ${studyCourse.name}</th>
                            </tr>
                            </thead>
                            <#items as  course>
                            <tr>
                                <td>Course name</td>
                                <td >${course.name}</td>
                                <td ><a href="CourseProfile?id=${course.idCourse}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>">Read more</a></td>
                            </tr>

                            </#items>
                        </table>
                    </div>
                <#else>
                    <h3>No course provided at the moment</h3>
                </#list>

                </div> <#--div panel-group-->
            </div> <#--div id course-->
        </div> <#--div tab-content-->
    </div>  <#--div container-->

</div>



<#include "tail_en.ftl">
</body>
</html>