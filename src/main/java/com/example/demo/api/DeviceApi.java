package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Device;
import com.example.demo.repository.DeviceRepository;

@RestController
public class DeviceApi {

	@Autowired
	DeviceRepository deviceRepository;
	
	@PostMapping("/addDevice")
	public String addDevice(@RequestBody Device device)
	{
		deviceRepository.save(device);
		return "Device added with id "+device.getDeviceId();
	}
	
	@GetMapping("/device/{deviceId}")
	public Optional<Device> getDevice(@PathVariable Integer deviceId)
	{
		Optional<Device> device = deviceRepository.findById(deviceId);
		return device;
	}
	@GetMapping("/allDevices")
	public List<Device> getAllDevices()
	{
		return deviceRepository.findAll();
	}
	@DeleteMapping("/deleteDevice/{deviceId}")
	public String deleteDevice(@PathVariable Integer deviceId)
	{
		deviceRepository.deleteById(deviceId);
		return "Device with id "+deviceId+" deleted Successfully!";
	}
}
