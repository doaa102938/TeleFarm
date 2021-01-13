package com.example.telefarm2.fragment.Products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telefarm2.R;
import com.example.telefarm2.model.fruits;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{
private Context context;
private List <fruits> list;

    public ProductsAdapter(Context context, List<fruits> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_fruits,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setNameProduct(list.get(position).getName());
        holder.setDescProduct(list.get(position).getDesc());
        holder.setPriceProduct(list.get(position).getPrice());
        holder.setImageProduct(list.get(position).getImage());
        holder.setQuantityProduct(list.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameProduct;
        TextView priceProduct;
        TextView descProduct;
        TextView quantityProduct;
        ImageView imageProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameProduct=itemView.findViewById(R.id.name_product);
            priceProduct=itemView.findViewById(R.id.price);
            descProduct=itemView.findViewById(R.id.discription);
            quantityProduct=itemView.findViewById(R.id.quantity);
            imageProduct=itemView.findViewById(R.id.image_products);

        }

        void setNameProduct(String name){
            nameProduct.setText(name);
        }
        void setPriceProduct(double price){
            priceProduct.setText(String.valueOf(price));
        }
        void setDescProduct(String desc){
            descProduct.setText(desc);
        }
        void setQuantityProduct(double quantity){
            quantityProduct.setText(String.valueOf(quantity));
        }
        void setImageProduct(String url){
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.image_fruit)
                    .into(imageProduct);

        }

    }
}
