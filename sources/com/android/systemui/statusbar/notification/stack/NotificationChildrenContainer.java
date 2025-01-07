package com.android.systemui.statusbar.notification.stack;

import android.app.Notification;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.internal.widget.NotificationExpandButton;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.NotificationGroupingUtil;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.notification.NotificationFadeAware;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.Roundable;
import com.android.systemui.statusbar.notification.RoundableState;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridGroupManager;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.util.PluralMessageFormaterKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationChildrenContainer extends ViewGroup implements NotificationFadeAware, Roundable {
    public static final AnonymousClass1 ALPHA_FADE_IN;
    public static final SourceType$Companion$from$1 FROM_PARENT;
    static final int NUMBER_OF_CHILDREN_WHEN_COLLAPSED = 2;
    static final int NUMBER_OF_CHILDREN_WHEN_SYSTEM_EXPANDED = 5;
    public int mActualHeight;
    public final List mAttachedChildren;
    public Path mChildClipPath;
    public int mChildPadding;
    public boolean mChildrenExpanded;
    public int mClipBottomAmount;
    public float mCollapsedBottomPadding;
    public ExpandableNotificationRow mContainingNotification;
    public boolean mContainingNotificationIsFaded;
    public ViewGroup mCurrentHeader;
    public int mCurrentHeaderTranslation;
    public float mDividerAlpha;
    public int mDividerHeight;
    public final List mDividers;
    public boolean mEnableShadowOnChildNotifications;
    public NotificationHeaderView mGroupHeader;
    public NotificationHeaderViewWrapper mGroupHeaderWrapper;
    public ViewState mGroupOverFlowState;
    public NotificationGroupingUtil mGroupingUtil;
    public ExpandableNotificationRow.AnonymousClass1 mHeaderClickListener;
    public int mHeaderHeight;
    public final Path mHeaderPath;
    public ViewState mHeaderViewState;
    public float mHeaderVisibleAmount;
    public boolean mHideDividersDuringExpand;
    public final HybridGroupManager mHybridGroupManager;
    public boolean mIsMinimized;
    public NotificationChildrenContainerLogger mLogger;
    public int mMinSingleLineHeight;
    public NotificationHeaderView mMinimizedGroupHeader;
    public NotificationHeaderViewWrapper mMinimizedGroupHeaderWrapper;
    public boolean mNeverAppliedGroupState;
    public int mNotificationHeaderMargin;
    public int mNotificationTopPadding;
    public TextView mOverflowNumber;
    public int mRealHeight;
    public final RoundableState mRoundableState;
    public boolean mShowDividersWhenExpanded;
    public boolean mShowGroupCountInExpander;
    public int mTranslationForHeader;
    public int mUntruncatedChildCount;
    public boolean mUserLocked;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimationProperties {
        public AnimationFilter mAnimationFilter;

        @Override // com.android.systemui.statusbar.notification.stack.AnimationProperties
        public final AnimationFilter getAnimationFilter() {
            return this.mAnimationFilter;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer$2, reason: invalid class name */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ NotificationChildrenContainer this$0;
        public final /* synthetic */ View val$divider;

        public /* synthetic */ AnonymousClass2(NotificationChildrenContainer notificationChildrenContainer, View view, int i) {
            this.$r8$classId = i;
            this.this$0 = notificationChildrenContainer;
            this.val$divider = view;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.getOverlay().remove(this.val$divider);
                    break;
                default:
                    this.this$0.removeTransientView(this.val$divider);
                    break;
            }
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        AnimationFilter animationFilter = new AnimationFilter();
        animationFilter.animateAlpha = true;
        anonymousClass1.mAnimationFilter = animationFilter;
        anonymousClass1.duration = 200L;
        ALPHA_FADE_IN = anonymousClass1;
        FROM_PARENT = new SourceType$Companion$from$1("FromParent(NCC)");
    }

    public NotificationChildrenContainer(Context context) {
        this(context, null);
    }

    public final void addTransientView(View view, int i) {
        NotificationChildrenContainerLogger notificationChildrenContainerLogger = this.mLogger;
        if (notificationChildrenContainerLogger != null && (view instanceof ExpandableNotificationRow)) {
            NotificationEntry notificationEntry = ((ExpandableNotificationRow) view).mEntry;
            NotificationEntry notificationEntry2 = this.mContainingNotification.mEntry;
            notificationChildrenContainerLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            NotificationChildrenContainerLogger$addTransientRow$2 notificationChildrenContainerLogger$addTransientRow$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerLogger$addTransientRow$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    String str2 = logMessage.getStr2();
                    int int1 = logMessage.getInt1();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("addTransientRow: childKey: ", str1, " -- containerKey: ", str2, " -- index: ");
                    m.append(int1);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = notificationChildrenContainerLogger.notificationRenderBuffer;
            LogMessage obtain = logBuffer.obtain("NotifChildrenContainer", logLevel, notificationChildrenContainerLogger$addTransientRow$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
            logMessageImpl.int1 = i;
            logBuffer.commit(obtain);
        }
        super.addTransientView(view, i);
    }

    @Override // com.android.systemui.statusbar.notification.Roundable
    public final void applyRoundnessAndInvalidate() {
        NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.mGroupHeaderWrapper;
        if (notificationHeaderViewWrapper != null) {
            notificationHeaderViewWrapper.requestTopRoundness(this.mRoundableState.topRoundness, FROM_PARENT, false);
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = this.mMinimizedGroupHeaderWrapper;
        if (notificationHeaderViewWrapper2 != null) {
            notificationHeaderViewWrapper2.requestTopRoundness(this.mRoundableState.topRoundness, FROM_PARENT, false);
        }
        boolean z = true;
        for (int size = ((ArrayList) this.mAttachedChildren).size() - 1; size >= 0; size--) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(size);
            if (expandableNotificationRow.getVisibility() != 8) {
                expandableNotificationRow.requestRoundness(0.0f, z ? this.mRoundableState.bottomRoundness : 0.0f, FROM_PARENT, false);
                z = false;
            }
        }
        super.applyRoundnessAndInvalidate();
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        Path path = this.mChildClipPath;
        boolean z2 = true;
        if (path != null) {
            float translation = view instanceof ExpandableNotificationRow ? ((ExpandableNotificationRow) view).getTranslation() : view.getTranslationX();
            canvas.save();
            if (translation != 0.0f) {
                path.offset(translation, 0.0f);
                canvas.clipPath(path);
                path.offset(-translation, 0.0f);
            } else {
                canvas.clipPath(path);
            }
            z = true;
        } else {
            z = false;
        }
        if ((view instanceof NotificationHeaderView) && this.mGroupHeaderWrapper.hasRoundedCorner()) {
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.mGroupHeaderWrapper;
            float[] fArr = notificationHeaderViewWrapper.mRoundableState.radiiBuffer;
            float topCornerRadius = notificationHeaderViewWrapper.getTopCornerRadius();
            float bottomCornerRadius = notificationHeaderViewWrapper.getBottomCornerRadius();
            if (fArr.length != 8) {
                throw new IllegalStateException(("Unexpected radiiBuffer size " + fArr.length).toString());
            }
            if (fArr[0] != topCornerRadius || fArr[4] != bottomCornerRadius) {
                IntProgressionIterator it = new IntRange(0, 3, 1).iterator();
                while (it.hasNext) {
                    fArr[it.nextInt()] = topCornerRadius;
                }
                IntProgressionIterator it2 = new IntRange(4, 7, 1).iterator();
                while (it2.hasNext) {
                    fArr[it2.nextInt()] = bottomCornerRadius;
                }
            }
            this.mHeaderPath.reset();
            this.mHeaderPath.addRoundRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), fArr, Path.Direction.CW);
            if (z) {
                z2 = z;
            } else {
                canvas.save();
            }
            canvas.clipPath(this.mHeaderPath);
            z = z2;
        }
        if (!z) {
            return super.drawChild(canvas, view, j);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return drawChild;
    }

    @Override // com.android.systemui.statusbar.notification.Roundable
    public final int getClipHeight() {
        return Math.max(this.mActualHeight - this.mClipBottomAmount, 0);
    }

    public ViewGroup getCurrentHeaderView() {
        return this.mCurrentHeader;
    }

    public final float getGroupExpandFraction() {
        int i;
        if (showingAsLowPriority()) {
            i = getMaxContentHeight();
        } else {
            i = this.mNotificationHeaderMargin + this.mCurrentHeaderTranslation + this.mNotificationTopPadding + this.mDividerHeight;
            int size = ((ArrayList) this.mAttachedChildren).size();
            int maxAllowedVisibleChildren = getMaxAllowedVisibleChildren(true);
            int i2 = 0;
            for (int i3 = 0; i3 < size && i2 < maxAllowedVisibleChildren; i3++) {
                i = (int) (i + (((ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i3)).isExpanded(true) ? r7.getMaxExpandHeight() : r7.getShowingLayout().getMinHeight(true)));
                i2++;
            }
        }
        int minHeight = getMinHeight(getMaxAllowedVisibleChildren(true), this.mCurrentHeaderTranslation, false);
        return Math.max(0.0f, Math.min(1.0f, (this.mActualHeight - minHeight) / (i - minHeight)));
    }

    public final int getIntrinsicHeight() {
        int interpolate;
        float maxAllowedVisibleChildren = getMaxAllowedVisibleChildren();
        if (showingAsLowPriority()) {
            return this.mHeaderHeight;
        }
        int i = this.mNotificationHeaderMargin + this.mCurrentHeaderTranslation;
        int size = ((ArrayList) this.mAttachedChildren).size();
        float groupExpandFraction = this.mUserLocked ? getGroupExpandFraction() : 0.0f;
        boolean z = this.mChildrenExpanded;
        boolean z2 = true;
        int i2 = 0;
        for (int i3 = 0; i3 < size && i2 < maxAllowedVisibleChildren; i3++) {
            if (z2) {
                interpolate = this.mUserLocked ? (int) (NotificationUtils.interpolate(0.0f, this.mNotificationTopPadding + this.mDividerHeight, groupExpandFraction) + i) : i + (z ? this.mNotificationTopPadding + this.mDividerHeight : 0);
                z2 = false;
            } else if (this.mUserLocked) {
                interpolate = (int) (NotificationUtils.interpolate(this.mChildPadding, this.mDividerHeight, groupExpandFraction) + i);
            } else {
                interpolate = i + (z ? this.mDividerHeight : this.mChildPadding);
            }
            i = interpolate + ((ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i3)).getIntrinsicHeight();
            i2++;
        }
        if (this.mUserLocked) {
            return (int) (NotificationUtils.interpolate(this.mCollapsedBottomPadding, 0.0f, groupExpandFraction) + i);
        }
        return !z ? (int) (i + this.mCollapsedBottomPadding) : i;
    }

    public int getMaxAllowedVisibleChildren() {
        return getMaxAllowedVisibleChildren(false);
    }

    public final int getMaxContentHeight() {
        if (showingAsLowPriority()) {
            return getMinHeight(5, this.mCurrentHeaderTranslation, true);
        }
        int i = this.mNotificationHeaderMargin + this.mCurrentHeaderTranslation + this.mNotificationTopPadding;
        int size = ((ArrayList) this.mAttachedChildren).size();
        int i2 = 0;
        for (int i3 = 0; i3 < size && i2 < 8; i3++) {
            i = (int) (i + (((ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i3)).isExpanded(true) ? r5.getMaxExpandHeight() : r5.getShowingLayout().getMinHeight(true)));
            i2++;
        }
        return i2 > 0 ? i + (i2 * this.mDividerHeight) : i;
    }

    public final int getMinHeight(int i, int i2, boolean z) {
        if (!z && showingAsLowPriority()) {
            return this.mHeaderHeight;
        }
        int i3 = this.mNotificationHeaderMargin + i2;
        int size = ((ArrayList) this.mAttachedChildren).size();
        boolean z2 = true;
        int i4 = 0;
        for (int i5 = 0; i5 < size && i4 < i; i5++) {
            if (z2) {
                z2 = false;
            } else {
                i3 += this.mChildPadding;
            }
            HybridNotificationView singleLineView = ((ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i5)).getSingleLineView();
            i3 = singleLineView != null ? singleLineView.getHeight() + i3 : i3 + this.mMinSingleLineHeight;
            i4++;
        }
        return (int) (i3 + this.mCollapsedBottomPadding);
    }

    @Override // com.android.systemui.statusbar.notification.Roundable
    public final RoundableState getRoundableState() {
        return this.mRoundableState;
    }

    public final NotificationHeaderViewWrapper getWrapperForView$1(View view) {
        return view == this.mGroupHeader ? this.mGroupHeaderWrapper : this.mMinimizedGroupHeaderWrapper;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final View inflateDivider() {
        View inflate = LayoutInflater.from(((ViewGroup) this).mContext).inflate(R.layout.notification_children_divider, (ViewGroup) this, false);
        inflate.setAlpha(0.0f);
        return inflate;
    }

    public final void initDimens$2() {
        Resources resources = getResources();
        this.mChildPadding = resources.getDimensionPixelOffset(R.dimen.notification_children_padding);
        this.mDividerHeight = resources.getDimensionPixelOffset(R.dimen.notification_children_container_divider_height);
        this.mDividerAlpha = resources.getFloat(R.dimen.notification_divider_alpha);
        this.mNotificationHeaderMargin = resources.getDimensionPixelOffset(R.dimen.notification_children_container_margin_top);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.notification_children_container_top_padding);
        this.mNotificationTopPadding = dimensionPixelOffset;
        this.mHeaderHeight = this.mNotificationHeaderMargin + dimensionPixelOffset;
        this.mCollapsedBottomPadding = resources.getDimensionPixelOffset(R.dimen.notification_children_collapsed_bottom_padding);
        this.mEnableShadowOnChildNotifications = resources.getBoolean(R.bool.config_enableShadowOnChildNotifications);
        this.mShowGroupCountInExpander = resources.getBoolean(R.bool.config_showNotificationGroupCountInExpander);
        this.mShowDividersWhenExpanded = resources.getBoolean(R.bool.config_showDividersWhenGroupNotificationExpanded);
        this.mHideDividersDuringExpand = resources.getBoolean(R.bool.config_hideDividersDuringExpand);
        this.mTranslationForHeader = resources.getDimensionPixelOffset(android.R.dimen.notification_expand_button_pill_height) - this.mNotificationHeaderMargin;
        HybridGroupManager hybridGroupManager = this.mHybridGroupManager;
        Resources resources2 = hybridGroupManager.mContext.getResources();
        hybridGroupManager.mOverflowNumberSize = resources2.getDimensionPixelSize(R.dimen.group_overflow_number_size);
        hybridGroupManager.mOverflowNumberPadding = resources2.getDimensionPixelSize(R.dimen.group_overflow_number_padding);
        this.mMinSingleLineHeight = getResources().getDimensionPixelSize(R.dimen.conversation_single_line_face_pile_size);
    }

    public boolean isUserLocked() {
        return this.mUserLocked;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateGroupOverflow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int min = Math.min(((ArrayList) this.mAttachedChildren).size(), 8);
        for (int i5 = 0; i5 < min; i5++) {
            View view = (View) ((ArrayList) this.mAttachedChildren).get(i5);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            ((View) ((ArrayList) this.mDividers).get(i5)).layout(0, 0, getWidth(), this.mDividerHeight);
        }
        if (this.mOverflowNumber != null) {
            int width = getLayoutDirection() == 1 ? 0 : getWidth() - this.mOverflowNumber.getMeasuredWidth();
            int measuredWidth = this.mOverflowNumber.getMeasuredWidth() + width;
            TextView textView = this.mOverflowNumber;
            textView.layout(width, 0, measuredWidth, textView.getMeasuredHeight());
        }
        NotificationHeaderView notificationHeaderView = this.mGroupHeader;
        if (notificationHeaderView != null) {
            notificationHeaderView.layout(0, 0, notificationHeaderView.getMeasuredWidth(), this.mGroupHeader.getMeasuredHeight());
        }
        NotificationHeaderView notificationHeaderView2 = this.mMinimizedGroupHeader;
        if (notificationHeaderView2 != null) {
            notificationHeaderView2.layout(0, 0, notificationHeaderView2.getMeasuredWidth(), this.mMinimizedGroupHeader.getMeasuredHeight());
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        TextView textView;
        Trace.beginSection("NotificationChildrenContainer#onMeasure");
        int mode = View.MeasureSpec.getMode(i2);
        boolean z = mode == 1073741824;
        boolean z2 = mode == Integer.MIN_VALUE;
        int size = View.MeasureSpec.getSize(i2);
        int makeMeasureSpec = (z || z2) ? View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE) : i2;
        int size2 = View.MeasureSpec.getSize(i);
        TextView textView2 = this.mOverflowNumber;
        if (textView2 != null) {
            textView2.measure(View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE), makeMeasureSpec);
        }
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.mDividerHeight, 1073741824);
        int i3 = this.mNotificationHeaderMargin + this.mNotificationTopPadding;
        int min = Math.min(((ArrayList) this.mAttachedChildren).size(), 8);
        int maxAllowedVisibleChildren = getMaxAllowedVisibleChildren(true);
        int i4 = min > maxAllowedVisibleChildren ? maxAllowedVisibleChildren - 1 : -1;
        int i5 = 0;
        while (i5 < min) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i5);
            int measuredWidth = (i5 != i4 || (textView = this.mOverflowNumber) == null) ? 0 : textView.getMeasuredWidth();
            NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
            if (measuredWidth != notificationContentView.mSingleLineWidthIndention) {
                notificationContentView.mSingleLineWidthIndention = measuredWidth;
                notificationContentView.mContainingNotification.forceLayout();
                notificationContentView.forceLayout();
            }
            expandableNotificationRow.measure(i, makeMeasureSpec);
            ((View) ((ArrayList) this.mDividers).get(i5)).measure(i, makeMeasureSpec2);
            if (expandableNotificationRow.getVisibility() != 8) {
                i3 = expandableNotificationRow.getMeasuredHeight() + this.mDividerHeight + i3;
            }
            i5++;
        }
        this.mRealHeight = i3;
        if (mode != 0) {
            i3 = Math.min(i3, size);
        }
        int makeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(this.mHeaderHeight, 1073741824);
        NotificationHeaderView notificationHeaderView = this.mGroupHeader;
        if (notificationHeaderView != null) {
            notificationHeaderView.measure(i, makeMeasureSpec3);
        }
        NotificationHeaderView notificationHeaderView2 = this.mMinimizedGroupHeader;
        if (notificationHeaderView2 != null) {
            notificationHeaderView2.measure(i, makeMeasureSpec3);
        }
        setMeasuredDimension(size2, i3);
        Trace.endSection();
    }

    public final void onNotificationUpdated() {
        if (this.mShowGroupCountInExpander) {
            return;
        }
        int i = this.mContainingNotification.mNotificationColor;
        TypedArray obtainStyledAttributes = new ContextThemeWrapper(((ViewGroup) this).mContext, android.R.style.Theme.DeviceDefault.DayNight).getTheme().obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorPrimary});
        try {
            int color = obtainStyledAttributes.getColor(0, i);
            obtainStyledAttributes.close();
            HybridGroupManager hybridGroupManager = this.mHybridGroupManager;
            TextView textView = this.mOverflowNumber;
            hybridGroupManager.mOverflowNumberColor = color;
            if (textView != null) {
                textView.setTextColor(color);
            }
        } catch (Throwable th) {
            if (obtainStyledAttributes != null) {
                try {
                    obtainStyledAttributes.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public final boolean pointInView(float f, float f2, float f3) {
        float f4 = -f3;
        return f >= f4 && f2 >= f4 && f < ((float) (((ViewGroup) this).mRight - ((ViewGroup) this).mLeft)) + f3 && f2 < ((float) this.mRealHeight) + f3;
    }

    public void recreateLowPriorityHeader(Notification.Builder builder, boolean z) {
        throw new IllegalStateException("Legacy code path not supported when com.android.systemui.notification_async_group_header_inflation is enabled.");
    }

    public final void removeNotification(ExpandableNotificationRow expandableNotificationRow) {
        int indexOf = this.mAttachedChildren.indexOf(expandableNotificationRow);
        this.mAttachedChildren.remove(expandableNotificationRow);
        removeView(expandableNotificationRow);
        View view = (View) this.mDividers.remove(indexOf);
        removeView(view);
        getOverlay().add(view);
        CrossFadeHelper.fadeOut(view, 210L, new AnonymousClass2(this, view, 0));
        expandableNotificationRow.mIsSystemChildExpanded = false;
        expandableNotificationRow.setNotificationFaded(false);
        expandableNotificationRow.setUserLocked(false);
        NotificationGroupingUtil notificationGroupingUtil = this.mGroupingUtil;
        for (int i = 0; i < notificationGroupingUtil.mProcessors.size(); i++) {
            ((NotificationGroupingUtil.Processor) notificationGroupingUtil.mProcessors.get(i)).apply(expandableNotificationRow, true);
        }
        notificationGroupingUtil.sanitizeTopLineViews(expandableNotificationRow);
        expandableNotificationRow.requestRoundness(0.0f, 0.0f, FROM_PARENT, false);
        applyRoundnessAndInvalidate();
    }

    public final void removeTransientView(View view) {
        NotificationChildrenContainerLogger notificationChildrenContainerLogger = this.mLogger;
        if (notificationChildrenContainerLogger != null && (view instanceof ExpandableNotificationRow)) {
            NotificationEntry notificationEntry = ((ExpandableNotificationRow) view).mEntry;
            NotificationEntry notificationEntry2 = this.mContainingNotification.mEntry;
            notificationChildrenContainerLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            NotificationChildrenContainerLogger$removeTransientRow$2 notificationChildrenContainerLogger$removeTransientRow$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerLogger$removeTransientRow$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("removeTransientRow: childKey: ", logMessage.getStr1(), " -- containerKey: ", logMessage.getStr2());
                }
            };
            LogBuffer logBuffer = notificationChildrenContainerLogger.notificationRenderBuffer;
            LogMessage obtain = logBuffer.obtain("NotifChildrenContainer", logLevel, notificationChildrenContainerLogger$removeTransientRow$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.str2 = NotificationUtilsKt.getLogKey(notificationEntry2);
            logBuffer.commit(obtain);
        }
        super.removeTransientView(view);
    }

    public final void resetHeaderVisibilityIfNeeded(View view, View view2) {
        if (view == null) {
            return;
        }
        if (view != this.mCurrentHeader && view != view2) {
            getWrapperForView$1(view).setVisible(false);
            view.setVisibility(4);
        }
        if (view != view2 || view.getVisibility() == 0) {
            return;
        }
        getWrapperForView$1(view).setVisible(true);
        view.setVisibility(0);
    }

    public final void setNotificationFaded(boolean z) {
        this.mContainingNotificationIsFaded = z;
        NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.mGroupHeaderWrapper;
        if (notificationHeaderViewWrapper != null) {
            notificationHeaderViewWrapper.setNotificationFaded(z);
        }
        NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = this.mMinimizedGroupHeaderWrapper;
        if (notificationHeaderViewWrapper2 != null) {
            notificationHeaderViewWrapper2.setNotificationFaded(z);
        }
        Iterator it = this.mAttachedChildren.iterator();
        while (it.hasNext()) {
            ((ExpandableNotificationRow) it.next()).setNotificationFaded(z);
        }
    }

    public final void setUserLocked(boolean z) {
        this.mUserLocked = z;
        if (!z) {
            updateHeaderVisibility(false);
        }
        int size = ((ArrayList) this.mAttachedChildren).size();
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= size) {
                break;
            }
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i);
            if (!z || showingAsLowPriority()) {
                z2 = false;
            }
            expandableNotificationRow.setUserLocked(z2);
            i++;
        }
        NotificationHeaderView notificationHeaderView = this.mGroupHeader;
        if (notificationHeaderView != null) {
            notificationHeaderView.setAcceptAllTouches(this.mChildrenExpanded || this.mUserLocked);
        }
    }

    public final boolean showingAsLowPriority() {
        return this.mIsMinimized && !this.mContainingNotification.isExpanded(false);
    }

    public final void updateChildrenAppearance() {
        View view;
        View findViewById;
        NotificationViewWrapper notificationViewWrapper;
        NotificationViewWrapper notificationViewWrapper2;
        NotificationGroupingUtil notificationGroupingUtil = this.mGroupingUtil;
        ExpandableNotificationRow expandableNotificationRow = notificationGroupingUtil.mRow;
        List attachedChildren = expandableNotificationRow.getAttachedChildren();
        if (attachedChildren == null || !expandableNotificationRow.mIsSummaryWithChildren) {
            return;
        }
        int i = 0;
        while (true) {
            Notification notification = null;
            if (i >= notificationGroupingUtil.mProcessors.size()) {
                break;
            }
            NotificationGroupingUtil.Processor processor = (NotificationGroupingUtil.Processor) notificationGroupingUtil.mProcessors.get(i);
            ExpandableNotificationRow expandableNotificationRow2 = processor.mParentRow;
            if (expandableNotificationRow2.mIsSummaryWithChildren) {
                notificationViewWrapper = expandableNotificationRow2.mChildrenContainer.mGroupHeaderWrapper;
            } else {
                NotificationContentView notificationContentView = expandableNotificationRow2.mPrivateLayout;
                if ((notificationContentView.mContractedChild != null && (notificationViewWrapper2 = notificationContentView.mContractedWrapper) != null) || (notificationContentView.mExpandedChild != null && (notificationViewWrapper2 = notificationContentView.mExpandedWrapper) != null)) {
                    notificationViewWrapper = notificationViewWrapper2;
                } else if (notificationContentView.mHeadsUpChild == null || (notificationViewWrapper = notificationContentView.mHeadsUpWrapper) == null) {
                    notificationViewWrapper = null;
                }
            }
            View notificationHeader = notificationViewWrapper == null ? null : notificationViewWrapper.getNotificationHeader();
            processor.mParentView = notificationHeader == null ? null : notificationHeader.findViewById(processor.mId);
            if (processor.mExtractor != null) {
                notification = expandableNotificationRow2.mEntry.mSbn.getNotification();
            }
            processor.mParentData = notification;
            processor.mApply = !processor.mComparator.isEmpty(processor.mParentView);
            i++;
        }
        for (int i2 = 0; i2 < attachedChildren.size(); i2++) {
            ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) attachedChildren.get(i2);
            for (int i3 = 0; i3 < notificationGroupingUtil.mProcessors.size(); i3++) {
                NotificationGroupingUtil.Processor processor2 = (NotificationGroupingUtil.Processor) notificationGroupingUtil.mProcessors.get(i3);
                if (processor2.mApply && (view = expandableNotificationRow3.mPrivateLayout.mContractedChild) != null && (findViewById = view.findViewById(processor2.mId)) != null) {
                    processor2.mApply = processor2.mComparator.compare(processor2.mParentView, findViewById, processor2.mParentData, processor2.mExtractor == null ? null : expandableNotificationRow3.mEntry.mSbn.getNotification());
                }
            }
        }
        for (int i4 = 0; i4 < attachedChildren.size(); i4++) {
            ExpandableNotificationRow expandableNotificationRow4 = (ExpandableNotificationRow) attachedChildren.get(i4);
            for (int i5 = 0; i5 < notificationGroupingUtil.mProcessors.size(); i5++) {
                ((NotificationGroupingUtil.Processor) notificationGroupingUtil.mProcessors.get(i5)).apply(expandableNotificationRow4, false);
            }
            notificationGroupingUtil.sanitizeTopLineViews(expandableNotificationRow4);
        }
    }

    public final void updateChildrenClipping() {
        int i;
        boolean z;
        if (this.mContainingNotification.mChildIsExpanding) {
            return;
        }
        int size = ((ArrayList) this.mAttachedChildren).size();
        int i2 = this.mContainingNotification.mActualHeight - this.mClipBottomAmount;
        for (int i3 = 0; i3 < size; i3++) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i3);
            if (expandableNotificationRow.getVisibility() != 8) {
                float translationY = expandableNotificationRow.getTranslationY();
                float f = expandableNotificationRow.mActualHeight + translationY;
                float f2 = i2;
                if (translationY > f2) {
                    i = 0;
                    z = false;
                } else {
                    i = f > f2 ? (int) (f - f2) : 0;
                    z = true;
                }
                if (z != (expandableNotificationRow.getVisibility() == 0)) {
                    expandableNotificationRow.setVisibility(z ? 0 : 4);
                }
                expandableNotificationRow.setClipBottomAmount(i);
            }
        }
    }

    public final void updateExpansionStates() {
        boolean z;
        if (this.mChildrenExpanded || this.mUserLocked) {
            return;
        }
        int size = ((ArrayList) this.mAttachedChildren).size();
        for (int i = 0; i < size; i++) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i);
            if (i == 0) {
                z = true;
                if (size == 1) {
                    expandableNotificationRow.mIsSystemChildExpanded = z;
                }
            }
            z = false;
            expandableNotificationRow.mIsSystemChildExpanded = z;
        }
    }

    public final void updateGroupOverflow() {
        int i = 1;
        if (this.mShowGroupCountInExpander) {
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.mGroupHeaderWrapper;
            View expandButton = notificationHeaderViewWrapper == null ? null : notificationHeaderViewWrapper.getExpandButton();
            if (expandButton instanceof NotificationExpandButton) {
                ((NotificationExpandButton) expandButton).setNumber(this.mUntruncatedChildCount);
            }
            NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = this.mMinimizedGroupHeaderWrapper;
            View expandButton2 = notificationHeaderViewWrapper2 != null ? notificationHeaderViewWrapper2.getExpandButton() : null;
            if (expandButton2 instanceof NotificationExpandButton) {
                ((NotificationExpandButton) expandButton2).setNumber(this.mUntruncatedChildCount);
                return;
            }
            return;
        }
        int maxAllowedVisibleChildren = getMaxAllowedVisibleChildren(true);
        int i2 = this.mUntruncatedChildCount;
        if (i2 <= maxAllowedVisibleChildren) {
            View view = this.mOverflowNumber;
            if (view != null) {
                removeView(view);
                if (isShown() && isAttachedToWindow()) {
                    View view2 = this.mOverflowNumber;
                    addTransientView(view2, getTransientViewCount());
                    CrossFadeHelper.fadeOut(view2, 210L, new AnonymousClass2(this, view2, i));
                }
                this.mOverflowNumber = null;
                this.mGroupOverFlowState = null;
                return;
            }
            return;
        }
        int i3 = i2 - maxAllowedVisibleChildren;
        HybridGroupManager hybridGroupManager = this.mHybridGroupManager;
        TextView textView = this.mOverflowNumber;
        if (textView == null) {
            textView = (TextView) ((LayoutInflater) hybridGroupManager.mContext.getSystemService(LayoutInflater.class)).inflate(R.layout.hybrid_overflow_number, (ViewGroup) this, false);
            addView(textView);
            textView.setTextColor(hybridGroupManager.mOverflowNumberColor);
        }
        String string = hybridGroupManager.mContext.getResources().getString(R.string.notification_group_overflow_indicator, Integer.valueOf(i3));
        if (!string.equals(textView.getText())) {
            textView.setText(string);
        }
        textView.setContentDescription(PluralMessageFormaterKt.icuMessageFormat(hybridGroupManager.mContext.getResources(), R.string.notification_group_overflow_description, i3));
        textView.setTextSize(0, hybridGroupManager.mOverflowNumberSize);
        textView.setPaddingRelative(textView.getPaddingStart(), textView.getPaddingTop(), hybridGroupManager.mOverflowNumberPadding, textView.getPaddingBottom());
        textView.setTextColor(hybridGroupManager.mOverflowNumberColor);
        this.mOverflowNumber = textView;
        if (this.mGroupOverFlowState == null) {
            this.mGroupOverFlowState = new ViewState();
            this.mNeverAppliedGroupState = true;
        }
    }

    public final void updateHeaderVisibility(boolean z) {
        NotificationHeaderView notificationHeaderView = this.mCurrentHeader;
        NotificationHeaderView notificationHeaderView2 = showingAsLowPriority() ? this.mMinimizedGroupHeader : this.mGroupHeader;
        if (notificationHeaderView == notificationHeaderView2 || notificationHeaderView2 == null) {
            return;
        }
        if (z) {
            if (notificationHeaderView != null) {
                notificationHeaderView.setVisibility(0);
                notificationHeaderView2.setVisibility(0);
                TransformableView wrapperForView$1 = getWrapperForView$1(notificationHeaderView2);
                TransformableView wrapperForView$12 = getWrapperForView$1(notificationHeaderView);
                wrapperForView$1.transformFrom(wrapperForView$12);
                wrapperForView$12.transformTo(wrapperForView$1, new Runnable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        NotificationChildrenContainer notificationChildrenContainer = NotificationChildrenContainer.this;
                        NotificationChildrenContainer.AnonymousClass1 anonymousClass1 = NotificationChildrenContainer.ALPHA_FADE_IN;
                        notificationChildrenContainer.updateHeaderVisibility(false);
                    }
                });
                float f = notificationHeaderView2 == this.mGroupHeader ? 1.0f : 0.0f;
                float f2 = 1.0f - f;
                int size = ((ArrayList) this.mAttachedChildren).size();
                for (int i = 0; i < size && i < 5; i++) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) ((ArrayList) this.mAttachedChildren).get(i);
                    expandableNotificationRow.setAlpha(f2);
                    ViewState viewState = new ViewState();
                    viewState.initFrom(expandableNotificationRow);
                    viewState.setAlpha(f);
                    AnonymousClass1 anonymousClass1 = ALPHA_FADE_IN;
                    anonymousClass1.delay = i * 50;
                    viewState.animateTo(expandableNotificationRow, anonymousClass1);
                }
            } else {
                z = false;
            }
        }
        if (!z) {
            getWrapperForView$1(notificationHeaderView2).setVisible(true);
            notificationHeaderView2.setVisibility(0);
            if (notificationHeaderView != null) {
                NotificationHeaderViewWrapper wrapperForView$13 = getWrapperForView$1(notificationHeaderView);
                if (wrapperForView$13 != null) {
                    wrapperForView$13.setVisible(false);
                }
                notificationHeaderView.setVisibility(4);
            }
        }
        resetHeaderVisibilityIfNeeded(this.mGroupHeader, notificationHeaderView2);
        resetHeaderVisibilityIfNeeded(this.mMinimizedGroupHeader, notificationHeaderView2);
        this.mCurrentHeader = notificationHeaderView2;
    }

    public NotificationChildrenContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public int getMaxAllowedVisibleChildren(boolean z) {
        if (!z && ((this.mChildrenExpanded || this.mContainingNotification.mUserLocked) && !showingAsLowPriority())) {
            return 8;
        }
        if (this.mIsMinimized) {
            return 5;
        }
        ExpandableNotificationRow expandableNotificationRow = this.mContainingNotification;
        if (expandableNotificationRow.mOnKeyguard || !expandableNotificationRow.isExpanded(false)) {
            return (this.mContainingNotification.isHeadsUpState() && this.mContainingNotification.canShowHeadsUp()) ? 5 : 2;
        }
        return 5;
    }

    public NotificationChildrenContainer(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public NotificationChildrenContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDividers = new ArrayList();
        this.mAttachedChildren = new ArrayList();
        this.mChildClipPath = null;
        this.mHeaderPath = new Path();
        this.mCurrentHeaderTranslation = 0;
        this.mHeaderVisibleAmount = 1.0f;
        this.mContainingNotificationIsFaded = false;
        this.mHybridGroupManager = new HybridGroupManager(getContext());
        this.mRoundableState = new RoundableState(this, this, 0.0f);
        initDimens$2();
        setClipChildren(false);
    }
}
