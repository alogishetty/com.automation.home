package com.automation.home.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.home.models.Device;
import com.automation.home.repositories.DeviceRepository;

@Service
public class DeviceService {
	
	@Autowired 
	private DeviceRepository deviceRepository;
    
    public boolean exists(Device device) {
    	return this.deviceRepository.doesDeviceExistsByUniqueIdentifier(device.getUniqueIdentifier());
    }
    
    @Transactional
    public Device save(Device device) {
    	this.deviceRepository.save(device);
        return Device.addHATEOAS(device);
    }
    
    @Transactional
    public Iterable<Device> getAll() {
    	Iterable<Device> devices = this.deviceRepository.findAll();
    	devices.forEach(device -> Device.addHATEOAS(device));
    	return this.deviceRepository.findAll();
    }
    
    @Transactional
    public Device findById(long deviceId) {
    	Device device = this.deviceRepository.findOne(deviceId);
    	Device.addHATEOAS(device);
    	return device;
    }

}
