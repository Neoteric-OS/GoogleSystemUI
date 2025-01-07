package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.keyguard.AlphaOptimizedLinearLayout;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class StatusIconContainer extends AlphaOptimizedLinearLayout {
    public static final AnonymousClass1 ADD_ICON_PROPERTIES;
    public static final AnonymousClass1 ANIMATE_ALL_PROPERTIES;
    public static final AnonymousClass1 X_ANIMATION_PROPERTIES;
    public final Configuration mConfiguration;
    public int mDotPadding;
    public int mIconDotFrameWidth;
    public int mIconSpacing;
    public final ArrayList mIgnoredSlots;
    public final ArrayList mLayoutStates;
    public final ArrayList mMeasureViews;
    public boolean mNeedsUnderflow;
    public boolean mQsExpansionTransitioning;
    public boolean mShouldRestrictIcons;
    public int mStaticDotDiameter;
    public int mUnderflowStart;
    public int mUnderflowWidth;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusIconContainer$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimationProperties {
        public final /* synthetic */ int $r8$classId;
        public AnimationFilter mAnimationFilter;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // com.android.systemui.statusbar.notification.stack.AnimationProperties
        public final AnimationFilter getAnimationFilter() {
            switch (this.$r8$classId) {
            }
            return this.mAnimationFilter;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StatusIconState extends ViewState {
        public float distanceToViewEnd;
        public boolean justAdded;
        public boolean qsExpansionTransitioning;
        public int visibleState;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.android.systemui.statusbar.notification.stack.ViewState
        public final void applyToView(View view) {
            AnonymousClass1 anonymousClass1;
            float width = (view.getParent() instanceof View ? ((View) view.getParent()).getWidth() : 0.0f) - this.mXTranslation;
            if (view instanceof StatusIconDisplayable) {
                StatusIconDisplayable statusIconDisplayable = (StatusIconDisplayable) view;
                boolean z = true;
                if (this.justAdded || (statusIconDisplayable.getVisibleState() == 2 && this.visibleState == 0)) {
                    super.applyToView(view);
                    view.setAlpha(0.0f);
                    statusIconDisplayable.setVisibleState(2);
                    anonymousClass1 = StatusIconContainer.ADD_ICON_PROPERTIES;
                } else {
                    int visibleState = statusIconDisplayable.getVisibleState();
                    int i = this.visibleState;
                    anonymousClass1 = null;
                    if (visibleState != i) {
                        if (statusIconDisplayable.getVisibleState() == 0 && this.visibleState == 2) {
                            z = false;
                        } else {
                            anonymousClass1 = StatusIconContainer.ANIMATE_ALL_PROPERTIES;
                        }
                    } else if (i != 2 && this.distanceToViewEnd != width) {
                        anonymousClass1 = StatusIconContainer.X_ANIMATION_PROPERTIES;
                    }
                }
                statusIconDisplayable.setVisibleState(this.visibleState, z);
                if (anonymousClass1 == null || this.qsExpansionTransitioning) {
                    super.applyToView(view);
                } else {
                    animateTo(view, anonymousClass1);
                }
                this.qsExpansionTransitioning = false;
                this.justAdded = false;
                this.distanceToViewEnd = width;
            }
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        AnimationFilter animationFilter = new AnimationFilter();
        animationFilter.animateAlpha = true;
        anonymousClass1.mAnimationFilter = animationFilter;
        anonymousClass1.duration = 200L;
        anonymousClass1.delay = 50L;
        ADD_ICON_PROPERTIES = anonymousClass1;
        AnonymousClass1 anonymousClass12 = new AnonymousClass1(1);
        AnimationFilter animationFilter2 = new AnimationFilter();
        animationFilter2.animateX = true;
        anonymousClass12.mAnimationFilter = animationFilter2;
        anonymousClass12.duration = 200L;
        X_ANIMATION_PROPERTIES = anonymousClass12;
        AnonymousClass1 anonymousClass13 = new AnonymousClass1(2);
        AnimationFilter animationFilter3 = new AnimationFilter();
        animationFilter3.animateX = true;
        animationFilter3.animateY = true;
        animationFilter3.animateAlpha = true;
        animationFilter3.mAnimatedProperties.add(View.SCALE_X);
        animationFilter3.mAnimatedProperties.add(View.SCALE_Y);
        anonymousClass13.mAnimationFilter = animationFilter3;
        anonymousClass13.duration = 200L;
        ANIMATE_ALL_PROPERTIES = anonymousClass13;
    }

    public StatusIconContainer(Context context) {
        this(context, null);
    }

    public final void addIgnoredSlot(String str) {
        if (this.mIgnoredSlots.contains(str)) {
            return;
        }
        this.mIgnoredSlots.add(str);
        requestLayout();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int diff = configuration.diff(this.mConfiguration);
        this.mConfiguration.setTo(configuration);
        if ((1073745920 & diff) != 0) {
            reloadDimens$2();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float height = getHeight() / 2.0f;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i6 = (int) (height - (measuredHeight / 2.0f));
            childAt.layout(0, i6, measuredWidth, measuredHeight + i6);
        }
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt2 = getChildAt(i7);
            StatusIconState statusIconState = (StatusIconState) childAt2.getTag(R.id.status_bar_view_state_tag);
            if (statusIconState != null) {
                statusIconState.initFrom(childAt2);
                statusIconState.setAlpha(1.0f);
                statusIconState.hidden = false;
            }
        }
        this.mLayoutStates.clear();
        float width = getWidth();
        float paddingEnd = width - getPaddingEnd();
        float paddingStart = getPaddingStart();
        int childCount = getChildCount();
        for (int i8 = childCount - 1; i8 >= 0; i8--) {
            View childAt3 = getChildAt(i8);
            StatusIconDisplayable statusIconDisplayable = (StatusIconDisplayable) childAt3;
            StatusIconState statusIconState2 = (StatusIconState) childAt3.getTag(R.id.status_bar_view_state_tag);
            if (!statusIconDisplayable.isIconVisible() || statusIconDisplayable.isIconBlocked() || this.mIgnoredSlots.contains(statusIconDisplayable.getSlot())) {
                statusIconState2.visibleState = 2;
            } else {
                float paddingEnd2 = paddingEnd - (childAt3.getPaddingEnd() + (childAt3.getPaddingStart() + childAt3.getWidth()));
                statusIconState2.visibleState = 0;
                statusIconState2.setXTranslation(paddingEnd2);
                this.mLayoutStates.add(0, statusIconState2);
                paddingEnd = paddingEnd2 - this.mIconSpacing;
            }
        }
        int size = this.mLayoutStates.size();
        int i9 = size > 7 ? 6 : 7;
        this.mUnderflowStart = (int) Math.max(paddingStart, (width - getPaddingEnd()) - this.mUnderflowWidth);
        int i10 = size - 1;
        int i11 = 0;
        while (true) {
            if (i10 < 0) {
                i10 = -1;
                break;
            }
            StatusIconState statusIconState3 = (StatusIconState) this.mLayoutStates.get(i10);
            if ((this.mNeedsUnderflow && statusIconState3.mXTranslation < this.mUnderflowWidth + paddingStart) || (this.mShouldRestrictIcons && i11 >= i9)) {
                break;
            }
            this.mUnderflowStart = (int) Math.max(paddingStart, (statusIconState3.mXTranslation - this.mUnderflowWidth) - this.mIconSpacing);
            i11++;
            i10--;
        }
        if (i10 != -1) {
            int i12 = this.mStaticDotDiameter + this.mDotPadding;
            int i13 = (this.mUnderflowStart + this.mUnderflowWidth) - this.mIconDotFrameWidth;
            int i14 = 0;
            while (i10 >= 0) {
                StatusIconState statusIconState4 = (StatusIconState) this.mLayoutStates.get(i10);
                if (i14 < 1) {
                    statusIconState4.setXTranslation(i13);
                    statusIconState4.visibleState = 1;
                    i13 -= i12;
                    i14++;
                } else {
                    statusIconState4.visibleState = 2;
                }
                i10--;
            }
        }
        if (isLayoutRtl()) {
            for (int i15 = 0; i15 < childCount; i15++) {
                StatusIconState statusIconState5 = (StatusIconState) getChildAt(i15).getTag(R.id.status_bar_view_state_tag);
                statusIconState5.setXTranslation((width - statusIconState5.mXTranslation) - r13.getWidth());
            }
        }
        for (int i16 = 0; i16 < getChildCount(); i16++) {
            View childAt4 = getChildAt(i16);
            StatusIconState statusIconState6 = (StatusIconState) childAt4.getTag(R.id.status_bar_view_state_tag);
            if (statusIconState6 != null) {
                statusIconState6.applyToView(childAt4);
                statusIconState6.qsExpansionTransitioning = this.mQsExpansionTransitioning;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int paddingTop;
        int paddingStart;
        int paddingEnd;
        this.mMeasureViews.clear();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int childCount = getChildCount();
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            StatusIconDisplayable statusIconDisplayable = (StatusIconDisplayable) getChildAt(i4);
            if (statusIconDisplayable.isIconVisible() && !statusIconDisplayable.isIconBlocked() && !this.mIgnoredSlots.contains(statusIconDisplayable.getSlot())) {
                this.mMeasureViews.add((View) statusIconDisplayable);
            }
        }
        int size2 = this.mMeasureViews.size();
        int i5 = size2 <= 7 ? 7 : 6;
        int i6 = ((LinearLayout) this).mPaddingLeft + ((LinearLayout) this).mPaddingRight;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, 0);
        this.mNeedsUnderflow = this.mShouldRestrictIcons && size2 > 7;
        int i7 = 0;
        boolean z = true;
        while (i7 < size2) {
            View view = (View) this.mMeasureViews.get((size2 - i7) - 1);
            measureChild(view, makeMeasureSpec, i2);
            int i8 = i7 == size2 + (-1) ? 0 : this.mIconSpacing;
            if (!this.mShouldRestrictIcons) {
                paddingStart = view.getPaddingStart() + view.getMeasuredWidth();
                paddingEnd = view.getPaddingEnd();
            } else if (i7 >= i5 || !z) {
                if (z) {
                    i6 += this.mUnderflowWidth;
                    z = false;
                }
                i7++;
            } else {
                paddingStart = view.getPaddingStart() + view.getMeasuredWidth();
                paddingEnd = view.getPaddingEnd();
            }
            i6 = paddingEnd + paddingStart + i8 + i6;
            i7++;
        }
        if (mode == 1073741824) {
            if (!this.mNeedsUnderflow && i6 > size) {
                this.mNeedsUnderflow = true;
            }
        } else if (mode != Integer.MIN_VALUE || i6 <= size) {
            size = i6;
        } else {
            this.mNeedsUnderflow = true;
        }
        ArrayList arrayList = this.mMeasureViews;
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            paddingTop = View.MeasureSpec.getSize(i2);
        } else {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i3 = Math.max(((View) it.next()).getMeasuredHeight(), i3);
            }
            paddingTop = getPaddingTop() + i3 + getPaddingBottom();
        }
        setMeasuredDimension(size, paddingTop);
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        StatusIconState statusIconState = new StatusIconState();
        statusIconState.visibleState = 0;
        statusIconState.qsExpansionTransitioning = false;
        statusIconState.distanceToViewEnd = -1.0f;
        statusIconState.justAdded = true;
        view.setTag(R.id.status_bar_view_state_tag, statusIconState);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        view.setTag(R.id.status_bar_view_state_tag, null);
    }

    public final void reloadDimens$2() {
        this.mIconDotFrameWidth = getResources().getDimensionPixelSize(android.R.dimen.text_edit_floating_toolbar_elevation);
        this.mDotPadding = getResources().getDimensionPixelSize(R.dimen.overflow_icon_dot_padding);
        this.mIconSpacing = getResources().getDimensionPixelSize(R.dimen.status_bar_system_icon_spacing);
        this.mStaticDotDiameter = getResources().getDimensionPixelSize(R.dimen.overflow_dot_radius) * 2;
        this.mUnderflowWidth = this.mIconDotFrameWidth;
    }

    public final void removeIgnoredSlot(String str) {
        if (this.mIgnoredSlots.remove(str)) {
            requestLayout();
        }
    }

    public StatusIconContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUnderflowStart = 0;
        this.mShouldRestrictIcons = true;
        this.mLayoutStates = new ArrayList();
        this.mMeasureViews = new ArrayList();
        this.mIgnoredSlots = new ArrayList();
        this.mConfiguration = new Configuration(context.getResources().getConfiguration());
        reloadDimens$2();
        setWillNotDraw(true);
    }
}
