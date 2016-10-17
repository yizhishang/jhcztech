// JavaScript Document
(function($){
	var goToTopTime;
	$.fn.goToTop=function(options){
		var opts = $.extend({},$.fn.goToTop.def,options);
		var $window=$(window);
		$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body'); // opera fix

		var $this=$(this);
		clearTimeout(goToTopTime);
		goToTopTime=setTimeout(function(){
			var cssfixedsupport=$.support.msie && parseFloat($.support.version) < 7;//判断是否ie6
			var controlTop=$window.height() - $this.height()-opts.pageHeightJg;
			controlTop=cssfixedsupport ? $window.scrollTop() + controlTop : controlTop;
			var shouldvisible=( $window.scrollTop() >= opts.startline );
			
			if (shouldvisible){
				$this.stop().show();
			}else{
				$this.stop().hide();
			}
			$this.css({
				position: cssfixedsupport ? 'absolute' : 'fixed',
				top: controlTop,
				right: 5
			});
		},30);
		
		$(this).click(function(event){
			$body.stop().animate( { scrollTop: $(opts.targetObg).offset().top}, opts.duration);
			$(this).blur();
			event.preventDefault();
			event.stopPropagation();
		});
	};
	
	$.fn.goToTop.def={
		pageHeightJg:0,//按钮和页面底部的间隔距离
		startline:30,//出现回到顶部按钮的滚动条scrollTop距离
		duration:200,//回到顶部的速度时间
		targetObg:"body"//目标位置
	};
})(jQuery);
$(function(){
	$('<a href="javascript:;" class="backToTop" title="返回顶部">返回顶部</a>').appendTo("body");
});