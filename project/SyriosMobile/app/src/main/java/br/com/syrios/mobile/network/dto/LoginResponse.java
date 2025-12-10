package br.com.syrios.mobile.network.dto;

import java.util.List;

public class LoginResponse {

    public String token;

    public UsuarioRemote usuario;
    public List<EscolaRemote> escolas;   // escolas que esse usuário tem acesso
    public List<String> roles;           // roles gerais, se quiser

    public static class UsuarioRemote {
        public Long id;
        public String cpf;
        public String nome_u;
        public String nascimento;
        public int status;
    }

    public static class EscolaRemote {
        public Long id;
        public String nome_e;
        public String cidade;
        public String estado;
        public boolean is_master;
        public List<String> roles;


        // Conversão para entidade local (Room)
        public br.com.syrios.mobile.data.entities.Escola toLocal() {
            br.com.syrios.mobile.data.entities.Escola e =
                    new br.com.syrios.mobile.data.entities.Escola();
            e.id = this.id;
            e.nome = this.nome_e;      // Nome da escola
            e.cidade = this.cidade;
            e.estado = this.estado;
            return e;
        }
    }
}
