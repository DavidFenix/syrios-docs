package br.com.syrios.mobile.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import br.com.syrios.mobile.LauncherActivity;
import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.ui.choose.ChooseSchoolWebActivity;
import br.com.syrios.mobile.ui.professor.dashboard.ProfessorDashboardActivity;
import br.com.syrios.mobile.ui.professor.ocorrencias.MinhasOcorrenciasActivity;
import br.com.syrios.mobile.ui.professor.ofertas.MinhasOfertasActivity;

import br.com.syrios.mobile.ui.professor.dashboard.ProfessorDashboardActivity;
import br.com.syrios.mobile.ui.professor.ofertas.MinhasOfertasActivity;
import br.com.syrios.mobile.ui.professor.ocorrencias.MinhasOcorrenciasActivity;


public abstract class BaseActivity extends AppCompatActivity {

    protected SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base);

        session = new SessionManager(this);

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

        // Preenche header
        TextView txtEscola = findViewById(R.id.txtEscola);
        TextView txtNome = findViewById(R.id.txtNome);

        txtEscola.setText(session.getCurrentSchoolName());
        txtNome.setText(session.getUserName() + " (" + session.getCurrentRole() + ")");
        txtNome.setText(userNameAbr + " (\uD83C\uDFAF " + currentRoleUp + ")");

        // Configura menu
        setupMenu();
    }

    protected void setContentLayout(int layoutRes) {
        FrameLayout container = findViewById(R.id.contentContainer);
        LayoutInflater.from(this).inflate(layoutRes, container, true);
    }

    protected void setupBottomMenu() {

        TextView menuHome = findViewById(R.id.menuHome);
        TextView menuOcorrencias = findViewById(R.id.menuOcorrencias);
        TextView menuOfertas = findViewById(R.id.menuOfertas);
        TextView menuExtra = findViewById(R.id.menuExtra);

        //============== HOME ===============
        menuHome.setOnClickListener(v -> {
            if (!(this instanceof ProfessorDashboardActivity)) {
                startActivity(new Intent(this, ProfessorDashboardActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        //============== OCORRÊNCIAS ===============
        menuOcorrencias.setOnClickListener(v -> {
            if (!(this instanceof MinhasOcorrenciasActivity)) {
                startActivity(new Intent(this, MinhasOcorrenciasActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        //============== OFERTAS ===============
        menuOfertas.setOnClickListener(v -> {
            if (!(this instanceof MinhasOfertasActivity)) {
                startActivity(new Intent(this, MinhasOfertasActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        //============== MENU EXTRA ===============
        menuExtra.setOnClickListener(v -> {
            showExtraMenu(v);
        });
    }

    private void showExtraMenu(View anchor) {

        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenu().add("Trocar de contexto");
        popup.getMenu().add("Sair");

        popup.setOnMenuItemClickListener(item -> {

            String title = item.getTitle().toString();
            SessionManager sm = new SessionManager(this);

            if (title.equals("Trocar de contexto")) {

                sm.clearContext(); // mantém login, apaga escola+role

                Intent i = new Intent(this, ChooseSchoolWebActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return true;
            }

            if (title.equals("Sair")) {

                sm.clearSession(); // apaga tudo

                Intent i = new Intent(this, LauncherActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return true;
            }

            return false;
        });

        popup.show();
    }

//    private void showExtraMenu(View anchor) {
//
//        PopupMenu popup = new PopupMenu(this, anchor);
//        popup.getMenu().add("Trocar de contexto");
//        popup.getMenu().add("Sair");
//
//        popup.setOnMenuItemClickListener(item -> {
//
//            String title = item.getTitle().toString();
//
//            if (title.equals("Trocar de contexto")) {
//                SessionManager sm = new SessionManager(this);
//                sm.clearContextOnly(); // remove escola+role, mantém login
//
//                Intent i = new Intent(this, ChooseSchoolWebActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//                finish();
//                return true;
//            }
//
//            if (title.equals("Sair")) {
//                SessionManager sm = new SessionManager(this);
//                sm.clearSession(); // apaga tudo
//
//                Intent i = new Intent(this, LauncherActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//                finish();
//                return true;
//            }
//
//            return false;
//        });
//
//        popup.show();
//    }


    private void setupMenu() {
        findViewById(R.id.menuHome).setOnClickListener(v -> finish());
        findViewById(R.id.menuOfertas).setOnClickListener(v -> {});
        findViewById(R.id.menuOcorrencias).setOnClickListener(v -> {});
        findViewById(R.id.menuExtra).setOnClickListener(v -> {});
    }

    protected void setScreenTitle(String title) {
        TextView txtTituloBody = findViewById(R.id.txtTituloBody);
        if (txtTituloBody != null) {
            txtTituloBody.setText(title);
        }
    }

//    protected void setScreenTitle(String title) {
//        TextView txtTitulo = findViewById(R.id.txtTituloBody);
//        txtTitulo.setText(title);
//    }

}
