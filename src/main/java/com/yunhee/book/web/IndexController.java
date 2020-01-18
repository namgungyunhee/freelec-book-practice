package com.yunhee.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // 알아서 파일경로 + return text + .mustache 로 view Resolver가 처리해줌
        return "index";
    }
}
