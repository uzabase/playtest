# HTTP POST リクエストを送信するステップのテスト

tags: http-request-test

## POSTリクエストを送信できる
* URL"/"にPOSTリクエストを送る
* URL"/"にPOSTリクエストが送信された
* API"mockApi"のURL"/"にPOSTリクエストされた

## POSTリクエストに対するレスポンスをアサートできる
* URL"/"にPOSTリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## ヘッダーを指定してPOSTリクエストを送信して、リクエストされたか確認できる
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、POSTリクエストされた

## ヘッダーを複数指定してPOSTリクエストを送信して、リクエストされたか確認できる
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、POSTリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、POSTリクエストされた
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222"で、POSTリクエストされた
* API"mockApi"のURL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、POSTリクエストされた
// 不要なヘッダーを付け加えたときには失敗する
API"mockApi"のURL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222 \r\n x-unnecessary: hoge"で、POSTリクエストされた

## ボディを指定してPOSTリクエストを送信して、リクエストされたか確認できる
* URL"/"にボディ"{\"test\": \"post\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にボディ"/verifications/post.json"JSONファイルの内容でPOSTリクエストされた

## ボディとヘッダーを指定してPOSTリクエストを送信して、リクエストされたか確認できる
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、POSTリクエストを送る
* API"mockApi"のURL"/"にボディ"/verifications/postWithHeader.json"JSONファイルの内容、ヘッダー"content-type: application/json"で、POSTリクエストされた

## MockされたAPIのリクエスト確認をする（文字列）
* URL"/"にボディ"{\"key1\": \"post\", \"key2\": \"value1\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に文字列"post"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に文字列"value1"を持つJSONでPOSTリクエストされた

## MockされたAPIのリクエスト確認をする（整数値）
* URL"/"にボディ"{\"key1\": 1, \"key2\": 100}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に整数値"1"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に整数値"100"を持つJSONでPOSTリクエストされた

## MockされたAPIのリクエスト確認をする（小数値）
* URL"/"にボディ"{\"key1\": 1.0, \"key2\": 100.0}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に小数値"1.0"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に小数値"100.0"を持つJSONでPOSTリクエストされた

## MockされたAPIのリクエスト確認をする（真偽値）
* URL"/"にボディ"{\"key1\": {\"key3\": false}, \"key2\": true}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1.key3"に真偽値"false"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に真偽値"true"を持つJSONでPOSTリクエストされた
