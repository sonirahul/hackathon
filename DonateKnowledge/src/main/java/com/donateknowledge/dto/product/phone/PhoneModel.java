package com.donateknowledge.dto.product.phone;

import static com.donateknowledge.utils.CheapestGadgetUtils.getEnumMappedList;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.donateknowledge.dto.product.phone.battery.Battery;
import com.donateknowledge.dto.product.phone.camera.Camera;
import com.donateknowledge.dto.product.phone.connectivity.Connectivity;
import com.donateknowledge.dto.product.phone.display.Display;
import com.donateknowledge.dto.product.phone.network.Network;
import com.donateknowledge.dto.product.phone.os.OS;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true, 
value={"simSlotCount"})
public class PhoneModel {

	private Battery battery;
	private List<Camera> cameras;
	private String chip;
	private Connectivity connectivity;
	private String cpu;
	private Integer cpuCores;
	private BigDecimal cpuFrequency;
	private Display display;
	private String gpu;
	private Memory memory;
	private String modelName;
	private Network networkConnectivity;
	private OS os;
	private List<String> sensors;
	private List<String> simSlotTypes;
	private Integer simSlotCount;

	public Battery getBattery() {
		return battery;
	}
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	public List<Camera> getCameras() {
		if (cameras == null) {
			cameras = new ArrayList<Camera>();
		}
		return cameras;
	}
	public String getChip() {
		return chip;
	}
	public void setChip(String chip) {
		this.chip = chip;
	}
	public Connectivity getConnectivity() {
		return connectivity;
	}
	public void setConnectivity(Connectivity connectivity) {
		this.connectivity = connectivity;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public Integer getCpuCores() {
		return cpuCores;
	}
	public void setCpuCores(Integer cpuCores) {
		this.cpuCores = cpuCores;
	}
	public BigDecimal getCpuFrequency() {
		return cpuFrequency;
	}
	public void setCpuFrequency(BigDecimal cpuFrequency) {
		this.cpuFrequency = cpuFrequency;
	}
	public Display getDisplay() {
		return display;
	}
	public void setDisplay(Display display) {
		this.display = display;
	}
	public String getGpu() {
		return gpu;
	}
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public Memory getMemory() {
		return memory;
	}
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Network getNetworkConnectivity() {
		return networkConnectivity;
	}
	public void setNetworkConnectivity(Network networkConnectivity) {
		this.networkConnectivity = networkConnectivity;
	}
	
	public OS getOs() {
		return os;
	}
	public void setOs(OS os) {
		this.os = os;
	}
	public List<String> getSensors() {
		sensors = getEnumMappedList(sensors, asList(Features.values()));
		return sensors;
	}

	public List<String> getSimSlotTypes() {
		simSlotTypes = getEnumMappedList(simSlotTypes, asList(SimType.values()));
		return simSlotTypes;
	}
	public Integer getSimSlotCount() {
		if (!CollectionUtils.isEmpty(simSlotTypes)) {
			simSlotCount = simSlotTypes.size();
		}
		else {
			simSlotCount = 0;
		}
		return simSlotCount;
	}
	@Override
	public String toString() {
		return "PhoneModel [battery=" + battery + ", cameras=" + cameras + ", chip=" + chip + ", connectivity="
				+ connectivity + ", cpu=" + cpu + ", cpuCores=" + cpuCores + ", cpuFrequency=" + cpuFrequency
				+ ", display=" + display + ", gpu=" + gpu + ", memory=" + memory + ", modelName=" + modelName
				+ ", networkConnectivity=" + networkConnectivity + ", os=" + os + ", sensors=" + sensors
				+ ", simSlotTypes=" + simSlotTypes + ", simSlotCount=" + getSimSlotCount() + "]";
	}
}
