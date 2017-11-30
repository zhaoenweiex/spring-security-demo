package com.zew.research.springsecuritydemo.ctrl;

import com.zew.research.springsecuritydemo.util.FakeRedis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class Controller {
    @PostMapping(value = "/user/login")
    public void login(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, String userName) throws IOException {
        String remoteAddress=request.getRemoteAddr();
        httpSession.setAttribute("cur_user",userName);
        response.sendRedirect("/index.html");
        FakeRedis.getInstance().setKV(userName+"_current_ip",remoteAddress);
    }
    @GetMapping(value = "/user/find")
    public String findUser(HttpSession httpSession) {
        Object obj= httpSession.getAttribute("cur_user");
        if(obj instanceof String) {
            return (String) obj;
        } else {
            return "you are no one";
        }
    }
    @GetMapping(value = "/admin/hello")
    public String sayHello() {
        return "hello";
    }
}
