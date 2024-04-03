var isLiked = false;

let postCode = 6;
let userCode = 4;
$('.likeBtn').click(function () {
    if (isLiked) {
        // 이미 좋아요를 누른 상태라면, 좋아요 취소 요청을 보냅니다.
        $.ajax({
            type: 'post',
            url: '/user/likeDown', // 좋아요 취소 요청을 보내는 URL
            contentType: 'application/json',
            data: JSON.stringify({
                postCode,
                userCode
            }),
            success: function (data) {
                // 성공적으로 처리되면 좋아요를 취소한 것으로 간주하고 버튼의 텍스트를 변경합니다.
                isLiked = false;
                $('.likeBtn').html('<i class="far fa-heart"></i>');
                // $('.likeBtn').text("좋아요");
                console.log("좋아요 취소 성공");
                // alert('취소 성공');
            }
        });
    } else {
        // 좋아요를 누르지 않은 상태라면, 좋아요 요청을 보냅니다.
        $.ajax({
            type: 'post',
            url: '/user/likeUp', // 좋아요 요청을 보내는 URL
            contentType: 'application/json',
            data: JSON.stringify({
                postCode,
                userCode
            }),
            success: function (data) {
                // 성공적으로 처리되면 좋아요를 한 것으로 간주하고 버튼의 텍스트를 변경합니다.
                isLiked = true;
                $('.likeBtn').html('<i class="fas fa-heart"></i>');
                // $('.likeBtn').text("좋아요 취소");
                console.log("좋아요 성공");
                // alert('성공염');
            }
        });
    }
});