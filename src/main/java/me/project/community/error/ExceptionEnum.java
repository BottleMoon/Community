package me.project.community.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    LOGIN_FAIL_EXCEPTION(HttpStatus.ACCEPTED, "E01", "id or password is not correct"),
    DELETE_FAIL_EXCEPTION(HttpStatus.ACCEPTED, "E02", "No permission for delete");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
