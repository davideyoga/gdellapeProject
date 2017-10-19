<!-- header -->

<div class="header-w3ls-agileinfo">

    <div class="wthree_agile_top_header">
        <div class="logo-agileits">
            <h1><a href="/home"><span>U</span>niversit&agrave <i class="fa fa-graduation-cap" aria-hidden="true"></i></a></h1>
        </div>

        <div class="agileits_w3layouts_sign_in">
            <ul class="nav navbar-nav">
                    <#if user??>
                        <li class="dropdown" id="spaziatura">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" > ${user.name} <span class="glyphicon glyphicon-user white_color"></span></a>
                            <ul class="dropdown-menu">
                                <li class="dimension">
                                    <a href="/homeBackOffice" class="dimension">BackOffice <span class="glyphicon glyphicon-cog white_color pull-right"></span></a></li>

                                <li class="dimension">
                                    <a href="#" class="dimension">Profile <span class="glyphicon glyphicon-stats pull-right"></span></a></li>

                                <li class="dimension">
                                    <a href="#" class="dimension">Favourites <span class="glyphicon glyphicon-heart red_color pull-right"></span></a></li>

                                <li class="dimension">
                                    <a href="Logout" class="dimension">Log Out <span class="glyphicon glyphicon-log-out red_color pull-right"></span></a></li>
                            </ul>
                        </li>
                    <#else>
                        <li id="spaziatura1">
                        <a href="login">Login </a></li>
                    </#if>
                        <li><a href="Home?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>">Italian<img src="images/blank.gif" class="flag flag-it" alt="" /></a></li>
            </ul>

        </div>
    <div class="clearfix"> </div>
    </div>

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

                </div>
                <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">

                    <nav>
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <a href="/home">Home</a></li>
                            <li><a href="/ListStudyCourses_en" class="hvr-rectangle-out">Courses</a></li>
                            <li><a href="gianni" class="hvr-rectangle-out">Teachers</a></li>
                            <li><a href="#team" class="hvr-rectangle-out">Faculty</a></li>
                            <li><a href="#news" class="hvr-rectangle-out">News</a></li>
                            <li><a href="#gallery" class="hvr-rectangle-out">Services</a></li>
                            <li><a href="#contact" class="hvr-rectangle-out">Contacts</a></li>
                        </ul>

                    </nav>
                </div>
            </nav>
        </div>
    </div>
</div>
<!-- //header -->
