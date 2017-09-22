<!--//pop-up-box -->
<script type="text/javascript" src="templates/js/jquery-2.1.4.min.js"></script>
<!--pop-up-box -->
<script src="templates/js/jquery.magnific-popup.js" type="text/javascript"></script>
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
<script type="text/javascript" src="templates/js/move-top.js"></script>
<script type="text/javascript" src="templates/js/easing.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
<!-- start-smoth-scrolling -->

<script src="templates/js/jzBox.js"></script>

<!-- Countdown-Timer-JavaScript -->
<script src="templates/js/simplyCountdown.js"></script>
<script>
    var d = new Date(new Date().getTime() + 948 * 120 * 120 * 2000);

    // default example
    simplyCountdown('.simply-countdown-one', {
        year: d.getFullYear(),
        month: d.getMonth() + 1,
        day: d.getDate()
    });

    // inline example
    simplyCountdown('.simply-countdown-inline', {
        year: d.getFullYear(),
        month: d.getMonth() + 1,
        day: d.getDate(),
        inline: true
    });

    //jQuery example
    $('#simply-countdown-losange').simplyCountdown({
        year: d.getFullYear(),
        month: d.getMonth() + 1,
        day: d.getDate(),
        enableUtc: false
    });
</script>
<!-- //Countdown-Timer-JavaScript -->

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
<script type="text/javascript" src="templates/js/bootstrap.js"></script>