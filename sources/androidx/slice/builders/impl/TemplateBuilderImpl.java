package androidx.slice.builders.impl;

import androidx.slice.Slice;
import androidx.slice.SliceSpec;
import androidx.slice.SystemClock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TemplateBuilderImpl {
    public final SystemClock mClock;
    public final Slice.Builder mSliceBuilder;
    public final SliceSpec mSpec;

    public TemplateBuilderImpl(Slice.Builder builder, SliceSpec sliceSpec, SystemClock systemClock) {
        this.mSliceBuilder = builder;
        this.mSpec = sliceSpec;
        this.mClock = systemClock;
    }

    public abstract void apply(Slice.Builder builder);

    public Slice build() {
        Slice.Builder builder = this.mSliceBuilder;
        builder.mSpec = this.mSpec;
        apply(builder);
        return this.mSliceBuilder.build();
    }
}
