package com.project.spaceship.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseDto implements Serializable {

	private static final long serialVersionUID = 9019461721665760379L;

	protected Integer id;

}
