/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author fabio
 */
public class InternetUtil {

    public static String acessarURL(String sURL) {
        String resposta = null;

        BufferedReader in = null;
        try {
            URL myURL = new URL(sURL);
            URLConnection myURLConnection = myURL.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(myURLConnection.getInputStream(), Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
                sb.append('\n');
            }
            resposta = sb.toString();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        return resposta;
    }

    public static boolean acessarParaSalvarImagem(String sURL, File arquivo) {
        boolean resposta = false;

        if (sURL == null || sURL.trim().isEmpty() || arquivo == null) {
            return resposta;
        } else {
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                URL myURL = new URL(sURL);
                URLConnection myURLConnection = myURL.openConnection();

                in = new BufferedInputStream(myURLConnection.getInputStream());
                out = new BufferedOutputStream(new FileOutputStream(arquivo));
                byte[] itens = new byte[1024];
                int length = 0;
                while ((length = in.read(itens)) >= 0) {
                    out.write(itens, 0, length);
                    out.flush();
                }
                resposta = true;
            } catch (Exception e) {
                e.printStackTrace(System.err);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace(System.err);
                    }
                }
            }

            return resposta;
        }
    }

    /**
     * Acrescentar símbolo + no lugar dos espaços e separadores
     *
     * @param valor
     * @return url substituída
     */
    public static String processar(String valor) {
        String resposta = "";
        //Processa a string quando não for vazia E não for nula
        if (valor != null
                && !valor.isEmpty()) {
            String[] split = valor.trim().split("\\s");
            String s = null;
            for (int i = 0; i < split.length; i++) {
                s = split[i];
                resposta += s;
                if (i < split.length - 1) {
                    resposta += "%20";
                }
            }
        }
        return resposta;
    }

    /**
     * Converte um objeto JSON em um JsonArray
     *
     * @param sURL
     * @param JSONNome
     * @return JsonArray
     */
    public static JsonArray criaJsonArray(String sURL, String JSONNome) {
        JsonArray results = null;
        InputStream is = null;
        try {

            URL myURL = new URL(sURL);
            is = myURL.openStream();
            JsonReader jd = Json.createReader(is);
            JsonObject obj = jd.readObject();

            results = obj.getJsonArray(JSONNome);

            return results;

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return results;
    }
}
