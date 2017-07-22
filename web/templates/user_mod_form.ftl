<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>


        <h2>Mod ${user.email}</h2>
        <form method="POST" action="AdmModStudyCourse">
            <div class="col-md-6">

                <div class="profile-form-email">
                    Email
                    <input type="text" value= "<#if studyCourse.code??>${studyCourse.code}<#else></#if>" name="code" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-password">
                    Password
                    <input type="text" value= "<#if studyCourse.name??>${studyCourse.name}<#else></#if>" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-surname">
                    Surname
                    <input type="text" value="<#if studyCourse.description_ita??>${studyCourse.description_ita}<#else></#if>" name="description_ita">
                    <i class="fa fa-envelope"></i>
                </div>

                <div class="profile-form-name">
                    Name
                    <input type="text" value= "<#if studyCourse.description_eng??>${studyCourse.description_eng}<#else></#if>" name="description_eng">
                    <i class="fa fa-lock"></i>
                </div>


                <div class="profile-form-number">
                    Telephone Number
                    <input type="number" value= "<#if studyCourse.department_ita??>${studyCourse.department_ita}<#else></#if>" name="department_ita">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-curriculum_ita">
                    Italian Curriculum
                    <input type="text" value= "<#if studyCourse.department_eng??>${studyCourse.department_eng}<#else></#if>" name="department_eng">
                </div>

                <div class="profile-form-curriculum_eng">
                    English Curriculum
                    <input type="text" value= "<#if user.curriculum_eng??>${user.curriculum_eng}<#else></#if>" name="curriculum_eng">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_ita">
                    Reception Hours in Italian
                    <input type="text" value= "<#if user.receprion_hours_ita??>${user.receprion_hours_ita}<#else></#if>" name="receprion_hours_ita">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_eng">
                    Reception Hours in English
                    <input type="text" value=   "<#if user.receprion_hours_ita??>${user.receprion_hours_ita}<#else></#if>" name="receprion_hours_eng">
                    <i class="fa fa-lock"></i>
                </div>

                <!--RESTO DEI CAMPI-->

                <!--CICLO SULLA LISTA DEI CORSI PER FARE I CECKBOX (VEDERE USER CON GROUPS, OPPURE GROUPS CON SERVICE)-->

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