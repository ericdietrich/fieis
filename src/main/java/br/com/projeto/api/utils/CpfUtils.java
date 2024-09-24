package br.com.projeto.api.utils;

public class CpfUtils {
    public static String formatCpf(String cpf) {
        if(cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
