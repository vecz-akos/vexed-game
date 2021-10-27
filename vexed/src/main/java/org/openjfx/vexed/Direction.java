package org.openjfx.vexed;

public enum Direction {
	
	                 UP(1, "up"),
	LEFT(4, "left"), NOMOVE(0, "nomove"), RIGHT(2, "right"),
	                 DOWN(3, "down");
	
	private int directionCode;
	private String name;
	
	Direction(int directionCode, String name) {
		this.directionCode = directionCode;
		this.name = name;
	}
	
	public int getDirCode() {
		return directionCode;
	}
}
