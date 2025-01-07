package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.taskview.TaskView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ManageEducationView extends LinearLayout {
    public BubbleExpandedView bubbleExpandedView;
    public final Lazy gotItButton$delegate;
    public boolean isHiding;
    public final Lazy manageButton$delegate;
    public final Lazy manageView$delegate;
    public final BubblePositioner positioner;
    public final Rect realManageButtonRect;

    public ManageEducationView(Context context, BubblePositioner bubblePositioner) {
        super(context);
        this.positioner = bubblePositioner;
        this.manageView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.ManageEducationView$manageView$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return (ViewGroup) ManageEducationView.this.requireViewById(R.id.manage_education_view);
            }
        });
        this.manageButton$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.ManageEducationView$manageButton$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return (Button) ManageEducationView.this.requireViewById(R.id.manage_button);
            }
        });
        this.gotItButton$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.ManageEducationView$gotItButton$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return (Button) ManageEducationView.this.requireViewById(R.id.got_it);
            }
        });
        this.realManageButtonRect = new Rect();
        LayoutInflater.from(context).inflate(R.layout.bubbles_manage_button_education, this);
        setVisibility(8);
        setElevation(getResources().getDimensionPixelSize(R.dimen.bubble_elevation));
        setLayoutDirection(3);
    }

    public final Button getManageButton() {
        return (Button) this.manageButton$delegate.getValue();
    }

    public final void hide() {
        TaskView taskView;
        BubbleExpandedView bubbleExpandedView = this.bubbleExpandedView;
        if (bubbleExpandedView != null && (taskView = bubbleExpandedView.mTaskView) != null) {
            taskView.mObscuredTouchRegion = null;
        }
        if (getVisibility() != 0 || this.isHiding) {
            return;
        }
        final int i = 0;
        ViewPropertyAnimator duration = animate().withStartAction(new Runnable(this) { // from class: com.android.wm.shell.bubbles.ManageEducationView$hide$1
            public final /* synthetic */ ManageEducationView this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i) {
                    case 0:
                        this.this$0.isHiding = true;
                        break;
                    default:
                        ManageEducationView manageEducationView = this.this$0;
                        manageEducationView.isHiding = false;
                        manageEducationView.setVisibility(8);
                        break;
                }
            }
        }).alpha(0.0f).setDuration(200L);
        final int i2 = 1;
        duration.withEndAction(new Runnable(this) { // from class: com.android.wm.shell.bubbles.ManageEducationView$hide$1
            public final /* synthetic */ ManageEducationView this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        this.this$0.isHiding = true;
                        break;
                    default:
                        ManageEducationView manageEducationView = this.this$0;
                        manageEducationView.isHiding = false;
                        manageEducationView.setVisibility(8);
                        break;
                }
            }
        });
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setLayoutDirection(getResources().getConfiguration().getLayoutDirection());
    }

    @Override // android.view.View
    public final void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        ((ViewGroup) this.manageView$delegate.getValue()).setBackgroundResource(i == 0 ? R.drawable.bubble_stack_user_education_bg : R.drawable.bubble_stack_user_education_bg_rtl);
    }

    public final void show(final BubbleExpandedView bubbleExpandedView, boolean z) {
        int dimensionPixelSize;
        TypedArray obtainStyledAttributes = ((LinearLayout) this).mContext.obtainStyledAttributes(new int[]{android.R.^attr-private.colorAccentPrimary});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        getManageButton().setTextColor(((LinearLayout) this).mContext.getColor(android.R.color.system_neutral1_900));
        getManageButton().setBackgroundDrawable(new ColorDrawable(color));
        ((Button) this.gotItButton$delegate.getValue()).setBackgroundDrawable(new ColorDrawable(color));
        if (getVisibility() == 0) {
            return;
        }
        this.bubbleExpandedView = bubbleExpandedView;
        TaskView taskView = bubbleExpandedView.mTaskView;
        if (taskView != null) {
            taskView.mObscuredTouchRegion = new Region(new Rect(this.positioner.mScreenRect));
        }
        setAlpha(0.0f);
        setVisibility(0);
        bubbleExpandedView.mManageButton.getBoundsOnScreen(this.realManageButtonRect);
        Rect rect = this.realManageButtonRect;
        int marginStart = ((LinearLayout.LayoutParams) bubbleExpandedView.mManageButton.getLayoutParams()).getMarginStart();
        boolean z2 = getResources().getConfiguration().getLayoutDirection() == 0;
        if (!this.positioner.mDeviceConfig.isLargeScreen) {
            z = z2;
        }
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.bubble_user_education_padding_horizontal);
        ((ViewGroup) this.manageView$delegate.getValue()).setBackgroundResource(z ? R.drawable.bubble_stack_user_education_bg : R.drawable.bubble_stack_user_education_bg_rtl);
        setGravity(z ? 3 : 5);
        ViewGroup.LayoutParams layoutParams = ((ViewGroup) this.manageView$delegate.getValue()).getLayoutParams();
        if (z2 && !z) {
            dimensionPixelSize = this.positioner.mScreenRect.right - ((rect.left - marginStart) - dimensionPixelSize2);
        } else if (z2 || !z) {
            DeviceConfig deviceConfig = this.positioner.mDeviceConfig;
            dimensionPixelSize = deviceConfig.isLargeScreen ? -2 : deviceConfig.isLandscape ? getResources().getDimensionPixelSize(R.dimen.bubbles_user_education_width) : -1;
        } else {
            dimensionPixelSize = rect.right + marginStart + dimensionPixelSize2;
        }
        layoutParams.width = dimensionPixelSize;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ((ViewGroup) this.manageView$delegate.getValue()).getLayoutParams();
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.bubble_user_education_margin_horizontal);
        marginLayoutParams.leftMargin = z ? 0 : dimensionPixelSize3;
        if (!z) {
            dimensionPixelSize3 = 0;
        }
        marginLayoutParams.rightMargin = dimensionPixelSize3;
        ViewGroup viewGroup = (ViewGroup) this.manageView$delegate.getValue();
        int i = (z2 && z) ? rect.left - marginStart : dimensionPixelSize2;
        if (!z2 && !z) {
            dimensionPixelSize2 = (this.positioner.mScreenRect.right - rect.right) - marginStart;
        }
        viewGroup.setPadding(i, viewGroup.getPaddingTop(), dimensionPixelSize2, viewGroup.getPaddingBottom());
        post(new Runnable() { // from class: com.android.wm.shell.bubbles.ManageEducationView$show$1
            @Override // java.lang.Runnable
            public final void run() {
                Button manageButton = ManageEducationView.this.getManageButton();
                final ManageEducationView manageEducationView = ManageEducationView.this;
                final BubbleExpandedView bubbleExpandedView2 = bubbleExpandedView;
                manageButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.ManageEducationView$show$1.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ManageEducationView.this.hide();
                        bubbleExpandedView2.requireViewById(R.id.manage_button).performClick();
                    }
                });
                Button button = (Button) ManageEducationView.this.gotItButton$delegate.getValue();
                final ManageEducationView manageEducationView2 = ManageEducationView.this;
                final int i2 = 0;
                button.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.ManageEducationView$show$1.2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i2) {
                            case 0:
                                manageEducationView2.hide();
                                break;
                            default:
                                manageEducationView2.hide();
                                break;
                        }
                    }
                });
                final ManageEducationView manageEducationView3 = ManageEducationView.this;
                final int i3 = 1;
                manageEducationView3.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.ManageEducationView$show$1.2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i3) {
                            case 0:
                                manageEducationView3.hide();
                                break;
                            default:
                                manageEducationView3.hide();
                                break;
                        }
                    }
                });
                Rect rect2 = new Rect();
                ManageEducationView.this.getManageButton().getDrawingRect(rect2);
                ((ViewGroup) ManageEducationView.this.manageView$delegate.getValue()).offsetDescendantRectToMyCoords(ManageEducationView.this.getManageButton(), rect2);
                ManageEducationView.this.setTranslationY(r1.realManageButtonRect.top - rect2.top);
                ManageEducationView.this.bringToFront();
                ManageEducationView.this.animate().setDuration(200L).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).alpha(1.0f);
            }
        });
        getContext().getSharedPreferences(getContext().getPackageName(), 0).edit().putBoolean("HasSeenBubblesManageOnboarding", true).apply();
    }
}
