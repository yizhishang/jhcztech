function swapButton(id)
{
    var btn = null;
    btn = document.all(id);
    if (btn == null)
        return;
    if (btn.click == 'yes')
        return;
    if (btn.background == 'images/left_btn_bg_dark.gif')
    {
        btn.background = 'images/left_btn_bg_light.gif';
    }
    else if (btn.background == 'images/left_btn_bg_light.gif')
    {
        btn.background = 'images/left_btn_bg_dark.gif';
    }
    return;
}

function clickButton(id)
{
    var btn = null;
    btn = document.all(id);
    btn.click = 'yes';
    for (var i = 0; i < tabs.length; i++)
    {
        if (tabs[i] != '')
        {
            btn = document.all(tabs[i]);
            if (btn == null)
                continue;
            if (tabs[i] != id)
            {
                btn.background = "images/left_btn_bg_dark.gif";
                btn.click = 'no';
            }
        }
    }
    return;
}

