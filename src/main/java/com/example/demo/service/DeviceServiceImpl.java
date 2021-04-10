package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.DeviceDTO;
import com.example.demo.exception.DeviceException;
import com.example.demo.model.Device;
import com.example.demo.repository.DeviceRepository;

@Service(value = "deviceService")
@Transactional
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepository deviceRepository;
	
	public List<DeviceDTO> getAllDevices() throws DeviceException
	{
		List<Device> devicesFromRepo = deviceRepository.findAll();
		List<DeviceDTO> devices = new ArrayList<>();
		for(Device device:devicesFromRepo)
		{
			DeviceDTO d = new DeviceDTO();
			d.setDeviceId(device.getDeviceId());
			d.setDeviceName(device.getDeviceName());
			d.setDeviceType(device.getDeviceType());
			d.setOsVersion(device.getOsVersion());
			
			devices.add(d);
		}
		if(devices.isEmpty())
			throw new DeviceException("Service.NO_DEVICE_FOUND");
		
		return devices;
	}
	
	public DeviceDTO getDeviceById(Integer deviceId) throws DeviceException
	{
		Optional<Device> optional = deviceRepository.findById(deviceId);
		Device deviceFromRepo = optional.orElseThrow(()-> new DeviceException("Service.Device_Not_Found"));
		DeviceDTO device = new DeviceDTO();
		device.setDeviceId(deviceFromRepo.getDeviceId());
		device.setDeviceName(deviceFromRepo.getDeviceName());
		device.setDeviceType(deviceFromRepo.getDeviceType());
		device.setOsVersion(deviceFromRepo.getOsVersion());
		return device;
		
	}
	public String addDevice(DeviceDTO device) throws DeviceException{
		
		Device deviceToAdd = new Device();
		deviceToAdd.setDeviceId(device.getDeviceId());
		deviceToAdd.setDeviceName(device.getDeviceName());
		deviceToAdd.setDeviceType(device.getDeviceType());
		deviceToAdd.setOsVersion(device.getOsVersion());
		
		Device addedDevice = deviceRepository.save(deviceToAdd);
		return "Device Added with id: "+addedDevice.getDeviceId();
		
	}
	
	public String deleteDeviceById(Integer deviceId) throws DeviceException
	{
		Optional<Device> optional = deviceRepository.findById(deviceId);
		Device device = optional.orElseThrow(()-> new DeviceException("Service.No_Device_Found"));
		deviceRepository.deleteById(device.getDeviceId());
		return "Device deleted with id: "+deviceId;
		
	}
}
