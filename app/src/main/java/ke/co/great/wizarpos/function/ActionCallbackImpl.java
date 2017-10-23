
package ke.co.great.wizarpos.function;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import ke.co.great.wizarpos.common.Common;
import ke.co.great.wizarpos.common.Enums;
import ke.co.great.wizarpos.mvc.base.ActionCallback;

public class ActionCallbackImpl extends ActionCallback {

    private Handler handler;

    /**
     * three ways to write log:
     * <p>
     * sendResponse(String msgString) write normal log, the type is 'Log'
     * </p>
     * <p>
     * sendSuccessMsg(String msgString)write success log, the type is 'SUCCESSLOG'
     * </p>
     * <p>
     * sendFailedMsg(String msgString)write fail log, the type is 'FAILEDLOG'
     * </p>
     * 
     * @param handler
     */
    public ActionCallbackImpl(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void sendResponse(int code, String msgString) {
        Message msg = new Message();
        msg.what = code;
        msg.obj = "\t\t" + msgString + "\n";
        handler.sendMessage(msg);
    }

    @Override
    public void sendResponse(String msgString) {
        Message msg = new Message();
        msg.what = Enums.StateLog.LOG;
        msg.obj = "\t\t" + msgString + "\n";
        handler.sendMessage(msg);
    }

    public void sendFailedMsg(String msg) {
        int code = Enums.StateLog.LOG_FAILED;
        sendResponse(code, msg);
    }

    public void sendSuccessMsg(String msg) {
        int code = Enums.StateLog.LOG_SUCCESS;
        sendResponse(code, msg);
    }
    
    public void sendFailedMsgInCheck(String msg) {
        int code = Enums.StateLog.LOG_FAILED;
        msg = Common.getMethodName() + " " + msg;
        sendResponse(code, msg);
    }

    public void sendSuccessMsgInCheck(String msg) {
        int code = Enums.StateLog.LOG_SUCCESS;
        msg = Common.getMethodName() + " " + msg;
        sendResponse(code, msg);
    }
    
    public void sendImgVisible(){
        handler.obtainMessage(Enums.StateLog.IMG_VISIBLE).sendToTarget();
    }
    
    public void sendImgInvisible(){
        handler.obtainMessage(Enums.StateLog.IMG_INVISIBLE).sendToTarget();
    }
    
    public void sendImgShow(Bitmap bm){
        int code = Enums.StateLog.IMG_SHOW;
        handler.obtainMessage(code, bm).sendToTarget();
    }
}
