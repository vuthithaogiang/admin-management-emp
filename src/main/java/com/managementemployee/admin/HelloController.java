package com.managementemployee.admin;
import com.managementemployee.admin.common.config.MyLocaleResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MyLocaleResolver myLocaleResolver;

    @GetMapping
    public String hello(HttpServletRequest request) {
        return messageSource.getMessage("hello" , null, myLocaleResolver.resolveLocale(request));
    }
}
