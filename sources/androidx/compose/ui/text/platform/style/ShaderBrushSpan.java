package androidx.compose.ui.text.platform.style;

import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.text.platform.AndroidTextPaint_androidKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShaderBrushSpan extends CharacterStyle implements UpdateAppearance {
    public final float alpha;
    public final ShaderBrush shaderBrush;
    public final MutableState size$delegate = SnapshotStateKt.mutableStateOf$default(new Size(9205357640488583168L));
    public final State shaderState = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.ui.text.platform.style.ShaderBrushSpan$shaderState$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            if (((Size) ((SnapshotMutableStateImpl) ShaderBrushSpan.this.size$delegate).getValue()).packedValue == 9205357640488583168L || Size.m330isEmptyimpl(((Size) ((SnapshotMutableStateImpl) ShaderBrushSpan.this.size$delegate).getValue()).packedValue)) {
                return null;
            }
            ShaderBrushSpan shaderBrushSpan = ShaderBrushSpan.this;
            return shaderBrushSpan.shaderBrush.mo359createShaderuvyYCjk(((Size) ((SnapshotMutableStateImpl) shaderBrushSpan.size$delegate).getValue()).packedValue);
        }
    });

    public ShaderBrushSpan(ShaderBrush shaderBrush, float f) {
        this.shaderBrush = shaderBrush;
        this.alpha = f;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        AndroidTextPaint_androidKt.setAlpha(textPaint, this.alpha);
        textPaint.setShader((Shader) this.shaderState.getValue());
    }
}
