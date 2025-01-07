package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.content.Context;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SuggestController {
    public final BundleUtils bundleUtils = new BundleUtils();
    public final Context uiContext;
    public final ContentSuggestionsServiceWrapperImpl wrapper;

    public SuggestController(Context context, Context context2, Executor executor, Executor executor2) {
        this.uiContext = context2;
        this.wrapper = new ContentSuggestionsServiceWrapperImpl(context, executor, executor2);
    }

    public void reportMetricsToService(String str, final FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch, SuggestListener suggestListener) {
        List list = feedbackParcelables$FeedbackBatch.feedback;
        int i = Utils.$r8$clinit;
        list.getClass();
        if (list.isEmpty()) {
            return;
        }
        Supplier supplier = new Supplier() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.SuggestController$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                SuggestController suggestController = SuggestController.this;
                FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch2 = feedbackParcelables$FeedbackBatch;
                suggestController.bundleUtils.getClass();
                return BundleUtils.createFeedbackRequest(feedbackParcelables$FeedbackBatch2);
            }
        };
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = this.wrapper;
        contentSuggestionsServiceWrapperImpl.loggingExecutor.execute(new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1(contentSuggestionsServiceWrapperImpl, supplier, str, feedbackParcelables$FeedbackBatch));
    }
}
