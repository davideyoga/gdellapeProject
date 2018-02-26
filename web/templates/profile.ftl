<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

<#include "import.ftl">

</head>
<body>
<#include "navbar.ftl">

<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h3>Modifica dati ${user.name} ${user.surname}</h3>
        </div>
    </div>
</div>

<div class="container">
    <div>
        <#if message??>
            <div class="jumbotron">
                ${message}
            </div>
        <#else>
        </#if>
    </div>
</div>

<div class="container">

    <form method="POST" action="AdmModUser">
        <div class="col-md-6">

            <div class="align_left">
                <label>Email</label>
                <input type="email" value="<#if user.email??>${user.email}<#else></#if>" name="email"/>
                <i class="fa fa-envelope"></i>
            </div>

            <div class="align_left">
                <label>Password</label>
                <input type="password" value="<#if user.password??>${user.password}<#else></#if>" name="password">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Repeat Password</label>
                <input type="password" value="<#if user.password??>${user.password}<#else></#if>"
                       name="ripetere-password">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Surname</label>
                <input type="text" value="<#if user.surname??>${user.surname}<#else></#if>" name="surname">
                <i class="fa fa-envelope"></i>
            </div>

            <div class="align_left">
                <label>Name</label>
                <input type="text" value="<#if user.name??>${user.name}<#else></#if>" name="name">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Telephone Number</label>
                <input type="text" value="<#if user.number??>${user.number?string.computer}<#else>666</#if>"
                       name="number">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Italian Curriculum</label>
                <input type="text" value="<#if user.curriculum_ita??>${user.curriculum_ita}<#else></#if>"
                       name="curriculum_ita">
            </div>

            <div class="align_left">
                <label>English Curriculum</label>
                <input type="text" value="<#if user.curriculum_eng??>${user.curriculum_eng}<#else></#if>"
                       name="curriculum_eng">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Reception Hours in Italian</label>
                <input type="text"
                       value="<#if user.receprion_hours_ita??>${user.receprion_hours_ita}<#else></#if>"
                       name="receprion_hours_ita">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Reception Hours in English</label>
                <input type="text"
                       value="<#if user.receprion_hours_ita??>${user.receprion_hours_ita}<#else></#if>"
                       name="receprion_hours_eng"/>
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>l'utente fa parte dei seguenti gruppi: </label>
                <br>
            <#list listGroups as groups>
                <input type="checkbox" name="${groups.name}" value="${groups.name}" <#if listUserGroups?seq_contains(groups) >checked<#else></#if> > ${groups.name} <br>
            </#list>
            </div>
        </div>

        <input type="submit" value="Update">

        <div class="clearfix"></div>
    </form>
</div>
<#include "tail.ftl">

</body>
</html>