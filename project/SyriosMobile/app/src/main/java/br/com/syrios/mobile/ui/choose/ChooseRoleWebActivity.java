package br.com.syrios.mobile.ui.choose;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.cache.EscolasCache;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.network.dto.LoginResponse;
import br.com.syrios.mobile.ui.professor.dashboard.ProfessorDashboardActivity;

public class ChooseRoleWebActivity extends AppCompatActivity {

    private RadioGroup groupRoles;
    private Button btnEntrar;
    private Button btnVoltar;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role_web);

        groupRoles = findViewById(R.id.groupRoles);
        btnEntrar = findViewById(R.id.btnEntrarRole);
        btnVoltar = findViewById(R.id.btnVoltarRole);

        session = new SessionManager(this);

        String schoolName = getIntent().getStringExtra("school_name");
        long schoolId = getIntent().getLongExtra("school_id", -1);

        TextView subtitulo = findViewById(R.id.txtSubtituloRole);
        subtitulo.setText("Você possui múltiplos papéis na escola: " + schoolName);

        LoginResponse.EscolaRemote escola = EscolasCache.getRemoteById(schoolId);
        if (escola == null || escola.roles == null || escola.roles.isEmpty()) {
            subtitulo.setText("Nenhum papel disponível nesta escola.");
            return;
        }

        groupRoles.removeAllViews();

        for (String role : escola.roles) {
            RadioButton rb = new RadioButton(this);
            rb.setText(role);
            rb.setTextSize(18);
            rb.setPadding(8, 12, 8, 12);
            groupRoles.addView(rb);
        }

        btnEntrar.setOnClickListener(v -> {
            int checkedId = groupRoles.getCheckedRadioButtonId();
            if (checkedId == -1) return;

            RadioButton rb = findViewById(checkedId);
            String selectedRole = rb.getText().toString();

            // ✅ Define o CONTEXTO ATUAL
            session.setCurrentSchool(schoolId, schoolName);
            session.setCurrentRole(selectedRole);

            // (Opcional) atualizar info bruta de login se você ainda estiver usando internamente
            session.saveLoginSession(
                    schoolId,
                    selectedRole,
                    session.getToken()
            );

            if ("professor".equals(selectedRole)) {
                startActivity(new Intent(this, ProfessorDashboardActivity.class));
            } else {
                // No futuro: outros dashboards (pais, gestor, etc.)
                startActivity(new Intent(this, ProfessorDashboardActivity.class));
            }

            finish();
        });

        btnVoltar.setOnClickListener(v -> finish());
    }
}
