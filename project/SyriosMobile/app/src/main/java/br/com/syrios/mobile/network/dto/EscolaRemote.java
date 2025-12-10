package br.com.syrios.mobile.network.dto;

import java.util.List;

public class EscolaRemote {
    public Long id;
    public String nome_e;
    public String cidade;
    public String estado;
    public boolean is_master;
    public List<String> roles;   // ← NOVO, obrigatório

    // Conversão para entidade local (Room)
    public br.com.syrios.mobile.data.entities.Escola toLocal() {
        br.com.syrios.mobile.data.entities.Escola e =
                new br.com.syrios.mobile.data.entities.Escola();
        e.id = this.id;
        e.nome = this.nome_e;
        e.cidade = this.cidade;
        e.estado = this.estado;
        return e;
    }
}

