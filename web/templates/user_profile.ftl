<!DOCTYPE HTML>
<html>
<head>
    <title>Docente</title>


    <!--CAMBIO LINGUA-->
    <a href="UserProfile?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&email=${user.email}">Change Language EN</a>


</head>
<body>

<!--Eventuale messaggio di errore-->
<p><#if message??>${message}<#else></#if></p>




<p>${user.name} ${user.surname}</p>
<p>Email: ${user.email}</p>
<p>Numero di telefono: <#if user.number??>${user.number}<#else>Non presente</#if></p>

<p>Curriculum:  <#if user.curriculum_ita??>
                    ${user.curriculum_ita}
                <#elseif user.curriculum_eng??>
                    ${user.curriculum_eng}
                <#else>
                </#if>
</p>
<p>Orario di ricevimento:   <#if user.receprion_hours_ita??>
                                ${user.receprion_hours_ita}
                            <#elseif user.receprion_hours_eng??>
                                ${user.receprion_hours_eng}
                            <#else>
                            </#if>
</p>




</body>
</html>