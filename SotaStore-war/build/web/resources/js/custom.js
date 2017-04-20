/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $(function() {
        $('.gallery a').lightBox();
    });

    $(function() {
        $('#camera_wrap_1').camera({
            pagination: false,
            height: 'auto'
        });
    });

    /* go top */
    $('#gotop').click(function() {
        $('html, body').animate({scrollTop: 0}, "slow");
    });
    $(window).scroll(function() {
        if ($(window).scrollTop() != 0) {
            $('#gotop').fadeIn();
        } else {
            $('#gotop').fadeOut();
        }
    });
    /*
    var currentDate = new Date();

// Set some date in the past. In this case, it's always been since Jan 1
                                                var pastDate = new Date(
                                                                            2017, 
                                                                            currentDate.getMonth(), 
                                                                            currentDate.getDate(), 21, 0, 0);

// Calculate the difference in seconds between the future and current date
                                                var diff = Math.round(pastDate.getTime() / 1000 - currentDate.getTime() / 1000);
                                                debugger;

// Instantiate a coutdown FlipClock new FlipClock($('.your-clock'), {clockFace: 'DailyCounter')
                                                clock = new FlipClock(
                                                        $('.your-clock'),
                                                        diff,
                                                        {       
                                                            countdown: true
                                                        });
                                                       */
    /* end go top */

    /* class active menu */
    
    /* end class active menu */
});


