package com.secretchat.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secretchat.entities.Message;
import com.secretchat.entities.Room;
import com.secretchat.repositories.RoomRepositories;

@Service
public class RoomService {

    private RoomRepositories roomRepo;

    RoomService(RoomRepositories roomrepo) {
        this.roomRepo = roomrepo;
    }

    public Room createRoom(Room room) {
        var _room = this.roomRepo.findByRoomId(room.getRoomId());
        if(_room == null) {
            room.setCreatedDateTime(LocalDateTime.now());
            this.roomRepo.save(room);
        } else {
            // room = null;
            throw new RuntimeException("Room is already exist");
        }
        return room;
    }

    public Room getRoom(String roomId) {
        var room = this.roomRepo.findByRoomId(roomId);
        return room;
    }

    public List<Message> getMessages(String roomId) {
        var room = this.roomRepo.findByRoomId(roomId);
        if (room != null) {
            return room.getMessages();
        } else {
            // throw new RuntimeException("Room not found");
            return null;
        }
    }

    public void saveMessages(String roomId,Message messages) {
        var room = this.roomRepo.findByRoomId(roomId);
        if (room != null) {
            room.getMessages().add(messages);
            roomRepo.save(room);
        } else {
            throw new RuntimeException("Room not found");
        }
    }

}
