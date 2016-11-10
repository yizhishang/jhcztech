/**
 * 描述:  引入所需要的js包
 * 作者: yizhishang-蒋斌
 * 时间：2016/3/14
 */
if(!window['ui_relative_path']){
  window['ui_relative_path']='';
}
var _includedTag='';
_includedTag += '<link href="'+ui_relative_path+'favicon.ico" rel="shortcut icon" type="text/css"/>';
_includedTag += '<link href="'+ui_relative_path+'/component/bootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>'
_includedTag += '<link href="'+ui_relative_path+'/component/app/util.css" rel="stylesheet" type="text/css"/>';
_includedTag += '<link href="'+ui_relative_path+'/component/app/common.css" rel="stylesheet" type="text/css"/>';




_includedTag += '<script src="'+ui_relative_path+'/component/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/bootstrap/bootstrap.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/html5/respond.min.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/html5/html5shiv.min.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/nicescroll/jquery.easing.1.3.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/nicescroll/jquery.nicescroll.min.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/nicescroll/jquery.nicescroll.plus.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/superslide/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/qcode/qcode.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/tip/jquery.poshytip.min.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/component/mousewheel/mousewheel.js" type="text/javascript"></script>';
_includedTag += '<script src="'+ui_relative_path+'/js/common/ui.js" type="text/javascript"></script>';
document.write(_includedTag);
