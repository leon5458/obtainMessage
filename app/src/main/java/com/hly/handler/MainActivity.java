package com.hly.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private Handler handler;
    private TextView textView;

    private MesBean mesBean = new MesBean("张三丰", "男", 108);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_content);

        handler = new Handler(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启一个线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Message message = Message.obtain();
                        //用户定义的消息代码，以便收件人可以识别
                        message.what = 1;
                        // 定义传递的int 类型的值
                        message.arg1 = 119;
                        message.arg2 = 120;
                        // 定义传递的值由于类型是object(对象) 所以比较常用,可以传递各种值
                        message.obj = "传送的内容:哇咔咔";
                        handler.sendMessage(message);

                    }
                });
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Message message = Message.obtain();
                        //用户定义的消息代码，以便收件人可以识别
                        message.what = 2;
                        // 定义传递的值定义类型为bean
                        message.obj = mesBean;
                        handler.sendMessage(message);
                    }
                });
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Message message = Message.obtain();
                        //用户定义的消息代码，以便收件人可以识别
                        message.what = 3;
                        // setData 传值
                        Bundle bundle = new Bundle();
                        bundle.putString("Name", "张三丰");
                        bundle.putString("Sex", "男");
                        bundle.putInt("Age", 108);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                });
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        handler.obtainMessage(4, mesBean).sendToTarget();
                    }
                });
            }
        });

    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case 1:
                String info = (String) msg.obj;
                int mes1 = msg.arg1;
                int mes2 = msg.arg2;
                textView.setText(info + "arg1=" + mes1 + "arg2=" + mes2);
                break;
            case 2:
            case 4:
                MesBean mesBean1 = (MesBean) msg.obj;
                textView.setText(
                        mesBean1.getName() + "," + mesBean1.getSex() + "," + mesBean1.getAge());
                break;
            case 3:
                String name = msg.getData().getString("Name");
                String sex = msg.getData().getString("Sex");
                int age = msg.getData().getInt("Age");
                textView.setText(name + sex + age);
                break;

        }
        return false;
    }
}
