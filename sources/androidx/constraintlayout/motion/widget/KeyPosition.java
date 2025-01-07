package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.R$styleable;
import com.android.app.viewcapture.data.ViewNode;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyPosition extends Key {
    public int mCurveFit = -1;
    public String mTransitionEasing = null;
    public int mPathMotionArc = -1;
    public int mDrawPath = 0;
    public float mPercentWidth = Float.NaN;
    public float mPercentHeight = Float.NaN;
    public float mPercentX = Float.NaN;
    public float mPercentY = Float.NaN;
    public int mPositionType = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Loader {
        public static final SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sAttrMap = sparseIntArray;
            sparseIntArray.append(4, 1);
            sparseIntArray.append(2, 2);
            sparseIntArray.append(11, 3);
            sparseIntArray.append(0, 4);
            sparseIntArray.append(1, 5);
            sparseIntArray.append(8, 6);
            sparseIntArray.append(9, 7);
            sparseIntArray.append(3, 9);
            sparseIntArray.append(10, 8);
            sparseIntArray.append(7, 11);
            sparseIntArray.append(6, 12);
            sparseIntArray.append(5, 10);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void addValues(HashMap hashMap) {
        throw null;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void load(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyPosition);
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
                    if (obtainStyledAttributes.peekValue(index).type == 3) {
                        this.mTransitionEasing = obtainStyledAttributes.getString(index);
                        break;
                    } else {
                        this.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                        break;
                    }
                case 4:
                    this.mCurveFit = obtainStyledAttributes.getInteger(index, this.mCurveFit);
                    break;
                case 5:
                    this.mDrawPath = obtainStyledAttributes.getInt(index, this.mDrawPath);
                    break;
                case 6:
                    this.mPercentX = obtainStyledAttributes.getFloat(index, this.mPercentX);
                    break;
                case 7:
                    this.mPercentY = obtainStyledAttributes.getFloat(index, this.mPercentY);
                    break;
                case 8:
                    float f = obtainStyledAttributes.getFloat(index, this.mPercentHeight);
                    this.mPercentWidth = f;
                    this.mPercentHeight = f;
                    break;
                case 9:
                    this.mPositionType = obtainStyledAttributes.getInt(index, this.mPositionType);
                    break;
                case 10:
                    this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    this.mPercentWidth = obtainStyledAttributes.getFloat(index, this.mPercentWidth);
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    this.mPercentHeight = obtainStyledAttributes.getFloat(index, this.mPercentHeight);
                    break;
                default:
                    Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
                    break;
            }
        }
        if (this.mFramePosition == -1) {
            Log.e("KeyPosition", "no frame position");
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final Key m706clone() {
        KeyPosition keyPosition = new KeyPosition();
        super.copy(this);
        keyPosition.mTransitionEasing = this.mTransitionEasing;
        keyPosition.mPathMotionArc = this.mPathMotionArc;
        keyPosition.mDrawPath = this.mDrawPath;
        keyPosition.mPercentWidth = this.mPercentWidth;
        keyPosition.mPercentHeight = Float.NaN;
        keyPosition.mPercentX = this.mPercentX;
        keyPosition.mPercentY = this.mPercentY;
        return keyPosition;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void getAttributeNames(HashSet hashSet) {
    }
}
