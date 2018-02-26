package com.automation.home.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.automation.home.controllers.DeviceController;
import com.automation.home.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "devices")
public class Device extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("id")
	private long deviceId;

	@NotNull
	@Column(unique = true)
	private String uniqueIdentifier;

	@NotNull
	private Boolean isActive = true;

	@NotNull
	private Status status = Status.UNKOWN;

	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private List<User> users = new ArrayList<User>();

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public static Device addHATEOAS(Device device) {
		device.add(ControllerLinkBuilder.linkTo(DeviceController.class).slash(device.getDeviceId()).withSelfRel());
		return device;
	}

}
