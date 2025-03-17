package com.secretchat.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secretchat.services.RoomService;
import com.secretchat.entities.Message;
import com.secretchat.entities.Room;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*",maxAge = 3600)
public class RoomController {

    private RoomService roomService;
    private Logger logger = LoggerFactory.getLogger(RoomController.class);

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createRoom(@RequestBody Room room) {
        try {
            var _room = this.roomService.createRoom(room);
            this.logger.info("Room created RoomID: {}",_room.getRoomId());
            return ResponseEntity.status(HttpStatus.CREATED).body(_room);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoom(@PathVariable String roomId) {
        try {
            var room = this.roomService.getRoom(roomId);
            if (room == null) {
                return ResponseEntity.badRequest().body("Room not found");
            }
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // public ResponseEntity<?> joinRoom(@RequestBody Room room) {
    //     try {
    //         var _room = this.roomService.getRoom(room.getRoomId());
    //         if (_room == null) {
    //             return ResponseEntity.badRequest().body("Room not found");
    //         }
    //         return ResponseEntity.ok(_room);
    //     } catch (Exception e) {
    //         this.logger.error(e.getMessage());
    //         return ResponseEntity.internalServerError().build();
    //     }
    // }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMeessagesOfRoom(@PathVariable String roomId,
    @RequestParam(value = "page", required = false, defaultValue = "0") int page,
    @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
        try {
            var messages = this.roomService.getMessages(roomId);
            if (messages == null) {
                return ResponseEntity.badRequest().body("Room not found");
            } else {
                int start = Math.max(0,messages.size()-(page+1)*size);
                int end = Math.min(messages.size(),start+size);
                List<Message> paginatedMessages = messages.subList(start, end);
                return ResponseEntity.ok(paginatedMessages);
            }
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
