package androidx.compose.ui.text;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AnnotationType {
    public static final /* synthetic */ AnnotationType[] $VALUES;
    public static final AnnotationType Clickable;
    public static final AnnotationType Link;
    public static final AnnotationType Paragraph;
    public static final AnnotationType Span;
    public static final AnnotationType String;
    public static final AnnotationType Url;
    public static final AnnotationType VerbatimTts;

    static {
        AnnotationType annotationType = new AnnotationType("Paragraph", 0);
        Paragraph = annotationType;
        AnnotationType annotationType2 = new AnnotationType("Span", 1);
        Span = annotationType2;
        AnnotationType annotationType3 = new AnnotationType("VerbatimTts", 2);
        VerbatimTts = annotationType3;
        AnnotationType annotationType4 = new AnnotationType("Url", 3);
        Url = annotationType4;
        AnnotationType annotationType5 = new AnnotationType("Link", 4);
        Link = annotationType5;
        AnnotationType annotationType6 = new AnnotationType("Clickable", 5);
        Clickable = annotationType6;
        AnnotationType annotationType7 = new AnnotationType("String", 6);
        String = annotationType7;
        $VALUES = new AnnotationType[]{annotationType, annotationType2, annotationType3, annotationType4, annotationType5, annotationType6, annotationType7};
    }

    public static AnnotationType valueOf(String str) {
        return (AnnotationType) Enum.valueOf(AnnotationType.class, str);
    }

    public static AnnotationType[] values() {
        return (AnnotationType[]) $VALUES.clone();
    }
}
