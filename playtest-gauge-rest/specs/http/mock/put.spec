# Mockに対するPUTリクエストのテスト

tags: http-request-test

## MockされたAPIのPUTリクエスト確認をする（文字列）
* URL"/"にボディ"{\"key1\": \"put\", \"key2\": \"value1\"}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に文字列"put"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に文字列"value1"を持つJSONでPUTリクエストされた

## MockされたAPIのPUTリクエスト確認をする（整数値）
* URL"/"にボディ"{\"key1\": 1, \"key2\": 100}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に整数値"1"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に整数値"100"を持つJSONでPUTリクエストされた

## MockされたAPIのPUTリクエスト確認をする（小数値）
* URL"/"にボディ"{\"key1\": 1.0, \"key2\": 100.0}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に小数値"1.0"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に小数値"100.0"を持つJSONでPUTリクエストされた

## MockされたAPIのPUTリクエスト確認をする（真偽値）
* URL"/"にボディ"{\"key1\": {\"key3\": false}, \"key2\": true}"で、PUTリクエストを送る
* API"mockApi"のURL"/"にパス"key1.key3"に真偽値"false"を持つJSONでPUTリクエストされた
* API"mockApi"のURL"/"にパス"key2"に真偽値"true"を持つJSONでPUTリクエストされた

## MockされたAPIのPUTリクエストのヘッダー確認をする（一部）
* URL"/"にヘッダー"options: 1111,2222 \r\n options2: aaa"で、PUTリクエストを送る
* API"mockApi"のURL"/"にヘッダー"options2":"aaa"を含むリクエストをされた