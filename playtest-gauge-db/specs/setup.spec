# playtest-dbのセットアップ関連のテスト
tags: truncate

## CSVファイルからセットアップする
* test_dbにテストデータをセットアップする
* DB"test_db"の"test"スキーマの"todos"テーブルの、"id"を"'404e05a3-a34f-47d0-8997-968d90ba64ca'"で取得した一意の"memo"が文字列の"this is test"である
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である

## truncateする
* test_dbにテストデータをセットアップする
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である
* test_dbのテーブルをtruncateする
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"0"である

## 1レコードを挿入する
* test_dbにレコードを挿入する
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である

## 1レコードを削除する
* test_dbにレコードを挿入する
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"1"である
* test_dbからレコードを削除する
* DB"test_db"の"test"スキーマの"todos"テーブルのレコード数が"0"である
