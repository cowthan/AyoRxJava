package org.ayo.rx.sample;

import android.util.Log;

import org.ayo.sample.menu.notify.ToasterDebug;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_onErrorResumeNext extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "empty";
    }

    private Disposable task;

    protected void runOk(){
        /*
        - empty
            - 直接调用complete
         */
        task = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                Thread.sleep(1000);
                if(1 + 1 == 2){
                    throw new RuntimeException("Don't worry, just test");
                }else{
                    e.onComplete();
                }
            }

        })
                //.onExceptionResumeNext(Observable.just("onErrorResumeNext - 111", "onErrorResumeNext - 222"))
//                .onErrorReturn(new Function<Throwable, String>() {
//                    @Override
//                    public String apply(Throwable throwable) throws Exception {
//                        return "onErrorReturn了";
//                    }
//                })
                //.retry(3)
//                .retry(3, new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(Throwable throwable) throws Exception {
//                        return false;
//                    }
//                })
//                .retry(new BiPredicate<Integer, Throwable>() {
//                    @Override
//                    public boolean test(Integer integer, Throwable throwable) throws Exception {
//                        if(integer <= 2){
//                            return true;
//                        }
//                        return false;
//                    }
//                })
//                .retryUntil(new BooleanSupplier() {
//                    @Override
//                    public boolean getAsBoolean() throws Exception {
//                        boolean shouldStopRetry = true;
//                        return shouldStopRetry;
//                    }
//                })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return Observable.just(1, 2, 3);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                               @Override
                               public void accept(String s) throws Exception {
                                   Log.i("error", "onNext--" + s);
                                   if ("3".equals(s)) {
                                       throw new RuntimeException("Don't worry, just test");
                                   } else {
                                       notifyy(s);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   ToasterDebug.toastShort("onError--出异常了：" + throwable.getMessage());
                                   throwable.printStackTrace();
                               }
                           },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                notifyy("onComplete---结束了！@@");
                            }
                        });

    }

    protected void runError(){
    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.dispose();
    }
}
