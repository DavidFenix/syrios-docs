package br.com.syrios.mobile.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.syrios.mobile.data.entities.Escola;
import br.com.syrios.mobile.network.dto.LoginResponse;

public class EscolasCache {

    private static final String PREF_NAME = "syrios_escolas_cache";
    private static final String KEY_ESCOLAS_REMOTAS = "escolas_remotas_json";
    private static final String KEY_ESCOLAS_LOCAL = "escolas_local_json";

    private static List<Escola> escolas = new ArrayList<>();
    private static List<LoginResponse.EscolaRemote> escolasRemotas = new ArrayList<>();

    private static final Gson gson = new Gson();

    // ---------------------------------------------------------
    // SALVAR TUDO (versão melhorada do seu método atual)
    // ---------------------------------------------------------
    public static void saveAll(Context ctx, List<LoginResponse.EscolaRemote> remotas) {

        if (remotas == null) {
            escolasRemotas = new ArrayList<>();
            escolas = new ArrayList<>();
            saveToDisk(ctx);
            return;
        }

        // salvar remotas na memória
        escolasRemotas = remotas;

        // converter para locais
        List<Escola> locais = new ArrayList<>();
        for (LoginResponse.EscolaRemote er : remotas) {
            if (er != null) locais.add(er.toLocal());
        }
        escolas = locais;

        // persistir em disco
        saveToDisk(ctx);
    }

    // ---------------------------------------------------------
    // Salvar no SharedPreferences
    // ---------------------------------------------------------
    private static void saveToDisk(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();

        ed.putString(KEY_ESCOLAS_REMOTAS, gson.toJson(escolasRemotas));
        ed.putString(KEY_ESCOLAS_LOCAL, gson.toJson(escolas));

        ed.apply();
    }

    // ---------------------------------------------------------
    // Carregar do disco SE NECESSÁRIO
    // ---------------------------------------------------------
    private static void loadFromDisk(Context ctx) {

        // se já estiverem na memória, não recarrega
        if (!escolasRemotas.isEmpty()) return;

        SharedPreferences prefs = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String remoteJson = prefs.getString(KEY_ESCOLAS_REMOTAS, null);
        String localJson = prefs.getString(KEY_ESCOLAS_LOCAL, null);

        Type remoteType = new TypeToken<List<LoginResponse.EscolaRemote>>(){}.getType();
        Type localType  = new TypeToken<List<Escola>>(){}.getType();

        if (remoteJson != null) {
            escolasRemotas = gson.fromJson(remoteJson, remoteType);
        }

        if (localJson != null) {
            escolas = gson.fromJson(localJson, localType);
        }

        if (escolasRemotas == null) escolasRemotas = new ArrayList<>();
        if (escolas == null) escolas = new ArrayList<>();
    }

    // ---------------------------------------------------------
    // GETTERS
    // ---------------------------------------------------------
    public static List<LoginResponse.EscolaRemote> getRemote(Context ctx) {
        loadFromDisk(ctx);
        return escolasRemotas;
    }

    public static List<Escola> get(Context ctx) {
        loadFromDisk(ctx);
        return escolas;
    }

    // Compatibilidade com código antigo (sem contexto)
    public static List<LoginResponse.EscolaRemote> getRemote() {
        return escolasRemotas;
    }

    public static List<Escola> get() {
        return escolas;
    }


    // ---------------------------------------------------------
    public static LoginResponse.EscolaRemote getRemoteById(Long id) {
        for (LoginResponse.EscolaRemote e : escolasRemotas) {
            if (e.id.equals(id)) return e;
        }
        return null;
    }

    // ---------------------------------------------------------
    public static void clear(Context ctx) {
        escolas.clear();
        escolasRemotas.clear();

        SharedPreferences prefs = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
