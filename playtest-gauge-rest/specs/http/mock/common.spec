# Mockに対するリクエストをアサートできる

tags: http-request-test

## ヘッダー（一部）
* URL"/"にヘッダー"options: 1111,2222 \r\n options2: aaa"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"options2":"aaa"を含むリクエストをされた

## ボディ（文字列）
* URL"/"にボディ"{\"key1\": \"post\", \"key2\": \"value1\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に文字列"post"を持つJSONをリクエストされた

## ボディ（整数値）
* URL"/"にボディ"{\"key1\": 1, \"key2\": \"value1\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に整数値"1"を持つJSONをリクエストされた

## ボディ（小数値）
* URL"/"にボディ"{\"key1\": 1.01, \"key2\": \"value1\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に小数値"1.01"を持つJSONをリクエストされた

## ボディ（真偽値）
* URL"/"にボディ"{\"key1\": true, \"key2\": \"value1\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に真偽値"true"を持つJSONをリクエストされた
