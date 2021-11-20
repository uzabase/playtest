# HTTP GET リクエストを送信するステップのテスト
tags: http-request-test

## GETリクエストを送信できる
* URL"/"にGETリクエストを送る
* URL"/"にGETリクエストが送信された

## GETリクエストに対するレスポンスをアサートできる
* URL"/"にGETリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* JSON形式のレスポンスボディが<file:expected/get-response.json>と一致する

## ヘッダーを指定してGETリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストが送信された
