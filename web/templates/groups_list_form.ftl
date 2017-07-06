<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>

    <#list groups as group>

        <p>id group: ${group.id}</p>
        <p>name group: ${group.name}</p>
        <a href="AdmModGroups?id=${group.id}">Mod Group</a>

    </#list>

    </div>
</div>