package top.liyanxing.common;

import cn.hutool.core.util.StrUtil;

/**
 * 通用返回数据
 * @author 李艳兴
 */
public class CommonResult<T>
{
    /**
     * 方法执行是否成功
     */
    private Boolean success;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    private CommonResult()
    {
    }

    private CommonResult(Boolean success, String msg, T data)
    {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getSuccess()
    {
        return success;
    }

    public String getMsg()
    {
        return msg;
    }

    public T getData()
    {
        return data;
    }

    @Override
    public String toString()
    {
        return "CommonResult{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> CommonResult<T> success()
    {
        return new CommonResult<T>(true,null,null);
    }

    public static <T> CommonResult<T> successData(T data)
    {
        return new CommonResult<T>(true,null,data);
    }

    public static <T> CommonResult<T> errorMsg(CharSequence template, Object... params)
    {
        return new CommonResult<T>(false, StrUtil.format(template, params), null);
    }
}