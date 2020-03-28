package com.smartVisitor.avand.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.Holders.ItemClickListener;
import com.smartVisitor.avand.Holders.OrderItemRecyclerViewHolder;
import com.smartVisitor.avand.classes.Convert;
import com.smartVisitor.avand.entities.OrderItem;

import java.util.List;
public class OrderItemAdapter  extends RecyclerView.Adapter<OrderItemRecyclerViewHolder> {
    private List<OrderItem> Items;
    private Context context;
    public OrderItemAdapter( Context context, List<OrderItem> Items) {
        this.Items = Items;
        this.context = context;
//        super (context, R.layout.product_list_layout,null, products);
    }

    @NonNull
    @Override
    public OrderItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_order_item_list, parent,false);
        return new OrderItemRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderItemRecyclerViewHolder holder,final int i) {
        OrderItem it = Items.get(i);
        int total = it.getPrice() * it.getQty() ;
        holder.tv_order_Item_Code.setText(it.ProductCode);
        holder.tv_order_Item_Name.setText(it.ProductName);
        holder.tv_order_Item_Rls.setText(Convert.toSeprate(it.Price));
        holder.tv_order_Item_Total.setText(Convert.toSeprate(total));
        holder.Chk_OrderItem.setChecked(it.IsChecked);
        if ( it.Offer > 0  && it.Qty >0 ){
            holder.tv_order_Item_Qty.setText(Convert.toSeprate(it.Qty) +"+"+Convert.toSeprate(it.Offer));
        }
        else if  ( it.Qty > 0  ){
            holder.tv_order_Item_Qty.setText(Convert.toSeprate(it.Qty));
        }
        else if  ( it.Offer > 0  ){
            holder.tv_order_Item_Qty.setText(Convert.toSeprate(it.Offer));
        }
        else{
            holder.tv_order_Item_Qty.setVisibility(View.INVISIBLE);
        }
        //======================== Event Listener =============================================
        holder.Chk_OrderItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Items.get(i).setChecked(isChecked);
            }
        });


        //holder.img_Price.setOnTouchListener(new View.OnTouchListener()......
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                OrderItem item = Items.get(position);
                if (isLongClick) {
                    //Intent intent = new Intent(context, ProductDetailActivity.class);
                    //intent.putExtra("Product_Id", selectedProduct.getId());
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //context.startActivity(intent);
                } else {
                    //Toast.makeText(context, "Click " + selectedProduct.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }


}
