/**
 * 描述:
 * 作者: JHCZ-宋秋明
 * 时间：2016/03/16
 */
var paramValue=new Array(5);
var paramName=$("#header_js").attr("src");
var result = paramName.match(new RegExp("[\?\&]" + "pageName" + "=([^\&]+)", "i"));
if (result == null || result.length < 1) {
}
switch(result[1])
{
  case 'index':
    paramValue[0]='select';//前面需要有一个空格
    break;
  case 'products':
    paramValue[1]='select';//前面需要有一个空格
    break;
  case 'news':
    paramValue[2]='select';//前面需要有一个空格
    break;
  case 'recruit':
    paramValue[3]='select';//前面需要有一个空格
    break;
  case 'about':
    paramValue[4]='select';//前面需要有一个空格
    break;
  default:
}

var headHtml = '<div class="header main">' +
    '<a class="logo" href="/index.html">' +
    '<img src="/component/app/images/logo.png">' +
    '</a>' +
    '<ul class="menu fr">' +
    '<li class="homepage_li '+paramValue[0]+'"><a href="/index.html">首页</a></li>' +
    '<li class="product_li '+paramValue[1]+'"><a href="/page/products.html">产品与解决方案</a></li>' +
    '<li class="news_li '+paramValue[2]+'"><a href="/page/news.html">新闻中心</a></li>' +
    '<li class="recruit_li '+paramValue[3]+'"><a href="/page/recruit.html">招聘英才</a></li>' +
    '<li class="aboutus_li '+paramValue[4]+'"><a href="/page/about.html">关于我们</a></li>' +
    '</ul>' +
    '</div>';
document.write(headHtml);
