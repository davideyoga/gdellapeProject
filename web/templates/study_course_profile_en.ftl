<!DOCTYPE HTML>
<html>
<head>
    <title>Study Course</title>


    <!--CAMBIO LINGUA-->
    <a href="StudyCourseProfile?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&code=${studyCourse.code}&age=${currentFirstYear?string.computer}">Cambia Lingua IT</a>


</head>
<body>

<!--Eventuale messaggio di errore-->
<p><#if message??>${message}<#else></#if></p>


<p>Accademic Year: ${accademicYear}</p>


<a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear - 1)?string.computer}&<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&code=${studyCourse.code} ">Previous year</a>
<a href="StudyCourseProfile?code=${studyCourse.code}&age=${(currentFirstYear + 1)?string.computer}&<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&code=${studyCourse.code} ">Next year</a>

<p>${studyCourse.name} ${studyCourse.code}</p>
<p>Duration: ${studyCourse.duration}</p>

<p>Class: <#if studyCourse.class??>${studyCourse.class}<#else>Non presente</#if></p>

<!--Tutta la descrizione del corso, per prendere spunto guardare user_profile.ftl-->

<br>

<p>CORSI APPARTENENTI AL CORSO DI STUDI (DA SCRIVERE IN INGLESE)</p>


<br>
<#list courses as course>
<br>
<p>Course name: ${course.name}</p>
<p>AGGIUNGERE LINK AL CORSO </p>

</#list>



</body>
</html>