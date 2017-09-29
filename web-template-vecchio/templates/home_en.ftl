<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>

    <!--CAMBIO LINGUA-->
    <a href="Home?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>">Cambia Lingua IT</a>

    <!--FORM PER IL LOGIN-->
    <form method="GET" action="Login">
        <div class="col-md-6">

        </div>
        <div class="col-md-6 login-do">
            <label class="hvr-shutter-in-horizontal login-sub">
                <input type="submit" value="Login">
            </label>
        </div>

        <div class="clearfix"> </div>
    </form>


</head>
<body>


    <!--FORM PER la lista dei docenti-->
    <form method="GET" action="ListUser">
    <div class="col-md-6">

    </div>
    <div class="col-md-6 login-do">
        <label class="hvr-shutter-in-horizontal login-sub">
            <input type="submit" value="Docent List">
        </label>
    </div>

    <div class="clearfix"> </div>
</form>

    <!--LISTA DOCENTI-->
    <a href="ListUser?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">Professor list</a>

</body>
</html>