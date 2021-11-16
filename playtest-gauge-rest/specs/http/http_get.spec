# HTTP GET リクエストを送信するステップのテスト
tags: http-request-test
* URL"/"にGETリクエストを送る

## GETリクエストを送信できる
* URL"/"にGETリクエストが送信された

## GETリクエストに対するレスポンスをアサートできる
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* JSON形式のレスポンスボディが<file:expected/get-response.json>と一致する
