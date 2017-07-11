<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>

    <#list groups as group>

        <p>Id Group: ${group.id}</p>
        <p>Name Group: ${group.name}</p>
        <a href="AdmModGroups?id=${group.id}">Mod Group</a>

        <a href="DeleteGroups?id=${group.id}">Delete Group</a>

    </#list>

    </div>
</div>