# playtest-dbのセットアップ関連のテスト
tags: _truncate

## CSVファイルからセットアップする
* [not-provide] test_dbにテストデータをセットアップする
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"2"である

## truncateする
* [not-provide] test_dbにテストデータをセットアップする
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"2"である
* [not-provide] test_dbのテーブルをtruncateする
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"0"である

## 1レコードを挿入する
* [not-provide] test_dbのtodoテーブルにレコードを挿入する
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である

## 1レコードを削除する
* [not-provide] test_dbのtodoテーブルにレコードを挿入する
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である
* [not-provide] test_dbのtodoテーブルからレコードを削除する
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"0"である
