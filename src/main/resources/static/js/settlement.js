function saveUserSettlementForm() {
    event.preventDefault();

    const $saveForm = document.userSettlementForm;
    const bankCode = $saveForm.bankCode.value;
    const accountNumber = $saveForm.accountNumber.value;
    const accountHolder = $saveForm.accountHolder.value;

    if (bankCode <= 0) {
        alert("은행을 선택해주세요.");
        return;
    }

    // TODO 검증
    // fetch("/api/portone/token", { method: "POST" })
    //     .then(response => response.json())
    //     .then(data => {
    //         if (!data || !data.response || !data.response.token) {
    //             alert("오류가 발생하였습니다.");
    //             throw new Error("토큰을 가져올 수 없습니다.");
    //         }
    //         fetch('https://api.iamport.kr/vbanks/holder', {
    //             method: 'POST',
    //             headers: {
    //                 'Content-Type': 'application/json',
    //                 // 'Access-Control-Allow-Origin': 'http://localhost:8080',
    //                 'Cache-Control': 'none',
    //                 'Authorization': `Bearer ${ data.response.token }`
    //             }, body: JSON.stringify({
    //                 bank_code: bankCode,
    //                 bank_num: accountNumber
    //             })
    //         }).then(response => { console.log(response);})
    //           .then(data => {console.log(data);});
    //     });

    document.userSettlementForm.submit();
}

function restrictInputToNumeric(event) {
    const self = event;
    self.value = self.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
}
