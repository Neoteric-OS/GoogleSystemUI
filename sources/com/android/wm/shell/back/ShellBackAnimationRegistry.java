package com.android.wm.shell.back;

import android.util.SparseArray;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShellBackAnimationRegistry {
    public final SparseArray mAnimationDefinition;
    public final CrossTaskBackAnimation mCrossTaskAnimation;
    public final CustomCrossActivityBackAnimation mCustomizeActivityAnimation;
    public final DefaultCrossActivityBackAnimation mDefaultCrossActivityAnimation;
    public final ArrayList mSupportedAnimators;
    public boolean mSupportedAnimatorsChanged;

    public ShellBackAnimationRegistry(DefaultCrossActivityBackAnimation defaultCrossActivityBackAnimation, CrossTaskBackAnimation crossTaskBackAnimation, CustomCrossActivityBackAnimation customCrossActivityBackAnimation) {
        SparseArray sparseArray = new SparseArray();
        this.mAnimationDefinition = sparseArray;
        this.mSupportedAnimatorsChanged = false;
        this.mSupportedAnimators = new ArrayList();
        sparseArray.set(2, defaultCrossActivityBackAnimation.backAnimationRunner);
        sparseArray.set(3, crossTaskBackAnimation.mBackAnimationRunner);
        this.mDefaultCrossActivityAnimation = defaultCrossActivityBackAnimation;
        this.mCustomizeActivityAnimation = customCrossActivityBackAnimation;
        this.mCrossTaskAnimation = crossTaskBackAnimation;
        updateSupportedAnimators();
    }

    public final void updateSupportedAnimators() {
        this.mSupportedAnimators.clear();
        for (int size = this.mAnimationDefinition.size() - 1; size >= 0; size--) {
            this.mSupportedAnimators.add(Integer.valueOf(this.mAnimationDefinition.keyAt(size)));
        }
        this.mSupportedAnimatorsChanged = true;
    }
}
