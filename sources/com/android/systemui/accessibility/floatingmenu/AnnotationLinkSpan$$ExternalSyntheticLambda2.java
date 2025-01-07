package com.android.systemui.accessibility.floatingmenu;

import android.text.Annotation;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import com.android.systemui.accessibility.floatingmenu.AnnotationLinkSpan;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AnnotationLinkSpan$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ SpannableStringBuilder f$0;
    public final /* synthetic */ SpannableString f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ AnnotationLinkSpan$$ExternalSyntheticLambda2(SpannableStringBuilder spannableStringBuilder, SpannableString spannableString, Annotation annotation) {
        this.f$0 = spannableStringBuilder;
        this.f$1 = spannableString;
        this.f$2 = annotation;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                SpannableStringBuilder spannableStringBuilder = this.f$0;
                SpannableString spannableString = this.f$1;
                Annotation annotation = (Annotation) this.f$2;
                AnnotationLinkSpan annotationLinkSpan = new AnnotationLinkSpan((View.OnClickListener) obj);
                spannableStringBuilder.setSpan(annotationLinkSpan, spannableString.getSpanStart(annotation), spannableString.getSpanEnd(annotation), spannableString.getSpanFlags(annotationLinkSpan));
                break;
            default:
                AnnotationLinkSpan.LinkInfo[] linkInfoArr = (AnnotationLinkSpan.LinkInfo[]) this.f$2;
                SpannableStringBuilder spannableStringBuilder2 = this.f$0;
                SpannableString spannableString2 = this.f$1;
                Annotation annotation2 = (Annotation) obj;
                final String value = annotation2.getValue();
                Arrays.asList(linkInfoArr).stream().filter(new Predicate() { // from class: com.android.systemui.accessibility.floatingmenu.AnnotationLinkSpan$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj2) {
                        AnnotationLinkSpan.LinkInfo linkInfo = (AnnotationLinkSpan.LinkInfo) obj2;
                        return linkInfo.mAnnotation.isPresent() && ((String) linkInfo.mAnnotation.get()).equals(value);
                    }
                }).findFirst().flatMap(new AnnotationLinkSpan$$ExternalSyntheticLambda1()).ifPresent(new AnnotationLinkSpan$$ExternalSyntheticLambda2(spannableStringBuilder2, spannableString2, annotation2));
                break;
        }
    }

    public /* synthetic */ AnnotationLinkSpan$$ExternalSyntheticLambda2(AnnotationLinkSpan.LinkInfo[] linkInfoArr, SpannableStringBuilder spannableStringBuilder, SpannableString spannableString) {
        this.f$2 = linkInfoArr;
        this.f$0 = spannableStringBuilder;
        this.f$1 = spannableString;
    }
}
