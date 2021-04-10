package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DeviceDTO;
import com.example.demo.exception.DeviceException;


public interface DeviceService {

	public List<DeviceDTO> getAllDevices() throws DeviceException;
	public DeviceDTO getDeviceById(Integer deviceId)  throws DeviceException;
	public String deleteDeviceById(Integer deviceId)  throws DeviceException;
	public String addDevice(DeviceDTO device)  throws DeviceException;
}
