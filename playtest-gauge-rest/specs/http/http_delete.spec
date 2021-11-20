# HTTP DELETE リクエストを送信するステップのテスト

tags: http-request-test

## DELETEリクエストを送信できる
* URL"/"にDELETEリクエストを送る
* URL"/"にDELETEリクエストが送信された

## DELETEリクエストに対するレスポンスをアサートできる
* URL"/"にDELETEリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* レスポンスのJSONの"$.message"が文字列の"OK"である

## リクエストヘッダーを指定してDELETEリクエストを送信できる
* URL"/"にリクエストヘッダー"content-type: application/json"で、DELETEリクエストを送る
* URL"/"にリクエストヘッダー"content-type: application/json"で、DELETEリクエストが送信された
