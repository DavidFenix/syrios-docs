package br.com.syrios.mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.databinding.ActivityLauncherBinding;
import br.com.syrios.mobile.ui.choose.ChooseRoleWebActivity;
import br.com.syrios.mobile.ui.choose.ChooseSchoolWebActivity;
import br.com.syrios.mobile.ui.login.LoginProfessorActivity;
import br.com.syrios.mobile.ui.professor.dashboard.ProfessorDashboardActivity;

public class LauncherActivity extends AppCompatActivity {

    private ActivityLauncherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager session = new SessionManager(this);

        // Caso o usuário esteja totalmente logado + com contexto
        if (session.hasValidContext()) {
            if ("professor".equals(session.getCurrentRole())) {
                startActivity(new Intent(this, ProfessorDashboardActivity.class));
                finish();
                return;
            }
        }

        // Caso esteja logado mas sem contexto → escolha de escola
        if (session.isLoggedIn()) {
            startActivity(new Intent(this, ChooseSchoolWebActivity.class));
            finish();
            return;
        }

//        // Ninguém logado → tela inicial de login
//        startActivity(new Intent(this, LauncherActivity.class));
//        finish();


//        // ================================
//        //   FLUXO INTELIGENTE NOVO
//        // ================================
//        if (session.isLoggedIn()) {
//
//            Long schoolId = session.getCurrentSchoolId();
//            String role   = session.getCurrentRole();
//
//            // Se o usuário já tem contexto COMPLETO → vai direto ao dashboard
//            if (schoolId != null && schoolId != -1 &&
//                    role != null && !role.isEmpty()) {
//
//                if (role.equals("professor")) {
//                    startActivity(new Intent(this, ProfessorDashboardActivity.class));
//                }
//                // no futuro: aluno, responsável, gestor, etc...
//
//                finish();
//                return;
//            }
//
//            // Sem escola escolhida → ChooseSchool
//            if (schoolId == null || schoolId == -1) {
//                startActivity(new Intent(this, ChooseSchoolWebActivity.class));
//                finish();
//                return;
//            }
//
//            // Sem papel escolhido → ChooseRole
//            if (role == null || role.isEmpty()) {
//                Intent i = new Intent(this, ChooseRoleWebActivity.class);
//                i.putExtra("school_id", session.getCurrentSchoolId());
//                i.putExtra("school_name", session.getCurrentSchoolName());
//                startActivity(i);
//                finish();
//                return;
//            }
//        }

        // Caso não esteja logado ou faltou contexto → mostra a tela normal
        binding = ActivityLauncherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnProfessor.setOnClickListener(v ->
                startActivity(new Intent(this, LoginProfessorActivity.class))
        );

        // Futuro:
        // binding.btnAluno.setOnClickListener(...)
        // binding.btnResponsavel.setOnClickListener(...)
    }
}
