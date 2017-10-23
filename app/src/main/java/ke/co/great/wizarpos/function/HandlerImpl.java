package ke.co.great.wizarpos.function;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import ke.co.great.wizarpos.common.Enums;
import ke.co.great.wizarpos.util.LogHelper;


public class HandlerImpl extends Handler {

	private TextView txtResult;

	/**
	 * Output the information to display.
	 */
	public HandlerImpl(TextView textView) {
		this.txtResult = textView;
	}

	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case Enums.StateLog.LOG:
			LogHelper.infoAppendMsg((String) msg.obj, txtResult);
			break;
		case Enums.StateLog.LOG_SUCCESS:
			LogHelper.infoAppendMsgForSuccess((String) msg.obj, txtResult);
			break;
		case Enums.StateLog.LOG_FAILED:
			LogHelper.infoAppendMsgForFailed((String) msg.obj, txtResult);
			break;
		case Enums.StateLog.IMG_VISIBLE:
		    
		default:
			LogHelper.infoAppendMsg((String) msg.obj, txtResult);
			break;
		}
	}

}
