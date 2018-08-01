package cn.kvmial.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/1 0001 下午 15:59
 */


@Controller
public class HelloController {
    @RequestMapping("/index")
    public String hello(){
        return "index";
    }

    @RequestMapping("/index/admin")
    public String admin() {
        return "admin/index";
    }
}
