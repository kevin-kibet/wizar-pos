
package ke.co.great.wizarpos.action;

import android.util.Log;


import java.util.Map;

import ke.co.great.wizarpos.R;
import ke.co.great.wizarpos.function.ActionCallbackImpl;
import ke.co.great.wizarpos.mvc.base.AbstractAction;
import ke.co.great.wizarpos.mvc.base.ActionCallback;

public class ConstantAction extends AbstractAction {
    public static String TAG = null;
    protected ActionCallbackImpl mCallback;
    // public static boolean isOpened = false;
    public boolean isOpened = false;
    protected static final int EVENT_ID_CANCEL = -1;

    @Override
    protected void doBefore(Map<String, Object> param, ActionCallback callback) {
        TAG = this.getClass().getName()
                .substring(this.getClass().getName().indexOf("action.") + "action.".length());
        Log.e("TAG", "TAG = " + TAG);
    }

    int checkOpenedAndGetData(DataAction action) {
        int result = -1;
        if (!isOpened) {
            mCallback.sendFailedMsg(mContext.getResources().getString(R.string.device_not_open));
        } else {
            try {
                result = action.getResult();
                if (result < 0) {
                    mCallback.sendFailedMsgInCheck(mContext.getResources().getString(
                            R.string.operation_with_error)
                            + result);
                } else {
                    mCallback.sendSuccessMsgInCheck(mContext.getResources().getString(
                            R.string.operation_successful));
                }
            } catch (Throwable e) {
                e.printStackTrace();
                mCallback.sendFailedMsgInCheck(mContext.getResources().getString(
                        R.string.operation_failed));
            }
        }
        return result;
    }

    int getData(DataAction action) {
        int result = 0;
        try {
            result = action.getResult();
            if (result < 0) {
                mCallback.sendFailedMsgInCheck(mContext.getResources().getString(
                        R.string.operation_with_error)
                        + result);
            } else {
                mCallback.sendSuccessMsgInCheck(mContext.getResources().getString(
                        R.string.operation_successful));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            mCallback.sendFailedMsgInCheck(mContext.getResources().getString(
                    R.string.operation_failed));
        }
        return result;
    }

}

interface DataAction {
    int getResult();
}
