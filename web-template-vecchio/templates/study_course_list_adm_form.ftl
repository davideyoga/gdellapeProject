<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>

    <#list studyCourses as studyCourse>

        <p>Id StudyCourse: ${studyCourse.id}</p>
        <p>Name Study Course: ${studyCourse.name}</p>
        <p>Code Study Course: ${studyCourse.code}</p>
        <a href="AdmModStudyCourse?id=${studyCourse.id}">Mod Study Course</a>

        <a href="DeleteStudyCourse?id=${studyCourse.id}">Delete Study Course</a>

    </#list>

    </div>
</div>