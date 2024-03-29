package com.course.server.dto;

public class ResponseDto<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;

    public static<V> ResponseDto errorResult() {
        ResponseDto<V> responseDto = new ResponseDto<>();
        responseDto.setMessage("失败");
        responseDto.setSuccess(false);
        return responseDto;
    }

    public static ResponseDto errorResult(String msg) {
        ResponseDto responseDto = new ResponseDto<>();
        responseDto.setMessage(msg);
        responseDto.setSuccess(false);
        return responseDto;
    }

    public static<V> ResponseDto successResult() {
        ResponseDto<V> responseDto = new ResponseDto<>();
        return responseDto;
    }

    public static<V> ResponseDto<V> successResult(V data) {
        ResponseDto<V> responseDto = new ResponseDto<>();
        responseDto.setContent(data);
        return responseDto;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseDto{");
        sb.append("success=").append(success);
        sb.append(", code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}
