package br.com.syrios.mobile.data.session;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import br.com.syrios.mobile.data.cache.EscolasCache;

public class SessionManager {

    private static final String PREF_NAME = "syrios_prefs";

    // Dados de sessão
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ROLES = "roles";

    // Contexto
    private static final String KEY_CURRENT_SCHOOL_ID = "current_school_id";
    private static final String KEY_CURRENT_SCHOOL_NAME = "current_school_name";
    private static final String KEY_CURRENT_ROLE = "current_role";

    // Infos do usuário
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_CPF = "user_cpf";
    private static final String KEY_USER_NASC = "user_nasc";
    private static final String KEY_USER_STATUS = "user_status";

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    private final Context context;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
        this.context = context;
    }

    public void clearContextOnly() {
        editor.remove(KEY_CURRENT_SCHOOL_ID);
        editor.remove(KEY_CURRENT_SCHOOL_NAME);
        editor.remove(KEY_CURRENT_ROLE);
        editor.apply();
    }


    // ============================================================
    //  LOGIN / SALVAR SESSÃO
    // ============================================================

    public void saveLoginSession(long schoolId, String role, String token) {
        editor.putLong(KEY_CURRENT_SCHOOL_ID, schoolId);
        editor.putString(KEY_CURRENT_ROLE, role);
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public void saveUserInfo(long userId, String nome, String nascimento, int status, String cpf) {
        editor.putLong(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, nome);
        editor.putString(KEY_USER_NASC, nascimento);
        editor.putInt(KEY_USER_STATUS, status);
        editor.putString(KEY_USER_CPF, cpf);
        editor.apply();
    }

    // Limpa somente a escola e papel atual (igual ao Syrios Web)
    public void clearContext() {
        editor.remove(KEY_CURRENT_SCHOOL_ID);
        editor.remove(KEY_CURRENT_SCHOOL_NAME);
        editor.remove(KEY_CURRENT_ROLE);
        editor.apply();
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }


//    public void saveRoles(List<String> roles) {
//        if (roles == null || roles.isEmpty()) return;
//        editor.putString(KEY_ROLES, String.join(",", roles));
//        editor.apply();
//    }

    // ============================================================
    //  GETTERS
    // ============================================================

    public long getUserId() { return prefs.getLong(KEY_USER_ID, -1); }

    public String getUserName() { return prefs.getString(KEY_USER_NAME, ""); }

    public String getUserCpf() { return prefs.getString(KEY_USER_CPF, ""); }

    public String getToken() { return prefs.getString(KEY_TOKEN, null); }

    public long getCurrentSchoolId() { return prefs.getLong(KEY_CURRENT_SCHOOL_ID, -1); }

    public String getCurrentSchoolName() {
        return prefs.getString(KEY_CURRENT_SCHOOL_NAME, "");
    }

    public String getCurrentRole() { return prefs.getString(KEY_CURRENT_ROLE, null); }

    public boolean isLoggedIn() {
        return getUserId() != -1 && getToken() != null;
    }

    public boolean hasValidContext() {
        return isLoggedIn()
                && getCurrentSchoolId() != -1
                && getCurrentRole() != null;
    }

//    public List<String> getRolesList() {
//        String stored = prefs.getString(KEY_ROLES, null);
//        if (stored == null || stored.isEmpty()) {
//            return new ArrayList<>();
//        }
//        return Arrays.asList(stored.split(","));
//    }


    // ============================================================
    //  DEFINIR CONTEXTO
    // ============================================================

    public void setCurrentSchool(long id, String name) {
        editor.putLong(KEY_CURRENT_SCHOOL_ID, id);
        editor.putString(KEY_CURRENT_SCHOOL_NAME, name);
        editor.apply();
    }

    public void setCurrentRole(String role) {
        editor.putString(KEY_CURRENT_ROLE, role);
        editor.apply();
    }

    // ============================================================
    //  LOGOUT (VERSÃO CORRETA)
    // ============================================================

//    public void logout() {
//        editor.clear().apply();          // remove tudo
//        EscolasCache.clear(context);            // limpa cache das escolas
//    }

    // LOGOUT COMPLETO (Sair) ===========================
// aqui vamos limpar TUDO + cache de escolas
    public void logout(android.content.Context ctx) {
        editor.clear().apply();      // limpa prefs de sessão
        br.com.syrios.mobile.data.cache.EscolasCache.clear(ctx); // limpa cache de escolas
    }
}

