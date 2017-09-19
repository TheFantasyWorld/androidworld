package com.androidworld.app.ui.adapter;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.androidworld.app.R;
import com.androidworld.app.bean.WidgetItem;
import com.androidworld.app.bean.WidgetType;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final WidgetType widgetType = (WidgetType) item;
                holder.setText(R.id.tv_title, widgetType.title);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (widgetType.isExpanded()) {
                            collapse(pos);
                            final ValueAnimator rotateIconAnimator = ValueAnimator.ofFloat(
                                    holder.getView(R.id.iv).getRotation(), 45f);
                            rotateIconAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                            rotateIconAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    holder.getView(R.id.iv).setRotation((float) animation.getAnimatedValue());
                                }
                            });
                            rotateIconAnimator.start();
                        } else {
                            expand(pos);
                            final ValueAnimator rotateIconAnimator = ValueAnimator.ofFloat(
                                    holder.getView(R.id.iv).getRotation(), 0f);
                            rotateIconAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                            rotateIconAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    holder.getView(R.id.iv).setRotation((float) animation.getAnimatedValue());
                                }
                            });
                            rotateIconAnimator.start();
                        }
                    }
                });
                break;

            case TYPE_LEVEL_1:
                final WidgetItem widgetItem = (WidgetItem) item;
                holder.setText(R.id.tv_title, widgetItem.title);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, widgetItem.activity));
                    }
                });
                break;
        }
    }
}
