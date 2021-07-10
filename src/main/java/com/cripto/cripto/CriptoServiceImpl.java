package com.cripto.cripto;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class CriptoServiceImpl implements CriptoService {
	@Override
	public GeneralContainer exteuclidGCD(BigInteger firstNumber, BigInteger secondNumber) {
		BigInteger[] x = new BigInteger[3];
		BigInteger[] y = new BigInteger[3];
		BigInteger[] t = new BigInteger[3];
		List<EuclidContainer> listOfEuclidContainers = new ArrayList<>();
		x[0] = BigInteger.ONE;
		x[1] = BigInteger.ZERO;
		x[2] = firstNumber;
		y[0] = BigInteger.ZERO;
		y[1] = BigInteger.ONE;
		y[2] = secondNumber;
		while (y[2] != BigInteger.ZERO) {
			BigInteger q = x[2].divide(y[2]);
			t[0] = x[0].subtract(y[0].multiply(q));
			t[1] = x[1].subtract(y[1].multiply(q));
			t[2] = x[2].subtract(y[2].multiply(q));
			check(firstNumber, secondNumber, t);
			x[0] = y[0];
			x[1] = y[1];
			x[2] = y[2];
			check(firstNumber, secondNumber, x);
			y[0] = t[0];
			y[1] = t[1];
			y[2] = t[2];
			check(firstNumber, secondNumber, y);
			listOfEuclidContainers.add(new EuclidContainer(q, x[0], x[1], x[2], y[0], y[1], y[2]));
		}
		StringBuilder stringBuilder = new StringBuilder();
		// @formatter:off
		stringBuilder.append("(").append(x[0]).append(")*").append(firstNumber).append(" + ").append("(").append(x[1])
				.append(")*").append(secondNumber).append(" = ").append(x[2]);
		// @formatter:on
		return new GeneralContainer(listOfEuclidContainers, stringBuilder.toString());
	}

	public static void check(BigInteger a, BigInteger b, BigInteger[] w) {
		if (a.multiply(w[0]).add(b.multiply(w[1])) != w[2]) {
			System.out.println("*** Provjera nije uspjela: " + a + " " + b);
		}
	}
	@Override
	public ArrayList<String> rsaAlgorithm(String textmessage) {
		ArrayList<String> lista = new ArrayList<>();
		BigInteger p = largePrime(2048);
		BigInteger q = largePrime(2048);

		BigInteger n = n(p, q);
		BigInteger phi = getPhi(p, q);
		BigInteger e = genE(phi);
		BigInteger d = extEuclid(e, phi)[1];

		System.out.println("p: " + p);
		System.out.println("q: " + q);
		System.out.println("n: " + n);
		System.out.println("Phi: " + phi);
		System.out.println("e: " + e);
		System.out.println("d: " + d);

		lista.add("p: " + p);
		lista.add("q: " + q);
		lista.add("n: " + p);
		lista.add("Phi: " + p);
		lista.add("e: " + p);
		lista.add("d: " + p);
		String message = textmessage;

		BigInteger cipherMessage = stringCipher(message);
		BigInteger encrypted = encrypt(cipherMessage, e, n);
		BigInteger decrypted = decrypt(encrypted, d, n);
		String restoredMessage = cipherToString(decrypted);

		System.out.println("Original message\r\n" + ": " + message);
		System.out.println("Hash: " + cipherMessage);
		System.out.println("Encrypted: " + encrypted);
		System.out.println("Decrypted: " + decrypted);
		System.out.println("Restored: " + restoredMessage);
		lista.add("Original message\r\n" + ": " + message);
		lista.add("Hash: " + cipherMessage);
		lista.add("Encrypted: " + encrypted);
		lista.add("Decrypted: " + decrypted);
		lista.add("Restored: " + restoredMessage);
		return lista;
	}
	public static BigInteger stringCipher(String message) {
		message = message.toUpperCase();
		String cipherString = "";
		int i = 0;
		while (i < message.length()) {
			int ch = message.charAt(i);
			cipherString = cipherString + ch;
			i++;
		}
		BigInteger cipherBig = new BigInteger(String.valueOf(cipherString));
		return cipherBig;
	}
	public static String cipherToString(BigInteger message) {
		String cipherString = message.toString();
		String output = "";
		int i = 0;
		while (i < cipherString.length()) {
			int temp = Integer.parseInt(cipherString.substring(i, i + 2));
			char ch = (char) temp;
			output = output + ch;
			i = i + 2;
		}
		return output;
	}

	public static BigInteger getPhi(BigInteger p, BigInteger q) {
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		return phi;
	}
	public static BigInteger largePrime(int bits) {
		Random randomInteger = new Random();
		BigInteger largePrime = BigInteger.probablePrime(bits, randomInteger);
		return largePrime;
	}
	public static BigInteger gcd(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO)) {
			return a;
		} else {
			return gcd(b, a.mod(b));
		}
	}
	public static BigInteger[] extEuclid(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO)) {
			return new BigInteger[]{a, BigInteger.ONE, BigInteger.ZERO}; // { a, 1, 0 }
		}
		BigInteger[] vals = extEuclid(b, a.mod(b));
		BigInteger d = vals[0];
		BigInteger p = vals[2];
		BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));
		return new BigInteger[]{d, p, q};
	}
	public static BigInteger genE(BigInteger phi) {
		Random rand = new Random();
		BigInteger e = new BigInteger(2048, rand);
		do {
			e = new BigInteger(2048, rand);
			while (e.min(phi).equals(phi)) {
				e = new BigInteger(2048, rand);
			}
		} while (!gcd(e, phi).equals(BigInteger.ONE));
		return e;
	}
	public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
		return message.modPow(e, n);
	}
	public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {
		return message.modPow(d, n);
	}
	public static BigInteger n(BigInteger p, BigInteger q) {
		return p.multiply(q);
	}

	@Override
	public Boolean fermatPrimalityTest(BigInteger n, BigInteger k) {
		final BigInteger ONE = new BigInteger("1");
		BigInteger e = n.subtract(ONE);
		BigInteger b = k.multiply(n.subtract(new BigInteger("4")).add(ONE).add(new BigInteger("2")));
		return b.modPow(e, n).equals(ONE);
	}

	@Override
	public boolean millerrabinTest(BigInteger n, BigInteger k) {

		BigInteger n_minus_one = n.subtract(BigInteger.ONE);

		BigInteger d = n_minus_one;
		int s = d.getLowestSetBit();
		d = d.shiftRight(s);

		BigInteger x = k.modPow(d, n);
		if (x.equals(BigInteger.ONE)) {
			return true;
		}
		for (int i = 0; i < s - 1; i++) {
			if (x.equals(n_minus_one)) {
				return true;
			}
			x = x.multiply(x).mod(n);
		}
		if (x.equals(n_minus_one)) {
			return true;
		}
		return false;

	}

	public long mulMod(long a, long b, long mod) {
		return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
	}


	@Override
	public BigInteger euklidovGCD(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO)) {
			return a;
		} else {
			return euklidovGCD(b, a.mod(b));
		}
	}

	@Override
	public BigInteger findmoduloMultiplication(BigInteger a, BigInteger b, BigInteger m) {
		BigInteger multiplic = a.multiply(b).mod(m);
		return multiplic;

	}

	@Override
	public BigInteger findmodulodivision(BigInteger a, BigInteger b, BigInteger m) {
		if (findinvers(b, m) == null) {
			return null;
		}
		BigInteger result = a.multiply(findinvers(b, m)).mod(m);

		return result;
	}

	@Override
	public BigInteger findinvers(BigInteger x, BigInteger y) {
		if (x.gcd(y).equals(BigInteger.ONE)) {
			return x.modInverse(y);
		}
		return null;
	}

	@Override
	public BigInteger findmoduloexponenation(BigInteger a, BigInteger b, BigInteger m) {
		BigInteger exp = a.modPow(b, m);
		return exp;
	}

	@Override
	public BigInteger findsquereandmultiply(BigInteger xmessage, BigInteger k, BigInteger modulo) {
		BigInteger nula = new BigInteger("0");
		BigInteger dva = new BigInteger("2");
		BigInteger s = new BigInteger("0");
		BigInteger jedan = new BigInteger("1");
		BigInteger y = new BigInteger("1");

		List<BigInteger> lista = new ArrayList();
		while (k.compareTo(nula) > 0) {
			lista.add(k.mod(dva));
			k = k.divide(dva);
		}

		for (BigInteger num : lista) {
			s.add(jedan);
		}

		for (int i = 0, j = lista.size() - 1; i < j; i++) {
			lista.add(i, lista.remove(j));
		}

		for (BigInteger num : lista) {
			System.out.println(num);
			y = (y.multiply(y)).mod(modulo);
			if (num.equals(jedan)) {
				y = (y.multiply(xmessage)).mod(modulo);
			}
		}
		return y;
	}
}
