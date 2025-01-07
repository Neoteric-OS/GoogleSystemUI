package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShapeAppearanceModel {
    public EdgeTreatment bottomEdge;
    public CornerTreatment bottomLeftCorner;
    public CornerSize bottomLeftCornerSize;
    public CornerTreatment bottomRightCorner;
    public CornerSize bottomRightCornerSize;
    public EdgeTreatment leftEdge;
    public EdgeTreatment rightEdge;
    public EdgeTreatment topEdge;
    public CornerTreatment topLeftCorner;
    public CornerSize topLeftCornerSize;
    public CornerTreatment topRightCorner;
    public CornerSize topRightCornerSize;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public CornerTreatment topLeftCorner = new RoundedCornerTreatment();
        public CornerTreatment topRightCorner = new RoundedCornerTreatment();
        public CornerTreatment bottomRightCorner = new RoundedCornerTreatment();
        public CornerTreatment bottomLeftCorner = new RoundedCornerTreatment();
        public CornerSize topLeftCornerSize = new AbsoluteCornerSize(0.0f);
        public CornerSize topRightCornerSize = new AbsoluteCornerSize(0.0f);
        public CornerSize bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
        public CornerSize bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
        public EdgeTreatment topEdge = new EdgeTreatment();
        public EdgeTreatment rightEdge = new EdgeTreatment();
        public EdgeTreatment bottomEdge = new EdgeTreatment();
        public EdgeTreatment leftEdge = new EdgeTreatment();

        public static void compatCornerTreatmentSize(CornerTreatment cornerTreatment) {
            if (cornerTreatment instanceof RoundedCornerTreatment) {
                return;
            }
            boolean z = cornerTreatment instanceof CutCornerTreatment;
        }

        public final ShapeAppearanceModel build() {
            ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel();
            shapeAppearanceModel.topLeftCorner = this.topLeftCorner;
            shapeAppearanceModel.topRightCorner = this.topRightCorner;
            shapeAppearanceModel.bottomRightCorner = this.bottomRightCorner;
            shapeAppearanceModel.bottomLeftCorner = this.bottomLeftCorner;
            shapeAppearanceModel.topLeftCornerSize = this.topLeftCornerSize;
            shapeAppearanceModel.topRightCornerSize = this.topRightCornerSize;
            shapeAppearanceModel.bottomRightCornerSize = this.bottomRightCornerSize;
            shapeAppearanceModel.bottomLeftCornerSize = this.bottomLeftCornerSize;
            shapeAppearanceModel.topEdge = this.topEdge;
            shapeAppearanceModel.rightEdge = this.rightEdge;
            shapeAppearanceModel.bottomEdge = this.bottomEdge;
            shapeAppearanceModel.leftEdge = this.leftEdge;
            return shapeAppearanceModel;
        }
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2) {
        AbsoluteCornerSize absoluteCornerSize = new AbsoluteCornerSize(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, resourceId);
        if (resourceId2 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, resourceId2);
        }
        TypedArray obtainStyledAttributes2 = contextThemeWrapper.obtainStyledAttributes(R$styleable.ShapeAppearance);
        try {
            int i3 = obtainStyledAttributes2.getInt(0, 0);
            int i4 = obtainStyledAttributes2.getInt(3, i3);
            int i5 = obtainStyledAttributes2.getInt(4, i3);
            int i6 = obtainStyledAttributes2.getInt(2, i3);
            int i7 = obtainStyledAttributes2.getInt(1, i3);
            CornerSize cornerSize = getCornerSize(obtainStyledAttributes2, 5, absoluteCornerSize);
            CornerSize cornerSize2 = getCornerSize(obtainStyledAttributes2, 8, cornerSize);
            CornerSize cornerSize3 = getCornerSize(obtainStyledAttributes2, 9, cornerSize);
            CornerSize cornerSize4 = getCornerSize(obtainStyledAttributes2, 7, cornerSize);
            CornerSize cornerSize5 = getCornerSize(obtainStyledAttributes2, 6, cornerSize);
            Builder builder = new Builder();
            CornerTreatment createCornerTreatment = MaterialShapeUtils.createCornerTreatment(i4);
            builder.topLeftCorner = createCornerTreatment;
            Builder.compatCornerTreatmentSize(createCornerTreatment);
            builder.topLeftCornerSize = cornerSize2;
            CornerTreatment createCornerTreatment2 = MaterialShapeUtils.createCornerTreatment(i5);
            builder.topRightCorner = createCornerTreatment2;
            Builder.compatCornerTreatmentSize(createCornerTreatment2);
            builder.topRightCornerSize = cornerSize3;
            CornerTreatment createCornerTreatment3 = MaterialShapeUtils.createCornerTreatment(i6);
            builder.bottomRightCorner = createCornerTreatment3;
            Builder.compatCornerTreatmentSize(createCornerTreatment3);
            builder.bottomRightCornerSize = cornerSize4;
            CornerTreatment createCornerTreatment4 = MaterialShapeUtils.createCornerTreatment(i7);
            builder.bottomLeftCorner = createCornerTreatment4;
            Builder.compatCornerTreatmentSize(createCornerTreatment4);
            builder.bottomLeftCornerSize = cornerSize5;
            return builder;
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    public static CornerSize getCornerSize(TypedArray typedArray, int i, CornerSize cornerSize) {
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue == null) {
            return cornerSize;
        }
        int i2 = peekValue.type;
        return i2 == 5 ? new AbsoluteCornerSize(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new RelativeCornerSize(peekValue.getFraction(1.0f, 1.0f)) : cornerSize;
    }

    public final boolean isRoundRect(RectF rectF) {
        boolean z = this.leftEdge.getClass().equals(EdgeTreatment.class) && this.rightEdge.getClass().equals(EdgeTreatment.class) && this.topEdge.getClass().equals(EdgeTreatment.class) && this.bottomEdge.getClass().equals(EdgeTreatment.class);
        float cornerSize = this.topLeftCornerSize.getCornerSize(rectF);
        return z && ((this.topRightCornerSize.getCornerSize(rectF) > cornerSize ? 1 : (this.topRightCornerSize.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.bottomLeftCornerSize.getCornerSize(rectF) > cornerSize ? 1 : (this.bottomLeftCornerSize.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.bottomRightCornerSize.getCornerSize(rectF) > cornerSize ? 1 : (this.bottomRightCornerSize.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0) && ((this.topRightCorner instanceof RoundedCornerTreatment) && (this.topLeftCorner instanceof RoundedCornerTreatment) && (this.bottomRightCorner instanceof RoundedCornerTreatment) && (this.bottomLeftCorner instanceof RoundedCornerTreatment));
    }

    public final Builder toBuilder() {
        Builder builder = new Builder();
        builder.topLeftCorner = this.topLeftCorner;
        builder.topRightCorner = this.topRightCorner;
        builder.bottomRightCorner = this.bottomRightCorner;
        builder.bottomLeftCorner = this.bottomLeftCorner;
        builder.topLeftCornerSize = this.topLeftCornerSize;
        builder.topRightCornerSize = this.topRightCornerSize;
        builder.bottomRightCornerSize = this.bottomRightCornerSize;
        builder.bottomLeftCornerSize = this.bottomLeftCornerSize;
        builder.topEdge = this.topEdge;
        builder.rightEdge = this.rightEdge;
        builder.bottomEdge = this.bottomEdge;
        builder.leftEdge = this.leftEdge;
        return builder;
    }
}
