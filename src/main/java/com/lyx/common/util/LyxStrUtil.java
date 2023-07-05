package com.lyx.common.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class LyxStrUtil
{
    public static String format(CharSequence template, Object... params)
    {
        if (Objects.isNull(template))
        {
            return "null";
        }

        if (Objects.isNull(params) || params.length == 0 || isBlank(template))
        {
            return template.toString();
        }

        return format(template.toString(), params);
    }

    public static boolean isBlank(CharSequence str)
    {
        final int length;
        if ((str == null) || ((length = str.length()) == 0))
        {
            return true;
        }
        for (int i = 0; i < length; i++)
        {
            if (!isBlankChar(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean isBlankChar(char c)
    {
        return isBlankChar((int) c);
    }

    private static boolean isBlankChar(int c)
    {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a'
                || c == '\u0000'
                || c == '\u3164'
                || c == '\u2800'
                || c == '\u180e';
    }

    private static String format(String strPattern, Object... argArray)
    {
        return formatWith(strPattern, "{}", argArray);
    }

    private static String formatWith(String strPattern, String placeHolder, Object... argArray)
    {
        if (isBlank(strPattern) || isBlank(placeHolder) || (argArray == null || argArray.length == 0))
        {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();
        final int placeHolderLength = placeHolder.length();

        // 初始化定义好的长度以获得更好的性能
        final StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

        int handledPosition = 0;// 记录已经处理到的位置
        int delimIndex;// 占位符所在位置
        for (int argIndex = 0; argIndex < argArray.length; argIndex++)
        {
            delimIndex = strPattern.indexOf(placeHolder, handledPosition);
            if (delimIndex == -1)
            {// 剩余部分无占位符
                if (handledPosition == 0)
                { // 不带占位符的模板直接返回
                    return strPattern;
                }
                // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                sbuf.append(strPattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }

            // 转义符
            if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == '\\')
            {// 转义符
                if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == '\\')
                {// 双转义符
                    // 转义符之前还有一个转义符，占位符依旧有效
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + placeHolderLength;
                }
                else
                {
                    // 占位符被转义
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(placeHolder.charAt(0));
                    handledPosition = delimIndex + 1;
                }
            }
            else
            {// 正常占位符
                sbuf.append(strPattern, handledPosition, delimIndex);
                sbuf.append(utf8Str(argArray[argIndex]));
                handledPosition = delimIndex + placeHolderLength;
            }
        }

        // 加入最后一个占位符后所有的字符
        sbuf.append(strPattern, handledPosition, strPatternLength);

        return sbuf.toString();
    }

    private static String utf8Str(Object obj)
    {
        return mStr(obj, StandardCharsets.UTF_8);
    }

    private static String mStr(Object obj, Charset charset)
    {
        if (null == obj)
        {
            return null;
        }

        if (obj instanceof String)
        {
            return (String) obj;
        }
        else if (obj instanceof byte[])
        {
            return str((byte[]) obj, charset);
        }
        else if (obj instanceof Byte[])
        {
            return str((Byte[]) obj, charset);
        }
        else if (obj instanceof ByteBuffer)
        {
            return str((ByteBuffer) obj, charset);
        }
        else if (null != obj && obj.getClass().isArray())
        {
            return arrayToString(obj);
        }

        return obj.toString();
    }

    private static String arrayToString(Object obj)
    {
        if (null == obj)
        {
            return null;
        }

        if (obj instanceof long[])
        {
            return Arrays.toString((long[]) obj);
        }
        else if (obj instanceof int[])
        {
            return Arrays.toString((int[]) obj);
        }
        else if (obj instanceof short[])
        {
            return Arrays.toString((short[]) obj);
        }
        else if (obj instanceof char[])
        {
            return Arrays.toString((char[]) obj);
        }
        else if (obj instanceof byte[])
        {
            return Arrays.toString((byte[]) obj);
        }
        else if (obj instanceof boolean[])
        {
            return Arrays.toString((boolean[]) obj);
        }
        else if (obj instanceof float[])
        {
            return Arrays.toString((float[]) obj);
        }
        else if (obj instanceof double[])
        {
            return Arrays.toString((double[]) obj);
        }
        else if (null != obj && obj.getClass().isArray())
        {
            // 对象数组
            try
            {
                return Arrays.deepToString((Object[]) obj);
            }
            catch (Exception ignore)
            {
                //ignore
            }
        }

        return obj.toString();
    }

    private static String str(byte[] data, Charset charset)
    {
        if (data == null)
        {
            return null;
        }

        if (null == charset)
        {
            return new String(data);
        }
        return new String(data, charset);
    }

    private static String str(Byte[] data, Charset charset)
    {
        if (data == null)
        {
            return null;
        }

        byte[] bytes = new byte[data.length];
        Byte dataByte;
        for (int i = 0; i < data.length; i++)
        {
            dataByte = data[i];
            bytes[i] = (null == dataByte) ? -1 : dataByte;
        }

        return str(bytes, charset);
    }

    private static String str(ByteBuffer data, Charset charset)
    {
        if (null == charset)
        {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }
}
