<div class="create_user_form">
    <h1><a href="create_user">PortaleDell'Universita'</a></h1>
    <div class="create-user-button">

        <p><#if message??>${message}<#else></#if></p>

        <h2>Create Course</h2>
        <form method="POST" action="CreateCourse">
            <div class="col-md-6">

                <div class="create-group-name">
                    Name
                    <input type="text" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-group-description">
                    Code
                    <input type="text" name="code" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-group-description">
                    Year(Prima parte dell'anno accademico)
                    <input type="number" name="year" >
                    <i class="fa fa-lock"></i>
                </div>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Create course">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>