package lvc.com.androidarchcomponents.utils;

import java.util.concurrent.Executor;

/**
 * Created by leonardo2050 on 28/05/17.
 */

public class DirectExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }
}
