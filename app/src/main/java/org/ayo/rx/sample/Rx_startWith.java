package org.ayo.rx.sample;

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

public class Rx_startWith extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "empty";
    }

    private Disposable task;

    protected void runOk(){
        /*
        - startWith
            - 在原始Observable发射item之前增加几项item
            - startWith(T)
            - startWithArray(T[])
            - startWith(Iterable<T>)
            - startWith(Publisher<T>)
         */
        task = Flowable.fromIterable(DataMgmr.Memory.getDataListQuick())
                .startWithArray("..", "....", "......", "哎呦我草泥马")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object s) throws Exception {
                        notifyy("item--" + s);
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
