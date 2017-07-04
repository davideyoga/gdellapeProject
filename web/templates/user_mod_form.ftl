<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>


        <h2>Mod ${user.email}</h2>
        <form method="POST" action="AdmModUser">
            <div class="col-md-6">

                <div class="profile-form-email">
                    Password
                    <input type="email" value= "<#if user.email??>${user.email}<#else></#if>" name="password" >
                    <i class="fa fa-lock"></i>
                </div>

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
                    <input type="number" value= "<#if user.number??>${user.number}<#else>666</#if>" name="number">
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
                    <input type="text" value=   "<#if user.receprion_hours_ita??>${user.receprion_hours_ita}<#else></#if>" name="receprion_hours_eng">
                    <i class="fa fa-lock"></i>
                </div>

            <#list listGroups as groups>

                <!--listUserGroups e' la lista dei gruppi che ha l'utente-->

                <#assign x = ["red", 16, "blue", "cyan"]>
                "blue": ${x?seq_contains("blue")?string("yes", "no")}
                "yellow": ${x?seq_contains("yellow")?string("yes", "no")}
                16: ${x?seq_contains(16)?string("yes", "no")}
                "16": ${x?seq_contains("16")?string("yes", "no")

                <input type="checkbox" name="vehicle" value="Bike" <#if ${listUserGroups?seq_contains(groups)?>checked<#else></#if>> I have a bike<br>

            </#list>

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