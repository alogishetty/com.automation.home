package com.automation.home.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.automation.home.models.Device;

public interface DeviceRepository extends CrudRepository<Device, Long> {

	@Query("SELECT CASE WHEN COUNT(d.id) > 0 THEN true ELSE false END FROM Device d WHERE d.uniqueIdentifier = :ui")
    boolean doesDeviceExistsByUniqueIdentifier(@Param("ui") String ui);
	
}
