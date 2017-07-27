<!DOCTYPE HTML>
<html>
    <head>
        <title>Professor</title>

        <!--CAMBIO LINGUA-->
        <a href="UserProfile?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>&email=${user.email}">Cambia Lingua IT</a>


    </head>
    <body>

        <!--Eventuale messaggio di errore-->
        <p><#if message??>${message}<#else></#if></p>


        <p>${user.name} ${user.surname}</p>
        <p>Email: ${user.email}</p>
        <p>Telephone number: <#if user.number??>${user.number?string.computer}<#else>Not present</#if></p>
        <p>Curriculum:  <#if user.curriculum_eng??>
                ${user.curriculum_eng}
            <#elseif user.curriculum_ita??>
                ${user.curriculum_ita}
            <#else>
            </#if>
        </p>

        <p>Receprion hours:   <#if user.receprion_hours_eng??>
                ${user.receprion_hours_eng}
            <#elseif user.receprion_hours_ita??>
                ${user.receprion_hours_ita}
            <#else>
            </#if>
        </p>

    </body>
</html>