package spring.be.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String roomId; // 방 번호
    private String sender;  // 보낸 사람
    private String content; // 내용
}
