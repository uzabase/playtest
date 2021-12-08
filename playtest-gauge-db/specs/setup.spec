# playtest-dbのセットアップ関連のテスト
tags: truncate

## CSVファイルからセットアップする
* [not-provide] test_dbにテストデータをセットアップする
* DB"test_db"の"test"スキーマの"todos"テーブルの、"id"を"'404e05a3-a34f-47d0-8997-968d90ba64ca'"で取得した一意の"memo"が文字列の"this is test"である
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である

## truncateする
* [not-provide] test_dbにテストデータをセットアップする
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である
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
