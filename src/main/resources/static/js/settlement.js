function restrictInputToNumeric(event) {
    const $self = event.currentTarget;
    $self.value = $self.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
}

function calculateActualAmount(event) {
    const $self = event.currentTarget;
    const $actualAmount = document.getElementById('actualAmount');

    $actualAmount.value = ($self.value / 10) * 9;
}
function validateSettlementForm(event) {
    event.preventDefault();

    const $settlementForm = document.settlementForm;
    const requestRevenue = $settlementForm.requestAmount.value;
    const hasRevenue = $settlementForm.revenue.value;

    if (requestRevenue < 1000) {
        alert("최소 신청 금액은 1000원 입니다.");
        return;
    }
    if (requestRevenue > hasRevenue) {
        alert("보유한 수익보다 큰 금액을 신청할 수 없습니다.");
        return;
    }

    $settlementForm.submit();
}

async function saveUserSettlementForm(event) {
    event.preventDefault();

    const $saveForm = document.userSettlementForm;
    const bankCode = $saveForm.bankCode.value;
    const accountNumber = $saveForm.accountNumber.value;
    const accountHolder = $saveForm.accountHolder.value;

    if (bankCode <= 0) {
        alert("은행을 선택해주세요.");
        return;
    }

    fetch('/api/portone/vbank', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        }, body: JSON.stringify({ bankCode, accountNumber })
    }).then(response => response.json())
      .then(data => {
          if (data.success && data.response['bank_holder'] === accountHolder) {
              $saveForm.submit();
          } else {
              alert("올바른 계좌 정보를 입력해주세요.");
          }
      });
}
