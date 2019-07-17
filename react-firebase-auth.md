# 概要

この記事では Firebase 認証機能を導入する方法について説明します。

# Firebase を React で使えるようにする。

まずは下記のように Firebase の API キーを用意し、その API キーを使って Firebase のインスタンスを作ります。

```js
// パス: ./src/firebase.js

import firebase from 'firebase/app';
import 'firebase/auth';
import 'firebase/firestore';
//APIキープロジェクトごとに異なります。
const firebaseConfig = {
    apiKey: "ghjghjghjGhpsD3pJ01u0",
    authDomain: "inghjalentgatghirebaseapp.com",
    databaseURL: "https://infoirebaseio.com",
    projectId: "infe",
    storageBucket: "infodate.appspot.com",
    messagingSenderId: "9817228",
    appId: "1:980089817228:we69a3b908"
};
//　Firebaseインスタンスを初期化
firebase.initializeApp(firebaseConfig);
firebaseAuth =  firebase.auth();
firebaseCloudStore = firebase.firestore();
export default firebase;
export firebaseAuth, firebaseCloudStore;
```

> firebaseAuth は認証用のインスタンスで firebaseCloudStore はデータベース用のインスタンスです。

# ログインページ

次にログインページでは先ほど firebase.js でエクスポートしたインスタンス (firebaseAuth, firebaseCloudStore)をインポートします。下記のコンポーネントではユーザが入力した email と pass を state に保存しています。ユーザが Submit ボタンを押すと、この state の email, pass を firebaseAuth の SignInWithEmailAndPassword メソッドに引数として渡して実行します。これでもしログインが成功すると.then()の中身が実行され、ログインが失敗すると.catch()の中身が実行されます。

もしログインが成功すれば FirebaseSDK によって認証情報(Token)がブラウザーの LocalStorage に保存され、Firebase のデータベースからデータをアクセスする度々にこの認証情報が**暗黙的にリクエストヘッダーに組み込まれます**。なのでデータのクエリーは今まで通りで大丈夫です。

```js
// パス : ./src/login.js
import React, { Component } from 'react';
import { Input, Button } from 'antd';
import { compose } from 'recompose';
import { withRouter } from 'react-router-dom';
import { firebaseAuth, firebaseCloudStore } from './firebase.js';

class SignInForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      pass: '',
      error: ''
    };
  }

  onChange = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  handleSubmit = () => {
    const { email, pass } = this.state;
    firebaseAuth
      .SignInWithEmailAndPassword(email, pass)
      .then(() => {
        this.setState({ email: '', pass: '', error: '' });
        // ログイン成功したらホームページにリダイレクト
        this.props.history.push('/');
      })
      .catch(error => {
        this.setState({
          error: `エラーが発生しました : ${error.message}`
        });
      });
  };

  render() {
    const { email, pass, error } = this.state;
    return (
      <Style>
        <form>
          <Input
            value={email}
            type="email"
            name="email"
            placeholder="メール"
            onChange={this.onChange}
          />
          <Input
            value={pass}
            type="password"
            name="pass"
            placeholder="パスワード"
            onChange={this.onChange}
          />
          {error && <p>{error}</p>}
          <Button onClick={this.handleSubmit} type="submit" htmlType="button">
            Submit
          </Button>
        </form>
      </Style>
    );
  }
}

const SignInPage = compose(withRouter)(SignInForm);

export default SignInPage;
```

# ページをログインしないと見れないようにする

firebase.auth (firebaseAuth)クラスにはonAuthStateChangedという便利なメソッドがあり、これを使ってユーザの認証状態を取得することができます。 

下記のコードでは ```if (!(authUser)))``` で認証状態を確認し、もし(!authUser)がtrueだったら **(認証されていない場合は(!authUser)がtrueになるので)** ログインページにリダイレクトするようにしています。そうでない場合はauthステートをtrueにします。
authステートをtrueにするとページが描画されます。

```js
// 日本語のコンポーネント名はお勧めしません。
// パス : ./src/コンポーネント太郎.js
class コンポーネント太郎 extends Component {
  constructor(props) {
    super(props);
    this.state = {
      auth: false
    };
  }

  componentDidMount() {
    this.listener = firebaseAuth.onAuthStateChanged(authUser => {
      if (!(authUser)) {
        // 認証されていない場合はログインページへリダイレクト
        this.history.push("/signin");
      } else {
        //認証成功した場合はauthステートをtrueにする
        this.setState({
          auth: true
        });
      }
    });
  }

  componentWillUnmount() {
    this.listener();
  }

  render() {
    const { auth } = this.state;
    // ログインされていない場合は何も表示しない。
    if (!auth) return null;
    return <p>これはログインされているユーザにしか見えません</p>;
  }
}
```

# セキュリティールール

Firebaseのデータにログインされていないユーザーしかアクセスできないようにします。

```js

service cloud.firestore {
  match /databases/{database}/documents {
  match /{document=**} {
   allow read: if if request.auth.uid != null 
   allow write: if request.auth.uid != null;
  }
}
```
先ほど**クライアント側で一回ログインするとそれ以降は認証情報が暗黙的にリクエストヘッダーに組み込まれます**と書きましたが、上記のコードでは実際にバックエンド側でそのヘッダーを検証しています。もしリクエストヘッダーに認証情報(Token)がある場合はそのユーザーはログインされているとみなしデータを返します。リクエストヘッダーがない場合はエラーを返します。

また、データベース全体ではなく特定のコレクションやドキュメントだけにルールを当てることもできます。下記のコードはTalentGateのデータベースで使われているセキュリティールールです。

```js
service cloud.firestore {
  match /databases/{database}/documents {
  match /Private/{doc} {
      allow read: if request.auth.uid != null;
      allow write: if request.auth.uid != null ;
  }
  match /Published/{doc} {
    allow read: if request.auth.uid != null;
    allow write: if request.auth.uid != null ;
  }
  match /{collectionName}/{documentId}/{SubColl}/{SubDoc} {
    allow read: if collectionName != "Private" || "Published";}
    
  match /{collectionName}/{documentId} {
    allow read: if collectionName != "Private" || "Published";}
	}
  
  match /{document=**} {
   allow read, write: if request.auth.uid != null;
}
}
```
ここではPublishedとPrivateコレクションは認証されたユーザしかread,writeすることができなく、それ以外のコレクションは認証されているユーザならread,writeそうでないユーザーはreadしかできません。