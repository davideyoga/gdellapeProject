<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>

        <#list courses as course>

            <p>Name Course: ${course.name}</p>
            <p>Code Course: ${course.code}</p>

            <a href="ModCourse?id=${course.idCourse}">Mod Course</a>

        </#list>

    </div>
</div>