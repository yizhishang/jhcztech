//判断是否是NetScape浏览器,true:是
ns4 = (document.layers)? true:false

//判断是否是IE,true:是
ie4 = (document.all)? true:false

function init()
{
    if (ns4) block = document.blockDiv
    if (ie4) block = blockDiv.style
    /*这里定义了一个函数，初始化对象block，在NS中，对CSS对象的表示方法是：
    document.blockdiv.propertyname,这里blockdiv是您可以任意定义的名称,
    在IE中， 表示方法是：blockdiv.style.propertyname。上面的代码是针对两
     种浏览器用不同的格式定义对象block,从而确保了在两种浏览器下都能正常显示
    */
}


// 取通过URL传过来的参数 (格式如 ?Param1=Value1&Param2=Value2)
function getUrlParams()
{
    var urlParams = new Object() ;
    var aParams = document.location.search.substr(1).split('&') ;
    for (i = 0; i < aParams.length; i++)
    {
        var aParam = aParams[i].split('=') ;
        urlParams[aParam[0]] = aParam[1];
    }
    return urlParams;
}

/**
  为 Array 类增加一个 max 方法
**/
Array.prototype.max = function()
{
    var i, max = this[0];

    for (i = 1; i < this.length; i++)
    {
        if (max < this[i])
            max = this[i];
    }

    return max;
}

/**
 为字符串增加trim方法
**/
String.prototype.trim = function()
{
    // 用正则表达式将前后空格用空字符串替代。
    return this.replace(/(^\s*)|(\s*$)/g, "");
}


/**
  添加getBytesLength方法，用于得到字节数。中文为2个字节
**/
String.prototype.getBytesLength = function()
{
    var cArr = this.match(/[^\x00-\xff]/ig);
    return this.length + (cArr == null ? 0 : cArr.length);
}

//检查字符的长度，使用字节数来检测，即1个中文当作2个字节
function checkStrLengthOfBytes(strValue, strParam)
{
    //if( isEmpty( strValue ) )	return true; // 此处注释掉，空字符串同样要检查

    // 参数形如：L<10, L=5, L>117
    if (strParam.charAt(0) != 'L')    return false;

    return compare(strValue.getBytesLength(), strParam.substr(1));
}

//检查字符的长度
function checkStrLength(strValue, strParam)
{
    //if( isEmpty( strValue ) )	return true; // 此处注释掉，空字符串同样要检查

    // 参数形如：L<10, L=5, L>117, L<=10, L>=10
    if (strParam.charAt(0) != 'L')    return false;

    return compare(strValue.length, strParam.substr(1));
}
/**
 * 检查文件扩展名
 * @param fileName 文件名
 * @param allowedName 允许的扩展名，以|分开，如"jpg|gif"代表允许.jpg和.gif文件。"*"和""（空字符串）代表不允许所有。如果该文件无扩展名，返回true
 */
function checkFileExtendName(fileName, allowedName)
{
    if (allowedName == null || allowedName == "" || allowedName == "*" ||
        fileName == null || fileName == "" || fileName.indexOf(".") == -1)
    {
        return true;
    }

    var realFileName = "";
    if (fileName.indexOf("\\") != -1)
    { // 如果包含路径名
        realFileName = fileName.substr(fileName.lastIndexOf("\\") + 1, fileName.length);
    }
    else if (fileName.indexOf("/") != -1)
    { // 如果包含路径名（for Unix）
        realFileName = fileName.substr(fileName.lastIndexOf("/") + 1, fileName.length);
    }
    else
    { // 无路径名
        realFileName = fileName;
    }

    if (realFileName.indexOf(".") == -1)
    {
    	return true;
    }

    var extendName = realFileName.substr(realFileName.lastIndexOf(".") + 1, realFileName.length);

    var extendNames = allowedName.split("|");
    //alert("文件名：" + realFileName + " 扩展名：" + extendName + " " + extendNames);

    for (var i = 0; i < extendNames.length; i++)
    {
        if (extendName.toLowerCase() == extendNames[i].toLowerCase())
        {
            return true;
        }
    }

    return false;
}


/**
  根据指定的名称，返回特定的元素数组
**/
function getElements(name)
{
    var objArray = document.getElementsByName(name);
    return objArray;
}

/**
  根据指定的名称，判断元素是否存在
**/
function isElementExist(name)
{
    var objArray = document.getElementsByName(name);
    if (objArray != null && objArray.length > 0)
    {
        return true;
    }
    return false;
}

/**
  检查字符的长度，使用字节数来检测，即1个中文当作2个字节
**/
function validStrLengthOfBytes(objName, strDescription, strParam)
{
    var strMsg = "";
    if (!isElementExist(objName))
    {
        strMsg = strDescription + " 对象不存在";
        window.alert(strMsg);
        return;
    }
    var strValue = getElement(objName).value.trim();

    if (!checkStrLengthOfBytes(strValue, strParam))
    {
        strMsg = '"' + strDescription + '" 长度不正确，必需为' + strParam + '（注意1个中文为2个字节长度）\n';
    }

    return strMsg;
}

/**
  链接转向
**/
function goToURL(url)
{
    window.location = url;
}

/**
  刷新当前窗口
**/
function refresh()
{
    document.location.reload();
}


function openUploadFileDialog(fieldObj)
{
    if(fieldObj)
    {
        if(fieldObj.tagName == "INPUT" && fieldObj.type=="text")
        {
          return window.showModalDialog("upload/upload.jsp",fieldObj,'dialogHeight: 150px; dialogWidth: 350px;edge: Raised; center: Yes; help: no;scroll:no; resizable: no; status: no;')
        }
    }
    alert("接收上传文件路径的对象不是一个输入域");
}

function openDeleteFileDialog(fieldObj)
{
    if(fieldObj)
    {
        if(fieldObj.value.length == 0)
        {
            alert("您还没有上传文件");
            return;
        }
        var url = "upload/delete.jsp?filePath=" + fieldObj.value;
        return window.showModalDialog(url,fieldObj,'dialogHeight: 80px; dialogWidth: 350px;edge: Raised; center: Yes; help: no;scroll:no; resizable: no; status: no;')
    }
}

function isIE() { //ie?  
    if (!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
}

function getSysInfo(){ 
    var locator = new ActiveXObject ("WbemScripting.SWbemLocator");  
    var service = locator.ConnectServer(".");  
    //CPU信息 
    var cpu = new Enumerator (service.ExecQuery("SELECT * FROM Win32_Processor")).item();  
    var cpuType=cpu.Name,hostName=cpu.SystemName; 
    //内存信息 
    var memory = new Enumerator (service.ExecQuery("SELECT * FROM Win32_PhysicalMemory")); 
    for (var mem=[],i=0;!memory.atEnd();memory.moveNext()) mem[i++]={cap:memory.item().Capacity/1024/1024,speed:memory.item().Speed} 
    //系统信息 
    var system=new Enumerator (service.ExecQuery("SELECT * FROM Win32_ComputerSystem")).item(); 
    var physicMenCap=Math.ceil(system.TotalPhysicalMemory/1024/1024),curUser=system.UserName,cpuCount=system.NumberOfProcessors 
     
    return {cpuType:cpuType,cpuCount:cpuCount,hostName:hostName,curUser:curUser,memCap:physicMenCap,mem:mem} 
}