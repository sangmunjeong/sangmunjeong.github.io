---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발10_장고 CSS"
date:   2019-06-19 15:45
categories: python
tags: python django
---

#Django 공부를 위한 웹 페이지를 개발10_장고 CSS

장고 CSS 적용법에 대해서 정리해보겠습니다.

기본적인 CSS파일 작성법은 동일 합니다.

일단, 부트스트랩을 사용하는 방법을 소개합니다. 물론, 직접 코딩하는것도 좋지만, 이것저것 사용해봅시다.

[부트스트랩(Bootstrap)][bootstrap]은 다양한 HTML, CSS 코드가 있으며 템플릿도 존재합니다.

부트스트랩을 설치하는건 간단합니다.

`.html` 파일의 `<head>` 에 링크를 넣기만 하면 됩니다.

```
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
```

부트스트랩은 인터넷과 연결해서 적용하지만 정적파일로 적용할 수도 있습니다.

익숙한 CSS나 이미지 파일이 여기에 해당하니다.

정적파일은 `blog` 앱에 추가해서 사용하면 됩니다.

blog/static/css 디렉토리에 `blog.css` 를 파일을 추가해서 사용하겠습니다.

간단한 테스트를 해보겠습니다.

`h1 a{ color: #FCA205; }`를 파일에 입력해 니다.

이제 이 css 파일을 html에서 불러와 사용하기 위한 작업을 하겠습니다.

`blog/templates/blog/post_list.html` 파일의 첫줄에 `{% load static %}` 을 추가합니다.

```
{% load static %}
```

그다음 `<head>` 와 `</head>` 사이에 아래의 코드를 추가합니다.

```
<link rel="stylesheet" href="{% static 'css/blog.css' %}">
```

그밖에도 다양한 CSS를 적용할 수 있습니다.

[bootstrap]: https://getbootstrap.com/
