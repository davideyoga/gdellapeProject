<!-- header -->

<div class="header-w3ls-agileinfo">

    <#assign lng="EN">

    <div class="wthree_agile_top_header">
        <div class="logo-agileits">
            <h1><a href="/home?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>"><span>U</span>niversit&agrave <i class="fa fa-graduation-cap" aria-hidden="true"></i></a></h1>
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
                                    <a href="ProfileManagement" class="dimension">Profile<span class="glyphicon glyphicon-stats pull-right"></span></a></li>

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

    <div class="row">
        <div class="w3layouts_agileits_nav_section container">
            <nav class="navbar navbar-default">
                <!-- Collect the nav links, forms, and other content for toggling -->
                <#--<div class="w3ls__agileinfo_search">-->
                    <#--<form action="#" method="post">-->
                        <#--<input type="search" name="Search" placeholder="Search here..." required="">-->
                        <#--<input type="submit" value=" ">-->
                    <#--</form>-->

                <#--</div>-->
                <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">

                    <nav>
                        <ul class="nav navbar-nav">
                            <li><a href="/home?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>">Home</a></li>
                            <li><a href="/ListStudyCourses?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>" class="hvr-rectangle-out">Degree</a></li>
                            <li><a href="/listCourseAn?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>" class="hvr-rectangle-out">Courses</a></li>
                            <li><a href="/listUser?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=EN</#if>" class="hvr-rectangle-out">Teachers</a></li>
                            <#--<li><a href="#news" class="hvr-rectangle-out">News</a></li>-->
                            <#--<li><a href="#gallery" class="hvr-rectangle-out">Services</a></li>-->
                            <#--<li><a href="#contact" class="hvr-rectangle-out">Contacts</a></li>-->
                        </ul>

                    </nav>
                </div>
            </nav>
        </div>
    </div>
</div>
<!-- //header -->
