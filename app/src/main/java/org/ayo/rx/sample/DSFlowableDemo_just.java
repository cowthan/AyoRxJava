package org.ayo.rx.sample;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DSFlowableDemo_just extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "just";
    }

    protected void runOk(){
        List<String> list = DataMgmr.Memory.getDataListQuick();
        Flowable.just(list.get(0), list.get(1))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        notifyy(s);
                    }
                });
    }

    protected void runError(){

    }

}
