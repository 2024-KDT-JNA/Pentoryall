const $orderBtn = document.getElementById('orderBtn');
$orderBtn.onclick = function () {
    const pg = document.querySelector("input[name=pg]:checked").dataset.pg;
    const amount = document.querySelector("input[name=point]:checked").dataset.point;
    const orderNo = "ORD-" + Date.now() + "-" + Math.floor((Math.random() * 10000));

    IMP.init("imp57751104");
    IMP.request_pay({
        pg: pg,
        pay_method: "card",
        merchant_uid: orderNo,
        name: `[포인트] ${amount}P `,
        amount: amount,
        buyer_email: buyerEmail,
        buyer_name: buyerName,
        m_redirect_url: "/order/payment"
    }, function (response) {
        if (response.success) {
            fetch("/order/payment", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                },
                body: JSON.stringify({
                    impUid: response.imp_uid,
                    amount: response.paid_amount,
                    point: response.paid_amount,
                })
            })
                .then((response) => response.json())
                .then((result) => {
                    console.log("response : " + response);
                    console.log("result : " + result);
                    if (result) {
                        window.location.href = "/order/complete?orderCode=" + result.orderCode;
                    } else {
                        window.location.href = `/order/complete`;
                    }
                });
        }
    });
};
// function imp_certification() {
//     IMP.init("imp57751104");
//     IMP.certification({ // param
//         pg: 'inicis_unified',//본인인증 설정이 2개이상 되어 있는 경우 필수
//         merchant_uid: "ORD20180131-0000011", // 주문 번호
//         m_redirect_url: "{리디렉션 될 URL}", // 모바일환경에서 popup:false(기본값) 인 경우 필수, 예: https://www.myservice.com/payments/complete/mobile
//         popup: false // PC환경에서는 popup 파라미터가 무시되고 항상 true 로 적용됨
//     }, function (rsp) { // callback
//         if (rsp.success) {
//             console.log("imp_certification success -----------------------------");
//             console.log(rsp);
//             alert("success!");
//         } else {
//             alert("failed!");
//
//         }
//     });
// }


