package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.R;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StackEducationView extends LinearLayout {
    public final Lazy descTextView$delegate;
    public boolean isHiding;
    public final BubbleStackView$$ExternalSyntheticLambda36 manager;
    public final BubblePositioner positioner;
    public final Lazy titleTextView$delegate;
    public final Lazy view$delegate;

    public StackEducationView(Context context, BubblePositioner bubblePositioner, BubbleStackView$$ExternalSyntheticLambda36 bubbleStackView$$ExternalSyntheticLambda36) {
        super(context);
        this.positioner = bubblePositioner;
        this.manager = bubbleStackView$$ExternalSyntheticLambda36;
        this.view$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.StackEducationView$view$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return StackEducationView.this.requireViewById(R.id.stack_education_layout);
            }
        });
        this.titleTextView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.StackEducationView$titleTextView$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return (TextView) StackEducationView.this.requireViewById(R.id.stack_education_title);
            }
        });
        this.descTextView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.bubbles.StackEducationView$descTextView$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return (TextView) StackEducationView.this.requireViewById(R.id.stack_education_description);
            }
        });
        LayoutInflater.from(context).inflate(R.layout.bubble_stack_user_education, this);
        setVisibility(8);
        setElevation(getResources().getDimensionPixelSize(R.dimen.bubble_elevation));
        setLayoutDirection(3);
    }

    public final void hide(boolean z) {
        if (getVisibility() != 0 || this.isHiding) {
            return;
        }
        this.isHiding = true;
        this.manager.updateWindowFlagsForBackpress(false);
        animate().alpha(0.0f).setDuration(z ? 40L : 200L).withEndAction(new Runnable() { // from class: com.android.wm.shell.bubbles.StackEducationView$hide$1
            @Override // java.lang.Runnable
            public final void run() {
                StackEducationView.this.setVisibility(8);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setFocusableInTouchMode(true);
        setOnKeyListener(new View.OnKeyListener() { // from class: com.android.wm.shell.bubbles.StackEducationView$onAttachedToWindow$1
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1 && i == 4) {
                    StackEducationView stackEducationView = StackEducationView.this;
                    if (!stackEducationView.isHiding) {
                        stackEducationView.hide(false);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setOnKeyListener(null);
        this.manager.updateWindowFlagsForBackpress(false);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setLayoutDirection(getResources().getConfiguration().getLayoutDirection());
        TypedArray obtainStyledAttributes = ((LinearLayout) this).mContext.obtainStyledAttributes(new int[]{android.R.attr.colorAccent, android.R.attr.textColorPrimaryInverse});
        int color = obtainStyledAttributes.getColor(0, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT);
        int color2 = obtainStyledAttributes.getColor(1, -1);
        obtainStyledAttributes.recycle();
        int ensureTextContrast = ContrastColorUtil.ensureTextContrast(color2, color, true);
        ((TextView) this.titleTextView$delegate.getValue()).setTextColor(ensureTextContrast);
        ((TextView) this.descTextView$delegate.getValue()).setTextColor(ensureTextContrast);
    }

    @Override // android.view.View
    public final void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        ((View) this.view$delegate.getValue()).setBackgroundResource(i == 0 ? R.drawable.bubble_stack_user_education_bg : R.drawable.bubble_stack_user_education_bg_rtl);
    }
}
