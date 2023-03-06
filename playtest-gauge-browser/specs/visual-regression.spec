# Visual Regression Testができる
* [not-provide] ページ"/page"を開く

## 画像が存在しないとき、新規に画像が保存される
* [not-provide] 画像"images/new.png"が存在しない
* ページの見た目が"images/new.png"と一致する
* [not-provide] 画像"images/new.png"が存在する

## 保存されている画像とHTMLの見た目が一致するとき、エラーとならない
* [not-provide] 画像"images/new.png"が存在する
* ページの見た目が"images/success.png"と一致する

## 保存されている画像とHTMLの見た目が一致しないとき（5%以上の差異）、エラーとなり比較画像が保存される
* [not-provide] 「ページの見た目が"images/failure.png"と一致する」を実行するとエラーになる
* [not-provide] 画像"logs/vrresults/images/failure.png"が存在する

## 環境変数UPDATE_IMAGEにtrueが入っているとき、保存されている画像とHTMLの見た目が一致しなくてもエラーとならず、保存されている画像が更新される
* [not-provide] 環境変数"UPDATE_IMAGE"に値"true"を入れる
* [not-provide] ページの見た目が"images/will_update.png"と一致しない
* ページの見た目が"images/will_update.png"と一致する
* [not-provide] ページの見た目が"images/will_update.png"と一致する（更新せず、差異があればエラーとする）
* [not-provide] 環境変数"UPDATE_IMAGE"を削除する
* [not-provide] ファイル"images/_will_update.png.org"を"images/will_update.png"にコピーする