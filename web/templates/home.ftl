<#--<!DOCTYPE HTML>-->
<#--<html>-->
<#--<head>-->
    <#--<title>Home</title>-->

    <#--<!--CAMBIO LINGUA&ndash;&gt;-->
    <#--<a href="Home?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>">Change Language EN</a>-->



    <#--<!--FORM PER IL LOGIN&ndash;&gt;-->
    <#--<form method="GET" action="Login">-->
        <#--<div class="col-md-6">-->

        <#--</div>-->
        <#--<div class="col-md-6 login-do">-->
            <#--<label class="hvr-shutter-in-horizontal login-sub">-->
                <#--<input type="submit" value="Login">-->
            <#--</label>-->
        <#--</div>-->

        <#--<div class="clearfix"> </div>-->
    <#--</form>-->


<#--</head>-->
    <#--<body>-->

        <#--<!--LISTA DOCENTI&ndash;&gt;-->
        <#--<a href="ListUser?<#if lng == 'IT'>lng=IT<#elseif lng == 'EN'>lng=EN<#else>lng=IT</#if>">Lista dei docenti</a>-->

    <#--</body>-->
<#--</html>-->
<!--
author: W3layouts
author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/slider-def.css">
    <title> il portale dell'università</title>
    <!-- custom-theme -->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);


    </script>
    <!-- //custom-theme -->

    <#include "css/bootstrap.css">
    <#include "css/style.css">
    <#include "css/lsb.css">
    <#include "css/font-awesome.css">
    <#include "css/flexslider.css">
    <!-- js -->
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <!-- //js -->

    <link href="//fonts.googleapis.com/css?family=Prompt:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&amp;subset=latin-ext,thai,vietnamese" rel="stylesheet">
</head>

<body>
<!-- banner -->
<div class="banner">
    <nav class="navbar navbar-default">
        <div class="navbar-header navbar-left">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <h1><a class="navbar-brand" href="Home"><span>E</span>difying</a></h1>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right" idCourse="bs-example-navbar-collapse-1">
            <nav class="link-effect-2" idCourse="link-effect-2">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html"><span data-hover="Home">Home</span></a></li>
                    <li><a href="courses.html"><span data-hover="Courses">Courses</span></a></li>
                    <li><a href="services.html"><span data-hover="Services">Services</span></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span data-hover="Short Codes">Short Codes</span> <b class="caret"></b></a>
                        <ul class="dropdown-menu agile_short_dropdown">
                            <li><a href="icons.html">Web Icons</a></li>
                            <li><a href="typography.html">Typography</a></li>
                        </ul>
                    </li>
                    <li><a href="mail.html"><span data-hover="Mail Us">Mail Us</span></a></li>
                    <!--CAMBIO LINGUA&ndash;&gt;-->
                    <li> <a href="Home?<#if lng == 'IT'>lng=EN<#elseif lng == 'EN'>lng=IT<#else>lng=EN</#if>">Change Language EN</a></li>
                </ul>
            </nav>
        </div>
        <div class="w3_agile_phone">



        </div>
    </nav>

</div>
<!-- //banner -->
<!-- banner-bottom -->
<div class="banner-bottom">
    <div class="container">
        <div class="col-md-6 w3ls_banner_bottom_left">
            <div class="w3ls_banner_bottom_left1">
                <p>01</p>
                <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
            </div>
            <div class="w3ls_banner_bottom_right1">
                <h2>the whole purpose of <span>education</span> is to turn mirrors into windows</h2>
                <p>Pellentesque convallis diam consequat magna vulputate malesuada.
                    Cras a ornare elit. Nulla viverra pharetra sem, eget pulvinar neque pharetra ac.</p>
                <div class="w3l_more">
                    <a href="#" class="button button--nina" data-text="Learn more" data-toggle="modal" data-target="#myModal">
                        <span>L</span><span>e</span><span>a</span><span>n</span> <span>m</span><span>o</span><span>r</span><span>e</span>
                    </a>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="col-md-6 w3ls_banner_bottom_right">
            <section class="slider">
                <div class="flexslider">
                    <ul class="slides">
                        <li>
                            <div class="agileits_w3layouts_banner_bottom_grid">
                                <img src="images/1.jpg" alt=" " class="img-responsive" />
                            </div>
                        </li>
                        <li>
                            <div class="agileits_w3layouts_banner_bottom_grid">
                                <img src="images/2.jpg" alt=" " class="img-responsive" />
                            </div>
                        </li>
                        <li>
                            <div class="agileits_w3layouts_banner_bottom_grid">
                                <img src="images/3.jpg" alt=" " class="img-responsive" />
                            </div>
                        </li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
