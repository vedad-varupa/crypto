package com.cripto.cripto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;
import java.util.ArrayList;

@RestController
public class CriptoController {
    private CriptoService criptoService;
    public CriptoController(CriptoService criptoService) {
        this.criptoService = criptoService;
    }

    @GetMapping(value = "/rsa-algorithm/{textmessage}")
    public ArrayList<String> rsaAlgorithm(@PathVariable String textmessage) {
        return criptoService.rsaAlgorithm(textmessage);
    }
    @GetMapping("/extended-euklid-gcd/{firstNumber}/{secondNumber}")
    public GeneralContainer exteuclidGCD(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        BigInteger bigInteger_a = new BigInteger(firstNumber);
        BigInteger bigInteger_b = new BigInteger(secondNumber);
        return criptoService.exteuclidGCD(bigInteger_a, bigInteger_b);
    }

    @GetMapping(value = "/fermaov-testprostosti/{firstNumber}/{secondNumber}")
    public Boolean fermatPrimalityTest(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        BigInteger number = new BigInteger(firstNumber);
        BigInteger k = new BigInteger(secondNumber);
        return criptoService.fermatPrimalityTest(number, k);
    }

    @GetMapping(value = "/millerrabinov-test/{firstNumber}/{secondNumber}")
    public boolean millerrabinTest(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        BigInteger n = new BigInteger(firstNumber);
        BigInteger k = new BigInteger(secondNumber);
        return criptoService.millerrabinTest(n, k);
    }

    @GetMapping(value = "/big-gcd/{firstNumber}/{secondNumber}")
    public BigInteger euklidovGCD(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        BigInteger a = new BigInteger(firstNumber);
        BigInteger b = new BigInteger(secondNumber);
        return criptoService.euklidovGCD(a, b);
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

    @GetMapping(value = "/big-inverse2/{firstNumber}/{secondNumber}")
    public BigInteger findinvers(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        BigInteger a = new BigInteger(firstNumber);
        BigInteger b = new BigInteger(secondNumber);
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
