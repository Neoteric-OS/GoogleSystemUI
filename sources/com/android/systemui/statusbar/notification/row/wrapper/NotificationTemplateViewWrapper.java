package com.android.systemui.statusbar.notification.row.wrapper;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.util.ArraySet;
import android.util.Pools;
import android.view.NotificationHeaderView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.internal.util.ContrastColorUtil;
import com.android.internal.widget.NotificationActionListLayout;
import com.android.systemui.Dependency;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.ImageTransformState;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationTemplateViewWrapper extends NotificationHeaderViewWrapper {
    public NotificationActionListLayout mActions;
    public View mActionsContainer;
    public final boolean mAllowHideHeader;
    public boolean mCanHideHeader;
    final ArraySet mCancelledPendingIntents;
    public final int mFullHeaderTranslation;
    public float mHeaderTranslation;
    public ImageView mLeftIcon;
    public ProgressBar mProgressBar;
    public View mRemoteInputHistory;
    public ImageView mRightIcon;
    public View mSmartReplyContainer;
    public TextView mText;
    public TextView mTitle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper$1, reason: invalid class name */
    public final class AnonymousClass1 extends ViewTransformationHelper.CustomTransformation {
        @Override // com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation
        public final boolean customTransformTarget(TransformState transformState, TransformState transformState2) {
            int[] laidOutLocationOnScreen = transformState2.getLaidOutLocationOnScreen();
            int[] laidOutLocationOnScreen2 = transformState.getLaidOutLocationOnScreen();
            transformState.mTransformationEndY = ((transformState2.mTransformedView.getHeight() + laidOutLocationOnScreen[1]) - laidOutLocationOnScreen2[1]) * 0.33f;
            return true;
        }

        @Override // com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation
        public final boolean initTransformation(TransformState transformState, TransformState transformState2) {
            int[] laidOutLocationOnScreen = transformState2.getLaidOutLocationOnScreen();
            int[] laidOutLocationOnScreen2 = transformState.getLaidOutLocationOnScreen();
            transformState.mTransformedView.setTag(R.id.transformation_start_y_tag, Float.valueOf(((transformState2.mTransformedView.getHeight() + laidOutLocationOnScreen[1]) - laidOutLocationOnScreen2[1]) * 0.33f));
            return true;
        }

        @Override // com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation
        public final boolean transformFrom(TransformState transformState, TransformableView transformableView, float f) {
            if (!(transformableView instanceof HybridNotificationView)) {
                return false;
            }
            TransformState currentState = ((HybridNotificationView) transformableView).mTransformationHelper.getCurrentState(1);
            CrossFadeHelper.fadeIn(transformState.mTransformedView, f, true);
            if (currentState != null) {
                transformState.transformViewFrom(currentState, 16, this, f);
                currentState.recycle();
            }
            return true;
        }

        @Override // com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation
        public final boolean transformTo(TransformState transformState, TransformableView transformableView, float f) {
            if (!(transformableView instanceof HybridNotificationView)) {
                return false;
            }
            TransformState currentState = ((HybridNotificationView) transformableView).mTransformationHelper.getCurrentState(1);
            CrossFadeHelper.fadeOut(transformState.mTransformedView, f, true);
            if (currentState != null) {
                transformState.transformViewTo(currentState, 16, this, f);
                currentState.recycle();
            }
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ActionPendingIntentCancellationHandler implements View.OnAttachStateChangeListener {
        public static UiOffloadThread sUiOffloadThread;
        public final AnonymousClass1 mCancelListener = new AnonymousClass1();
        public final NotificationTemplateViewWrapper$$ExternalSyntheticLambda0 mOnCancelledCallback;
        public final PendingIntent mPendingIntent;
        public final View mView;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper$ActionPendingIntentCancellationHandler$1, reason: invalid class name */
        public final class AnonymousClass1 implements PendingIntent.CancelListener {
            public AnonymousClass1() {
            }

            public final void onCanceled(final PendingIntent pendingIntent) {
                ActionPendingIntentCancellationHandler.this.mView.post(new Runnable() { // from class: com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper$ActionPendingIntentCancellationHandler$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        NotificationTemplateViewWrapper.ActionPendingIntentCancellationHandler.AnonymousClass1 anonymousClass1 = NotificationTemplateViewWrapper.ActionPendingIntentCancellationHandler.AnonymousClass1.this;
                        NotificationTemplateViewWrapper.ActionPendingIntentCancellationHandler.this.mOnCancelledCallback.accept(pendingIntent);
                        NotificationTemplateViewWrapper.ActionPendingIntentCancellationHandler.this.remove();
                    }
                });
            }
        }

        public ActionPendingIntentCancellationHandler(PendingIntent pendingIntent, View view, NotificationTemplateViewWrapper$$ExternalSyntheticLambda0 notificationTemplateViewWrapper$$ExternalSyntheticLambda0) {
            this.mPendingIntent = pendingIntent;
            this.mView = view;
            this.mOnCancelledCallback = notificationTemplateViewWrapper$$ExternalSyntheticLambda0;
        }

        public static UiOffloadThread getUiOffloadThread() {
            if (sUiOffloadThread == null) {
                sUiOffloadThread = (UiOffloadThread) Dependency.sDependency.getDependencyInner(UiOffloadThread.class);
            }
            return sUiOffloadThread;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            UiOffloadThread uiOffloadThread = getUiOffloadThread();
            uiOffloadThread.mExecutorService.submit(new NotificationTemplateViewWrapper$ActionPendingIntentCancellationHandler$$ExternalSyntheticLambda0(this, 0));
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            UiOffloadThread uiOffloadThread = getUiOffloadThread();
            uiOffloadThread.mExecutorService.submit(new NotificationTemplateViewWrapper$ActionPendingIntentCancellationHandler$$ExternalSyntheticLambda0(this, 1));
        }

        public final void remove() {
            this.mView.removeOnAttachStateChangeListener(this);
            if (this.mView.getTag(R.id.pending_intent_listener_tag) == this) {
                this.mView.setTag(R.id.pending_intent_listener_tag, null);
            }
            UiOffloadThread uiOffloadThread = getUiOffloadThread();
            uiOffloadThread.mExecutorService.submit(new NotificationTemplateViewWrapper$ActionPendingIntentCancellationHandler$$ExternalSyntheticLambda0(this, 2));
        }
    }

    public NotificationTemplateViewWrapper(Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        super(context, view, expandableNotificationRow);
        this.mCancelledPendingIntents = new ArraySet();
        this.mAllowHideHeader = context.getResources().getBoolean(R.bool.heads_up_notification_hides_header);
        ViewTransformationHelper viewTransformationHelper = this.mTransformationHelper;
        viewTransformationHelper.mCustomTransformations.put(2, new AnonymousClass1());
        this.mFullHeaderTranslation = context.getResources().getDimensionPixelSize(android.R.dimen.notification_expand_button_pill_height) - context.getResources().getDimensionPixelSize(android.R.dimen.notification_header_app_name_margin_start);
    }

    public final void disableActionView(Button button) {
        if (button.isEnabled()) {
            button.setEnabled(false);
            ColorStateList textColors = button.getTextColors();
            int[] colors = textColors.getColors();
            int[] iArr = new int[colors.length];
            float f = this.mView.getResources().getFloat(android.R.dimen.notification_actions_padding_start);
            for (int i = 0; i < colors.length; i++) {
                int i2 = colors[i];
                iArr[i] = ContrastColorUtil.compositeColors(Color.argb((int) (255.0f * f), Color.red(i2), Color.green(i2), Color.blue(i2)), resolveBackgroundColor());
            }
            button.setTextColor(new ColorStateList(textColors.getStates(), iArr));
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final int getExtraMeasureHeight() {
        NotificationActionListLayout notificationActionListLayout = this.mActions;
        int extraMeasureHeight = notificationActionListLayout != null ? notificationActionListLayout.getExtraMeasureHeight() : 0;
        View view = this.mRemoteInputHistory;
        return (view == null || view.getVisibility() == 8) ? extraMeasureHeight : extraMeasureHeight + this.mRow.getContext().getResources().getDimensionPixelSize(R.dimen.remote_input_history_extra_height);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final int getHeaderTranslation(boolean z) {
        return (z && this.mCanHideHeader) ? this.mFullHeaderTranslation : (int) this.mHeaderTranslation;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public void onContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
        ImageView imageView;
        ActionPendingIntentCancellationHandler actionPendingIntentCancellationHandler;
        Bitmap bitmap;
        Icon largeIcon;
        Bitmap bitmap2;
        boolean z = true;
        StatusBarNotification statusBarNotification = expandableNotificationRow.mEntry.mSbn;
        ImageView imageView2 = (ImageView) this.mView.findViewById(android.R.id.scrollView);
        this.mRightIcon = imageView2;
        if (imageView2 != null) {
            Pools.SimplePool simplePool = ImageTransformState.sInstancePool;
            Notification notification = statusBarNotification.getNotification();
            if ((!notification.extras.getBoolean("android.showBigPictureWhenCollapsed") || !notification.isStyle(Notification.BigPictureStyle.class) || (largeIcon = Notification.BigPictureStyle.getPictureIcon(notification.extras)) == null) && (largeIcon = notification.getLargeIcon()) == null && (bitmap2 = notification.largeIcon) != null) {
                largeIcon = Icon.createWithBitmap(bitmap2);
            }
            imageView2.setTag(R.id.image_icon_tag, largeIcon);
            ImageView imageView3 = this.mRightIcon;
            Pools.SimplePool simplePool2 = TransformState.sInstancePool;
            imageView3.setTag(R.id.align_transform_end_tag, Boolean.TRUE);
        }
        ImageView imageView4 = (ImageView) this.mView.findViewById(android.R.id.lock_screen);
        this.mLeftIcon = imageView4;
        if (imageView4 != null) {
            Pools.SimplePool simplePool3 = ImageTransformState.sInstancePool;
            Notification notification2 = statusBarNotification.getNotification();
            Icon largeIcon2 = notification2.getLargeIcon();
            if (largeIcon2 == null && (bitmap = notification2.largeIcon) != null) {
                largeIcon2 = Icon.createWithBitmap(bitmap);
            }
            imageView4.setTag(R.id.image_icon_tag, largeIcon2);
        }
        this.mTitle = (TextView) this.mView.findViewById(android.R.id.title);
        this.mText = (TextView) this.mView.findViewById(android.R.id.textPersonName);
        View findViewById = this.mView.findViewById(android.R.id.progress);
        if (findViewById instanceof ProgressBar) {
            this.mProgressBar = (ProgressBar) findViewById;
        } else {
            this.mProgressBar = null;
        }
        this.mSmartReplyContainer = this.mView.findViewById(android.R.id.spinner);
        this.mActionsContainer = this.mView.findViewById(android.R.id.alertTitle);
        this.mActions = this.mView.findViewById(android.R.id.alarm);
        this.mRemoteInputHistory = this.mView.findViewById(android.R.id.off);
        NotificationActionListLayout notificationActionListLayout = this.mActions;
        if (notificationActionListLayout != null) {
            int childCount = notificationActionListLayout.getChildCount();
            ArraySet arraySet = new ArraySet(childCount);
            for (int i = 0; i < childCount; i++) {
                Button button = (Button) this.mActions.getChildAt(i);
                PendingIntent pendingIntent = (PendingIntent) button.getTag(android.R.id.personalInfo);
                if (pendingIntent != null) {
                    int identityHashCode = System.identityHashCode(pendingIntent.getTarget().asBinder());
                    arraySet.add(Integer.valueOf(identityHashCode));
                    if (this.mCancelledPendingIntents.contains(Integer.valueOf(identityHashCode))) {
                        disableActionView(button);
                    }
                }
                if (pendingIntent != null) {
                    ActionPendingIntentCancellationHandler actionPendingIntentCancellationHandler2 = new ActionPendingIntentCancellationHandler(pendingIntent, button, new NotificationTemplateViewWrapper$$ExternalSyntheticLambda0(this));
                    button.addOnAttachStateChangeListener(actionPendingIntentCancellationHandler2);
                    actionPendingIntentCancellationHandler = actionPendingIntentCancellationHandler2;
                    if (button.isAttachedToWindow()) {
                        actionPendingIntentCancellationHandler2.onViewAttachedToWindow(button);
                        actionPendingIntentCancellationHandler = actionPendingIntentCancellationHandler2;
                    }
                } else {
                    actionPendingIntentCancellationHandler = null;
                }
                ActionPendingIntentCancellationHandler actionPendingIntentCancellationHandler3 = (ActionPendingIntentCancellationHandler) button.getTag(R.id.pending_intent_listener_tag);
                if (actionPendingIntentCancellationHandler3 != null) {
                    actionPendingIntentCancellationHandler3.remove();
                }
                button.setTag(R.id.pending_intent_listener_tag, actionPendingIntentCancellationHandler);
            }
            this.mCancelledPendingIntents.retainAll(arraySet);
        }
        super.onContentUpdated(expandableNotificationRow);
        if (!this.mAllowHideHeader || this.mNotificationHeader == null || ((imageView = this.mRightIcon) != null && imageView.getVisibility() == 0)) {
            z = false;
        }
        this.mCanHideHeader = z;
        float f = expandableNotificationRow.mHeaderVisibleAmount;
        if (f != 1.0f) {
            setHeaderVisibleAmount(f);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void setContentHeight(int i, int i2) {
        if (this.mActionsContainer != null) {
            this.mActionsContainer.setTranslationY((Math.max(i, i2) - this.mView.getHeight()) - getHeaderTranslation(false));
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void setHeaderVisibleAmount(float f) {
        float f2;
        NotificationHeaderView notificationHeaderView;
        if (!this.mCanHideHeader || (notificationHeaderView = this.mNotificationHeader) == null) {
            f2 = 0.0f;
        } else {
            notificationHeaderView.setAlpha(f);
            f2 = (1.0f - f) * this.mFullHeaderTranslation;
        }
        this.mHeaderTranslation = f2;
        this.mView.setTranslationY(f2);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public boolean shouldClipToRounding(boolean z) {
        View view;
        return (!z || (view = this.mActionsContainer) == null || view.getVisibility() == 8) ? false : true;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper
    public void updateTransformedTypes() {
        super.updateTransformedTypes();
        TextView textView = this.mTitle;
        ViewTransformationHelper viewTransformationHelper = this.mTransformationHelper;
        if (textView != null) {
            viewTransformationHelper.addTransformedView(textView, 1);
        }
        TextView textView2 = this.mText;
        if (textView2 != null) {
            viewTransformationHelper.addTransformedView(textView2, 2);
        }
        ImageView imageView = this.mRightIcon;
        if (imageView != null) {
            viewTransformationHelper.addTransformedView(imageView, 3);
        }
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            viewTransformationHelper.addTransformedView(progressBar, 4);
        }
        addViewsTransformingToSimilar(this.mLeftIcon);
        addTransformedViews(this.mSmartReplyContainer);
    }
}
