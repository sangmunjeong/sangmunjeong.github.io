---
layout: post
title: "Django 공부를 위한 웹 페이지 개발10_장고CSS"
date:   2019-06-21
categories: python
tags: python django
---
#Django 공부를 위한 웹 페이지 개발10_장고CSS

이번에는 장고에서의 CSS적용법에 대해서 정리해 보겠습니다.

기본적인 CSS파일 작성법은 동일합니다.

일단, 부트스트랩을 사용하는 방법을 소개합니다.

직접코딩하는것도 좋지만 다양한 CSS를 사용할 수 있습니다.

[부트스트랩(Bootstrap)][bootstrap]은 다양한 HTML, CSS 코드가 있으며 다양한 템플릿도 이용가능합니다.

부트스트랩을 이용하는건 간단합니다.

html 헤드에 링크만 넣어주면 됩니다.

```
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
```

부트스트랩은 인터넷과 연결해서 사용합니다.

부트스트랩 이외에 직접 CSS코딩도 가능합니다.

정적파일로 `blog` 앱에 추가해서 사용할 수 있습니다.

경로는 `blog/static/css/blog.css` 로 생성합니다.

테스트를 해봅니다.

```
h1 a{ color: #FCA205; }
```

이 파일을 html에서 호출해 사용합니다.

`blog/templates/blog/post_list.html` 

파일의 첫줄에 다음 코드를 추가합니다.

{% raw %}
```
{% load static %}
```
{% endraw %}

`<head>`에 다음의 코드를 추가합니다.

{% raw %}
```
<link rel="stylesheet" href="{% static 'css/blog.css' %}">
```
{% endraw %}

그 밖에도 다양하게 CSS를 적용해 볼수 있습니다.

[bootstrap]: https://getbootstrap.com/
