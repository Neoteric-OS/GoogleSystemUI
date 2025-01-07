package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R$styleable;
import com.android.app.viewcapture.data.ViewNode;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyTrigger extends Key {
    public float mFireLastPos;
    public float mTriggerSlack = 0.1f;
    public int mViewTransitionOnNegativeCross = -1;
    public int mViewTransitionOnPositiveCross = -1;
    public int mViewTransitionOnCross = -1;
    public RectF mCollisionRect = new RectF();
    public RectF mTargetRect = new RectF();
    public HashMap mMethodHashMap = new HashMap();
    public String mCross = null;
    public int mTriggerReceiver = -1;
    public String mNegativeCross = null;
    public String mPositiveCross = null;
    public int mTriggerID = -1;
    public int mTriggerCollisionId = -1;
    public View mTriggerCollisionView = null;
    public boolean mFireCrossReset = true;
    public boolean mFireNegativeReset = true;
    public boolean mFirePositiveReset = true;
    public float mFireThreshold = Float.NaN;
    public boolean mPostLayout = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Loader {
        public static final SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sAttrMap = sparseIntArray;
            sparseIntArray.append(0, 8);
            sparseIntArray.append(4, 4);
            sparseIntArray.append(5, 1);
            sparseIntArray.append(6, 2);
            sparseIntArray.append(1, 7);
            sparseIntArray.append(7, 6);
            sparseIntArray.append(9, 5);
            sparseIntArray.append(3, 9);
            sparseIntArray.append(2, 10);
            sparseIntArray.append(8, 11);
            sparseIntArray.append(10, 12);
            sparseIntArray.append(11, 13);
            sparseIntArray.append(12, 14);
        }
    }

    public KeyTrigger() {
        this.mCustomConstraints = new HashMap();
    }

    public static void setUpRect(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void addValues(HashMap hashMap) {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void conditionallyFire(android.view.View r11, float r12) {
        /*
            Method dump skipped, instructions count: 343
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTrigger.conditionallyFire(android.view.View, float):void");
    }

    public final void fire(View view, String str) {
        Method method;
        if (str == null) {
            return;
        }
        if (!str.startsWith(".")) {
            if (this.mMethodHashMap.containsKey(str)) {
                method = (Method) this.mMethodHashMap.get(str);
                if (method == null) {
                    return;
                }
            } else {
                method = null;
            }
            if (method == null) {
                try {
                    Class[] clsArr = new Class[0];
                    method = view.getClass().getMethod(str, null);
                    this.mMethodHashMap.put(str, method);
                } catch (NoSuchMethodException unused) {
                    this.mMethodHashMap.put(str, null);
                    Log.e("KeyTrigger", "Could not find method \"" + str + "\"on class " + view.getClass().getSimpleName() + " " + Debug.getName(view));
                    return;
                }
            }
            try {
                method.invoke(view, null);
                return;
            } catch (Exception unused2) {
                Log.e("KeyTrigger", "Exception in call \"" + this.mCross + "\"on class " + view.getClass().getSimpleName() + " " + Debug.getName(view));
                return;
            }
        }
        boolean z = str.length() == 1;
        if (!z) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.mCustomConstraints.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z || lowerCase.matches(str)) {
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.mCustomConstraints.get(str2);
                if (constraintAttribute != null) {
                    Class<?> cls = view.getClass();
                    boolean z2 = constraintAttribute.mMethod;
                    String str3 = constraintAttribute.mName;
                    String m = !z2 ? AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("set", str3) : str3;
                    try {
                        switch (constraintAttribute.mType.ordinal()) {
                            case 0:
                            case 7:
                                cls.getMethod(m, Integer.TYPE).invoke(view, Integer.valueOf(constraintAttribute.mIntegerValue));
                                break;
                            case 1:
                                cls.getMethod(m, Float.TYPE).invoke(view, Float.valueOf(constraintAttribute.mFloatValue));
                                break;
                            case 2:
                                cls.getMethod(m, Integer.TYPE).invoke(view, Integer.valueOf(constraintAttribute.mColorValue));
                                break;
                            case 3:
                                Method method2 = cls.getMethod(m, Drawable.class);
                                ColorDrawable colorDrawable = new ColorDrawable();
                                colorDrawable.setColor(constraintAttribute.mColorValue);
                                method2.invoke(view, colorDrawable);
                                break;
                            case 4:
                                cls.getMethod(m, CharSequence.class).invoke(view, constraintAttribute.mStringValue);
                                break;
                            case 5:
                                cls.getMethod(m, Boolean.TYPE).invoke(view, Boolean.valueOf(constraintAttribute.mBooleanValue));
                                break;
                            case 6:
                                cls.getMethod(m, Float.TYPE).invoke(view, Float.valueOf(constraintAttribute.mFloatValue));
                                break;
                        }
                    } catch (IllegalAccessException e) {
                        StringBuilder m2 = ActivityResultRegistry$$ExternalSyntheticOutline0.m(" Custom Attribute \"", str3, "\" not found on ");
                        m2.append(cls.getName());
                        Log.e("TransitionLayout", m2.toString(), e);
                    } catch (NoSuchMethodException e2) {
                        Log.e("TransitionLayout", cls.getName() + " must have a method " + m, e2);
                    } catch (InvocationTargetException e3) {
                        StringBuilder m3 = ActivityResultRegistry$$ExternalSyntheticOutline0.m(" Custom Attribute \"", str3, "\" not found on ");
                        m3.append(cls.getName());
                        Log.e("TransitionLayout", m3.toString(), e3);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void load(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyTrigger);
        SparseIntArray sparseIntArray = Loader.sAttrMap;
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            SparseIntArray sparseIntArray2 = Loader.sAttrMap;
            switch (sparseIntArray2.get(index)) {
                case 1:
                    this.mNegativeCross = obtainStyledAttributes.getString(index);
                    break;
                case 2:
                    this.mPositiveCross = obtainStyledAttributes.getString(index);
                    break;
                case 3:
                default:
                    Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
                    break;
                case 4:
                    this.mCross = obtainStyledAttributes.getString(index);
                    break;
                case 5:
                    this.mTriggerSlack = obtainStyledAttributes.getFloat(index, this.mTriggerSlack);
                    break;
                case 6:
                    this.mTriggerID = obtainStyledAttributes.getResourceId(index, this.mTriggerID);
                    break;
                case 7:
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
                case 8:
                    int integer = obtainStyledAttributes.getInteger(index, this.mFramePosition);
                    this.mFramePosition = integer;
                    this.mFireThreshold = (integer + 0.5f) / 100.0f;
                    break;
                case 9:
                    this.mTriggerCollisionId = obtainStyledAttributes.getResourceId(index, this.mTriggerCollisionId);
                    break;
                case 10:
                    this.mPostLayout = obtainStyledAttributes.getBoolean(index, this.mPostLayout);
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    this.mTriggerReceiver = obtainStyledAttributes.getResourceId(index, this.mTriggerReceiver);
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    this.mViewTransitionOnCross = obtainStyledAttributes.getResourceId(index, this.mViewTransitionOnCross);
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    this.mViewTransitionOnNegativeCross = obtainStyledAttributes.getResourceId(index, this.mViewTransitionOnNegativeCross);
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    this.mViewTransitionOnPositiveCross = obtainStyledAttributes.getResourceId(index, this.mViewTransitionOnPositiveCross);
                    break;
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final Key m708clone() {
        KeyTrigger keyTrigger = new KeyTrigger();
        super.copy(this);
        keyTrigger.mCross = this.mCross;
        keyTrigger.mTriggerReceiver = this.mTriggerReceiver;
        keyTrigger.mNegativeCross = this.mNegativeCross;
        keyTrigger.mPositiveCross = this.mPositiveCross;
        keyTrigger.mTriggerID = this.mTriggerID;
        keyTrigger.mTriggerCollisionId = this.mTriggerCollisionId;
        keyTrigger.mTriggerCollisionView = this.mTriggerCollisionView;
        keyTrigger.mTriggerSlack = this.mTriggerSlack;
        keyTrigger.mFireCrossReset = this.mFireCrossReset;
        keyTrigger.mFireNegativeReset = this.mFireNegativeReset;
        keyTrigger.mFirePositiveReset = this.mFirePositiveReset;
        keyTrigger.mFireThreshold = this.mFireThreshold;
        keyTrigger.mFireLastPos = this.mFireLastPos;
        keyTrigger.mPostLayout = this.mPostLayout;
        keyTrigger.mCollisionRect = this.mCollisionRect;
        keyTrigger.mTargetRect = this.mTargetRect;
        keyTrigger.mMethodHashMap = this.mMethodHashMap;
        return keyTrigger;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public final void getAttributeNames(HashSet hashSet) {
    }
}
