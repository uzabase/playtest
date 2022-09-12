# 変化したレコード数のアサート
record-changesタグが付与されたシナリオは、シナリオが始まったタイミングから、1つ目の変化数のアサートのタイミングまでのDBの変化を記録する
## 指定したテーブルで変更されたレコード数をアサートする
tags: record-changes
* [not-provide] test_dbのtodoテーブルのレコードをアップデートする
* DB"test_db"の"test"スキーマの"todos"テーブルで変更されたレコード数が"1"である

## 指定したテーブルで削除されたレコード数をアサートする
tags: record-changes
* [not-provide] test_dbのtodoテーブルのid"404e05a3-a34f-47d0-8997-968d90ba64ca"のレコードを削除する
* DB"test_db"の"test"スキーマの"todos"テーブルで削除されたレコード数が"1"である

## 指定したテーブルで作成されたレコード数をアサートする
tags: record-changes
* [not-provide] test_dbのtodoテーブルにレコードを挿入する
* DB"test_db"の"test"スキーマの"todos"テーブルで作成されたレコード数が"1"である

## Stepによる記録開始タイミングの制御
* start-record
* [not-provide] test_dbのtodoテーブルのレコードをアップデートする
* DB"test_db"の"test"スキーマの"todos"テーブルで変更されたレコード数が"1"である
