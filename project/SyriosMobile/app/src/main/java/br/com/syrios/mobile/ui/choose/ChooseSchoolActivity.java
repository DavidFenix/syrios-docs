package br.com.syrios.mobile.ui.choose;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.cache.EscolasCache;
import br.com.syrios.mobile.data.entities.Escola;
import br.com.syrios.mobile.data.repository.AuthRepository;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.ui.choose.ChooseRoleActivity;

public class ChooseSchoolActivity extends AppCompatActivity {

    private ListView listView;
    private List<Escola> escolas = new ArrayList<>();

    private AuthRepository authRepository;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_school);

        listView = findViewById(R.id.listEscolhasEscola);

        //authRepository = new AuthRepository(this);
        //session = authRepository.getSessionManager();
        session = new SessionManager(this);

        // Pega as escolas recebidas no login
        escolas = EscolasCache.get();

        if (escolas == null || escolas.isEmpty()) {
            finish();
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                escolas.stream().map(e -> e.nome).toArray(String[]::new)
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, pos, id) -> {
            Escola escola = escolas.get(pos);
//session.getUserId(),session.getCpf()
            // salve a escola escolhida
            session.saveLoginSession(
                    escola.id,
                    session.getCurrentRole(),   // mantemos a role por agora
                    session.getToken()
            );

            // abrir escolha de role
            Intent i = new Intent(ChooseSchoolActivity.this, ChooseRoleActivity.class);
            startActivity(i);
            finish();
        });
    }
}
