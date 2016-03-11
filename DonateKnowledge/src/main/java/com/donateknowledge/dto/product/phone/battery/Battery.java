package com.donateknowledge.dto.product.phone.battery;

import java.math.BigDecimal;

import com.donateknowledge.utils.CheapestGadgetUtils;

public class Battery {

	private BigDecimal capacity;
	private BigDecimal musicPlay;
	private boolean removable;
	private BigDecimal standBy;
	private BigDecimal talkTime;
	// Battery type
	private String technology;
	private BigDecimal videoPlay;

	public Battery() {
		super();
	}

	public Battery(String capacity, String musicPlay, boolean removable, String standBy, String talkTime,
			String technology, String videoPlay) {
		super();
		this.capacity = CheapestGadgetUtils.stringToBigDecimal(capacity);
		this.musicPlay = CheapestGadgetUtils.stringToBigDecimal(musicPlay);
		this.removable = removable;
		this.standBy = CheapestGadgetUtils.stringToBigDecimal(standBy);
		this.talkTime = CheapestGadgetUtils.stringToBigDecimal(talkTime);
		setTechnology(technology);
		this.videoPlay = CheapestGadgetUtils.stringToBigDecimal(videoPlay);
	}

	public BigDecimal getCapacity() {
		return capacity;
	}
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}
	public BigDecimal getMusicPlay() {
		return musicPlay;
	}
	public void setMusicPlay(BigDecimal musicPlay) {
		this.musicPlay = musicPlay;
	}
	public boolean isRemovable() {
		return removable;
	}
	public void setRemovable(boolean removable) {
		this.removable = removable;
	}
	public BigDecimal getStandBy() {
		return standBy;
	}
	public void setStandBy(BigDecimal standBy) {
		this.standBy = standBy;
	}
	public BigDecimal getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(BigDecimal talkTime) {
		this.talkTime = talkTime;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		BatteryType type = BatteryType.getBatteryType(technology);
		if (type != null) {
			this.technology = type.getValue();
		}
	}
	public BigDecimal getVideoPlay() {
		return videoPlay;
	}
	public void setVideoPlay(BigDecimal videoPlay) {
		this.videoPlay = videoPlay;
	}
	@Override
	public String toString() {
		return "Battery [capacity=" + capacity + ", musicPlay=" + musicPlay + ", removable=" + removable + ", standBy="
				+ standBy + ", talkTime=" + talkTime + ", technology=" + technology
				+ ", videoPlay=" + videoPlay + "]";
	}
}
