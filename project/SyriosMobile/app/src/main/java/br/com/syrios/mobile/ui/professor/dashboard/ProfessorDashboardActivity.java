package br.com.syrios.mobile.ui.professor.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.databinding.ActivityProfessorDashboardBinding;
import br.com.syrios.mobile.ui.base.BaseActivity;
import br.com.syrios.mobile.ui.login.LoginProfessorActivity;
import br.com.syrios.mobile.ui.professor.ocorrencias.MinhasOcorrenciasActivity;
import br.com.syrios.mobile.ui.professor.ofertas.MinhasOfertasActivity;

public class ProfessorDashboardActivity extends BaseActivity {

    private ActivityProfessorDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfessorDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView txtEscola = findViewById(R.id.txtEscola);
        TextView txtNome = findViewById(R.id.txtNome);
        TextView menuExtra = findViewById(R.id.menuExtra);

        // 🔒 Garante login + contexto (escola+role)
        if (!br.com.syrios.mobile.core.ContextGuard.ensureLoginAndContext(this, true)) {
            return; // já foi redirecionado
        }

        SessionManager session = new SessionManager(this);

        long currentSchoolId = session.getCurrentSchoolId();
        String currentSchoolName = session.getCurrentSchoolName();
        String currentRole = session.getCurrentRole();
        String userName = session.getUserName();

        if (userName == null) userName = "";
        if (currentRole == null) currentRole = "";

        // Abreviação opcional do nome
        String userNameAbr = userName;
        String[] partesDoNome = userName.split(" ");
        if (partesDoNome.length >= 2) {
            String pNome = partesDoNome[0];
            String uNome = partesDoNome[partesDoNome.length - 1];
            userNameAbr = pNome + " " + uNome;
        }

        String currentRoleUp = currentRole.isEmpty()
                ? ""
                : currentRole.substring(0, 1).toUpperCase() + currentRole.substring(1);

        txtEscola.setText(currentSchoolName);
        txtNome.setText(userNameAbr + " (\uD83C\uDFAF " + currentRoleUp + ")");

        // Visibilidade de cards conforme papel
        if ("professor".equals(currentRole)) {
            binding.cardOfertas.setVisibility(android.view.View.VISIBLE);
            binding.cardOcorrencias.setVisibility(android.view.View.VISIBLE);
            binding.cardHistorico.setVisibility(android.view.View.VISIBLE);
        } else {
            binding.cardOfertas.setVisibility(android.view.View.GONE);
            binding.cardOcorrencias.setVisibility(android.view.View.GONE);
            binding.cardHistorico.setVisibility(android.view.View.GONE);
        }

        binding.cardOfertas.setOnClickListener(v -> {
            Intent i = new Intent(this, MinhasOfertasActivity.class);
            startActivity(i);
        });

        binding.cardOcorrencias.setOnClickListener(v -> {
            Intent i = new Intent(this, MinhasOcorrenciasActivity.class);
            startActivity(i);
        });

        binding.menuExtra.setOnClickListener(v -> {
            android.widget.PopupMenu popup = new android.widget.PopupMenu(this, v);
            popup.getMenu().add(0, 1, 0, "Trocar contexto");
            popup.getMenu().add(0, 2, 1, "Sair");

            popup.setOnMenuItemClickListener(item -> {
                SessionManager sm = new SessionManager(this);

                if (item.getItemId() == 1) {
                    // Trocar contexto → limpa apenas contexto e manda para Escolher Escola
                    sm.clearContext();
                    Intent i = new Intent(this, br.com.syrios.mobile.ui.choose.ChooseSchoolWebActivity.class);
                    startActivity(i);
                    finish();
                    return true;
                } else if (item.getItemId() == 2) {
                    // Sair → logout completo
                    sm.logout(this);
                    Intent i = new Intent(this, br.com.syrios.mobile.LauncherActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                    return true;
                }

                return false;
            });

            popup.show();
        });


        // 🔒 Proteção: precisa ter contexto válido (login + escola + role)
        if (!session.hasValidContext()) {
            Intent i = new Intent(this, LoginProfessorActivity.class);
            startActivity(i);
            finish();
            return;
        }


    }
}
