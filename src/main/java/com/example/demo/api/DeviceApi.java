package com.example.demo.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DeviceDTO;
import com.example.demo.exception.DeviceException;
import com.example.demo.model.Device;

import com.example.demo.service.DeviceService;

@RestController
public class DeviceApi {

	@Autowired
	DeviceService deviceService;
	
	@PostMapping("/addDevice")
	public String addDevice(@RequestBody Device device) throws DeviceException
	{
		DeviceDTO deviceToAdd = new DeviceDTO();
		deviceToAdd.setDeviceId(device.getDeviceId());
		deviceToAdd.setDeviceName(device.getDeviceName());
		deviceToAdd.setDeviceType(device.getDeviceType());
		deviceToAdd.setOsVersion(device.getOsVersion());
		
		String toReturn = deviceService.addDevice(deviceToAdd);
		return toReturn;
	}
	
	@GetMapping("/device/{deviceId}")
	public DeviceDTO getDevice(@PathVariable Integer deviceId) throws DeviceException
	{
		DeviceDTO device= deviceService.getDeviceById(deviceId);
		return device;
	}
	@GetMapping("/allDevices")
	public List<DeviceDTO> getAllDevices() throws DeviceException
	{
		List<DeviceDTO> devices = deviceService.getAllDevices();
		return devices;
	}
	@DeleteMapping("/deleteDevice/{deviceId}")
	public String deleteDevice(@PathVariable Integer deviceId) throws DeviceException
	{
		String toReturn = deviceService.deleteDeviceById(deviceId);
		return toReturn;
		
	}
}
