<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="log-list">

        <p><#if message??>${message}<#else></#if></p>

    <#list logs as log>

        <p>id log: ${log.id}</p>
        <p>id user: ${log.idUser}</p>
        <p>log description: ${log.description}</p>
        <p>log date: ${log.date}</p>

    </#list>

    </div>
</div>