package com.donateknowledge.dto.product.phone;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true, 
value={"cardSlotAvailable", "internalStorageAvailable"})
public class Memory {

	private boolean cardSlotAvailable;
	private List<BigDecimal> cardSlotExpendable;
	private List<BigDecimal> internalStorage;
	private boolean internalStorageAvailable;
	private BigDecimal ram;
	private BigDecimal rom;

	public boolean isCardSlotAvailable() {
		if (CollectionUtils.isEmpty(cardSlotExpendable)) {
			cardSlotAvailable = false;
		}
		else {
			cardSlotAvailable = true;
		}
		return cardSlotAvailable;
	}
	public List<BigDecimal> getCardSlotExpendable() {
		if (cardSlotExpendable == null) {
			cardSlotExpendable = new ArrayList<>();
		}
		return cardSlotExpendable;
	}
	public List<BigDecimal> getInternalStorage() {
		if (internalStorage == null) {
			internalStorage = new ArrayList<>();
		}
		return internalStorage;
	}
	public boolean isInternalStorageAvailable() {
		if (CollectionUtils.isEmpty(internalStorage)) {
			internalStorageAvailable = false;
		}
		else {
			internalStorageAvailable = true;
		}
		return internalStorageAvailable;
	}
	public BigDecimal getRam() {
		return ram;
	}
	public void setRam(BigDecimal ram) {
		this.ram = ram;
	}
	public BigDecimal getRom() {
		return rom;
	}
	public void setRom(BigDecimal rom) {
		this.rom = rom;
	}
	@Override
	public String toString() {
		return "Memory [cardSlotAvailable=" + isCardSlotAvailable() + ", cardSlotExpendable=" + cardSlotExpendable
				+ ", internalStorage=" + internalStorage + ", internalStorageAvailable=" + isInternalStorageAvailable()
				+ ", ram=" + ram + ", rom=" + rom + "]";
	}
}
