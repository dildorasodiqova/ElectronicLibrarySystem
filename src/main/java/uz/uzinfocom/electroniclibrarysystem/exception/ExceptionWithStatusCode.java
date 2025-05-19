package uz.uzinfocom.electroniclibrarysystem.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ExceptionWithStatusCode extends BaseException{
    private Integer httpStatusCode;
    private String messageKey;
    private Objects[] args;

    public ExceptionWithStatusCode(Integer status, String messageKey){
        this.httpStatusCode =status;
        this.messageKey = messageKey;
    }


}
