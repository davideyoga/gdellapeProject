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

<#--NON FUNZIONA MANCO PE CAZZI-->
<#--<p>${user.name} ${user.surname}</p>-->

<#--<p>Email: ${user.email}</p>-->

<#--<p>Numero di telefono: <#if user.number??>${user.number?string.computer}<#else>Non presente</#if></p>-->

<#--<p>Curriculum:  <#if user.curriculum_ita??>-->
                        <#--${user.curriculum_ita}-->
                    <#--<#elseif user.curriculum_eng??>-->
                        <#--${user.curriculum_eng}-->
                    <#--<#else>-->
                <#--</#if>-->
<#--</p>-->

<#--<p>Orario di ricevimento:   <#if user.receprion_hours_ita??>-->
                                    <#--${user.receprion_hours_ita}-->
                                <#--<#elseif user.receprion_hours_eng??>-->
                                    <#--${user.receprion_hours_eng}-->
                                <#--<#else>-->
                            <#--</#if>-->
<#--</p>-->

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