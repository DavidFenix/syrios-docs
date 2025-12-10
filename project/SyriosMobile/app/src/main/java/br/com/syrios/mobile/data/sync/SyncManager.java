package br.com.syrios.mobile.data.sync;

import android.content.Context;
import android.util.Log;

import br.com.syrios.mobile.data.db.SyriosDatabase;
import br.com.syrios.mobile.data.session.SessionManager;

public class SyncManager {

    private static final String TAG = "SyncManager";

    private final SyriosDatabase db;
    private final SessionManager sessionManager;
    private final Context context;

    public SyncManager(Context context, SyriosDatabase db, SessionManager sessionManager) {
        this.context = context.getApplicationContext();
        this.db = db;
        this.sessionManager = sessionManager;
    }

    /**
     * Chamado logo após login online bem-sucedido
     * Aqui vamos:
     * - Baixar ofertas do professor
     * - Baixar turmas
     * - Baixar alunos
     * - Baixar motivos
     * - (no futuro) histórico de ocorrências
     */
    public void syncAfterLogin() {
        Long userId = sessionManager.getUserId();
        Long schoolId = sessionManager.getCurrentSchoolId();

        if (userId == null || schoolId == null) {
            Log.w(TAG, "syncAfterLogin: sessão incompleta");
            return;
        }

        Log.d(TAG, "Iniciando sync para user=" + userId + " school=" + schoolId);

        // TODO: chamar APIs como:
        // - GET /mobile/professor/ofertas
        // - GET /mobile/escola/{schoolId}/turmas
        // - GET /mobile/escola/{schoolId}/alunos
        // - GET /mobile/escola/{schoolId}/motivos
        //
        // e salvar nos DAOs correspondentes:
        // db.ofertaDao().insertAll(...);
        // db.turmaDao().insertAll(...);
        // db.alunoDao().insertAll(...);
        // db.modeloMotivoDao().insertAll(...);

        // Por enquanto, só deixamos o esqueleto claro.
    }

    /**
     * Enviar ocorrências pendentes (sync = 0) para o servidor
     */
    public void syncOcorrenciasPendentes() {
        Log.d(TAG, "Sincronizando ocorrências pendentes...");
        // TODO:
        // 1) buscar todas as ocorrências com sync = 0
        // 2) enviar via API
        // 3) marcar como sync = 1 no banco local
    }
}
