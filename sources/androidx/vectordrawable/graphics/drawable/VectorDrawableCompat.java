package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import com.android.app.viewcapture.data.ViewNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VectorDrawableCompat extends VectorDrawableCommon {
    public static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    public boolean mAllowCaching;
    public ColorFilter mColorFilter;
    public boolean mMutated;
    public PorterDuffColorFilter mTintFilter;
    public final Rect mTmpBounds;
    public final float[] mTmpFloats;
    public final Matrix mTmpMatrix;
    public VectorDrawableCompatState mVectorState;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VClipPath extends VPath {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VFullPath extends VPath {
        public float mFillAlpha;
        public ComplexColorCompat mFillColor;
        public float mStrokeAlpha;
        public ComplexColorCompat mStrokeColor;
        public Paint.Cap mStrokeLineCap;
        public Paint.Join mStrokeLineJoin;
        public float mStrokeMiterlimit;
        public float mStrokeWidth;
        public float mTrimPathEnd;
        public float mTrimPathOffset;
        public float mTrimPathStart;

        public float getFillAlpha() {
            return this.mFillAlpha;
        }

        public int getFillColor() {
            return this.mFillColor.mColor;
        }

        public float getStrokeAlpha() {
            return this.mStrokeAlpha;
        }

        public int getStrokeColor() {
            return this.mStrokeColor.mColor;
        }

        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        public float getTrimPathEnd() {
            return this.mTrimPathEnd;
        }

        public float getTrimPathOffset() {
            return this.mTrimPathOffset;
        }

        public float getTrimPathStart() {
            return this.mTrimPathStart;
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public final boolean isStateful() {
            return this.mFillColor.isStateful() || this.mStrokeColor.isStateful();
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean onStateChanged(int[] r6) {
            /*
                r5 = this;
                androidx.core.content.res.ComplexColorCompat r0 = r5.mFillColor
                boolean r1 = r0.isStateful()
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L1c
                android.content.res.ColorStateList r1 = r0.mColorStateList
                int r4 = r1.getDefaultColor()
                int r1 = r1.getColorForState(r6, r4)
                int r4 = r0.mColor
                if (r1 == r4) goto L1c
                r0.mColor = r1
                r0 = r3
                goto L1d
            L1c:
                r0 = r2
            L1d:
                androidx.core.content.res.ComplexColorCompat r5 = r5.mStrokeColor
                boolean r1 = r5.isStateful()
                if (r1 == 0) goto L36
                android.content.res.ColorStateList r1 = r5.mColorStateList
                int r4 = r1.getDefaultColor()
                int r6 = r1.getColorForState(r6, r4)
                int r1 = r5.mColor
                if (r6 == r1) goto L36
                r5.mColor = r6
                r2 = r3
            L36:
                r5 = r0 | r2
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VFullPath.onStateChanged(int[]):boolean");
        }

        public void setFillAlpha(float f) {
            this.mFillAlpha = f;
        }

        public void setFillColor(int i) {
            this.mFillColor.mColor = i;
        }

        public void setStrokeAlpha(float f) {
            this.mStrokeAlpha = f;
        }

        public void setStrokeColor(int i) {
            this.mStrokeColor.mColor = i;
        }

        public void setStrokeWidth(float f) {
            this.mStrokeWidth = f;
        }

        public void setTrimPathEnd(float f) {
            this.mTrimPathEnd = f;
        }

        public void setTrimPathOffset(float f) {
            this.mTrimPathOffset = f;
        }

        public void setTrimPathStart(float f) {
            this.mTrimPathStart = f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class VObject {
        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VectorDrawableCompatState extends Drawable.ConstantState {
        public boolean mAutoMirrored;
        public boolean mCacheDirty;
        public boolean mCachedAutoMirrored;
        public Bitmap mCachedBitmap;
        public int mCachedRootAlpha;
        public ColorStateList mCachedTint;
        public PorterDuff.Mode mCachedTintMode;
        public int mChangingConfigurations;
        public Paint mTempPaint;
        public ColorStateList mTint;
        public PorterDuff.Mode mTintMode;
        public VPathRenderer mVPathRenderer;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }
    }

    public VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        VectorDrawableCompatState vectorDrawableCompatState = new VectorDrawableCompatState();
        vectorDrawableCompatState.mTint = null;
        vectorDrawableCompatState.mTintMode = DEFAULT_TINT_MODE;
        vectorDrawableCompatState.mVPathRenderer = new VPathRenderer();
        this.mVectorState = vectorDrawableCompatState;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable == null) {
            return false;
        }
        drawable.canApplyTheme();
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.mTmpBounds);
        if (this.mTmpBounds.width() <= 0 || this.mTmpBounds.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.mColorFilter;
        if (colorFilter == null) {
            colorFilter = this.mTintFilter;
        }
        canvas.getMatrix(this.mTmpMatrix);
        this.mTmpMatrix.getValues(this.mTmpFloats);
        float abs = Math.abs(this.mTmpFloats[0]);
        float abs2 = Math.abs(this.mTmpFloats[4]);
        float abs3 = Math.abs(this.mTmpFloats[1]);
        float abs4 = Math.abs(this.mTmpFloats[3]);
        if (abs3 != 0.0f || abs4 != 0.0f) {
            abs = 1.0f;
            abs2 = 1.0f;
        }
        int min = Math.min(2048, (int) (this.mTmpBounds.width() * abs));
        int min2 = Math.min(2048, (int) (this.mTmpBounds.height() * abs2));
        if (min <= 0 || min2 <= 0) {
            return;
        }
        int save = canvas.save();
        Rect rect = this.mTmpBounds;
        canvas.translate(rect.left, rect.top);
        if (isAutoMirrored() && getLayoutDirection() == 1) {
            canvas.translate(this.mTmpBounds.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.mTmpBounds.offsetTo(0, 0);
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        Bitmap bitmap = vectorDrawableCompatState.mCachedBitmap;
        if (bitmap == null || min != bitmap.getWidth() || min2 != vectorDrawableCompatState.mCachedBitmap.getHeight()) {
            vectorDrawableCompatState.mCachedBitmap = Bitmap.createBitmap(min, min2, Bitmap.Config.ARGB_8888);
            vectorDrawableCompatState.mCacheDirty = true;
        }
        if (this.mAllowCaching) {
            VectorDrawableCompatState vectorDrawableCompatState2 = this.mVectorState;
            if (vectorDrawableCompatState2.mCacheDirty || vectorDrawableCompatState2.mCachedTint != vectorDrawableCompatState2.mTint || vectorDrawableCompatState2.mCachedTintMode != vectorDrawableCompatState2.mTintMode || vectorDrawableCompatState2.mCachedAutoMirrored != vectorDrawableCompatState2.mAutoMirrored || vectorDrawableCompatState2.mCachedRootAlpha != vectorDrawableCompatState2.mVPathRenderer.getRootAlpha()) {
                VectorDrawableCompatState vectorDrawableCompatState3 = this.mVectorState;
                vectorDrawableCompatState3.mCachedBitmap.eraseColor(0);
                Canvas canvas2 = new Canvas(vectorDrawableCompatState3.mCachedBitmap);
                VPathRenderer vPathRenderer = vectorDrawableCompatState3.mVPathRenderer;
                vPathRenderer.drawGroupTree(vPathRenderer.mRootGroup, VPathRenderer.IDENTITY_MATRIX, canvas2, min, min2);
                VectorDrawableCompatState vectorDrawableCompatState4 = this.mVectorState;
                vectorDrawableCompatState4.mCachedTint = vectorDrawableCompatState4.mTint;
                vectorDrawableCompatState4.mCachedTintMode = vectorDrawableCompatState4.mTintMode;
                vectorDrawableCompatState4.mCachedRootAlpha = vectorDrawableCompatState4.mVPathRenderer.getRootAlpha();
                vectorDrawableCompatState4.mCachedAutoMirrored = vectorDrawableCompatState4.mAutoMirrored;
                vectorDrawableCompatState4.mCacheDirty = false;
            }
        } else {
            VectorDrawableCompatState vectorDrawableCompatState5 = this.mVectorState;
            vectorDrawableCompatState5.mCachedBitmap.eraseColor(0);
            Canvas canvas3 = new Canvas(vectorDrawableCompatState5.mCachedBitmap);
            VPathRenderer vPathRenderer2 = vectorDrawableCompatState5.mVPathRenderer;
            vPathRenderer2.drawGroupTree(vPathRenderer2.mRootGroup, VPathRenderer.IDENTITY_MATRIX, canvas3, min, min2);
        }
        VectorDrawableCompatState vectorDrawableCompatState6 = this.mVectorState;
        Rect rect2 = this.mTmpBounds;
        if (vectorDrawableCompatState6.mVPathRenderer.getRootAlpha() >= 255 && colorFilter == null) {
            paint = null;
        } else {
            if (vectorDrawableCompatState6.mTempPaint == null) {
                Paint paint2 = new Paint();
                vectorDrawableCompatState6.mTempPaint = paint2;
                paint2.setFilterBitmap(true);
            }
            vectorDrawableCompatState6.mTempPaint.setAlpha(vectorDrawableCompatState6.mVPathRenderer.getRootAlpha());
            vectorDrawableCompatState6.mTempPaint.setColorFilter(colorFilter);
            paint = vectorDrawableCompatState6.mTempPaint;
        }
        canvas.drawBitmap(vectorDrawableCompatState6.mCachedBitmap, (Rect) null, rect2, paint);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.mDelegateDrawable;
        return drawable != null ? drawable.getAlpha() : this.mVectorState.mVPathRenderer.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.mVectorState.getChangingConfigurations() | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.mDelegateDrawable;
        return drawable != null ? drawable.getColorFilter() : this.mColorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.mDelegateDrawable != null) {
            return new VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        }
        this.mVectorState.mChangingConfigurations = getChangingConfigurations();
        return this.mVectorState;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.mDelegateDrawable;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.mVectorState.mVPathRenderer.mBaseHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.mDelegateDrawable;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.mVectorState.mVPathRenderer.mBaseWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.mDelegateDrawable;
        return drawable != null ? drawable.isAutoMirrored() : this.mVectorState.mAutoMirrored;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (!super.isStateful()) {
            VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
            if (vectorDrawableCompatState != null) {
                VPathRenderer vPathRenderer = vectorDrawableCompatState.mVPathRenderer;
                if (vPathRenderer.mIsStateful == null) {
                    vPathRenderer.mIsStateful = Boolean.valueOf(vPathRenderer.mRootGroup.isStateful());
                }
                if (vPathRenderer.mIsStateful.booleanValue() || ((colorStateList = this.mVectorState.mTint) != null && colorStateList.isStateful())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.mMutated && super.mutate() == this) {
            VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
            VectorDrawableCompatState vectorDrawableCompatState2 = new VectorDrawableCompatState();
            vectorDrawableCompatState2.mTint = null;
            vectorDrawableCompatState2.mTintMode = DEFAULT_TINT_MODE;
            if (vectorDrawableCompatState != null) {
                vectorDrawableCompatState2.mChangingConfigurations = vectorDrawableCompatState.mChangingConfigurations;
                VPathRenderer vPathRenderer = new VPathRenderer(vectorDrawableCompatState.mVPathRenderer);
                vectorDrawableCompatState2.mVPathRenderer = vPathRenderer;
                if (vectorDrawableCompatState.mVPathRenderer.mFillPaint != null) {
                    vPathRenderer.mFillPaint = new Paint(vectorDrawableCompatState.mVPathRenderer.mFillPaint);
                }
                if (vectorDrawableCompatState.mVPathRenderer.mStrokePaint != null) {
                    vectorDrawableCompatState2.mVPathRenderer.mStrokePaint = new Paint(vectorDrawableCompatState.mVPathRenderer.mStrokePaint);
                }
                vectorDrawableCompatState2.mTint = vectorDrawableCompatState.mTint;
                vectorDrawableCompatState2.mTintMode = vectorDrawableCompatState.mTintMode;
                vectorDrawableCompatState2.mAutoMirrored = vectorDrawableCompatState.mAutoMirrored;
            }
            this.mVectorState = vectorDrawableCompatState2;
            this.mMutated = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        ColorStateList colorStateList = vectorDrawableCompatState.mTint;
        if (colorStateList == null || (mode = vectorDrawableCompatState.mTintMode) == null) {
            z = false;
        } else {
            this.mTintFilter = updateTintFilter(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        VPathRenderer vPathRenderer = vectorDrawableCompatState.mVPathRenderer;
        if (vPathRenderer.mIsStateful == null) {
            vPathRenderer.mIsStateful = Boolean.valueOf(vPathRenderer.mRootGroup.isStateful());
        }
        if (vPathRenderer.mIsStateful.booleanValue()) {
            boolean onStateChanged = vectorDrawableCompatState.mVPathRenderer.mRootGroup.onStateChanged(iArr);
            vectorDrawableCompatState.mCacheDirty |= onStateChanged;
            if (onStateChanged) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.mVectorState.mVPathRenderer.getRootAlpha() != i) {
            this.mVectorState.mVPathRenderer.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.mVectorState.mAutoMirrored = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.mColorFilter = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            DrawableCompat.setTint(i, drawable);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.mTint != colorStateList) {
            vectorDrawableCompatState.mTint = colorStateList;
            this.mTintFilter = updateTintFilter(colorStateList, vectorDrawableCompatState.mTintMode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setTintMode(mode);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.mTintMode != mode) {
            vectorDrawableCompatState.mTintMode = mode;
            this.mTintFilter = updateTintFilter(vectorDrawableCompatState.mTint, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.mDelegateDrawable;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    public final PorterDuffColorFilter updateTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VectorDrawableDelegateState extends Drawable.ConstantState {
        public final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.mDelegateState = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable();
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(resources);
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(resources, theme);
            return vectorDrawableCompat;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class VPath extends VObject {
        public int mFillRule;
        public PathParser.PathDataNode[] mNodes;
        public String mPathName;

        public VPath() {
            this.mNodes = null;
            this.mFillRule = 0;
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public String getPathName() {
            return this.mPathName;
        }

        public void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
            PathParser.PathDataNode[] pathDataNodeArr2 = this.mNodes;
            boolean z = false;
            if (pathDataNodeArr2 != null && pathDataNodeArr != null && pathDataNodeArr2.length == pathDataNodeArr.length) {
                int i = 0;
                while (true) {
                    if (i >= pathDataNodeArr2.length) {
                        z = true;
                        break;
                    }
                    PathParser.PathDataNode pathDataNode = pathDataNodeArr2[i];
                    char c = pathDataNode.mType;
                    PathParser.PathDataNode pathDataNode2 = pathDataNodeArr[i];
                    if (c != pathDataNode2.mType || pathDataNode.mParams.length != pathDataNode2.mParams.length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (!z) {
                this.mNodes = PathParser.deepCopyNodes(pathDataNodeArr);
                return;
            }
            PathParser.PathDataNode[] pathDataNodeArr3 = this.mNodes;
            for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
                pathDataNodeArr3[i2].mType = pathDataNodeArr[i2].mType;
                int i3 = 0;
                while (true) {
                    float[] fArr = pathDataNodeArr[i2].mParams;
                    if (i3 < fArr.length) {
                        pathDataNodeArr3[i2].mParams[i3] = fArr[i3];
                        i3++;
                    }
                }
            }
        }

        public VPath(VPath vPath) {
            this.mNodes = null;
            this.mFillRule = 0;
            this.mPathName = vPath.mPathName;
            this.mNodes = PathParser.deepCopyNodes(vPath.mNodes);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        boolean z;
        VPathRenderer vPathRenderer;
        int i;
        int i2;
        int i3;
        int i4;
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        vectorDrawableCompatState.mVPathRenderer = new VPathRenderer();
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
        VectorDrawableCompatState vectorDrawableCompatState2 = this.mVectorState;
        VPathRenderer vPathRenderer2 = vectorDrawableCompatState2.mVPathRenderer;
        int i5 = !TypedArrayUtils.hasAttribute(xmlPullParser, "tintMode") ? -1 : obtainAttributes.getInt(6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        int i6 = 3;
        if (i5 == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (i5 != 5) {
            if (i5 != 9) {
                switch (i5) {
                    case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case 15:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        vectorDrawableCompatState2.mTintMode = mode;
        ColorStateList namedColorStateList = TypedArrayUtils.getNamedColorStateList(obtainAttributes, xmlPullParser, theme);
        if (namedColorStateList != null) {
            vectorDrawableCompatState2.mTint = namedColorStateList;
        }
        boolean z2 = vectorDrawableCompatState2.mAutoMirrored;
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "autoMirrored")) {
            z2 = obtainAttributes.getBoolean(5, z2);
        }
        vectorDrawableCompatState2.mAutoMirrored = z2;
        float f = vPathRenderer2.mViewportWidth;
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "viewportWidth")) {
            f = obtainAttributes.getFloat(7, f);
        }
        vPathRenderer2.mViewportWidth = f;
        float f2 = vPathRenderer2.mViewportHeight;
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "viewportHeight")) {
            f2 = obtainAttributes.getFloat(8, f2);
        }
        vPathRenderer2.mViewportHeight = f2;
        if (vPathRenderer2.mViewportWidth <= 0.0f) {
            throw new XmlPullParserException(obtainAttributes.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f2 > 0.0f) {
            vPathRenderer2.mBaseWidth = obtainAttributes.getDimension(3, vPathRenderer2.mBaseWidth);
            int i7 = 2;
            float dimension = obtainAttributes.getDimension(2, vPathRenderer2.mBaseHeight);
            vPathRenderer2.mBaseHeight = dimension;
            if (vPathRenderer2.mBaseWidth <= 0.0f) {
                throw new XmlPullParserException(obtainAttributes.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (dimension > 0.0f) {
                float alpha = vPathRenderer2.getAlpha();
                if (TypedArrayUtils.hasAttribute(xmlPullParser, "alpha")) {
                    alpha = obtainAttributes.getFloat(4, alpha);
                }
                vPathRenderer2.setAlpha(alpha);
                boolean z3 = false;
                String string = obtainAttributes.getString(0);
                if (string != null) {
                    vPathRenderer2.mRootName = string;
                    vPathRenderer2.mVGTargetsMap.put(string, vPathRenderer2);
                }
                obtainAttributes.recycle();
                vectorDrawableCompatState.mChangingConfigurations = getChangingConfigurations();
                int i8 = 1;
                vectorDrawableCompatState.mCacheDirty = true;
                VectorDrawableCompatState vectorDrawableCompatState3 = this.mVectorState;
                VPathRenderer vPathRenderer3 = vectorDrawableCompatState3.mVPathRenderer;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.push(vPathRenderer3.mRootGroup);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z4 = true;
                while (eventType != i8 && (xmlPullParser.getDepth() >= depth || eventType != i6)) {
                    if (eventType == i7) {
                        String name = xmlPullParser.getName();
                        VGroup vGroup = (VGroup) arrayDeque.peek();
                        if (vGroup != null) {
                            boolean equals = "path".equals(name);
                            i2 = depth;
                            ArrayMap arrayMap = vPathRenderer3.mVGTargetsMap;
                            if (equals) {
                                VFullPath vFullPath = new VFullPath();
                                vFullPath.mStrokeWidth = 0.0f;
                                vFullPath.mStrokeAlpha = 1.0f;
                                vFullPath.mFillAlpha = 1.0f;
                                vFullPath.mTrimPathStart = 0.0f;
                                vFullPath.mTrimPathEnd = 1.0f;
                                vFullPath.mTrimPathOffset = 0.0f;
                                Paint.Cap cap = Paint.Cap.BUTT;
                                vFullPath.mStrokeLineCap = cap;
                                Paint.Join join = Paint.Join.MITER;
                                vFullPath.mStrokeLineJoin = join;
                                vFullPath.mStrokeMiterlimit = 4.0f;
                                TypedArray obtainAttributes2 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
                                if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                                    vPathRenderer = vPathRenderer3;
                                    String string2 = obtainAttributes2.getString(0);
                                    if (string2 != null) {
                                        vFullPath.mPathName = string2;
                                    }
                                    String string3 = obtainAttributes2.getString(2);
                                    if (string3 != null) {
                                        vFullPath.mNodes = PathParser.createNodesFromPathData(string3);
                                    }
                                    vFullPath.mFillColor = TypedArrayUtils.getNamedComplexColor(obtainAttributes2, xmlPullParser, theme, "fillColor", 1);
                                    float f3 = vFullPath.mFillAlpha;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "fillAlpha")) {
                                        f3 = obtainAttributes2.getFloat(12, f3);
                                    }
                                    vFullPath.mFillAlpha = f3;
                                    int i9 = !TypedArrayUtils.hasAttribute(xmlPullParser, "strokeLineCap") ? -1 : obtainAttributes2.getInt(8, -1);
                                    Paint.Cap cap2 = vFullPath.mStrokeLineCap;
                                    if (i9 != 0) {
                                        if (i9 != 1) {
                                            cap = i9 != 2 ? cap2 : Paint.Cap.SQUARE;
                                        } else {
                                            cap = Paint.Cap.ROUND;
                                        }
                                    }
                                    vFullPath.mStrokeLineCap = cap;
                                    int i10 = !TypedArrayUtils.hasAttribute(xmlPullParser, "strokeLineJoin") ? -1 : obtainAttributes2.getInt(9, -1);
                                    Paint.Join join2 = vFullPath.mStrokeLineJoin;
                                    if (i10 == 0) {
                                        join2 = join;
                                    } else if (i10 == 1) {
                                        join2 = Paint.Join.ROUND;
                                    } else if (i10 == 2) {
                                        join2 = Paint.Join.BEVEL;
                                    }
                                    vFullPath.mStrokeLineJoin = join2;
                                    float f4 = vFullPath.mStrokeMiterlimit;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "strokeMiterLimit")) {
                                        f4 = obtainAttributes2.getFloat(10, f4);
                                    }
                                    vFullPath.mStrokeMiterlimit = f4;
                                    vFullPath.mStrokeColor = TypedArrayUtils.getNamedComplexColor(obtainAttributes2, xmlPullParser, theme, "strokeColor", 3);
                                    float f5 = vFullPath.mStrokeAlpha;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "strokeAlpha")) {
                                        f5 = obtainAttributes2.getFloat(11, f5);
                                    }
                                    vFullPath.mStrokeAlpha = f5;
                                    float f6 = vFullPath.mStrokeWidth;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "strokeWidth")) {
                                        f6 = obtainAttributes2.getFloat(4, f6);
                                    }
                                    vFullPath.mStrokeWidth = f6;
                                    float f7 = vFullPath.mTrimPathEnd;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "trimPathEnd")) {
                                        f7 = obtainAttributes2.getFloat(6, f7);
                                    }
                                    vFullPath.mTrimPathEnd = f7;
                                    float f8 = vFullPath.mTrimPathOffset;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "trimPathOffset")) {
                                        f8 = obtainAttributes2.getFloat(7, f8);
                                    }
                                    vFullPath.mTrimPathOffset = f8;
                                    float f9 = vFullPath.mTrimPathStart;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "trimPathStart")) {
                                        f9 = obtainAttributes2.getFloat(5, f9);
                                    }
                                    vFullPath.mTrimPathStart = f9;
                                    int i11 = vFullPath.mFillRule;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "fillType")) {
                                        i11 = obtainAttributes2.getInt(13, i11);
                                    }
                                    vFullPath.mFillRule = i11;
                                } else {
                                    vPathRenderer = vPathRenderer3;
                                }
                                obtainAttributes2.recycle();
                                vGroup.mChildren.add(vFullPath);
                                if (vFullPath.getPathName() != null) {
                                    arrayMap.put(vFullPath.getPathName(), vFullPath);
                                }
                                vectorDrawableCompatState3.mChangingConfigurations = vectorDrawableCompatState3.mChangingConfigurations;
                                z = false;
                                i4 = 1;
                                z4 = false;
                            } else {
                                vPathRenderer = vPathRenderer3;
                                if ("clip-path".equals(name)) {
                                    VClipPath vClipPath = new VClipPath();
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                                        TypedArray obtainAttributes3 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
                                        String string4 = obtainAttributes3.getString(0);
                                        if (string4 != null) {
                                            vClipPath.mPathName = string4;
                                        }
                                        String string5 = obtainAttributes3.getString(1);
                                        if (string5 != null) {
                                            vClipPath.mNodes = PathParser.createNodesFromPathData(string5);
                                        }
                                        vClipPath.mFillRule = !TypedArrayUtils.hasAttribute(xmlPullParser, "fillType") ? 0 : obtainAttributes3.getInt(2, 0);
                                        obtainAttributes3.recycle();
                                    }
                                    vGroup.mChildren.add(vClipPath);
                                    if (vClipPath.getPathName() != null) {
                                        arrayMap.put(vClipPath.getPathName(), vClipPath);
                                    }
                                    vectorDrawableCompatState3.mChangingConfigurations = vectorDrawableCompatState3.mChangingConfigurations;
                                } else if ("group".equals(name)) {
                                    VGroup vGroup2 = new VGroup();
                                    TypedArray obtainAttributes4 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
                                    float f10 = vGroup2.mRotate;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "rotation")) {
                                        f10 = obtainAttributes4.getFloat(5, f10);
                                    }
                                    vGroup2.mRotate = f10;
                                    i4 = 1;
                                    vGroup2.mPivotX = obtainAttributes4.getFloat(1, vGroup2.mPivotX);
                                    vGroup2.mPivotY = obtainAttributes4.getFloat(2, vGroup2.mPivotY);
                                    float f11 = vGroup2.mScaleX;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "scaleX")) {
                                        f11 = obtainAttributes4.getFloat(3, f11);
                                    }
                                    vGroup2.mScaleX = f11;
                                    float f12 = vGroup2.mScaleY;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "scaleY")) {
                                        f12 = obtainAttributes4.getFloat(4, f12);
                                    }
                                    vGroup2.mScaleY = f12;
                                    float f13 = vGroup2.mTranslateX;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "translateX")) {
                                        f13 = obtainAttributes4.getFloat(6, f13);
                                    }
                                    vGroup2.mTranslateX = f13;
                                    float f14 = vGroup2.mTranslateY;
                                    if (TypedArrayUtils.hasAttribute(xmlPullParser, "translateY")) {
                                        f14 = obtainAttributes4.getFloat(7, f14);
                                    }
                                    vGroup2.mTranslateY = f14;
                                    z = false;
                                    String string6 = obtainAttributes4.getString(0);
                                    if (string6 != null) {
                                        vGroup2.mGroupName = string6;
                                    }
                                    vGroup2.updateLocalMatrix();
                                    obtainAttributes4.recycle();
                                    vGroup.mChildren.add(vGroup2);
                                    arrayDeque.push(vGroup2);
                                    if (vGroup2.getGroupName() != null) {
                                        arrayMap.put(vGroup2.getGroupName(), vGroup2);
                                    }
                                    vectorDrawableCompatState3.mChangingConfigurations = vectorDrawableCompatState3.mChangingConfigurations;
                                }
                                z = false;
                            }
                            i3 = i4;
                            i = 3;
                        } else {
                            z = z3;
                            vPathRenderer = vPathRenderer3;
                            i2 = depth;
                        }
                        i4 = 1;
                        i3 = i4;
                        i = 3;
                    } else {
                        z = z3;
                        vPathRenderer = vPathRenderer3;
                        i = i6;
                        i2 = depth;
                        i3 = 1;
                        if (eventType == i && "group".equals(xmlPullParser.getName())) {
                            arrayDeque.pop();
                        }
                    }
                    eventType = xmlPullParser.next();
                    i6 = i;
                    z3 = z;
                    i8 = i3;
                    depth = i2;
                    vPathRenderer3 = vPathRenderer;
                    i7 = 2;
                }
                if (!z4) {
                    this.mTintFilter = updateTintFilter(vectorDrawableCompatState.mTint, vectorDrawableCompatState.mTintMode);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            }
            throw new XmlPullParserException(obtainAttributes.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(obtainAttributes.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VGroup extends VObject {
        public final ArrayList mChildren;
        public String mGroupName;
        public final Matrix mLocalMatrix;
        public float mPivotX;
        public float mPivotY;
        public float mRotate;
        public float mScaleX;
        public float mScaleY;
        public final Matrix mStackedMatrix;
        public float mTranslateX;
        public float mTranslateY;

        public VGroup() {
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            this.mLocalMatrix = new Matrix();
            this.mGroupName = null;
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.mLocalMatrix;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.mRotate;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public final boolean isStateful() {
            for (int i = 0; i < this.mChildren.size(); i++) {
                if (((VObject) this.mChildren.get(i)).isStateful()) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public final boolean onStateChanged(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.mChildren.size(); i++) {
                z |= ((VObject) this.mChildren.get(i)).onStateChanged(iArr);
            }
            return z;
        }

        public void setPivotX(float f) {
            if (f != this.mPivotX) {
                this.mPivotX = f;
                updateLocalMatrix();
            }
        }

        public void setPivotY(float f) {
            if (f != this.mPivotY) {
                this.mPivotY = f;
                updateLocalMatrix();
            }
        }

        public void setRotation(float f) {
            if (f != this.mRotate) {
                this.mRotate = f;
                updateLocalMatrix();
            }
        }

        public void setScaleX(float f) {
            if (f != this.mScaleX) {
                this.mScaleX = f;
                updateLocalMatrix();
            }
        }

        public void setScaleY(float f) {
            if (f != this.mScaleY) {
                this.mScaleY = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.mTranslateX) {
                this.mTranslateX = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.mTranslateY) {
                this.mTranslateY = f;
                updateLocalMatrix();
            }
        }

        public final void updateLocalMatrix() {
            this.mLocalMatrix.reset();
            this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
            this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
            this.mLocalMatrix.postRotate(this.mRotate, 0.0f, 0.0f);
            this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public VGroup(VGroup vGroup, ArrayMap arrayMap) {
            VClipPath vClipPath;
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            Matrix matrix = new Matrix();
            this.mLocalMatrix = matrix;
            this.mGroupName = null;
            this.mRotate = vGroup.mRotate;
            this.mPivotX = vGroup.mPivotX;
            this.mPivotY = vGroup.mPivotY;
            this.mScaleX = vGroup.mScaleX;
            this.mScaleY = vGroup.mScaleY;
            this.mTranslateX = vGroup.mTranslateX;
            this.mTranslateY = vGroup.mTranslateY;
            String str = vGroup.mGroupName;
            this.mGroupName = str;
            if (str != null) {
                arrayMap.put(str, this);
            }
            matrix.set(vGroup.mLocalMatrix);
            ArrayList arrayList = vGroup.mChildren;
            for (int i = 0; i < arrayList.size(); i++) {
                Object obj = arrayList.get(i);
                if (obj instanceof VGroup) {
                    this.mChildren.add(new VGroup((VGroup) obj, arrayMap));
                } else {
                    if (obj instanceof VFullPath) {
                        VFullPath vFullPath = (VFullPath) obj;
                        VFullPath vFullPath2 = new VFullPath(vFullPath);
                        vFullPath2.mStrokeWidth = 0.0f;
                        vFullPath2.mStrokeAlpha = 1.0f;
                        vFullPath2.mFillAlpha = 1.0f;
                        vFullPath2.mTrimPathStart = 0.0f;
                        vFullPath2.mTrimPathEnd = 1.0f;
                        vFullPath2.mTrimPathOffset = 0.0f;
                        vFullPath2.mStrokeLineCap = Paint.Cap.BUTT;
                        vFullPath2.mStrokeLineJoin = Paint.Join.MITER;
                        vFullPath2.mStrokeMiterlimit = 4.0f;
                        vFullPath2.mStrokeColor = vFullPath.mStrokeColor;
                        vFullPath2.mStrokeWidth = vFullPath.mStrokeWidth;
                        vFullPath2.mStrokeAlpha = vFullPath.mStrokeAlpha;
                        vFullPath2.mFillColor = vFullPath.mFillColor;
                        vFullPath2.mFillRule = vFullPath.mFillRule;
                        vFullPath2.mFillAlpha = vFullPath.mFillAlpha;
                        vFullPath2.mTrimPathStart = vFullPath.mTrimPathStart;
                        vFullPath2.mTrimPathEnd = vFullPath.mTrimPathEnd;
                        vFullPath2.mTrimPathOffset = vFullPath.mTrimPathOffset;
                        vFullPath2.mStrokeLineCap = vFullPath.mStrokeLineCap;
                        vFullPath2.mStrokeLineJoin = vFullPath.mStrokeLineJoin;
                        vFullPath2.mStrokeMiterlimit = vFullPath.mStrokeMiterlimit;
                        vClipPath = vFullPath2;
                    } else if (obj instanceof VClipPath) {
                        vClipPath = new VClipPath((VClipPath) obj);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.mChildren.add(vClipPath);
                    Object obj2 = vClipPath.mPathName;
                    if (obj2 != null) {
                        arrayMap.put(obj2, vClipPath);
                    }
                }
            }
        }
    }

    public VectorDrawableCompat(VectorDrawableCompatState vectorDrawableCompatState) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = vectorDrawableCompatState;
        this.mTintFilter = updateTintFilter(vectorDrawableCompatState.mTint, vectorDrawableCompatState.mTintMode);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VPathRenderer {
        public static final Matrix IDENTITY_MATRIX = new Matrix();
        public float mBaseHeight;
        public float mBaseWidth;
        public Paint mFillPaint;
        public final Matrix mFinalPathMatrix;
        public Boolean mIsStateful;
        public final Path mPath;
        public PathMeasure mPathMeasure;
        public final Path mRenderPath;
        public int mRootAlpha;
        public final VGroup mRootGroup;
        public String mRootName;
        public Paint mStrokePaint;
        public final ArrayMap mVGTargetsMap;
        public float mViewportHeight;
        public float mViewportWidth;

        public VPathRenderer() {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 255;
            this.mRootName = null;
            this.mIsStateful = null;
            this.mVGTargetsMap = new ArrayMap(0);
            this.mRootGroup = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x00f8, code lost:
        
            if (r0.mTrimPathEnd != 1.0f) goto L34;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v0 */
        /* JADX WARN: Type inference failed for: r10v1, types: [boolean] */
        /* JADX WARN: Type inference failed for: r10v20 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void drawGroupTree(androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VGroup r18, android.graphics.Matrix r19, android.graphics.Canvas r20, int r21, int r22) {
            /*
                Method dump skipped, instructions count: 572
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VPathRenderer.drawGroupTree(androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VGroup, android.graphics.Matrix, android.graphics.Canvas, int, int):void");
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.mRootAlpha;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.mRootAlpha = i;
        }

        public VPathRenderer(VPathRenderer vPathRenderer) {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 255;
            this.mRootName = null;
            this.mIsStateful = null;
            ArrayMap arrayMap = new ArrayMap(0);
            this.mVGTargetsMap = arrayMap;
            this.mRootGroup = new VGroup(vPathRenderer.mRootGroup, arrayMap);
            this.mPath = new Path(vPathRenderer.mPath);
            this.mRenderPath = new Path(vPathRenderer.mRenderPath);
            this.mBaseWidth = vPathRenderer.mBaseWidth;
            this.mBaseHeight = vPathRenderer.mBaseHeight;
            this.mViewportWidth = vPathRenderer.mViewportWidth;
            this.mViewportHeight = vPathRenderer.mViewportHeight;
            this.mRootAlpha = vPathRenderer.mRootAlpha;
            this.mRootName = vPathRenderer.mRootName;
            String str = vPathRenderer.mRootName;
            if (str != null) {
                arrayMap.put(str, this);
            }
            this.mIsStateful = vPathRenderer.mIsStateful;
        }
    }
}
