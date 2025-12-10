package br.com.syrios.mobile.data.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.syrios.mobile.data.cache.EscolasCache;
import br.com.syrios.mobile.data.db.SyriosDatabase;
import br.com.syrios.mobile.data.entities.Escola;
import br.com.syrios.mobile.data.entities.Usuario;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.network.ApiClient;
import br.com.syrios.mobile.network.api.AuthService;
import br.com.syrios.mobile.network.dto.LoginRequest;
import br.com.syrios.mobile.network.dto.LoginResponse;
import br.com.syrios.mobile.util.HashUtil;
import retrofit2.Call;
import retrofit2.Response;

public class AuthRepository {

    private final SyriosDatabase db;
    private final SessionManager sessionManager;
    private final AuthService authService;

    private Context context;

    public AuthRepository(Context context) {
        this.context = context;
        this.db = SyriosDatabase.getInstance(context);
        this.sessionManager = new SessionManager(context);
        this.authService = ApiClient.getRetrofit(sessionManager).create(AuthService.class);
    }

    /**
     * LOGIN ONLINE (primeiro acesso ou quando tiver internet)
     * - chama API
     * - se ok: guarda usuário no banco local
     * - gera senha_local_hash para login offline
     * - salva sessão no SessionManager
     */
    @Nullable
    public Usuario loginOnline(String cpf, String senha) throws IOException {
        LoginRequest request = new LoginRequest(cpf, senha);
        Call<LoginResponse> call = authService.login(request);
        Response<LoginResponse> response = call.execute();

        if (!response.isSuccessful() || response.body() == null) {
            return null;
        }

        LoginResponse body = response.body();

        // Já recebemos a lista de escolas no LoginResponse
        // Salva escolas remotas + locais automaticamente (com persistência)
        EscolasCache.saveAll(context, body.escolas);

        // Monta Usuario local a partir do response
        LoginResponse.UsuarioRemote u = body.usuario;
        if (u == null) {
            return null;
        }

        Usuario local = new Usuario();
        local.id = u.id;
        local.schoolId = (body.escolas != null && !body.escolas.isEmpty())
                ? body.escolas.get(0).id
                : null;
        local.cpf = u.cpf;
        local.nome = u.nome_u;
        local.nascimento = u.nascimento;
        local.status = u.status;
        local.senhaLocalHash = HashUtil.sha256(cpf + ":" + senha);
        local.ultimoLogin = System.currentTimeMillis();
        local.sincronizadoEm = System.currentTimeMillis();

        db.usuarioDao().insert(local);

        Long schoolId = local.schoolId;
        String role = (body.roles != null && !body.roles.isEmpty()) ? body.roles.get(0) : "professor";
    //local.id, , cpf
        sessionManager.saveLoginSession(schoolId, role, body.token);

        return local;
    }

    /**
     * LOGIN OFFLINE:
     * - Não chama API
     * - Confere cpf e senha com senha_local_hash do banco local
     */
    @Nullable
    public Usuario loginOffline(String cpf, String senha) {
        Usuario usuario = db.usuarioDao().findByCpf(cpf);
        if (usuario == null || usuario.senhaLocalHash == null) {
            return null;
        }

        String hashEntrada = HashUtil.sha256(cpf + ":" + senha);
        if (!usuario.senhaLocalHash.equals(hashEntrada)) {
            return null;
        }

        usuario.ultimoLogin = System.currentTimeMillis();
        db.usuarioDao().insert(usuario);
        //usuario.id, , cpf
        sessionManager.saveLoginSession(usuario.schoolId, "professor", null);

        return usuario;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public SyriosDatabase getDatabase() {
        return db;
    }
}
