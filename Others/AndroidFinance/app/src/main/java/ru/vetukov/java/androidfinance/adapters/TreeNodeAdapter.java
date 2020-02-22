package ru.vetukov.java.androidfinance.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import ru.vetukov.java.androidfinance.R;
import ru.vetukov.java.androidfinance.fragments.SprListFragment;
import ru.vetukov.java.core.database.Initializer;
import ru.vetukov.java.core.interfaces.Source;
import ru.vetukov.java.core.interfaces.TreeNode;

import java.util.List;

public class TreeNodeAdapter extends RecyclerView.Adapter<TreeNodeAdapter.ViewHolder> {

     private List<? extends TreeNode> list;
     private final SprListFragment.OnListFragmentInteractionListener mListener;
     private Context context;

    public TreeNodeAdapter(List<? extends TreeNode> items,
                           SprListFragment.OnListFragmentInteractionListener listener,
                           Context context) {
        list = items;
        mListener = listener;
        this.context = context;
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
            holder.mTVChCount.setText("");

        initPopup(holder.mSprPopup, context, node, position);

        holder.mSprNameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN :

                        holder.mSprImage.setImageResource(R.drawable.ic_star_gold);

                        break;
                    case MotionEvent.ACTION_UP :

                        holder.mSprImage.setImageResource(R.drawable.ic_star_grey);

                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.onListFragmentInteraction(node);
                        }

                        if (node.hasChilds()) {     // если есть дочерние значения
                            updateData(node.getChilds());
                        }

                        break;
                }

                return true;
            }
        });

    }

    private void initPopup(ImageView mSprPopup, Context context, TreeNode node, int position) {
        mSprPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(context, mSprPopup);
                dropDownMenu.getMenuInflater().inflate(R.menu.spr_popup_menu, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_add :

                                break;
                            case R.id.item_edit :

                                break;
                            case R.id.item_delete :

                                new AlertDialog.Builder(context)
                                        .setTitle("Confirm")
                                        .setMessage("Delete record?")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Initializer.getSourceSync().delete((Source) node);
                                                notifyDataSetChanged();
                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        })
                                        .show();

                                break;
                        }

                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<? extends TreeNode> list) {
        this.list = list;

        mListener.onOnBackImageVisibility(list.get(0).hasParent());

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTVTitle;
        public final TextView mTVChCount;
        public final ImageView mSprImage;
        public final ImageView mSprPopup;
        public final View mSprNameView;

        public ViewHolder(View view) {
            super(view);
            mTVTitle = view.findViewById(R.id.spr_name);
            mTVChCount = view.findViewById(R.id.spr_child_count);
            mSprImage= view.findViewById(R.id.spr_image);
            mSprPopup = view.findViewById(R.id.spr_popup);
            mSprNameView = view.findViewById(R.id.spr_name_view);
        }

        @Override
        public String toString() {
            return mTVTitle.getText().toString();
        }
    }
}
