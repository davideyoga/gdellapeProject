<!-- header -->

<div class="header-w3ls-agileinfo">

    <div class="wthree_agile_top_header">
        <div class="logo-agileits">
            <h1><a href="/home"><span>U</span>niversit&agrave <i class="fa fa-graduation-cap" aria-hidden="true"></i></a></h1>
        </div>

        <div class="agileits_w3layouts_sign_in">
            <ul>
                <#--<li><a class="active" href="#" data-toggle="modal" data-target="#myModal2" >Sign In</a></li>
                <li><a href="#" data-toggle="modal" data-target="#myModal3" >Sign Up</a></li>-->
                    <li><a class="active" href="login" >Login</a></li>
                    <li><a href="register" >Register</a></li>
            </ul>

        </div>

        <div class="clearfix"> </div>
        </div>
    <#--language change-->
    <div align="right" class="language-selection language">
        <a href="Home?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>">Switch to English <img src="images/blank.gif" class="flag flag-gb" alt="" /></a>
    </div>
    <#--language change-->

    <div class="container">
        <div class="w3layouts_agileits_nav_section">
            <nav class="navbar navbar-default">
                <div class="navbar-header navbar-left">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="w3ls__agileinfo_search">
                    <form action="#" method="post">
                        <input type="search" name="Search" placeholder="Search here..." required="">
                        <input type="submit" value=" ">
                    </form>
                    </a>
                </div>
                <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">

                    <nav>
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="/home">Home</a></li>

                            <li><a href="#about" class="hvr-rectangle-out scroll">About</a></li>
                            <li><a href="#services" class="hvr-rectangle-out scroll">Objectives</a></li>
                            <li><a href="#team" class="hvr-rectangle-out scroll">Faculties</a></li>
                            <li><a href="#news" class="hvr-rectangle-out scroll">News</a></li>
                            <li><a href="#gallery" class="hvr-rectangle-out scroll">Gallery</a></li>
                            <li><a href="#contact" class="hvr-rectangle-out scroll">Contact</a></li>
                        </ul>

                    </nav>
                </div>
            </nav>
        </div>
    </div>
</div>
<!-- //header -->


<!-- Modal2 -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>

                <div class="signin-form profile">
                    <h3 class="agileinfo_sign">Sign Up</h3>
                    <div class="login-form">
                        <form action="#" method="post">
                            <input type="text" name="name" placeholder="Username" required="">
                            <input type="email" name="email" placeholder="Email" required="">
                            <input type="password" name="password" placeholder="Password" required="">
                            <input type="password" name="password" placeholder="Confirm Password" required="">
                            <input type="submit" value="Sign Up">
                        </form>
                    </div>
                    <p><a href="#"> By clicking register, I agree to your terms</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //Modal2 -->