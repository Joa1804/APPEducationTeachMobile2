package com.example.appeducacationteach.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeducacationteach.R;
import com.example.appeducacationteach.model.Atividade;

import java.util.List;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.ViewHolder> {

    private List<Atividade> atividades;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;

    public interface OnItemClickListener {
        void onItemClick(Atividade atividade);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Atividade atividade);
    }

    public AtividadeAdapter(List<Atividade> atividades, OnItemClickListener listener, OnItemLongClickListener longClickListener) {
        this.atividades = atividades;
        this.listener = listener;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atividade, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Atividade at = atividades.get(position);
        holder.txtTitulo.setText(at.getTitulo());
        holder.txtData.setText("Entrega: " + at.getDataEntrega());
        holder.txtDificuldade.setText("Dificuldade: " + at.getDificuldade());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(at));
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(at);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtData, txtDificuldade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloAtividade);
            txtData = itemView.findViewById(R.id.txtDataEntrega);
            txtDificuldade = itemView.findViewById(R.id.txtDificuldade);
        }
    }
}
