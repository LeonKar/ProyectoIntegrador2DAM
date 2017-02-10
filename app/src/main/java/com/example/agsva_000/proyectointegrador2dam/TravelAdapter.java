package com.example.agsva_000.proyectointegrador2dam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class TravelAdapter extends BaseAdapter{
    Context context;
    ArrayList<Travel> travels;

    public TravelAdapter(Context context, ArrayList<Travel> travels) {
        this.context = context;
        this.travels = travels;
    }

    @Override
    public int getCount() {
        return travels.size();
    }

    @Override
    public Object getItem(int position) {
        return travels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
            holder=new ViewHolder();
            holder.origen=(TextView) convertView.findViewById(R.id.tvOrigen);
            holder.destino=(TextView) convertView.findViewById(R.id.tvDestino);
            holder.fecha=(TextView) convertView.findViewById(R.id.tvFecha);
            holder.hora=(TextView) convertView.findViewById(R.id.tvHora);
            holder.precio=(TextView) convertView.findViewById(R.id.tvPrecio);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.origen.setText(" "+travels.get(position).getOrigen());
        holder.destino.setText(" "+travels.get(position).getDestino());
        holder.fecha.setText(" "+travels.get(position).getFecha());
        holder.hora.setText(" "+travels.get(position).getHora());
        holder.precio.setText(" "+travels.get(position).getPrecio());
        return convertView;
    }
    public static class ViewHolder{
        TextView origen;
        TextView destino;
        TextView fecha;
        TextView hora;
        TextView precio;
    }
}