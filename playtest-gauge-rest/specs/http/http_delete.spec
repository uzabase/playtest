# HTTP DELETE リクエストを送信するステップのテスト

tags: http-request-test

## DELETEリクエストを送信できる
* URL"/"にDELETEリクエストを送る
* URL"/"にDELETEリクエストが送信された

## DELETEリクエストに対するレスポンスをアサートできる
* URL"/"にDELETEリクエストを送る
* レスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## ヘッダーを指定してDELETEリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、DELETEリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、DELETEリクエストが送信された

## ヘッダーを複数指定してDELETEリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、DELETEリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、DELETEリクエストが送信された
* URL"/"にヘッダー"options: 1111,2222"で、DELETEリクエストが送信された