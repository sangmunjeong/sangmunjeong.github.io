---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발9_장고 템플릿"
date:   2019-06-18 11:18
categories: python
tags: python django
---

#Django 공부를 위한 웹 페이지를 개발9_장고 템플릿

장고에는 내장된 템플릿 태그(template tags)가 있어, 이 기능을 주로 사용합니다.

템플릿 태그는 파이썬 코드를 HTML로 바꾸어 웹 화면에 실행할 수 있도록 도와줍니다.

`views.py` 에서 `post_list.html` 로 보낸 `posts` 변수를 실행해봅니다.

`body`에 코드를 넣어 실행하겠습니다.

```
{{ posts }}
```

결과를 보면 쿼리셋에서 받은 모양으로 출력됩니다.

좀더 깔끔하게 정리해서 출력되도록 가다듬어 봅니다.

```
<div>
    <h1><a href="/">Django Girls Blog</a></h1>
</div>

{% for post in posts %}
    <div>
        <p>published: {{ post.published_date }}</p>
        <h1><a href="">{{ post.title }}</a></h1>
        <p>{{ post.text|linebreaksbr }}</p>
    </div>
{% endfor %}
```

반복문을 사용해서 각 리스트의 내용을 불러와 출력해주도록 되어있습니다.

`post.title`, `post.text` 등 각 요소를 가져오는것도 사용되어 있습니다.

각 요소는 `models.py` 에서 `Post`를 생성할때 정의한 파일입니다.

