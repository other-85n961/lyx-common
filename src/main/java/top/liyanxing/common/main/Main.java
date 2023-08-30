package top.liyanxing.common.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import top.liyanxing.common.Constants;
import top.liyanxing.common.Util;

import java.io.File;

public class Main
{
    public static void main(String[] args)
    {
        boolean img = Util.isImg("/Users/leeyx/Library/CloudStorage/Dropbox/电脑数据/收藏/03.隐私收藏/Ⅰ按专题/偷拍美女专题/3项及以上/C工作_040_2303.mp4");
        Console.log("是图片：", img); // TODO 李艳兴 删除
    }
}
