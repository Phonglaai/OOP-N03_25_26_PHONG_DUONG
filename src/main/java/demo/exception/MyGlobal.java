package demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

/**
 * MyGlobal - Global Exception Handler
 * Bắt và xử lý lỗi trong ứng dụng
 */
@ControllerAdvice
public class MyGlobal {

    /**
     * Bắt lỗi MyException - Custom Exception
     */
    @ExceptionHandler(MyException.class)
    public ModelAndView handleMyException(MyException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        
        System.err.println("❌ MY EXCEPTION: " + e.getMessage());
        
        modelAndView.addObject("errorMessage", e.getMessage());
        modelAndView.addObject("errorType", "MyException");
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("suggestion", "Lỗi tùy chỉnh từ ứng dụng");
        
        return modelAndView;
    }

    /**
     * Bắt lỗi NullPointerException
     */
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleNullPointerException(NullPointerException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        
        System.err.println("❌ LỖI NULL POINTER: " + e.getMessage());
        
        modelAndView.addObject("errorMessage", "Dữ liệu không tồn tại hoặc bị null");
        modelAndView.addObject("errorType", "NullPointerException");
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("suggestion", "Vui lòng kiểm tra lại dữ liệu đầu vào");
        
        return modelAndView;
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        
        System.err.println("LỖI XẢY RA: " + e.getMessage());
        e.printStackTrace();
        
        modelAndView.addObject("errorMessage", e.getMessage());
        modelAndView.addObject("errorType", e.getClass().getSimpleName());
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("suggestion", "Vui lòng thử lại sau");
        
        return modelAndView;
    }
}
