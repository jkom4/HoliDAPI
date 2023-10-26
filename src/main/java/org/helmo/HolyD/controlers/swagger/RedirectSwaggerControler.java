package org.helmo.HolyD.controlers.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class RedirectSwaggerControler {
    @RequestMapping("/doc")
    public String swaggerHome() {
        return "redirect:/swagger-ui/";
    }
}
