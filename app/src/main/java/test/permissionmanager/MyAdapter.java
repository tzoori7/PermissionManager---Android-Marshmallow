package test.permissionmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Itzhar on 11/12/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private ArrayList<String> permissions;
    private View.OnClickListener listener;
    public MyAdapter(ArrayList<String> permissions,View.OnClickListener listener) {
        this.permissions = permissions;
        this.listener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvPermission.setText(permissions.get(position));
        holder.tvPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return permissions.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvPermission;

        public CustomViewHolder(View view) {
            super(view);
            this.tvPermission = (TextView) view.findViewById(R.id.tvPermission);
        }
    }
}
