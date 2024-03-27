// 1. 아이디 입력창 정보 가져오기
const elInputUsername = document.querySelector('#userId'); // input#username
// 2. 성공 메시지 정보 가져오기
const elSuccessMessage = document.querySelector('.success-message'); // div.success-message.hide
// 3. 실패 메시지 정보 가져오기 (글자수 제한 4~12글자)
const elFailureMessage = document.querySelector('.failure-message'); // div.failure-message.hide
// 4. 실패 메시지2 정보 가져오기 (영어 또는 숫자)
const elFailureMessageTwo = document.querySelector('.failure-message2'); // div.failure-message2.hide
// 5. 실패 메시지3 정보 가져오기 (이미 존재하는 아이디)
const elFailureMessageThree = document.querySelector('.failure-message3'); // div.failure-message3.hide
// 중복체크 버튼
const duplicationCheckBtn = document.querySelector('#duplicationCheck');


/* 아이디 글자 수 제한 */
function idLength(value) {
    return value.length >= 5 && value.length <= 11
}

/* 아이디 영어 숫자만 가능 */
function onlyNumberAndEnglish(str) {
    return /^[A-Za-z0-9][A-Za-z0-9]*$/.test(str);
}

// // 아이디 중복값 체크 여부를 저장하는 변수
// let isIdChecked = false;
//
// // 중복 체크 버튼 클릭 시 실행되는 함수
// duplicationCheckBtn.onclick = function () {
//     // 중복 체크를 수행한 경우
//     isIdChecked = true;
//
//     // 여기에 중복 체크 로직을 구현합니다.
//     // 중복 체크 결과에 따라 isIdChecked 값을 업데이트합니다.
//
//     // 예시로 중복 체크가 성공했다고 가정하고, 중복 체크 완료 메시지를 표시합니다.
//     alert('아이디 중복 체크가 완료되었습니다.');
// };
//
// // 회원가입 버튼 클릭 시 실행되는 함수
// document.querySelector('.regist_form').onsubmit = function (event) {
//     // 중복 체크를 하지 않은 경우
//     if (!isIdChecked) {
//         // 회원가입을 막고, 사용자에게 메시지를 표시하여 중복 체크를 요청합니다.
//         alert('아이디 중복 체크를 해주세요.');
//         event.preventDefault(); // 회원가입을 막습니다.
//     } else {
//         // 중복 체크를 한 경우에는 서버에서 아이디 중복을 다시 확인하도록 요청합니다.
//         // 여기에 서버에 중복 체크 요청하는 로직을 구현합니다.
//
//         // 서버에서 아이디 중복을 확인하는 비동기 요청을 수행합니다.
//         // 성공 시 회원가입을 진행합니다.
//
//         // 회원가입 양식을 전송합니다.
//         document.querySelector('.regist_form').submit();
//     }
// };


elInputUsername.onkeyup = function () {
    // 값을 입력한 경우
    if (elInputUsername.value.length !== 0) {
        // 영어 또는 숫자 외의 값을 입력했을 경우
        if (onlyNumberAndEnglish(elInputUsername.value) === false) {
            elSuccessMessage.classList.add('hide');
            elFailureMessage.classList.add('hide');
            elFailureMessageTwo.classList.remove('hide'); // 영어 또는 숫자만 가능합니다
            duplicationCheckBtn.disabled = true;
            
        }
        // 글자 수가 4~12글자가 아닐 경우
        else if (idLength(elInputUsername.value) === false) {
            elSuccessMessage.classList.add('hide'); // 성공 메시지가 가려져야 함
            elFailureMessage.classList.remove('hide'); // 아이디는 4~12글자이어야 합니다
            elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
            duplicationCheckBtn.disabled = true;
        }
        // 조건을 모두 만족할 경우
        else if (idLength(elInputUsername.value) || onlyNumberAndEnglish(elInputUsername.value)) {
            // elSuccessMessage.classList.remove('hide'); // 사용할 수 있는 아이디입니다
            elFailureMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
            elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
            duplicationCheckBtn.disabled = false;
        }
    }
        // 값을 입력하지 않은 경우 (지웠을 때)
    // 모든 메시지를 가린다.
    else {
        elSuccessMessage.classList.add('hide');
        elFailureMessage.classList.add('hide');
        elFailureMessageTwo.classList.add('hide');
        duplicationCheckBtn.disabled = true;
    }
}