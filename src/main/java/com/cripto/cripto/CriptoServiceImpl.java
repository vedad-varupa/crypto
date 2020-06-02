package com.cripto.cripto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class CriptoServiceImpl implements CriptoService {

	@Override
	public Boolean fermatPrimalityTest(BigInteger n, BigInteger k) {
		final BigInteger ONE = new BigInteger("1");
		BigInteger e = n.subtract(ONE);
		BigInteger b = k.multiply(n.subtract(new BigInteger("4")).add(ONE).add(new BigInteger("2")));
		return b.modPow(e, n).equals(ONE);
	}

	@Override
	public boolean strassenTest(long n, long iteration) {
		if (n == 0 || n == 1) {
			return false;
		}
		if (n == 2) {
			return true;
		}
		if (n % 2 == 0) {
			return false;
		}

		Random rand = new Random();
		for (int i = 0; i < iteration; i++) {
			long r = Math.abs(rand.nextLong());
			long a = r % (n - 1) + 1;
			long jacobian = (n + Jacobi(a, n)) % n;
			long mod = modPow(a, (n - 1) / 2, n);
			if (jacobian == 0 || mod != jacobian) {
				return false;
			}
		}
		return true;
	}

	public long Jacobi(long a, long b) {
		if (b <= 0 || b % 2 == 0) {
			return 0;
		}
		long j = 1L;
		if (a < 0) {
			a = -a;
			if (b % 4 == 3) {
				j = -j;
			}
		}
		while (a != 0) {
			while (a % 2 == 0) {
				a /= 2;
				if (b % 8 == 3 || b % 8 == 5) {
					j = -j;
				}
			}

			long temp = a;
			a = b;
			b = temp;

			if (a % 4 == 3 && b % 4 == 3) {
				j = -j;
			}
			a %= b;
		}
		if (b == 1) {
			return j;
		}
		return 0;
	}

	public long modPow(long a, long b, long c) {
		long res = 1;
		for (int i = 0; i < b; i++) {
			res *= a;
			res %= c;
		}
		return res % c;
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
	public Long binaryGCD(Long A, Long B) {
		return binaryGCD1(A, B);
	}

	public static long binaryGCD1(long u, long v) {

		if (v == 0) {
			return u;
		}
		if (u == 0) {
			return v;
		}

		if ((u & 1) == 0 && (v & 1) == 0) {
			return binaryGCD1(u >> 1, v >> 1) << 1;
		}

		else if ((u & 1) == 0) {
			return binaryGCD1(u >> 1, v);
		}

		else if ((v & 1) == 0) {
			return binaryGCD1(u, v >> 1);
		}

		else if (u >= v) {
			return binaryGCD1(u - v >> 1, v);
		}

		else {
			return binaryGCD1(u, v - u >> 1);
		}
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
	public GeneralContainer exteuklidovGCD(BigInteger a, BigInteger b) {
		BigInteger[] x = new BigInteger[3];
		BigInteger[] y = new BigInteger[3];
		BigInteger[] t = new BigInteger[3];

		List<EuclidContainer> listOfEuclidContainers = new ArrayList<>();
		x[0] = BigInteger.ONE;
		x[1] = BigInteger.ZERO;
		x[2] = a;
		y[0] = BigInteger.ZERO;
		y[1] = BigInteger.ONE;
		y[2] = b;

		while (y[2] != BigInteger.ZERO) {
			BigInteger q = x[2].divide(y[2]);
			t[0] = x[0].subtract(y[0].multiply(q));
			t[1] = x[1].subtract(y[1].multiply(q));
			t[2] = x[2].subtract(y[2].multiply(q));
			check(a, b, t);

			x[0] = y[0];
			x[1] = y[1];
			x[2] = y[2];
			check(a, b, x);

			y[0] = t[0];
			y[1] = t[1];
			y[2] = t[2];
			check(a, b, y);

			listOfEuclidContainers.add(new EuclidContainer(q, x[0], x[1], x[2], y[0], y[1], y[2]));

		}
		StringBuilder stringBuilder = new StringBuilder();
		// @formatter:off
		stringBuilder.append("(").append(x[0]).append(")*").append(a).append(" + ").append("(").append(x[1])
				.append(")*").append(b).append(" = ").append(x[2]);
		// @formatter:on

		return new GeneralContainer(listOfEuclidContainers, stringBuilder.toString());
	}

	public static void check(BigInteger a, BigInteger b, BigInteger[] w) {
		if (a.multiply(w[0]).add(b.multiply(w[1])) != w[2]) {
			System.out.println("*** Provjera nije uspjela: " + a + " " + b);
			
		}
	}

	@Override
	public ArrayList<String> rsaAlgoritam(String a) {
		ArrayList<String> lista = new ArrayList<>();
		BigInteger p = largePrime(512);
		BigInteger q = largePrime(512);

	
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

	
		String message = a;
		
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
			return new BigInteger[] { a, BigInteger.ONE, BigInteger.ZERO }; // { a, 1, 0 }
		}
		BigInteger[] vals = extEuclid(b, a.mod(b));
		BigInteger d = vals[0];
		BigInteger p = vals[2];
		BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));
		return new BigInteger[] { d, p, q };
	}

	public static BigInteger genE(BigInteger phi) {
		Random rand = new Random();
		BigInteger e = new BigInteger(1024, rand);
		do {
			e = new BigInteger(1024, rand);
			while (e.min(phi).equals(phi)) { 
				e = new BigInteger(1024, rand);
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
	public BigInteger findmoduloMultiplication(BigInteger a, BigInteger b, BigInteger m) {
		BigInteger multiplic = a.multiply(b).mod(m);
		return multiplic;

	}

	@Override
	public BigInteger findmodulodivision(BigInteger a, BigInteger b, BigInteger m) {
		if(findinvers(b, m) == null) {
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
