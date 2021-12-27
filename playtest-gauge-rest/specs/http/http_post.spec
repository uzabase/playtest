# HTTP POST リクエストを送信するステップのテスト

tags: http-request-test

## POSTリクエストを送信できる
* URL"/"にPOSTリクエストを送る
* URL"/"にPOSTリクエストが送信された

## POSTリクエストに対するレスポンスをアサートできる
* URL"/"にPOSTリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## ヘッダーを指定してPOSTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストされた

## ヘッダーを複数指定してPOSTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、POSTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストが送信された
* URL"/"にヘッダー"options: 1111,2222"で、POSTリクエストされた

## ボディを指定してPOSTリクエストを送信できる
* URL"/"にボディ"{\"test\": \"post\"}"で、POSTリクエストを送る
* URL"/"にボディ"/verifications/post.json"JSONファイルの内容でPOSTリクエストされた

## ボディとヘッダーを指定してPOSTリクエストを送信できる
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、POSTリクエストを送る
* URL"/"にボディ"/verifications/postWithHeader.json"JSONファイルの内容、ヘッダー"content-type: application/json"で、POSTリクエストされた