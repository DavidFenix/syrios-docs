package br.com.syrios.mobile.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import br.com.syrios.mobile.data.api.ApiClient;
import br.com.syrios.mobile.data.api.ApiService;
import br.com.syrios.mobile.data.cache.EscolasCache;
import br.com.syrios.mobile.databinding.ActivityLoginProfessorBinding;
import br.com.syrios.mobile.network.dto.LoginResponse;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.ui.choose.ChooseSchoolWebActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginProfessorActivity extends AppCompatActivity {

    private ActivityLoginProfessorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnEntrar.setOnClickListener(v -> fazerLogin());
    }

    private void fazerLogin() {
        String usuario = binding.edtUsuario.getText().toString().trim();
        String senha = binding.edtSenha.getText().toString().trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Informe usuário e senha", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> body = new HashMap<>();
        body.put("cpf", usuario);
        body.put("senha", senha);

        ApiService api = ApiClient.getService();
        api.login(body).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    LoginResponse res = response.body();

                    SessionManager sm = new SessionManager(LoginProfessorActivity.this);

                    // Roles gerais (se quiser usar depois)
                    //sm.saveRoles(res.roles);

                    // ID do usuário
                    Long userId = (res.usuario != null) ? res.usuario.id : null;

                    // Primeira escola (apenas como informação bruta; contexto real será escolhido depois)
                    Long firstSchoolId = (res.escolas != null && !res.escolas.isEmpty())
                            ? res.escolas.get(0).id
                            : null;

                    // Papel "principal" (apenas informação bruta)
                    String firstRole = (res.roles != null && !res.roles.isEmpty())
                            ? res.roles.get(0)
                            : "professor";

                    // Salva sessão de login (token + info bruta se seu SessionManager usar)
                    sm.saveLoginSession(
                            firstSchoolId,
                            firstRole,
                            res.token
                    );

                    // Salva informações do usuário
                    sm.saveUserInfo(
                            userId,
                            res.usuario.nome_u,
                            res.usuario.nascimento,
                            res.usuario.status,
                            res.usuario.cpf
                    );

                    // Salva TODAS as escolas no cache (remotas)
                    EscolasCache.saveAll(LoginProfessorActivity.this, res.escolas);

                    // Vai para tela de escolha de escola
                    Intent i = new Intent(LoginProfessorActivity.this, ChooseSchoolWebActivity.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(LoginProfessorActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginProfessorActivity.this, "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
