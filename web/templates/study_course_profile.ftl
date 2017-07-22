<!DOCTYPE HTML>
<html>
<head>
    <title>Corso di Studi</title>


    <!--CAMBIO LINGUA-->
    <a href="StudyCourseProfile?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&code=${studyCourse.code}">Change Language EN</a>


</head>
<body>

<!--Eventuale messaggio di errore-->
<p><#if message??>${message}<#else></#if></p>



<p>${studyCourse.name} ${studyCourse.code}</p>
<p>Durata: ${studyCourse.duration}</p>
<p>Classe: <#if studyCourse.class??>${studyCourse.class}<#else>Non presente</#if></p>

<!--Tutta la descrizione del corso, per prendere spunto guardare user_profile.ftl-->




</body>
</html>