<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>


        <h2>Profile</h2>
        <form method="POST" action="ProfileManagement">
            <div class="col-md-6">

                <div class="profile-form-password">
                    Password
                    <input type="password" value= "<#if user.password??>${user.password}<#else></#if>" name="password" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-ripetere-password">
                    Ripetere password
                    <input type="password" value= "<#if user.password??>${user.password}<#else></#if>" name="ripetere-password">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-surname">
                    Cognome
                    <input type="text" value="<#if user.surname??>${user.surname}<#else></#if>" name="surname">
                    <i class="fa fa-envelope"></i>
                </div>

                <div class="profile-form-name">
                    Nome
                    <input type="text" value= "<#if user.name??>${user.name}<#else></#if>" name="name">
                    <i class="fa fa-lock"></i>
                </div>


                <div class="profile-form-number">
                    Numero di telefono
                    <input type="number" value= "<#if user.number??>${user.number}<#else></#if>" name="number">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-curriculum_ita">
                    Curriculum in italiano
                    <input type="text" value= "<#if user.curriculum_ita??>${user.curriculum_ita}<#else></#if>" name="curriculum_ita">
                </div>

                <div class="profile-form-curriculum_eng">
                    Curriculum in inglese
                    <input type="text" value= "<#if user.curriculum_eng??>${user.curriculum_eng}<#else></#if>" name="curriculum_eng">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_ita">
                    Orario ricevimento in italiano
                    <input type="text" value= "<#if user.receprion_hours_ita??>${user.receprion_hours_ita}<#else></#if>" name="receprion_hours_ita">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_eng">
                    Orario ricevimento in inglese
                    <input type="text" value=   "<#if user.receprion_hours_eng??>${user.receprion_hours_eng}<#else></#if>" name="receprion_hours_eng">
                    <i class="fa fa-lock"></i>
                </div>



            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Aggiorna profilo">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>