package com.dca.spring.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    /** 1000 : 요청 성공 **/
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /** 2000 : Request 오류 **/
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),

    // [POST] /user/login
    POST_LOGIN_EMPTY_IP(false, 2001, "IP or DOMAIN을 입력해주세요"),
    POST_LOGIN_EMPTY_PWD(false, 2002, "PASSWORD를 입력해주세요"),

    // [GET] /malware/detail
    MALWARE_EMPTY_FILE_ID(false, 2005, "파일인덱스를 입력해주세요."),

    /** 3000 : Response 오류 **/
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),
    MALWARE_CHANGEIMAGE_TYPE_ERROR(false, 3005, "changeImageType에 실패하였습니다."),


    /** 4000 : Database, Server 오류 **/
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
