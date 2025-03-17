package com.judo.entitites;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "wifi_sessions")
public class WiFiSession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails user;
    
    @Pattern(
        regexp = "^(\\d{1,3}\\.){3}\\d{1,3}$",
        message = "Invalid IP address format"
    )
    private String ipAddress;

    @Pattern(
        regexp = "^[0-9A-Fa-f]{2}([-:])[0-9A-Fa-f]{2}(\\1[0-9A-Fa-f]{2}){4}$",
        message = "Invalid MAC address format"
    )
    private String macAddress;
    
    private LocalDateTime connectedAt;
    private LocalDateTime disconnectedAt;
    public Long getSessionId() {
        return sessionId;
    }
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
    public UserDetails getUser() {
        return user;
    }
    public void setUser(UserDetails user) {
        this.user = user;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    public LocalDateTime getConnectedAt() {
        return connectedAt;
    }
    public void setConnectedAt(LocalDateTime connectedAt) {
        this.connectedAt = connectedAt;
    }
    public LocalDateTime getDisconnectedAt() {
        return disconnectedAt;
    }
    public void setDisconnectedAt(LocalDateTime disconnectedAt) {
        this.disconnectedAt = disconnectedAt;
    }
    
    
}
