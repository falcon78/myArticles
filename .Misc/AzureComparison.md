# Postgres 

## メリット
* 機能がたくさんある
* SQL規格の179個の機能の内160個実装済み, MySQLより多い　(2019年3月)
* jsonデータタイプにも対応している。
* ドキュメントが充実している。
* 拡張性が高い (ユーザプログラムで新しい機能などを追加できる)

## デメリット
* パフォーマンスが若干遅い。
* メモリー使用量が大きい (プロセスごとに10MB)。
* READ処理が多い場合はほかのDBMS(MySQL)の方がいい。 (READ速度が遅いため)
* スピードよりもセキュリティーやデータの保全性を重視している。

便利機能
* テキストサーチがすごい！
* テーブル結合アルゴリズム「ネステッドループ結合（Nested Loop Join）」「ハッシュ結合（Hash Join）」「ソートマージ結合（Sort Merge Join）」の3種類ともサポートしている。
* 下記のようにテーブル継承が可能
* 副問い合わせ式
    https://www.postgresql.jp/document/9.3/html/functions-subquery.html


```
CREATE TABLE cities (
    name            text,
    population      float,
    altitude        int     -- in feet
);

CREATE TABLE capitals (
    state           char(2)
) INHERITS (cities);
```

参考
https://www.digitalocean.com/community/tutorials/sqlite-vs-mysql-vs-postgresql-a-comparison-of-relational-database-management-systems

https://www.postgresql.org/docs/9.5/ddl-inherit.html