package ke.co.great.wizarpos;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ke.co.great.wizarpos.action.PrinterAction;
import ke.co.great.wizarpos.common.Enums;
import ke.co.great.wizarpos.function.ActionCallbackImpl;
import ke.co.great.wizarpos.mvc.base.ActionCallback;
import ke.co.great.wizarpos.mvc.base.ActionManager;
import ke.co.great.wizarpos.util.LogHelper;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private TextView txtResult;
    private ImageView imgShow;

    public static Handler handler;
    public static ActionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button) findViewById(R.id.print);
        txtResult = (TextView) findViewById(R.id.text_result);
        imgShow = (ImageView) findViewById(R.id.image_show);

        handler = new Handler(handlerCallback);
        callback = new ActionCallbackImpl(handler);

        final Map<String, Object> params = new HashMap<>();
        params.put("host", this);
        params.put("name", "Any Data to pass");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionManager.doSubmit("PrinterAction/open", MainActivity.this, params, callback);
            }
        });
    }

    private Handler.Callback handlerCallback = new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
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
                    Log.e(TAG, "IMG_VISIBLE");
                    txtResult.setVisibility(View.GONE);
                    imgShow.setVisibility(View.VISIBLE);
                    break;
                case Enums.StateLog.IMG_INVISIBLE:
                    Log.e(TAG, "IMG_INVISIBLE");
                    txtResult.setVisibility(View.VISIBLE);
                    imgShow.setVisibility(View.GONE);
                    break;
                case Enums.StateLog.IMG_SHOW:
                    Log.e(TAG, "IMG_SHOW");
                    imgShow.setImageBitmap((Bitmap) msg.obj);
                    break;
                default:
                    LogHelper.infoAppendMsg((String) msg.obj, txtResult);
                    break;
            }
            return true;
        }
    };
}
