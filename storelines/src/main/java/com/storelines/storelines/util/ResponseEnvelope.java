package com.storelines.storelines.util;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseEnvelope {
    private int code;
    private BaseResponseEntity payload;
    private String error;

    public ResponseEnvelope(int code, BaseResponseEntity payload, String error) {
        super();
        this.code = code;
        this.payload = payload;
        this.error = error;
    }

    public ResponseEnvelope(int code, String error) {
        super();
        this.code = code;
        this.error = error;
    }

    public ResponseEnvelope(int code) {
        super();
        this.code = code;
    }

    public ResponseEnvelope() {
        super();
    }

    public ResponseEnvelope(int code, BaseResponseEntity payload) {
        super();
        this.code = code;
        this.payload = payload;
    }
}
