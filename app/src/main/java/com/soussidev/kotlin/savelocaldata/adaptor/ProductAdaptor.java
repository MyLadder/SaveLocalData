package com.soussidev.kotlin.savelocaldata.adaptor;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soussidev.kotlin.savelocaldata.R;
import com.soussidev.kotlin.savelocaldata.model.PriceUnit;
import com.soussidev.kotlin.savelocaldata.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.soussidev.kotlin.savelocaldata.MainActivity.SPAN_COUNT_ONE;

/**
 * Created by Soussi on 15/10/2017.
 */

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ItemViewHolder>{

    private static final int VIEW_TYPE_SMALL = 1;
    private static final int VIEW_TYPE_BIG = 2;

    //private ArrayList<Product> mProduct;
    private ArrayList<Product> mProduct=new ArrayList<Product>();
    private GridLayoutManager mLayoutManager;
    private Context mcontext;

    public ProductAdaptor(ArrayList<Product> items, GridLayoutManager layoutManager, Context context) {
        mProduct = items;
        mLayoutManager = layoutManager;
        mcontext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_ONE) {
            return VIEW_TYPE_BIG;
        } else {
            return VIEW_TYPE_SMALL;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_BIG) {//parent.getContext()
            view = LayoutInflater.from(mcontext).inflate(R.layout.item_big, parent, false);
        } else {
            view = LayoutInflater.from(mcontext).inflate(R.layout.item_small, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        // final Item item = mItems.get(position % 4);
        final Product product = mProduct.get(position );
        holder.title.setText(product.getNom_prod());
//        Glide.with(mcontext)
//                .load(product.getImg_prod())
//                .apply(RequestOptions.circleCropTransform())
//                .into(holder.iv);

        Picasso.with(mcontext).load(product.getImg_prod())
                .fit().centerInside()
                .error(R.drawable.pixel_2)
                .into(holder.iv);


        //  holder.iv.setImageResource(R.mipmap.ic_launcher);
        if (getItemViewType(position) == VIEW_TYPE_BIG) {
            holder.info.setText(" Marque  ·  "+product.getMarque_prod() +"- Model "  + product.getModel_product());
            holder.price.setText(String.valueOf(product.getPrice_prod()+" "+ PriceUnit.DT));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,String.valueOf(product.getNom_prod()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return mProduct.size();
    }




    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title;
        TextView info;
        TextView price;

        ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == VIEW_TYPE_BIG) {
                iv = (ImageView) itemView.findViewById(R.id.image_big);
                title = (TextView) itemView.findViewById(R.id.title_big);
                info = (TextView) itemView.findViewById(R.id.tv_info);
                price = (TextView) itemView.findViewById(R.id.tv_price);
            } else {
                iv = (ImageView) itemView.findViewById(R.id.image_small);
                title = (TextView) itemView.findViewById(R.id.title_small);
            }

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos =getPosition();
                    Toast.makeText(mcontext,String.valueOf(pos), Toast.LENGTH_SHORT).show();
                }
            });*/

        }



    }
}
