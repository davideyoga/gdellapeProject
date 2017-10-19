<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">
<div class="container">

    <div class="title">
        Modifica dati ${usermod.name} ${usermod.surname}
    </div>

    <p><#if message??>${message}<#else></#if></p>

    <form method="POST" action="AdmModUser">
        <div class="col-md-6">

            <div class="align_left">
                <label>Email</label>
                <input type="email" value="<#if usermod.email??>${usermod.email}<#else></#if>" name="email"/>
                <i class="fa fa-envelope"></i>
            </div>

            <div class="align_left">
                <label>Password</label>
                <input type="password" value="<#if usermod.password??>${usermod.password}<#else></#if>" name="password" >
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Repeat Password</label>
                <input type="password" value="<#if usermod.password??>${usermod.password}<#else></#if>" name="ripetere-password">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Surname</label>
                <input type="text" value="<#if usermod.surname??>${usermod.surname}<#else></#if>" name="surname">
                <i class="fa fa-envelope"></i>
            </div>

            <div class="align_left">
                <label>Name</label>
                <input type="text" value="<#if usermod.name??>${usermod.name}<#else></#if>" name="name">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Telephone Number</label>
                <input type="text" value="<#if usermod.number??>${usermod.number?string.computer}<#else>666</#if>" name="number">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Italian Curriculum</label>
                <input type="text" value="<#if usermod.curriculum_ita??>${usermod.curriculum_ita}<#else></#if>" name="curriculum_ita">
            </div>

            <div class="align_left">
                <label>English Curriculum</label>
                <input type="text" value="<#if usermod.curriculum_eng??>${usermod.curriculum_eng}<#else></#if>" name="curriculum_eng">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Reception Hours in Italian</label>
                <input type="text" value="<#if usermod.receprion_hours_ita??>${usermod.receprion_hours_ita}<#else></#if>" name="receprion_hours_ita">
                <i class="fa fa-lock"></i>
            </div>

            <div class="align_left">
                <label>Reception Hours in English</label>
                <input type="text" value="<#if usermod.receprion_hours_ita??>${usermod.receprion_hours_ita}<#else></#if>" name="receprion_hours_eng"/>
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

        <div class="clearfix"> </div>
    </form>
</div>
<#include "tail.ftl">

</body>
</html>