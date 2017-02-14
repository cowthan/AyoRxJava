package org.ayo.rx.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiang.android.rxjavaapp.R;

import org.ayo.component.MasterFragment;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public abstract class BaseRxDemo extends MasterFragment {

    protected abstract void runOk();
    protected abstract void runError();
    protected abstract String getTitle();

    @Override
    protected int getLayoutId() {
        return R.layout.demo_ds;
    }

    TextView tv;

    @Override
    protected void onCreate2(View view, @Nullable Bundle bundle) {
        Button btn1 = id(R.id.btn1);
        Button btn2 = id(R.id.btn2);

        tv = id(R.id.tv);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                runOk();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                runError();
            }
        });

        TextView title = id(R.id.title);
        title.setText(getTitle());
    }



    @Override
    protected void onDestroy2() {

    }

    protected void notifyy(String s){
        tv.append("\n" + s);
    }

    protected void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void sleep(long m){
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

    }
}
