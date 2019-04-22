package com.revature.caliber.beans;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.NATURAL)
public enum CategoryOwner implements Serializable{
	@JsonProperty("training")
	TRAINING,
	@JsonProperty("panel")
	PANEL
}