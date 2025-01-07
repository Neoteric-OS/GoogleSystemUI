package com.android.systemui.util;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ViewController {
    public boolean mInited;
    public final AnonymousClass1 mOnAttachStateListener = new AnonymousClass1();
    public final View mView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.util.ViewController$1, reason: invalid class name */
    public final class AnonymousClass1 implements View.OnAttachStateChangeListener {
        public AnonymousClass1() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            ViewController.this.onViewAttached();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            ViewController.this.onViewDetached();
        }
    }

    public ViewController(View view) {
        this.mView = view;
    }

    public final void init$9() {
        if (this.mInited) {
            return;
        }
        onInit();
        this.mInited = true;
        View view = this.mView;
        if (view != null && view.isAttachedToWindow()) {
            this.mOnAttachStateListener.onViewAttachedToWindow(this.mView);
        }
        AnonymousClass1 anonymousClass1 = this.mOnAttachStateListener;
        View view2 = this.mView;
        if (view2 != null) {
            view2.addOnAttachStateChangeListener(anonymousClass1);
        }
    }

    public abstract void onViewAttached();

    public abstract void onViewDetached();

    public void start() {
        init$9();
    }

    public void onInit() {
    }
}