<!-- //banner-bottom -->
<!-- bootstrap-pop-up -->
<div class="modal video-modal fade" idCourse="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                Edifying
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <section>
                <div class="modal-body">
                    <img src="images/10.jpg" alt=" " class="img-responsive" />
                    <p>Ut enim ad minima veniam, quis nostrum
                        exercitationem ullam corporis suscipit laboriosam,
                        nisi ut aliquid ex ea commodi consequatur? Quis autem
                        vel eum iure reprehenderit qui in ea voluptate velit
                        esse quam nihil molestiae consequatur, vel illum qui
                        dolorem eum fugiat quo voluptas nulla pariatur.
                        <i>" Quis autem vel eum iure reprehenderit qui in ea voluptate velit
                            esse quam nihil molestiae consequatur.</i></p>
                </div>
            </section>
        </div>
    </div>
</div>
<!-- //bootstrap-pop-up -->
<!-- register -->
<div class="register">
    <div class="container">
        <div class="col-md-6 w3layouts_register_right">
            <form action="#" method="post">
                <input name="Name" placeholder="First Name" type="text" required="">
                <input name="Name" placeholder="Last Name" type="text" required="">
                <input name="Email" placeholder="Email" type="email" required="">
                <input name="Subject" placeholder="Subject" type="text" required="">
                <input type="submit" value="Send">
            </form>
        </div>
        <div class="col-md-6 w3layouts_register_left">
            <h3><span>Register</span> now</h3>
            <p>Aliquam sit amet sapien felis. Proin vel dolor sed risus maximus gravida.
                Ut suscipit orci sem, eget lobortis sem dictum eu. Etiam congue ex sed volutpat fringilla.</p>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
<!-- //register -->
<!-- team -->
<div class="team w3_women_team" idCourse="team">
    <div class="container">
        <div class="w3_agile_team_grid">
            <div class="w3_agile_team_grid_left">
                <p>03</p>
                <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
            </div>
            <div class="w3_agile_team_grid_right">
                <h3>meet our <span>edifying</span> team</h3>
                <p>Aliquam sit amet sapien felis. Proin vel dolor sed risus maximus gravida.
                    Ut suscipit orci sem, eget lobortis sem dictum eu. Etiam congue ex sed volutpat fringilla.</p>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="agileits_w3layouts_team_grids">
            <div class="col-md-4 agileits_w3layouts_team_grid">
                <h3>Jane Smith</h3>
                <p>Manager</p>
                <ul class="agileinfo_social_icons w3_agileits_social_icons">
                    <li><a href="#" class="w3_agileits_facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="wthree_twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileinfo_google"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileits_pinterest"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
                </ul>
                <img src="images/5.jpg" alt=" " class="img-responsive" />
            </div>
            <div class="col-md-4 agileits_w3layouts_team_grid">
                <h3>Belinda Paul</h3>
                <p>Creative Head</p>
                <ul class="agileinfo_social_icons w3_agileits_social_icons">
                    <li><a href="#" class="w3_agileits_facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="wthree_twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileinfo_google"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileits_pinterest"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
                </ul>
                <img src="images/6.jpg" alt=" " class="img-responsive" />
            </div>
            <div class="col-md-4 agileits_w3layouts_team_grid">
                <h3>Laura Doe</h3>
                <p>Head of the department</p>
                <ul class="agileinfo_social_icons w3_agileits_social_icons">
                    <li><a href="#" class="w3_agileits_facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="wthree_twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileinfo_google"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileits_pinterest"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
                </ul>
                <img src="images/7.jpg" alt=" " class="img-responsive" />
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!-- //team -->
<!-- gallery-top -->
<div class="gallery-top">
    <div class="agileinfo_gallery_top">
        <h3><span>Education</span> is the most powerful weapon which you can use to change
            the world</h3>
    </div>
