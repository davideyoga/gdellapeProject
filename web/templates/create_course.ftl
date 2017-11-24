<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lista Corsi di studio</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="title">
    Aggiungi materia
</div>

<div class="container">

    <#if message??>

        <h2>ATTENZIONE</h2>
            <div class="modalContent">
                <p>${message}</p>
            </div>

    <#else>

    </#if>

        <h2>Create Course</h2>
        <form method="POST" action="CreateCourse">
            <div class="col-md-6">

                <div class="create-group-name">
                    Name
                    <input type="text" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-group-description">
                    Code
                    <input type="text" name="code" >
                    <i class="fa fa-lock"></i>
                </div>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Create course">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>
</div>

<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>
