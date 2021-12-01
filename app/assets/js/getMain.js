$(function () {
    $('#get_main_btn').click(function () {
        var inputName = $("#summonerName").val()
        console.log(inputName)
        $.ajax(jsRoutes.controllers.HomeController.getMain(inputName))
            .done( function (response) {
                $('#mainChampion').val(response)
            });
    });
});