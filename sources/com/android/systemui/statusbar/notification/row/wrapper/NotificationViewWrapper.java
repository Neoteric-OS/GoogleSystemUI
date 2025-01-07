package com.android.systemui.statusbar.notification.row.wrapper;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.util.ContrastColorUtil;
import com.android.internal.widget.CachingIconView;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.notification.FeedbackIcon;
import com.android.systemui.statusbar.notification.NotificationFadeAware;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationViewWrapper implements TransformableView {
    public int mBackgroundColor;
    public final ExpandableNotificationRow mRow;
    public final View mView;

    public NotificationViewWrapper(View view, ExpandableNotificationRow expandableNotificationRow) {
        new Rect();
        this.mBackgroundColor = 0;
        this.mView = view;
        this.mRow = expandableNotificationRow;
        onReinflated();
    }

    public static int getBackgroundColor(View view) {
        if (view == null) {
            return 0;
        }
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            return ((ColorDrawable) background).getColor();
        }
        return 0;
    }

    public static void invertViewLuminosity(View view) {
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix.setRGB2YUV();
        colorMatrix2.set(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        colorMatrix.postConcat(colorMatrix2);
        colorMatrix2.setYUV2RGB();
        colorMatrix.postConcat(colorMatrix2);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        view.setLayerType(2, paint);
    }

    public static NotificationViewWrapper wrap(Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        if (view.getId() == 16909611) {
            return "bigPicture".equals(view.getTag()) ? new NotificationBigPictureTemplateViewWrapper(context, view, expandableNotificationRow) : "bigText".equals(view.getTag()) ? new NotificationBigTextTemplateViewWrapper(context, view, expandableNotificationRow) : ("media".equals(view.getTag()) || "bigMediaNarrow".equals(view.getTag())) ? new NotificationMediaTemplateViewWrapper(context, view, expandableNotificationRow) : "messaging".equals(view.getTag()) ? new NotificationMessagingTemplateViewWrapper(context, view, expandableNotificationRow) : "conversation".equals(view.getTag()) ? new NotificationConversationTemplateViewWrapper(context, view, expandableNotificationRow) : "call".equals(view.getTag()) ? new NotificationCallTemplateViewWrapper(context, view, expandableNotificationRow) : "compactHUN".equals(view.getTag()) ? new NotificationCompactHeadsUpTemplateViewWrapper(context, view, expandableNotificationRow) : "compactMessagingHUN".equals(view.getTag()) ? new NotificationCompactMessagingTemplateViewWrapper(context, view, expandableNotificationRow) : expandableNotificationRow.mEntry.mSbn.getNotification().isStyle(Notification.DecoratedCustomViewStyle.class) ? new NotificationDecoratedCustomViewWrapper(context, view, expandableNotificationRow) : NotificationDecoratedCustomViewWrapper.getWrappedCustomView(view) != null ? new NotificationDecoratedCustomViewWrapper(context, view, expandableNotificationRow) : new NotificationTemplateViewWrapper(context, view, expandableNotificationRow);
        }
        if (view instanceof NotificationHeaderView) {
            return new NotificationHeaderViewWrapper(context, view, expandableNotificationRow);
        }
        NotificationCustomViewWrapper notificationCustomViewWrapper = new NotificationCustomViewWrapper(view, expandableNotificationRow);
        notificationCustomViewWrapper.mLegacyColor = expandableNotificationRow.getContext().getColor(R.color.notification_legacy_background_color);
        return notificationCustomViewWrapper;
    }

    public boolean childrenNeedInversion(int i, ViewGroup viewGroup) {
        if (viewGroup == null) {
            return false;
        }
        int backgroundColor = getBackgroundColor(viewGroup);
        if (Color.alpha(backgroundColor) != 255) {
            backgroundColor = ColorUtils.setAlphaComponent(ContrastColorUtil.compositeColors(backgroundColor, i), 255);
        }
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof TextView) {
                if (ColorUtils.calculateContrast(((TextView) childAt).getCurrentTextColor(), backgroundColor) < 3.0d) {
                    return true;
                }
            } else if ((childAt instanceof ViewGroup) && childrenNeedInversion(backgroundColor, (ViewGroup) childAt)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.TransformableView
    public TransformState getCurrentState(int i) {
        return null;
    }

    public int getCustomBackgroundColor() {
        if (this.mRow.mIsSummaryWithChildren) {
            return 0;
        }
        return this.mBackgroundColor;
    }

    public View getExpandButton() {
        return null;
    }

    public int getExtraMeasureHeight() {
        return 0;
    }

    public int getHeaderTranslation(boolean z) {
        return 0;
    }

    public CachingIconView getIcon() {
        return null;
    }

    public int getMinLayoutHeight() {
        return 0;
    }

    public NotificationHeaderView getNotificationHeader() {
        return null;
    }

    public int getOriginalIconColor() {
        return 1;
    }

    public View getShelfTransformationTarget() {
        return null;
    }

    public final boolean needsInversion(View view, int i) {
        if (view == null || (this.mView.getResources().getConfiguration().uiMode & 48) != 32 || this.mRow.mEntry.targetSdk >= 29) {
            return false;
        }
        int backgroundColor = getBackgroundColor(view);
        if (backgroundColor != 0) {
            i = backgroundColor;
        }
        if (i == 0) {
            i = resolveBackgroundColor();
        }
        float[] fArr = {0.0f, 0.0f, 0.0f};
        ColorUtils.colorToHSL(i, fArr);
        float f = fArr[1];
        if (f != 0.0f) {
            return false;
        }
        if (f == 0.0f && fArr[2] > 0.5d) {
            return true;
        }
        if (view instanceof ViewGroup) {
            return childrenNeedInversion(i, (ViewGroup) view);
        }
        return false;
    }

    public abstract void onContentUpdated(ExpandableNotificationRow expandableNotificationRow);

    public final void onReinflated() {
        if (!(this instanceof NotificationCustomViewWrapper)) {
            this.mBackgroundColor = 0;
        }
        int backgroundColor = getBackgroundColor(this.mView);
        if (backgroundColor != 0) {
            this.mBackgroundColor = backgroundColor;
            this.mView.setBackground(new ColorDrawable(0));
        }
    }

    public final int resolveBackgroundColor() {
        int customBackgroundColor = getCustomBackgroundColor();
        return customBackgroundColor != 0 ? customBackgroundColor : Utils.getColorAttr(android.R.^attr-private.materialColorSurfaceContainerHigh, this.mView.getContext()).getDefaultColor();
    }

    public void setNotificationFaded(boolean z) {
        NotificationFadeAware.setLayerTypeForFaded(getIcon(), z);
        NotificationFadeAware.setLayerTypeForFaded(getExpandButton(), z);
    }

    public boolean shouldClipToRounding(boolean z) {
        return this instanceof NotificationCustomViewWrapper;
    }

    @Override // com.android.systemui.statusbar.TransformableView
    public void transformFrom(TransformableView transformableView) {
        CrossFadeHelper.fadeIn(this.mView, 210L, 0);
    }

    @Override // com.android.systemui.statusbar.TransformableView
    public void transformTo(TransformableView transformableView, Runnable runnable) {
        CrossFadeHelper.fadeOut(this.mView, 210L, runnable);
    }

    @Override // com.android.systemui.statusbar.TransformableView
    public void transformFrom(float f, TransformableView transformableView) {
        CrossFadeHelper.fadeIn(this.mView, f, true);
    }

    @Override // com.android.systemui.statusbar.TransformableView
    public void transformTo(float f, TransformableView transformableView) {
        CrossFadeHelper.fadeOut(this.mView, f, true);
    }

    public void onContentShown(boolean z) {
    }

    public void setAnimationsRunning(boolean z) {
    }

    public void setFeedbackIcon(FeedbackIcon feedbackIcon) {
    }

    public void setHeaderVisibleAmount(float f) {
    }

    public void setIsChildInGroup(boolean z) {
    }

    public void setLegacy(boolean z) {
    }

    public void setRecentlyAudiblyAlerted(boolean z) {
    }

    public void setRemoteInputVisible(boolean z) {
    }

    public void setContentHeight(int i, int i2) {
    }

    public void updateExpandability(boolean z, ExpandableNotificationRow.AnonymousClass1 anonymousClass1, boolean z2) {
    }
}
