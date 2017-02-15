package org.ayo.rx.sample;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DSFlowableDemo_create extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "Flowable.create";
    }

    Subscription task = null;

    protected void runOk(){
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                List<String> list = DataMgmr.server.getDataListSlow();
                for(String s: list){
                    e.onNext(s);
                    sleep();
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                //这一步是必须，我们通常可以在这里做一些初始化操作，调用request()方法表示初始化工作已经完成
                //调用request()方法，会立即触发onNext()方法
                //在onComplete()方法完成，才会再执行request()后边的代码
                task = s;
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.e("hahahahha--onNext", s);
                notifyy(s);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("hahahahha--onError", t.getMessage());
            }

            @Override
            public void onComplete() {
                //由于Reactive-Streams的兼容性，方法onCompleted被重命名为onComplete
                Log.e("hahahahha--onComplete", "complete--flowable");
                task = null;
            }
        });
    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.cancel();
    }

    protected void runError(){


        Observable<String> src = null;
        src.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
/*    private void runOk2(){

        Completable src = Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        src = Completable.fromRunnable(new Runnable() {});
        src = Completable.fromCallable(new Callable<String>() {});
        src = Completable.fromFuture(Future<?>);
        src = Completable.fromSingle(Single.just(""));
        src = Completable.fromPublisher(Flowable.just(""));

        Single<String> src = Single.just(String item);
        src = Single.fromCallable(Call<? extends String>);
        src = Single.fromFuture(Future<? extends String>);
        src = Single.fromFuture(future, Scheduler);
        src = Single.fromFuture(future, long timeout, TimeUnit);
        src = Single.fromFuture(future, long timeout, TimeUnit, Scheduler);
        src = Single.fromObservable(Observable<? extends String>);
        src = Single.fromPublisher(Publisher<? extends String>);


        Flowable<String> src = Flowable.just(T...);
        src = Flowable.fromIterable(List<String>);
        src = Flowable.fromArray(String[] items);
        src = Flowable.fromCallable(Callable);
        src = Flowable.fromFuture(Future);
        src = Flowable.fromPublisher(Publisher);

        ///创建数据源
        ///---1 create：自己取数据，数据可能在任何地方取，所以应该配合线程调度
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                List<String> list = DataMgmr.server.getDataListSlow();
                for(String s: list){
                    e.onNext(s);
                    //sleep();
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
        flowable.subscribeOn(Schedulers.io());

        ///---2 fromIterato和数组
        List<String> list = DataMgmr.Memory.getDataListQuick();
        Flowable<String> flowable1 = Flowable.fromIterable(list);
        flowable1.subscribeOn(AndroidSchedulers.mainThread());

        ///---3 fromCallable和fromFuture
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                sleep();
                return "欲迎还拒羞解衣";
            }
        });
        Flowable<String> flowable2 = Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                sleep();
                return "半似游客半似鸡";
            }
        });
        flowable2.subscribeOn(Schedulers.io());

        Flowable<String> flowable3 = Flowable.fromFuture(future, Schedulers.computation());

        Flowable<String> flowable4 = Flowable.just("身无分文行千里", "别人刷卡我刷逼");

        ///订阅
        flowable.observeOn(AndroidSchedulers.mainThread());
        flowable.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                //这一步是必须，我们通常可以在这里做一些初始化操作，调用request()方法表示初始化工作已经完成
                //调用request()方法，会立即触发onNext()方法
                //在onComplete()方法完成，才会再执行request()后边的代码
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                notifyy(s);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("onError", t.getMessage());
            }

            @Override
            public void onComplete() {
                //由于Reactive-Streams的兼容性，方法onCompleted被重命名为onComplete
                Log.e("onComplete", "complete--flowable");
            }
        });

        flowable3.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ///这个是onNext
                notifyy(s);
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ///这个是onError
                Functions.ERROR_CONSUMER.accept(throwable); //默认处理
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                ///这个是onComplete
                Functions.EMPTY_ACTION.run();  //默认处理
            }
        }, new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                ///这个是onSubscribe
                FlowableInternalHelper.RequestMax.INSTANCE.accept(subscription); //默认处理
            }
        });

        flowable2.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

        Maybe<String> src = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> e) throws Exception {
                e.onSuccess("");
                e.onComplete();
            }
        });
        src = Maybe.fromCompletable(Completable.fromSingle(Single.just("")));

        Completable src = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                e.onComplete();
            }
        });
        src.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        src.subscribe(new Action() {
            @Override
            public void run() throws Exception {

            }
        });

        src.subscribe(new Action() {
            @Override
            public void run() throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

        Completable<String> src = null;
        src.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        src.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
        src.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        })

    }*/
}
