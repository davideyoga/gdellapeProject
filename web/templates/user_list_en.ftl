<!DOCTYPE HTML>
<html>
<head>
    <title>gdellapeProject - Docent List</title>



    <!--CAMBIO LINGUA-->
    <a href="ListUser?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>">Cambia Lingua IT</a>

</head>
<body>

<!--Eventuale messaggio di errore-->
<p><#if message??>${message}<#else></#if></p>

<!--Come un for-each, cicla sulla lista di users estraendo ogni volta l'utente della lista-->
<#list users as user>

<p>${user.name} ${user.surname}</p>
<p>${user.email}</p>
<a href="UserProfile?email=${user.email}">Read More</a>

</#list>

</body>
</html>