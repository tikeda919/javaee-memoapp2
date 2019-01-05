javaee-memoapp2
===============

EclipseのMavenプロジェクト(maven-archetype-webapp)で作成したJavaEE + MySQLなwebapp習作

- [[Eclipse/Tomcat] MavenのwebappプロジェクトでServlet+JSP - Qiita](https://qiita.com/zaki-lknr/items/8137ac40ebd8f5bdb3c5)
- [Eclipseを使ってTomcat+JSP+Servlet+MySQLでメモアプリ作成 - Qiita](https://qiita.com/zaki-lknr/items/32690b071abf202281d6)
- [JNDIを使ったデータベース接続設定のXMLファイル定義 - Qiita](https://qiita.com/zaki-lknr/items/2bd955df62d4de0528ac)

## Build

`pom.xml`ファイルのあるディレクトリで`mvn package`を実行すると、`target`ディレクトリ以下に`memoapp2.war`が作成される。

## Require

MySQL

設定は`src/main/webapp/META-INF/context.xml`

## Licence

[MIT](https://opensource.org/licenses/MIT)