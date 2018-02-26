package com.automation.home.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@RequestMapping(value = "/publish/{topic}/{message}", method = RequestMethod.GET)
    public ResponseEntity<String> publish(
    		@PathVariable("topic") String topic,
    		@PathVariable("message") String message) {
		Context context = ZMQ.context(1);

        //  Socket to talk to server
        Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://104.131.49.181:5555");
        
        requester.send(String.format("%s|_|_%s", topic, message), 0);
        return new ResponseEntity<String>(requester.recvStr(0), HttpStatus.OK);
    }

}
