package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator$WavePoint;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R$styleable;
import com.android.app.viewcapture.data.ViewNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyCycle extends Key {
    public int mCurveFit = 0;
    public int mWaveShape = -1;
    public String mCustomWaveShape = null;
    public float mWavePeriod = Float.NaN;
    public float mWaveOffset = 0.0f;
    public float mWavePhase = 0.0f;
    public float mProgress = Float.NaN;
    public int mWaveVariesBy = -1;
    public float mAlpha = Float.NaN;
    public float mElevation = Float.NaN;
    public float mRotation = Float.NaN;
    public float mTransitionPathRotate = Float.NaN;
    public float mRotationX = Float.NaN;
    public float mRotationY = Float.NaN;
    public float mScaleX = Float.NaN;
    public float mScaleY = Float.NaN;
    public float mTranslationX = Float.NaN;
    public float mTranslationY = Float.NaN;
    public float mTranslationZ = Float.NaN;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Loader {
        public static final SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sAttrMap = sparseIntArray;
            sparseIntArray.append(13, 1);
            sparseIntArray.append(11, 2);
            sparseIntArray.append(14, 3);
            sparseIntArray.append(10, 4);
            sparseIntArray.append(19, 5);
            sparseIntArray.append(17, 6);
            sparseIntArray.append(16, 7);
            sparseIntArray.append(20, 8);
            sparseIntArray.append(0, 9);
            sparseIntArray.append(9, 10);
            sparseIntArray.append(5, 11);
            sparseIntArray.append(6, 12);
            sparseIntArray.append(7, 13);
            sparseIntArray.append(15, 14);
            sparseIntArray.append(3, 15);
            sparseIntArray.append(4, 16);
            sparseIntArray.append(1, 17);
            sparseIntArray.append(2, 18);
            sparseIntArray.append(8, 19);
            sparseIntArray.append(12, 20);
            sparseIntArray.append(18, 21);
        }
    }

    public KeyCycle() {
        this.mCustomConstraints = new HashMap();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void addCycleValues(HashMap hashMap) {
        char c;
        float f;
        ViewOscillator viewOscillator;
        ViewOscillator viewOscillator2;
        int i = 7;
        for (String str : hashMap.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.mCustomConstraints.get(str.substring(i));
                if (constraintAttribute != null) {
                    if (constraintAttribute.mType == ConstraintAttribute.AttributeType.FLOAT_TYPE && (viewOscillator2 = (ViewOscillator) hashMap.get(str)) != null) {
                        int i2 = this.mFramePosition;
                        int i3 = this.mWaveShape;
                        String str2 = this.mCustomWaveShape;
                        int i4 = this.mWaveVariesBy;
                        viewOscillator2.mWavePoints.add(new KeyCycleOscillator$WavePoint(this.mWavePeriod, this.mWaveOffset, this.mWavePhase, constraintAttribute.getValueToInterpolate(), i2));
                        if (i4 != -1) {
                            viewOscillator2.mVariesBy = i4;
                        }
                        viewOscillator2.mWaveShape = i3;
                        viewOscillator2.setCustom(constraintAttribute);
                        viewOscillator2.mWaveString = str2;
                    }
                }
            } else {
                switch (str.hashCode()) {
                    case -1249320806:
                        if (str.equals("rotationX")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1249320805:
                        if (str.equals("rotationY")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497657:
                        if (str.equals("translationX")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497656:
                        if (str.equals("translationY")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497655:
                        if (str.equals("translationZ")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1001078227:
                        if (str.equals("progress")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189618:
                        if (str.equals("scaleX")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189617:
                        if (str.equals("scaleY")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case -40300674:
                        if (str.equals("rotation")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case -4379043:
                        if (str.equals("elevation")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 37232917:
                        if (str.equals("transitionPathRotate")) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 92909918:
                        if (str.equals("alpha")) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    case 156108012:
                        if (str.equals("waveOffset")) {
                            c = '\f';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1530034690:
                        if (str.equals("wavePhase")) {
                            c = '\r';
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        f = this.mRotationX;
                        break;
                    case 1:
                        f = this.mRotationY;
                        break;
                    case 2:
                        f = this.mTranslationX;
                        break;
                    case 3:
                        f = this.mTranslationY;
                        break;
                    case 4:
                        f = this.mTranslationZ;
                        break;
                    case 5:
                        f = this.mProgress;
                        break;
                    case 6:
                        f = this.mScaleX;
                        break;
                    case 7:
                        f = this.mScaleY;
                        break;
                    case '\b':
                        f = this.mRotation;
                        break;
                    case '\t':
                        f = this.mElevation;
                        break;
                    case '\n':
                        f = this.mTransitionPathRotate;
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        f = this.mAlpha;
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        f = this.mWaveOffset;
                        break;
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        f = this.mWavePhase;
                        break;
                    default:
                        str.startsWith("CUSTOM");
                        f = Float.NaN;
                        break;
                }
                float f2 = f;
                if (!Float.isNaN(f2) && (viewOscillator = (ViewOscillator) hashMap.get(str)) != null) {
                    int i5 = this.mFramePosition;
                    int i6 = this.mWaveShape;
                    String str3 = this.mCustomWaveShape;
                    int i7 = this.mWaveVariesBy;
                    viewOscillator.mWavePoints.add(new KeyCycleOscillator$WavePoint(this.mWavePeriod, this.mWaveOffset, this.mWavePhase, f2, i5));
                    if (i7 != -1) {
                        viewOscillator.mVariesBy = i7;
                    }
                    viewOscillator.mWaveShape = i6;
                    viewOscillator.mWaveString = str3;
                }
            }
            i = 7;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void addValues(HashMap hashMap) {
        throw null;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void getAttributeNames(HashSet hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (this.mCustomConstraints.size() > 0) {
            Iterator it = this.mCustomConstraints.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + ((String) it.next()));
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void load(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyCycle);
        SparseIntArray sparseIntArray = Loader.sAttrMap;
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            SparseIntArray sparseIntArray2 = Loader.sAttrMap;
            switch (sparseIntArray2.get(index)) {
                case 1:
                    if (MotionLayout.IS_IN_EDIT_MODE) {
                        int resourceId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                        this.mTargetId = resourceId;
                        if (resourceId == -1) {
                            this.mTargetString = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            break;
                        }
                    } else if (obtainStyledAttributes.peekValue(index).type == 3) {
                        this.mTargetString = obtainStyledAttributes.getString(index);
                        break;
                    } else {
                        this.mTargetId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                        break;
                    }
                case 2:
                    this.mFramePosition = obtainStyledAttributes.getInt(index, this.mFramePosition);
                    break;
                case 3:
                    obtainStyledAttributes.getString(index);
                    break;
                case 4:
                    this.mCurveFit = obtainStyledAttributes.getInteger(index, this.mCurveFit);
                    break;
                case 5:
                    if (obtainStyledAttributes.peekValue(index).type == 3) {
                        this.mCustomWaveShape = obtainStyledAttributes.getString(index);
                        this.mWaveShape = 7;
                        break;
                    } else {
                        this.mWaveShape = obtainStyledAttributes.getInt(index, this.mWaveShape);
                        break;
                    }
                case 6:
                    this.mWavePeriod = obtainStyledAttributes.getFloat(index, this.mWavePeriod);
                    break;
                case 7:
                    if (obtainStyledAttributes.peekValue(index).type == 5) {
                        this.mWaveOffset = obtainStyledAttributes.getDimension(index, this.mWaveOffset);
                        break;
                    } else {
                        this.mWaveOffset = obtainStyledAttributes.getFloat(index, this.mWaveOffset);
                        break;
                    }
                case 8:
                    this.mWaveVariesBy = obtainStyledAttributes.getInt(index, this.mWaveVariesBy);
                    break;
                case 9:
                    this.mAlpha = obtainStyledAttributes.getFloat(index, this.mAlpha);
                    break;
                case 10:
                    this.mElevation = obtainStyledAttributes.getDimension(index, this.mElevation);
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    this.mRotation = obtainStyledAttributes.getFloat(index, this.mRotation);
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    this.mRotationX = obtainStyledAttributes.getFloat(index, this.mRotationX);
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    this.mRotationY = obtainStyledAttributes.getFloat(index, this.mRotationY);
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    this.mTransitionPathRotate = obtainStyledAttributes.getFloat(index, this.mTransitionPathRotate);
                    break;
                case 15:
                    this.mScaleX = obtainStyledAttributes.getFloat(index, this.mScaleX);
                    break;
                case 16:
                    this.mScaleY = obtainStyledAttributes.getFloat(index, this.mScaleY);
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    this.mTranslationX = obtainStyledAttributes.getDimension(index, this.mTranslationX);
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    this.mTranslationY = obtainStyledAttributes.getDimension(index, this.mTranslationY);
                    break;
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    this.mTranslationZ = obtainStyledAttributes.getDimension(index, this.mTranslationZ);
                    break;
                case 20:
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                    break;
                case 21:
                    this.mWavePhase = obtainStyledAttributes.getFloat(index, this.mWavePhase) / 360.0f;
                    break;
                default:
                    Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
                    break;
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final Key m705clone() {
        KeyCycle keyCycle = new KeyCycle();
        super.copy(this);
        keyCycle.mCurveFit = this.mCurveFit;
        keyCycle.mWaveShape = this.mWaveShape;
        keyCycle.mCustomWaveShape = this.mCustomWaveShape;
        keyCycle.mWavePeriod = this.mWavePeriod;
        keyCycle.mWaveOffset = this.mWaveOffset;
        keyCycle.mWavePhase = this.mWavePhase;
        keyCycle.mProgress = this.mProgress;
        keyCycle.mWaveVariesBy = this.mWaveVariesBy;
        keyCycle.mAlpha = this.mAlpha;
        keyCycle.mElevation = this.mElevation;
        keyCycle.mRotation = this.mRotation;
        keyCycle.mTransitionPathRotate = this.mTransitionPathRotate;
        keyCycle.mRotationX = this.mRotationX;
        keyCycle.mRotationY = this.mRotationY;
        keyCycle.mScaleX = this.mScaleX;
        keyCycle.mScaleY = this.mScaleY;
        keyCycle.mTranslationX = this.mTranslationX;
        keyCycle.mTranslationY = this.mTranslationY;
        keyCycle.mTranslationZ = this.mTranslationZ;
        return keyCycle;
    }
}
