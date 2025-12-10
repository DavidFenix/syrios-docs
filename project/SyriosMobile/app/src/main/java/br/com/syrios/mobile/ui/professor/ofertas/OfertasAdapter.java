package br.com.syrios.mobile.ui.professor.ofertas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.network.dto.OfertaRemote;
import br.com.syrios.mobile.ui.professor.alunos.SelecionarAlunosActivity;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.OfertaViewHolder> {

    private final List<OfertaRemote> lista;

    public OfertasAdapter(List<OfertaRemote> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public OfertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_oferta, parent, false);
        return new OfertaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaViewHolder h, int position) {
        OfertaRemote o = lista.get(position);

        h.txtDisciplina.setText(o.disciplina);
        h.txtTurma.setText("Turma: " + (o.turma != null ? o.turma : "-"));
        h.txtSerie.setText("Série: " + (o.serie != null ? o.serie : "-"));

        if (o.horario != null && !o.horario.isEmpty()) {
            h.txtHorario.setText("Horário: " + o.horario);
            h.txtHorario.setVisibility(View.VISIBLE);
        } else {
            h.txtHorario.setVisibility(View.GONE);
        }

        h.itemView.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), SelecionarAlunosActivity.class);
            i.putExtra("oferta_id", o.id);   // CORRETO
            v.getContext().startActivity(i);
        });
    }


//    @Override
//    public void onBindViewHolder(@NonNull OfertaViewHolder h, int position) {
//        OfertaRemote o = lista.get(position);
//
//        h.txtDisciplina.setText(o.disciplina);
//        h.txtTurma.setText("Turma: " + (o.turma != null ? o.turma : "-"));
//        h.txtSerie.setText("Série: " + (o.serie != null ? o.serie : "-"));
//
//        if (o.horario != null && !o.horario.isEmpty()) {
//            h.txtHorario.setText("Horário: " + o.horario);
//            h.txtHorario.setVisibility(View.VISIBLE);
//        } else {
//            h.txtHorario.setVisibility(View.GONE);
//        }
//
//        holder.itemView.setOnClickListener(v -> {
//            Intent i = new Intent(v.getContext(), SelecionarAlunosActivity.class);
//            i.putExtra("oferta_id", oferta.id);
//            v.getContext().startActivity(i);
//        });
//
//    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    static class OfertaViewHolder extends RecyclerView.ViewHolder {

        TextView txtDisciplina, txtTurma, txtSerie, txtHorario;

        public OfertaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDisciplina = itemView.findViewById(R.id.txtDisciplina);
            txtTurma      = itemView.findViewById(R.id.txtTurma);
            txtSerie      = itemView.findViewById(R.id.txtSerie);
            txtHorario    = itemView.findViewById(R.id.txtHorario);
        }
    }
}
