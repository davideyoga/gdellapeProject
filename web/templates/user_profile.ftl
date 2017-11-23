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

<div class="container">

    <table class="responsive">
        <#--Come un for-each, cicla sulla lista di users estraendo ogni volta l'utente della lista&ndash;&gt;-->

        <thead>
        <tr>
            <th>${userCurrent.name} ${userCurrent.surname}</th>
            <th> - </th>
        </tr>
        </thead>

        <tbody>

        <tr>
            <td>
                Email:
            </td>
            <td>
                ${userCurrent.email}
            </td>
        </tr>
        <tr>
            <td>
                Numero di telefono:
            </td>
            <td>
                <#if userCurrent.number??>
                    ${userCurrent.number?string.computer}
                <#else>Non presente</#if>
            </td>
        </tr>
        <tr>
            <td>
                Curriculum:
            </td>
            <td>
                <#if userCurrent.curriculum_ita??>
                    ${userCurrent.curriculum_ita}
                 <#elseif userCurrent.curriculum_eng??>
                    ${userCurrent.curriculum_eng}
                 <#else>
                </#if>
            </td>
        </tr>
        <tr>
            <td>
                Orario di ricevimento:
            </td>
            <td>
                <#if userCurrent.receprion_hours_ita??>
                    ${userCurrent.receprion_hours_ita}
                 <#elseif userCurrent.receprion_hours_eng??>
                    ${userCurrent.receprion_hours_eng}
                 <#else>
                </#if>
            </td>
        </tr>

        </tbody>
    </table>
</div>
<!--modulo contatti, email, conclusione-->
<#include "tail.ftl">
</body>
</html>