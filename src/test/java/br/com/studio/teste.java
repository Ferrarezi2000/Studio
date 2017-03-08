package br.com.studio;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class teste {

    public static void main(String[] args){
        LocalDate hoje = LocalDate.now();
        LocalDate aniversario = LocalDate.of(1986, 01, 22);
        System.out.println(hoje);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatado = formatter.format(hoje);
        System.out.println(formatado);
        Period idade = Period.between(aniversario,hoje);
        System.out.println(idade.getYears());
    }
}
