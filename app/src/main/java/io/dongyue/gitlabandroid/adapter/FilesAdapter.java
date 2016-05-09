package io.dongyue.gitlabandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.dongyue.gitlabandroid.R;
import io.dongyue.gitlabandroid.model.api.RepositoryTreeObject;

/**
 * Shows the files
 * Created by Jawnnypoo on 11/22/2015.
 */
public class FilesAdapter extends RecyclerView.Adapter<FileViewHolder> {

    public interface Listener {
        void onFolderClicked(RepositoryTreeObject treeItem);
        void onFileClicked(RepositoryTreeObject treeItem);
        void onCopyClicked(RepositoryTreeObject treeItem);
        void onShareClicked(RepositoryTreeObject treeItem);
        void onOpenInBrowserClicked(RepositoryTreeObject treeItem);
    }
    private Listener mListener;
    private ArrayList<RepositoryTreeObject> mValues;

    private final View.OnClickListener onProjectClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.id.list_position);
            RepositoryTreeObject treeItem = getValueAt(position);

            if (treeItem.getType() == RepositoryTreeObject.Type.FOLDER) {
                mListener.onFolderClicked(treeItem);
            } else if (treeItem.getType() == RepositoryTreeObject.Type.FILE) {
                mListener.onFileClicked(treeItem);
            }
        }
    };

    public FilesAdapter(Listener listener) {
        mListener = listener;
        mValues = new ArrayList<>();
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FileViewHolder holder = FileViewHolder.inflate(parent);
        holder.itemView.setOnClickListener(onProjectClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(final FileViewHolder holder, int position) {
        final RepositoryTreeObject treeItem = getValueAt(position);
        holder.bind(treeItem);
        holder.itemView.setTag(R.id.list_position, position);
        holder.popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_copy:
                        mListener.onCopyClicked(treeItem);
                        return true;
                    case R.id.action_share:
                        mListener.onShareClicked(treeItem);
                        return true;
                    case R.id.action_open:
                        mListener.onOpenInBrowserClicked(treeItem);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setData(Collection<RepositoryTreeObject> values) {
        mValues.clear();
        if (values != null) {
            mValues.addAll(values);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        mValues.clear();
        notifyDataSetChanged();
    }

    public RepositoryTreeObject getValueAt(int position) {
        return mValues.get(position);
    }
}
class FileViewHolder extends RecyclerView.ViewHolder {

    public static FileViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Bind(R.id.file_title) TextView mTitleView;
    @Bind(R.id.file_image) ImageView mImageView;
    @Bind(R.id.file_more) ImageView mMoreView;

    public final PopupMenu popupMenu;

    public FileViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

        popupMenu = new PopupMenu(itemView.getContext(), mMoreView);
        popupMenu.getMenuInflater().inflate(R.menu.item_menu_file, popupMenu.getMenu());

        mMoreView.setOnClickListener(v -> popupMenu.show());
    }

    public void bind(RepositoryTreeObject treeItem) {
        mTitleView.setText(treeItem.getName());
        mImageView.setImageResource(treeItem.getDrawableForType());
    }
}
