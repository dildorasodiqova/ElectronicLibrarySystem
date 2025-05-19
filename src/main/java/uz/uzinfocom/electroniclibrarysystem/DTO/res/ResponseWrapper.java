package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper<T> {

    @Setter
    Integer code = 2000;

    T data;


    String errorMessage;

    Long timestamp;

    public ResponseWrapper(Integer code, T data) {
        this.data = data;
        this.code =code;
        this.timestamp = System.currentTimeMillis();
    }
    public ResponseWrapper(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }


    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(200, data);
    }

    public static <T> ResponseWrapper<T> created(T data) {
        return new ResponseWrapper<>(201, data);
    }
    public static <T> ResponseWrapper<T> error(Integer code, String errorMessage) {
        return new ResponseWrapper<>(code, errorMessage);
    }

    public static <T> ResponseWrapper<T> badRequest(String errorMessage) {
        return new ResponseWrapper<>(400, errorMessage);
    }

    public static <T> ResponseWrapper<T> unauthorized(String errorMessage) {
        return new ResponseWrapper<>(401, errorMessage);
    }

    public static <T> ResponseWrapper<T> forbidden(String errorMessage) {
        return new ResponseWrapper<>(403, errorMessage);
    }

    public static <T> ResponseWrapper<T> notFound(String errorMessage) {
        return new ResponseWrapper<>(404, errorMessage);
    }

    public static <T> ResponseWrapper<T> internalServerError(String errorMessage) {
        return new ResponseWrapper<>(500, errorMessage);
    }

}