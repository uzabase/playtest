# HTTP PUT リクエストを送信するステップのテスト

tags: http-request-test

## PUTリクエストを送信できる
* URL"/"にPUTリクエストを送る
* URL"/"にPUTリクエストが送信された
* API"mockApi"のURL"/"にPUTリクエストされた

## PUTリクエストに対するレスポンスをアサートできる
* URL"/"にPUTリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## ボディを指定してPUTリクエストを送信できる
* URL"/"にボディ"{\"test\": \"test\"}"で、PUTリクエストを送る
* URL"/"にボディ"{\"test\": \"test\"}"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にボディ"/verifications/put.json"JSONファイルの内容でPUTリクエストされた

## ヘッダーを指定してPUTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、PUTリクエストされた

## ヘッダーを複数指定してPUTリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、PUTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にヘッダー"content-type: application/json "で、PUTリクエストされた
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222 "で、PUTリクエストされた
* API"mockApi"のURL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222 "で、PUTリクエストされた
// 不要なヘッダーを付け加えたときには失敗する
API"mockApi"のURL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222 \r\n x-unnecessary: hoge"で、PUTリクエストされた

## ボディとヘッダーを指定してPUTリクエストを送信できる
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にボディ"/verifications/putWithHeader.json"JSONファイルの内容、ヘッダー"content-type: application/json"で、PUTリクエストされた

## MockされたAPIのリクエスト確認をする（文字列）
* URL"/"にボディ"{\"key1\": \"put\", \"key2\": \"value1\"}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に文字列"put"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に文字列"value1"を持つJSONでPUTリクエストされた

## MockされたAPIのリクエスト確認をする（整数値）
* URL"/"にボディ"{\"key1\": 1, \"key2\": 100}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に整数値"1"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に整数値"100"を持つJSONでPUTリクエストされた

## MockされたAPIのリクエスト確認をする（小数値）
* URL"/"にボディ"{\"key1\": 1.0, \"key2\": 100.0}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に小数値"1.0"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に小数値"100.0"を持つJSONでPUTリクエストされた

## MockされたAPIのリクエスト確認をする（真偽値）
* URL"/"にボディ"{\"key1\": {\"key3\": false}, \"key2\": true}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1.key3"に真偽値"false"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に真偽値"true"を持つJSONでPUTリクエストされた