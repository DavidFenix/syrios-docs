package br.com.syrios.mobile.network.dto;

public class LoginRequest {
    public String cpf;
    public String senha;

    public LoginRequest(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
}
