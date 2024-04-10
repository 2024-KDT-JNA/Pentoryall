document.addEventListener('DOMContentLoaded', function () {
    if (document.getElementById('aside')) {
        /* layout */
        const $sideMenuBtn = document.querySelector('.profile-btn');
        const $overlay = document.querySelector('.overlay');

        const layoutEvents = {
            sideMenuClickHandler: (e) => {
                const $sideMenu = document.querySelector('#aside');
                $sideMenu.classList.add('active');
            },
            overlayClickHandler: (e) => {
                if (e.target == $overlay) e.currentTarget.classList.remove('active');
            },
        };

        if ($sideMenuBtn) {
            $sideMenuBtn.addEventListener('click', layoutEvents.sideMenuClickHandler);
        }
        if ($overlay) {
            $overlay.addEventListener('click', layoutEvents.overlayClickHandler);
        }
    }
});

function isAllChecked(targetName) {
    const $checkInputs = document.querySelectorAll(`input[name='${ targetName }']`);
    let checked = 0;
    $checkInputs.forEach((checkInput) => checkInput.checked && checked++);

    return $checkInputs.length === checked;
}

/* ============== COMMON ================= */

function onClickMain() {
    location.href = "/";
}
function onClickAddSeries() {
    location.href = "/series/add";

}


function onClickAddPost() {
    location.href = "/post/writer";
}
function onClickRemovePost(postCode) {
    location.href = "/post/delete?code=" + postCode;
}
function onClickModifyPost(postCode) {
    location.href = "/post/updatewriter?code=" + postCode;
}


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
