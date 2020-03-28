package com.smartVisitor.avand.adapters;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.Holders.ItemClickListener;
import com.smartVisitor.avand.Holders.ProductRecyclerViewHolder;
import com.smartVisitor.avand.activities.AddProductActivity;
import com.smartVisitor.avand.activities.ProductDetailActivity;
import com.smartVisitor.avand.classes.Convert;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.dataTransferObjects.ProductDTO;
import com.smartVisitor.avand.entities.Order;
import com.smartVisitor.avand.entities.OrderItem;
import com.smartVisitor.avand.entities.Product;

import java.util.List;
public class ProductListAdapter extends RecyclerView.Adapter<ProductRecyclerViewHolder> {
    public List<ProductDTO> products;
    public Order order;
    private Context context;
    private  boolean CallFromOrder;
    public ProductListAdapter( Context context, List<ProductDTO> products,Order order,boolean CallFromOrder) {
        this.products = products;
        this.context = context;
        this.CallFromOrder =  CallFromOrder;
        this.order = order;
//        super (context, R.layout.product_list_layout,null, products);
    }

    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_product_list, parent,false);
        return new ProductRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ProductRecyclerViewHolder holder, int i) {
        final ProductDTO product = products.get(i);
        if ( CallFromOrder){
            holder.tv_Price.setText(Convert.toSeprate((int)Global.isNull( product.priceListDetail.Price,0)));
            //holder.tv_currency.setText("Rls");
            if ( product.priceListDetail.Tax > 0 ){
                holder.tv_Tax.setVisibility(View.VISIBLE);
                holder.tv_Tax.setText(" +" + Integer.toString(product.priceListDetail.Tax) + "%");
            }else {
                holder.tv_Tax.setVisibility(View.INVISIBLE);
            }

        }else {
            holder.tv_Price.setVisibility(View.INVISIBLE);
            holder.tv_Tax.setVisibility(View.INVISIBLE);
            holder.tv_currency.setVisibility(View.INVISIBLE);
        }

        holder.tv_Id .setText(Integer.toString( product.id));
        holder.tv_Code.setText(product.code);
        holder.tv_Name.setText(product.name);
        holder.tv_Weight.setText(Integer.toString( product.weight));
        holder.tv_Count.setText(Integer.toString( product.Inventory));
        holder.tv_Detail.setText(product.getDetail());

        for (OrderItem item:this.order.Items) {
            if ( (float)item.ID_Product== (float)product.id ){
                product.SelectedCount =item.Qty;
                product.SelectedOffer= item.Offer;
                product.SelectedTax= item.Tax;

            }
        }
        if ( (product.SelectedCount == null ||product.SelectedCount  == 0)&&(product.SelectedOffer == null ||product.SelectedOffer  == 0) ) {
            holder.tv_selectedCount.setText("");
            holder.Img.setVisibility(View.INVISIBLE );
        }
        else {
            String Msg = " عدد";
            String Qty = "";
            String Offer = "";
            if ( !(product.SelectedCount == null ||product.SelectedCount  == 0) ){
                Qty = Convert.toSeprate(product.SelectedCount);
            }
            if ( !(product.SelectedOffer == null ||product.SelectedOffer  == 0) ){
                Offer = Convert.toSeprate(product.SelectedOffer);
            }
            if ( !(product.SelectedCount == null ||product.SelectedCount  == 0) && !(product.SelectedOffer == null ||product.SelectedOffer  == 0) ) {
                Msg = Qty + "+" + Offer + Msg;
            }
            else {
                Msg = Qty + Offer + Msg;
            }
            holder.tv_selectedCount.setText(Msg);
            holder.Img.setVisibility(View.VISIBLE );
        }
        //======================== Event Listener =============================================
        //holder.img_Price.setOnTouchListener(new View.OnTouchListener()......
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Product selectedProduct = products.get(position);
                if (isLongClick) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("Product_Id", selectedProduct.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    if (  CallFromOrder  ){
                        Intent intent = new Intent(context, AddProductActivity.class);
                        intent.putExtra("Product" , selectedProduct);
                        intent.putExtra("Order" , order);
                        intent.putExtra("position" , position);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //context.startActivity(intent);
                        ((Activity) context).startActivityForResult(intent,1);
                    }
                    else {
                        Toast.makeText(context, selectedProduct.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return products.size();
    }
}
