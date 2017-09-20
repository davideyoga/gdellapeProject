<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <form method="POST" action="ModCourseRelation">


        <#list allCourse as course>

            <!--se il corso e' presente tra i corsi associati al corso di studi preso in esame, setta il checkbox a true, false altrimenti -->
            <input type="checkbox" name="${course.idCourse}" value="${course.idCourse}" <#if courseRelated?seq_contains(course) >checked<#else></#if> > ${course.name}


        </#list>

            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Update">
                </label>
            </div>

        </form>
    </div>
</div>