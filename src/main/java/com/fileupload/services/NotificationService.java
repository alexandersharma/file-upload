package com.fileupload.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fileupload.model.Notification;


/**
 * Service class for sending notification messages.
 */
@Service
public class NotificationService {
   
  @Autowired
  private SimpMessagingTemplate messagingTemplate;
  
  /**
   * Send notification to users subscribed on channel "/queue/notify".
   * 
   * @param notification The notification message.
   */
  public void notify(Notification notification) {
    messagingTemplate.convertAndSend("/queue/notify", notification);
    return;
  }
  
} 
