package br.com.syrios.mobile.core;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.ui.choose.ChooseSchoolWebActivity;
import br.com.syrios.mobile.ui.login.LoginProfessorActivity;

public class ContextGuard {

    /**
     * Garante que:
     * 1) exista usuário logado
     * 2) exista contexto (escola + role) se requireFullContext = true
     *
     * Retorna false se a Activity foi redirecionada e deve PARAR a execução do onCreate.
     */
    public static boolean ensureLoginAndContext(AppCompatActivity activity, boolean requireFullContext) {
        SessionManager sm = new SessionManager(activity);

        // Ninguém logado → manda pra tela de login
        if (!sm.isLoggedIn()) {
            Intent i = new Intent(activity, LoginProfessorActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
            activity.finish();
            return false;
        }

        // Logado mas sem contexto completo → manda escolher escola
        if (requireFullContext && !sm.hasValidContext()) {
            Intent i = new Intent(activity, ChooseSchoolWebActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
            activity.finish();
            return false;
        }

        return true;
    }
}
