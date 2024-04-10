/* main */
const swiper = new Swiper('.main-series-swiper', {
    slidesPerView: '5',
    spaceBetween: 25,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
});

window.addEventListener("DOMContentLoaded", function () {
    likeCounts1();
    likeCounts();
    fetchGenreSeries(1);

    document.querySelectorAll(".genre-list").forEach(btn => {
        btn.addEventListener("click", (e) => {
            document.querySelectorAll(".genre-list").forEach(innerBtn => {
                innerBtn.classList.remove('active');
            });

            e.currentTarget.classList.add('active');

            fetchGenreSeries(e.target.value);
        });
    });
});

function fetchGenreSeries(genreCode) {
    fetch("/genre/series?code=" + genreCode)
        .then(response => response.json())
        .then(data => {
            console.log("데이터", data);
            makeSeries(data);
        }).catch(error => console.log("데이터를 가져오는 중 오류 발생", error));
}

function makeSeries(seriesList) {
    // 시리즈 목록을 담을 swiper-wrapper를 찾습니다.
    const swiperWrapper = document.querySelector(".selected-series");

    // swiper-wrapper 내용을 초기화합니다.
    swiperWrapper.innerHTML = "";

    // 시리즈 목록을 동적으로 생성하여 swiper-wrapper에 추가합니다.
    seriesList.forEach(series => {
        const swiperSlide = document.createElement("div");
        swiperSlide.className = "swiper-slide";
        swiperSlide.innerHTML = `
            <a href="/series/page?code=${ series.code }">
                <div class="thumbnail-image">
                    <img class="best-series-img" src="${ series.thumbnailImage }"
                         onerror="this.src='/images/temp/thumbnail.png'" alt="시리즈 썸네일" />
                </div>
                <p class="description">${ series.title }</p>
            </a>
        `;
        swiperWrapper.appendChild(swiperSlide);
    });
}

/*현재 좋아요 갯수*/
async function likeCounts() {
    const input = document.querySelectorAll(".input-post");
    input.forEach(el => {
        const code = el.value;
        fetch('/post/likeCount', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                code
            }),
        })
            .then(result => result.json())
            .then(data => {
                console.log("data:" + data);
                console.log("성공임니다");
                makeLikeArea(data, code);
            })
            .catch(error => console.log(error));
    });
}

function makeLikeArea(likeCount, code) {
    // 좋아요 갯수를 업데이트할 요소 선택
    const likeElement = document.querySelectorAll(".input-post");
    likeElement.forEach(el => {
        if (el.value == code) {
            const like = el.closest(".post-item").querySelector(".like-input");
            // 요소의 내용 업데이트
            like.innerHTML = ` ${ likeCount }`;
        }
    });
}

/*현재 좋아요 갯수*/
async function likeCounts1() {
    const input = document.querySelectorAll(".input-post");
    input.forEach(el => {
        const code = el.value;
        fetch('/post/likeCount', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                code
            }),
        })
            .then(result => result.json())
            .then(data => {
                console.log("data:" + data);
                console.log("성공임니다");
                makeLikeArea1(data, code);
            })
            .catch(error => console.log(error));
    });
}

function makeLikeArea1(likeCount, code) {
    // 좋아요 갯수를 업데이트할 요소 선택
    const likeElement = document.querySelectorAll(".input-post");
    likeElement.forEach(el => {
        if (el.value == code) {
            const like = el.closest(".post-item").querySelector(".like-input");
            // 요소의 내용 업데이트
            like.innerHTML = ` ${ likeCount }`;
        }
    });
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

