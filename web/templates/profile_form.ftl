<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p>${message?cap_first}</p>

        <h2>Profile</h2>
        <form method="POST" action="ProfileManagement">
            <div class="col-md-6">

                <div class="profile-form-email">
                    Email
                    <input type="email" placeholder= "${user.email}" name="email" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-password">
                    Password
                    <input type="password" placeholder= "${user.password}" name="password" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-ripetere-password">
                    Ripetere password
                    <input type="password" placeholder= "${user.password}" name="ripetere-password">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-surname">
                    Cognome
                    <input type="text" placeholder="${user.surname}" name="surname">
                    <i class="fa fa-envelope"></i>
                </div>

                <div class="profile-form-name">
                    Nome
                    <input type="text" placeholder= "${user.name}" name="name">
                    <i class="fa fa-lock"></i>
                </div>


                <div class="profile-form-number">
                    Numero di telefono
                    <input type="number" placeholder= "${user.number}" name="number">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-curriculum_ita">
                    Curriculum in italiano
                    <input type="text" placeholder= "${user.curriculum_ita}" name="curriculum_ita">
                </div>

                <div class="profile-form-curriculum_eng">
                    Curriculum in inglese
                    <input type="text" placeholder= "${user.curriculum_eng}" name="curriculum_eng">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_ita">
                    Orario ricevimento in italiano
                    <input type="text" placeholder= "${user.receprion_hours_ita}" name="receprion_hours_ita">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="profile-form-receprion_hours_eng">
                    Orario ricevimento in inglese
                    <input type="text" placeholder= "${user.receprion_hours_eng}" name="receprion_hours_eng">
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