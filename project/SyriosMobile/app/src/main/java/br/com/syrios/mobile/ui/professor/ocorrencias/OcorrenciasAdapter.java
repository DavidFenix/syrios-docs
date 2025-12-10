package br.com.syrios.mobile.ui.professor.ocorrencias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.network.dto.OcorrenciaRemote;

public class OcorrenciasAdapter extends RecyclerView.Adapter<OcorrenciasAdapter.ViewHolder> {

    private final List<OcorrenciaRemote> lista;

    public OcorrenciasAdapter(List<OcorrenciaRemote> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ocorrencia, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        OcorrenciaRemote oc = lista.get(position);

        h.txtAluno.setText(oc.aluno);
        h.txtTurma.setText(oc.turma);
        h.txtDescricao.setText(oc.descricao);
        h.txtData.setText(oc.data);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAluno, txtTurma, txtDescricao, txtData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtAluno = itemView.findViewById(R.id.txtAluno);
            txtTurma = itemView.findViewById(R.id.txtTurma);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtData = itemView.findViewById(R.id.txtData);
        }
    }
}
