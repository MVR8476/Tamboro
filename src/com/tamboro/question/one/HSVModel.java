package com.tamboro.question.one;

public class HSVModel {
	private int hue;
	private int saturtion;
	private int value;

	public HSVModel(){

	}

public HSVModel(Integer hue, Integer saturtion, Integer value) {

		this.hue = hue;
		this.saturtion = saturtion;
		this.value = value;
	}

	/**
	 * @return the hue
	 */
	public int getHue() {
		return hue;
	}

	/**
	 * @param hue
	 *
	 */
	public void setHue(int hue) {
		this.hue = hue;
	}

	/**
	 * @return the saturtion
	 */
	public int getSaturtion() {
		return saturtion;
	}

	/**
	 * @param saturtion
	 *
	 */
	public void setSaturtion(int saturtion) {
		this.saturtion = saturtion;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *
	 */
	public void setValue(int value) {
		this.value = value;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hue;
		result = prime * result + saturtion;
		result = prime * result + value;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HSVModel other = (HSVModel) obj;
		if (hue != other.hue)
			return false;
		if (saturtion != other.saturtion)
			return false;
		if (value != other.value)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "HSVModel [hue=" + hue + ", saturtion=" + saturtion + ", value="
				+ value + "]";
	}
}
