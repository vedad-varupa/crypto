package com.cripto.cripto;

import java.io.Serializable;
import java.util.List;

public class GeneralContainer implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EuclidContainer> euclidContainers;
	private String result;

	public GeneralContainer() {
		super();
	}

	public GeneralContainer(List<EuclidContainer> euclidContainers, String result) {
		super();
		this.euclidContainers = euclidContainers;
		this.result = result;
	}

	public List<EuclidContainer> getEuclidContainers() {
		return euclidContainers;
	}

	public void setEuclidContainers(List<EuclidContainer> euclidContainers) {
		this.euclidContainers = euclidContainers;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
