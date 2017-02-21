package org.ayo.rx.sample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_skip extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "take";
    }

    @Override
    protected String getImageName() {
        return "skip1";
    }

    @Override
    protected String getCodeNormal() {
        return "Flowable.interval(1, 1, TimeUnit.SECONDS)\n" +
                "   .skip(4)\n" +
                "会跳过前四个item，从4开始输出";
    }

    private Disposable task;

    protected void runOk(){
        /*
        - empty
            - 直接调用complete
         */
        task = Flowable.interval(1, 1, TimeUnit.SECONDS)
                .skip(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long s) throws Exception {
                        notifyy(s + "");
                    }
                }, Functions.ERROR_CONSUMER,
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                notifyy("onComplete---结束了！@@");
                            }
                        },
                        FlowableInternalHelper.RequestMax.INSTANCE);

    }

    protected void runError(){
    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.dispose();
    }


}
