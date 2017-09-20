<div class="create_user_form">
    <h1><a href="create_user">PortaleDell'Universita'</a></h1>
    <div class="create-user-button">

        <p><#if message??>${message}<#else></#if></p>

        <h2>Add Book</h2>
        <form method="POST" action="AddBook">
            <div class="col-md-6">

                <div class="create-group-name">
                    Code
                    <input type="text" name="code" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-group-description">
                    Title
                    <input type="text" name="title" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-group-description">
                    Author
                    <input type="text" name="author" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="create-group-description">
                    Age
                    <input type="text" name="age" >
                    <i class="fa fa-lock"></i>
                </div>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Add Book">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>