# HTTP GET リクエストを送信するステップのテスト
tags: http-request-test

## GETリクエストを送信できる
* URL"/"にGETリクエストを送る
* URL"/"にGETリクエストされた

## GETリクエストに対するレスポンスをアサートできる
* URL"/"にGETリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"Hello Playtest-Gauge-Rest!"である

## ヘッダーを指定してGETリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストされた

## ヘッダーを複数指定してGETリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、GETリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストされた
* URL"/"にヘッダー"options: 1111,2222"で、GETリクエストされた
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、GETリクエストされた
// 不要なヘッダーを付け加えたときには失敗する
URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222 \r\n x-unnecessary: hoge"で、GETリクエストされた
