<!--questa pagina serve per vedere più dettagli di un docente-->
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

    <!--librerie-->
<#include "import.ftl">
</head>
<body>
<!--menù navigazione-->
<#include "navbar.ftl">

<p><#if message??>${message}<#else></#if></p>

<p>${userCurrent.name} ${userCurrent.surname}</p>

<p>Email: ${userCurrent.email}</p>

<p>Numero di telefono: <#if userCurrent.number??>${userCurrent.number?string.computer}<#else>Non presente</#if></p>

<p>Curriculum:  <#if userCurrent.curriculum_ita??>
                        ${userCurrent.curriculum_ita}
                    <#elseif userCurrent.curriculum_eng??>
                        ${userCurrent.curriculum_eng}
                    <#else>
                </#if>
</p>

<p>Orario di ricevimento:   <#if userCurrent.receprion_hours_ita??>
                                    ${userCurrent.receprion_hours_ita}
                                <#elseif userCurrent.receprion_hours_eng??>
                                    ${userCurrent.receprion_hours_eng}
                                <#else>
                            </#if>
</p>

<#--<div class="container">-->

    <#--<table class="responsive">-->
        <#--<!--Come un for-each, cicla sulla lista di users estraendo ogni volta l'utente della lista&ndash;&gt;-->

        <#--<thead>-->
        <#--<tr>-->
            <#--<th>Nome Docente</th>-->
            <#--<th>Email</th>-->
            <#--<th> - </th>-->
        <#--</tr>-->
        <#--</thead>-->

        <#--<tbody>-->
        <#--<#list users as user>-->
        <#--<tr>-->
            <#--<td>${user.name} ${user.surname}</td>-->
            <#--<td>${user.email}</td>-->
            <#--<td>-->
                <#--<a href="UserProfile?email=${user.email}">Leggi di piu'</a>-->
            <#--</td>-->
        <#--</tr>-->
        <#--</#list>-->
        <#--</tbody>-->
    <#--</table>-->
<#--</div>-->
<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>