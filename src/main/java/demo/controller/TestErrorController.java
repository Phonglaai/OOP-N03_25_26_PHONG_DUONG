package demo.controller;

import demo.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TestErrorController - Controller để test exception handling
 */
@Controller
public class TestErrorController {

    /**
     * Test MyException
     * Truy cập: /test/my-exception
     */
    @GetMapping("/test/my-exception")
    public String testMyException() throws MyException {
        throw new MyException("Đây là lỗi tùy chỉnh MyException!");
    }

    /**
     * Test NullPointerException
     * Truy cập: /test/null-pointer
     */
    @GetMapping("/test/null-pointer")
    public String testNullPointer() {
        String str = null;
        str.length(); // Sẽ throw NullPointerException
        return "index";
    }

    /**
     * Test Exception chung
     * Truy cập: /test/general-error
     */
    @GetMapping("/test/general-error")
    public String testGeneralError() {
        int result = 10 / 0; // Sẽ throw ArithmeticException
        return "index";
    }

    /**
     * Test với parameter
     * Truy cập: /test/error?type=null
     * Truy cập: /test/error?type=custom
     * Truy cập: /test/error?type=general
     */
    @GetMapping("/test/error")
    public String testError(@RequestParam(defaultValue = "null") String type) throws MyException {
        switch (type) {
            case "null":
                String str = null;
                str.length();
                break;
            case "custom":
                throw new MyException("Custom exception được throw!");
            case "general":
                int result = 10 / 0;
                break;
            default:
                throw new MyException("Unknown error type: " + type);
        }
        return "index";
    }
}
