package ru.vetukov.java.androidfinance.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.vetukov.java.androidfinance.R;
import ru.vetukov.java.androidfinance.fragments.SprListFragment;
import ru.vetukov.java.core.interfaces.TreeNode;

import java.util.List;

public class TreeNodeAdapter extends RecyclerView.Adapter<TreeNodeAdapter.ViewHolder> {

     private List<? extends TreeNode> list;
     private final SprListFragment.OnListFragmentInteractionListener mListener;

    public TreeNodeAdapter(List<? extends TreeNode> items,
                           SprListFragment.OnListFragmentInteractionListener listener) {
        list = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spr_node, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        TreeNode node = list.get(position);

        holder.mTVTitle.setText(node.getName());

        if (node.hasChilds())
            holder.mTVChCount.setText(String.format("%d",node.getChilds().size()));
        else
            holder.mTVChCount.setText("0");

        holder.mSprNameView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(node);
            }

            if (node.hasChilds()) {     // если есть дочерние значения
                updateData(node.getChilds());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<? extends TreeNode> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTVTitle;
        public final TextView mTVChCount;
        public final View mSprNameView;

        public ViewHolder(View view) {
            super(view);
            mTVTitle = view.findViewById(R.id.spr_name);
            mTVChCount = view.findViewById(R.id.spr_child_count);
            mSprNameView = view.findViewById(R.id.spr_name_view);
        }

        @Override
        public String toString() {
            return mTVTitle.getText().toString();
        }
    }
}
