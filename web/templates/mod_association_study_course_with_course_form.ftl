<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>

        <form method="POST" action="ModAssociationStudyCourseWithCourse">

            <div>Study Course: ${studyCourse.name}</div>

            <#list allCourses as course>

                <!--se il corso e' presente tra i corsi associati al corso di studi preso in esame, setta il checkbox a true, false altrimenti -->
                <input type="checkbox" name="${course.name}" value="${course.name}" <#if coursesMatch?seq_contains(course) >checked<#else></#if> > ${course.name}

                <!--VA INSERITO value = "tipo di cfu con id = al corso preso in esame"-->
                cfu type: <input type="text" name="cfuType${course.name}">  <br>

            </#list>

            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Update">
                </label>
            </div>

        </form>
    </div>
</div>