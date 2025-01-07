package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VelocityTracker {
    public long lastMoveEventTimeStamp;
    public final VelocityTracker1D xVelocityTracker = new VelocityTracker1D();
    public final VelocityTracker1D yVelocityTracker = new VelocityTracker1D();

    /* renamed from: calculateVelocity-AH228Gc, reason: not valid java name */
    public final long m470calculateVelocityAH228Gc(long j) {
        if (Velocity.m694getXimpl(j) <= 0.0f || Velocity.m695getYimpl(j) <= 0.0f) {
            InlineClassHelperKt.throwIllegalStateException("maximumVelocity should be a positive value. You specified=" + ((Object) Velocity.m699toStringimpl(j)));
        }
        return VelocityKt.Velocity(this.xVelocityTracker.calculateVelocity(Velocity.m694getXimpl(j)), this.yVelocityTracker.calculateVelocity(Velocity.m695getYimpl(j)));
    }

    public final void resetTracking() {
        this.xVelocityTracker.resetTracking();
        this.yVelocityTracker.resetTracking();
        this.lastMoveEventTimeStamp = 0L;
    }
}
