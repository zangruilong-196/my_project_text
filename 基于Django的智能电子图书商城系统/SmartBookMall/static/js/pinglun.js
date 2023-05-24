var pid = ""        //设置一个变量pid默认为空，用于后面作为数据库存储的父级评论的ID，如果没有父级评论则为空，子评论有父级评论 
//提交评论按钮的点击事件
$(".comment_post p button").click(function (){
    $.ajax({
        url: '/bookmall/pinglun/',
        type: 'post',
        data: {
            comment_content: $("#comment_content").val(), // 提交的数据内容data
            article_id: $("#book-id").val(),
            pid: pid
        },
        success: function (res){           // 本例中返回的数据仅仅用于在控制台打印而已
            console.log(res)               // 控制台打印返回的数据
            $("#comment_content").val("")  // 提交完成后，清空评论输入框的内容
            pid = ""                       // 子评论提交完成后，将pid默认设置为空，恢复为默认的父评论
        }
    })
})
 
 
//回复按钮的点击事件
$(".reply").click(function (){
    $("#comment_content").focus()      // 回复按钮的事件，点击时，将光标聚集到评论输入框中
    var val = "@" + $(this).attr("username") + "\n"   //$(this)指代".reply"标签本身，获取这个标签的username值
    $("#comment_content").val(val)      //回复时，自动在输入框加入：@要回复的人
    pid = $(this).attr("pk")            //当点击回复时，父评论ID不再为空，此时修改为：对应评论的主键值ID
})