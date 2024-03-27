window.onload = function () {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/user/login";
            // // 로그인 버튼 클릭 시
            // document.getElementById("loginSubmit").onclick = function () {
            //     var userId = document.getElementById("userId").value;
            //     var password = document.getElementById("password").value;
            //
            //     // AJAX 또는 Fetch API를 사용하여 서버로 로그인 요청을 보냅니다.
            //     // 요청이 올바른 URL과 데이터를 포함하는지 확인합니다.
            //     fetch("/user/login", {
            //         method: "POST",
            //         headers: {
            //             "Content-Type": "application/json"
            //         },
            //         body: JSON.stringify({userId: userId, password: password})
            //     }).then(response => {
            //         if (response.ok) {
            //             // 로그인 성공 시 리다이렉트 또는 다른 동작 수행
            //         } else {
            //             // 로그인 실패 시 에러 처리
            //         }
            //     }).catch(error => {
            //         console.error("Error:", error);
            //     });
            // };
        }
    }

    if (document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function () {
            location.href = "/user/logout";
        }
    }

    if (document.getElementById("regist")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function () {
            location.href = "/user/regist";
        }
    }

    if (document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");

        // $duplication.removeAttribute("disabled");

        $duplication.onclick = function () {
            let userId = document.getElementById("userId").value.trim();

            fetch("/user/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({userId: userId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }
    }

    if (document.getElementById("updateMember")) {
        const $update = document.getElementById("updateMember");
        $update.onclick = function () {
            location.href = "/user/update";
        }
    }

    if (document.getElementById("deleteMember")) {

        const $update = document.getElementById("deleteMember");
        $update.onclick = function () {
            if (confirm('정말 탈퇴하시겠습니까?'))
                location.href = "/user/delete";
        }
    }

    // if (document.getElementById("writeBoard")) {
    //     const $writeBoard = document.getElementById("writeBoard");
    //     $writeBoard.onclick = function () {
    //         location.href = "/board/regist";
    //     }
    // }
    //
    // if (document.getElementById("writeThumbnail")) {
    //     const $writeThumbnail = document.getElementById("writeThumbnail");
    //     $writeThumbnail.onclick = function () {
    //         location.href = "/thumbnail/regist";
    //     }
    // }
}