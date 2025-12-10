package br.com.syrios.mobile.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Representa o usuário no banco local do aplicativo.
 *
 * IMPORTANTE:
 * - Esta estrutura NÃO usa todos os campos do servidor.
 * - Não armazena senha_hash do servidor por segurança.
 * - Usa senha_local_hash para validação offline.
 * - school_id é obrigatório para multi-escola.
 */
@Entity(
        tableName = "usuario",
        indices = {
                @Index(value = {"cpf", "school_id"}, unique = false),
                @Index(value = {"school_id"})
        }
)
public class Usuario {

    @PrimaryKey
    @NonNull
    public Long id;                // Mesmo ID do servidor (syrios_usuario.id)

    @ColumnInfo(name = "school_id")
    public Long schoolId;          // Escola que este usuário pertence

    @ColumnInfo(name = "cpf")
    @NonNull
    public String cpf;             // Login principal

    @ColumnInfo(name = "nome_u")
    public String nome;            // Nome do usuário

    @ColumnInfo(name = "nascimento")
    public String nascimento;      // YYYY-MM-DD (string para evitar problemas com parsing)

    @ColumnInfo(name = "status")
    public Integer status;         // 1 = ativo, 0 = inativo

    // -------- AUTENTICAÇÃO LOCAL --------

    @ColumnInfo(name = "senha_local_hash")
    public String senhaLocalHash;  // Hash gerado pelo app para login offline

    @ColumnInfo(name = "ultimo_login")
    public Long ultimoLogin;       // timestamp (opcional)

    // Se no futuro o app guardar foto:
    @ColumnInfo(name = "foto_url")
    public String fotoUrl;         // Opcional (servidor envia se existir)

    // -------- CAMPOS PARA MULTI-ROLE --------

    @ColumnInfo(name = "roles_json")
    public String rolesJson;       // Ex.: ["professor","gestor"]

    // -------- CONTROLE DE SINCRONIZAÇÃO --------

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;    // timestamp da última atualização deste usuário

    // Construtor vazio exigido pelo Room
    public Usuario() {}
}
