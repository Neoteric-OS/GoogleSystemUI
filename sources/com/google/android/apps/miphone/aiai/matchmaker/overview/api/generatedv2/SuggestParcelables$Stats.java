package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SuggestParcelables$Stats {
    public long endTimestampMs;
    public long entityExtractionMs;
    public long ocrDetectionMs;
    public long ocrMs;
    public long startTimestampMs;

    public static SuggestParcelables$Stats create(Bundle bundle) {
        SuggestParcelables$Stats suggestParcelables$Stats = new SuggestParcelables$Stats();
        if (bundle.containsKey("startTimestampMs")) {
            suggestParcelables$Stats.startTimestampMs = bundle.getLong("startTimestampMs");
        }
        if (bundle.containsKey("endTimestampMs")) {
            suggestParcelables$Stats.endTimestampMs = bundle.getLong("endTimestampMs");
        }
        if (bundle.containsKey("ocrMs")) {
            suggestParcelables$Stats.ocrMs = bundle.getLong("ocrMs");
        }
        if (bundle.containsKey("ocrDetectionMs")) {
            suggestParcelables$Stats.ocrDetectionMs = bundle.getLong("ocrDetectionMs");
        }
        if (bundle.containsKey("entityExtractionMs")) {
            suggestParcelables$Stats.entityExtractionMs = bundle.getLong("entityExtractionMs");
        }
        return suggestParcelables$Stats;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong("startTimestampMs", this.startTimestampMs);
        bundle.putLong("endTimestampMs", this.endTimestampMs);
        bundle.putLong("ocrMs", this.ocrMs);
        bundle.putLong("ocrDetectionMs", this.ocrDetectionMs);
        bundle.putLong("entityExtractionMs", this.entityExtractionMs);
        return bundle;
    }
}
