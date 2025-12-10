package br.com.syrios.mobile.ui.role;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.syrios.mobile.R;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleViewHolder> {

    public interface OnRoleClickListener {
        void onRoleClick(String roleName);
    }

    private final List<String> roles;
    private final OnRoleClickListener listener;

    public RoleAdapter(List<String> roles, OnRoleClickListener listener) {
        this.roles = roles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_role, parent, false);
        return new RoleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        String role = roles.get(position);

        // nomes bonitos
        String pretty =
                role.equals("professor") ? "Professor" :
                        role.equals("gestor") ? "Gestor Escolar" :
                                role.equals("pais") ? "Responsável" :
                                        role.equals("aluno") ? "Aluno" : role;

        holder.textRole.setText(pretty);

        holder.itemView.setOnClickListener(v -> listener.onRoleClick(role));
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    static class RoleViewHolder extends RecyclerView.ViewHolder {
        TextView textRole;

        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            textRole = itemView.findViewById(R.id.textRole);
        }
    }
}
