package com.alotra.chat;
import jakarta.validation.constraints.NotBlank;
public class JoinMessage {
  @NotBlank
  private String roomId;
  @NotBlank
  private String user;
  private String role;
  public String getRoomId(){return roomId;}
  public void setRoomId(String roomId){this.roomId=roomId;}
  public String getUser(){return user;}
  public void setUser(String user){this.user=user;}
  public String getRole(){return role;}
  public void setRole(String role){this.role=role;}
}
