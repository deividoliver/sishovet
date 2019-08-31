/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.util;

import br.com.sishovet.entidade.tipo.TipoPerfilUsuario;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author GreenHand
 */
public class SisHovetUtil {
    
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static String encriptarSHA256(String senha) {
        ShaPasswordEncoder sha = new ShaPasswordEncoder(256);
        String senhaCripto = sha.encodePassword(senha, null);
        return senhaCripto;
    }

    public static String rederizaMenu(char valor) {

        if (valor == TipoPerfilUsuario.ADMINISTRADOR.getValor()) {
            return "../admin/menu.xhtml";
        } else if (valor == TipoPerfilUsuario.MEDICO.getValor()) {
            return "../medico/menu.xhtml";
        } else {
            return "../secretaria/menu.xhtml";
        }
    }

    public static String retornaNomePerfil(char valor) {

        if (valor == TipoPerfilUsuario.ADMINISTRADOR.getValor()) {
            return TipoPerfilUsuario.ADMINISTRADOR.getDescricao();
        } else if (valor == TipoPerfilUsuario.MEDICO.getValor()) {
            return TipoPerfilUsuario.MEDICO.getDescricao();
        } else {
            return TipoPerfilUsuario.SECRETARIA.getDescricao();
        }
    }

    public static Date stringToDate(String texto) {
        Date resposta = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            resposta = formato.parse(texto);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return resposta;
    }

    public static String removeSimbolosPontuacoes(String texto) {
        if (texto == null) {
            return null;
        } else {
            String txt = Normalizer.normalize(texto, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{Punct}+");
            String resposta = pattern.matcher(txt).replaceAll("");
            return resposta;
        }
    }

    public static boolean validarEmail(String email) {
        String EMAIL_PATTERN
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    
    public static String formatarData(Date data, boolean dataEHora) {
        if (data == null) {
            return "";
        } else {
            String padrao = "dd/MM/yyyy";
            if (dataEHora) {
                padrao = "dd/MM/yyyy - HH:mm";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(padrao);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
            return sdf.format(data);
        }
    }
    
    public static boolean isCPFouCNPJ(String CPFouCNPJ) {
        CPFouCNPJ = removeSimbolosPontuacoes(CPFouCNPJ);
        if ((CPFouCNPJ == null) || (CPFouCNPJ.length() < 11) || (CPFouCNPJ.length() > 14)) {
            return false;
        } else if (CPFouCNPJ.length() == 11) {

            Integer digito1 = calcularDigito(CPFouCNPJ.substring(0, 9), pesoCPF);
            Integer digito2 = calcularDigito(CPFouCNPJ.substring(0, 9) + digito1, pesoCPF);
            return CPFouCNPJ.equals(CPFouCNPJ.substring(0, 9) + digito1.toString() + digito2.toString());

        } else if (CPFouCNPJ.length() == 14) {

            Integer digito1 = calcularDigito(CPFouCNPJ.substring(0, 12), pesoCNPJ);
            Integer digito2 = calcularDigito(CPFouCNPJ.substring(0, 12) + digito1, pesoCNPJ);
            return CPFouCNPJ.equals(CPFouCNPJ.substring(0, 12) + digito1.toString() + digito2.toString());

        } else {
            return false;
        }
    }
    
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
}
