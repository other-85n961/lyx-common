package com.lyx;

/**
 * 通用返回数据
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

    public void setSuccess(Boolean success)
    {
        this.success = success;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
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

    public static <T> CommonResult<T> successMsg(String msg)
    {
        return new CommonResult<T>(true, msg, null);
    }

    public static <T> CommonResult<T> successData(T data)
    {
        return new CommonResult<T>(true,null,data);
    }

    public static <T> CommonResult<T> successMsgData(String msg, T data)
    {
        return new CommonResult<T>(true,msg,data);
    }

    public static <T> CommonResult<T> errorMsg(String msg)
    {
        return new CommonResult<T>(false, msg, null);
    }
}