<!DOCTYPE HTML>
<html>
<head>
    <title>Study Course</title>


    <!--CAMBIO LINGUA-->
    <a href="StudyCourseProfile?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&code=${studyCourse.code}">Cambia Lingua IT</a>


</head>
<body>

<!--Eventuale messaggio di errore-->
<p><#if message??>${message}<#else></#if></p>



<p>${studyCourse.name} ${studyCourse.code}</p>
<p>Duration: ${studyCourse.duration}</p>
<p>Class: <#if studyCourse.class??>${studyCourse.class}<#else>Non presente</#if></p>

<!--Tutta la descrizione del corso, per prendere spunto guardare user_profile.ftl-->




</body>
</html>