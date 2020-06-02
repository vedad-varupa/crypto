package com.cripto.cripto;

import java.io.Serializable;
import java.math.BigInteger;

public class EuclidContainer implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigInteger q;
	private BigInteger x0;
	private BigInteger x1;
	private BigInteger x2;
	private BigInteger y0;
	private BigInteger y1;
	private BigInteger y2;

	public EuclidContainer() {
		super();
	}

	public EuclidContainer(BigInteger q, BigInteger x0, BigInteger x1, BigInteger x2, BigInteger y0, BigInteger y1,
			BigInteger y2) {
		super();
		this.q = q;
		this.x0 = x0;
		this.x1 = x1;
		this.x2 = x2;
		this.y0 = y0;
		this.y1 = y1;
		this.y2 = y2;
	}

	public BigInteger getQ() {
		return q;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}

	public BigInteger getX0() {
		return x0;
	}

	public void setX0(BigInteger x0) {
		this.x0 = x0;
	}

	public BigInteger getX1() {
		return x1;
	}

	public void setX1(BigInteger x1) {
		this.x1 = x1;
	}

	public BigInteger getX2() {
		return x2;
	}

	public void setX2(BigInteger x2) {
		this.x2 = x2;
	}

	public BigInteger getY0() {
		return y0;
	}

	public void setY0(BigInteger y0) {
		this.y0 = y0;
	}

	public BigInteger getY1() {
		return y1;
	}

	public void setY1(BigInteger y1) {
		this.y1 = y1;
	}

	public BigInteger getY2() {
		return y2;
	}

	public void setY2(BigInteger y2) {
		this.y2 = y2;
	}

}
