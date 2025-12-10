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
//import br.com.syrios.mobile.network.dto.OcorrenciaRemote;
//
//public class OcorrenciasAdapter extends RecyclerView.Adapter<OcorrenciasAdapter.Holder> {
//
//    private final List<OcorrenciaRemote> lista;
//
//    public OcorrenciasAdapter(List<OcorrenciaRemote> lista) {
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
//        OcorrenciaRemote o = lista.get(pos);
//        h.t1.setText(o.aluno);
//        h.t2.setText(o.data + " - " + o.descricao);
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
