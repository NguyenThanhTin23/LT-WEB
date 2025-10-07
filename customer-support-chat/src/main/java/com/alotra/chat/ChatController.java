package com.alotra.chat;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
@Controller
public class ChatController {
  private final SimpMessagingTemplate messagingTemplate;
  public ChatController(SimpMessagingTemplate messagingTemplate){this.messagingTemplate=messagingTemplate;}
  @MessageMapping("/room/{roomId}/send")
  public void send(@DestinationVariable String roomId,@Valid @Payload ChatMessage msg){
    messagingTemplate.convertAndSend("/topic/room."+roomId,msg);
  }
  @MessageMapping("/room/{roomId}/join")
  public void join(@DestinationVariable String roomId,@Valid @Payload JoinMessage msg){
    ChatMessage system=new ChatMessage();
    system.setRoomId(roomId);
    system.setSender("system");
    system.setRole("system");
    system.setContent((msg.getRole()!=null?msg.getRole():"user")+": "+msg.getUser()+" joined");
    messagingTemplate.convertAndSend("/topic/room."+roomId,system);
  }
}
