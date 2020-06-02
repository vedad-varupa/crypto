package com.cripto.cripto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriptoController {

	@Autowired
	private CriptoService criptoService;

	@GetMapping(value = "/fermaov-testprostosti/{x}/{y}")
	public Boolean fermatPrimalityTest(@PathVariable String x, @PathVariable String y) {
		BigInteger number = new BigInteger(x);
	    BigInteger k = new BigInteger(y);
		return criptoService.fermatPrimalityTest(number,k);
	}

	@GetMapping(value = "/strassenov-test/{num}/{k}")
	public boolean strassenTest(@PathVariable long num, @PathVariable long k) {
        return criptoService.strassenTest(num, k);
	}


	@GetMapping(value = "/millerrabinov-test/{x}/{y}")
	public boolean millerrabinTest(@PathVariable String x, @PathVariable String y) {
		BigInteger n = new BigInteger(x);
	    BigInteger k = new BigInteger(y);
		return criptoService.millerrabinTest(n, k);
	}

	@GetMapping(value = "/binary-gcd/{a}/{b}")
	public Long binaryGCD(@PathVariable Long a, @PathVariable Long b) {
		return criptoService.binaryGCD(a, b);
	}

	@GetMapping(value = "/big-gcd/{x}/{y}")
	public BigInteger euklidovGCD(@PathVariable String x, @PathVariable String y) {
		BigInteger a = new BigInteger(x);
	    BigInteger b = new BigInteger(y);
		return criptoService.euklidovGCD(a, b);
	}

	@GetMapping(value = "/extended-euklid-gcd/{x}/{y}")
	public GeneralContainer exteuklidovGCD(@PathVariable String x, @PathVariable String y) {
		BigInteger a = new BigInteger(x);
	    BigInteger b = new BigInteger(y);
		return criptoService.exteuklidovGCD(a, b);
	}

	@GetMapping(value = "/rsa-algoritam/{a}")
	public ArrayList<String> rsaAlgortiam(@PathVariable String a) {
		return criptoService.rsaAlgoritam(a);
	}

	@GetMapping(value = "/big-multiplication/{x}/{y}/{z}")
	public BigInteger findmoduloMultiplication(@PathVariable String x, @PathVariable String y, @PathVariable String z) {
           BigInteger a = new BigInteger(x);
	       BigInteger b = new BigInteger(y);
	       BigInteger m = new BigInteger(z);
		return criptoService.findmoduloMultiplication(a, b, m);
	}

	@GetMapping(value = "/big-d/{x}/{y}/{z}")
	public BigInteger findmodulodivision(@PathVariable String x, @PathVariable String y, @PathVariable String z) {
		   BigInteger a = new BigInteger(x);
	       BigInteger b = new BigInteger(y);
	       BigInteger m = new BigInteger(z);
		return criptoService.findmodulodivision(a, b, m);
	}

	@GetMapping(value = "/big-inverse2/{x}/{y}")
	public BigInteger findinvers(@PathVariable String x, @PathVariable String y) {
		 BigInteger a = new BigInteger(x);
	     BigInteger b = new BigInteger(y);
		return criptoService.findinvers(a, b);
	}

	@GetMapping(value = "/big-exp/{x}/{y}/{z}")
	public BigInteger findmoduloexponenation(@PathVariable String x, @PathVariable String y, @PathVariable String z) {
		BigInteger a = new BigInteger(x);
		BigInteger b = new BigInteger(y);
		BigInteger m = new BigInteger(z);
		return criptoService.findmoduloexponenation(a, b, m);
	}

	@GetMapping(value = "/kvadriraj/{x}/{y}/{z}")
	public BigInteger findsquereandmultiply(@PathVariable String x, @PathVariable String y, @PathVariable String z) {
		BigInteger xmessage = new BigInteger(x);
		BigInteger k = new BigInteger(y);
		BigInteger modulo = new BigInteger(z);
		return criptoService.findsquereandmultiply(xmessage, k, modulo);
	}
}
