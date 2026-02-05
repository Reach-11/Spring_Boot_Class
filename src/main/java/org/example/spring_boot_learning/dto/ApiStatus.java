package org.example.spring_boot_learning.dto;
// for Java 16 and above, you can use the following record definition:
public record ApiStatus(String message, int code,String version) {

}

// for Java under version 16, you can use the following class definition instead of the record:
//public class ApiStatus {
//    private String message;
//    private String version;
//    private int code;
//
//    public ApiStatus(String message, String version) {
//        this.message = message;
//        this.version = version;
//        this.code  = 200;
//    }
//    public int getCode() { return code; }
//    public String getMessage() { return message; }
//    public String getVersion() { return version; }
//}