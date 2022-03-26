# Mockに対するPOSTリクエストをアサートできる
tags: http-request-test

## ボディ（文字列）
* URL"/"にボディ"{\"key1\": \"post\", \"key2\": \"value1\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に文字列"post"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に文字列"value1"を持つJSONでPOSTリクエストされた

## ボディ（整数値）
* URL"/"にボディ"{\"key1\": 1, \"key2\": 100}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に整数値"1"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に整数値"100"を持つJSONでPOSTリクエストされた

## ボディ（小数値）
* URL"/"にボディ"{\"key1\": 1.0, \"key2\": 100.0}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に小数値"1.0"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に小数値"100.0"を持つJSONでPOSTリクエストされた

## ボディ（真偽値）
* URL"/"にボディ"{\"key1\": {\"key3\": false}, \"key2\": true}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1.key3"に真偽値"false"を持つJSONでPOSTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に真偽値"true"を持つJSONでPOSTリクエストされた

## ヘッダー（一部）
* URL"/"にヘッダー"options: 1111,2222 \r\n options2: aaa"で、POSTリクエストを送る
* API"mockApi"のURL"/"にヘッダー"options2":"aaa"を含むリクエストをされた

## ボディ（ファイル一致）
* URL"/"にボディ"{\"test\": \"post\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にボディ"/verifications/post.json"JSONファイルの内容でPOSTリクエストされた

## ボディ（ファイル一致）とヘッダー
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、POSTリクエストを送る
* API"mockApi"のURL"/"にボディ"/verifications/postWithHeader.json"JSONファイルの内容、ヘッダー"content-type: application/json"で、POSTリクエストされた
