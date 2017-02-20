# timer

- 创建一个在给定的延时之后发射数据项为0的Observable<Long>
- 内部通过OnSubscribeTimerOnce工作

```
Observable.timer(1000,TimeUnit.MILLISECONDS)
            .subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    Log.d("JG",aLong.toString()); // 0
                }
            });
```