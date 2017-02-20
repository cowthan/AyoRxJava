# interval

 - 用于间隔的延时任务
- interval(初次delay, 固定interval, TimeUnit.MILLISECONDS)
- 没机会调onComplete
- 具体发的item是什么值，从0开始，到无限


```
 Observable.interval(0, 1, TimeUnit.SECONDS)
            .subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                     //每隔1秒发送数据项，从0开始计数
                     //0,1,2,3....
                }
            });
``