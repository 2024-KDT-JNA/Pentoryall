$(document).ready(function () {
    var isLiked = false;

    var postCode = 1;
    var userCode = 1;

    $('.likeBtn').click(function () {
        var $btn = $(this);
        if (isLiked) {
            $.ajax({
                type: 'post',
                url: '/user/likeDown',
                contentType: 'application/json',
                data: JSON.stringify({
                    postCode,
                    userCode
                }),
                success: function (data) {
                    isLiked = false;
                    $btn.html('<i class="far fa-heart"></i>');
                    console.log("좋아요 취소 성공");
                }
            });
        } else {
            $.ajax({
                type: 'post',
                url: '/user/likeUp',
                contentType: 'application/json',
                data: JSON.stringify({
                    postCode,
                    userCode
                }),
                success: function (data) {
                    isLiked = true;
                    $btn.html('<i class="fas fa-heart"></i>');
                    console.log("좋아요 성공");
                }
            });
        }
    });
});