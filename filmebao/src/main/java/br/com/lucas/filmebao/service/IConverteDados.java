package br.com.lucas.filmebao.service;

public interface IConverteDados {
    <T> T obterDados (String json, Class <T> classe); 
}
