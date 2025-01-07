package androidx.appsearch.platformstorage.converter;

import android.app.appsearch.SearchResult;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.app.SearchResult;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SearchResultToPlatformConverter {
    public static SearchResult toJetpackSearchResult(android.app.appsearch.SearchResult searchResult) {
        searchResult.getClass();
        GenericDocument jetpackGenericDocument = GenericDocumentToPlatformConverter.toJetpackGenericDocument(searchResult.getGenericDocument());
        SearchResult.Builder builder = new SearchResult.Builder(searchResult.getPackageName(), searchResult.getDatabaseName());
        builder.resetIfBuilt();
        builder.mGenericDocument = jetpackGenericDocument;
        searchResult.getRankingSignal();
        builder.resetIfBuilt();
        List<SearchResult.MatchInfo> matchInfos = searchResult.getMatchInfos();
        for (int i = 0; i < matchInfos.size(); i++) {
            SearchResult.MatchInfo matchInfo = matchInfos.get(i);
            matchInfo.getClass();
            matchInfo.getPropertyPath().getClass();
            if (matchInfo.getExactMatchRange().getStart() > matchInfo.getExactMatchRange().getEnd()) {
                throw new IllegalArgumentException("Start point must be less than or equal to end point");
            }
            if (matchInfo.getSnippetRange().getStart() > matchInfo.getSnippetRange().getEnd()) {
                throw new IllegalArgumentException("Start point must be less than or equal to end point");
            }
            if (matchInfo.getSubmatchRange().getStart() > matchInfo.getSubmatchRange().getEnd()) {
                throw new IllegalArgumentException("Start point must be less than or equal to end point");
            }
            SearchResult.MatchInfo matchInfo2 = new SearchResult.MatchInfo();
            builder.resetIfBuilt();
            builder.mMatchInfos.add(matchInfo2);
        }
        Iterator<android.app.appsearch.SearchResult> it = searchResult.getJoinedResults().iterator();
        while (it.hasNext()) {
            androidx.appsearch.app.SearchResult jetpackSearchResult = toJetpackSearchResult(it.next());
            builder.resetIfBuilt();
            builder.mJoinedResults.add(jetpackSearchResult);
        }
        builder.mBuilt = true;
        return new androidx.appsearch.app.SearchResult(builder.mGenericDocument.mDocumentParcel, builder.mMatchInfos, builder.mPackageName, builder.mDatabaseName, builder.mJoinedResults, builder.mInformationalRankingSignals);
    }
}
