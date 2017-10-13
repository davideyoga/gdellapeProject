<!DOCTYPE html>
<html lang="it">
<head>
    <title>Home</title>

<#include "import.ftl">

</head>
<body>

<#include "navbar.ftl">

<!-- Modal1 -->

<div class="modal-dialog">
    <!-- Modal content-->

    <div class="modal-header">


        <div class="signin-form profile">
            <h3 class="agileinfo_sign">Accedi</h3>
            <div class="login-form">
                <form action="#" method="post">
                    <input type="text" name="email" placeholder="E-mail" required="">
                    <input type="password" name="password" placeholder="Password" required="">
                    <div class="tp">
                    <#--bottone per login-->
                        <input type="submit" value="Accedi">
                    </div>
                </form>
            </div>
            <div class="login-social-grids">
                <ul>
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa fa-rss"></i></a></li>
                </ul>
            </div>
            <p><a href="#" data-toggle="modal" data-target="#myModal3" > Non hai un account?</a></p>
        </div>
    </div>

</div>
<#include "tail.ftl">

</body>
</html>