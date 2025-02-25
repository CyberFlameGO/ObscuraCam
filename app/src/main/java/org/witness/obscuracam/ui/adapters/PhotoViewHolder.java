package org.witness.obscuracam.ui.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.witness.sscphase1.R;

public class PhotoViewHolder extends RecyclerView.ViewHolder {
    final View mRootView;
    final ImageView mPhoto;
    final ImageView mVideoIcon;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        this.mRootView = itemView;
        this.mPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
        this.mVideoIcon = (ImageView) itemView.findViewById(R.id.ivVideoIcon);
    }
}
