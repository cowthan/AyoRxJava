# startWith

- 在数据序列的开头增加一项数据
- 内部也是调用了concat

```
Observable.just(1,2,3,4,5)
    .startWith(6,7,8)
    .subscribe(item->Log.d("JG",item.toString()));//6,7,8,1,2,3,4,5
```