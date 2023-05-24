$("#book-button2").click(function () {
    $.ajax({

        //写地址，要进django的urls匹配，且在这里只能写完整的地址，
        //因为ajax无法重定向，若写/add，django虽然会帮你加上/但是，无法重定向到这个页面，导致报错
        //url为 /bookmall/shoucang
        //使用django去除url硬编码
        url: "/bookmall/shoucang/",
        type: 'get',
        data: {bid: $("#book-id").val(),uid: $("#user-id").val()},
        success: function (data) {
            let bt = document.getElementById("book-button2")
            bt.innerHTML = data
        },
    })

})

$("#book-button3").click(function () {
    $.ajax({

        //写地址，要进django的urls匹配，且在这里只能写完整的地址，
        //因为ajax无法重定向，若写/add，django虽然会帮你加上/但是，无法重定向到这个页面，导致报错
        //url为 /bookmall/shoucang
        //使用django去除url硬编码
        url: "/bookmall/dianzan/",
        type: 'post',
        data: {bid3: $("#book-id").val(),uid3: $("#user-id").val()},
        success: function (data2) {
            let bt3 = document.getElementById("book-button3")
            bt3.innerHTML = data2
        },
    })

})