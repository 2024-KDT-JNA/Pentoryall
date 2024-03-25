// 체크박스 전체 선택
$(".checkBoxWrap").on("click", "#agree_all", function () {
    $(this).parents(".checkBoxWrap").find('input').prop("checked", $(this).is(":checked"));
});

// 체크박스 개별 선택
$(".checkBoxWrap").on("click", ".normal", function () {
    var is_checked = true;

    $(".checkBoxWrap .normal").each(function () {
        is_checked = is_checked && $(this).is(":checked");
    });

    $("#agree_all").prop("checked", is_checked);
});
