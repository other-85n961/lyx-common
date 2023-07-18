package top.liyanxing.common;

import top.liyanxing.common.util.LyxStrUtil;

public class OptRuntimeException extends RuntimeException
{
    private OptRuntimeException(String message)
    {
        super(message);
    }

    public static OptRuntimeException getInstance(CharSequence template, Object... params)
    {
        return new OptRuntimeException(LyxStrUtil.format(template, params));
    }
}