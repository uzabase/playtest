# JSONPathを使用したレスポンスのJSONをアサートするステップ

## 文字列
* レスポンスボディとしてシナリオデータストアに"{\"test\": \"test\"}"を保存する
* レスポンスのJSONの"$.test"が文字列の"test"である

## 整数値
* レスポンスボディとしてシナリオデータストアに"{\"test\": 0}"を保存する
* レスポンスのJSONの"$.test"が整数の"0"である

## 小数値
* レスポンスボディとしてシナリオデータストアに"{\"test\": 0.0}"を保存する
* レスポンスのJSONの"$.test"が小数の"0.0"である

## 真偽値
* レスポンスボディとしてシナリオデータストアに"{\"test\": true}"を保存する
* レスポンスのJSONの"$.test"が真偽値の"true"である

## 配列の特定の要素の値(文字列)
* レスポンスボディとしてシナリオデータストアに"{\"key1\":[{\"id\":\"a\",\"key3\":\"x\"}, {\"id\":\"b\",\"key3\": \"y\" }, {\"id\":\"c\",\"key3\":\"z\"}]}"を保存する
* レスポンスのJSONの"$.key1"の配列の、UniqueKey"id"の値が"b"である要素の"key3"が、文字列の"y"である

## 配列の特定の要素の値(整数)
* レスポンスボディとしてシナリオデータストアに"{\"key1\":[{\"id\":\"a\",\"key3\":1}, {\"id\":\"b\",\"key3\": 2 }, {\"id\":\"c\",\"key3\":3}]}"を保存する
* レスポンスのJSONの"$.key1"の配列の、UniqueKey"id"の値が"b"である要素の"key3"が、整数値の"2"である

## 配列の特定の要素の値(小数)
* レスポンスボディとしてシナリオデータストアに"{\"key1\":[{\"id\":\"a\",\"key3\":1.0}, {\"id\":\"b\",\"key3\": 2.0 }, {\"id\":\"c\",\"key3\":3.0}]}"を保存する
* レスポンスのJSONの"$.key1"の配列の、UniqueKey"id"の値が"b"である要素の"key3"が、小数値の"2.0"である

## 配列の特定の要素の値(真偽値)
* レスポンスボディとしてシナリオデータストアに"{\"key1\":[{\"id\":\"a\",\"key3\":false}, {\"id\":\"b\",\"key3\": true }, {\"id\":\"c\",\"key3\":true}]}"を保存する
* レスポンスのJSONの"$.key1"の配列の、UniqueKey"id"の値が"b"である要素の"key3"が、真偽値の"true"である

## 配列の特定の要素の存在有無
* レスポンスボディとしてシナリオデータストアに"{\"key1\":[{\"id\":\"a\"}]}"を保存する
* レスポンスのJSONの"$.key1"の配列に、Key"id"の値が"a"である要素が存在する

## 配列の値の存在（文字列）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [\"test1\", \"test2\", \"test3\"]}"を保存する
* レスポンスのJSONの配列"$.tests"に、文字列"test2"が存在する

## 配列の値が存在しない（文字列）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [\"test1\", \"test2\", \"test3\"]}"を保存する
* レスポンスのJSONの配列"$.tests"に、文字列"test5"が存在しない

## 配列の値の存在（整数値）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [1, 2, 3]}"を保存する
* レスポンスのJSONの配列"$.tests"に、整数値"3"が存在する

## 配列の値が存在しない（整数値）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [1, 3]}"を保存する
* レスポンスのJSONの配列"$.tests"に、整数値"2"が存在しない

## 配列の値の存在（小数値）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [1.1, 2.1, 3.1]}"を保存する
* レスポンスのJSONの配列"$.tests"に、小数値"3.1"が存在する

## 配列の値が存在しない（小数値）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [1.0, 3.3]}"を保存する
* レスポンスのJSONの配列"$.tests"に、小数値"2.1"が存在しない

## 配列の長さ
* レスポンスボディとしてシナリオデータストアに"{\"key1\":[{\"id\":\"a\",\"key3\":\"x\"}, {\"id\":\"b\",\"key3\": \"y\" }]}"を保存する
* レスポンスのJSONの"$.key1"の配列の長さが"2"である

## Keyが存在しない
* レスポンスボディとしてシナリオデータストアに"{\"test\": true}"を保存する
* レスポンスのJSONの"$.nonexist"が存在しない

## ソート順（数値、昇順）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"id\": 1}, {\"id\": 2}, {\"id\": 3}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、数値"id"の昇順に並んでいる

## ソート順（数値、降順）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"id\": 3}, {\"id\": 2}, {\"id\": 1}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、数値"id"の降順に並んでいる

## ソート順（タイムゾーン付きの日付/時間、昇順）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"createdAt\": \"2007-12-03T10:15:30+01:00\"}, {\"createdAt\": \"2007-12-03T11:15:30+01:00\"}, {\"createdAt\": \"2007-12-05T10:15:30+01:00\"}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、タイムゾーン付きの日付/時間"createdAt"の昇順に並んでいる

## ソート順（タイムゾーン付きの日付/時間、降順）
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"createdAt\": \"2007-12-06T10:15:30+01:00\"}, {\"createdAt\": \"2007-12-06T09:15:30+01:00\"}, {\"createdAt\": \"2007-12-05T10:15:30+01:00\"}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、タイムゾーン付きの日付/時間"createdAt"の降順に並んでいる
