package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.android.app.viewcapture.data.ViewNode;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DocumentDataParser implements ValueParser {
    public static final DocumentDataParser INSTANCE = new DocumentDataParser();
    public static final JsonReader.Options NAMES = JsonReader.Options.of("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of", "ps", "sz");

    @Override // com.airbnb.lottie.parser.ValueParser
    public final Object parse(JsonReader jsonReader, float f) {
        boolean z;
        int i;
        DocumentData.Justification justification = DocumentData.Justification.CENTER;
        jsonReader.beginObject();
        String str = null;
        DocumentData.Justification justification2 = justification;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z2 = true;
        String str2 = null;
        PointF pointF = null;
        PointF pointF2 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    z = z2;
                    str = jsonReader.nextString();
                    z2 = z;
                    break;
                case 1:
                    z = z2;
                    str2 = jsonReader.nextString();
                    z2 = z;
                    break;
                case 2:
                    z = z2;
                    f2 = (float) jsonReader.nextDouble();
                    z2 = z;
                    break;
                case 3:
                    z = z2;
                    i = i4;
                    int nextInt = jsonReader.nextInt();
                    justification2 = (nextInt > 2 || nextInt < 0) ? justification : DocumentData.Justification.values()[nextInt];
                    i4 = i;
                    z2 = z;
                    break;
                case 4:
                    z = z2;
                    i2 = jsonReader.nextInt();
                    z2 = z;
                    break;
                case 5:
                    z = z2;
                    f3 = (float) jsonReader.nextDouble();
                    z2 = z;
                    break;
                case 6:
                    z = z2;
                    f4 = (float) jsonReader.nextDouble();
                    z2 = z;
                    break;
                case 7:
                    z = z2;
                    i3 = JsonUtils.jsonToColor(jsonReader);
                    z2 = z;
                    break;
                case 8:
                    i4 = JsonUtils.jsonToColor(jsonReader);
                    break;
                case 9:
                    z = z2;
                    f5 = (float) jsonReader.nextDouble();
                    z2 = z;
                    break;
                case 10:
                    z2 = jsonReader.nextBoolean();
                    i4 = i4;
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    z = z2;
                    jsonReader.beginArray();
                    i = i4;
                    pointF = new PointF(((float) jsonReader.nextDouble()) * f, ((float) jsonReader.nextDouble()) * f);
                    jsonReader.endArray();
                    i4 = i;
                    z2 = z;
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    jsonReader.beginArray();
                    pointF2 = new PointF(((float) jsonReader.nextDouble()) * f, ((float) jsonReader.nextDouble()) * f);
                    jsonReader.endArray();
                    z2 = z2;
                    i4 = i4;
                    break;
                default:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        DocumentData documentData = new DocumentData();
        documentData.text = str;
        documentData.fontName = str2;
        documentData.size = f2;
        documentData.justification = justification2;
        documentData.tracking = i2;
        documentData.lineHeight = f3;
        documentData.baselineShift = f4;
        documentData.color = i3;
        documentData.strokeColor = i4;
        documentData.strokeWidth = f5;
        documentData.strokeOverFill = z2;
        documentData.boxPosition = pointF;
        documentData.boxSize = pointF2;
        return documentData;
    }
}
