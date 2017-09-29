<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>


        <h2>Mod ${studyCourse.name}</h2>
        <form method="POST" action="AdmModStudyCourse">
            <div class="col-md-6">

                <div class="profile-form-email">
                    Name
                    <input type="text" value= "<#if studyCourse.name??>${studyCourse.name}<#else></#if>" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-password">
                    Code
                    <input type="number" value= "<#if studyCourse.code??>${studyCourse.code}<#else></#if>" name="code" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-ripetere-password">
                    Description Ita
                    <input type="text" value= "<#if studyCourse.description_ita??>${studyCourse.description_ita}<#else></#if>" name="description_ita">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-surname">
                    Description Eng
                    <input type="text" value="<#if studyCourse.description_eng??>${studyCourse.description_eng}<#else></#if>" name="description_eng">
                    <i class="fa fa-envelope"></i>
                </div>

                <div class="profile-form-name">
                    department_ita
                    <input type="text" value= "<#if studyCourse.department_ita??>${studyCourse.department_ita}<#else></#if>" name="department_ita">
                    <i class="fa fa-lock"></i>
                </div>


                <div class="profile-form-number">
                    department_eng
                    <input type="text" value= "<#if studyCourse.department_eng??>${studyCourse.department_eng}<#else></#if>" name="department_eng">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-curriculum_ita">
                    level_ita
                    <input type="text" value= "<#if studyCourse.level_ita??>${studyCourse.level_ita}<#else></#if>" name="level_ita">
                </div>

                <div class="profile-form-curriculum_eng">
                    level_eng
                    <input type="text" value= "<#if studyCourse.level_eng??>${studyCourse.level_eng}<#else></#if>" name="level_eng">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_ita">
                    duration
                    <input type="text" value= "<#if studyCourse.duration??>${studyCourse.duration}<#else></#if>" name="duration">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_eng">
                    class
                    <input type="number" value="<#if studyCourse.class??>${studyCourse.class}<#else></#if>" name="class">
                    <i class="fa fa-lock"></i>
                </div>


                <div class="profile-form-receprion_hours_eng">
                    seat
                    <input type="text" value=   "<#if studyCourse.seat??>${studyCourse.seat}<#else></#if>" name="seat">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_eng">
                    accessType_ita
                    <input type="text" value=   "<#if studyCourse.accessType_ita??>${studyCourse.accessType_ita}<#else></#if>" name="accessType_ita">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_eng">
                    accessType_eng
                    <input type="text" value=   "<#if studyCourse.accessType_eng??>${studyCourse.accessType_eng}<#else></#if>" name="accessType_eng">
                    <i class="fa fa-lock"></i>
                </div>


                <div class="profile-form-receprion_hours_eng">
                    language_ita
                    <input type="text" value=   "<#if studyCourse.language_ita??>${studyCourse.language_ita}<#else></#if>" name="language_ita">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_eng">
                    language_eng
                    <input type="text" value=   "<#if studyCourse.language_eng??>${studyCourse.language_eng}<#else></#if>" name="language_eng">
                    <i class="fa fa-lock"></i>
                </div>


                <a href="ModAssociationStudyCourseWithCourse?idStudyCourse=${studyCourse.id}">Mod Association With Course</a>


                <!--CICLO SUI CORSI-->

                <!--CICLO TUTTI I CORSI-->
                <!--se il corso e' contenuto nei corsi del corso di studi allora il checkbox sara' spuntato-->

                <#list listCourses as course>

                    <input type="checkbox" name="${course.name}" value="${course.name}" <#if listCourseByStudyCourse?seq_contains(course) >checked<#else></#if> > ${course.name} <br>

                </#list>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Update">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>