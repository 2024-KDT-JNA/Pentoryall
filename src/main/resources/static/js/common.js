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
function onClickAddPost() {
    location.href = "/post/writer";
}
function onClickAddSeries() {
    location.href = "/series/add";

}
