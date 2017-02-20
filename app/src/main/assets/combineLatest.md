# combineLatest

- 区别于zip，注意看ball图
- 不管谁，只要发出一个item，则combineLatest会立即将其与另一个Observable的最近的item组合并发射
- 不管最近的item是否已经用过
- 这个区别于zip的只有等两个Observable都发出新的item，才会组合并发射
- 只有当找不到对方Observable的最新item时，才会等待

```
```