package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardDismissTransitionInteractor {
    public static final String TAG = Reflection.getOrCreateKotlinClass(KeyguardDismissTransitionInteractor.class).getSimpleName();
    public final FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor;
    public final FromAodTransitionInteractor fromAodTransitionInteractor;
    public final FromDozingTransitionInteractor fromDozingTransitionInteractor;
    public final FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor;
    public final FromOccludedTransitionInteractor fromOccludedTransitionInteractor;
    public final FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor;
    public final KeyguardTransitionRepositoryImpl repository;

    public KeyguardDismissTransitionInteractor(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor, FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor, FromAodTransitionInteractor fromAodTransitionInteractor, FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor, FromDozingTransitionInteractor fromDozingTransitionInteractor, FromOccludedTransitionInteractor fromOccludedTransitionInteractor) {
        this.repository = keyguardTransitionRepositoryImpl;
        this.fromLockscreenTransitionInteractor = fromLockscreenTransitionInteractor;
        this.fromPrimaryBouncerTransitionInteractor = fromPrimaryBouncerTransitionInteractor;
        this.fromAodTransitionInteractor = fromAodTransitionInteractor;
        this.fromAlternateBouncerTransitionInteractor = fromAlternateBouncerTransitionInteractor;
        this.fromDozingTransitionInteractor = fromDozingTransitionInteractor;
        this.fromOccludedTransitionInteractor = fromOccludedTransitionInteractor;
    }
}
