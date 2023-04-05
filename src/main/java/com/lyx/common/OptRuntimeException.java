package com.lyx.common;

import com.lyx.thrid.hutool.core.util.StrUtil;

public class OptRuntimeException extends RuntimeException
{
    private OptRuntimeException(String message)
    {
        super(message);
    }

    public static OptRuntimeException getInstance(CharSequence template, Object... params)
    {
        return new OptRuntimeException(StrUtil.format(template, params));
    }
}