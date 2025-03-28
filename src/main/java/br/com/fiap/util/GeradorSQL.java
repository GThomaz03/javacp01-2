package br.com.fiap.util;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;

public class GeradorSQL {

    // SELECT * FROM
    public static String gerarSelect(Class<?> classe) {
        if (!classe.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("A classe não possui a anotação @Table");
        }

        String nomeTabela = classe.getAnnotation(Table.class).name();
        return "SELECT * FROM " + nomeTabela + ";";
    }

    // INSERT INTO
    public static String gerarInsert(Object objeto) {
        Class<?> classe = objeto.getClass();
        if (!classe.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("A classe não possui a anotação @Table");
        }

        String nomeTabela = classe.getAnnotation(Table.class).name();
        StringBuilder colunas = new StringBuilder();
        StringBuilder valores = new StringBuilder();

        boolean temCampos = false;

        for (Field campo : classe.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Column.class)) {
                campo.setAccessible(true);
                try {
                    String nomeColuna = campo.getAnnotation(Column.class).name();
                    Object valor = campo.get(objeto);

                    if (valor != null) {
                        colunas.append(nomeColuna).append(",");
                        valores.append(valor instanceof String ? "'" + valor + "'" : valor).append(",");
                        temCampos = true;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao acessar campo", e);
                }
            }
        }

        if (!temCampos) {
            throw new RuntimeException("Nenhum campo válido encontrado para gerar o INSERT");
        }

        if (colunas.length() > 0) {
            colunas.setLength(colunas.length() - 1);
        }

        return "INSERT INTO " + nomeTabela + " (" + colunas + ") VALUES (" + valores + ");";
    }

    //SELECT * FROM WHERE ID =
    public static String gerarSelectPorId(Class<?> classe, Object id) {
        if (!classe.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("A classe não possui a anotação @Table");
        }

        String nomeTabela = classe.getAnnotation(Table.class).name();
        String idColuna = null;

        for (Field campo : classe.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Column.class) && campo.isAnnotationPresent(javax.persistence.Id.class)) {
                campo.setAccessible(true);
                try {
                    idColuna = campo.getAnnotation(Column.class).name();
                    break;
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao acessar campo", e);
                }
            }
        }

        if (idColuna == null) {
            throw new RuntimeException("Não foi possível encontrar a chave primária");
        }

        return "SELECT * FROM " + nomeTabela + " WHERE " + idColuna + "=" + id + ";";
    }


    // UPDATE WHERE
    public static String gerarUpdate(Object objeto, Object id) {
        Class<?> classe = objeto.getClass();
        if (!classe.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("A classe não possui a anotação @Table");
        }

        String nomeTabela = classe.getAnnotation(Table.class).name();
        StringBuilder setClause = new StringBuilder();
        String idColuna = null;

        for (Field campo : classe.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Column.class)) {
                campo.setAccessible(true);
                try {
                    String nomeColuna = campo.getAnnotation(Column.class).name();
                    Object valor = campo.get(objeto);

                    if (campo.isAnnotationPresent(javax.persistence.Id.class)) {
                        idColuna = nomeColuna;
                    } else {
                        setClause.append(nomeColuna)
                                .append("=")
                                .append(valor instanceof String ? "'" + valor + "'" : valor)
                                .append(",");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao acessar campo", e);
                }
            }
        }

        setClause.setLength(setClause.length() - 1);

        if (idColuna == null) {
            throw new RuntimeException("Não foi possível encontrar o identificador do objeto");
        }

        return "UPDATE " + nomeTabela + " SET " + setClause + " WHERE " + idColuna + "=" + id + ";";
    }

    // DELETE WHERE
    public static String gerarDelete(Class<?> classe, Object id) {
        if (!classe.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("A classe não possui a anotação @Table");
        }

        String nomeTabela = classe.getAnnotation(Table.class).name();
        String idColuna = null;

        for (Field campo : classe.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Column.class) && campo.isAnnotationPresent(javax.persistence.Id.class)) {
                campo.setAccessible(true);
                try {
                    idColuna = campo.getAnnotation(Column.class).name();
                    break;
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao acessar campo", e);
                }
            }
        }

        if (idColuna == null) {
            throw new RuntimeException("Não foi possível encontrar a chave primária");
        }

        return "DELETE FROM " + nomeTabela + " WHERE " + idColuna + "=" + id + ";";
    }
}
