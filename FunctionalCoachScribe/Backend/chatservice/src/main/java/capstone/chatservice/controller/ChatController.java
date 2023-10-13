package capstone.chatservice.controller;

import capstone.chatservice.controller.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    //send message to the chatroom topic
    private SimpMessagingTemplate simpMessagingTemplate;

    //public message method
    @MessageMapping ("/message") //-- /app/message
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message){
        return message;
    }

    //private message method
    @MessageMapping("/private.message")
    public Message receivePrivateMessage(@Payload Message message){
        // we are not declaring just sending the message like the other one, but we want to send this to a particular user
        //for this u use a template to specify those topics that we want
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); //  /user/{Name}}/private
        return message;
    }


}
