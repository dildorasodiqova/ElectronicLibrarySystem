package uz.uzinfocom.electroniclibrarysystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.ResponseWrapper;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestControllerAdvice
@Slf4j
public class ExceptionController {



    @ExceptionHandler({ExceptionWithStatusCode.class})
    ResponseEntity<ResponseWrapper<?>> handeCustomErrors(ExceptionWithStatusCode ex, HttpServletRequest request) {
        //TODO  internationalization



        return ResponseEntity.status(ex.getHttpStatusCode()).body(ResponseWrapper.error(ex.getHttpStatusCode(), ex.getMessageKey()));
    }

    @ExceptionHandler({Throwable.class})
    ResponseEntity<ResponseWrapper<?>> handleOtherErrors(Exception ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex.getStackTrace());

        return ResponseEntity.status(500).body(ResponseWrapper.internalServerError("Internal server error"));
    }
}
