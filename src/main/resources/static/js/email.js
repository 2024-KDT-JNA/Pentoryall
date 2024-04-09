const $memail = document.querySelector("#memail");
const $memailconfirmTxt = document.querySelector("#memailconfirmTxt");

let isEmailDuplicate = false;

// 이메일 인증번호

// 이메일 인증번호
function sendEmailConfirmation() {
    if (!isEmailDuplicationChecked) {
        alert("이메일 중복 확인을 먼저 해주세요.");
        return; // 중복 확인을 먼저 요청하도록 함수 종료
    }

    $.ajax({
        type: "POST",
        url: "/user/mailConfirm",
        data: {
            "email": $memail.value
        },
        success: function (data) {
            alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
            console.log("data : " + data);
        }
    });
}

// 중복 확인 여부를 저장할 변수
let isEmailDuplicationChecked = false;

// 이메일 중복 확인 버튼 클릭 이벤트 처리
if (document.getElementById("emailDuplicationCheck")) {
    const $emailDuplicationCheck = document.getElementById("emailDuplicationCheck");
    $emailDuplicationCheck.onclick = function () {
        let email = document.getElementById("memail").value.trim();

        // 이메일 중복 확인 요청
        fetch("/user/emailDupCheck", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify({email: email})
        })
            .then(response => response.json())
            .then(result => {
                if (result === true) {
                    alert("중복된 이메일입니다.");
                    // 중복된 이메일이 있으면 인증 버튼을 비활성화합니다.
                    document.getElementById("checkEmail").disabled = true;
                } else if (result === false) {
                    alert("사용 가능한 이메일입니다.");
                    isEmailDuplicationChecked = true;
                    // 중복 확인 후에 이메일 인증 버튼을 활성화합니다.
                    document.getElementById("checkEmail").disabled = false;
                    $checkEmail.addEventListener("click", sendEmailConfirmation); // 클릭 이벤트 등록
                }
            })
            .catch(error => console.error('Error:', error));
    }
}

// 이메일 인증 버튼 클릭 이벤트 처리
const $checkEmail = document.querySelector("#checkEmail");
$checkEmail.addEventListener("click", sendEmailConfirmation);


const $checkEmailNum = document.querySelector("#checkEmailNum");
const $memailconfirm = document.querySelector("#memailconfirm");

/* 인증번호 확인 start */
function checkEmailNum() {
    fetch("/user/mailConfirmNum", {
        method: "POST",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: new URLSearchParams({memailconfirm: $memailconfirm.value})
    })
        .then(response => {
            if (response.ok) { // 성공 응답을 받았을 때
                return response.text(); // 응답 내용을 텍스트로 반환
            } else {
                throw new Error('Network response was not ok.'); // 오류 응답을 처리
            }
        })
        .then(data => {
            if (data === "") { // 서버로부터 받은 응답이 빈 문자열인 경우
                alert("인증번호 확인 완료!"); // 인증번호 확인 완료 메시지 표시
            } else {
                throw new Error('Invalid confirmation number.'); // 서버로부터 받은 응답이 유효하지 않은 경우
            }
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
            alert("인증번호가 잘못되었습니다."); // 오류 메시지 표시
        });
}

/* 인증번호 확인 end */

$checkEmailNum.addEventListener("click", checkEmailNum);

document.addEventListener("DOMContentLoaded", function () {
    // 이메일 입력란과 인증 버튼 요소 참조
    var $memail = document.getElementById("memail");
    var $checkEmail = document.getElementById("checkEmail");

    // 페이지 로드시 초기 버튼 상태 설정
    checkEmailValidity();

    // 이메일 입력란의 내용이 변경될 때마다 이벤트 리스너 추가
    $memail.addEventListener("input", function () {
        checkEmailValidity();
        // 입력된 이메일 주소
    });

    // 인증 버튼의 활성화 여부를 검사하는 함수
    function checkEmailValidity() {
        // 입력된 이메일 주소
        var emailValue = $memail.value.trim();

        // 이메일 주소가 유효한지 확인
        var isValidEmail = validateEmail(emailValue);

        // 유효한 이메일 주소라면 인증 버튼 활성화
        // 그렇지 않다면 비활성화
        if (isValidEmail) {
            $checkEmail.disabled = false;
        } else {
            $checkEmail.disabled = true;
        }
    }
});

// 이메일 주소 유효성 검사 함수
function validateEmail(email) {
    // 간단한 이메일 형식 검사 (더 엄격한 검사가 필요할 수 있음)
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// 회원가입 버튼 클릭 시 서버로 이메일 중복 여부 요청
document.querySelector(".btn[type='submit']").addEventListener("click", function (event) {
    // 이미 중복된 이메일이면 가입을 막음
    if (isEmailDuplicate) {
        event.preventDefault(); // 회원가입 이벤트 중지
        alert("이미 등록된 이메일입니다.");
    }
});