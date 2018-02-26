package com.automation.home.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.home.models.Device;
import com.automation.home.services.DeviceService;

@RestController
@RequestMapping("/devices")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Device> add(@RequestBody Device device) {
		/*if (this.deviceService.exists(device)) {
			return new ResponseEntity<Object>("Resource already exists", HttpStatus.CONFLICT);
		} else {
		}*/
		return new ResponseEntity<Device>(this.deviceService.save(device), HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Device>> getAll() {
		return new ResponseEntity<Iterable<Device>>(this.deviceService.getAll(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
	public ResponseEntity<Device> getDevice(@PathParam("deviceId") long deviceId) {
		return new ResponseEntity<Device>(this.deviceService.findById(deviceId), HttpStatus.OK);
	}

}
