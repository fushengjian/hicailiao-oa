$(function() {
	$(".ce > li > a").click(function() {
		$(this).addClass("xz").parents().siblings().find("a").removeClass("xz");
		$(this).parents().siblings().find(".er").hide(300);
		$(this).siblings(".er").toggle(300);
		$(this).parents().siblings().find(".er > li > .thr").hide().parents().siblings().find(".thr_nr").hide();
	});

	$(".er > li > a").click(function() {
		$(this).addClass("sen_x").parents().siblings().find("a").removeClass("sen_x");
		$(this).parents().find(".thr").hide(300);
		$(this).siblings(".thr").toggle();
	});

	$(".thr > li > a").click(function() {
		$(this).addClass("xuan").parents().siblings().find("a").removeClass("xuan");
		$(this).parents().siblings().find(".thr_nr").hide();
		$(this).siblings(".thr_nr").toggle();
	});

	$(".ce-other > li > a").click(function() {
		$(this).addClass("xz").parents().siblings().find("a").removeClass("xz");

	});
});