<div class="create_user_form">
    <h1><a href="create_user">PortaleDell'Universita'</a></h1>
    <div class="create-user-button">

        <p><#if message??>${message}<#else></#if></p>

        <p><#if course.id??>${course.id}<#else></#if></p>
        <p><#if course.name??>${course.name}<#else></#if></p>

        <h2>Add Material</h2>
        <form method="POST" action="AddMaterial" enctype="multipart/form-data">
            <div class="col-md-6">

                <div class="create-group-name">
                    Name
                    <input type="text" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <p>Select the file to upload <input type='file' name='filetoupload'/></p>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Add Material" ajax="false">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>