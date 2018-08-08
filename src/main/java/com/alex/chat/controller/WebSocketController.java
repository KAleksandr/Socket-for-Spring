package com.alex.chat.controller;


import com.alex.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  @MessageMapping("/chat.sendMessage")
  @SendTo("/topic/publicChatRoom")
  public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
    return chatMessage;
  }

  @MessageMapping("/addUser")
  @SendTo("/topic/publicChatRoom")
  public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
    //add user in web socket session
    headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
    return chatMessage;
  }


}