</div>
<!-- //gallery-top -->
<!-- gallery -->
<div class="gallery">
    <ul idCourse="flexiselDemo1">
        <li>
            <div class="wthree_gallery_grid">
                <a href="images/8.jpg" class="lsb-preview" data-lsb-group="header">
                    <div class="view second-effect">
                        <img src="images/8.jpg" alt="" class="img-responsive" />
                        <div class="mask">
                            <p>Edifying</p>
                        </div>
                    </div>
                </a>
            </div>
        </li>
        <li>
            <div class="wthree_gallery_grid">
                <a href="images/9.jpg" class="lsb-preview" data-lsb-group="header">
                    <div class="view second-effect">
                        <img src="images/9.jpg" alt="" class="img-responsive" />
                        <div class="mask">
                            <p>Edifying</p>
                        </div>
                    </div>
                </a>
            </div>
        </li>
        <li>
            <div class="wthree_gallery_grid">
                <a href="images/10.jpg" class="lsb-preview" data-lsb-group="header">
                    <div class="view second-effect">
                        <img src="images/10.jpg" alt="" class="img-responsive" />
                        <div class="mask">
                            <p>Edifying</p>
                        </div>
                    </div>
                </a>
            </div>
        </li>
        <li>
            <div class="wthree_gallery_grid">
                <a href="images/11.jpg" class="lsb-preview" data-lsb-group="header">
                    <div class="view second-effect">
                        <img src="images/11.jpg" alt="" class="img-responsive" />
                        <div class="mask">
                            <p>Edifying</p>
                        </div>
                    </div>
                </a>
            </div>
        </li>
        <li>
            <div class="wthree_gallery_grid">
                <a href="images/12.jpg" class="lsb-preview" data-lsb-group="header">
                    <div class="view second-effect">
                        <img src="images/12.jpg" alt="" class="img-responsive" />
                        <div class="mask">
                            <p>Edifying</p>
                        </div>
                    </div>
                </a>
            </div>
        </li>
    </ul>
</div>
<!-- //gallery -->
<!-- testimonials -->
<div class="testimonials">
    <div class="container">
        <div class="w3_agile_team_grid">
            <div class="w3_agile_team_grid_left">
                <p>05</p>
                <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
            </div>
            <div class="w3_agile_team_grid_right">
                <h3>What Our <span>Students</span> Says</h3>
                <p>Aliquam sit amet sapien felis. Proin vel dolor sed risus maximus gravida.
                    Ut suscipit orci sem, eget lobortis sem dictum eu. Etiam congue ex sed volutpat fringilla.</p>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="w3ls_testimonials_grids">
            <section class="center slider">
                <div class="agileits_testimonial_grid">
                    <div class="w3l_testimonial_grid">
                        <p>In eu auctor felis, idCourse eleifend dolor. Integer bibendum dictum erat,
                            non laoreet dolor.</p>
                        <h4>Rosy Crisp</h4>
                        <h5>Student</h5>
                        <div class="w3l_testimonial_grid_pos">
                            <img src="images/1.png" alt=" " class="img-responsive" />
                        </div>
                    </div>
                </div>
                <div class="agileits_testimonial_grid">
                    <div class="w3l_testimonial_grid">
                        <p>In eu auctor felis, idCourse eleifend dolor. Integer bibendum dictum erat,
                            non laoreet dolor.</p>
                        <h4>Laura Paul</h4>
                        <h5>Student</h5>
                        <div class="w3l_testimonial_grid_pos">
                            <img src="images/2.png" alt=" " class="img-responsive" />
                        </div>
                    </div>
                </div>
                <div class="agileits_testimonial_grid">
                    <div class="w3l_testimonial_grid">
                        <p>In eu auctor felis, idCourse eleifend dolor. Integer bibendum dictum erat,
                            non laoreet dolor.</p>
                        <h4>Michael Doe</h4>
                        <h5>Student</h5>
                        <div class="w3l_testimonial_grid_pos">
                            <img src="images/3.png" alt=" " class="img-responsive" />
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<!-- //testimonials -->
<!-- footer -->
<div class="footer">
    <div class="container">
        <h2>Subscribe to <span>Newsletter</span></h2>
        <form action="#" method="post">
            <input type="email" name="Email" placeholder="Enter Your Email..." required="">
            <input type="submit" value="Send">
        </form>
        <div class="agile_footer_copy">
            <div class="w3agile_footer_grids">
                <div class="col-md-4 w3agile_footer_grid">
                    <h3>About Us</h3>
                    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse.<span>Excepteur sint occaecat cupidatat
																	non proident, sunt in culpa qui officia deserunt mollit.</span></p>
                </div>
                <div class="col-md-4 w3agile_footer_grid">
                    <h3>Contact Info</h3>
                    <ul>
                        <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>1234k Avenue, 4th block, <span>New York City.</span></li>
                        <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a href="mailto:info@example.com">info@example.com</a></li>
                        <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 567</li>
                    </ul>
                </div>
                <div class="col-md-4 w3agile_footer_grid w3agile_footer_grid1">
                    <h3>Navigation</h3>
                    <ul>
                        <li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="courses.html">Courses</a></li>
                        <li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="services.html">Services</a></li>
                        <li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="icons.html">Web Icons</a></li>
                        <li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="mail.html">Mail Us</a></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
        <div class="w3_agileits_copy_right_social">
            <div class="col-md-6 agileits_w3layouts_copy_right">
                <p>&copy; 2017 Edifying. All rights reserved | Design by <a href="http://w3layouts.com/">W3layouts</a></p>
            </div>
            <div class="col-md-6 w3_agile_copy_right">
                <ul class="agileinfo_social_icons">
                    <li><a href="#" class="w3_agileits_facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="wthree_twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileinfo_google"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                    <li><a href="#" class="agileits_pinterest"><i class="fa fa-pinterest-p" aria-hidden="true"></i></a></li>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!-- //footer -->
