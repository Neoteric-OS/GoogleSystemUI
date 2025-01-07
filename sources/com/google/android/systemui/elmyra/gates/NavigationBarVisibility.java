package com.google.android.systemui.elmyra.gates;

import android.content.Context;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NavigationBarVisibility extends Gate {
    public final AssistManagerGoogle mAssistManager;
    public final AnonymousClass1 mCommandQueueCallbacks;
    public final int mDisplayId;
    public final List mExceptions;
    public final AnonymousClass2 mGateListener;
    public boolean mIsKeyguardShowing;
    public boolean mIsNavigationGestural;
    public boolean mIsNavigationHidden;
    public final KeyguardVisibility mKeyguardGate;
    public final NonGesturalNavigation mNavigationModeGate;

    public NavigationBarVisibility(Context context, Executor executor, CommandQueue commandQueue, AssistManagerGoogle assistManagerGoogle, KeyguardVisibility keyguardVisibility, NonGesturalNavigation nonGesturalNavigation, Set set) {
        super(executor);
        CommandQueue.Callbacks callbacks = new CommandQueue.Callbacks() { // from class: com.google.android.systemui.elmyra.gates.NavigationBarVisibility.1
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void setWindowState(int i, int i2, int i3) {
                NavigationBarVisibility navigationBarVisibility = NavigationBarVisibility.this;
                if (navigationBarVisibility.mDisplayId == i && i2 == 2) {
                    boolean z = i3 != 0;
                    if (z != navigationBarVisibility.mIsNavigationHidden) {
                        navigationBarVisibility.mIsNavigationHidden = z;
                        navigationBarVisibility.notifyListener();
                    }
                }
            }
        };
        Gate.Listener listener = new Gate.Listener() { // from class: com.google.android.systemui.elmyra.gates.NavigationBarVisibility.2
            @Override // com.google.android.systemui.elmyra.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                NavigationBarVisibility navigationBarVisibility = NavigationBarVisibility.this;
                if (gate.equals(navigationBarVisibility.mKeyguardGate)) {
                    navigationBarVisibility.mIsKeyguardShowing = ((KeyguardStateControllerImpl) navigationBarVisibility.mKeyguardGate.mKeyguardStateController).mShowing;
                    return;
                }
                NonGesturalNavigation nonGesturalNavigation2 = navigationBarVisibility.mNavigationModeGate;
                if (gate.equals(nonGesturalNavigation2)) {
                    navigationBarVisibility.mIsNavigationGestural = nonGesturalNavigation2.mCurrentModeIsGestural;
                }
            }
        };
        this.mExceptions = new ArrayList(set);
        this.mIsNavigationHidden = false;
        commandQueue.addCallback(callbacks);
        this.mDisplayId = context.getDisplayId();
        this.mAssistManager = assistManagerGoogle;
        this.mKeyguardGate = keyguardVisibility;
        keyguardVisibility.mListener = listener;
        this.mNavigationModeGate = nonGesturalNavigation;
        nonGesturalNavigation.mListener = listener;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        if (this.mIsKeyguardShowing) {
            return false;
        }
        if (this.mIsNavigationGestural && this.mAssistManager.mNgaIsAssistant) {
            return false;
        }
        for (int i = 0; i < ((ArrayList) this.mExceptions).size(); i++) {
            if (((Action) ((ArrayList) this.mExceptions).get(i)).isAvailable()) {
                return false;
            }
        }
        return this.mIsNavigationHidden;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        KeyguardVisibility keyguardVisibility = this.mKeyguardGate;
        keyguardVisibility.activate();
        this.mIsKeyguardShowing = ((KeyguardStateControllerImpl) keyguardVisibility.mKeyguardStateController).mShowing;
        NonGesturalNavigation nonGesturalNavigation = this.mNavigationModeGate;
        nonGesturalNavigation.activate();
        this.mIsNavigationGestural = nonGesturalNavigation.mCurrentModeIsGestural;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        NonGesturalNavigation nonGesturalNavigation = this.mNavigationModeGate;
        nonGesturalNavigation.deactivate();
        this.mIsNavigationGestural = nonGesturalNavigation.mCurrentModeIsGestural;
        KeyguardVisibility keyguardVisibility = this.mKeyguardGate;
        keyguardVisibility.deactivate();
        this.mIsKeyguardShowing = ((KeyguardStateControllerImpl) keyguardVisibility.mKeyguardStateController).mShowing;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mIsNavigationHidden -> ");
        sb.append(this.mIsNavigationHidden);
        sb.append("; mExceptions -> ");
        sb.append(this.mExceptions);
        sb.append("; mIsNavigationGestural -> ");
        sb.append(this.mIsNavigationGestural);
        sb.append("; isActiveAssistantNga() -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mAssistManager.mNgaIsAssistant, "]");
    }
}
