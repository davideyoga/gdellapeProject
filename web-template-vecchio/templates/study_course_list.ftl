<!DOCTYPE HTML>
<html>
<head>
    <title>gdellapeProject - Lista dei Corsi di Studio</title>

    <!--CAMBIO LINGUA-->
    <a href="ListStudyCourses?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>">Change Language EN</a>


</head>
<body>

<!--Eventuale messaggio di errore-->
<p><#if message??>${message}<#else></#if></p>

<!--Come un for-each, cicla sulla lista di corso di studi estraendo ogni volta il corso corrente della lista-->
<#list studyCourses as studyCourse>

<p> ${studyCourse.name}</p>
<p> ${studyCourse.code}</p>
<a href="StudyCourseProfile?code=${studyCourse.code}">Leggi di piu'</a>

</#list>

</body>
</html>