package com.google.android.material.shape;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.util.BitSet;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaterialShapeDrawable extends Drawable implements Shapeable {
    public static final Paint clearPaint = null;
    public final BitSet containsIncompatibleShadowOp;
    public final ShapePath.ShadowCompatOperation[] cornerShadowOperation;
    public MaterialShapeDrawableState drawableState;
    public final ShapePath.ShadowCompatOperation[] edgeShadowOperation;
    public final Paint fillPaint;
    public final RectF insetRectF;
    public final Matrix matrix;
    public final Path path;
    public final RectF pathBounds;
    public boolean pathDirty;
    public final Path pathInsetByStroke;
    public final ShapeAppearancePathProvider pathProvider;
    public final AnonymousClass1 pathShadowListener;
    public final RectF rectF;
    public final Region scratchRegion;
    public final ShadowRenderer shadowRenderer;
    public final Paint strokePaint;
    public ShapeAppearanceModel strokeShapeAppearance;
    public PorterDuffColorFilter strokeTintFilter;
    public PorterDuffColorFilter tintFilter;
    public final Region transparentRegion;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.material.shape.MaterialShapeDrawable$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MaterialShapeDrawableState extends Drawable.ConstantState {
        public int alpha;
        public float elevation;
        public ElevationOverlayProvider elevationOverlayProvider;
        public ColorStateList fillColor;
        public float interpolation;
        public Rect padding;
        public Paint.Style paintStyle;
        public float parentAbsoluteElevation;
        public float scale;
        public int shadowCompatOffset;
        public int shadowCompatRadius;
        public ShapeAppearanceModel shapeAppearanceModel;
        public ColorStateList strokeColor;
        public float strokeWidth;
        public ColorStateList tintList;
        public PorterDuff.Mode tintMode;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this);
            materialShapeDrawable.pathDirty = true;
            return materialShapeDrawable;
        }
    }

    static {
        Paint paint = new Paint(1);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialShapeDrawable(com.google.android.material.shape.ShapeAppearanceModel r4) {
        /*
            r3 = this;
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r0 = new com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState
            r0.<init>()
            r1 = 0
            r0.fillColor = r1
            r0.strokeColor = r1
            r0.tintList = r1
            android.graphics.PorterDuff$Mode r2 = android.graphics.PorterDuff.Mode.SRC_IN
            r0.tintMode = r2
            r0.padding = r1
            r2 = 1065353216(0x3f800000, float:1.0)
            r0.scale = r2
            r0.interpolation = r2
            r2 = 255(0xff, float:3.57E-43)
            r0.alpha = r2
            r2 = 0
            r0.parentAbsoluteElevation = r2
            r0.elevation = r2
            r2 = 0
            r0.shadowCompatRadius = r2
            r0.shadowCompatOffset = r2
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.FILL_AND_STROKE
            r0.paintStyle = r2
            r0.shapeAppearanceModel = r4
            r0.elevationOverlayProvider = r1
            r3.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.shape.MaterialShapeDrawable.<init>(com.google.android.material.shape.ShapeAppearanceModel):void");
    }

    public final void calculatePath(RectF rectF, Path path) {
        ShapeAppearancePathProvider shapeAppearancePathProvider = this.pathProvider;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        shapeAppearancePathProvider.calculatePath(materialShapeDrawableState.shapeAppearanceModel, materialShapeDrawableState.interpolation, rectF, this.pathShadowListener, path);
        if (this.drawableState.scale != 1.0f) {
            this.matrix.reset();
            Matrix matrix = this.matrix;
            float f = this.drawableState.scale;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.matrix);
        }
        path.computeBounds(this.pathBounds, true);
    }

    public final int compositeElevationOverlayIfNeeded(int i) {
        int i2;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        float f = materialShapeDrawableState.elevation + 0.0f + materialShapeDrawableState.parentAbsoluteElevation;
        ElevationOverlayProvider elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
        if (elevationOverlayProvider == null || !elevationOverlayProvider.elevationOverlayEnabled || ColorUtils.setAlphaComponent(i, 255) != elevationOverlayProvider.colorSurface) {
            return i;
        }
        float min = (elevationOverlayProvider.displayDensity <= 0.0f || f <= 0.0f) ? 0.0f : Math.min(((((float) Math.log1p(f / r3)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        int alpha = Color.alpha(i);
        int layer = MaterialColors.layer(ColorUtils.setAlphaComponent(i, 255), min, elevationOverlayProvider.elevationOverlayColor);
        if (min > 0.0f && (i2 = elevationOverlayProvider.elevationOverlayAccentColor) != 0) {
            layer = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i2, ElevationOverlayProvider.OVERLAY_ACCENT_COLOR_ALPHA), layer);
        }
        return ColorUtils.setAlphaComponent(layer, alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        this.fillPaint.setColorFilter(this.tintFilter);
        int alpha = this.fillPaint.getAlpha();
        Paint paint = this.fillPaint;
        int i = this.drawableState.alpha;
        paint.setAlpha(((i + (i >>> 7)) * alpha) >>> 8);
        this.strokePaint.setColorFilter(this.strokeTintFilter);
        this.strokePaint.setStrokeWidth(this.drawableState.strokeWidth);
        int alpha2 = this.strokePaint.getAlpha();
        Paint paint2 = this.strokePaint;
        int i2 = this.drawableState.alpha;
        paint2.setAlpha(((i2 + (i2 >>> 7)) * alpha2) >>> 8);
        if (this.pathDirty) {
            float f = -(hasStroke() ? this.strokePaint.getStrokeWidth() / 2.0f : 0.0f);
            ShapeAppearanceModel shapeAppearanceModel = this.drawableState.shapeAppearanceModel;
            ShapeAppearanceModel.Builder builder = shapeAppearanceModel.toBuilder();
            CornerSize cornerSize = shapeAppearanceModel.topLeftCornerSize;
            if (!(cornerSize instanceof RelativeCornerSize)) {
                cornerSize = new AdjustedCornerSize(f, cornerSize);
            }
            builder.topLeftCornerSize = cornerSize;
            CornerSize cornerSize2 = shapeAppearanceModel.topRightCornerSize;
            if (!(cornerSize2 instanceof RelativeCornerSize)) {
                cornerSize2 = new AdjustedCornerSize(f, cornerSize2);
            }
            builder.topRightCornerSize = cornerSize2;
            CornerSize cornerSize3 = shapeAppearanceModel.bottomLeftCornerSize;
            if (!(cornerSize3 instanceof RelativeCornerSize)) {
                cornerSize3 = new AdjustedCornerSize(f, cornerSize3);
            }
            builder.bottomLeftCornerSize = cornerSize3;
            CornerSize cornerSize4 = shapeAppearanceModel.bottomRightCornerSize;
            if (!(cornerSize4 instanceof RelativeCornerSize)) {
                cornerSize4 = new AdjustedCornerSize(f, cornerSize4);
            }
            builder.bottomRightCornerSize = cornerSize4;
            ShapeAppearanceModel build = builder.build();
            this.strokeShapeAppearance = build;
            ShapeAppearancePathProvider shapeAppearancePathProvider = this.pathProvider;
            float f2 = this.drawableState.interpolation;
            this.insetRectF.set(getBoundsAsRectF());
            float strokeWidth = hasStroke() ? this.strokePaint.getStrokeWidth() / 2.0f : 0.0f;
            this.insetRectF.inset(strokeWidth, strokeWidth);
            shapeAppearancePathProvider.calculatePath(build, f2, this.insetRectF, null, this.pathInsetByStroke);
            calculatePath(getBoundsAsRectF(), this.path);
            this.pathDirty = false;
        }
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        materialShapeDrawableState.getClass();
        if (materialShapeDrawableState.shadowCompatRadius > 0 && !this.drawableState.shapeAppearanceModel.isRoundRect(getBoundsAsRectF())) {
            this.path.isConvex();
        }
        MaterialShapeDrawableState materialShapeDrawableState2 = this.drawableState;
        Paint.Style style = materialShapeDrawableState2.paintStyle;
        if (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL) {
            Paint paint3 = this.fillPaint;
            Path path = this.path;
            ShapeAppearanceModel shapeAppearanceModel2 = materialShapeDrawableState2.shapeAppearanceModel;
            RectF boundsAsRectF = getBoundsAsRectF();
            if (shapeAppearanceModel2.isRoundRect(boundsAsRectF)) {
                float cornerSize5 = shapeAppearanceModel2.topRightCornerSize.getCornerSize(boundsAsRectF) * this.drawableState.interpolation;
                canvas.drawRoundRect(boundsAsRectF, cornerSize5, cornerSize5, paint3);
            } else {
                canvas.drawPath(path, paint3);
            }
        }
        if (hasStroke()) {
            Paint paint4 = this.strokePaint;
            Path path2 = this.pathInsetByStroke;
            ShapeAppearanceModel shapeAppearanceModel3 = this.strokeShapeAppearance;
            this.insetRectF.set(getBoundsAsRectF());
            float strokeWidth2 = hasStroke() ? this.strokePaint.getStrokeWidth() / 2.0f : 0.0f;
            this.insetRectF.inset(strokeWidth2, strokeWidth2);
            RectF rectF = this.insetRectF;
            if (shapeAppearanceModel3.isRoundRect(rectF)) {
                float cornerSize6 = shapeAppearanceModel3.topRightCornerSize.getCornerSize(rectF) * this.drawableState.interpolation;
                canvas.drawRoundRect(rectF, cornerSize6, cornerSize6, paint4);
            } else {
                canvas.drawPath(path2, paint4);
            }
        }
        this.fillPaint.setAlpha(alpha);
        this.strokePaint.setAlpha(alpha2);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.drawableState.alpha;
    }

    public final RectF getBoundsAsRectF() {
        this.rectF.set(getBounds());
        return this.rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        this.drawableState.getClass();
        if (this.drawableState.shapeAppearanceModel.isRoundRect(getBoundsAsRectF())) {
            outline.setRoundRect(getBounds(), this.drawableState.shapeAppearanceModel.topLeftCornerSize.getCornerSize(getBoundsAsRectF()) * this.drawableState.interpolation);
        } else {
            calculatePath(getBoundsAsRectF(), this.path);
            outline.setPath(this.path);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.drawableState.padding;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        this.transparentRegion.set(getBounds());
        calculatePath(getBoundsAsRectF(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public final boolean hasStroke() {
        Paint.Style style = this.drawableState.paintStyle;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.strokePaint.getStrokeWidth() > 0.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.pathDirty = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        if (!super.isStateful() && ((colorStateList = this.drawableState.tintList) == null || !colorStateList.isStateful())) {
            this.drawableState.getClass();
            ColorStateList colorStateList3 = this.drawableState.strokeColor;
            if ((colorStateList3 == null || !colorStateList3.isStateful()) && ((colorStateList2 = this.drawableState.fillColor) == null || !colorStateList2.isStateful())) {
                return false;
            }
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        MaterialShapeDrawableState materialShapeDrawableState2 = new MaterialShapeDrawableState();
        materialShapeDrawableState2.fillColor = null;
        materialShapeDrawableState2.strokeColor = null;
        materialShapeDrawableState2.tintList = null;
        materialShapeDrawableState2.tintMode = PorterDuff.Mode.SRC_IN;
        materialShapeDrawableState2.padding = null;
        materialShapeDrawableState2.scale = 1.0f;
        materialShapeDrawableState2.interpolation = 1.0f;
        materialShapeDrawableState2.alpha = 255;
        materialShapeDrawableState2.parentAbsoluteElevation = 0.0f;
        materialShapeDrawableState2.elevation = 0.0f;
        materialShapeDrawableState2.shadowCompatRadius = 0;
        materialShapeDrawableState2.shadowCompatOffset = 0;
        materialShapeDrawableState2.paintStyle = Paint.Style.FILL_AND_STROKE;
        materialShapeDrawableState2.shapeAppearanceModel = materialShapeDrawableState.shapeAppearanceModel;
        materialShapeDrawableState2.elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
        materialShapeDrawableState2.strokeWidth = materialShapeDrawableState.strokeWidth;
        materialShapeDrawableState2.fillColor = materialShapeDrawableState.fillColor;
        materialShapeDrawableState2.strokeColor = materialShapeDrawableState.strokeColor;
        materialShapeDrawableState2.tintMode = materialShapeDrawableState.tintMode;
        materialShapeDrawableState2.tintList = materialShapeDrawableState.tintList;
        materialShapeDrawableState2.alpha = materialShapeDrawableState.alpha;
        materialShapeDrawableState2.scale = materialShapeDrawableState.scale;
        materialShapeDrawableState2.shadowCompatOffset = materialShapeDrawableState.shadowCompatOffset;
        materialShapeDrawableState2.interpolation = materialShapeDrawableState.interpolation;
        materialShapeDrawableState2.parentAbsoluteElevation = materialShapeDrawableState.parentAbsoluteElevation;
        materialShapeDrawableState2.elevation = materialShapeDrawableState.elevation;
        materialShapeDrawableState2.shadowCompatRadius = materialShapeDrawableState.shadowCompatRadius;
        materialShapeDrawableState2.paintStyle = materialShapeDrawableState.paintStyle;
        if (materialShapeDrawableState.padding != null) {
            materialShapeDrawableState2.padding = new Rect(materialShapeDrawableState.padding);
        }
        this.drawableState = materialShapeDrawableState2;
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.pathDirty = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z = updateColorsForState(iArr) || updateTintFilter();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.alpha != i) {
            materialShapeDrawableState.alpha = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.drawableState.getClass();
        super.invalidateSelf();
    }

    public final void setElevation(float f) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.elevation != f) {
            materialShapeDrawableState.elevation = f;
            updateZ();
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        this.drawableState.tintList = colorStateList;
        updateTintFilter();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.tintMode != mode) {
            materialShapeDrawableState.tintMode = mode;
            updateTintFilter();
            super.invalidateSelf();
        }
    }

    public final boolean updateColorsForState(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.drawableState.fillColor == null || color2 == (colorForState2 = this.drawableState.fillColor.getColorForState(iArr, (color2 = this.fillPaint.getColor())))) {
            z = false;
        } else {
            this.fillPaint.setColor(colorForState2);
            z = true;
        }
        if (this.drawableState.strokeColor == null || color == (colorForState = this.drawableState.strokeColor.getColorForState(iArr, (color = this.strokePaint.getColor())))) {
            return z;
        }
        this.strokePaint.setColor(colorForState);
        return true;
    }

    public final boolean updateTintFilter() {
        PorterDuffColorFilter porterDuffColorFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.tintFilter;
        PorterDuffColorFilter porterDuffColorFilter3 = this.strokeTintFilter;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        ColorStateList colorStateList = materialShapeDrawableState.tintList;
        PorterDuff.Mode mode = materialShapeDrawableState.tintMode;
        Paint paint = this.fillPaint;
        if (colorStateList == null || mode == null) {
            int color = paint.getColor();
            int compositeElevationOverlayIfNeeded = compositeElevationOverlayIfNeeded(color);
            porterDuffColorFilter = compositeElevationOverlayIfNeeded != color ? new PorterDuffColorFilter(compositeElevationOverlayIfNeeded, PorterDuff.Mode.SRC_IN) : null;
        } else {
            porterDuffColorFilter = new PorterDuffColorFilter(compositeElevationOverlayIfNeeded(colorStateList.getColorForState(getState(), 0)), mode);
        }
        this.tintFilter = porterDuffColorFilter;
        this.drawableState.getClass();
        this.strokeTintFilter = null;
        this.drawableState.getClass();
        return (Objects.equals(porterDuffColorFilter2, this.tintFilter) && Objects.equals(porterDuffColorFilter3, this.strokeTintFilter)) ? false : true;
    }

    public final void updateZ() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        float f = materialShapeDrawableState.elevation + 0.0f;
        materialShapeDrawableState.shadowCompatRadius = (int) Math.ceil(0.75f * f);
        this.drawableState.shadowCompatOffset = (int) Math.ceil(f * 0.25f);
        updateTintFilter();
        super.invalidateSelf();
    }

    public MaterialShapeDrawable(MaterialShapeDrawableState materialShapeDrawableState) {
        ShapeAppearancePathProvider shapeAppearancePathProvider;
        this.cornerShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.containsIncompatibleShadowOp = new BitSet(8);
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        Paint paint = new Paint(1);
        this.fillPaint = paint;
        Paint paint2 = new Paint(1);
        this.strokePaint = paint2;
        new ShadowRenderer();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            shapeAppearancePathProvider = ShapeAppearancePathProvider.Lazy.INSTANCE;
        } else {
            shapeAppearancePathProvider = new ShapeAppearancePathProvider();
        }
        this.pathProvider = shapeAppearancePathProvider;
        this.pathBounds = new RectF();
        this.drawableState = materialShapeDrawableState;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        updateTintFilter();
        updateColorsForState(getState());
        this.pathShadowListener = new AnonymousClass1();
    }
}
