package br.com.syrios.mobile.ui.role;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.cache.EscolasCache;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.network.dto.LoginResponse;
import br.com.syrios.mobile.ui.professor.dashboard.ProfessorDashboardActivity;

public class ChooseRoleActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        sessionManager = new SessionManager(this);

        //List<String> roles = sessionManager.getRolesList(); // implementaremos agora

        Long chosenSchoolId = getIntent().getLongExtra("school_id", -1);

        List<LoginResponse.EscolaRemote> escolasRemotas = EscolasCache.getRemote();

        List<String> roles = new ArrayList<>();

        for (LoginResponse.EscolaRemote escola : escolasRemotas) {
            if (escola.id.equals(chosenSchoolId)) {
                roles = escola.roles; // agora vem da API
                break;
            }
        }

        RecyclerView recycler = findViewById(R.id.recyclerRoles);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new RoleAdapter(roles, role -> {
            sessionManager.setCurrentRole(role);
            openDashboard();
        }));
    }

    private void openDashboard() {
        Intent i = new Intent(this, ProfessorDashboardActivity.class);
        startActivity(i);
        finish();
    }
}
