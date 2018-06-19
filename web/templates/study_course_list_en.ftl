<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Study Course List</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar_en.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Available Study Course</h3>
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

        <div class="col-md-12 col-xs-12">
            <div class="container">

                <div class="table-responsive">
                    <table id="sc_tab" class="table table-bordered table-striped table-hover">

                        <thead>
                        <tr>
                            <th>Study Course code</th>
                            <th>Name</th>
                            <th> - </th>
                        </tr>
                        </thead>

                        <tbody>
                    <#list studyCourses as studyCourse>
                    <tr>
                        <td>${studyCourse.code}</td>
                        <td>${studyCourse.name}</td>
                        <td>
                            <a class="btn btn-default" href="StudyCourseProfile?code=${studyCourse.code}&<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>">Read more</a>
                        </td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>


<!--modulo contatti, email, conclusione-->
<#include "tail_en.ftl">
<script src="/templates/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/templates/js/dataTables.bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $("#sc_tab").dataTable({
            "iDisplayLength": 10,
            "aLengthMenu": [[10, 25, 50, 100,  -1], [10, 25, 50, 100, "All"]]
        });
    });
</script>
</body>
</html>