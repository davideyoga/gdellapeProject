<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>


        <h2>Mod ${user.email}</h2>
        <form method="POST" action="AdmModUser">
            <div class="col-md-6">

                <div class="profile-form-email">
                    Email
                    <input type="email" value= "<#if user.email??>${user.email}<#else></#if>" name="email" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-password">
                    Password
                    <input type="password" value= "<#if user.password??>${user.password}<#else></#if>" name="password" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-ripetere-password">
                    Repeat Password
                    <input type="password" value= "<#if user.password??>${user.password}<#else></#if>" name="ripetere-password">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-surname">
                    Surname
                    <input type="text" value="<#if user.surname??>${user.surname}<#else></#if>" name="surname">
                    <i class="fa fa-envelope"></i>
                </div>

                <div class="profile-form-name">
                    Name
                    <input type="text" value= "<#if user.name??>${user.name}<#else></#if>" name="name">
                    <i class="fa fa-lock"></i>
                </div>


                <div class="profile-form-number">
                    Telephone Number
                    <input type="number" value= "<#if user.number??>${user.number}<#else>666</#if>" name="number">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-curriculum_ita">
                    Italian Curriculum
                    <input type="text" value= "<#if user.curriculum_ita??>${user.curriculum_ita}<#else></#if>" name="curriculum_ita">
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

            <#list listGroups as groups>

                <input type="checkbox" name="${groups.name}" value="${groups.name}" <#if listUserGroups?seq_contains(groups) >checked<#else></#if> > ${groups.name} <br>

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