# HTTP PUT リクエストを送信するステップのテスト

tags: http-request-test

## PUTリクエストを送信できる
* URL"/"にPUTリクエストを送る
* URL"/"にPUTリクエストが送信された

## PUTリクエストに対するレスポンスをアサートできる
* URL"/"にPUTリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## ボディを指定してPUTリクエストを送信できる
* URL"/"にボディ"{\"test\": \"test\"}"で、PUTリクエストを送る
* URL"/"にボディ"{\"test\": \"test\"}"で、PUTリクエストが送信された

## ヘッダーを指定してPUTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストが送信された

## ヘッダーを複数指定してPUTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、PUTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストが送信された
* URL"/"にヘッダー"options: 1111,2222"で、PUTリクエストが送信された

## ボディとヘッダーを指定してPUTリクエストを送信できる
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストが送信された