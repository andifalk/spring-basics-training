package com.example;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

@Component
@WebEndpoint(id = "custom")
public class MyCustomEndpoint {

    @ReadOperation
    public MyStatus myValue() {
        return new MyStatus(200, "ok");
    }

    class MyStatus {
        private int status;
        private String detail;

        public MyStatus() {
        }

        public MyStatus(int status, String detail) {
            this.status = status;
            this.detail = detail;
        }

        public int getStatus() {
            return status;
        }

        public String getDetail() {
            return detail;
        }
    }
}
