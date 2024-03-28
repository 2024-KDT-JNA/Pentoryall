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

if ($sideMenuBtn !=null)
    $sideMenuBtn.addEventListener('click', layoutEvents.sideMenuClickHandler);
$overlay.addEventListener('click', layoutEvents.overlayClickHandler);
