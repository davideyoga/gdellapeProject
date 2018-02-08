<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
<#--sono le informazioni specifiche della materia-->
    <title>
        Home
    </title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">

<div class="container">
    <p><#if message??>${message}<#else></#if></p>
    <div class="title personal-title align_left" >
        dettagli corso
    </div>
    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course background">
            <div class="col-md-2 col-xs-4">
                <div class="contact-info-right">
                <h5>Nome corso: </h5>

                <p>Codice: <br>
                </p>

                <p>Tipo: <br>
                </p>

                <p>Anno: <br>
                </p>

                <p>Cfu: <br>
                </p>

                <p>Settore: <br>
                </p>

                <p>Lingua: <br>
                </p>

                <p>Semestre: <br>
                </p>

                </div>
            </div>
            <div class="col-md-10 col-xs-4">
                <div class="contact-info-right">
                <h5>${course.name}</h5>

                <p>${course.code}<br>
                </p>

                <p>${course.type}<br>
                </p>

                <p>${course.year}<br>
                </p>

                <p>${course.cfu}<br>
                </p>

                <p>${course.sector}<br>
                </p>

                <p>${course.language}<br>
                </p>

                <p>${course.semester}<br>
                </p>

                </div>
            </div>
        </div>
    </div>

    <#--Prerequisiti-->
    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course background">
            <div class="contact-info-right">
                <h5>Prerequisiti: </h5>
            <p>${course.prerequisite_ita}<br>
            </p>
            </div>

        </div>
    </div>

    <#--Obiettivi-->
    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course background">
            <div class="contact-info-right">
                <h5>Obiettivi: </h5>
                <p>${course.goals_ita}<br>
                </p>
            </div>

        </div>
    </div>

    <#--Modalità d'esame-->
    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course background">
            <div class="contact-info-right">
                <h5>Modalità d'esame: </h5>
                <p>${course.exame_mode_ita}<br>
                </p>
            </div>

        </div>
    </div>

    <#--Modalità d'insegnamento-->
    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course background">
            <div class="contact-info-right">
                <h5>Modalità d'insegnamento: </h5>
                <p>${course.teaching_mode_ita}<br>
                </p>
            </div>

        </div>
    </div>

    <#--Sillabo-->
    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course background">
            <div class="contact-info-right">
                <h5>Sillabo: </h5>
                <p>${course.syllabus_ita}<br>
                </p>
            </div>

        </div>
    </div>

    <#--Note-->
    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course background">
            <div class="contact-info-right">
                <h5>Note: </h5>
                <p>${course.note_ita}<br>
                </p>
            </div>

        </div>
    </div>

    <div class="clearfix"></div>

</div>

<#include "tail.ftl">

</body>
</html>