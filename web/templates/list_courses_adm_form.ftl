<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>

    ${accademicYear}

    <#list courses as course>

        <p>Id Course: ${course.idCourse}</p>
        <p>Name Course: ${course.name}</p>
        <p>Code Course: ${course.code}</p>

        <a href="ModAdmCourse?id=${course.idCourse}">Mod Course</a>

    </#list>

    </div>
</div>