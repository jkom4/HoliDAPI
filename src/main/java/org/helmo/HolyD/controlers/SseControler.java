package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.SseSwaggerControler;
import org.helmo.HolyD.services.SseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin
@RestController
@RequestMapping(value = "/sse")
public class SseControler implements SseSwaggerControler {

    private final SseService sseService;

    public SseControler(SseService sseService) {
        this.sseService = sseService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public SseEmitter subscribe() {
        return sseService.addEmitter();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/unsubscribe")
    public void unsubscribe() {
        sseService.delEmitter();
    }
}
