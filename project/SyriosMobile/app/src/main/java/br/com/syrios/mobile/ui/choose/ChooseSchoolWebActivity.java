package br.com.syrios.mobile.ui.choose;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.cache.EscolasCache;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.network.dto.LoginResponse;

public class ChooseSchoolWebActivity extends AppCompatActivity {

    private LinearLayout containerEscolas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_school_web);

        containerEscolas = findViewById(R.id.containerEscolas);

        List<LoginResponse.EscolaRemote> escolas = EscolasCache.getRemote(this);


        if (escolas == null || escolas.isEmpty()) {
            android.widget.Toast.makeText(
                    this,
                    "Nenhuma escola encontrada para este usuário.",
                    android.widget.Toast.LENGTH_LONG
            ).show();
            return;
        }

        for (LoginResponse.EscolaRemote e : escolas) {
            addEscolaCard(e);
        }
    }

    private void addEscolaCard(LoginResponse.EscolaRemote escola) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(0, 0, 0, 0);
        card.setBackgroundResource(R.drawable.bg_card_syrios);
        card.setElevation(8f);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        params.setMargins(30, 20, 30, 40);
        card.setLayoutParams(params);

        TextView nomeEscola = new TextView(this);
        nomeEscola.setText(escola.nome_e);
        nomeEscola.setTextSize(20f);
        nomeEscola.setTextColor(Color.parseColor("#3b5ccc"));
        nomeEscola.setPadding(0, 0, 0, 10);

        TextView cidade = new TextView(this);

        String cidadeStr = (escola.cidade == null ? "" : escola.cidade);
        String estadoStr = (escola.estado == null ? "" : escola.estado);
        if (!cidadeStr.isEmpty() && !estadoStr.isEmpty()) {
            cidade.setText(cidadeStr + " - " + estadoStr);
        } else if (!cidadeStr.isEmpty()) {
            cidade.setText(cidadeStr);
        } else {
            cidade.setText(estadoStr);
        }

        cidade.setTextSize(15f);
        cidade.setTextColor(Color.parseColor("#555555"));
        cidade.setPadding(0, 0, 0, 20);

        Button btnEscolher = new Button(this);
        btnEscolher.setText("Escolher papéis");
        btnEscolher.setBackgroundResource(R.drawable.bg_button_syrios);
        btnEscolher.setTextColor(Color.WHITE);
        btnEscolher.setTextSize(18f);
        btnEscolher.setAllCaps(false);

        btnEscolher.setOnClickListener(v -> {
            SessionManager sm = new SessionManager(this);
            sm.setCurrentSchool(escola.id, escola.nome_e);

            Intent i = new Intent(this, ChooseRoleWebActivity.class);
            i.putExtra("school_id", escola.id);
            i.putExtra("school_name", escola.nome_e);
            startActivity(i);
        });

        card.addView(nomeEscola);
        card.addView(cidade);
        card.addView(btnEscolher);

        containerEscolas.addView(card);
    }

}
