package com.alotra.chat;
import jakarta.validation.constraints.NotBlank;
public class ChatMessage {
  @NotBlank
  private String roomId;
  @NotBlank
  private String sender;
  @NotBlank
  private String content;
  private String role;
  public String getRoomId(){return roomId;}
  public void setRoomId(String roomId){this.roomId=roomId;}
  public String getSender(){return sender;}
  public void setSender(String sender){this.sender=sender;}
  public String getContent(){return content;}
  public void setContent(String content){this.content=content;}
  public String getRole(){return role;}
  public void setRole(String role){this.role=role;}
}
