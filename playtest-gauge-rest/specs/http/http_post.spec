# HTTP POST リクエストを送信するステップのテスト

tags: http-request-test

## POSTリクエストを送信できる
* URL"/"にPOSTリクエストを送る
* URL"/"にPOSTリクエストが送信された

## POSTリクエストに対するレスポンスをアサートできる
* URL"/"にPOSTリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## リクエストヘッダーを指定してPOSTリクエストを送信できる
* URL"/"にリクエストヘッダー"content-type: application/json"で、POSTリクエストを送る
* URL"/"にリクエストヘッダー"content-type: application/json"で、POSTリクエストが送信された

## リクエストボディを指定してPOSTリクエストを送信できる
* URL"/"にリクエストボディ"{\"test\": \"post\"}"で、POSTリクエストを送る
* URL"/"にリクエストボディ"{\"test\": \"post\"}"で、POSTリクエストが送信された

## リクエストボディとリクエストヘッダーを指定してPOSTリクエストを送信できる
* URL"/"にリクエストボディ"{\"test\": \"test\"}"、リクエストヘッダー"content-type: application/json"で、POSTリクエストを送る
* URL"/"にリクエストボディ"{\"test\": \"test\"}"、リクエストヘッダー"content-type: application/json"で、POSTリクエストが送信された