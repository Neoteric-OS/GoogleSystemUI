package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SolidColor extends Brush {
    public final long value;

    public SolidColor(long j) {
        this.value = j;
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* renamed from: applyTo-Pq9zytI */
    public final void mo358applyToPq9zytI(float f, long j, Paint paint) {
        AndroidPaint androidPaint = (AndroidPaint) paint;
        androidPaint.setAlpha(1.0f);
        long j2 = this.value;
        if (f != 1.0f) {
            j2 = ColorKt.Color(Color.m368getRedimpl(j2), Color.m367getGreenimpl(j2), Color.m365getBlueimpl(j2), Color.m364getAlphaimpl(j2) * f, Color.m366getColorSpaceimpl(j2));
        }
        androidPaint.m348setColor8_81llA(j2);
        if (androidPaint.internalShader != null) {
            androidPaint.setShader(null);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SolidColor) {
            return Color.m363equalsimpl0(this.value, ((SolidColor) obj).value);
        }
        return false;
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return "SolidColor(value=" + ((Object) Color.m369toStringimpl(this.value)) + ')';
    }
}
