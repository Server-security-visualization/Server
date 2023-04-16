package com.dca.spring.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BaseException extends Exception {
    private BaseResponseStatus status;
}
