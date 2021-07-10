package com.cripto.cripto;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public interface CriptoService {
	public GeneralContainer exteuclidGCD(BigInteger firstNumber, BigInteger secondNumber);

	public ArrayList<String> rsaAlgorithm(String textmessage);

	public Boolean fermatPrimalityTest(BigInteger number, BigInteger k);

	public boolean millerrabinTest(BigInteger n, BigInteger k);

	public BigInteger euklidovGCD(BigInteger a, BigInteger b);

	public BigInteger findmoduloMultiplication(BigInteger a, BigInteger b, BigInteger m);

	public BigInteger findmodulodivision(BigInteger a, BigInteger b, BigInteger m);

	public BigInteger findmoduloexponenation(BigInteger a, BigInteger b, BigInteger m);

	public BigInteger findsquereandmultiply(BigInteger xmessage, BigInteger k, BigInteger modulo);

	public BigInteger findinvers(BigInteger x, BigInteger y);

}
