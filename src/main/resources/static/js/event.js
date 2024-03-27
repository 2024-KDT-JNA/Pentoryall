window.onload = function () {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/views/user/login";
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
            location.href = "/views/user/logout";
        }
    }

    if (document.getElementById("regist")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function () {
            location.href = "/views/user/regist";
        }
    }

    if (document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");

        // $duplication.removeAttribute("disabled");

        $duplication.onclick = function () {
            let userId = document.getElementById("userId").value.trim();
            let nickname = document.getElementById("nickname").value.trim();

            fetch("/views/user/idDupCheck", {
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
    // if (document.querySelector(".regist_form")) {
    //     const $registForm = document.querySelector(".regist_form");
    //     $registForm.onsubmit = function (event) {
    //         // 여기에 회원가입 요청을 처리하는 코드가 있어야 합니다.
    //         // 성공적으로 회원가입이 완료된 경우에는 아래의 함수를 호출하여 완료 메시지를 표시하고 페이지를 이동할 수 있습니다.
    //         showRegistrationSuccess();
    //         // const success = handleRegistration();
    //         // if (success) {
    //         //     showRegistrationSuccess();
    //         // } else {
    //         //     showRegistrationFailure();
    //         // }
    //     }
    // }

    function showRegistrationSuccess() {
        // 회원가입 완료 메시지를 표시합니다.
        alert('회원가입이 완료되었습니다.');
        // 페이지 이동을 원하는 경우 아래의 코드를 사용하여 페이지를 이동할 수 있습니다.
        location.href = "/";
    }

    // 회원가입 폼 제출 이벤트 처리
    if (document.querySelector(".regist_form")) {
        const $registForm = document.querySelector(".regist_form");
        $registForm.onsubmit = function (event) {
            if (!isDuplicationChecked) { // 중복 확인이 되지 않은 경우
                alert("아이디 중복 확인을 해주세요."); // 알림창 표시
                event.preventDefault(); // 폼 제출 방지
            }
        }

        // function showRegistrationFailure() {
        //     // 회원가입 실패 메시지를 표시합니다.
        //     alert('회원가입에 실패했습니다. 다시 시도해주세요.');
        // }

        if (document.getElementById("updateMember")) {
            const $update = document.getElementById("updateMember");
            $update.onclick = function () {
                location.href = "/views/user/update";
            }
        }

        if (document.getElementById("deleteMember")) {

            const $update = document.getElementById("deleteMember");
            $update.onclick = function () {
                if (confirm('정말 탈퇴하시겠습니까?'))
                    location.href = "/views/user/delete";
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
}