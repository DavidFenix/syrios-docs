package br.com.syrios.mobile.ui.professor.alunos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.network.dto.AlunoDaOfertaRemote;

public class AlunosAdapter extends RecyclerView.Adapter<AlunosAdapter.Holder> {

    private List<AlunoDaOfertaRemote> lista;
    private List<Long> selecionados = new ArrayList<>();

    public AlunosAdapter(List<AlunoDaOfertaRemote> lista) {
        this.lista = lista;
    }

    public List<Long> getSelecionados() {
        return selecionados;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aluno_selecao, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h, int position) {

        AlunoDaOfertaRemote a = lista.get(position);

//        h.txtNome.setText(a.nome);
//        h.txtTurma.setText(a.turma != null ? "Turma: " + a.turma : "Turma: -");
        h.txtNome.setText(a.nome);
        //h.txtMatricula.setText("Matrícula: " + a.matricula);

        // Foto (se futuramente usar)
//        if (a.foto != null && !a.foto.isEmpty()) {
//            h.imgFoto.setImageResource(R.drawable.ic_role); // TEMP — ajustaremos depois
//        } else {
//            h.imgFoto.setImageResource(R.drawable.ic_role);
//        }

        // Checkbox
        h.checkbox.setOnCheckedChangeListener(null);
        h.checkbox.setChecked(selecionados.contains(a.id));

        h.checkbox.setOnCheckedChangeListener((button, checked) -> {
            if (checked) {
                if (!selecionados.contains(a.id))
                    selecionados.add(a.id);
            } else {
                selecionados.remove(a.id);
            }
        });

        // Clicar no item também marca/desmarca
        h.itemView.setOnClickListener(v -> {
            boolean novoEstado = !h.checkbox.isChecked();
            h.checkbox.setChecked(novoEstado);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView imgFoto;
        TextView txtNome, txtTurma;
        CheckBox checkbox;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgAluno);
            txtNome = itemView.findViewById(R.id.txtNomeAluno);
            txtTurma = itemView.findViewById(R.id.txtTurmaAluno);
            checkbox = itemView.findViewById(R.id.checkboxAluno);
        }
    }
}
