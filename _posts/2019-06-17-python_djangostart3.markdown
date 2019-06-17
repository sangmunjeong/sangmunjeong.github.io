---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발3_장고관리자"
date:   2019-06-17
categories: python
tags: python django
---
# Django 공부를 위한 웹 페이지를 개발3_장고관리자

장고 admin을 다루어보겠습니다.

`blog/admin.py` 를 열어서 내용을 다음과 같이 수정합니다.

```python
from django.contrib import admin
from .models import Post

admin.site.register(Post)
```

코드에서는 앞에서 정의한 `Post` 모델을 가져와서(import)

`admin.site.register(Post)` 로 보여주고 있습니다. 

`python manage.py runserver` 를 실행 후 브라우저에서 `http://127.0.0.1:8000/admin/` 을 입력하면 로그인 페이지를 볼 수 있습니다.

로그인을 하기 위해서는 관리자를 생성해야 합니다.

터미널에서 `python manage.py createsuperuser`를 입력합시다.

```
$ python manage.py createsuperuser
Username: admin
Email address: admin@admin.com
Password:
Password (again):
Superuser created successfully.
```
username 과 email 은 임의로 설정해줍니다.

사용자 등록이 끝났다면 슈버 사용자로 로그인한 후 대시보드를 확인해봅시다.

게시글쪽에도 이것저것 만져봅시다.

