package com.minegusta.hubfun.util;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public class BossBarMessage {
	private String message;
	private BarColor color;
	private BarStyle style;

	public BossBarMessage(String message, BarColor color, BarStyle style) {
		this.message = message;
		this.style = style;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public BarStyle getStyle() {
		return style;
	}

	public BarColor getColor() {
		return color;
	}
}
