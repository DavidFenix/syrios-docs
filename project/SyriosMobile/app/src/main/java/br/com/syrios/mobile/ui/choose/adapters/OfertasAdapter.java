//package br.com.syrios.mobile.ui.choose.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import br.com.syrios.mobile.network.dto.OfertaRemote;
//
//public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.Holder> {
//
//    private final List<OfertaRemote> lista;
//
//    public OfertasAdapter(List<OfertaRemote> lista) {
//        this.lista = lista;
//    }
//
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(android.R.layout.simple_list_item_2, parent, false);
//        return new Holder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Holder h, int pos) {
//        OfertaRemote o = lista.get(pos);
//        h.t1.setText(o.disciplina);
//        h.t2.setText("Turma: " + o.turma);
//    }
//
//    @Override
//    public int getItemCount() {
//        return lista.size();
//    }
//
//    static class Holder extends RecyclerView.ViewHolder {
//        TextView t1, t2;
//        Holder(View v) {
//            super(v);
//            t1 = v.findViewById(android.R.id.text1);
//            t2 = v.findViewById(android.R.id.text2);
//        }
//    }
//}
//
