package com.android.systemui.shared.shadow;

import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DoubleShadowIconDrawable extends Drawable {
    public final int mCanvasSize;
    public final RenderNode mDoubleShadowNode;
    public final InsetDrawable mIconDrawable;

    public DoubleShadowIconDrawable(DoubleShadowTextHelper$ShadowInfo doubleShadowTextHelper$ShadowInfo, DoubleShadowTextHelper$ShadowInfo doubleShadowTextHelper$ShadowInfo2, Drawable drawable, int i, int i2) {
        int i3 = (i2 * 2) + i;
        this.mCanvasSize = i3;
        setBounds(0, 0, i3, i3);
        InsetDrawable insetDrawable = new InsetDrawable(drawable, i2);
        this.mIconDrawable = insetDrawable;
        insetDrawable.setBounds(0, 0, i3, i3);
        RenderNode renderNode = new RenderNode("DoubleShadowNode");
        renderNode.setPosition(0, 0, i3, i3);
        float f = doubleShadowTextHelper$ShadowInfo2.blur;
        int argb = Color.argb(doubleShadowTextHelper$ShadowInfo2.alpha, 0.0f, 0.0f, 0.0f);
        PorterDuff.Mode mode = PorterDuff.Mode.MULTIPLY;
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(argb, mode);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        RenderEffect createColorFilterEffect = RenderEffect.createColorFilterEffect(porterDuffColorFilter, RenderEffect.createOffsetEffect(doubleShadowTextHelper$ShadowInfo2.offsetX, doubleShadowTextHelper$ShadowInfo2.offsetY, RenderEffect.createBlurEffect(f, f, tileMode)));
        float f2 = doubleShadowTextHelper$ShadowInfo.blur;
        renderNode.setRenderEffect(RenderEffect.createBlendModeEffect(createColorFilterEffect, RenderEffect.createColorFilterEffect(new PorterDuffColorFilter(Color.argb(doubleShadowTextHelper$ShadowInfo.alpha, 0.0f, 0.0f, 0.0f), mode), RenderEffect.createOffsetEffect(doubleShadowTextHelper$ShadowInfo.offsetX, doubleShadowTextHelper$ShadowInfo.offsetY, RenderEffect.createBlurEffect(f2, f2, tileMode))), BlendMode.DST_ATOP));
        this.mDoubleShadowNode = renderNode;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (canvas.isHardwareAccelerated()) {
            if (!this.mDoubleShadowNode.hasDisplayList()) {
                this.mIconDrawable.draw(this.mDoubleShadowNode.beginRecording());
                this.mDoubleShadowNode.endRecording();
            }
            canvas.drawRenderNode(this.mDoubleShadowNode);
        }
        this.mIconDrawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.mCanvasSize;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.mCanvasSize;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.mIconDrawable.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.mIconDrawable.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        this.mIconDrawable.setTint(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        this.mIconDrawable.setTintList(colorStateList);
    }
}
