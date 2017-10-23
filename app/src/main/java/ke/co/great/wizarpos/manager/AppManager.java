
package ke.co.great.wizarpos.manager;

import android.app.Application;

import ke.co.great.wizarpos.mvc.base.ActionManager;

public class AppManager extends Application {

    /**
     * 外接设备的类型
     * <p>
     * Access equipment type.
     */
    public static String model = "";
    public static boolean isHand = false;
    public static boolean isQ1 = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // Common.getModel();

        model = "actions";

        ActionManager.initActionContainer(new ActionContainerImpl(this, model));
    }

}
