// 定义一个数组，保存当前所有页面的class name
var page_index = ["shouye", "shucheng", "shequ", "shujia", "wode"];

// 输入pagename，打开指定的div，隐藏其他的div
function page_option(pagename){
	var tar_index = page_index.indexOf(pagename);
	page_index.slice(tar_index, 1);
	for (var j = 0; j < page_index.length ; j++){
		var close_div = document.getElementsByClassName(page_index[j]);
		for (var i = 0; i<close_div.length;i++) {
			   close_div[i].style.display="none";
		};
	}

	var opendiv = document.getElementsByClassName(pagename);
	for (var i = 0; i<opendiv.length;i++) {
		   opendiv[i].style.display="block";
	};
}


function shouye_click(){
	page_option("shouye");
}


function shucheng_click(){
	page_option("shucheng");
}

function shequ_click(){
	page_option("shequ");
}
function shujia_click(){
	page_option("shujia");
}
function wode_click(){
	page_option("wode");
}


