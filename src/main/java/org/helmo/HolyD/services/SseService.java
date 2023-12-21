package org.helmo.HolyD.services;

import org.helmo.HolyD.repository.DTO.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private final Map<Long, SseEmitter> emittersMap = new ConcurrentHashMap<>();

    public SseEmitter addEmitter() {
        Long userId = ((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emittersMap.put(userId, emitter);
        emitter.onCompletion(() -> emittersMap.remove(userId, emitter));
        emitter.onTimeout(() -> emittersMap.remove(userId, emitter));
        System.out.println("User: %d connected to SSE flux");
        return emitter;
    }
    public void delEmitter() {
        Long userId = ((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SseEmitter emitter = emittersMap.get(userId);
        emitter.complete();
        emittersMap.remove(userId, emitter); //Pour être sûr
        System.out.println("User : %d disconnected to SSE flux");
    }

    public void sendMessageSSEToclients(Set<Long> usersId, boolean all){ //classe Message mais time<assez
        String msg = all ? "ALL" : "MSG"; // :O
        emittersMap.forEach((userId, emitter) -> {
            if (usersId.contains(userId)){
                try {
                    emitter.send(msg);
                } catch (Exception e) {
                    emitter.completeWithError(e);
                    emittersMap.remove(userId, emitter);
                }
            }
        });
    }
}
