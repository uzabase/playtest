# Mockに対するリクエストのテスト

tags: http-request-test

## MockされたAPIのリクエストのヘッダー確認をする（一部）
* URL"/"にヘッダー"options: 1111,2222 \r\n options2: aaa"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"options2":"aaa"を含むリクエストをされた

## MockされたAPIのリクエストボディを確認をする（文字列）
* URL"/"にボディ"{\"key1\": \"post\", \"key2\": \"value1\"}"で、POSTリクエストを送る
* API"mockApi"のURL"/"にパス"key1"に文字列"post"を持つJSONをリクエストされた
