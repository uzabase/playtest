# HTTP POST リクエストを送信するステップのテスト

tags: http-request-test

## POSTリクエストを送信できる
* URL"/"にPOSTリクエストを送る
* URL"/"にPOSTリクエストが送信された

## POSTリクエストに対するレスポンスをアサートできる
* URL"/"にPOSTリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* JSON形式のレスポンスボディが<file:expected/post-response.json>と一致する

## ヘッダーを指定してPOSTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストが送信された

## リクエストボディを指定してPOSTリクエストを送信できる
* URL"/"にリクエストボディ"{\"test\": \"post\"}"で、POSTリクエストを送る
* URL"/"にリクエストボディ"{\"test\": \"post\"}"で、POSTリクエストが送信された

## リクエストボディとヘッダーを指定してPOSTリクエストを送信できる
* URL"/"にリクエストボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、POSTリクエストを送る
* URL"/"にリクエストボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、POSTリクエストが送信された