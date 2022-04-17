package com.freedom.misc.httpclient.exceptions;

import lombok.Getter;

@Getter
public class UrlSyntaxException extends RuntimeException {

    private final String url;

    public UrlSyntaxException(String url) {

        super("Url" + url + " is not syntactically correct.");
        this.url = url;
    }

}