function onClickValidatePost(postCode) {
    const $post = event.target;

    fetch("/api/post/validate?postCode=" + postCode, {
        method: "GET",
        headers: { 'Content-Type': 'application/json; charset=UTF-8' },
    }).then(response => response.json())
      .then(data => {
          if (data.success) {
              location.href = '/post/information?code=' + postCode;
          }
          if (data.message === "NO_PURCHASE") {
              const hasPoint = data.response.point;
              const postPrice = data.response.post.price;

              if (confirm(`해당 포스트는 유료 컨텐츠 입니다.\n${ postPrice }P를 소모하여 구매시겠습니까?\n\n보유 포인트: ${ hasPoint }P`)) {
                  if (hasPoint >= postPrice) {
                      return fetch("/api/transaction/post", {
                          method: "POST",
                          headers: { 'Content-Type': 'application/json; charset=UTF-8' },
                          body: JSON.stringify(postCode)
                      });
                  } else {
                      if (confirm("포인트가 부족합니다. 충전 페이지로 이동하시겠습니까?")) {
                          location.href = '/point/order';
                      }
                  }
              }
          }
      })
      .then(response => response.json())
      .then(data => {
          if (data.success) {
              alert("구매가 완료되었습니다.");
              location.href = '/post/information?code=' + postCode;
          } else {
              alert(data.message);
          }
      })
      .catch(error => console.error(error));
}
