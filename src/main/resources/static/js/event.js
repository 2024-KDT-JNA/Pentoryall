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

    let isDuplicationChecked = false;

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
                /* Response객체로 가지고 온 result 데이터 .json()으로 파싱처리 필요 */
                .then(result => result.json())
                .then(result => {
                    if (result === true) {
                        alert("중복된 아이디 입니다.");
                    } else if (result === false) {
                        alert(
                            "사용가능한 아이디 입니다."
                        )
                        isDuplicationChecked = true;
                    }
                })
                // .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));
        }
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

        function showRegistrationFailure() {
            // 회원가입 실패 메시지를 표시합니다.
            alert('회원가입에 실패했습니다. 다시 시도해주세요.');
        }

        if (document.getElementById("updateUser")) {
            const $update = document.getElementById("updateUser");
            $update.onclick = function () {
                location.href = "/user/update";
            }
        }
        //
        // if (document.getElementById("deleteUser")) {
        //
        //     const $update = document.getElementById("deleteUser");
        //     $update.onclick = function () {
        //         if (confirm('정말 탈퇴하시겠습니까?'))
        //             location.href = "/views/user/delete";
        //     }
        // }

        // if (document.getElementById("deleteUser")) {
        //     const $deleteUser = document.getElementById("deleteUser");
        //     $deleteUser.onclick = function () {
        //         if (confirm('정말 탈퇴하시겠습니까?')) {
        //             fetch("/user/delete", {
        //                 method: "POST"
        //             })
        //                 .then(response => {
        //                     if (response.ok) {
        //                         // 성공적으로 탈퇴가 처리된 경우
        //                         alert("탈퇴가 처리되었습니다.");
        //                         location.href = "/"; // 메인 페이지로 리다이렉트
        //                     } else {
        //                         // 탈퇴 처리에 실패한 경우
        //                         alert("탈퇴 처리에 실패했습니다.");
        //                     }
        //                 })
        //                 .catch(error => {
        //                     // 네트워크 오류 등의 이유로 요청이 실패한 경우
        //                     console.error("Error:", error);
        //                     alert("탈퇴 처리에 실패했습니다.");
        //                 });
        //         }
        //     };
        // }

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