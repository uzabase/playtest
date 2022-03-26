# レスポンスのアサートができる

## ステータスコード
* URL"/"にPUTリクエストを送る
* レスポンスステータスコードが"200"である

## ヘッダー
* URL"/"にPUTリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である

## GETリクエスト
* URL"/"にGETリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"Hello Playtest-Gauge-Rest!"である

## POSTリクエスト
* URL"/"にPOSTリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## PUTリクエスト
* URL"/"にPUTリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である


## DELETEリクエスト
* URL"/"にDELETEリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である
