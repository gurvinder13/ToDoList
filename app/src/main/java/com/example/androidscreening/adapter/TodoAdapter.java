package com.example.androidscreening.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidscreening.CheckBoxView;
import com.example.androidscreening.R;
import com.example.androidscreening.model.ItemsList;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    List<ItemsList> myDataLists;
    private Context context;
    private CheckBoxView checkInterface;
    public TodoAdapter(List<ItemsList> myDataLists,Context context, CheckBoxView checkInterface) {
        this.myDataLists = myDataLists;
        this.context=context;
        this.checkInterface = checkInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ItemsList list=myDataLists.get(i);

        viewHolder.tv_title.setText(list.getTitle());
        viewHolder.tv_desc.setText(list.getDescription());
        Glide.with(context)
                .load(list.getImage()).apply(new RequestOptions().placeholder(R.drawable.default_profile_image))
                .into(viewHolder.imageView);
        if(list.isDone()){
            viewHolder.check.setChecked(true);
        }else{
            viewHolder.check.setChecked(false);
        }
        viewHolder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Interface method
                    checkInterface.onChecked(list.getId(),true);
                    Toast.makeText(context, list.getTitle()+" Done", Toast.LENGTH_SHORT).show();
                }else{
                    checkInterface.onUnCheckded(list.getId(),false);
                    Toast.makeText(context, list.getTitle()+" Undone", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return myDataLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_desc;
        private ImageView imageView;
        private CheckBox check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title= itemView.findViewById(R.id.tv_title);
            tv_desc= itemView.findViewById(R.id.tv_description);
            imageView=itemView.findViewById(R.id.imageView);
            check = itemView.findViewById(R.id.checkBox);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                List<ItemsList> filteredList = new ArrayList<>();
                if (charString.isEmpty()) {
                    filteredList.addAll(myDataLists);
                } else {
                    for (ItemsList row : myDataLists) {

                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                myDataLists = (ArrayList<ItemsList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
