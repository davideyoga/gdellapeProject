<div class="create_user_form">
    <h1><a href="create_user">PortaleDell'Universita'</a></h1>
    <div class="create-user-button">

        <p><#if message??>${message}<#else></#if></p>

        <h2>Create User</h2>
        <form method="POST" action="CreateUser">
            <div class="col-md-6">

                <div class="create-user-email">
                    Email
                    <input type="email" name="email" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-user-password">
                    Password
                    <input type="password" name="password" >
                    <i class="fa fa-lock"></i>
                </div>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Create User">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>