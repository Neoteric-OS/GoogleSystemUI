package com.android.systemui.keyguard.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TransitionState {
    public static final /* synthetic */ TransitionState[] $VALUES;
    public static final TransitionState CANCELED;
    public static final TransitionState FINISHED;
    public static final TransitionState RUNNING;
    public static final TransitionState STARTED;

    static {
        TransitionState transitionState = new TransitionState() { // from class: com.android.systemui.keyguard.shared.model.TransitionState.STARTED
            @Override // com.android.systemui.keyguard.shared.model.TransitionState
            public final boolean isTransitioning() {
                return true;
            }
        };
        STARTED = transitionState;
        TransitionState transitionState2 = new TransitionState() { // from class: com.android.systemui.keyguard.shared.model.TransitionState.RUNNING
            @Override // com.android.systemui.keyguard.shared.model.TransitionState
            public final boolean isTransitioning() {
                return true;
            }
        };
        RUNNING = transitionState2;
        TransitionState transitionState3 = new TransitionState() { // from class: com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
            @Override // com.android.systemui.keyguard.shared.model.TransitionState
            public final boolean isTransitioning() {
                return false;
            }
        };
        FINISHED = transitionState3;
        TransitionState transitionState4 = new TransitionState() { // from class: com.android.systemui.keyguard.shared.model.TransitionState.CANCELED
            @Override // com.android.systemui.keyguard.shared.model.TransitionState
            public final boolean isTransitioning() {
                return false;
            }
        };
        CANCELED = transitionState4;
        TransitionState[] transitionStateArr = {transitionState, transitionState2, transitionState3, transitionState4};
        $VALUES = transitionStateArr;
        EnumEntriesKt.enumEntries(transitionStateArr);
    }

    public static TransitionState valueOf(String str) {
        return (TransitionState) Enum.valueOf(TransitionState.class, str);
    }

    public static TransitionState[] values() {
        return (TransitionState[]) $VALUES.clone();
    }

    public abstract boolean isTransitioning();
}
