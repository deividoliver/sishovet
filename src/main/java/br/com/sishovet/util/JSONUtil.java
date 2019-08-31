/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author bpmlab
 */
public class JSONUtil {

    public static <T> String toString(final T o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException ex) {
            System.out.println("JsonUtil.toString: " + o);
            ex.printStackTrace(System.err);
            return null;
        }
    }

    public static <T> T fromString(final String s, final Class<T> classe) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(s, classe);
        } catch (IOException e) {
            System.out.println("JsonUtil.fromString: " + s);
            e.printStackTrace(System.err);
            return null;
        }
    }
}
