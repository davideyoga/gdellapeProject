<div class="create_user_form">
    <h1><a href="create_user">PortaleDell'Universita'</a></h1>
    <div class="create-user-button">

        <p><#if message??>${message}<#else></#if></p>

        <h2>Create Group</h2>
        <form method="POST" action="CreateGroups">
            <div class="col-md-6">

                <div class="create-group-name">
                    Name
                    <input type="text" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-group-description">
                    Description
                    <input type="text" name="description" >
                    <i class="fa fa-lock"></i>
                </div>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Create Group">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>