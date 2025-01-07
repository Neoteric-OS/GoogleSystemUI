package androidx.compose.ui.platform;

import android.content.ClipData;
import android.content.Context;
import android.text.Annotation;
import android.text.Spanned;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidClipboardManager implements ClipboardManager {
    public final android.content.ClipboardManager clipboardManager;

    public AndroidClipboardManager(Context context) {
        this.clipboardManager = (android.content.ClipboardManager) context.getSystemService("clipboard");
    }

    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.ui.graphics.Shadow, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontSynthesis, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.style.BaselineShift, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextGeometricTransform, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v59 */
    public final AnnotatedString getText() {
        CharSequence charSequence;
        Spanned spanned;
        boolean z;
        int i;
        byte b = 2;
        byte b2 = 1;
        ClipData primaryClip = this.clipboardManager.getPrimaryClip();
        ?? r3 = 0;
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        boolean z2 = false;
        ClipData.Item itemAt = primaryClip.getItemAt(0);
        CharSequence text = itemAt != null ? itemAt.getText() : null;
        if (text == null) {
            return null;
        }
        if (!(text instanceof Spanned)) {
            return new AnnotatedString(text.toString());
        }
        Spanned spanned2 = (Spanned) text;
        Annotation[] annotationArr = (Annotation[]) spanned2.getSpans(0, text.length(), Annotation.class);
        ArrayList arrayList = new ArrayList();
        int length = annotationArr.length - 1;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                Annotation annotation = annotationArr[i2];
                if (Intrinsics.areEqual(annotation.getKey(), "androidx.compose.text.SpanStyle")) {
                    int spanStart = spanned2.getSpanStart(annotation);
                    int spanEnd = spanned2.getSpanEnd(annotation);
                    DecodeHelper decodeHelper = new DecodeHelper(annotation.getValue());
                    spanned = spanned2;
                    long j = Color.Unspecified;
                    int i3 = i2;
                    long j2 = TextUnit.Unspecified;
                    MutableSpanStyle mutableSpanStyle = new MutableSpanStyle();
                    mutableSpanStyle.color = j;
                    mutableSpanStyle.fontSize = j2;
                    mutableSpanStyle.fontWeight = r3;
                    mutableSpanStyle.fontStyle = r3;
                    mutableSpanStyle.fontSynthesis = r3;
                    mutableSpanStyle.fontFeatureSettings = r3;
                    mutableSpanStyle.letterSpacing = j2;
                    mutableSpanStyle.baselineShift = r3;
                    mutableSpanStyle.textGeometricTransform = r3;
                    mutableSpanStyle.background = j;
                    mutableSpanStyle.textDecoration = r3;
                    mutableSpanStyle.shadow = r3;
                    while (decodeHelper.parcel.dataAvail() > b2) {
                        byte readByte = decodeHelper.parcel.readByte();
                        if (readByte == b2) {
                            if (decodeHelper.parcel.dataAvail() < 8) {
                                break;
                            }
                            long readLong = decodeHelper.parcel.readLong();
                            int i4 = Color.$r8$clinit;
                            mutableSpanStyle.color = readLong;
                        } else if (readByte != b) {
                            byte b3 = 3;
                            if (readByte == 3) {
                                if (decodeHelper.parcel.dataAvail() < 4) {
                                    break;
                                }
                                mutableSpanStyle.fontWeight = new FontWeight(decodeHelper.parcel.readInt());
                            } else if (readByte == 4) {
                                if (decodeHelper.parcel.dataAvail() < b2) {
                                    break;
                                }
                                byte readByte2 = decodeHelper.parcel.readByte();
                                mutableSpanStyle.fontStyle = new FontStyle((readByte2 != 0 && readByte2 == b2) ? b2 : (byte) 0);
                            } else if (readByte == 5) {
                                if (decodeHelper.parcel.dataAvail() < b2) {
                                    break;
                                }
                                byte readByte3 = decodeHelper.parcel.readByte();
                                if (readByte3 != 0) {
                                    if (readByte3 == b2) {
                                        b3 = b2;
                                    } else if (readByte3 != 3) {
                                        if (readByte3 == b) {
                                            b3 = b;
                                        }
                                    }
                                    mutableSpanStyle.fontSynthesis = new FontSynthesis(b3);
                                }
                                b3 = 0;
                                mutableSpanStyle.fontSynthesis = new FontSynthesis(b3);
                            } else if (readByte == 6) {
                                mutableSpanStyle.fontFeatureSettings = decodeHelper.parcel.readString();
                            } else if (readByte == 7) {
                                if (decodeHelper.parcel.dataAvail() < 5) {
                                    break;
                                }
                                mutableSpanStyle.letterSpacing = decodeHelper.m561decodeTextUnitXSAIIZE();
                            } else if (readByte == 8) {
                                if (decodeHelper.parcel.dataAvail() < 4) {
                                    break;
                                }
                                mutableSpanStyle.baselineShift = new BaselineShift(decodeHelper.parcel.readFloat());
                            } else if (readByte == 9) {
                                if (decodeHelper.parcel.dataAvail() < 8) {
                                    break;
                                }
                                mutableSpanStyle.textGeometricTransform = new TextGeometricTransform(decodeHelper.parcel.readFloat(), decodeHelper.parcel.readFloat());
                            } else if (readByte == 10) {
                                if (decodeHelper.parcel.dataAvail() < 8) {
                                    break;
                                }
                                long readLong2 = decodeHelper.parcel.readLong();
                                int i5 = Color.$r8$clinit;
                                mutableSpanStyle.background = readLong2;
                            } else if (readByte != 11) {
                                z = false;
                                if (readByte != 12) {
                                    b = 2;
                                } else {
                                    if (decodeHelper.parcel.dataAvail() < 20) {
                                        charSequence = text;
                                        break;
                                    }
                                    long readLong3 = decodeHelper.parcel.readLong();
                                    int i6 = Color.$r8$clinit;
                                    mutableSpanStyle.shadow = new Shadow(decodeHelper.parcel.readFloat(), readLong3, (Float.floatToRawIntBits(decodeHelper.parcel.readFloat()) << 32) | (Float.floatToRawIntBits(decodeHelper.parcel.readFloat()) & 4294967295L));
                                    b = 2;
                                    b2 = 1;
                                    text = text;
                                }
                            } else {
                                if (decodeHelper.parcel.dataAvail() < 4) {
                                    z = false;
                                    charSequence = text;
                                    break;
                                }
                                int readInt = decodeHelper.parcel.readInt();
                                byte b4 = (readInt & 2) != 0 ? b2 : (byte) 0;
                                byte b5 = (readInt & b2) != 0 ? b2 : (byte) 0;
                                TextDecoration textDecoration = TextDecoration.LineThrough;
                                TextDecoration textDecoration2 = TextDecoration.Underline;
                                if (b4 != 0 && b5 != 0) {
                                    List listOf = CollectionsKt__CollectionsKt.listOf(textDecoration, textDecoration2);
                                    Integer num = 0;
                                    int size = listOf.size();
                                    for (int i7 = 0; i7 < size; i7 += b2) {
                                        num = Integer.valueOf(((TextDecoration) listOf.get(i7)).mask | num.intValue());
                                    }
                                    textDecoration = new TextDecoration(num.intValue());
                                } else if (b4 == 0) {
                                    textDecoration = b5 != 0 ? textDecoration2 : TextDecoration.None;
                                }
                                mutableSpanStyle.textDecoration = textDecoration;
                                b = 2;
                            }
                        } else {
                            if (decodeHelper.parcel.dataAvail() < 5) {
                                break;
                            }
                            mutableSpanStyle.fontSize = decodeHelper.m561decodeTextUnitXSAIIZE();
                        }
                    }
                    charSequence = text;
                    z = false;
                    arrayList.add(new AnnotatedString.Range(spanStart, spanEnd, new SpanStyle(mutableSpanStyle.color, mutableSpanStyle.fontSize, mutableSpanStyle.fontWeight, mutableSpanStyle.fontStyle, mutableSpanStyle.fontSynthesis, (FontFamily) null, mutableSpanStyle.fontFeatureSettings, mutableSpanStyle.letterSpacing, mutableSpanStyle.baselineShift, mutableSpanStyle.textGeometricTransform, (LocaleList) null, mutableSpanStyle.background, mutableSpanStyle.textDecoration, mutableSpanStyle.shadow, 49152)));
                    i = i3;
                } else {
                    charSequence = text;
                    spanned = spanned2;
                    int i8 = i2;
                    z = z2;
                    i = i8;
                }
                if (i == length) {
                    break;
                }
                int i9 = i + 1;
                text = charSequence;
                z2 = z;
                spanned2 = spanned;
                r3 = 0;
                i2 = i9;
                b2 = 1;
                b = 2;
            }
        } else {
            charSequence = text;
        }
        return new AnnotatedString(charSequence.toString(), arrayList, 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ce, code lost:
    
        if (androidx.compose.ui.text.font.FontSynthesis.m611equalsimpl0(r11, 3) != false) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setText(androidx.compose.ui.text.AnnotatedString r17) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidClipboardManager.setText(androidx.compose.ui.text.AnnotatedString):void");
    }
}
