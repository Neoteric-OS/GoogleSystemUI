package com.android.systemui.statusbar.notification.row.wrapper;

import android.app.Notification;
import android.content.Context;
import android.util.ArraySet;
import android.util.Pools;
import android.view.NotificationHeaderView;
import android.view.NotificationTopLineView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.DateTimeView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.internal.widget.CachingIconView;
import com.android.internal.widget.NotificationCloseButton;
import com.android.internal.widget.NotificationExpandButton;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.FeedbackIcon;
import com.android.systemui.statusbar.notification.ImageTransformState;
import com.android.systemui.statusbar.notification.Roundable;
import com.android.systemui.statusbar.notification.RoundableState;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda3;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer$$ExternalSyntheticLambda0;
import com.android.wm.shell.R;
import java.util.Stack;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationHeaderViewWrapper extends NotificationViewWrapper implements Roundable {
    public static final Interpolator LOW_PRIORITY_HEADER_CLOSE = new PathInterpolator(0.4f, 0.0f, 0.7f, 1.0f);
    public View mAltExpandTarget;
    public View mAudiblyAlertedIcon;
    public NotificationCloseButton mCloseButton;
    public NotificationExpandButton mExpandButton;
    public View mFeedbackIcon;
    public TextView mHeaderText;
    public CachingIconView mIcon;
    public View mIconContainer;
    public boolean mIsLowPriority;
    public NotificationHeaderView mNotificationHeader;
    public NotificationTopLineView mNotificationTopLine;
    public final RoundableState mRoundableState;
    public NotificationChildrenContainer$$ExternalSyntheticLambda0 mRoundnessChangedListener;
    public boolean mTransformLowPriorityTitle;
    public final ViewTransformationHelper mTransformationHelper;
    public ImageView mWorkProfileImage;

    public NotificationHeaderViewWrapper(Context context, View view, final ExpandableNotificationRow expandableNotificationRow) {
        super(view, expandableNotificationRow);
        this.mRoundableState = new RoundableState(this.mView, this, context.getResources().getDimension(R.dimen.notification_corner_radius));
        ViewTransformationHelper viewTransformationHelper = new ViewTransformationHelper();
        this.mTransformationHelper = viewTransformationHelper;
        viewTransformationHelper.mCustomTransformations.put(1, new ViewTransformationHelper.CustomTransformation() { // from class: com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper.1
            @Override // com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation
            public final Interpolator getCustomInterpolator(int i, boolean z) {
                boolean z2 = NotificationHeaderViewWrapper.this.mView instanceof NotificationHeaderView;
                if (i == 16) {
                    return ((!z2 || z) && (z2 || !z)) ? NotificationHeaderViewWrapper.LOW_PRIORITY_HEADER_CLOSE : Interpolators.LINEAR_OUT_SLOW_IN;
                }
                return null;
            }

            @Override // com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation
            public final boolean transformFrom(TransformState transformState, TransformableView transformableView, float f) {
                TransformState currentState;
                NotificationHeaderViewWrapper notificationHeaderViewWrapper = NotificationHeaderViewWrapper.this;
                if (!(notificationHeaderViewWrapper.mIsLowPriority && notificationHeaderViewWrapper.mTransformLowPriorityTitle) || (currentState = transformableView.getCurrentState(1)) == null) {
                    return false;
                }
                CrossFadeHelper.fadeIn(transformState.mTransformedView, f, true);
                transformState.transformViewFrom(currentState, 17, this, f);
                currentState.recycle();
                return true;
            }

            @Override // com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation
            public final boolean transformTo(TransformState transformState, TransformableView transformableView, float f) {
                TransformState currentState;
                NotificationHeaderViewWrapper notificationHeaderViewWrapper = NotificationHeaderViewWrapper.this;
                if (!(notificationHeaderViewWrapper.mIsLowPriority && notificationHeaderViewWrapper.mTransformLowPriorityTitle) || (currentState = transformableView.getCurrentState(1)) == null) {
                    return false;
                }
                CrossFadeHelper.fadeOut(transformState.mTransformedView, f, true);
                transformState.transformViewTo(currentState, 17, this, f);
                currentState.recycle();
                return true;
            }
        });
        resolveHeaderViews();
        ExpandableNotificationRow$$ExternalSyntheticLambda3 expandableNotificationRow$$ExternalSyntheticLambda3 = expandableNotificationRow.mOnFeedbackClickListener;
        NotificationTopLineView notificationTopLineView = this.mNotificationTopLine;
        if (notificationTopLineView != null) {
            notificationTopLineView.setFeedbackOnClickListener(expandableNotificationRow$$ExternalSyntheticLambda3);
        }
        View view2 = this.mFeedbackIcon;
        if (view2 != null) {
            view2.setOnClickListener(expandableNotificationRow$$ExternalSyntheticLambda3);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.row.ExpandableNotificationRow$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                ExpandableNotificationRow expandableNotificationRow2 = ExpandableNotificationRow.this;
                SourceType$Companion$from$1 sourceType$Companion$from$1 = ExpandableNotificationRow.BASE_VALUE;
                if (expandableNotificationRow2 != null) {
                    expandableNotificationRow2.performDismiss(false);
                }
            }
        };
        NotificationCloseButton notificationCloseButton = this.mCloseButton;
        if (notificationCloseButton != null) {
            notificationCloseButton.setOnClickListener(onClickListener);
        }
    }

    public final void addTransformedViews(View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                this.mTransformationHelper.addTransformedView(view);
            }
        }
    }

    public final void addViewsTransformingToSimilar(View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                this.mTransformationHelper.addViewTransformingToSimilar(view);
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.Roundable
    public final void applyRoundnessAndInvalidate() {
        NotificationChildrenContainer$$ExternalSyntheticLambda0 notificationChildrenContainer$$ExternalSyntheticLambda0 = this.mRoundnessChangedListener;
        if (notificationChildrenContainer$$ExternalSyntheticLambda0 != null) {
            notificationChildrenContainer$$ExternalSyntheticLambda0.f$0.invalidate();
        }
        super.applyRoundnessAndInvalidate();
    }

    @Override // com.android.systemui.statusbar.notification.Roundable
    public final int getClipHeight() {
        return this.mView.getHeight();
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper, com.android.systemui.statusbar.TransformableView
    public final TransformState getCurrentState(int i) {
        return this.mTransformationHelper.getCurrentState(i);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final View getExpandButton() {
        return this.mExpandButton;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final CachingIconView getIcon() {
        return this.mIcon;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final NotificationHeaderView getNotificationHeader() {
        return this.mNotificationHeader;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final int getOriginalIconColor() {
        return this.mIcon.getOriginalIconColor();
    }

    @Override // com.android.systemui.statusbar.notification.Roundable
    public final RoundableState getRoundableState() {
        return this.mRoundableState;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public View getShelfTransformationTarget() {
        return this.mIcon;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public void onContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
        int id;
        this.mIsLowPriority = expandableNotificationRow.mEntry.mRanking.isAmbient();
        this.mTransformLowPriorityTitle = (expandableNotificationRow.isChildInGroup() || expandableNotificationRow.mIsSummaryWithChildren) ? false : true;
        ViewTransformationHelper viewTransformationHelper = this.mTransformationHelper;
        viewTransformationHelper.getClass();
        ArraySet arraySet = new ArraySet(viewTransformationHelper.mTransformedViews.values());
        resolveHeaderViews();
        updateTransformedTypes();
        View view = this.mView;
        int size = viewTransformationHelper.mTransformedViews.size();
        for (int i = 0; i < size; i++) {
            Object valueAt = viewTransformationHelper.mTransformedViews.valueAt(i);
            while (true) {
                View view2 = (View) valueAt;
                if (view2 != view.getParent()) {
                    view2.setTag(R.id.contains_transformed_view, Boolean.TRUE);
                    valueAt = view2.getParent();
                }
            }
        }
        Stack stack = new Stack();
        stack.push(view);
        while (!stack.isEmpty()) {
            View view3 = (View) stack.pop();
            if (((Boolean) view3.getTag(R.id.contains_transformed_view)) != null || (id = view3.getId()) == -1) {
                view3.setTag(R.id.contains_transformed_view, null);
                if ((view3 instanceof ViewGroup) && !viewTransformationHelper.mTransformedViews.containsValue(view3)) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        stack.push(viewGroup.getChildAt(i2));
                    }
                }
            } else {
                viewTransformationHelper.addTransformedView(view3, id);
            }
        }
        Stack stack2 = new Stack();
        stack2.push(this.mView);
        while (!stack2.isEmpty()) {
            View view4 = (View) stack2.pop();
            if ((view4 instanceof ImageView) && view4.getId() != 16908958) {
                ((ImageView) view4).setCropToPadding(true);
            } else if (view4 instanceof ViewGroup) {
                ViewGroup viewGroup2 = (ViewGroup) view4;
                for (int i3 = 0; i3 < viewGroup2.getChildCount(); i3++) {
                    stack2.push(viewGroup2.getChildAt(i3));
                }
            }
        }
        Notification notification = expandableNotificationRow.mEntry.mSbn.getNotification();
        if (notification.shouldUseAppIcon()) {
            CachingIconView cachingIconView = this.mIcon;
            Pools.SimplePool simplePool = ImageTransformState.sInstancePool;
            cachingIconView.setTag(R.id.image_icon_tag, notification.getAppIcon());
        } else {
            CachingIconView cachingIconView2 = this.mIcon;
            Pools.SimplePool simplePool2 = ImageTransformState.sInstancePool;
            cachingIconView2.setTag(R.id.image_icon_tag, notification.getSmallIcon());
        }
        ArraySet arraySet2 = new ArraySet(viewTransformationHelper.mTransformedViews.values());
        for (int i4 = 0; i4 < arraySet.size(); i4++) {
            View view5 = (View) arraySet.valueAt(i4);
            if (!arraySet2.contains(view5)) {
                TransformState createFrom = TransformState.createFrom(view5, viewTransformationHelper);
                createFrom.setVisible(true, true);
                createFrom.recycle();
            }
        }
    }

    public final void resolveHeaderViews() {
        this.mIcon = this.mView.findViewById(android.R.id.icon);
        this.mHeaderText = (TextView) this.mView.findViewById(android.R.id.hour);
        this.mExpandButton = this.mView.findViewById(android.R.id.feedbackAllMask);
        this.mAltExpandTarget = this.mView.findViewById(android.R.id.arrow);
        this.mIconContainer = this.mView.findViewById(android.R.id.date_picker_header_year);
        this.mWorkProfileImage = (ImageView) this.mView.findViewById(android.R.id.radio);
        this.mNotificationHeader = this.mView.findViewById(android.R.id.numberPassword);
        this.mNotificationTopLine = this.mView.findViewById(android.R.id.open_cross_profile);
        this.mAudiblyAlertedIcon = this.mView.findViewById(android.R.id.animation);
        this.mFeedbackIcon = this.mView.findViewById(android.R.id.fingerprints);
        this.mCloseButton = this.mView.findViewById(android.R.id.content_preview_file_icon);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public void setFeedbackIcon(FeedbackIcon feedbackIcon) {
        View view = this.mFeedbackIcon;
        if (view != null) {
            view.setVisibility(feedbackIcon != null ? 0 : 8);
            if (feedbackIcon != null) {
                View view2 = this.mFeedbackIcon;
                if (view2 instanceof ImageButton) {
                    ((ImageButton) view2).setImageResource(feedbackIcon.iconRes);
                }
                this.mFeedbackIcon.setContentDescription(this.mView.getContext().getString(feedbackIcon.contentDescRes));
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void setIsChildInGroup(boolean z) {
        this.mTransformLowPriorityTitle = !z;
    }

    public final void setNotificationWhen(long j) {
        DateTimeView findViewById = this.mView.findViewById(android.R.id.to_org_unit);
        if (findViewById instanceof DateTimeView) {
            findViewById.setTime(j);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public void setRecentlyAudiblyAlerted(boolean z) {
        View view = this.mAudiblyAlertedIcon;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    @Override // com.android.systemui.statusbar.TransformableView
    public final void setVisible(boolean z) {
        this.mView.animate().cancel();
        this.mView.setVisibility(z ? 0 : 4);
        this.mTransformationHelper.setVisible(z);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper, com.android.systemui.statusbar.TransformableView
    public final void transformFrom(TransformableView transformableView) {
        this.mTransformationHelper.transformFrom(transformableView);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper, com.android.systemui.statusbar.TransformableView
    public final void transformTo(TransformableView transformableView, Runnable runnable) {
        this.mTransformationHelper.transformTo(transformableView, runnable);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public void updateExpandability(boolean z, ExpandableNotificationRow.AnonymousClass1 anonymousClass1, boolean z2) {
        this.mExpandButton.setVisibility(z ? 0 : 8);
        this.mExpandButton.setOnClickListener(z ? anonymousClass1 : null);
        View view = this.mAltExpandTarget;
        if (view != null) {
            view.setOnClickListener(z ? anonymousClass1 : null);
        }
        View view2 = this.mIconContainer;
        if (view2 != null) {
            view2.setOnClickListener(z ? anonymousClass1 : null);
        }
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView != null) {
            if (!z) {
                anonymousClass1 = null;
            }
            notificationHeaderView.setOnClickListener(anonymousClass1);
        }
        if (z2) {
            this.mExpandButton.getParent().requestLayout();
        }
    }

    public void updateTransformedTypes() {
        TextView textView;
        ViewTransformationHelper viewTransformationHelper = this.mTransformationHelper;
        viewTransformationHelper.mTransformedViews.clear();
        viewTransformationHelper.mKeysTransformingToSimilar.clear();
        viewTransformationHelper.addTransformedView(this.mIcon, 0);
        viewTransformationHelper.addTransformedView(this.mExpandButton, 6);
        if (this.mIsLowPriority && (textView = this.mHeaderText) != null) {
            viewTransformationHelper.addTransformedView(textView, 1);
        }
        addViewsTransformingToSimilar(this.mWorkProfileImage, this.mAudiblyAlertedIcon, this.mFeedbackIcon);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper, com.android.systemui.statusbar.TransformableView
    public final void transformFrom(float f, TransformableView transformableView) {
        this.mTransformationHelper.transformFrom(f, transformableView);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper, com.android.systemui.statusbar.TransformableView
    public final void transformTo(float f, TransformableView transformableView) {
        this.mTransformationHelper.transformTo(f, transformableView);
    }
}
