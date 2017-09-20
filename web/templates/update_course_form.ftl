<div class="create_user_form">
    <h1><a href="create_user">PortaleDell'Universita'</a></h1>
    <div class="create-user-button">

        <p><#if message??>${message}<#else></#if></p>

        <h2>Update Course</h2>

        <p><#if course.id??>${course.id}<#else></#if></p>

        <p><#if course.name??>${course.name}<#else></#if></p>

        <form method="POST" action="UpdateCourse">
            <div class="col-md-6">

                <div class="create-group-description">
                    Year
                    <input type="text" name="year" >
                    <i class="fa fa-lock"></i>
                </div>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Update Course">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>