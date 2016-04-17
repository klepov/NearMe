package klep.nearme.Utils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import link.fls.swipestack.SwipeHelper;
import link.fls.swipestack.SwipeStack;

/**
 * Created by klep.io on 31.03.16.
 */
public class SwipeHelperExept extends SwipeHelper {
    public SwipeHelperExept(SwipeStack swipeStack) {
        super(swipeStack);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("exept","" + event.getPointerId(0));
        return super.onTouch(v, event);

    }
}
