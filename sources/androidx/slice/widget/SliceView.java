package androidx.slice.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.SliceMetadata;
import androidx.slice.core.SliceAction;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.core.SliceQuery;
import com.android.systemui.volume.VolumePanelDialog$$ExternalSyntheticLambda1;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliceView extends ViewGroup implements Observer, View.OnClickListener {
    public static final AnonymousClass3 SLICE_ACTION_PRIORITY_COMPARATOR = new AnonymousClass3();
    public ActionRow mActionRow;
    public int mActionRowHeight;
    public List mActions;
    public int[] mClickInfo;
    public Slice mCurrentSlice;
    public boolean mCurrentSliceLoggedVisible;
    public SliceMetricsWrapper mCurrentSliceMetrics;
    public SliceChildView mCurrentView;
    public int mDownX;
    public int mDownY;
    public Handler mHandler;
    public boolean mInLongpress;
    public int mLargeHeight;
    public ListContent mListContent;
    public View.OnLongClickListener mLongClickListener;
    public final AnonymousClass1 mLongpressCheck;
    public int mMinTemplateHeight;
    public View.OnClickListener mOnClickListener;
    public boolean mPressing;
    public final AnonymousClass1 mRefreshLastUpdated;
    public int mShortcutSize;
    public boolean mShowActionDividers;
    public boolean mShowHeaderDivider;
    public final boolean mShowLastUpdated;
    public boolean mShowTitleItems;
    public SliceMetadata mSliceMetadata;
    public final VolumePanelDialog$$ExternalSyntheticLambda1 mSliceObserver;
    public SliceStyle mSliceStyle;
    public int mThemeTintColor;
    public int mTouchSlopSquared;
    public SliceViewPolicy mViewPolicy;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.slice.widget.SliceView$3, reason: invalid class name */
    public final class AnonymousClass3 implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int priority = ((SliceAction) obj).getPriority();
            int priority2 = ((SliceAction) obj2).getPriority();
            if (priority < 0 && priority2 < 0) {
                return 0;
            }
            if (priority >= 0) {
                if (priority2 >= 0) {
                    if (priority2 >= priority) {
                        if (priority2 <= priority) {
                            return 0;
                        }
                    }
                }
                return -1;
            }
            return 1;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.slice.widget.SliceView$1] */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.slice.widget.SliceView$1] */
    public SliceView(Context context) {
        super(context, null, R.attr.sliceViewStyle);
        final int i = 0;
        final int i2 = 1;
        this.mShowLastUpdated = true;
        this.mCurrentSliceLoggedVisible = false;
        this.mShowTitleItems = false;
        this.mShowHeaderDivider = false;
        this.mShowActionDividers = false;
        this.mThemeTintColor = -1;
        this.mLongpressCheck = new Runnable(this) { // from class: androidx.slice.widget.SliceView.1
            public final /* synthetic */ SliceView this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                View.OnLongClickListener onLongClickListener;
                switch (i) {
                    case 0:
                        SliceView sliceView = this.this$0;
                        if (sliceView.mPressing && (onLongClickListener = sliceView.mLongClickListener) != null) {
                            sliceView.mInLongpress = true;
                            onLongClickListener.onLongClick(sliceView);
                            this.this$0.performHapticFeedback(0);
                            break;
                        }
                        break;
                    default:
                        SliceMetadata sliceMetadata = this.this$0.mSliceMetadata;
                        if (sliceMetadata != null && sliceMetadata.isExpired()) {
                            this.this$0.mCurrentView.setShowLastUpdated(true);
                            SliceView sliceView2 = this.this$0;
                            sliceView2.mCurrentView.setSliceContent(sliceView2.mListContent);
                        }
                        this.this$0.mHandler.postDelayed(this, 60000L);
                        break;
                }
            }
        };
        this.mRefreshLastUpdated = new Runnable(this) { // from class: androidx.slice.widget.SliceView.1
            public final /* synthetic */ SliceView this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                View.OnLongClickListener onLongClickListener;
                switch (i2) {
                    case 0:
                        SliceView sliceView = this.this$0;
                        if (sliceView.mPressing && (onLongClickListener = sliceView.mLongClickListener) != null) {
                            sliceView.mInLongpress = true;
                            onLongClickListener.onLongClick(sliceView);
                            this.this$0.performHapticFeedback(0);
                            break;
                        }
                        break;
                    default:
                        SliceMetadata sliceMetadata = this.this$0.mSliceMetadata;
                        if (sliceMetadata != null && sliceMetadata.isExpired()) {
                            this.this$0.mCurrentView.setShowLastUpdated(true);
                            SliceView sliceView2 = this.this$0;
                            sliceView2.mCurrentView.setSliceContent(sliceView2.mListContent);
                        }
                        this.this$0.mHandler.postDelayed(this, 60000L);
                        break;
                }
            }
        };
        SliceStyle sliceStyle = new SliceStyle(context, null, R.attr.sliceViewStyle, R.style.Widget_SliceView);
        this.mSliceStyle = sliceStyle;
        this.mThemeTintColor = sliceStyle.mTintColor;
        this.mShortcutSize = getContext().getResources().getDimensionPixelSize(R.dimen.abc_slice_shortcut_size);
        this.mMinTemplateHeight = getContext().getResources().getDimensionPixelSize(R.dimen.abc_slice_row_min_height);
        this.mLargeHeight = getResources().getDimensionPixelSize(R.dimen.abc_slice_large_height);
        this.mActionRowHeight = getResources().getDimensionPixelSize(R.dimen.abc_slice_action_row_height);
        SliceViewPolicy sliceViewPolicy = new SliceViewPolicy();
        sliceViewPolicy.mMaxHeight = 0;
        sliceViewPolicy.mMaxSmallHeight = 0;
        sliceViewPolicy.mScrollable = true;
        this.mViewPolicy = sliceViewPolicy;
        TemplateView templateView = new TemplateView(getContext());
        this.mCurrentView = templateView;
        templateView.setPolicy(this.mViewPolicy);
        SliceChildView sliceChildView = this.mCurrentView;
        addView(sliceChildView, getChildLp(sliceChildView));
        this.mCurrentView.setSliceActionListener(null);
        SliceChildView sliceChildView2 = this.mCurrentView;
        SliceStyle sliceStyle2 = this.mSliceStyle;
        sliceChildView2.setStyle(sliceStyle2, sliceStyle2.getRowStyle());
        this.mCurrentView.setTint(getTintColor());
        ListContent listContent = this.mListContent;
        if (listContent == null || listContent.getLayoutDir() == -1) {
            this.mCurrentView.setLayoutDirection(2);
        } else {
            this.mCurrentView.setLayoutDirection(this.mListContent.getLayoutDir());
        }
        ActionRow actionRow = new ActionRow(getContext());
        this.mActionRow = actionRow;
        actionRow.setBackground(new ColorDrawable(-1118482));
        ActionRow actionRow2 = this.mActionRow;
        addView(actionRow2, getChildLp(actionRow2));
        updateActions();
        int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mTouchSlopSquared = scaledTouchSlop * scaledTouchSlop;
        this.mHandler = new Handler();
        setClipToPadding(false);
        super.setOnClickListener(this);
    }

    public final ViewGroup.LayoutParams getChildLp(View view) {
        if (!(view instanceof ShortcutView)) {
            return new ViewGroup.LayoutParams(-1, -1);
        }
        int i = this.mShortcutSize;
        return new ViewGroup.LayoutParams(i, i);
    }

    public final int getTintColor() {
        int i = this.mThemeTintColor;
        if (i != -1) {
            return i;
        }
        SliceItem findSubtype = SliceQuery.findSubtype(this.mCurrentSlice, "int", "color");
        return findSubtype != null ? findSubtype.getInt() : SliceViewUtil.getColorAttr(android.R.attr.colorAccent, getContext());
    }

    public final boolean handleTouchForLongpress(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mHandler.removeCallbacks(this.mLongpressCheck);
            this.mDownX = (int) motionEvent.getRawX();
            this.mDownY = (int) motionEvent.getRawY();
            this.mPressing = true;
            this.mInLongpress = false;
            this.mHandler.postDelayed(this.mLongpressCheck, ViewConfiguration.getLongPressTimeout());
            return false;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                int rawX = ((int) motionEvent.getRawX()) - this.mDownX;
                int rawY = ((int) motionEvent.getRawY()) - this.mDownY;
                if ((rawY * rawY) + (rawX * rawX) > this.mTouchSlopSquared) {
                    this.mPressing = false;
                    this.mHandler.removeCallbacks(this.mLongpressCheck);
                }
                return this.mInLongpress;
            }
            if (actionMasked != 3) {
                return false;
            }
        }
        boolean z = this.mInLongpress;
        this.mPressing = false;
        this.mInLongpress = false;
        this.mHandler.removeCallbacks(this.mLongpressCheck);
        return z;
    }

    public final void logSliceMetricsVisibilityChange(boolean z) {
        SliceMetricsWrapper sliceMetricsWrapper = this.mCurrentSliceMetrics;
        if (sliceMetricsWrapper != null) {
            if (z && !this.mCurrentSliceLoggedVisible) {
                sliceMetricsWrapper.mSliceMetrics.logVisible();
                this.mCurrentSliceLoggedVisible = true;
            }
            if (z || !this.mCurrentSliceLoggedVisible) {
                return;
            }
            this.mCurrentSliceMetrics.mSliceMetrics.logHidden();
            this.mCurrentSliceLoggedVisible = false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isShown()) {
            logSliceMetricsVisibilityChange(true);
            refreshLastUpdatedLabel(true);
        }
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        setSlice((Slice) obj);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ListContent listContent = this.mListContent;
        if (listContent == null || listContent.getShortcut(getContext()) == null) {
            View.OnClickListener onClickListener = this.mOnClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(this);
                return;
            }
            return;
        }
        try {
            SliceActionImpl shortcut = this.mListContent.getShortcut(getContext());
            SliceItem sliceItem = shortcut.mActionItem;
            SliceItem sliceItem2 = shortcut.mSliceItem;
            if (sliceItem != null) {
                sliceItem.fireActionInternal(getContext(), null);
            }
        } catch (PendingIntent.CanceledException e) {
            Log.e("SliceView", "PendingIntent for slice cannot be sent", e);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        logSliceMetricsVisibilityChange(false);
        refreshLastUpdatedLabel(false);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (this.mLongClickListener != null && handleTouchForLongpress(motionEvent)) || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        SliceChildView sliceChildView = this.mCurrentView;
        sliceChildView.layout(0, 0, sliceChildView.getMeasuredWidth(), sliceChildView.getMeasuredHeight());
        if (this.mActionRow.getVisibility() != 8) {
            int measuredHeight = sliceChildView.getMeasuredHeight();
            ActionRow actionRow = this.mActionRow;
            actionRow.layout(0, measuredHeight, actionRow.getMeasuredWidth(), this.mActionRow.getMeasuredHeight() + measuredHeight);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        SliceAdapter sliceAdapter;
        ListContent listContent;
        SliceAdapter sliceAdapter2;
        int size = View.MeasureSpec.getSize(i);
        this.mViewPolicy.getClass();
        int i3 = this.mActionRow.getVisibility() != 8 ? this.mActionRowHeight : 0;
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int i4 = ((layoutParams == null || layoutParams.height != -2) && mode != 0) ? size2 : -1;
        ListContent listContent2 = this.mListContent;
        if (listContent2 != null && listContent2.isValid()) {
            SliceViewPolicy sliceViewPolicy = this.mViewPolicy;
            sliceViewPolicy.getClass();
            if (i4 > 0 && i4 < this.mSliceStyle.mRowMaxHeight) {
                int i5 = this.mMinTemplateHeight;
                if (i4 <= i5) {
                    i4 = i5;
                }
                if (sliceViewPolicy.mMaxSmallHeight != i4) {
                    sliceViewPolicy.mMaxSmallHeight = i4;
                    TemplateView templateView = sliceViewPolicy.mListener;
                    if (templateView != null && (sliceAdapter2 = templateView.mAdapter) != null) {
                        sliceAdapter2.notifyHeaderChanged();
                    }
                }
            } else if (sliceViewPolicy.mMaxSmallHeight != 0) {
                sliceViewPolicy.mMaxSmallHeight = 0;
                TemplateView templateView2 = sliceViewPolicy.mListener;
                if (templateView2 != null && (sliceAdapter = templateView2.mAdapter) != null) {
                    sliceAdapter.notifyHeaderChanged();
                }
            }
            SliceViewPolicy sliceViewPolicy2 = this.mViewPolicy;
            if (i4 != sliceViewPolicy2.mMaxHeight) {
                sliceViewPolicy2.mMaxHeight = i4;
                TemplateView templateView3 = sliceViewPolicy2.mListener;
                if (templateView3 != null && (listContent = templateView3.mListContent) != null) {
                    templateView3.updateDisplayedItems(listContent.getHeight(templateView3.mSliceStyle, templateView3.mViewPolicy));
                }
            }
        }
        int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
        if (mode != 1073741824) {
            ListContent listContent3 = this.mListContent;
            if (listContent3 == null || !listContent3.isValid()) {
                paddingTop = i3;
            } else {
                SliceViewPolicy sliceViewPolicy3 = this.mViewPolicy;
                sliceViewPolicy3.getClass();
                int height = this.mListContent.getHeight(this.mSliceStyle, sliceViewPolicy3) + i3;
                if (paddingTop > height || mode == 0) {
                    paddingTop = height;
                } else if (!this.mSliceStyle.mExpandToAvailableHeight) {
                    this.mViewPolicy.getClass();
                    int i6 = this.mLargeHeight + i3;
                    if (paddingTop >= i6 || paddingTop <= (i6 = this.mMinTemplateHeight)) {
                        paddingTop = i6;
                    }
                }
            }
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
        this.mActionRow.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(i3 > 0 ? getPaddingBottom() + i3 : 0, 1073741824));
        this.mCurrentView.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(getPaddingTop() + paddingTop + (i3 <= 0 ? getPaddingBottom() : 0), 1073741824));
        setMeasuredDimension(size, this.mActionRow.getMeasuredHeight() + this.mCurrentView.getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return (this.mLongClickListener != null && handleTouchForLongpress(motionEvent)) || super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (isAttachedToWindow()) {
            logSliceMetricsVisibilityChange(i == 0);
            refreshLastUpdatedLabel(i == 0);
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        logSliceMetricsVisibilityChange(i == 0);
        refreshLastUpdatedLabel(i == 0);
    }

    public final void refreshLastUpdatedLabel(boolean z) {
        SliceMetadata sliceMetadata;
        if (!this.mShowLastUpdated || (sliceMetadata = this.mSliceMetadata) == null || sliceMetadata.mExpiry == -1) {
            return;
        }
        if (!z) {
            this.mHandler.removeCallbacks(this.mRefreshLastUpdated);
            return;
        }
        Handler handler = this.mHandler;
        AnonymousClass1 anonymousClass1 = this.mRefreshLastUpdated;
        long j = 60000;
        if (!sliceMetadata.isExpired()) {
            SliceMetadata sliceMetadata2 = this.mSliceMetadata;
            sliceMetadata2.getClass();
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = sliceMetadata2.mExpiry;
            long j3 = 0;
            if (j2 != 0 && j2 != -1 && currentTimeMillis <= j2) {
                j3 = j2 - currentTimeMillis;
            }
            j = 60000 + j3;
        }
        handler.postDelayed(anonymousClass1, j);
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @Override // android.view.View
    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
        this.mLongClickListener = onLongClickListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setSlice(androidx.slice.Slice r13) {
        /*
            Method dump skipped, instructions count: 565
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.SliceView.setSlice(androidx.slice.Slice):void");
    }

    public void setSliceViewPolicy(SliceViewPolicy sliceViewPolicy) {
        this.mViewPolicy = sliceViewPolicy;
    }

    public final void updateActions() {
        if (this.mActions == null) {
            this.mActionRow.setVisibility(8);
            this.mCurrentView.setSliceActions(null);
            this.mCurrentView.setInsets(getPaddingStart(), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
        } else {
            ArrayList arrayList = new ArrayList(this.mActions);
            Collections.sort(arrayList, SLICE_ACTION_PRIORITY_COMPARATOR);
            this.mCurrentView.setSliceActions(arrayList);
            this.mCurrentView.setInsets(getPaddingStart(), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
            this.mActionRow.setVisibility(8);
        }
    }
}
