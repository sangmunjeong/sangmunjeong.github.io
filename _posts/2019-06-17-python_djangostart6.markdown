---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발6_HTML템플릿"
date:   2019-06-17
categories: python
tags: python django
---
#Django 공부를 위한 웹 페이지를 개발6_HTML템플릿

템플릿은 `blog/templates/blog` 에 저장됩니다.

`blog` 안에 하위 디렉토리 `templates` 를 생성하고 그 하위에 `blog` 디렉토리를 만들어줍니다.

`blog`로 디렉토리로 만들어야하는 이유는 폴더 구조가 복잡해졌을때 좀더 쉽게 찾기위한 관습적인 방법이라고 하네요.

`blog/templates/blog` 에 `post_list.html` 새 파일을 만들어 내용을 넣어줍니다.

```python
<html>
    <p>Hi there!</p>
    <p>It works!</p>
</html>
```

제대로 나오는지 서버를 켜서 확인해봅시다.

본격적인 페이지를 만들어봅시다.

```python
<html>
    <head>
        <title>Django Girls blog</title>
    </head>
    <body>
        <div>
            <h1><a href="">Django Girls Blog</a></h1>
        </div>

        <div>
            <p>published: 14.06.2014, 12:14</p>
            <h2><a href="">My first post</a></h2>
            <p>Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        </div>

        <div>
            <p>published: 14.06.2014, 12:14</p>
            <h2><a href="">My second post</a></h2>
            <p>Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut f.</p>
        </div>
    </body>
</html>
```

이제 위의 HTML이 정상적으로 동작하는지 확인해봅시다.


