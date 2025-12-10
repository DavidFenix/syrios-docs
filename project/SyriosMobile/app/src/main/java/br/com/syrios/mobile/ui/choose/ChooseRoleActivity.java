package br.com.syrios.mobile.ui.choose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import br.com.syrios.mobile.data.cache.EscolasCache;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.databinding.ActivityChooseRoleBinding;
import br.com.syrios.mobile.network.dto.LoginResponse;
import br.com.syrios.mobile.ui.choose.adapters.RoleAdapter;
import br.com.syrios.mobile.ui.professor.dashboard.ProfessorDashboardActivity;

public class ChooseRoleActivity extends AppCompatActivity {

    private ActivityChooseRoleBinding binding;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseRoleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Long schoolId = getIntent().getLongExtra("school_id", -1);

        LoginResponse.EscolaRemote escola = EscolasCache.getRemoteById(schoolId);

        List<String> roles = escola.roles;


//        session = new SessionManager(this);
//
//        // Carregar roles vindo da sessão
//        List<String> roles = session.getRolesList(); // vamos criar esse método já já

        setupRecycler(roles);
    }

    private void setupRecycler(List<String> roles) {
        RoleAdapter adapter = new RoleAdapter(roles, role -> {
            // salvar role escolhido
            session.setCurrentRole(role);

            // Se for professor → para o Dashboard do professor
            if (role.equalsIgnoreCase("professor")) {
                startActivity(new Intent(this, ProfessorDashboardActivity.class));
            }

            // no futuro aqui virá:
            // if (role.equals("diretor")) → tela própria
            // if (role.equals("aluno")) → histórico pessoal
            // if (role.equals("responsavel")) → histórico de filhos

            finish();
        });

        binding.recyclerRoles.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerRoles.setAdapter(adapter);
    }
}
