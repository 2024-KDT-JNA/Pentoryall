function onClickRequestPay() {
    if (!isAllChecked('agree')) {
        alert("결제 약관에 동의해주세요.");
        return;
    }

    const payProvider = document.querySelector("input[name='pg']:checked").value;
    const payAmount = document.querySelector("input[name='amount']:checked").value;

    IMP.init("imp57751104");
    IMP.request_pay({
        pg: payProvider,
        pay_method: "card",
        merchant_uid: paymentId,
        name: `[포인트] ${ payAmount }P `,
        amount: payAmount,
        buyer_email: UserInfo.email,
        buyer_name: UserInfo.name,
    }, function (response) {
        if (response.success) {
            fetch("/point/order/payment", {
                method: "POST",
                headers: { 'Content-Type': 'application/json; charset=UTF-8' },
                body: JSON.stringify({
                    userCode: UserInfo.code,
                    impUid: response.imp_uid,
                    amount: response.paid_amount,
                    point: response.paid_amount,
                })
            }).then(response => response.json())
              .then((data) => {
                  if (data.success) {
                      window.location.href = "/point/order/complete?orderCode=" + data.response;
                  } else {
                      alert('결제 실패');
                      window.location.href = "/point/order";
                  }
              });
        } else {
            alert("결제 중 오류가 발생하였습니다.");
        }
    });
}

function onClickImpCertification() {
    IMP.init("imp57751104");
    IMP.certification({ // param
        pg: 'inicis_unified',//본인인증 설정이 2개이상 되어 있는 경우 필수
        merchant_uid: "ORD20180131-0000011", // 주문 번호
        popup: false // PC환경에서는 popup 파라미터가 무시되고 항상 true 로 적용됨
    }, function (rsp) { // callback
        if (rsp.success) {
            console.log("imp_certification success -----------------------------");
            console.log(rsp);
            alert("success!");
        } else {
            alert("failed!");

        }
    });
}

