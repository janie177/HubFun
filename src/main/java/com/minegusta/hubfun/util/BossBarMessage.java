package com.minegusta.hubfun.util;

import org.inventivetalent.bossbar.BossBarAPI;

public class BossBarMessage {
	private String message;
	private BossBarAPI.Color color;
	private BossBarAPI.Style style;

	public BossBarMessage(String message, BossBarAPI.Color color, BossBarAPI.Style style) {
		this.message = message;
		this.style = style;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public BossBarAPI.Style getStyle() {
		return style;
	}

	public BossBarAPI.Color getColor() {
		return color;
	}
}
