<!-- Footer -->
<div class="footer w3ls">
    <div class="container">
    <#if user??>

    <#else>
        <div class="newsletter-agile">
            <form action="#" method="post">
                <p>Send us an E-mail, you won't miss a news!</p>
                <input type="email" name="email" size="30" required="" placeholder="E-mail...">
                <input type="submit" value="Sign-in">
            </form>
        </div>
    </#if>

        <div class="footer-main">
            <div class="footer-top">
                <div class="col-md-4 ftr-grid fg1">
                    <h3><a href="/home"><span>U</span>niversit&agrave</a></h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adip magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                </div>
                <div class="col-md-4 ftr-grid fg2 mid-gd">
                    <h3>How to reach us</h3>
                    <div class="ftr-address">
                        <div class="local">
                            <i class="fa fa-map-marker" aria-hidden="true"></i>
                        </div>
                        <div class="ftr-text">
                            <p>Address</p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="ftr-address">
                        <div class="local">
                            <i class="fa fa-phone" aria-hidden="true"></i>
                        </div>
                        <div class="ftr-text">
                            <p>+00 0123456789</p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="ftr-address">
                        <div class="local">
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                        </div>
                        <div class="ftr-text">
                            <p><a href="mailto:info@example.com">email@example.com</a></p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
                <div class="col-md-4 ftr-grid fg2">
                    <h3>Follow us</h3>
                    <div class="right-w3l">
                        <ul class="top-links">
                            <li><a href="#"><i class="fa fa-facebook facebook-color" aria-hidden="true"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter twitter-color" aria-hidden="true"></i></a></li>
                            <li><a href="#"><i class="fa fa-google-plus google-color" aria-hidden="true"></i></a></li>
                        </ul>
                    </div>
                    <div class="right-w3-2">
                        <ul class="text-w3">
                            <li><a href="#">Facebook</a></li>
                            <li><a href="#">Twitter</a></li>
                            <li><a href="#">Google</a></li>
                        </ul>
                    </div>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="copyrights">
                <p>&copy; 2017 universit&agrave. All Rights Reserved | Design by noialtri </p>
            </div>
        </div>
    </div>

</div>
<!-- Footer -->

<!--//pop-up-box -->
<script src="/templates/js/jquery.magnific-popup.js" type="text/javascript"></script>


<script>
    $(document).ready(function() {
        $('.popup-with-zoom-anim').magnificPopup({
            type: 'inline',
            fixedContentPos: false,
            fixedBgPos: true,
            overflowY: 'auto',
            closeBtnInside: true,
            preloader: false,
            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-zoom-in'
        });

    });
</script>
<!-- //pop-up-box -->

<!-- //js -->
<script type="text/javascript" src="/templates/js/move-top.js"></script>
<script type="text/javascript" src="/templates/js/easing.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
<!-- start-smoth-scrolling -->

<script src="/templates/js/jzBox.js"></script>

<!-- smooth scrolling -->
<script type="text/javascript">
    $(document).ready(function() {
        /*
            var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
            };
        */
        $().UItoTop({ easingType: 'easeOutQuart' });
    });
</script>

<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!-- //smooth scrolling -->
<script type="text/javascript" src="/templates/js/bootstrap.js"></script>