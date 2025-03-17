package com.secretchat.controllers;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.secretchat.entities.Message;
import com.secretchat.entities.Room;
import com.secretchat.payloads.MessageRequest;
import com.secretchat.services.RoomService;

@Controller
@CrossOrigin("*")
public class ChatController {

    private RoomService roomService;

    public ChatController(RoomService roomService) {
        this.roomService = roomService;
    }

    @MessageMapping("/sendMessage/{roomId}") // /app/chat/roomId
    @SendTo("/topic/room/roomId") // subscribe
    public Message sendMessage( @DestinationVariable String roomId,
        @RequestBody MessageRequest request) {
            Message message = new Message();
  
            Room room = this.roomService.getRoom(request.getRoomId());
            if(room != null) {
                message.setContent(request.getContent());
                message.setSender(request.getSender());
                message.setTimeStamp(LocalDateTime.now());
                roomService.saveMessages(request.getRoomId(), message);
            } else {
                throw new RuntimeException("Room not found");
            }
            return message;
       
    }
}
