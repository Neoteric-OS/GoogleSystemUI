package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyFrames {
    public static final HashMap sKeyMakers;
    public HashMap mFramesMap = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        sKeyMakers = hashMap;
        try {
            Class[] clsArr = new Class[0];
            hashMap.put("KeyAttribute", KeyAttributes.class.getConstructor(null));
            Class[] clsArr2 = new Class[0];
            hashMap.put("KeyPosition", KeyPosition.class.getConstructor(null));
            Class[] clsArr3 = new Class[0];
            hashMap.put("KeyCycle", KeyCycle.class.getConstructor(null));
            Class[] clsArr4 = new Class[0];
            hashMap.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(null));
            Class[] clsArr5 = new Class[0];
            hashMap.put("KeyTrigger", KeyTrigger.class.getConstructor(null));
        } catch (NoSuchMethodException e) {
            Log.e("KeyFrames", "unable to load", e);
        }
    }

    public KeyFrames(Context context, XmlPullParser xmlPullParser) {
        HashMap hashMap;
        HashMap hashMap2;
        char c;
        Key keyAttributes;
        try {
            int eventType = xmlPullParser.getEventType();
            Key key = null;
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (sKeyMakers.containsKey(name)) {
                        switch (name.hashCode()) {
                            case -300573030:
                                if (name.equals("KeyTimeCycle")) {
                                    c = 3;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -298435811:
                                if (name.equals("KeyAttribute")) {
                                    c = 0;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 540053991:
                                if (name.equals("KeyCycle")) {
                                    c = 2;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1153397896:
                                if (name.equals("KeyPosition")) {
                                    c = 1;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1308496505:
                                if (name.equals("KeyTrigger")) {
                                    c = 4;
                                    break;
                                }
                                c = 65535;
                                break;
                            default:
                                c = 65535;
                                break;
                        }
                        if (c == 0) {
                            keyAttributes = new KeyAttributes();
                        } else if (c == 1) {
                            keyAttributes = new KeyPosition();
                        } else if (c == 2) {
                            keyAttributes = new KeyCycle();
                        } else if (c == 3) {
                            keyAttributes = new KeyTimeCycle();
                        } else {
                            if (c != 4) {
                                throw new NullPointerException("Key " + name + " not found");
                            }
                            keyAttributes = new KeyTrigger();
                        }
                        keyAttributes.load(context, Xml.asAttributeSet(xmlPullParser));
                        addKey(keyAttributes);
                        key = keyAttributes;
                    } else if (name.equalsIgnoreCase("CustomAttribute")) {
                        if (key != null && (hashMap2 = key.mCustomConstraints) != null) {
                            ConstraintAttribute.parse(context, xmlPullParser, hashMap2);
                        }
                    } else if (name.equalsIgnoreCase("CustomMethod") && key != null && (hashMap = key.mCustomConstraints) != null) {
                        ConstraintAttribute.parse(context, xmlPullParser, hashMap);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e) {
            Log.e("KeyFrames", "Error parsing XML resource", e);
        } catch (XmlPullParserException e2) {
            Log.e("KeyFrames", "Error parsing XML resource", e2);
        }
    }

    public final void addFrames(MotionController motionController) {
        ArrayList arrayList = (ArrayList) this.mFramesMap.get(Integer.valueOf(motionController.mId));
        if (arrayList != null) {
            motionController.mKeyList.addAll(arrayList);
        }
        ArrayList arrayList2 = (ArrayList) this.mFramesMap.get(-1);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                Key key = (Key) it.next();
                String str = ((ConstraintLayout.LayoutParams) motionController.mView.getLayoutParams()).constraintTag;
                String str2 = key.mTargetString;
                if ((str2 == null || str == null) ? false : str.matches(str2)) {
                    motionController.mKeyList.add(key);
                }
            }
        }
    }

    public final void addKey(Key key) {
        if (!this.mFramesMap.containsKey(Integer.valueOf(key.mTargetId))) {
            this.mFramesMap.put(Integer.valueOf(key.mTargetId), new ArrayList());
        }
        ArrayList arrayList = (ArrayList) this.mFramesMap.get(Integer.valueOf(key.mTargetId));
        if (arrayList != null) {
            arrayList.add(key);
        }
    }
}
