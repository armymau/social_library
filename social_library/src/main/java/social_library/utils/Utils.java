package social_library.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

public class Utils {

    public static int getListViewCurrentScrollPosition(ListView listView) {
        View c = listView.getChildAt(0);
        if (c != null) {
            return -c.getTop() + listView.getFirstVisiblePosition() * c.getHeight();
        }
        return -listView.getPaddingTop();
    }

    public static int getListViewCurrentScrollPosition(RecyclerView recyclerView) {
        View c = recyclerView.getChildAt(0);
        if (c != null) {
            LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            return -c.getTop() + firstVisiblePosition * c.getHeight();
        }
        return -recyclerView.getPaddingTop();
    }
}
