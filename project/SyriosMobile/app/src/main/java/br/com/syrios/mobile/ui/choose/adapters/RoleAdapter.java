package br.com.syrios.mobile.ui.choose.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.syrios.mobile.databinding.ItemRoleBinding;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleViewHolder> {

    public interface OnRoleClickListener {
        void onClick(String role);
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
        ItemRoleBinding binding = ItemRoleBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new RoleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        String role = roles.get(position);
        holder.binding.textRole.setText(role);
        holder.binding.cardRole.setOnClickListener(v -> listener.onClick(role));
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    static class RoleViewHolder extends RecyclerView.ViewHolder {
        ItemRoleBinding binding;

        public RoleViewHolder(ItemRoleBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
