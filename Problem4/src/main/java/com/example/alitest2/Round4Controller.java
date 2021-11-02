package com.example.alitest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName Round4Controller
 * @Author ChangLu
 * @Date 2021/10/31 14:15
 * @Description TODO
 */
@RestController
public class Round4Controller {

    @Autowired
    @Qualifier("webSocketUrlMap")
    private Map<String, WebSocketHandler> webSocketUrlMap;

    @Autowired
    private MapReactiveUserDetailsService userDetailsService;

    @GetMapping("/endpoints")
    public Set<String> wsEndpoints() {
        return webSocketUrlMap.keySet();
    }

    @PostMapping("/users")
    public void addUser(UserDetails user) {
        userDetailsService.addUser(user);
    }

}