---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발4_장고URL"
date:   2019-06-17
categories: python
tags: python django
---
#Django 공부를 위한 웹 페이지를 개발4_장고URL

Django는 `URLconf (URL configuration)` 을 사용합니다.


`URLconf` 는 장고에서 URL과 일치하는 뷰를 찾는 패턴의 집합입니다.

`mysite/urls.py` 파일을 열어 봅시다

```python
"""mysite URL Configuration

[...]
"""
from django.contrib import admin
from django.urls import path

urlpatterns = [
    path('admin/', admin.site.urls),
]
```
주석으로 되어있는 부분은 독스트링(docstring)입니다.

파일, 클래스, 메서드의 윗부분에 작성하며 어떤 일을 수행하는지 알려줍니다.

자 그러면 URL을 만들어보겠습니다.

파일을 깔끔하게 정리하기 위해서 `blog` 어플리케이션에서 메인 `mysite/urls.py` 파일로 url을 가져오겠습니다.

`blog.urls` 를 가져오도록 `include` 함수가 필요합니다.

`from django.urls` 행에서 `import` 뒤에 `include` 를 추가합니다.

```python
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('blog.urls')),
]
```
자, 공간을 만들었으니 이제 파일을 만들어줍니다.

`blog/urls.py` 파일을 만들겠습니다.

```python
from django.urls import path
from . import views

urlpatterns = [
    path('', views.post_list, name='post_list'),
]
```

`http://127.0.0.1:8000/` 에 접속해봅시다.

no attribute `post_list` 가 보이면 올바릅니다.

이제 해당하는 뷰를 만들어 볼 차례가 왔습니다.





