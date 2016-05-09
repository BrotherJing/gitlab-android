package io.dongyue.gitlabandroid.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import butterknife.Bind;
import butterknife.ButterKnife;
import io.dongyue.gitlabandroid.R;
import io.dongyue.gitlabandroid.model.api.ProjectNamespace;

/**
 * Shows a button to take you to a group
 */
public class ProjectMemberFooterViewHolder extends RecyclerView.ViewHolder{

    public static ProjectMemberFooterViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.footer_project_member, parent, false);
        return new ProjectMemberFooterViewHolder(view);
    }

    @Bind(R.id.button) Button mButton;

    public ProjectMemberFooterViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(ProjectNamespace namespace) {
        mButton.setText(String.format(itemView.getResources().getString(R.string.group_members), namespace.getName()));
    }
}
