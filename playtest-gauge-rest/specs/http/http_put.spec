# HTTP PUT リクエストを送信するステップのテスト

tags: http-request-test

## PUTリクエストを送信できる
* URL"/"にPUTリクエストを送る
* URL"/"にPUTリクエストが送信された

## PUTリクエストに対するレスポンスをアサートできる
* URL"/"にPUTリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* JSON形式のレスポンスボディが<file:expected/put-response.json>と一致する

## リクエストボディを指定してPUTリクエストを送信できる
* URL"/"にリクエストボディ"{\"test\": \"test\"}"で、PUTリクエストを送る
* URL"/"にリクエストボディ"{\"test\": \"test\"}"で、PUTリクエストが送信された

## ヘッダーを指定してPUTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストが送信された

## リクエストボディとヘッダーを指定してPUTリクエストを送信できる
* URL"/"にリクエストボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にリクエストボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストが送信された