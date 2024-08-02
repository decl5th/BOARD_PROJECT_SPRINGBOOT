package org.choongang.global.exceptions.script;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@Getter
public class AlertBackException extends AlertException {

    private String target;
    private String url;

    public AlertBackException(String message, HttpStatus status) {
        super(message, status);

        target = StringUtils.hasText(target) ? target : "self";

        this.url = url;
        this.target = target;

    }

    public AlertBackException(String message, String url, HttpStatus status) {
        this(message, status);
    }
}