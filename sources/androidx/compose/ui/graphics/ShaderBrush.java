package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShaderBrush extends Brush {
    public long createdSize = 9205357640488583168L;
    public Shader internalShader;

    @Override // androidx.compose.ui.graphics.Brush
    /* renamed from: applyTo-Pq9zytI */
    public final void mo358applyToPq9zytI(float f, long j, Paint paint) {
        Shader shader = this.internalShader;
        if (shader == null || !Size.m326equalsimpl0(this.createdSize, j)) {
            if (Size.m330isEmptyimpl(j)) {
                shader = null;
                this.internalShader = null;
                this.createdSize = 9205357640488583168L;
            } else {
                shader = mo359createShaderuvyYCjk(j);
                this.internalShader = shader;
                this.createdSize = j;
            }
        }
        AndroidPaint androidPaint = (AndroidPaint) paint;
        long Color = ColorKt.Color(androidPaint.internalPaint.getColor());
        long j2 = Color.Black;
        if (!Color.m363equalsimpl0(Color, j2)) {
            androidPaint.m348setColor8_81llA(j2);
        }
        if (!Intrinsics.areEqual(androidPaint.internalShader, shader)) {
            androidPaint.setShader(shader);
        }
        if (androidPaint.internalPaint.getAlpha() / 255.0f == f) {
            return;
        }
        androidPaint.setAlpha(f);
    }

    /* renamed from: createShader-uvyYCjk */
    public abstract Shader mo359createShaderuvyYCjk(long j);
}
