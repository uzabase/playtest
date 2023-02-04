# レコード数のアサート
tags: _setup

## 存在するレコード数をアサートする
* DB"test_db"の"test"スキーマの"todos"テーブルの、条件"id = 'cc23f9f1-4e6a-4e9a-a17f-3de5e3600691'"なレコード数が"0"である
* DB"test_db"の"test"スキーマの"todos"テーブルの、条件"id = '404e05a3-a34f-47d0-8997-968d90ba64ca'"なレコード数が"1"である
* DB"test_db"の"test"スキーマの"todos"テーブルの、条件"priority = 1"なレコード数が"2"である

## 存在するレコード数を複数条件でアサートする
* DB"test_db"の"test"スキーマの"todos"テーブルの、条件"memo is NULL AND done = false"なレコード数が"0"である
* DB"test_db"の"test"スキーマの"todos"テーブルの、条件"memo is NULL AND done = true"なレコード数が"1"である
