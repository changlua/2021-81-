package com.example.alitest2;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @ClassName ReactiveWebSocketHandler
 * @Author ChangLu
 * @Date 2021/10/31 14:15
 * @Description TODO
 */
@Component("ReactiveWebSocketHandler")
public class ReactiveWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(
                session.receive()
                        .map(WebSocketMessage::getPayload)
                        .map(getBufferConverter())
                        .map(Utils::decodeMessage)
                        .map(Utils::stripHtmlTag)
                        .log()
                        .map(session::textMessage));
    }

    private Function<DataBuffer, byte[]> getBufferConverter() {
        final byte[] buffer = new byte[1024];
        return (DataBuffer dataBuffer) -> {
            int length = dataBuffer.readableByteCount();
            dataBuffer.read(buffer, 0, length);
            return buffer;
        };
    }


}