<!-- carousal -->
<script src="js/slick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(document).on('ready', function() {
        $(".center").slick({
            dots: true,
            infinite: true,
            centerMode: true,
            slidesToShow: 2,
            slidesToScroll: 2,
            responsive: [
                {
                    breakpoint: 768,
                    settings: {
                        arrows: true,
                        centerMode: false,
                        slidesToShow: 2
                    }
                },
                {
                    breakpoint: 480,
                    settings: {
                        arrows: true,
                        centerMode: false,
                        centerPadding: '40px',
                        slidesToShow: 1
                    }
                }
            ]
        });
    });
</script>
<!-- //carousal -->
<!-- flexisel -->
<script type="text/javascript">
    $(window).load(function() {
        $("#flexiselDemo1").flexisel({
            visibleItems: 4,
            animationSpeed: 1000,
            autoPlay: true,
            autoPlaySpeed: 3000,
            pauseOnHover: true,
            enableResponsiveBreakpoints: true,
            responsiveBreakpoints: {
                portrait: {
                    changePoint:480,
                    visibleItems: 1
                },
                landscape: {
                    changePoint:640,
                    visibleItems:2
                },
                tablet: {
                    changePoint:768,
                    visibleItems: 2
                }
            }
        });

    });
</script>
<script type="text/javascript" src="js/jquery.flexisel.js"></script>
<!-- //flexisel -->
<!-- gallery-pop-up -->
<script src="js/lsb.min.js"></script>
<script>
    $(window).load(function() {
        $.fn.lightspeedBox();
    });
</script>
<!-- //gallery-pop-up -->
<!-- flexSlider -->
<script defer src="js/jquery.flexslider.js"></script>
<script type="text/javascript">
    $(window).load(function(){
        $('.flexslider').flexslider({
            animation: "slide",
            start: function(slider){
                $('body').removeClass('loading');
            }
        });
    });
</script>
<!-- //flexSlider -->
<!-- banner-type-text -->
<script src="js/typed.js" type="text/javascript"></script>
<script>
    $(function(){

        $("#typed").typed({
            // strings: ["Typed.js is a <strong>jQuery</strong> plugin.", "It <em>types</em> out sentences.", "And then deletes them.", "Try it out!"],
            stringsElement: $('#typed-strings'),
            typeSpeed: 30,
            backDelay: 500,
            loop: false,
            contentType: 'html', // or text
            // defaults to false for infinite loop
            loopCount: false,
            callback: function(){ foo(); },
            resetCallback: function() { newTyped(); }
        });

        $(".reset").click(function(){
            $("#typed").typed('reset');
        });

    });

    function newTyped(){ /* A new typed object */ }

    function foo(){ console.log("Callback"); }
</script>
<!-- //banner-type-text -->
<!-- start-smooth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
<!-- start-smooth-scrolling -->
<!-- for bootstrap working -->
<script src="js/bootstrap.js"></script>
<!-- //for bootstrap working -->
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function() {
        /*
            var defaults = {
            containerID: 'toTop', // fading element idCourse
            containerHoverID: 'toTopHover', // fading element hover idCourse
            scrollSpeed: 1200,
            easingType: 'linear'
            };
            */

        $().UItoTop({ easingType: 'easeOutQuart' });

    });
</script>
<!-- //here ends scrolling icon -->
</body>
</html>