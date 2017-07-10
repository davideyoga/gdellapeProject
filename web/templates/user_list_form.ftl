<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>

        <#list users as user>

            <p>id user: ${user.id}</p>
            <p>email user: ${user.email}</p>
            <a href="AdmModUser?id=${user.id}">Mod User</a>

            <a href="DeleteUser?id=${user.id}">Delete User</a>

        </#list>

    </div>
</div>