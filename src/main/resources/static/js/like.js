var likeval = ${like};

let board_no = ${Detail.freeboard_no};
let user_no = '${login.user_code}';
if(likeval > 0){
    console.log(likeval + "좋아요 누름");
    $('.LikeBtn').html("좋아요 취소");
    $('.LikeBtn').click(function() {
        $.ajax({
            type :'post',
            url : '<c:url value ="/FreeBoard/likeDown"/>',
            contentType: 'application/json',
            data : JSON.stringify(
                {
                    "board_no" : board_no,
                    "user_no" : user_no
                }
            ),
            success : function(data) {
                alert('취소 성공');
            }
        })// 아작스 끝
    })

}else{
    console.log(likeval + "좋아요 안누름")
    console.log(user_no);
    $('.LikeBtn').click(function() {
        $.ajax({
            type :'post',
            url : '<c:url value ="/FreeBoard/likeUp"/>',
            contentType: 'application/json',
            data : JSON.stringify(
                {
                    "board_no" : board_no,
                    "user_no" : user_no
                }
            ),
            success : function(data) {
                alert('성공염');
            }
        })// 아작스 끝
    })