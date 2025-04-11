package com.udh.common.presentation.http;

import com.udh.common.presentation.domain.value_object.RequestAttribute;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

import java.util.List;

@Getter
@Setter
public final class Response {
    /**
     * Trả về true nếu thao tác thành công
     */
    private boolean success;

    /**
     * Message
     */
    private String message = "Thao tác thành công";

    /**
     * Dữ liệu trả về
     */
    private Object data = null;

    /**
     * Trả về trace_id sử dụng cho mục đích debug
     */
    private String traceId = null;

    public Response() {
        traceId = MDC.get(RequestAttribute.REQ_ATTR_REQUEST_ID.getKey());
    }

    public static Response newInstance() {
        return new Response();
    }

    public static ResponseEntity<Response> responseJson(Response response, HttpStatus status) {
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    public static ResponseEntity<Response> success() {
        return success("Thao tác thành công", null);
    }

    public static ResponseEntity<Response> success(Object data) {
        return success("Thao tác thành công", data);
    }

    public static ResponseEntity<Response> success(String message, Object data) {
        Response response = Response.newInstance();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return responseJson(response, HttpStatus.OK);
    }

    public static ResponseEntity<Response> fail(String message) {
        return error(message, null, HttpStatus.OK);
    }

    public static ResponseEntity<Response> fail(String message, Object data) {
        return error(message, data, HttpStatus.OK);
    }

    public static ResponseEntity<Response> error(String message, Object data, HttpStatus status) {
        Response response = Response.newInstance();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(data);
        return responseJson(response, status);
    }

    public static ResponseEntity<Response> exception(Throwable throwable) {
        return exception(throwable, (String) null, List.of());
    }

    public static ResponseEntity<Response> exception(Throwable throwable, String message) {
        return exception(throwable, message, List.of());
    }

    public static ResponseEntity<Response> exception(Throwable throwable, String message, Object data) {
        return exception(throwable, message, data, (HttpStatus) null);
    }

    public static ResponseEntity<Response> exception(Throwable throwable, String message, Object data, HttpStatus httpStatus) {
        if (message == null || message.isEmpty()) {
            message = throwable.getMessage();
        }

        if (httpStatus == null && throwable instanceof ErrorResponse) {
            httpStatus = HttpStatus.valueOf(((ErrorResponse) throwable).getStatusCode().value());
        }

        if (throwable instanceof ValidationException) {
            return error(message, data, httpStatus != null ? httpStatus : HttpStatus.BAD_REQUEST);
        } else {
            return error(message, data, httpStatus != null ? httpStatus : HttpStatus.OK);
        }
    }
}